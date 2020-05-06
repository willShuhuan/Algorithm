package com.dsh.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author DSH
 * @date 2020/4/14
 * @description  逆波兰计算器实现
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //（3+4）x5-6 => 3 4 + 5 x 6 -
        // 4x5-8+60+8/2 => 4 5 x 8 - 60 + 8 2 / +
        //为了方便分割字符串，用空格隔开
//        String suffixExpression = "3 4 + 5 x 6 -";
        String suffixExpression = "4 5 x 8 - 60 + 8 2 / +";
        //思路
        //1 先将suffixExpression放到ArrayList中
        //2 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        int res = calculate(rpnList);
        System.out.printf("计算表达式%s的结果是%d",suffixExpression,res);
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到arrayList中
    public static List<String> getListString(String suffixExpression){
        String[] slist =suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : slist){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /**
     * 1 从左至右扫描，将3和4压入堆栈
     * 2 遇到+运算符，因此弹出3和4（4为栈顶元素，3位次顶元素），计算出3+4的值，为7，将7入栈
     * 3 将5入栈
     * 4 接下来是x运算符，因此弹出5和7 计算出7x5 = 35，将35入栈
     * 5 将6入栈
     * 6 遇到-运算符，计算出35-6的值，即为29，由此得出计算结果
     */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String s:ls){
            //使用正则表达式来取数
            if (s.matches("\\d+")){//匹配的是多位数
                stack.push(s);
            }else {//运算符
                //pop出两个数并运算，再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = cal(num1,num2,s);
                stack.push(res+"");
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

    public static int cal(int num1,int num2,String oper){
        int res = 0;//用于存放计算结果
        switch (oper){
            case "+":
                res = num1+num2;
                break;
            case "-":
                res = num2-num1;
                break;
            case "x":
                res = num1*num2;
                break;
            case "/":
                res = num2/num1;
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return res;
    }

}
