/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6;

/**
 *
 * @author Aimo
 */


import collection.MyJavaLinkedListChar;
        
public class Assignment6
{
    //String infix = "2-3*(4/5+6)*((7-8)/9)";
    
    String infix = "5+5+5+5+5+5+5+5+5";
    char token;
    MyJavaLinkedListChar postfixResult = new MyJavaLinkedListChar();
    MyJavaLinkedListChar stack = new MyJavaLinkedListChar();
    
    public String getType(char token){
        switch (token)
        {
            case '0' :
                return "operand";
            case '1' :
                return "operand";
            case '2' :
                return "operand";
            case '3' :
                return "operand";
            case '4' :
                return "operand";
            case '5' :
                return "operand";
            case '6' :
                return "operand";
            case '7' :
                return "operand";
            case '8' :
                return "operand";
            case '9' :
                return "operand";
            
            case '+' :
                return "operatorPrecedence0";
            case '-' :
                return "operatorPrecedence0";
            case '*' :
                return "operatorPrecedence1";
            case '/' :
                return "operatorPrecedence1";
            
            case '(' :
                return "pStart";
            case ')' :
                return "pStop";
            
            default : return "Illegal";
        }
    }
    
    public void Assignment6()
    {
        
        int i = 0;
        while(i < infix.length()){
            token = infix.charAt(i++);
            
            switch (getType(token))
            {
                case "operand" : 
                    
                    postfixResult.addLast(token);
                    
                    break;
                
                case "operatorPrecedence0" : 
                    if (stack.size()==0) {
                        stack.addFirst(token);
                    }
                    else if (getType(stack.getFirst().getContents()).equals("operatorPrecedence0")) 
                    {
                        postfixResult.addLast(stack.getFirst().getContents());
                        stack.removeFirst();
                        stack.addFirst(token);
                    }
                    else{
                        if (stack.getFirst().getContents()== '(') {
                            stack.addFirst(token);
                        }
                        else if (getType(stack.getFirst().getContents()).equals("operatorPrecedence0")) {
                            postfixResult.addLast(stack.getFirst().getContents());
                            stack.removeFirst();
                            stack.addFirst(token);
                        }
                        else{
                        postfixResult.addLast(stack.getFirst().getContents());
                        stack.removeFirst();
                        stack.addFirst(token);
                        }
                    }
                    break;
                    
                case "operatorPrecedence1" : 
                    if (getType(stack.getFirst().getContents()).equals("operatorPrecedence0")) {
                        stack.addFirst(token);
                    }
                    else if (getType(stack.getFirst().getContents()).equals("pStart")||getType(stack.getFirst().getContents()).equals("pStop")) {
                        stack.addFirst(token);
                    }
                    else
                        {
                        postfixResult.addLast(stack.getFirst().getContents());
                        stack.removeFirst();
                        stack.addFirst(token);
                        }
                    break;
                
                case "pStart" :
                    stack.addFirst(token);
                    break;
                
                case "pStop" :
                    while(!getType(stack.getFirst().getContents()).equals("pStart")){
                        postfixResult.addLast(stack.getFirst().getContents());
                        stack.removeFirst();
                    }
                    stack.removeFirst();
                    break;
            }
            
            
            System.out.println("token : "+token);
            System.out.println("Postfix: ");
            postfixResult.printArrayInLine();
            System.out.println("\nStack: ");
            stack.printArrayInLine();
            System.out.println("\n\n");
        }
        while(stack.size()!=0) {
                postfixResult.addLast(stack.getFirst().getContents());
                stack.removeFirst();
            }
        System.out.println("Postfix: ");
        postfixResult.printArrayInLine();
        System.out.println("\n");
    }
    
    
}
