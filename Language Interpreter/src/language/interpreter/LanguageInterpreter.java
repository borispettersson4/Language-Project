/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.interpreter;

import java.util.Scanner;

/**
 *
 */
public class LanguageInterpreter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
            String done;
        do{
        
       Identifier aElement = new Identifier();
       
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter anything: ");
       String aString = input.nextLine();
       aString.trim();
       aElement.setBody(aString);
       
// //FailSafe
//       if (aString == ""){
//           do{
//           System.out.println("You've pressed enter, please try inserting a valid variable name: ");
//           aString = input.nextLine();
//           aElement.setBody(aString);
//           }while(aString == "");
//       }                                                
//       
//        //Check if element is exponent
//     

          //Check if is a sign.
          if(aElement.isSign()){
            System.out.println("This is a sign");
          if(aElement.signType() == 1 )
            System.out.println("This is the sign, +");
          else System.out.println("This is the sign, -");
          }else
              
//       //Check if is integer      
            if(aElement.isInteger()) {
           aElement.convertToInt();
           if(aElement.isSignedInteger())
            System.out.println("This is a signed Integer!"); 
           else if(!aElement.isSignedInteger())
            System.out.println("This is an unsigned Integer!");
           else;
           if(aElement.isDigit())                  //Check if it's a digit
               System.out.println("This unsigned integer is a digit");
           }

            if((aElement.isInteger() && aElement.hasExponent())){
                System.out.println("This integer has exponent.");
            }
               
           
       //Check if is double
       else if((aElement.contains(".") && aElement.isDouble()) || (!aElement.hasExponent())) { 
           aElement.convertToDouble();
           if(aElement.isSignedDouble())
           System.out.println("This is a signed Real Number!");   
           else
           System.out.println("This is an unsigned Real Number!");   
           
       if(aElement.hasExponent()){
         System.out.println("This realnumber has an exponent.");
       }
       }
       
       //Check if it's the decimal point
       else if(aElement.isDecimalPoint()){
         System.out.println("This is the decimal point");
       }
        
       
       //Check if is mathematical operator
       else if(aElement.isMathOperator()) 
       {
          System.out.println("This is a Arithmetic operator!");
       if(aElement.MathOperatorType() == 1)
           System.out.println("This is the arithmetic operator for addition");
       else if(aElement.MathOperatorType() == 2)
           System.out.println("This is the arithmetic for substraction");
       else if(aElement.MathOperatorType() == 3)
           System.out.println("This is the arithmetic operator for multiplication");
       else if(aElement.MathOperatorType() == 4)
           System.out.println("This is the arithmetic operator for division");
       }
        //Check if is assigner keyword 
       else   
       if(aElement.isAssigner()) 
          System.out.println("This is the assigner operator!");
       
       
       //Check if it's the print keyword
       else if (aElement.isPrint()){
           System.out.println("This is the keyword, PRINT");
       }
       
        //Check if it's the read keyword
       else if (aElement.isRead()){
           System.out.println("This is the keyword, READ");
       }
       // Check if it's the if keyword
       else if (aElement.isIf()){
           System.out.println("This is the keyword, IF");
       }
       //Check if it's the then keyword
       else if(aElement.isThen()){
           System.out.println("This is the the keyword, THEN");
       }
       
       //Check if it's the end keyword
       else if(aElement.isEND()){
           System.out.println("This is the END KeyWord.");
       }
       
       
          //Check if is operator
       else if(aElement.isOperator()) {
          System.out.println("This is an operator!");
       if(aElement.OperatorType() == 1)
           System.out.println("This is the logical operator, OR");
       else if(aElement.OperatorType() == 2)
           System.out.println("This is the logical operator, AND");
       else if(aElement.OperatorType() == 3)
           System.out.println("This is the logical operator, NOT");
       else if(aElement.OperatorType() == 4)
           System.out.println("This is the relational operator, EQUAL");
       else if(aElement.OperatorType() == 5)
           System.out.println("This is a relational operator, NOT EQUAL");
       else if(aElement.OperatorType() == 6)
           System.out.println("This is the relational operator, LESS THAN");
       else if(aElement.OperatorType() == 7)
           System.out.println("This is the relational operator, LESS EQUAL");
       else if(aElement.OperatorType() == 8)
           System.out.println("This is the relational operator, GREATER THAN");
       else if(aElement.OperatorType() == 9)
           System.out.println("This is the relational operator, GREATER EQUAL");
       }
        //Check if is a String
       else if(aElement.isString())
        System.out.println("This is a String!");
       
       //Check if it's the comment keyword
        else if(aElement.isComment())
           System.out.println("This is the keyword, REM");//IS REM A KEYWORD?
       //Check if is variable
       else if(aElement.isVariable()) {
          System.out.println("This is a variable!");
       if(aElement.variableType() == 1)
           System.out.println("This variable is for Integers");
       else if(aElement.variableType() == 2)
           System.out.println("The variable is for Doubles");
       else if(aElement.variableType() == 3)
           System.out.println("The variable is for Strings");
       }
       
       else
       
      System.out.println("This is an unkown token");
       
  
          
      
            //Final Verdict
        
       System.out.println("You've entered: " + aElement.getBody());
               
        System.out.println("");
        
        System.out.println("If you wish to end the program type in DONE, if not go ahead and press enter the program will then ask for another entry.");
        done = input.nextLine();
        }while(!(done.contains("DONE")|| done.contains("done")||done.contains("Done")||done.contains("dONE")));
    }      
}
