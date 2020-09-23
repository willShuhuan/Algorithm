package com.dsh.datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author DSH
 * @date 2020/9/7
 * @description 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node root = createrHuffmanTree(arr);
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root==null){
            System.out.println("树是空树,不能遍历");
            return;
        }
        root.preOrder();
    }

    //创建赫夫曼树
    public static Node createrHuffmanTree(int[] arr){
        //为了操作方便
        //1 遍历arr数组
        //2 将arr的每一个元素构建成一个node
        //3 将node放入到arrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value: arr){
            nodes.add(new Node(value));
        }

        //处理的过程是一个循环的过程
        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            System.out.println("nodes=="+nodes);

            //取出根节点权值最小的两颗二叉树
            //1 取出权值最小的节点
            Node left = nodes.get(0);
            //2 取出权值第二小的节点
            Node right = nodes.get(1);
            //3 构建一颗新的二叉树
            Node parent = new Node(left.value+right.value);
            parent.left = left;
            parent.right = right;
            //4 从arraylist中删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // 5 将parent加入到node中
            nodes.add(parent);

//            System.out.println("第一次处理后"+nodes);
        }

        //返回赫夫曼树的root节点
        return nodes.get(0);
    }

}

//创建节点类
//为了让node对象持续排序Collections集合排序
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//左子节点
    Node right;//右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value-o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

}