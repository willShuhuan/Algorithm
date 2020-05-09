package com.dsh.algorithm.sort;


import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/6
 * @description 自己手写排序测试
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {24,-2,33,9,256,50,23,-7,6,15};
//        int[] arr = {4,6,5,1,7,3};
//        bubleSort(arr);
//        selectionSort(arr);
//        insertSort(arr);
//        quickSort(arr,0,arr.length-1);
        quickSort2(arr,0,arr.length-1);
    }

    //冒泡排序，相邻元素不断交换
    private static void bubleSort(int[] arr) {
        boolean flag = false;//交换了么
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                break;//未发生交换，说明数组有序 ，跳出
            }else {
                flag = false;
            }

        }
        System.out.println("冒泡排序后的数组为=="+ Arrays.toString(arr));
    }

    //选择排序，找到最大或最小值，放在数组头
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            if (minIndex!=i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        System.out.println("选择排序后的数组为=="+ Arrays.toString(arr));
    }

    //插入排序，将元素插入到数组中合适的位置
    private static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i-1;
            while (insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex --;
            }
            if (insertIndex+1!=i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        System.out.println("插入排序后的数组为=="+ Arrays.toString(arr));
    }

    //快速排序,以数组中间的数作为基准数
    private static void quickSort(int[] arr,int left,int ritht) {
        int pivot = arr[(left+ritht)/2];
        int l = left;
        int r = ritht;
        while (l<r){
            while (arr[l]<pivot){
                l++;
            }
            while (arr[r]>pivot){
                r--;
            }
            if (l==r){
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l]==pivot){
                r--;
            }
            if (arr[r]==pivot){
                l++;
            }
        }
        System.out.println("分割后=="+Arrays.toString(arr));
        if (l==r){
            l++;
            r--;
        }
        if (left<r){
            quickSort(arr,left,r);
        }
        if (ritht>l){
            quickSort(arr,l,ritht);
        }
        System.out.println("快速排序后的数组为=="+Arrays.toString(arr));
    }

    //快速排序2 以数组第一个数作为基准数
    private static void quickSort2(int[] arr,int left,int right) {
        if (left>=right){
            return;
        }
        //定义一个基准数
        int pivot ;
        //将数组分割为大于基准数的右部分和小于基准数的左部分
        pivot = partition(arr,left,right);
        quickSort2(arr,left,pivot-1);
        quickSort2(arr,pivot+1,right);

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left<right){
            //从右向左扫描
            while (left<right&&arr[right]>pivot){
                right--;
            }
            arr[left] = arr[right];
            while (left<right&&arr[left]<pivot){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        System.out.println("快速排序后的数组为=="+Arrays.toString(arr));
        return left;
    }

}
