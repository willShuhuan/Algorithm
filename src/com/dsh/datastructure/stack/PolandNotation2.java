package com.dsh.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author DSH
 * @date 2020/4/14
 * @description 中缀表达式转后缀表达式
 */
public class PolandNotation2 {
    public static void main(String[] args) {

        //完成将一个中缀表达式转后缀表达式的功能
        //说明
        //1. 中缀表达式1+((2+3)x4)-5 =>后缀表达式1 2 3 + 4 x + 5 -
        //2. 由于直接对字符串操作不方便，因此先将1+((2+3)x4)-5 转成中缀表达式对应的list
        //即 1+((2+3)x4)-5 =》[1,+,(,(,2,+,3,),x,4,),-,5]
        //3. 将得到的中缀表达式对应的list转换为后缀表达式对应的list
        // 即 [1,+,(,(,2,+,3,),x,4,),-,5] => [1,2,3,+,4,x,+,5,-]
        String expression = "1+((2+3)x4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list="+infixExpressionList);//[1, +, (, (, 2, +, 3, ), x, 4, ), -, 5]
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list="+parseSuffixExpressionList);//[1, 2, 3, +, 4, x, +, 5, -]

        int result = calculate(parseSuffixExpressionList);
        System.out.printf("计算表达式%s的结果是%d",expression,result);

    }

    //--------------------完整逆波兰计算器步骤 start--------------------

    //第一步 .将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//做多位数的拼接
        char c ;//每遍历一个字符，就放入到c
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                ls.add(""+c);
                i++;
            }else {//如果是一个数，需要考虑多位数的问题
                str = "";//先将str 赋值为空串   '0'[48]->'9'[57]
                while (i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }

    //第二步 将得到的中缀表达式对应的list转换为后缀表达式对应的list
    // 即 [1,+,(,(,2,+,3,),x,4,),-,5] => [1,2,3,+,4,x,+,5,-]
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //因为s2 在整个转换过程中，没有pop的操作，而且后面还要逆序输出，较为麻烦，所以这里直接使用List<String> s2替代
        List<String> s2 = new ArrayList<>();//存储中间结果的栈，可以用ArrayList
        //遍历ls
        for (String item : ls){
            if (item.matches("\\d+")){
                //数字 加入s2
                s2.add(item);
            }else if (item.endsWith("(")){
                //左括号，压入符号栈
                s1.push(item);
            }else if (item.equals(")")){
               // 如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号")"为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将"（"弹出s1栈，消除小括号
            }else {
                //当item的优先级小于s1栈顶的运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到（4-1）与s1中新的栈顶运算符相比较
                while (s1.size()!=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//返回有序list，顺序遍历输出就是后缀表达式对应的list
    }


    //第三步 完成对逆波兰表达式的计算
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

    //--------------------完整逆波兰计算器步骤 end--------------------
}

//返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL =2;
    private static int DIV = 2;
    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，优先级就越高
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "x":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}