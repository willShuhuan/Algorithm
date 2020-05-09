package com.dsh.algorithm.search;

/**
 * @author DSH
 * @date 2020/5/7
 * @description 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = seqSearch(arr,34);
        System.out.println(index==-1?"未找到":"找到了元素下标为=="+index);
    }

    private static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
