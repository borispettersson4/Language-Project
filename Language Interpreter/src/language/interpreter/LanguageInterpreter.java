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
       
 //FailSafe
       if (aString == ""){
           do{
           System.out.println("You've pressed enter, please try inserting a valid variable name: ");
           aString = input.nextLine();
           aElement.setBody(aString);
           }while(aString == "");
       }
        
       //Check if is integer
       if(aElement.isInteger()) {
           aElement.convertToInt();
           if(aElement.isSignedInteger())
            System.out.println("This is a signed Integer!"); 
           else if(aElement.isUnsignedInteger())
            System.out.println("This is an unsigned Integer!");
           else if(aElement.isDigit())                  //Check if it's a digit
               System.out.println("This is an unsigned Integer and a digit");
           
        //Check if element is exponent
       if(aElement.isExponent()){
         System.out.println("This number has an exponent.");
       }
       }
       //Check if is double
       else if(aElement.isDouble()) {
           aElement.convertToDouble();
           if(aElement.isSignedDouble())
           System.out.println("This is a signed Real Number!");   
           else 
           System.out.println("This is an unsigned Real Number!");   
           
       if(aElement.isExponent()){
         System.out.println("This number has an exponent.");
       }
       }
       
       //Check if it's the decimal point
       else if(aElement.hasDecimalPoint()){
         System.out.println("This is the decimal point for the usage of real numbers.");
       }
             
       //Check if the element is a sign
       else if(aElement.isSign()){
           System.out.println("This a Sign.");
       if(aElement.signType() == 1)
           System.out.println("This is the + sign.");
       else if (aElement.signType() == 2)
           System.out.println("This is the - sign.");
       }
        
       //Check if is mathematical operator
       else if(aElement.isMathOperator()) {
          System.out.println("This is a mathematical operator!");
       if(aElement.MathOperatorType() == 1)
           System.out.println("This is addition");
       else if(aElement.MathOperatorType() == 2)
           System.out.println("This is substraction");
       else if(aElement.MathOperatorType() == 3)
           System.out.println("This is multiplication");
       else if(aElement.MathOperatorType() == 4)
           System.out.println("This is a division");
       }
           //Check if is assigner
       else if(aElement.isAssigner()) {
          System.out.println("This is the assigner operator!");
       }
       
       //Check if it's the print statement
       else if (aElement.isPrint()){
           System.out.println("This is the print statement.");
       }
       
        //Check if it's the read statement
       else if (aElement.isRead()){
           System.out.println("This is the read statement.");
       }
       // Check if it's the if statement
       else if (aElement.isIf()){
           System.out.println("This is the if statement.");
       }
       //Check if it's the then statement
       else if(aElement.isThen()){
           System.out.println("This is the then statement.");
       }
       
       //Check if it's the end statement
       else if(aElement.isEND()){
           System.out.println("This is the END statement.");
       }
       
       
          //Check if is operator
       else if(aElement.isOperator()) {
          System.out.println("This is an operator!");
       if(aElement.OperatorType() == 1)
           System.out.println("This is a logical OR operator");
       else if(aElement.OperatorType() == 2)
           System.out.println("This is a logical AND operator");
       else if(aElement.OperatorType() == 3)
           System.out.println("This is a logical NOT oprator");
       else if(aElement.OperatorType() == 4)
           System.out.println("This is a comparative EQUAL operator");
       else if(aElement.OperatorType() == 5)
           System.out.println("This is a comparative NOT EQUAL operator");
       else if(aElement.OperatorType() == 6)
           System.out.println("This is a comparative LESS THAN operator");
       else if(aElement.OperatorType() == 7)
           System.out.println("This is a comparative LESS EQUAL operator");
       else if(aElement.OperatorType() == 8)
           System.out.println("This is a comparative GREATER THAN operator");
       else if(aElement.OperatorType() == 9)
           System.out.println("This is a comparative GREATER EQUAL operator");
       }
       //Check if is variable
       else if(aElement.isVariable()) {
          System.out.println("This is a variable!");
       if(aElement.variableType() == 1)
           System.out.println("The variable type is Integer");
       else if(aElement.variableType() == 2)
           System.out.println("The variable type is Real Number");
       else if(aElement.variableType() == 3)
           System.out.println("The variable type is String");
       }
       
       else if(aElement.isString())
        System.out.println("This is a String!");
       
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