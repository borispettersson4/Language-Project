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
public class SintaxInterpreter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
       final int CONST = 40;
       TokenInterpreter [] aElement = new TokenInterpreter[CONST];
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter an instruction: ");
       String aString = input.nextLine();
       
       
       
       boolean foundAllElements = false;
       for(int k = 0; k < CONST; k++) {
       for(int i = 0 ;!foundAllElements; i++) {
           if(aString.charAt(i) != '\0' && aString.charAt(i) != ' '){
               boolean isAnElement = false;
               for(int j = 0; !isAnElement ; i++) {
                   
                   if(!(aString.charAt(i + j) != '\0' && aString.charAt(i + j) != ' ')) {
                       aElement[k].aString = aString.substring(i, j);
                       isAnElement = true;
                       i += j + 1;
                       break;
                   }
                       
                       
               }
               
             break;  
           
       }
           else if(aString.charAt(i) == '\0')
               foundAllElements = true;
       }
       
       System.out.println("Found Element " + k);
       }
       

    }     
}
