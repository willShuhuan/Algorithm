package com.dsh.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author DSH
 * @date 2020/4/10
 * @description 演示栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        while (stack.size()>0){
            System.out.println(stack.pop());//pop将栈顶数据取出
        }
    }
}
