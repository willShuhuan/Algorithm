package com.dsh.top_ten_algorithm.divideandconquer;

/**
 * @author DSH
 * @date 2020/9/25
 * @description  分治算法 解决汉诺塔问题
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }
    //汉诺塔移动的方法
    //使用分治算法
    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if (num==1){
            System.out.println("第一个盘从"+a+"->"+c);
        }else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            //1. 先把最上面的所有盘A->B, 移动过程会使用到C
            hanoiTower(num-1,a,c,b);
            //2. 吧最下边的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3. 把B塔的所有盘从B->C,移动过程使用到A
            hanoiTower(num-1,b,a,c);
        }
    }

}
