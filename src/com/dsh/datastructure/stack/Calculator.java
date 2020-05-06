package com.dsh.datastructure.stack;

/**
 * @author DSH
 * @date 2020/4/13
 * @description  栈实现综合计算器
 */
public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
//        String expression = "30+2*6-2";
        String expression = "7*2*2-5+1-5+3-4";
        //创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数

        //开始while扫描expression
        while (true){
            //一次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是符号还是数字
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，
                    //- 如果当前操作符的优先级小于或者等于栈中（栈顶）的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈；
                    //- 如果当前的操作符的优先级大于栈中（栈顶）的操作符，就直接入符号栈
                    if (operStack.priority(ch)<=operStack.priority(operStack.peak())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //计算结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入栈
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else {//如果是数，直接入数栈
//                numStack.push(ch-48);//根据ASCII码表 char 和 int的关系
                //1 当处理多位数时，不能发现是一个数就立即数栈，因为其可能是多位数
                //2 在处理数，需要向expression的表达式的index后再看一位，如果是数，就继续扫描，如果是符号，才入栈
                //3.所以需要定义一个字符串变量，用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //向后看
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
            }
            //index +1 ，并判断是否扫描到expression的最后
            index++;
            if (index>=expression.length()){
                break;//扫描结束
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出响应的数和符号，并运行
        //最后在数栈中只有一个数字，就是表达式的结果
        while (true){
            //如果符号栈为空，则计算到最后结果，数栈中只有一个数字（结果）
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d",expression,res2);
    }
}

//定义一个类 表示栈结构
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据放在该数组
    private int top = -1;//top表示栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回当前栈顶元素的值，不是真正的出栈
    public int peak(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈空间已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空间是空的，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈（遍历时，需要从栈顶开始显示）
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，优先级就越高
    public int priority(int oper){
        if (oper =='*'||oper=='/'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有+-*/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val =='+'||val =='-'||val =='*'||val =='/';
    }

    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放计算结果
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }

}