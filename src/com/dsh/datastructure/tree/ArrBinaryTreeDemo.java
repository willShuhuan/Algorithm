package com.dsh.datastructure.tree;

/**
 * @author DSH
 * @date 2020/5/11
 * @description 以数组方式存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder();// 1 2 4 5 3 6 7
        tree.infixOrder(0);//4 2 5 1 6 3 7
        tree.postOrder(0);//4 5 2 6 7 3 1
    }
}
