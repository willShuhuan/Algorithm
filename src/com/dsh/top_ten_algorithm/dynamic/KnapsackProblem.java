package com.dsh.top_ten_algorithm.dynamic;

/**
 * @author DSH
 * @date 2020/9/27
 * @description 动态规划算法解决背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        int[] w = {1,4,3};//物品的重量
        int[] val = {1500,3000,2000};//物品的价值, 这里的val[i] 就是v[i]
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组,表
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况,定义一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }
        
        //根据前面的公式进行冬天规划处理
        for (int  i = 1; i  < v.length; i ++) {
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i-1]>j){//因为程序中的i是从1开始的  ,因此原来公式中的w[i]修改为w[i-1]
                    v[i][j] = v[i-1][j];
                }else {
                    //因为程序中的i是从1开始的  ,因此原来公式中的val[i]修改为val[i-1]
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况,不能简单的使用公式,需要使用if-else来处理
                    if (v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        
        //输出 看下目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        //输出最后放入的是哪些商品
        //遍历path , 这样输出会把所有的放入情况都得到,有数据冗余, 其实我们只需要最后的放入
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j]==1){
//                    System.out.printf("第%d个商品放入了背包\n",i);
//                }
//            }
//        }

        //优化输出
        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0&&j>0){//从path的最后开始找
            if (path[i][j]==1){
                System.out.printf("第%d个商品放入了背包\n",i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
