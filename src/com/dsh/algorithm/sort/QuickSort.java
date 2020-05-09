package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/3
 * @description 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
//        quickSort(arr,0,arr.length-1);
        quickSort2(arr,0,arr.length-1);
        System.out.println("arr="+Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left+right)/2];
        int temp = 0;//临时变量 作为交换时使用
        //while循环的目的是让比pivot值小的放在左边
        //比pivot值大的放右边
        while (l<r){
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (arr[l]<pivot){
                l += 1;
            }
            //在pivot的右边一直找，找到小于pivot的值，才退出
            while (arr[r]>pivot){
                r -= 1;
            }
            //如果l>=r 说明pivot 的左右两边的值  已经按照左边全部是小于等于pivot的值
            //右边全是大于等于pivot的值排列
            if (l>=r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完以后，发现这个arr[l] == pivot值 相等 r--.前移
            if (arr[l] == pivot){
                r--;
            }
            //如果交换完以后，发现这个arr[r] == pivot值 相等 l++.后移
            if (arr[r] == pivot){
                l++;
            }
        }
        //如果l == r，必须l++，r--，否则出现栈溢出
        if (l==r){
            l++;
            r--;
        }
        //向左递归
        if (left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right>l){
            quickSort(arr,l,right);
        }
    }


    //以数组第一个值为基准
    public static void quickSort2(int[] arr,int left,int right) {
        int povit;
        if (left<right){
            povit = partition(arr,left,right);
            quickSort2(arr,left,povit-1);
            quickSort2(arr,povit+1,right);
        }

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left<right){
            while (left<right&&arr[right]>=pivot){
                right--;
            }
            arr[left] = arr[right];
            while (left<right&&arr[left]<=pivot){
                left++;
            }
            arr[right] = arr[left];
//            System.out.println("排序后=="+Arrays.toString(arr));
        }
        arr[left] = pivot;
//        System.out.println("循环后=="+Arrays.toString(arr));
        return left;
    }

}
