package com.dsh.top_ten_algorithm.binarysearch;

/**
 * @author DSH
 * @date 2020/9/25
 * @description  二分查找, 非递归
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        //测试
        int[] arr = {1,2,3,8,10,11,67,100};
        int index = binarySearch(arr,3);
        System.out.println("index="+index);
    }

    //二分查找的非递归实现
    /**
     *
     * @param arr 要查找的数组 arr是升序排列
     * @param target 要查找的目标
     * @return 返回对应下标
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (arr[mid]==target){
                return mid;
            }else if (arr[mid]>target){
                right = mid-1;//需要向左边查找
            }else {
                left = mid+1;
            }
        }
        return -1;
    }

}
