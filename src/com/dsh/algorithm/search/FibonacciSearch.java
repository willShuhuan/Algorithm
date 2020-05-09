package com.dsh.algorithm.search;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/5/8
 * @description 斐波那契（黄金分割查找）
 */
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {
        int [] arr = {1,8,10,89,1000,1234};
        int[] f = fib();
        System.out.println("斐波那契=="+Arrays.toString(f));
        int index=fibSearch(arr,89);
        System.out.println("找到了，下标为=="+index);

//        //递归生成斐波那契
//        int [] arr2 = new int[maxSize];
//        for (int i = 0; i < maxSize; i++) {
//            arr2[i] = fib2(i);
//        }
//        System.out.println("斐波那契=="+Arrays.toString(arr2));
    }

    //用非递归得到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    //非递归方式查找
    public static int fibSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid的值
        int[] f = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //因为f[k]的值可能大于arr的长度，因此需要使用Arrays类，构造一个新的数组，并指向arr
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr,f[k]);
        //实际上需求使用a数组最后的数填充temp
        //temp = {1,8,10,89,1000,1234,0,0} => {1,8,10,89,1000,1234,1234,1234};
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环处理，找到key
        while (low<=high){
            mid = low + f[k-1]-1;
            if (key<temp[mid]){//继续向数组的左边查找
                high = mid - 1;
                //为什么要k--
                //1 全部元素 = 前面的元素+后面的元素
                //2 f[k] = f[k-1]+f[k-2]
                //3 因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2]+f[k-3]
                //4 即在f[k-1]的前面继续查找k--
                //5 即下次循环mid = f[k-1-1]-1;
                k--;
            }else if (key>temp[mid]){//继续向数组的右边查找
                low = mid+1;
                //为什么是k -= 2
                //1 全部元素 = 前面的元素+后面的元素
                //2 f[k] = f[k-1]+f[k-2]
                //3 因为后面有f[k-2] 所以继续拆分  f[k-1] = f[k-3]+f[k-4]
                //4 即在f[k-2]的前面进行查找 k -= 2
                //5 即下次循环mid = f[k-1-2]-1
                k -= 2;
            }else {//找到
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }


    //递归生成斐波那契数列
    public static int fib2(int n){
        if (n < 2) {
            return 1;
        }
        return fib2(n - 1) + fib2(n - 2);
    }

}
