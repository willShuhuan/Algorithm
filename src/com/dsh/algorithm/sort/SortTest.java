package com.dsh.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DSH
 * @date 2020/4/30
 * @description
 */
public class SortTest {
    public static void main(String[] args) {
        //测试80000个数据的排序时间
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random()*800000);//生成80000个0-800000的数
        }
        long before = System.currentTimeMillis();

        //1 冒泡排序
//        BubleSort.bubbleSort(array);//11-12s之间
        //2 选择排序
//        SelectionSort.selectionSort(array);//2.6-2.8s之间
        //3 插入排序
//        InsertionSort.insertionSort(array);//0.7-0.75s之间
//        InsertionSort.myInsertionSort(array);//双层for循环，3.5-3.8s之间
        //4 希尔排序（交换式）
//        ShellSort.shellSort(array);//6-8s，7.7s左右
        //4 希尔排序（移位式）
//        ShellSort.shellSort2(array);//0.02s
        // 5 快速排序
//        QuickSort.quickSort(array,0,array.length-1);//0.02-0.05s之间
        // 6 归并排序
//        MergeSort.mergeSort(array,0,array.length-1,new int[array.length]);//0.03-0.06s之间
        // 7 基数排序
        RadixSort.radixSort(array);//0.012~0.019s 之间

        long after = System.currentTimeMillis();
        Long duration = after-before;
        System.out.println("排序消耗时间为=="+String.format("%.3f",duration.doubleValue()/1000 )+"s");
    }
}
