package com.dsh.datastructure.tree.threadbinarytree;

/**
 * @author DSH
 * @date 2020/5/13
 * @description 中序线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        THeroNode root = new THeroNode(1, "tom");
        THeroNode node2 = new THeroNode(3, "jack");
        THeroNode node3 = new THeroNode(6, "smith");
        THeroNode node4 = new THeroNode(8, "mary");
        THeroNode node5 = new THeroNode(10, "king");
        THeroNode node6 = new THeroNode(14, "stephen");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        TBinaryTree tBinaryTree = new TBinaryTree();
        tBinaryTree.setRoot(root);

        //线索化二叉树
        tBinaryTree.threadedNodes();
        //以10号节点测试,测试前驱后继节点
        System.out.println(node5.getLeft());//3
        System.out.println(node5.getRight());//1

        //线索化之后  不能使用原来的遍历方式
        System.out.println("使用线索化方式遍历线索化二叉树");
        tBinaryTree.listThreadedBinaryTree();
    }
}

