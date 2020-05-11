package com.dsh.datastructure.tree;

/**
 * @author DSH
 * @date 2020/5/11
 * @description 二叉树遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"公孙胜");
        HeroNode node5 = new HeroNode(5,"关胜");
        root.setLeft(node2);
        node3.setLeft(node5);
        node3.setRight(node4);
        root.setRight(node3);
        binaryTree.setRoot(root);

        //遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();//1 2 3 5 4
        System.out.println("中序遍历");
        binaryTree.infixOrder();//2 1 5 3 4
        System.out.println("后序遍历");
        binaryTree.postOrder();//2 5 4 3 1
        //查找
        System.out.println("前序查找~~ "+binaryTree.preOrderSearch(5));//遍历4次
        System.out.println("中序查找~~ "+binaryTree.infixOrderSearch(5));//遍历3次
        System.out.println("后序查找~~ "+binaryTree.postOrderSearch(5));//遍历2次
        //删除
        System.out.println("删除前");
        binaryTree.preOrder();
        System.out.println("删除后");
//        binaryTree.deleteNode(5);
        binaryTree.deleteNode2(3);
        binaryTree.preOrder();

    }
}
