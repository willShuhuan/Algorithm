package com.dsh.algorithm.recursion;

/**
 * @author DSH
 * @date 2020/4/27
 * @description 8皇后问题
 */
public class Queen8 {

    //定义max 表示多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置结果 如arr[8] = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("共有%d种解法",count);//92
        System.out.printf("判断%d次冲突",judgeCount);//15720
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++) ，因此会有回溯
    private void check(int n){
        if (n == max){//n=8,已经放好所有皇后
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前的皇后n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1个皇后
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i,即将第n个皇后放在在本行的下一列
        }
    }

    //当放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    //n表示第n个皇后
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //array[i]==array[n]表示判断第n个皇后和前面n-1个皇后是否在同一列
            //Math.abs(n-i)==Math.abs(array[n]-array[i])表示
            //例 ： n = 1 放在2列第2列 arr[1] = 1
            //Math.abs(1-0)==Math.abs(array[1]-array[0])  == Math.abs(1-0)==1
            //判断是否在同一行，没有必要，n每次都在递增
            if (array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }


    //将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
