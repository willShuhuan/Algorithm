package com.dsh.datastructure.stack;

import java.util.Scanner;

/**
 * @author DSH
 * @date 2020/4/11
 * @description  单链表实现栈结构
 */
public class SingleLinkedListStackDemo {

    public static void main(String[] args) {
        SingleLinkedListStack arrayStack = new SingleLinkedListStack(5);
        String key = "";
        boolean loop = true;//控制是否退出菜单
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
                    Node node = new Node(value);
                    arrayStack.push(node);
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

class SingleLinkedListStack{
    //栈的最大容量
    private int maxSize ;
    // 先初始化一个头结点，头结点不动
    private Node head = new Node(0);
    //定义一个节点，代表栈顶所指节点
    private Node top = null;

    // 创建一个获取头结点的方法
    public Node getHead() {
        return head;
    }

    public SingleLinkedListStack(int size){
        this.maxSize = size;
    }

    public void push(Node node){
        if (getLength(head)>=maxSize){
            System.out.println("栈空间已满");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助指针temp
        Node temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next==null){//找到了，跳出循环
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = node;
        top = node;
    }

    public int pop(){
        boolean flag = false;//标记是否找到待删除节点
        Node temp = head;
        //找到弹出节点的上一个节点temp
        // 若找到了，原来temp.next = top，令temp.next = null，同时令top = temp
        while (true){
            if (temp.next==null){
                System.out.println("单链表栈为空");
                break;
            }
            //找到要pop节点的上一个节点
            if(temp.next==top){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            int val = temp.next.value;//其实就是top
            temp.next = null;
            top = temp;
            return val;
        }else {
            throw new RuntimeException("弹不出来了");
        }
    }

    //显示链表（遍历）
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        Node temp = head.next;
        while (true){
            //判断是否为空
            if (temp == null){
                break;
            }
            System.out.println(temp.value);
            //将temp后移
            temp = temp.next;
        }
    }

    public static int getLength(Node head){
        if (head.next == null){//空链表
            return 0;
        }
        int length = 0;
        //辅助变量，不统计头节点
        Node cur = head.next;
        while (cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//先创建一个节点类Node
class Node {
    public int value;//存储的数据
    public Node next;//下一个节点

    //构造器
    public Node(int value) {
        this.value = value;
    }
}