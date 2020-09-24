package com.dsh.datastructure.binarysorttree;

/**
 * @author DSH
 * @date 2020/9/22
 * @description 二叉排序树BST
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int [] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        // 情况1 删除叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
        System.out.println("删除叶子节点后");

        // 情况2  删除只有一颗子树的节点
//        binarySortTree.delNode(1);
        System.out.println("删除只有一颗子树的节点后");

        // 情况3  删除有两颗子树的节点
        binarySortTree.delNode(3);
        System.out.println("删除有两颗子树的节点后");
        binarySortTree.infixOrder();


    }
}

//创建二叉排序树
class BinarySortTree{
    private Node root;

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找要删除的节点的父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //编写方法
    // 1. 返回的是以node为根节点的二叉排序树的最小节点的值
    // 2. 删除node 为根节点的二叉排序树的最小节点
    /**
     *
     * @param node 传入的节点(当做二叉排序树的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点, 就会找到最小值
        while (target.left!=null){
            target = target.left;
        }
        System.out.println("最小的="+target.value);
        //找到最小值节点 删除
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        }else {
            //1 需求先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            if (targetNode==null){
                return;
            }
            // 如果二叉排序树 只有一个节点
            if (root.left==null&&root.right==null){
                root = null;
                return;
            }
            // 去找到targetNode的父节点
            Node parentNode = searchParent(value);
            //情况1 如果删除的节点是叶子节点
            if (targetNode.left==null&&targetNode.right==null){
                //判断targetNode是父节点的左子节点还是右子节点
                if (parentNode.left!=null&& parentNode.left.value==value){
                    //左子节点
                    parentNode.left = null;
                }
                if (parentNode.right!=null&& parentNode.right.value==value){
                    //右子节点
                    parentNode.right = null;
                }
            }else if (targetNode.left!=null&&targetNode.right!=null){//情况3  删除有两颗子树的节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            }else { //情况2 删除只有一颗子树的节点
                // 如果要删除的节点有左子节点
                if (targetNode.left!=null) {
                    if (parentNode!=null){
                        //如果 targetNode 是 parent 的左子结点
                        if (parentNode.left.value==value){
                            parentNode.left = targetNode.left;
                        }else {//targetNode 是 parent 的右子结点
                            parentNode.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }

                }else {//要删除的节点有右子节点
                    if (parentNode!=null){
                        //如果 targetNode 是 parent 的左子结点
                        if (parentNode.left.value==value){
                            parentNode.left = targetNode.right;
                        }else {//targetNode 是 parent 的右子结点
                            parentNode.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }

        }
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

    //查找要删除的节点的方法
    public Node search(int value){
        if (value == this.value) {
            return this;
        }else if(value<this.value){
            //查找的值小于当前节点  向左子树递归查找
            if (this.left==null){
                //找不到了
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right==null){
                //找不到了
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    //返回当前节点的父节点
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点,就返回
        if ((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)) {
            return this;
        }else {
            //如果查找的值小于当前节点的值 并且当前节点的左子节点不为空
            if (value<this.value&&this.left!=null){
                return this.left.searchParent(value);//左子树递归查找
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);//左子树递归查找
            }else {
                return null;
            }
        }
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
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}