package com.dsh.datastructure.tree;

/**
 * @author DSH
 * @date 2020/5/11
 * @description 二叉树节点
 */
//定义heroNode，每个heroNode对象就是一个节点
public class HeroNode{

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int hNo,String hName){
        this.no = hNo;
        this.name = hName;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left!=null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);//先输出父节点
        //递归向右子树中序遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if (this.left!=null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);//先输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //先比较当前节点
        if (this.no==no){
            return this;
        }
        //否则遍历查找左子节点
        HeroNode resultNode = null;
        if (this.left!=null){
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode!=null){//左子树找到了
            return resultNode;
        }
        //否则遍历查找右子节点
        if (this.right!=null){
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resultNode = null;
        //左子节点
        if (this.left!=null){
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode!=null){
            return resultNode;
        }
        System.out.println("进入中序遍历");
        //当前节点
        if (this.no == no){
            return this;
        }
        //右子节点
        if (this.right!=null){
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;

    }

    //后续遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resultNode = null;
        //左子节点
        if (this.left!=null){
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode!=null){//左子节点找到
            return resultNode;
        }
        //否则遍历查找右子节点
        if (this.right!=null){
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode!=null){//右子节点找到
            return resultNode;
        }
        System.out.println("进入后序遍历");
        //都没找到，比较当前节点
        if (this.no == no){
            return this;
        }
        return resultNode;
    }

    //递归删除节点
    //规定
    //- 如果删除的节点是叶子节点，则删除该节点
    //- 如果删除的节点是非叶子节点，则删除该子树
    public void deleteNode(int no){
        /**
         * 1）  我们举例的二叉树是单向的，所以只能判断子节点是否需要删除，而不能判断当前节点是不是需要删除的节点（因为无法获取父节点并且将父节点指向当前节点置为null）
         * 2）  如果当前节点的左子节点不为空，并且左子节点的编号就是需要删除的节点，就将this.left=null；并且返回，结束递归删除
         * 3）  如果当前节点的右子节点不为空，并且右子节点的编号就是需要删除的节点，就将this.right=null；
         * 4）  如果2、3步操作都没有删除节点，那么我们就需要向左/右子树递归删除
         * 5）  如果4步都没有删除节点，那么我们就需要向右/左子树递归删除
         */
        //2
        if (this.left!=null && this.left.no==no){
            this.left = null;
            return;
        }
        //3
        if (this.right!=null && this.right.no==no){
            this.right = null;
            return;
        }
        //4
        if (this.left!=null){
            this.left.deleteNode(no);
        }
        //5
        if (this.right!=null){
            this.right.deleteNode(no);
        }

    }

    //要求
    //如果要删除的节点不是叶子节点（即有子节点），将子节点上移，左子节点优先
    public void deleteNode2(int no){
        //2
        if (this.left!=null && this.left.no==no){
            if (this.left.getLeft()==null&&this.left.getRight()==null){//如果是叶子节点，直接删
                this.left = null;
            }else {
                if (this.left.getLeft()!=null){
                    this.left = this.left.getLeft();
                }else {
                    this.left = this.left.getRight();
                }
            }
            return;
        }
        //3
        if (this.right!=null && this.right.no==no){
            if (this.right.getLeft()==null&&this.right.getRight()==null) {//如果是叶子节点，直接删
                this.right = null;
            }else {
                if (this.right.getLeft()!=null){
                    this.right = this.right.getLeft();
                }else {
                    this.right = this.right.getRight();
                }
            }
            return;
        }
        //4
        if (this.left!=null){
            this.left.deleteNode(no);
        }
        //5
        if (this.right!=null){
            this.right.deleteNode(no);
        }
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}