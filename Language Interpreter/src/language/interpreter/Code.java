/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package language.interpreter;

import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
public class Code {
    
    public ArrayList<Line> lines = new ArrayList<Line>();
    //Variable DataBase
    public ArrayList<IntegerVariable> ints = new ArrayList<IntegerVariable>();
    public ArrayList<RealVariable> reals = new ArrayList<RealVariable>();
    public ArrayList<StringVariable> strings = new ArrayList<StringVariable>();
    
    public boolean doAssignment(int i) throws ScriptException{
        boolean x = false;
        
        if(lines.get(i + 0).checkAssignmentStatement(0)){
            
            if(lines.get(i + 0).getTokens().get(0).isIntVariable()) {
                
                String expression = "";
                
                //Convert the LAO terms to Java
                for(int k = 0; k < lines.get(i + 0).getTokens().size(); k++) {
                    if(lines.get(i + 0).getTokens().size() > 2 + k) {
                        
                        if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".add.") {
                           expression += "+";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".sub.") {
                           expression += "-";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".div.") {
                           expression += "/";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".mul.") {
                           expression += "*";
                           expression += " ";
                        }
                        else {
                        expression += lines.get(i + 0).getTokens().get(2 + k).toString();
                        expression += " ";
                        }
                        
                    }
                }
                
                               
                //Parse to Int
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");
                int  result = ((Double) engine.eval(expression)).intValue();
                
                
                //Save Data
                IntegerVariable a = new IntegerVariable();
                a.value = result;
                a.name = lines.get(i + 0).getTokens().get(0).toString();
                ints.add(a);
            }//Check Integer
            
            else if(lines.get(i + 0).getTokens().get(0).isRealVariable()) {
                
                String expression = "";
                
                //Convert the LAO terms to Java
                for(int k = 0; k < lines.get(i + 0).getTokens().size(); k++) {
                    if(lines.get(i + 0).getTokens().size() > 2 + k) {
                        
                        if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".add.") {
                           expression += "+";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".sub.") {
                           expression += "-";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".div.") {
                           expression += "/";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(2 + k).toString() == ".mul.") {
                           expression += "*";
                           expression += " ";
                        }
                        else {
                        expression += lines.get(i + 0).getTokens().get(2 + k).toString();
                        expression += " ";
                        }
                        
                    }
                }
                
                               
                //Parse to Real
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");
                double  result = ((Double) engine.eval(expression)).doubleValue();
                
                
                //Save Data
                RealVariable a = new RealVariable();
                a.value = result;
                a.name = lines.get(i + 0).getTokens().get(0).toString();
                reals.add(a);
            }//Check Real
            
             else if(lines.get(i + 0).getTokens().get(0).isStringVariable()) {
                            
                //Save Data
                StringVariable a = new StringVariable();
                a.value = lines.get(i + 0).getTokens().get(2).toString();
                a.name = lines.get(i + 0).getTokens().get(0).toString();
                strings.add(a);
            }//Check Real
                      
            x = true;
        }
        
        return x;
    }
    
    public boolean doPrint(int i) {
        boolean x = false;
        
        if(lines.get(i + 0).isPrintStatement()) {
            
            if(lines.get(i + 0).getTokens().get(1).isString()) {
                System.out.println(lines.get(i + 0).getTokens().get(1).toString());
                x = true;
            }
            else if(lines.get(i + 0).getTokens().get(1).isIntVariable()) {
                
                for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() > k + 1 && lines.get(i + 0).getTokens().get(1).toString() == ints.get(k).name) {
                        System.out.println(ints.get(k).value);
                        x = true;
                       break; 
                    }
                    else {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                         endProgramError();
                    }
                    
                }//FOR
            }//ELSE IF
            else if(lines.get(i + 0).getTokens().get(1).isRealVariable()) {
                
                for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() > k + 1 && lines.get(i + 0).getTokens().get(1).toString() == reals.get(k).name) {
                        System.out.println(reals.get(k).value);
                        x = true;
                       break; 
                    }
                    else {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                         endProgramError();
                    }
                    
                }//FOR
            }//ELSE IF
            else if(lines.get(i + 0).getTokens().get(1).isStringVariable()) {
                
                for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() > k + 1 && lines.get(i + 0).getTokens().get(1).toString() == strings.get(k).name) {
                        System.out.println(strings.get(k).value);
                        x = true;
                       break; 
                    }
                    else {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                         endProgramError();
                    }
                    
                }//FOR
            }//ELSE IF
            
        }
        
        return x;
    }
    
    public boolean doRead(int i) {
        boolean x = false;
        
        if(lines.get(i + 0).isReadStatement()){
           
            //If reading an Int
            if(lines.get(i + 0).getTokens().get(1).isIntVariable()){
                
            for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() > k + 1 && lines.get(i + 0).getTokens().get(1).toString() == ints.get(k).name) {
                         Scanner input = new Scanner(System.in);    
                         ints.get(k).value = input.nextInt();
                        x = true;
                       break; 
                    }
                    else if(ints.size() > k + 1 && lines.get(i + 0).getTokens().get(1).toString() != ints.get(ints.size()).name) {
                         Scanner input = new Scanner(System.in); 
                         IntegerVariable a = new IntegerVariable();
                         a.value = input.nextInt();
                         a.name = lines.get(i + 0).getTokens().get(1).toString();
                         ints.add(a);
                        x = true;
                       break; 
                    }
                    
                }//FOR
            } //IF 
            
            
        }
        
        return x;
    }
      
    boolean conditionalPart(int i, int j){
        boolean x = false;
        
        if((lines.get(i + 0).getTokens().get(j + 0).isIntVariable() || lines.get(i + 0).getTokens().get(j + 0).isRealVariable()) && (lines.get(i + 0).getTokens().get(j + 2).isIntVariable() || lines.get(i + 0).getTokens().get(j + 2).isRealVariable())) {
            
            RealVariable a = new RealVariable();
            RealVariable b = new RealVariable();
            String middle = lines.get(i + 0).getTokens().get(j + 1).toString();
            
            for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() > k + 1 && lines.get(i + 0).getTokens().get(j + 0).toString() == reals.get(k).name) {
                        a = reals.get(k);
                       break; 
                    }
            }//FOR
            
            for(int h = 0; h < reals.size() ;h++){
                    if(reals.size() > h + 1 && lines.get(i + 0).getTokens().get(j + 2).toString() == reals.get(h).name) {
                        b = reals.get(h);
                       break; 
                    } 
            }//FOR
                    
            if(a.name == "" || b.name == ""){
             System.out.println("ERROR: Undiclared variable detected");
             endProgramError();
            }
                    
            //Check Comparisons
            if(middle == ".eq.") {
                x = (a.value == b.value);
            }
            else if(middle == ".ne.") {
                x = (a.value != b.value);
            }
            else if(middle == ".lt.") {
                x = (a.value < b.value);
            }
            else if(middle == ".le.") {
                x = (a.value <= b.value);
            }
            else if(middle == ".gt.") {
                x = (a.value > b.value);
            }
            else if(middle == ".ge.") {
                x = (a.value >= b.value);
            }
            
        }
        
        else if(lines.get(i + 0).getTokens().get(j + 0).isStringVariable() && lines.get(i + 0).getTokens().get(j + 0).isStringVariable()) {
            
            StringVariable a = new StringVariable();
            StringVariable b = new StringVariable();
            String middle = lines.get(i + 0).getTokens().get(j + 1).toString();
            
            for(int k = 0; k < strings.size() ;k++){
                    if(reals.size() > k + 1 && lines.get(i + 0).getTokens().get(j + 0).toString() == strings.get(k).name) {
                        a = strings.get(k);
                       break; 
                    }
            }//FOR
            
            for(int h = 0; h < strings.size() ;h++){
                    if(reals.size() > h + 1 && lines.get(i + 0).getTokens().get(j + 2).toString() == strings.get(h).name) {
                        b = strings.get(h);
                       break; 
                    } 
            }//FOR
                    
            if(a.name == "" || b.name == ""){
             System.out.println("ERROR: Undiclared variable detected");
            }
                    
            //Check Comparisons
            if(middle == ".eq.") {
                x = (a.value == b.value);
            }
            else if(middle == ".ne.") {
                x = (a.value != b.value);
            }
            else {
                System.out.println("ERROR: Wrong form of String comparison");
            }
            
        }
        
        
        return x;
    }
    
    boolean conditionalStatement(int i, int j){
        boolean x = false;
        
        if(lines.get(i + 0).getTokens().size() > 3) {
            
            
            //Case Method
            if(conditionalPart(i,j) && lines.get(i + 0).getTokens().get(5 + j).toString() == ".and.") {
                x = (conditionalPart(i,j) && conditionalStatement(i,j + 2));
            }
            if(conditionalPart(i,j) && lines.get(i + 0).getTokens().get(5 + j).toString() == ".or.") {
                x = (conditionalPart(i,j) || conditionalStatement(i,j + 2));
            }
            if(conditionalPart(i,j) && lines.get(i + 0).getTokens().get(5 + j).toString() == ".nor.") {
                x = !(conditionalPart(i,j) || conditionalStatement(i,j + 2));
            }
            
            
        }
        
        return x;
    }
    
    public boolean doIfThen(int i) throws ScriptException{
        boolean x = false;
        
        if(lines.get(i + 0).isIfStatement(0)){
            
          if(conditionalStatement(i + 0, 1)){
            for(int k = 0; k < lines.get(i + 0).getTokens().size();k++){
              if(doAssignment(k)) {
               x = true;
                  break;
              }
              else if(doPrint(k)) {
               x = true;
                  break;
              }
              else {
                  System.out.println("ERROR : Invalid Then Condition");
                  endProgramError();
                  break;
              }
            }//FOR
          }//IF
            
        }//Main IF
        
        return x;
    }
    
    public boolean doEnd(int i){
        boolean x = false;
        
        if(lines.get(i + 0).getTokens().get(0).isEND())
            endProgram();
        
        return x;
    }
    
    public void endProgram(){
        System.out.print("Program End : Build Successful");
        System.exit(0);
    }
    
    public void endProgramError(){
        System.out.print("Build Failed : There are compilation errors");
        System.exit(0);
    }

}
