package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/3
 * @description 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8,4,5,7,1,2,3,6};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("排序后数组为=="+ Arrays.toString(arr));

    }

    static int count = 0;
    //分+合的方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        System.out.println(++count);
        if (left<right){
            int mid = (left+right)/2;
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并时 每分解一次就合并一次
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并方法
     * @param arr 排序的数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        System.out.println("递归left=="+left+"  right=="+right+"  mid=="+mid);
        int i = left;//初始化i，左边有序序列的索引
        int j = mid+1; //初始化j，右边有序序列的初始索引
        int t = 0; //temp数组的当前索引
        //1
        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列有一边处理完毕为止
        while(i<=mid&&j<=right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，拷贝到temp数组,t,i后移
            if (arr[i]<=arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {//反之，将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //2
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i<=mid){//左边的有序序列还有剩余元素，全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j<=right){//左边的有序序列还有剩余元素，全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //3
        //将temp数组的元素拷贝到arr,并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并时 tempLeft = 0；right = 1； //二 tempLeft = 2；right = 3；//三 tempLeft = 0；right = 3；
        //最后一次合并tempLeft = 0；right = 7；
        System.out.println("tempLeft=="+tempLeft+"  right=="+right);
        while (tempLeft<=right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
