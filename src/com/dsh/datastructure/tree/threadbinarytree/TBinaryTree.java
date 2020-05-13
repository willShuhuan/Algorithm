package com.dsh.datastructure.tree.threadbinarytree;

import com.dsh.datastructure.tree.HeroNode;

/**
 * @author DSH
 * @date 2020/5/13
 * @description 线索化二叉树
 */
public class TBinaryTree {
    private THeroNode root;
    //为了实现线索化排序，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre 总是保留前一个接地那
    private THeroNode pre = null;

    public void setRoot(THeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 当前需要线索化的node
     */
    public void threadedNodes(THeroNode node){
        if (node==null){//空 不能线索化
            return;
        }
        //中序
        // （1） 先线索化左子树
        threadedNodes(node.getLeft());

        // （2） 再线索化当前节点
        //先处理当前节点的前驱节点
        //以8节点为例  8.left =null 8.leftType = 1
        if (node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        // （3） 最后线索化右子树
        threadedNodes(node.getRight());


    }

    //中序遍历线索化二叉树
    public void listThreadedBinaryTree(){
        //定义一个遍历，存储当前遍历的节点 从root开始
        THeroNode node = root;
        while (node!=null){
            //循环找到leftType == 1的节点 ，第一个找的的就是8节点
            //后面随着遍历而变化，当leftType == 1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType()==0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的节点
            node = node.getRight();
        }
    }



}
