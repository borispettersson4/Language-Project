/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.interpreter;

import java.util.ArrayList;
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
        
       Line aLine = new Line();
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter an instruction: ");
       String aString = input.nextLine();
       
       String[] tokens = aString.split(" ");
       
       
      for(int i = 0; i < tokens.length ; i++) {
          
          if(!tokens[i].equals("")) {
          aLine.getTokens().add(new Identifier(tokens[i]));
          }
       }
   
      aLine.checkSyntax();
    
    
    
    
    
    
    
    
    } //End Main

    }     
