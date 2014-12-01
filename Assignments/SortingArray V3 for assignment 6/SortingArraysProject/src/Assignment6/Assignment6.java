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
import collection.MyJavaLinkedListDouble;
        
public class Assignment6
{
    String infix = "2-3*(4/5+6)*((7-8)/9)";
    char token;
    MyJavaLinkedListChar postfixResult = new MyJavaLinkedListChar();
    MyJavaLinkedListChar stack = new MyJavaLinkedListChar();
    MyJavaLinkedListDouble result = new MyJavaLinkedListDouble();
    double op1 = 0;
    double op2 = 0;
    
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
    
    public double operator(char operator, double operand2, double operand1){
        double tempResult = 0;
        switch(operator)
        {
            case '*' :
                tempResult = operand1 * operand2;
                break;
            case '/' :
                tempResult = operand1 / operand2;
                break;
            case '+' :
                tempResult = operand1 + operand2;
                break;
            case '-' :
                tempResult = operand1 - operand2;
                break;
            
        }
        
        return tempResult;
        
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
        }
        while(stack.size()!=0) {
                postfixResult.addLast(stack.getFirst().getContents());
                stack.removeFirst();
            }
        System.out.println("Postfix Result: ");
        postfixResult.printArrayInLine();
        System.out.println("\n");
        
        
        
        while (postfixResult.size()!=0) {
            
            switch(getType(postfixResult.getFirst().getContents()))
            {
                case "operand" :
                    result.addLast(Character.getNumericValue(postfixResult.getFirst().getContents()));
                    postfixResult.removeFirst();
                    break;
            
                case "operatorPrecedence0" :
                    op1 = result.getLast().getContents();
                    result.removeLast();
                    op2 = result.getLast().getContents();
                    result.removeLast();
                    result.addLast(operator(postfixResult.getFirst().getContents(), op1, op2));
                    postfixResult.removeFirst();
                    break;
                
                case "operatorPrecedence1" :
                    op1 = result.getLast().getContents();
                    result.removeLast();
                    op2 = result.getLast().getContents();
                    result.removeLast();
                    result.addLast(operator(postfixResult.getFirst().getContents(), op1, op2));
                    postfixResult.removeFirst();
                    break;
            }
        }
        System.out.println("Postfix calculated: ");
        result.printArrayInLine();
        System.out.println("\n");
        
    }
    
    
}
