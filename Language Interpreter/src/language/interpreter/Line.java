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

    public Line() {
    }

    public ArrayList<Identifier> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Identifier> tokens) {
        this.tokens = tokens;
    }

     // CHECK IF EXPRESSION
    
    boolean isAssignment(int i) {
        return (tokens.get(0 + i).isVariable() && tokens.get(1 + i).isAssigner()) && (tokens.get(2 + i).isVariable() || isArithmeticExpression(2 + i));
    }
      
    boolean isArithmeticExpression(int i){
        return ((tokens.get(i + 0).isNumber() && tokens.get(i + 1).isMathOperator()) && tokens.get(i + 2).isNumber()) || tokens.get( i + 0).isNumber();
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
        return   ((isArithmeticExpression(0 + i) || tokens.get(0 + i).isNumberVariable())) 
              || ((isArithmeticExpression(0 + i) || tokens.get(0 + i).isNumberVariable()) && tokens.get(1 + i).isOperator() && conditionalStatement(2 + i));
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
}
