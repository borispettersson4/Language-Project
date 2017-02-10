/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.interpreter;

import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptException;

/**
 *
 */
public class SintaxInterpreter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ScriptException 
    {
        
       Code aCode = new Code();
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter your instructions. If pressed enter twice, program will execute.");
       
       String myString = input.nextLine();
       aCode.lines.add(scanLine(myString));
       
       for(int i = 0; !aCode.lines.get(i).isEndStatement(); i++) {
           myString = input.nextLine();
           aCode.lines.add(scanLine(myString));
       }
       
       System.out.println();
       System.out.println();
       System.out.println("---------------------- CODE EXECUTION ----------------------");
       System.out.println();
       aCode.execute();
       
       
    } //End Main
    
    public static Line scanLine(String aString){
        
      Line aLine = new Line();
      String[] tokens = aString.split(" +");
      
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
            //       System.out.println(aLine.getTokens().get(i).getBody());
                  
              }
         
       }
      
   //   for(int i = 0; i < tokens.length ; i++)
           //    if(aLine.getTokens().size() > i)
           //       System.out.println(aLine.getTokens().get(i).getBody());
   
      aLine.checkSyntax();
    
    return aLine;
    
    }

    }     
