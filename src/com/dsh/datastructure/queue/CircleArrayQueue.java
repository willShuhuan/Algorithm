package com.dsh.datastructure.queue;

import java.util.Scanner;

/**
 * @author DSH
 * @date 2020/4/8
 * @description 环形数组队列
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~");
        CircleArray arrayQueue = new CircleArray(4);//这里设置的4，其队列的有效数据最大数为3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("l(list): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 'l':
                    arrayQueue.listQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
            System.out.println("程序退出");
        }
    }
}
//使用数组模拟队列 - 编写一个arrayQueue类
class CircleArray {
    private int maxSize;//表示数组的最大容量
    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素，
    // front的初始值=0
    private int front;//队列头
    //rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定，
    // rear的初始值=0
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据

    //创建队列的构造器
    public CircleArray(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    private boolean isFull() {
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    private boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1 先把front对应的值保存到一个临时变量
        //2 将front后移,考虑取模
        //3 将临时保存的变量返回
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    //显示队列的所有数据
    public void listQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        //
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear+maxSize-front) % maxSize;
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }

}