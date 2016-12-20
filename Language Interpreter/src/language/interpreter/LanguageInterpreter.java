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
       Identifier aElement = new Identifier();
       
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter a variable name");
       String aString = input.nextLine();
       aElement.setBody(aString);
       
 
       if (aString != null){
           aString = " ";
           do{
           System.out.println("You've pressed enter, please try inserting a valid variable name: ");
           aString = input.nextLine();
           aElement.setBody(aString);
           }while(aString == " ");
       }
        
       
               
       if(aElement.isVariable()) {
          System.out.println("This is a variable!");
       if(aElement.variableType() == 1)
           System.out.println("This is an Integer");
       else if(aElement.variableType() == 2)
           System.out.println("This is a Real Number");
       else if(aElement.variableType() == 3)
           System.out.println("This is a String");
       }
       else 
           System.out.println("This is not a variable");
        
       //if(aString != null)
        //System.out.println("you've netered the enter key");
        System.out.println("You've entered : " + aElement.getBody());
    }      
}