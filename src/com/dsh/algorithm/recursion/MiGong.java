package com.dsh.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DSH
 * @date 2020/4/27
 * @description 迷宫回溯问题分析
 */
public class MiGong {

    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图 8行7列
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for(int i =0;i < 7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右两边全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);
        //输出新的地图，小球走过，并标识过的递归
        System.out.println("标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }


    }

    //使用递归回溯来给小球找路
    /**
     * 说明：
     * 1 map 表示地图
     * 2 i，j表示从地图哪个位置开始出发（1，1）
     * 3 如果小球能到map[6][5],说明能够找到通路
     * 4 约定：当map[i][j]为0 表示该点没有走过，党委1时，表示墙；如果为2，表示通路，可以走；如果为3，表示该位置已经走过，但是走不通
     * 5 在走迷宫时，要确定一个策略，下->右->上->左，如果该点走不通，再回溯
     *
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){//通路已经找到ok
            return true;
        }else {
            if (map[i][j]==0){//若果当前这个点还没有走过
                //按照策略 下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i+1, j)){//向下走
                    return true;
                }else if (setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }

//                //改成上->右->下->左的策略
//                map[i][j] = 2;//假定该点可以走通
//                if (setWay(map, i-1, j)){//向上走
//                    return true;
//                }else if (setWay(map,i,j+1)){//向右走
//                    return true;
//                }else if (setWay(map,i+1,j)){//向下走
//                    return true;
//                }else if (setWay(map,i,j-1)){//向左走
//                    return true;
//                }else {
//                    //说明该点走不通，是死路
//                    map[i][j] = 3;
//                    return false;
//                }

            }else {
                //如果map[i][j]!=0; 可能是1 墙，2 走过 3走不通
                return false;
            }
        }
    }





}
