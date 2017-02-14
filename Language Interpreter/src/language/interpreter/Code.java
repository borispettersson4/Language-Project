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
    
    private boolean display = true;
    
    public ArrayList<Line> lines = new ArrayList<Line>();
    //Variable DataBase
    public ArrayList<IntegerVariable> ints = new ArrayList<IntegerVariable>();
    public ArrayList<RealVariable> reals = new ArrayList<RealVariable>();
    public ArrayList<StringVariable> strings = new ArrayList<StringVariable>();
    
    private boolean error = false;
    
    public boolean doAssignment(int i, int j) throws ScriptException{
        boolean x = false;
        
        if(lines.get(i + 0).getTokens().size() > j + 1){
            
            if(lines.get(i + 0).isAssignment(j) && lines.get(i + 0).getTokens().get(j).isIntVariable() && lines.get(i + 0).getTokens().get(j + 1).isAssigner()) {

                String expression = "";
                
                //Convert the LAO terms to Java
  
                 
                for(int k = 2; k < lines.get(i + 0).getTokens().size(); k++) {
                    if(lines.get(i + 0).getTokens().size() > k + j) {
                                                
                        if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".add.")) {
                           expression += "+";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".sub.")) {
                           expression += "-";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".div.")) {
                           expression += "/";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".mul.")) {
                           expression += "*";
                           expression += " ";
                        }
                        else {
                            boolean forState = false;
                            for(int p = 0; p < ints.size();p++) {
                                if(lines.get(i + 0).getTokens().get(k + j).getBody().equals(ints.get(p).name)) {
                                  expression += ints.get(p).value;
                                  expression += " ";  
                                  forState = true;
                                  break;
                                }
                            }
                            
                            if(!forState) {
                                expression += lines.get(i + 0).getTokens().get(k + j).getBody();
                                expression += " ";  
                            }
                        
                        }//ELSE
                        
                    }
                }
              //  System.out.println("Expression is : " + expression);
                //Parse to Int
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("js");
                
                int result = (int) engine.eval(expression);      
                
                
                //Save Data
                if(!doesIntExist(i,j)) {
                IntegerVariable a = new IntegerVariable();
                a.value = result;
                a.name = lines.get(i + 0).getTokens().get(j).getBody();
                ints.add(a);
                }
                else {
                    
                      for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(ints.get(k).name)) {
                       ints.get(k).name = lines.get(i + 0).getTokens().get(j).getBody();
                       ints.get(k).value = result;
                       break; 
                    }  
                    
                }
                }
                
                
            }//Check Integer
            
            else if(lines.get(i + 0).isAssignment(j) && lines.get(i + 0).getTokens().get(j).isRealVariable() && lines.get(i + 0).getTokens().get(j + 1).isAssigner()) {
                
                String expression = "";
                
                //Convert the LAO terms to Java
                 for(int k = 2; k < lines.get(i + 0).getTokens().size(); k++) {
             //        System.out.print(expression);
                    if(lines.get(i + 0).getTokens().size() > k + j) {
                                                
                        if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".add.")) {
                           expression += "+";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".sub.")) {
                           expression += "-";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".div.")) {
                           expression += "/";
                           expression += " ";
                        }
                        
                        else if(lines.get(i + 0).getTokens().get(k + j).getBody().contains(".mul.")) {
                           expression += "*";
                           expression += " ";
                        }
                        else {
                         boolean forState = false;
                            for(int p = 0; p < reals.size();p++) {
                                if(reals.size() > p && lines.get(i + 0).getTokens().get(k + j).getBody().equals(reals.get(p).name)) {
                                  expression += reals.get(p).value + "";
                                  expression += " ";  
                                  forState = true;
                                  break;
                                }
                            }
                            
                            for(int p = 0; p < ints.size();p++) {
                                if(ints.size() > p && lines.get(i + 0).getTokens().get(k + j).getBody().equals(ints.get(p).name)) {
                                  expression += ints.get(p).value + "";
                                  expression += " ";  
                                  forState = true;
                                  break;
                                }
                            }
                            
                            if(!forState) {
                                expression += lines.get(i + 0).getTokens().get(k + j).getBody();
                                expression += " ";  
                            }
                        }
                        
                    }
                }
              //  System.out.println("Expression is : " + expression);
                //Parse to Int
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("js");
                double  result = (double) engine.eval(expression);               
                
                
                //Save Data
                if(!doesRealExist(i,j)) {
                RealVariable a = new RealVariable();
                a.value = result;
                a.name = lines.get(i + 0).getTokens().get(j).getBody();
                reals.add(a);
                }
                else {
                    
                      for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(reals.get(k).name)) {
                       reals.get(k).name = lines.get(i + 0).getTokens().get(j).getBody();
                       reals.get(k).value = result;
                       break; 
                    }  
                    
                }
                }
                
            }//Check Real
            
            //Check Strings
             else if(lines.get(i + 0).getTokens().get(j).isStringVariable() && lines.get(i + 0).getTokens().get(j + 1).isAssigner()) {    
               
                if(lines.get(i).getTokens().size() > 3 && lines.get(i).getTokens().get(j + 3).getBody().equals(".add.") && lines.get(i).getTokens().get(j + 4).isString()) {
                    //Save Data
                if(!doesStringExist(i,j)) {
                StringVariable a = new StringVariable();   
                a.value = lines.get(i + 0).getTokens().get(2 + j).getBody() + lines.get(i + 0).getTokens().get(4 + j).getBody();
                a.name = lines.get(i + 0).getTokens().get(j).getBody();
                strings.add(a);
                }
                else {
                    
                      for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(strings.get(k).name)) {
                       strings.get(k).name = lines.get(i + 0).getTokens().get(j).getBody();
                       strings.get(k).value = lines.get(i + 0).getTokens().get(2 + j).getBody() + lines.get(i + 0).getTokens().get(4 + j).getBody();
                       break; 
                    }  
                    
                }
                }
                    
                }//END IF
                
                else if(lines.get(i).getTokens().size() > 3 && lines.get(i).getTokens().get(j + 3).getBody().equals(".add.") && lines.get(i).getTokens().get(j + 4).isStringVariable()) {
                    //Save Data
                if(!doesStringExist(i,j)) {  
                String additionString = "";
                for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j + 2).getBody().equals(strings.get(k).name)) {
                       additionString += strings.get(k).value;
                    }
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j + 4).getBody().equals(strings.get(k).name)) {
                       additionString += strings.get(k).value;
                       break;
                    }
                }
                StringVariable a = new StringVariable(); 
                a.name = lines.get(i + 0).getTokens().get(j).getBody();
                a.value = additionString;
                strings.add(a);
                }
                else {
                      String additionString = "";
                      for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(strings.get(k).name)) {
                       strings.get(k).name = lines.get(i + 0).getTokens().get(j).getBody();
                      
                    for(int a = 0; k < strings.size() ;a++){
                    if(strings.size() >= a && lines.get(i + 0).getTokens().get(j + 2).getBody().equals(strings.get(a).name))
                       additionString += strings.get(a).value;
                    if(strings.size() >= a && lines.get(i + 0).getTokens().get(j + 4).getBody().equals(strings.get(a).name)) {
                       additionString += strings.get(a).value;
                       break;
                        }
                    }
                       
                       strings.get(k).value = additionString;
                       break; 
                    }  
                    
                }
                }
                    
                }//END IF
                
                else {
                    //Save Data
                if(!doesStringExist(i,j)) {
                StringVariable a = new StringVariable();   
                a.value = lines.get(i + 0).getTokens().get(2 + j).getBody();
                a.name = lines.get(i + 0).getTokens().get(j).getBody();
                
                StringBuilder sb = new StringBuilder(a.value);
                for(int k = 0; k < sb.length();k++){
                    if(sb.charAt(k) == '"'){
                    sb.deleteCharAt(k);
                  }
                }
                a.value = sb.toString();
                
                strings.add(a);
                }
                else {
                    
                      for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(strings.get(k).name)) {
                       strings.get(k).name = lines.get(i + 0).getTokens().get(j).getBody();
                       strings.get(k).value = lines.get(i + 0).getTokens().get(2 + j).getBody();
                       break; 
                    }  
                    
                }
                }
                }//END IF
                
            }//Check String
                      
            x = true;
        }
        
        return x;
    }
    
    public boolean doPrint(int i, int j) {
        boolean x = false;
        
        if(lines.get(i + 0).checkPrintStatement(j)) {
            
            if(lines.get(i + 0).getTokens().size() < 1) {
                System.out.println("");
                x = true;
            }
            
            else if(lines.get(i + 0).getTokens().size() > 1 && ints.size() >= 1 && lines.get(i + 0).getTokens().get(1 + j).isString()) {
                System.out.println(lines.get(i + 0).getTokens().get(1 + j).getBody());
                x = true;
            }
            else if(lines.get(i + 0).getTokens().size() > 1 && lines.get(i + 0).getTokens().get(1 + j).isVariable()) {
                boolean isVariablePresent = false;
                for(int k = 0; k <= ints.size();k++){
                    if(ints.size() > k && lines.get(i + 0).getTokens().get(1 + j).getBody().equals(ints.get(k).name)) {
                        System.out.println(ints.get(k).value);
                        x = true;
                        isVariablePresent = true;
                        break; 
                    }
                     }//FOR
                for(int k = 0; k <= reals.size();k++){
                    if(reals.size() > k && lines.get(i + 0).getTokens().get(1 + j).getBody().equals(reals.get(k).name)) {
                        System.out.println(reals.get(k).value);
                        x = true;
                        isVariablePresent = true;
                        break; 
                    }
                     }//FOR
                for(int k = 0; k <= strings.size();k++){
                    if(strings.size() > k && lines.get(i + 0).getTokens().get(1 + j).getBody().equals(strings.get(k).name)) {
                        System.out.println(strings.get(k).value);
                        x = true;
                        isVariablePresent = true;
                        break; 
                    }
                     }//FOR
                    if (!isVariablePresent) {
                        if(display) {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                        }
                         endProgramError();
                    }
                    
               
            }//ELSE IF
            else if(lines.get(i + 0).getTokens().size() > 1 && lines.get(i + 0).getTokens().get(1 + j).isRealVariable()) {
                
                for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(1 + j).getBody().equals(reals.get(k).name)) {
                        System.out.println(reals.get(k).value);
                        x = true;
                       break; 
                    }
                    else {
                        if(display) {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                        }
                         endProgramError();
                    }
                    
                }//FOR
            }//ELSE IF
            else if(lines.get(i + 0).getTokens().size() > 1 && lines.get(i + 0).getTokens().get(1 + j).isStringVariable()) {
                                
                for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(1 + j).getBody().equals(strings.get(k).name)) {
                        System.out.println(strings.get(k).value);
                        x = true;
                       break; 
                    }
                    else {
                        if(display) {
                         System.out.println("ERROR : You cannot print a non-existant variable");
                        }
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
                
                
                boolean forStatus = false;
            for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() >= k && lines.get(i + 0).getTokens().get(1).getBody().equals(ints.get(k).name)) {
                         Scanner input = new Scanner(System.in);    
                         ints.get(k).value = input.nextInt();
                         forStatus = true;
                        x = true;
                       break; 
                    }              
                    
                }//FOR
                                    
               if(!forStatus) {
                         Scanner input = new Scanner(System.in); 
                         IntegerVariable a = new IntegerVariable();
                         a.value = input.nextInt();
                         a.name = lines.get(i + 0).getTokens().get(1).getBody();
                         ints.add(a);
                        x = true;
                    }
            
            } //IF 
            
            //If reading an Reals
            if(lines.get(i + 0).getTokens().get(1).isRealVariable()){
                
                
                boolean forStatus = false;
            for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(1).getBody().equals(reals.get(k).name)) {
                         Scanner input = new Scanner(System.in);    
                         reals.get(k).value = input.nextDouble();
                         forStatus = true;
                        x = true;
                       break; 
                    }              
                    
                }//FOR
                                    
               if(!forStatus) {
                         Scanner input = new Scanner(System.in); 
                         RealVariable a = new RealVariable();
                         a.value = input.nextDouble();
                         a.name = lines.get(i + 0).getTokens().get(1).getBody();
                         reals.add(a);
                        x = true;
                    }
            
            } //IF 
            
            //If reading a String
            if(lines.get(i + 0).getTokens().get(1).isStringVariable()){
                
                boolean forStatus = false;
            for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(1).getBody().equals(strings.get(k).name)) {
                         Scanner input = new Scanner(System.in);    
                         strings.get(k).value = input.nextLine();
                         forStatus = true;
                        x = true;
                       break; 
                    }              
                    
                }//FOR
                                    
               if(!forStatus) {
                         Scanner input = new Scanner(System.in); 
                         StringVariable a = new StringVariable();
                         a.value = input.nextLine();
                         a.name = lines.get(i + 0).getTokens().get(1).getBody();
                         strings.add(a);
                        x = true;
                    }
            
            } //IF 
            
            
        }
        
        return x;
    }
    
    boolean conditionState = true;
      
    boolean conditionalPart(int i, int j){
        boolean x = false;
        
         if((lines.get(i + 0).getTokens().get(j + 0).isSoleNumber() || lines.get(i + 0).getTokens().get(j + 0).isIntVariable() || lines.get(i + 0).getTokens().get(j + 0).isRealVariable()) && (lines.get(i + 0).getTokens().get(j + 2).isSoleNumber() || lines.get(i + 0).getTokens().get(j + 2).isIntVariable() || lines.get(i + 0).getTokens().get(j + 2).isRealVariable())) {
            
            RealVariable a = new RealVariable();
            RealVariable b = new RealVariable();
            String middle = lines.get(i + 0).getTokens().get(j + 1).getBody();
            
            for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(j + 0).getBody().equals(reals.get(k).name)) {
                        a = reals.get(k);
                       break; 
                    }  
            }//FOR
                  if (lines.get(i + 0).getTokens().get(j + 0).isSoleNumber()){
                        a.name = lines.get(i + 0).getTokens().get(j + 0).getBody();
                        a.value = Double.parseDouble(lines.get(i + 0).getTokens().get(j + 0).getBody());
                    }
            
            for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(j + 2).getBody().equals(reals.get(k).name)) {
                        b = reals.get(k);
                       break; 
                    }     
            }//FOR
                  if (lines.get(i + 0).getTokens().get(j + 2).isSoleNumber()){
                        b.name = lines.get(i + 0).getTokens().get(j + 2).getBody();
                        b.value = Double.parseDouble(lines.get(i + 0).getTokens().get(j + 2).getBody());
                    }
            
             for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() >= k && lines.get(i + 0).getTokens().get(j + 0).getBody().equals(ints.get(k).name)) {
                        a.value = 0.0 + ints.get(k).value;
                        a.name = ints.get(k).name;
                       break; 
                    }
            }//FOR
            
            for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() >= k && lines.get(i + 0).getTokens().get(j + 2).getBody().equals(ints.get(k).name)) {
                        b.value = 0.0 + ints.get(k).value;
                        b.name = ints.get(k).name;
                       break; 
                    } 
            }//FOR
                    
            // System.out.println(a.value);
          //   System.out.println(b.value);
            
            if(a.name.equals("") || b.name.equals("")){
                if(display && conditionState) {
             System.out.println("ERROR: Undiclared variable detected");
             conditionState = false;
                }
             endProgramError();
            }
                    
            //Check Comparisons
            if(middle.equals(".eq.")) {
                x = (a.value.equals(b.value));
              //  System.out.println(x);
            }
            else if(middle.equals(".ne.")) {
                x = (a.value.equals(b.value));
             //   System.out.println(a.value != b.value);
            }
            else if(middle.equals(".lt.")) {
                x = (a.value < b.value);
            //    System.out.println(a.value < b.value);
            }
            else if(middle.equals(".le.")) {
                x = (a.value <= b.value);
            //    System.out.println(a.value <= b.value);
            }
            else if(middle.equals(".gt.")) {
                x = (a.value > b.value);
            //    System.out.println(a.value > b.value);
            }
            else if(middle.equals(".ge.")) {
                x = (a.value >= b.value);
           //    System.out.println(a.value >= b.value);
            }
             else {
                if(display) {
                System.out.println("ERROR: Wrong form of comparison");
                }
                endProgramError();
            }
            
            
        }
        
        else if(lines.get(i + 0).getTokens().get(j + 0).isStringVariable() && lines.get(i + 0).getTokens().get(j + 0).isStringVariable()) {
            
            StringVariable a = new StringVariable();
            StringVariable b = new StringVariable();
            String middle = lines.get(i + 0).getTokens().get(j + 1).getBody();
            
            for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j + 0).getBody().equals(strings.get(k).name)) {
                        a = strings.get(k);
                       break; 
                    }
                     else if (strings.size() >= k && lines.get(i + 0).getTokens().get(j + 0).isString()){
                        b.name = lines.get(i + 0).getTokens().get(j + 0).getBody();
                        b.value = lines.get(i + 0).getTokens().get(j + 0).getBody();
                        break;
                    }
            }//FOR
            
            for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j + 2).getBody().equals(strings.get(k).name)) {
                        b = strings.get(k);
                       break; 
                    } 
                       else if (strings.size() >= k && lines.get(i + 0).getTokens().get(j + 2).isString()){
                        b.name = lines.get(i + 0).getTokens().get(j + 2).getBody();
                        b.value = lines.get(i + 0).getTokens().get(j + 2).getBody();
                        break;
                    }
            }//FOR
                    
            if(a.name == "" || b.name == ""){
                if(display && conditionState) {
             System.out.println("ERROR: Undiclared variable detected");
             conditionState = false;
                }
             endProgramError();
            }
                    
            //Check Comparisons
            if(middle.equals(".eq.")) {
                x = (a.value.contains(b.value)) || (b.value.contains(a.value));
            }
            else if(middle.equals(".ne.")) {
                x = (a.value != b.value);
            }
            else {
                if(display) {
                System.out.println("ERROR: Wrong form of comparison");
                }
                endProgramError();
            }
            
        }
        
        
        return x;
    }
    
    int offsetNumber = 0;
    
    boolean conditionalStatement(int i, int j){
        boolean x = false;
        if(lines.get(i + 0).getTokens().size() >= 3) {
       
            //Case Method
            if((conditionalPart(i,j) || !conditionalPart(i,j)) && lines.get(i + 0).getTokens().get(3 + j).getBody().equals(".or.")) {
                offsetNumber = j + 8;
                x = ((!conditionalPart(i,j) || conditionalPart(i,j)) || conditionalStatement(i,j + 8));
            }
            else if(conditionalPart(i,j) && lines.get(i + 0).getTokens().get(3 + j).getBody().equals(".and.")) {
                 offsetNumber = j + 4;
                x = (conditionalPart(i,j) && conditionalStatement(i,j + 4));
        //      System.out.println(offsetNumber);
            }
            else if(conditionalPart(i,j) && lines.get(i + 0).getTokens().get(3 + j).getBody().equals(".not.")) {
                x = (!conditionalPart(i,j) && !conditionalStatement(i,j + 4));
        //      System.out.println(offsetNumber);
            }
            else  {
                offsetNumber = j + 4;
                x = (conditionalPart(i,j));
            //    System.out.println(lines.get(i + 0).getTokens().get(j).getBody());
            //    System.out.println(x);
            }
            
          // offsetNumber = j;
        }
        
        return x;
    }
    
    public boolean doIfThen(int i) throws ScriptException{
        boolean x = false;
        
        if(lines.get(i + 0).isIfStatement(0)){
            boolean forState = false;
          if(conditionalStatement(i + 0, 1)){
            //  System.out.println("TRUE");
              if(doPrint(i,offsetNumber) || doAssignment(i,offsetNumber)){
               x = true;
               forState = true;
              }    
          }//IF
          else {
         //     System.out.println("FALSE");
              forState = true;
          }
          
             if(!forState){
             System.out.println("ERROR : Invalid Then Condition");
             endProgramError();
            }
            
        }//Main IF
        
        return x;
    }
    
    public boolean doEnd(int i){
        boolean x = false;
        
        if(lines.get(i + 0).getTokens().get(0).isEND())
            endProgram();
        
        return x;
    }
    
       public boolean doesIntExist(int i, int j){
         boolean forStatus = false;
            for(int k = 0; k < ints.size() ;k++){
                    if(ints.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(ints.get(k).name)) {
                         forStatus = true;
                       break; 
                    }              
                    
                }//FOR
            return forStatus;
    }
    
       public boolean doesRealExist(int i, int j){
         boolean forStatus = false;
            for(int k = 0; k < reals.size() ;k++){
                    if(reals.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(reals.get(k).name)) {
                         forStatus = true;
                       break; 
                    }              
                    
                }//FOR
            return forStatus;
    }
       
       public boolean doesStringExist(int i, int j){
         boolean forStatus = false;
            for(int k = 0; k < strings.size() ;k++){
                    if(strings.size() >= k && lines.get(i + 0).getTokens().get(j).getBody().equals(strings.get(k).name)) {
                         forStatus = true;
                       break; 
                    }              
                    
                }//FOR
            return forStatus;
    }
            
    public void endProgram(){
        System.out.print("Program End : Build Successful");
        System.exit(0);
    }
    
    public void endProgramError(){
        error = true;
    }
    
    public void execute() throws ScriptException{
        
        boolean syntaxError = false;
        
        //Check Syntax First
        for(int k = 0; k < lines.size();k++){
             lines.get(k).display = false;
            if(!lines.get(k).checkSyntax()) {
                 System.out.println();
                 System.out.println("Error at line " + (k + 1));
                 lines.get(k).display = true;
                 lines.get(k).checkSyntax();                 
                 syntaxError = true;
            }
            }//FOR
            
        //Check if error from Compilation
        for(int i = 0; i < lines.size();i++){
        //    System.out.println(i + 1);
            //Execute Code
        display = false;
            if(!syntaxError && !error) {
                doAssignment(i,0);
                doPrint(i,0);          
                doRead(i);
                doIfThen(i);
              if(error) {
                 
        System.out.println();
        System.out.println("Error at line " + (i + 1));
        display = true;
        doAssignment(i,0);
        doPrint(i,0);
        doRead(i);
        doIfThen(i);
              }
            
            }
              
        }//FOR
        
          //   System.out.println(strings.size());
            // for(int p = 0; p<strings.size();p++)
             //System.out.println(strings.get(p).name);
        
        
        if(syntaxError) {
            System.out.println();
            System.out.println("Execution Failed : There are syntax errors");
        }
        
        if(error) {
            System.out.println();
            System.out.println("Execution Failed : There are logic errors");
        }
        
            
        
    }//Function
    

}
