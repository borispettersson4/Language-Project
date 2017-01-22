/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package language.interpreter;

import java.util.ArrayList;

/**
 *___________________________________________________________________________________
 *                                                                                                                                               
 *                            Boris M. Ruiz Pettersson                                                        
 *                                  CECS 3210-22                                                                                     
 *                                                                                                                  
 *                     Universidad Politecnica de Puerto Rico                                                                                                  
 *                                                                                                                                                  
 *___________________________________________________________________________________ 
 *
 * @author Boris Ruiz Pettersson
 */
public class Line {

    public ArrayList<Identifier> tokens = new ArrayList<Identifier>();
    public boolean isThereErrors = false;

    public Line() {
    }

    public ArrayList<Identifier> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Identifier> tokens) {
        this.tokens = tokens;
    }
    
    //Syntax Strucutre Checker

     // CHECK IF EXPRESSION
    
    boolean isAssignment(int i) {
        return (tokens.get(0 + i).isVariable() && tokens.get(1 + i).isAssigner()) && (tokens.get(2 + i).isVariable() || isArithmeticExpression(2 + i));
    }
      
    boolean isArithmeticExpression(int i){
        return (tokens.get(i + 0).isNumber() && ((tokens.size() > i + 1) && tokens.get(i + 1).isMathOperator()) && ((tokens.size() > i + 2) && isArithmeticExpression(i + 2)))
                || (tokens.get(i + 0).isNumber() && !((tokens.size() > i + 1)));
    }
    
     // CHECK IF COMMENT
    
    boolean isCommentStatement() {
        return (tokens.get(0).isComment() && tokens.get(1).isString()) || tokens.get(0).isComment();
    }
    
     // CHECK IF PRINT

    boolean isPrintStatement() {
        return tokens.get(0).isPrint() || (tokens.get(0).isPrint() && (tokens.get(1).isVariable() || tokens.get(1).isNumber() || tokens.get(1).isString()));
    }
         
     // CHECK IF READ
    
    boolean isReadStatement() {
        return (tokens.get(0).isRead() && tokens.get(1).isVariable());
    }
    
     // CHECK IF (IF STATEMENT)
    
    boolean isIfStatement(int i) {
        return (tokens.get(0 + i).isIf() && conditionalStatement(1 + i));
    }
    
    boolean conditionalStatement(int i) {
                  //First Bit
        return  (((tokens.get(0 + i).isNumberVariable() || tokens.get(0 + i).isNumber() || isArithmeticExpression(0 + i)) 
                 //Operator
                 && tokens.get(1 + i).isOperator() && conditionalStatement(2 + i))
                //Second bit
                 || ( tokens.get(0 + i).isNumberVariable() || tokens.get(0 + i).isNumber() || isArithmeticExpression(0 + i))); 
    }
      
    boolean isThenStatement(int i) {
           if(isIfStatement(0 + i)){
               i += 1;
           }
             while(conditionalStatement(1 + i)){
               i += 1;
           }
             
        return (tokens.get(0 + i).isThen() && thenStatement(1 + i));
    }
    
    boolean thenStatement(int i) {
        return (isAssignment(i + 0));
    }
    
     // CHECK IF END
    
    boolean isEndStatement() {
        return (tokens.get(0).isEND());
    }
    
    //Validate Expression
    boolean checkAssignmentStatement(int i) {
        
        boolean x = false;
        
        //Check if declared Real variable
        if(!tokens.get(0 + i).isKeyWord() && !tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isRealVariable()) {
            //Check if there is an assigner
            if((tokens.size() > i + 1) && tokens.get(1 + i).isAssigner()) {
                
                if(tokens.size() < 2 + i)
                System.out.println("SYNTAX ERROR : Assigner cannot be empty ");
                else if (tokens.get(2 + i).isKeyWord())
                System.out.println("SYNTAX ERROR : Illegal start of expression");
                else if (tokens.get(2 + i).isStringVariable() || tokens.get(2 + i).isString())
                System.out.println("SYNTAX ERROR : Cannot convert Real to String");
                else if (isArithmeticExpression(2 + i)) {
                System.out.println("This checks out fine");
                x = true;
                }
                else 
                System.out.println("SYNTAX ERROR : Missing Operator or Operator Mismatch");
            } //After decleration
            else if((tokens.size() > i + 1) && !tokens.get(1 + i).isAssigner())
                 System.out.println("SYNTAX ERROR : This is not a statement");
            else
                 System.out.println("SYNTAX ERROR : Variable not initialized ");
        } //After Decleration
        
        //Check if declared Int Variable
        else if(!tokens.get(0 + i).isKeyWord() && !tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isIntVariable()) {
            //Check if there is an assigner
            if((tokens.size() > i + 1) && tokens.get(1 + i).isAssigner()) {
                
                if(tokens.size() < 2 + i)
                System.out.println("SYNTAX ERROR : Assignment cannot be empty ");
                else if (tokens.get(2 + i).isKeyWord())
                System.out.println("SYNTAX ERROR : Illegal start of expression");
                else if (tokens.get(2 + i).isStringVariable() || tokens.get(2 + i).isString())
                System.out.println("SYNTAX ERROR : Cannot convert Int to String");
                else if (tokens.get(2 + i).isStringVariable() || tokens.get(2 + i).isRealVariable() || tokens.get(2 + i).isDouble())
                System.out.println("SYNTAX ERROR : Cannot convert Int to Real");
                else if (isArithmeticExpression(2 + i)) {
                System.out.println("This checks out fine");
                x = true;
                }
                else 
                System.out.println("SYNTAX ERROR : Missing Operator or Operator Mismatch");

            } //After decleration
            else if((tokens.size() > i + 1) && !tokens.get(1 + i).isAssigner())
                 System.out.println("SYNTAX ERROR : This is not a statement");
            else
                 System.out.println("SYNTAX ERROR : Variable not initialized ");
        } //After Decleration
        
          //Check if declared String Variable
        else if(!tokens.get(0 + i).isKeyWord() && !tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isStringVariable()) {
            //Check if there is an assigner
            if((tokens.size() > i + 1) && tokens.get(1 + i).isAssigner()) {
                
                if(tokens.size() < 2 + i)
                System.out.println("SYNTAX ERROR : Assignment cannot be empty ");
                else if (tokens.get(2 + i).isKeyWord())
                System.out.println("SYNTAX ERROR : Illegal start of expression");
                else if (tokens.get(2 + i).isIntVariable() || tokens.get(2 + i).isInteger())
                System.out.println("SYNTAX ERROR : Cannot convert String to Int");
                else if (tokens.get(2 + i).isRealVariable() || tokens.get(2 + i).isDouble())
                System.out.println("SYNTAX ERROR : Cannot convert String to Real");
                else if (tokens.get(2 + i).isString() || tokens.get(2 + i).isStringVariable()) {
                System.out.println("This checks out fine");
                x = true;
                }
                else 
                System.out.println("SYNTAX ERROR : Missing Operator or Operator Mismatch");

            } //After decleration
            else if((tokens.size() > i + 1) && !tokens.get(1 + i).isAssigner())
                 System.out.println("SYNTAX ERROR : This is not a statement");
            else
                 System.out.println("SYNTAX ERROR : Variable not initialized ");
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    boolean checkCommentStatement(int i) {
        
        boolean x = false;
        
        //Check if Comment
        if(!tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isComment()) {
            //Check if there is a Sting
            if((tokens.size() > i + 1) && tokens.get(1 + i).isString()) {
                System.out.println("This checks out fine");
                x = true;
            } //After decleration
           
            else if ((tokens.size() > i + 1) && !tokens.get(1 + i).isString())
                System.out.println("SYNTAX ERROR : You can only remark with a string");
           
            else {
                 System.out.println("This checks out fine");
                 x = true;
            }
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    boolean checkPrintStatement(int i) {
        
        boolean x = false;
        
        //Check if Print
        if(!tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isPrint()) {
            //Check if there is a Sting
            if((tokens.size() > i + 1) && !tokens.get(1 + i).isKeyWord() && (tokens.get(1 + i).isString() || tokens.get(1 + i).isVariable() || tokens.get(1 + i).isNumber())) {
                System.out.println("This checks out fine");
                x = true;
            } //After decleration
           
            else if ((tokens.size() > i + 1) && (!tokens.get(1 + i).isString() || !tokens.get(1 + i).isVariable() || !tokens.get(1 + i).isNumber() || tokens.get(1 + i).isKeyWord()))
                System.out.println("SYNTAX ERROR : You can only print a value");
           
            else {
                 System.out.println("This checks out fine");
                 x = true;
            }
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    boolean checkReadStatement(int i) {
        
        boolean x = false;
        
        //Check if Print
        if(!tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isRead()) {
            //Check if there is a Sting
            if((tokens.size() > i + 1) && !tokens.get(1 + i).isKeyWord() && tokens.get(1 + i).isVariable()) {
                System.out.println("This checks out fine");
                x = true;
            } //After decleration
           
            else if ((tokens.size() > i + 1) && (!tokens.get(1 + i).isVariable() ||  tokens.get(1 + i).isKeyWord()))
                System.out.println("SYNTAX ERROR : You can only read a variable");
           
            else {
                 System.out.println("SYNTAX ERROR : Read statement doensn't have parameter");
                 x = true;
            }
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    boolean checkEndStatement(int i) {
        
        boolean x = false;
        
        //Check if Print
        if(!tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isEND()) {
            //Check if there is a Sting
            if(!(tokens.size() > i + 1)) {
                System.out.println("This checks out fine");
                x = true;
            } //After decleration
           
            else {
                 System.out.println("SYNTAX ERROR : End statement should the last statement in the program");
                 x = true;
            }
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    boolean checkIfThenStatement(int i) {
        
        boolean x = false;
        
        //Check if declared Real variable
        if(!tokens.get(0 + i).isUnknown() && tokens.get(0 + i).isIf()) {
            //Check if there is an assigner
            if((tokens.size() > i + 1) && conditionalStatement(i + 1)) {
                
                                
                //Checks what's the condition
                while(!tokens.get(i).isThen() && (tokens.size() > i + 2) && conditionalStatement(i + 1)){
                    
                 i = i + 2;
                 
                }
                
                
                
                
                
                if (((tokens.size() > i) && tokens.get(i).isThen()) && ((tokens.size() > i + 1) && (checkAssignmentStatement(i + 1) || checkCommentStatement(i + 1) || checkPrintStatement(i + 1)))) {
                System.out.println("This checks out fine");
                x = true;
                }
                else if(!(tokens.size() > i))
                System.out.println("SYNTAX ERROR : THEN Statement is missing");
                
                else if(!(tokens.size() > i + 1))
                System.out.println("SYNTAX ERROR : THEN Statement Incomplete");
            } //After decleration
         //   else if((tokens.size() > i + 1) && !tokens.get(1 + i).isAssigner())
          //       System.out.println("SYNTAX ERROR : This is not a statement");
            else if((tokens.size() > i + 1) && !conditionalStatement(i + 1))
                 System.out.println("SYNTAX ERROR : IF Statement cannot be used like this");
            else 
                 System.out.println("SYNTAX ERROR : IF Statement Incomplete");
            
        } //After Decleration
        
        return x; // End of Check
        
        }//Void
    
    void checkSyntax() {
    if(!(checkAssignmentStatement(0) || checkCommentStatement(0) || checkPrintStatement(0) || checkReadStatement(0) || checkEndStatement(0) || checkIfThenStatement(0)))
        System.out.println("SYNTAX ERROR : This is not a statement");
        }
    }
