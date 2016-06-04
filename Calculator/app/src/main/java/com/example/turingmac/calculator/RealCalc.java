package com.example.turingmac.calculator;

/**
 * Created by TuringMac on 2016/6/4.
 */
import java.util.ArrayList;
import java.util.Stack;

public class RealCalc
{
    double op1=0, op2=0;
    Stack<Object> calcStack = new Stack<>();
    ArrayList<String> exp = new ArrayList<>();

    RealCalc()
    {
        op1=0;
        op2=0;
        calcStack.clear();
        exp.clear();
    }

    public void tranfser(String expression)
    {
        char[] arr = expression.toCharArray();

        for(int i=0; i < arr.length; i++)
        {
            if(isNum(arr[i]) || (i==0&&arr[i]=='-'))
            {
                int index=0;
                for(index=i+1; index<arr.length; index++)
                {
                    if(isOperator(arr[index]) || arr[index]=='(' || arr[index]==')')
                        break;
                }
                exp.add(String.valueOf(arr, i, index-i));
                i = index-1;
            }
            else if(isOperator(arr[i]))
            {
                if(calcStack.empty())
                    calcStack.push(arr[i]);
                else
                {
                    if(operatorPriority(arr[i]) >= operatorPriority(calcStack.peek()))
                        calcStack.push(arr[i]);
                    else
                    {
                        for(int j=1; j<= calcStack.size(); j++)
                            exp.add(calcStack.pop().toString());
                        calcStack.push(arr[i]);
                    }
                }
            }
            else if(arr[i] == '(')
            {
                calcStack.push(arr[i]);
                if(arr[i+1] == '-')
                {
                    int index=0;
                    for(index=i+2; index<arr.length; index++)
                    {
                        if(isOperator(arr[index]) || arr[index]==')')
                            break;
                    }
                    exp.add( String.valueOf(arr, i+1, index-(i+1)) );
                    i = index-1;
                }
            }
            else if(arr[i] == ')')
            {
                int len = calcStack.size();
                for(int k=1; k<=len ; k++)
                {
                    if(calcStack.peek().toString().equals("("))
                    {
                        calcStack.pop();
                        break;
                    }
                    exp.add(calcStack.pop().toString());
                }
            }
        }
        if(!calcStack.empty())
        {
            int len = calcStack.size();
            for(int i=0; i< len; i++)
                exp.add(calcStack.pop().toString());
        }
    }

    public double compute()
    {
        double temp = 0;

        for(String atom : exp)
        {
            if(isNum(atom))
                calcStack.push(atom);
            else if(isOperator(atom))
            {
                op1 = Double.parseDouble(calcStack.pop().toString());
                op2 = Double.parseDouble(calcStack.pop().toString());
                switch(atom)
                {
                    case "+": temp = op2+op1;break;
                    case "-": temp = op2-op1;break;
                    case "*": temp = op2*op1;break;
                    case "/": temp = op2/op1;break;
                }
                calcStack.push(temp);
            }
        }
        return Double.parseDouble(calcStack.pop().toString());
    }

    public boolean isNum(Object o)
    {
        Character c = (Character)o;
        return (c>='0' && c<='9')?true:false;
    }
    public boolean isNum(String s)
    {
        try{
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public boolean isOperator(Object o)
    {
        Character c = (Character)o;
        String s = "+-*/";
        if(s.indexOf(c) != -1)
            return true;
        return false;
    }
    public boolean isOperator(String s)
    {
        String operator = "+-*/";
        if(operator.indexOf(s)!=-1)
            return true;
        return false;
    }
    public int operatorPriority(Object o)
    {
        Character c = (Character)o;
        switch(c)
        {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            default: return 0;
        }
    }
    public String calc(String expression)
    {
        double result = 0;
        tranfser(expression);

        try{
            result = compute();
        }catch(Exception e){
            return "ERROR!";
        }
        return Double.toString(result);
    }
}
