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
        this.body = null;
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
    
    public boolean isEmpty(){
        return body == null;
    }
   
    public boolean isVariable(){
      boolean x = false;
      
      if((body.charAt(0) >= 'a' && body.charAt(0) <= 'z') || (body.charAt(0) >= 'A' && body.charAt(0) <= 'Z'))
          //for(int i = 0; i < body.length() ; i++)
        //      if((body.charAt(i) >= '0' && body.charAt(i) <= '9'))
      //    x = false;
    //  else
          x = true;
      
      return x;
    }
    
     public boolean isNumberVariable(){
     return(variableType() == 1 || variableType() == 2);
    }
     
     public boolean isIntVariable(){
     return(variableType() == 1);
    }
     
     public boolean isRealVariable(){
     return(variableType() == 2);
    }
     
     public boolean isStringVariable(){
     return(variableType() == 3);
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
        for(int i = 0; i < body.length() && ((body.charAt(i) >= '0' && body.charAt(i) <= '9') || (body.charAt(0) == '-' || body.charAt(0) == '+')); i++){
           
            if(body.charAt(i) == '.')
                return false;
                        
           else if(!(body.equals("e") || body.equals("E")))
                return false;
            
            else if((body.charAt(i + 1) == 'e' || body.charAt(i + 1) == 'E') && !(body.endsWith("E") || body.endsWith("e")))
                return true;
            
    } 
       
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
        
        boolean x = false;
 
        if(body.contains(".0") || body.contains(".1") || body.contains(".2") || body.contains(".3") || body.contains(".4")
                || body.contains(".5") || body.contains(".6") || body.contains(".7") || body.contains(".8") || body.contains(".9"))
            x = true;
    try { 
        Double.parseDouble(body); 
    } catch(NumberFormatException e) { 
        x =  false; 
    } catch(NullPointerException e) {
        x =  false;
    }
    // only got here if we didn't return false
    return x;
} 
    
    public double convertToDouble() {
    return Double.parseDouble(body);
    }

    
    public boolean isDecimalPoint(){
        boolean x = false;
        if(body.equals("."))
            x = true;
        return x;
    }
    
    public boolean contains(String s){
        return body.contains(s);
    }

    
    public boolean hasExponent(){
      boolean x = false;
      if (body.contains("e")||body.contains("E"))
          x = true;
      return x;
    }
    
    public boolean isDigit(){
       boolean x = false;
       if ((body.equals("0") || body.equals ("1") || body.equals ("2")) || body.equals("3")
               || body.equals("4") || body.equals("5") || body.equals("6") || body.equals("7")
               || body.equals("8") || body.equals("9"))
            x = true;
        return x;
    }
    
    public boolean isSign(){
        boolean x = false;
        if(body.equals("+")||body.equals("-"))
            x = true;
        return x;
    } 
    
    public int signType(){
        int i = 0;
        if(body.equals("+"))
            i = 1;
        else if(body.equals("-"))
            i = 2;
        return i;
        
    }
       
    public boolean isSignedInteger(){
        return ((body.charAt(0) == '-') || (body.charAt(0) == '+'));
    }
      
    public boolean isSignedDouble(){
        return ((body.charAt(0) == '-') || (body.charAt(0) == '+'));
    }
       
    public boolean isOperator() {
      boolean x = false;
      
      if(body.equals(".or.") || body.equals(".and.") || body.equals(".not.") || body.equals(".eq.") || body.equals(".ne.") 
              || body.equals(".lt.") || body.equals(".le.") || body.equals(".gt.") || body.equals(".ge."))
          x = true;
      
      return x;
   }
   
    public int OperatorType(){
       int i = 0;
       if(isOperator()){
           if(body.equals(".or."))
               i = 1;
           else if(body.equals(".and."))
               i = 2;
           else if(body.equals(".not."))
               i = 3;
           else if(body.equals(".eq."))
               i = 4;
           else if(body.equals(".ne."))
               i = 5;
           else if(body.equals(".lt."))
               i = 6;
           else if(body.equals(".le."))
               i = 7;
           else if(body.equals(".gt."))
               i = 8;
           else if(body.equals(".ge."))
               i = 9;
       }
       return i;
   }
   
    public boolean isMathOperator() {
       boolean x = false;
       if(body.equals(".add.") || body.equals(".sub.") || body.equals(".mul.") || body.equals(".div."))
           x = true;
       return x;
   }
   
    public int MathOperatorType(){
       int i = 0;
       if(isMathOperator()){
           if(body.equals(".add."))
               i = 1;
           else if(body.equals(".sub."))
               i = 2;
           else if(body.equals(".mul."))
               i = 3;
           else if(body.equals(".div."))
               i = 4;
       }
       return i;
   }
    

   
    //Assigner
    public boolean isAssigner() {
       boolean x = false;
       if(body.equals("="))
           x = true;
       return x;
   }
   
    //Keywords
    public boolean isPrint (){
       boolean x = false;
       if (body.equals("PRINT")||body.equals("print") || body.equals("Print")|| body.equals("pRINT"))
        x = true;
       return x;
   }

    public boolean isRead(){
   boolean x = false;
   if (body.equals("READ")||body.equals("read")||body.equals("Read")||body.equals("rEAD"))
       x = true;
   return x;
   }
   
    public boolean isIf(){
    boolean x = false;
    if(body.equals("IF")||body.equals("if")||body.equals("If")||body.equals("iF"))
       x= true;
    return x;
   }
   
    public boolean isThen(){
    boolean x= false;
    if (body.equals("THEN")||body.equals("then")||body.equals("Then")||body.equals("tHEN"))
        x = true;
    return x;
   }
    
     public boolean isPossibleEND(){
      boolean x = false;
      if (body.contains("END")||body.contains("end")||body.contains("End")||body.contains("eND"))
          x = true;
      return x;
  }
    
    public boolean isEND(){
      boolean x = false;
      if (body.equals("END.")||body.equals("end.")||body.equals("End.")||body.equals("eND."))
          x = true;
      return x;
  }
  
    public boolean isString(){
    boolean x = false;
    if((body.charAt(0) == '"' && body.charAt(body.length()-1) == '"'))
       x =  true;
    return x;
  }
  
  public boolean isComment(){
  boolean x = false;
  if (body.equals("REM")||body.equals("rem")||body.equals("Rem")||body.equals("rEM") || body.equals("ReM"))
  x = true;
  return x;
  }
  
  public boolean isUnknown(){
      return !(isComment() || isString() || isEND() || isThen() || isIf() || isRead() || isPrint() || isAssigner() || isMathOperator() || isOperator()  || isVariable() || isIntegerWithExponent() || isInteger() || isDouble() || isDecimalPoint() || isDigit() || isSign() || isSignedInteger() || isSignedDouble());
  }
  
  public boolean isKeyWord(){
      return (isComment() || isEND() || isThen() || isIf() || isRead() || isPrint());
  }
  
  public boolean isNumber() {
      return ((isInteger() || isIntegerWithExponent() || isDouble() || (isVariable() && (variableType() == 1 || variableType() == 2))));
  }
  
  public boolean isSoleNumber() {
      return ((isInteger() || isIntegerWithExponent() || isDouble()));
  }
  


 




















   
}
