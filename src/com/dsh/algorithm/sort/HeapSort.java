package com.dsh.algorithm.sort;

import com.dsh.datastructure.tree.ArrayBinaryTree;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/13
 * @description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
    }

    //堆排序方法
    public static void heapSort(int arr[]){
//        System.out.println("堆排序");
        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));//4 9 8 5 6
//        adjustHeap(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));//9 6 8 5 4

        //完成最终的代码
        //1. 将待排序序列构造成一个大顶堆
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
//        System.out.println("数组为==" + Arrays.toString(arr));//9 6 8 5 4
        //2. 将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        //3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末位元素，反复执行调整+交换步骤，知道整个序列有序
        int temp = 0;
        for (int j = arr.length-1; j >0 ; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
//        System.out.println("排序后数组为==" + Arrays.toString(arr));//4 5 6 8 9
    }

    //将一个数组（二叉树）调整成一个大顶堆
    /**
     * 功能：完成 将以i对应的非叶子节点的树调整成大顶堆
     * 如：{4,6,8,5,9} => i=1 => adjustHeap => {4,9,8,5,6}
     * 再次调用 i=0 => adjustHeap => {9,6,8,5,4}
     * @param arr 待调整的数组
     * @param index 表示非叶子节点 在数组中的索引
     * @param length 对多少个元素进行调整，length在逐渐减少
     */
    public static void adjustHeap(int arr[],int index,int length){
        int temp = arr[index];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1 k = index*2 +1,k是index节点的左子节点
        for (int k = index*2+1; k < length; k=k*2+1) {
            if (k+1<length&&arr[k]<arr[k+1]){//说明左子节点的值小于右子节点的值
                k++;//k 指向右子节点
            }
            if (arr[k]>temp){//如果子节点大于父节点
                arr[index] = arr[k]; //把较大的值赋给当前节点
                index=k;//index指向k，继续循环比较
            }else {
                break;
            }
        }
        //for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶上（局部的）
        arr[index] = temp;//将temp值放到调整后的位置
    }

}
