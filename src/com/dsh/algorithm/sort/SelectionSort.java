package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/4/30
 * @description 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};
        selectionSort(arr);
        System.out.println("排序后=="+Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr){
//        for (int i = 0; i < arr.length-1; i++) {
//            int min = arr[i];
//            int minIndex = i;
//            for (int j = i+1; j < arr.length; j++) {
//                if (arr[j]<min){
//                    min = arr[j];
//                    minIndex = j;
//                }
//            }
//            if (minIndex!=i) {
//                arr[minIndex] = arr[i];
//                arr[i] = min;
//            }
//        }

        for (int i = 0; i < arr.length-1; i++) {
//            System.out.println("-----------第"+(i+1)+"次排序------------");
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[minIndex]){
                    minIndex = j;
                }
//                System.out.println("内层循环 , 当前最小值为arr["+minIndex+"]="+arr[minIndex]);
            }
            if (minIndex!=i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
//            System.out.println("第"+(i+1)+"次排序后的数组");
//            System.out.println(Arrays.toString(arr));
        }

    }

}
