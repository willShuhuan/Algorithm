package com.dsh.datastructure.avltree;

/**
 * @author DSH
 * @date 2020/9/23
 * @description AVL 平衡二叉树
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};//坐旋转测试数组
//        int[] arr = {10,12,8,9,7,6};//右旋转测试数组
        int[] arr = {10,11,7,6,8,9};//双旋转测试数组
        //创建AVLTree
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在平衡处理前");
        System.out.println("树的高度="+avlTree.getRoot().height());
        System.out.println("左子树的高度="+avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度="+avlTree.getRoot().rightHeight());
        System.out.println("当前根节点="+avlTree.getRoot());

    }
    
    
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node){
        if (root == null) {
            //若根节点为空 赋值root
            root = node;
        }else {
            root.add(node);
        }
    }

    //中序遍历
    public void  infixOrder(){
        if (root != null) {
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空,不能遍历");
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left==null){
            return 0;
        }else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }else {
            return right.height();
        }
    }

    //左旋转方法
    private void leftRotate(){
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子树的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }

    //右旋转方法
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //添加节点的方法
    public  void add(Node node){
        if (node == null) {
            return;
        }
        //传入节点的值和当前子树的根节点的关系
        if(node.value<this.value){
            //如果当前节点左子节点为空
            if (this.left==null){
                this.left = node;
            }else {
                //递归向左子树添加
                this.left.add(node);
            }
        }else {
            //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }

        // 当添加完一个节点后,如果:(右子树的高度-左子树的高度)>1, 左旋转
        if (rightHeight()-leftHeight()>1){
            //如果它的右子树的左子树高度大于它的右子树的右子树的高度
            if (right!=null&&right.leftHeight()>right.rightHeight()){
                //先对右子树进行右旋转
                right.rightRotate();
                //然后再对当前节点进行左旋转
                leftRotate();
            }else {
                leftRotate();//左旋转
            }
            return;//必须要!!!
        }
        //当添加完一个节点后 如果(左子树的高度-右子树的高度)>1, 右旋转
        if (leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的左子树高度
            if (left!=null&&left.rightHeight()>left.leftHeight()){
                //先对当前节点的左节点->坐旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();//右旋转
            }else {
                //直接右旋转
                rightRotate();//右旋转
            }

        }

    }

    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}