package com.dsh.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author DSH
 * @date 2020/4/29
 * @description 冒泡排序
 */
public class BubleSort {

    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};

        bubbleSort(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr){
        //冒泡排序 时间复杂度O(n²)
        int temp = 0;//临时变量
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                //如果前面的数比后面的数大  则交换
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
//                System.out.println("内层排序后的数组为：j=="+j+"="+Arrays.toString(arr));
            }
//            System.out.println("----------------第"+(i+1)+"次排序后的数组----------------");
//            System.out.println(Arrays.toString(arr));
        }

//        //优化
//        int temp = 0;//临时变量
//        boolean flag = false;//标识变量，表示是否进行过交换
//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = 0; j < arr.length-1-i; j++) {
//                //如果前面的数比后面的数大  则交换
//                if (arr[j]>arr[j+1]){
//                    flag = true;
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
////            System.out.println("第"+(i+1)+"次排序后的数组");
////            System.out.println(Arrays.toString(arr));
//            if (!flag){//没有发生过交换，表示数组已经有序排列
//                break;
//            }else {
//                flag = false;//进行下次判断
//            }
//
//        }
    }

}
