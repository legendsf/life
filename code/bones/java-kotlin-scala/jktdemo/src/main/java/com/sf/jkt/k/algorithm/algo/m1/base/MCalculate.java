package com.sf.jkt.k.algorithm.algo.m1.base;


import java.util.Stack;

public class MCalculate {
    private Stack<Character> priStack=new Stack<>();
    private Stack<Integer>  numStack = new Stack<>();
    public boolean isNum(String temp){
        return temp.matches("[0-9]");
    }
    private boolean compare(char ch){
        if(priStack.isEmpty()){
            return true;
        }
        char last=priStack.lastElement();
        if(last=='('){
            return true;
        }
        switch (ch){
            case '#':
                return false;
            case '(':
                return true;
            case ')':
                return false;
            case '*':{
                if (last=='+'||last=='-'){
                    return true;
                }else {
                    return false;
                }
            }
            case '/':{
                if (last=='+'||last=='-'){
                    return true;
                }else {
                    return false;
                }
            }
            case '+':
                return false;
            case '-':
                return false;
        }
        return true;
    }
    public int caculate(String s){
        String temp;
        StringBuffer tempNum=new StringBuffer();//存放数字字符串，多位数字
        StringBuffer string=new StringBuffer().append(s);
        while (string.length()!=0){
            temp=string.substring(0,1);
            string.delete(0,1);
            if(temp.equals(" ")){
                continue;
            }
            if(isNum(temp)){
                tempNum.append(temp);
            }else {
                if(!"".equals(tempNum.toString())){
                    int num=Integer.parseInt(tempNum.toString());
                    numStack.push(num);
                    tempNum.delete(0,tempNum.length());
                }

                while (!compare(temp.charAt(0))&&(!priStack.isEmpty())){//优先级比原栈中的低，则计算
                   int a=(int) numStack.pop();
                   int b=(int) numStack.pop();
                   char ope=priStack.pop();
                   int result=0;
                   switch (ope){
                       case '+':
                           numStack.push(b+a);
                           break;
                       case '-':
                           numStack.push(b-a);
                           break;
                       case '*':
                           numStack.push(b*a);
                           break;
                       case '/':
                           numStack.push(b/a);
                           break;
                   }
                }

                if(temp.charAt(0)!='#'){
                    priStack.push(temp.charAt(0));
                    if(temp.charAt(0)==')'){
                        priStack.pop();//pop )
                        priStack.pop();//pop (
                    }
                }
            }
        }
        return numStack.pop();
    }

    public static void test2(){
        MCalculate operate = new MCalculate();
//        int t = operate.caculate("3+1*2-1#");
        int t = operate.caculate("(1+2+3)*2-3#");
//        int t = operate.caculate("1 + 2+3#");
        System.out.println(t);
    }

    public static void main(String[] args) {
        test2();
    }

}
