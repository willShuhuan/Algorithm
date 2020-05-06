package com.dsh.algorithm.sort;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/4/30
 * @description
 */
public class InsertionSort {
    public static void main(String[] args) {
        int arr[] = {17,3,25,14,20,9};
        insertionSort(arr);
        derivation();
    }

    public static void insertionSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i-1;//即arr[]前面的数的坐标
            //给insertValue找到插入的位置
            //1 insertIndex>=0保证不越界
            //2 insertVal<arr[insertIndex 待插入的数还没有找到插入位置
            //3 需要将arr[insertIndex]后移
            System.out.println("待插入的数据为arr["+i+"]="+insertVal);
            while(insertIndex>=0&&insertVal<arr[insertIndex]){//保证不越界
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
                System.out.println("挪动后的数组为"+Arrays.toString(arr));
            }
            //当退出while循环时，说明插入的位置找到了，insertIndex+1;
            //判断是否需要赋值
            if (insertIndex+1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println("第"+i+"轮插入后，数组为");
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------");
        }

    }

    //代码推导过程
    public static void derivation(){
        int arr[] = {17,3,25,14};
        //第一轮 =>{3,17,25,14}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1-1;//即arr[1]前面的数的坐标
        //给insertValue找到插入的位置
        //1 insertIndex>=0保证不越界
        //2 insertVal<arr[insertIndex 待插入的数还没有找到插入位置
        //3 需要将arr[insertIndex]后移
        while(insertIndex>=0&&insertVal<arr[insertIndex]){//保证不越界
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到了，insertIndex+1;
        arr[insertIndex+1] = insertVal;
        System.out.println("第一轮插入后");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2-1;//即arr[1]前面的数的坐标
        while(insertIndex>=0&&insertVal<arr[insertIndex]){//保证不越界
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到了，insertIndex+1;
        arr[insertIndex+1] = insertVal;
        System.out.println("第二轮插入后");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3-1;//即arr[1]前面的数的坐标
        while(insertIndex>=0&&insertVal<arr[insertIndex]){//保证不越界
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到了，insertIndex+1;
        arr[insertIndex+1] = insertVal;
        System.out.println("第三轮插入后");
        System.out.println(Arrays.toString(arr));

    }

    //自己憋了一个小时写的插入排序，双层for循环，效率并不高
    public static void myInsertionSort(int[] arr) {
        int[] newArr = new int[arr.length];
        //先给第一个元素赋值，然后从第二个开始比较
        newArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            newArr[i] = arr[i];
//            System.out.println("-------第"+i+"次排序");
            for (int j = 0; j < i; j++) {
                if (newArr[j]>newArr[i]){
                    int temp = newArr[i];
                    newArr[i] = newArr[j];
                    newArr[j] = temp;
                }
//                System.out.println(Arrays.toString(newArr));
            }
        }

    }



}
