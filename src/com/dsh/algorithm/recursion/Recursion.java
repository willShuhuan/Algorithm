package com.dsh.algorithm.recursion;

/**
 * @author DSH
 * @date 2020/4/18
 * @description 递归
 */
public class Recursion {
    public static void main(String[] args) {
        recursion(4);
        System.out.println("factorial result =="+factorial(4));
    }

    //打印问题
    public static void recursion(int n){
        if (n>2){
            recursion(n-1);
        }
        System.out.println("n="+n);
    }

    //阶乘问题
    public static int factorial(int n){
        if (n==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }


}
