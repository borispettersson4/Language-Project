/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package language.interpreter;

/**
 *___________________________________________________________________________________
 *                                                                                                                                               
 *             Boris M. Ruiz Pettersson    |      Jorge E. Maldonado Fonrodona     
 * 
 *                                  CECS 4200 - 08                                                                                     
 *                                                                                                                  
 *                     Universidad Politécnica de Puerto Rico                                                                                                  
 *                                                                                                                                                  
 *___________________________________________________________________________________ 
 *
 * @author Boris Ruiz Pettersson
 */
public class Identifier {

    private String body;
    
    public Identifier() {
        this.body = "";
    }

    public Identifier(String body) {
        this.body = body;
    }
          
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    //Variable Checkers
   
    public boolean isVariable(){
      boolean x = false;
      
      if((body.charAt(0) >= 'a' && body.charAt(0) <= 'z') || (body.charAt(0) >= 'A' && body.charAt(0) <= 'Z'))
          x = true;
      
      return x;
    }
   
    public int variableType(){
       int i = 0;
       if(isVariable()){
           if((body.charAt(0) >= 'a' && body.charAt(0) <= 'f') || (body.charAt(0) >= 'A' && body.charAt(0) <= 'F'))
               i = 1;
           else if((body.charAt(0) >= 'g' && body.charAt(0) <= 'n') || (body.charAt(0) >= 'G' && body.charAt(0) <= 'N'))
               i = 2;
           else if((body.charAt(0) >= 'o' && body.charAt(0) <= 'z') || (body.charAt(0) >= 'O' && body.charAt(0) <= 'Z'))
               i = 3;
       }
       return i;
   }
    
    public boolean isIntegerWithExponent(){
        boolean x = false;
        for(int i = 0; i < body.length() && (body.charAt(i) >= '0' && body.charAt(i) <= '9'); i++){
           
            if(body.charAt(i) == '.')
                return false;
                        
            else if(!(body.charAt(i + 1) == 'e' || body.charAt(i + 1) == 'E'))
                return false;
            
            else if((body.charAt(i + 1) == 'e' || body.charAt(i + 1) == 'E') && !(body.endsWith("E") || body.endsWith("e")))
                return true;
            
        } 
   
  // if((body.charAt(0) >= '0' && body.charAt(0) <= '9') && (body.contains("E") || body.contains("e")))
           
        return x;
    }
   
    public boolean isInteger() {
    try { 
        Integer.parseInt(body); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
}
    
    public int convertToInt() {
    return Integer.parseInt(body);
    }
    
    public boolean isDouble() {
    try { 
        Double.parseDouble(body); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
} 
    
    public double convertToDouble() {
    return Double.parseDouble(body);
    }
    
    public boolean hasDecimalPoint(){
        boolean x = false;
        if(body.contains("."))
            x = true;    
        return x;
    }
    
    public boolean hasExponent(){
      boolean x = false;
      if ((body.charAt(0) >= '0' && body.charAt(0) <= '9'))
      if (body.contains("e")||body.contains("E"))
          x = true;
      return x;
    }
    
    public boolean isDigit(){
       boolean x = false;
       if ((body.charAt(0) >= '0' && body.charAt(0) <= '9'))
            x = true;         
       return x;
    }
    
    public boolean isSign(){
        boolean x = false;
        if(body.contains("+")||body.contains("-"))
            x = true;
        return x;
    } 
    
    public int signType(){
        int x = 0;
        if(body.contains("+"))
            x = 1;
        else if(body.contains("-"))
            x = 2;
        return x;
        
    }
       
    public boolean isSignedInteger(){
        return (Integer.parseInt(body) < 0);
    }
  
    public boolean isUnsignedInteger(){
        return(Integer.parseInt(body) >  9);
    }
    
    public boolean isSignedDouble(){
        return (Double.parseDouble(body) < 0);
    }
       
    public boolean isOperator() {
      boolean x = false;
      
      if(body.contains(".or.") || body.contains(".and.") || body.contains(".not.") || body.contains(".eq.") || body.contains(".ne.") || body.contains(".lt.") || body.contains(".le.") || body.contains(".gt.") || body.contains(".ge."))
          x = true;
      
      return x;
   }
   
    public int OperatorType(){
       int i = 0;
       if(isOperator()){
           if(body.contains(".or."))
               i = 1;
           else if(body.contains(".and."))
               i = 2;
           else if(body.contains(".not."))
               i = 3;
           else if(body.contains(".eq."))
               i = 4;
           else if(body.contains(".ne."))
               i = 5;
           else if(body.contains(".lt."))
               i = 6;
           else if(body.contains(".le."))
               i = 7;
           else if(body.contains(".gt."))
               i = 8;
           else if(body.contains(".ge."))
               i = 9;
       }
       return i;
   }
   
    public boolean isMathOperator() {
       boolean x = false;
       if(body.contains(".add.") || body.contains(".sub.") || body.contains(".mul.") || body.contains(".div."))
           x = true;
       return x;
   }
   
    public int MathOperatorType(){
       int i = 0;
       if(isMathOperator()){
           if(body.contains(".add."))
               i = 1;
           else if(body.contains(".sub."))
               i = 2;
           else if(body.contains(".mul."))
               i = 3;
           else if(body.contains(".div."))
               i = 4;
       }
       return i;
   }
   
    public boolean isAssigner() {
       boolean x = false;
       if(body.contains("="))
           x = true;
       return x;
   }
   
    public boolean isPrint (){
       boolean x = false;
       if (body.contains("PRINT")||body.contains("print") || body.contains("Print")|| body.contains("pRINT"))
        x = true;
       return x;
   }

    public boolean isRead(){
   boolean x = false;
   if (body.contains("READ")||body.contains("read")||body.contains("Read")||body.contains("rEAD"))
       x = true;
   return x;
   }
   
    public boolean isIf(){
    boolean x = false;
    if(body.contains("IF")||body.contains("if")||body.contains("If")||body.contains("iF"))
       x= true;
    return x;
   }
   
    public boolean isThen(){
    boolean x= false;
    if (body.contains("THEN")||body.contains("then")||body.contains("Then")||body.contains("tHEN"))
        x = true;
    return x;
   }
    
    public boolean isEND(){
      boolean x = false;
      if (body.contains("END")||body.contains  ("end")||body.contains("End")||body.contains("eND"))
          x = true;
      return x;
  }
  
    public boolean isString(){
    boolean x = false;
    if((body.charAt(0) == '"' && body.charAt(body.length()-1) == '"'))
       x =  true;
    return x;
  }
  
























   
}
