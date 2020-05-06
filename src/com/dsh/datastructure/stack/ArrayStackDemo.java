package com.dsh.datastructure.stack;

import java.util.Scanner;

/**
 * @author DSH
 * @date 2020/4/11
 * @description   数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(8);
        String key = "";
        boolean loop = true;//控制是否退出菜单
//        arrayStack.list();
//        arrayStack.pop();
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:表示从栈中取出数据");
            System.out.println("exit:退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}

//定义一个类 表示栈结构
class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据放在该数组
    private int top = -1;//top表示栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value){
        if (isFull()){
            System.out.println("栈空间已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 pop
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空间是空的，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈（遍历时，需要从栈顶开始显示）
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}