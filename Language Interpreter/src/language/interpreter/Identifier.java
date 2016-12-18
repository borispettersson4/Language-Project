/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package language.interpreter;

/**
 *___________________________________________________________________________________
 *                                                                                                                                               
 *             Boris M. Ruiz Pettersson    |      Jorge Maldonado      
 * 
 *                                  CECS 4200 - 08                                                                                     
 *                                                                                                                  
 *                     Universidad Politecnica de Puerto Rico                                                                                                  
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
           if((body.charAt(0) >= 'g' && body.charAt(0) <= 'n') || (body.charAt(0) >= 'G' && body.charAt(0) <= 'N'))
               i = 2;
           if((body.charAt(0) >= 'o' && body.charAt(0) <= 'z') || (body.charAt(0) >= 'O' && body.charAt(0) <= 'Z'))
               i = 3;
       }
       return i;
   }
}
