/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.interpreter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.script.ScriptException;

/**
 *
 */
public class CodeInterpreter {
    
    public static String filePath; //Path of input file
    
    /**
     * @param args the command line arguments
     */
    public void runProgram() throws ScriptException, IOException 
    {
        
       Code aCode = new Code();
       Scanner s = new Scanner(new File(filePath));
       List<String> fileLines = Files.readAllLines(Paths.get(filePath));
       for(String line:fileLines){
          aCode.lines.add(scanLine(line));
     }
     
       
       
     //  String myString = input.nextLine();
    //   aCode.lines.add(scanLine(myString));
       
       
       
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
