package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/6
 * @description 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
    }

    //基数排序方法
    public static void radixSort(int arr[]){
       //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max+"").length();
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1; i < maxLength; i++,n *= 10) {
            //针对每个元素的对应位进行排序处理，第一次是个位，然后是十位
            for (int j= 0; j < arr.length; j++) {
                //取出每个元素的对应的位值
                int digitOfElement = arr[j]/n%10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入原数组
                if (bucketElementCounts[k]>0){
                    //循环该桶即第k个桶（即第k个一维数组）
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //第一轮处理后需要将每个bucketElementCounts[k]置为0
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮排序后的数组为=="+ Arrays.toString(arr));
        }

    }

    //代码推导过程
    public static void derivation(int[] arr){
        //第一轮（针对每个元素的个位进行排序处理）
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //二维数组包含10个一维数组，为了防止数据溢出，则每个一维数组的大小为arr.length
        //基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int j= 0; j < arr.length; j++) {
            //取出每个元素的个位值
            int digitOfElement = arr[j]%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        int index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入原数组
            if (bucketElementCounts[k]>0){
                //循环该桶即第k个桶（即第k个一维数组）
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第一轮处理后需要将每个bucketElementCounts[k]置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮排序后的数组为=="+ Arrays.toString(arr));

        //===================================================================
        //第2轮
        for (int j= 0; j < arr.length; j++) {
            //取出每个元素的十位值
            int digitOfElement = arr[j]/10%10;//748/10 = 74 74%10 = 4;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入原数组
            if (bucketElementCounts[k]>0){
                //循环该桶即第k个桶（即第k个一维数组）
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第二轮排序后的数组为=="+ Arrays.toString(arr));

        //===================================================================
        //第3轮
        for (int j= 0; j < arr.length; j++) {
            //取出每个元素的百位值
            int digitOfElement = arr[j]/100%10; //748/100 = 7 7%10 =7;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入原数组
            if (bucketElementCounts[k]>0){
                //循环该桶即第k个桶（即第k个一维数组）
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
        }
        System.out.println("第三轮排序后的数组为=="+ Arrays.toString(arr));

    }

}
