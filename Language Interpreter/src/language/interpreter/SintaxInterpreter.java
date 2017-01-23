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
          
          if(tokens[i].charAt(0) != '"') {
          if(!tokens[i].equals("")) 
          aLine.getTokens().add(new Identifier(tokens[i]));
          }
          else
              for(int j = 1; j < tokens.length; j++) {
              
                  if(tokens[i].charAt(tokens[i].length() - 1) == '"' && tokens[i].charAt(0) == '"') {
                      aLine.getTokens().add(new Identifier(tokens[i]));
                    //  i += j;
                      break;
                  }
                  
                  else if(tokens.length > i + j && tokens[i + j].charAt(tokens[i + j].length() - 1) == '"') {
                      tokens[i] = tokens[i] + " " + tokens[i + j];
                      aLine.getTokens().add(new Identifier(tokens[i]));
                      i += j;
                      break;
                  }
                  else 
                      {
                       tokens[i] = tokens[i] + " " + tokens[i + j];
                    //  System.out.println("Adding to Word");
                      }
              }
          
         
       }
      
   //   for(int i = 0; i < tokens.length ; i++)
           //    if(aLine.getTokens().size() > i)
           //       System.out.println(aLine.getTokens().get(i).getBody());
   
      aLine.checkSyntax();
    
    
    
    
    
    
    
    
    } //End Main

    }     
