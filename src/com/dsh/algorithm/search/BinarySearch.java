package com.dsh.algorithm.search;


import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.binarySearch;

/**
 * @author DSH
 * @date 2020/5/7
 * @description 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,8,10,89,1000,1234};
//        int index = binarySearch(arr,89,0,arr.length-1);
//        System.out.println("找到了，下标为=="+index);
        int[] arr2 = {1,8,10,89,1000,1000,1000,1234};
        ArrayList<Integer> resList = binarySearch2(arr2, 1000, 0, arr2.length - 1);
        System.out.println("找到了，结果集为=="+resList);
    }

    //二分查找算法
    private static int binarySearch(int[] arr, int findVal, int left, int right) {
        //当left>right时，说明递归整个数组，但是没有找到
        if (left>right){
            return -1;
        }
        int mid = (left + right) / 2;
        if (findVal > arr[mid]) {
            return binarySearch(arr, findVal, mid+1, right);
        }else if (findVal < arr[mid]) {
            return binarySearch(arr, findVal, left, mid-1);
        }else  {
            return mid;
        }
    }

    //返回所有要查找的值
    private static ArrayList<Integer> binarySearch2(int[] arr, int findVal, int left, int right) {
        if (left>right){
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if (findVal > arr[mid]) {
            return binarySearch2(arr, findVal, mid+1, right);
        }else if (findVal < arr[mid]) {
            return binarySearch2(arr, findVal, left, mid-1);
        }else  {
            //思路
            //1 在找到mid值时，不马上返回
            //2 向mid索引值的左边扫描，将所有满足查找值的元素的下标加入到集合中
            //3 向mid索引值的右边扫描，将所有满足查找值的元素的下标加入到集合中
            //4 将集合返回
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向左扫描
            int temp = mid-1;
            while (true){
                if (temp<0||arr[temp]!=findVal){//退出
                    break;
                }
                //否则将temp放入集合中
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            temp = mid+1;
            while (true){
                if (temp>arr.length-1||arr[temp]!=findVal){//扫描到最右边了
                    break;
                }
                //否则将temp放入集合中
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }

}
