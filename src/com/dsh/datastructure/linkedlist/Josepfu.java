package com.dsh.datastructure.linkedlist;

/**
 * @author DSH
 * @date 2020/4/10
 * @description
 * JosePhu（约瑟夫、约瑟夫环）问题：
 * 设编号为1，2，... n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m的那个人出列，
 * 他的下一位又从1开始报数，数到m的打个人又出列，以此类推，直到所有人都出列为止，由此产生一个出队编号的序列
 */
public class Josepfu {

    public static void main(String[] args) {
        //构建和遍历的测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }


}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        // 校验，至少需要两个小孩才能玩
        if (nums<2){
            System.out.println("nums的值不正确 至少为2");
            return;
        }
        //用for循环创建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            //根据编号 创建小孩节点
            Boy boy = new Boy(i);
            if (i==1){
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);//cur指向新的boy
                boy.setNext(first);//新的boy指向first
                curBoy = boy;//curBoy重新赋值

            }
        }
    }

    //遍历当前链表
    public void showBoy(){
        if (first==null){
            System.out.println("链表为空");
            return;
        }
        //由于first不能动，因此仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号为%d\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                //已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户的输入 计算出出圈的顺序
     *
     * @param startNo 从第几个开始数
     * @param countNum 表示数几下
     * @param nums 表示最初共有多少boy在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        if (first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助小孩完成出圈
        Boy helper = first;
        //需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后一个节点
        while (true){
            if (helper.getNext()==first){//helper指向最后的小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当报数时，让first和helper同时移动countNum-1次
        //循环操作，直到圈中只有一个节点
        while (true){
            if (helper==first){//圈中只有一个节点
                break;
            }
            //让first和helper同时移动countNum-1次
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时 first指向的是要出圈的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //然后将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }

}

//创建一个boy 表示一个节点
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }

}