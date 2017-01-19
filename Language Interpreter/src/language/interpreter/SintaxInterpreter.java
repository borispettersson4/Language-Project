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
        
       ArrayList<Identifier> identifiers = new ArrayList<Identifier>();
       Scanner input = new Scanner(System.in);    
       System.out.println("Enter an instruction: ");
       String aString = input.nextLine();
       
       String[] tokens = aString.split(" ");
       
       
      for(int i = 0; i < tokens.length ; i++) {
          
          if(!tokens[i].equals("")) {
          identifiers.add(new Identifier(tokens[i]));
          if(!identifiers.get(i).isUnknown())
          System.out.println("Found Token : " + identifiers.get(i).getBody());
          else
          System.out.println("Found Uknown Token : " + identifiers.get(i).getBody());
          }
       }
    }
       

    }     
