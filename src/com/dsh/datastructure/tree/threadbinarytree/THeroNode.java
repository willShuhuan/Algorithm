package com.dsh.datastructure.tree.threadbinarytree;

/**
 * @author DSH
 * @date 2020/5/13
 * @description  线索化二叉树  二叉树节点
 */
public class THeroNode {

    private int no;
    private String name;
    private THeroNode left;
    private THeroNode right;
    //说明
    //1 如果leftType == 0 表示指向的是左子树，如果为1则表示指向前驱节点
    //2 如果rightType == 0 表示指向的是右子树，如果为1表示指向的是后继节点
    private int leftType = 0;
    private int rightType = 0;

    public THeroNode(int hNo, String hName){
        this.no = hNo;
        this.name = hName;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public THeroNode getLeft() {
        return left;
    }

    public void setLeft(THeroNode left) {
        this.left = left;
    }

    public THeroNode getRight() {
        return right;
    }

    public void setRight(THeroNode right) {
        this.right = right;
    }


    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "THeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
}