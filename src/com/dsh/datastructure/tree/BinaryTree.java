package com.dsh.datastructure.tree;

/**
 * @author DSH
 * @date 2020/5/11
 * @description 二叉树
 */
public class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root!=null) {
            root.preOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root!=null) {
            root.infixOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root!=null) {
            root.postOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (this.root!=null) {
            return root.preOrderSearch(no);
        }else {
            System.out.println("当前二叉树为空，无法查找");
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (this.root!=null) {
            return root.infixOrderSearch(no);
        }else {
            System.out.println("当前二叉树为空，无法查找");
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if (this.root!=null) {
            return root.postOrderSearch(no);
        }else {
            System.out.println("当前二叉树为空，无法查找");
            return null;
        }
    }

    //删除节点
    //- 如果删除的节点是叶子节点，则删除该节点
    //- 如果删除的节点是非叶子节点，则删除该子树
    public void deleteNode(int no){
        if (root!=null){
            if (root.getNo()==no){
                root = null;
            }else {
                root.deleteNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

    //删除节点2
    //如果要删除的节点不是叶子节点（即有子节点），将子节点上移，左子节点优先
    public void deleteNode2(int no){
        if (root!=null){
            if (root.getNo()==no){
                root = null;
            }else {
                root.deleteNode2(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

}
