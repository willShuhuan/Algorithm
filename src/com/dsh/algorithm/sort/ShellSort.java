package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/1
 * @description 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8,9,1,7,2,3,5,4,6,0};
//        shellSort(arr);
        shellSort2(arr);
//        derivation(arr);
    }

    //交换式排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap >0 ; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组，每组arr/gap个元素，步长是gap）
                for (int j = i-gap; j >=0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"轮之后的数组为=="+ Arrays.toString(arr));
        }
    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步缩小增量
        int count = 0;
        for (int gap = arr.length/2; gap >0 ; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
//            System.out.println("第"+(++count)+"轮之后的数组为=="+ Arrays.toString(arr));
        }
    }


    public static void derivation(int[] arr){
        //第一轮排序，将十个数分成了5组
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组两个元素，步长是5）
            for (int j = i-5; j >=0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j]>arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮之后的数组为=="+ Arrays.toString(arr));

        //第二轮排序，将10个数分成5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素（共2组，每组5个元素，步长是2）
            for (int j = i-2; j >=0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j]>arr[j+2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮之后的数组为=="+ Arrays.toString(arr));

        //第三轮排序，将10个数分成2/2 一组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共2组，每组5个元素，步长是2）
            for (int j = i-1; j >=0; j --) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第三轮之后的数组为=="+ Arrays.toString(arr));

    }
}
