package com.dsh.algorithm.search;

/**
 * @author DSH
 * @date 2020/5/8
 * @description 插值查找
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
       int index = insertValueSearch(arr,0,arr.length-1,38);
        System.out.println("找到了，下标为=="+index);
    }


    //插值查找要求数组是有序的
    private static int insertValueSearch(int[] arr, int low, int high, int findVal) {
        System.out.println("查找中");
        //findVal<arr[0]和findVal>arr[arr.length-1]必须需要
        //否则得到的mid可能越界
        if (low>high||findVal<arr[0]||findVal>arr[arr.length-1]){
            return -1;
        }
        //求出mid，自适应
        int mid = low + (high-low)*(findVal-arr[low])/(arr[high]-arr[low]);
        int midVal = arr[mid];
        if (midVal<findVal){//向右查找
            return insertValueSearch(arr,mid+1,high,findVal);
        }else if (midVal>findVal){//向左查找
            return insertValueSearch(arr,low,mid-1,findVal);
        }else {//找到了
            return mid;
        }

    }

}
