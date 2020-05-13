package com.dsh.datastructure.tree;

/**
 * @author DSH
 * @date 2020/5/11
 * @description 顺序存储二叉树
 */
public class ArrayBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    //完成顺序存储二叉树的前序遍历
    //index表示数组的下标
    public void preOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //输出当前数组元素
        System.out.println(arr[index]);
        //向左递归遍历
        if (index*2+1<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if (index*2+2<arr.length){
            preOrder(2*index+2);
        }
    }


    //中序
    public void infixOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //向左递归遍历
        if (index*2+1<arr.length){
            infixOrder(2*index+1);
        }
        //输出当前数组元素
        System.out.println(arr[index]);
        //向右递归遍历
        if (index*2+2<arr.length){
            infixOrder(2*index+2);
        }
    }

    //后序
    public void postOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //向左递归遍历
        if (index*2+1<arr.length){
            postOrder(2*index+1);
        }
        //向右递归遍历
        if (index*2+2<arr.length){
            postOrder(2*index+2);
        }
        //输出当前数组元素
        System.out.println(arr[index]);
    }


}
