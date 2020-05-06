package com.dsh.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author DSH
 * @date 2020/4/9
 * @description 单链表按插入节点
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //创建英雄
        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(4,"林聪","豹子头");
        //创建链表
        //加入
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode4);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.list();
        //按照顺序加入
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode heroNode5 = new HeroNode(2,"卢俊义副本","玉麒麟");
        singleLinkedList.update(heroNode5);
        System.out.println("修改后的链表");
        singleLinkedList.list();
          //删除一个节点
//        System.out.println("删除后的链表");
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.list();
//
          //1 测试单链表节点个数
//        System.out.println("单链表节点个数为："+getLength(singleLinkedList.getHead()));
          //2 测试获取倒数第k个元素
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
//        System.out.println(res);
          //3 测试链表反转
        System.out.println("反转后的链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
//        //4 逆序打印链表
//        System.out.println("逆序打印单链表");//不改变链表的原有结构
//        reversePrint(singleLinkedList.getHead());

    }

    //面试题1 获取单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
    public static int getLength(HeroNode head){
        if (head.next == null){//空链表
            return 0;
        }
        int length = 0;
        //辅助变量，不统计头节点
        HeroNode cur = head.next;
        while (cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //面试题2 查找单链表中的倒数第k个节点【新浪面试题】
    //思路
    //1 编写一个方法，接收head节点，同时接收一个index
    //2 index表示是倒数第index个节点
    //3 先把链表从头到尾变量，得到链表的总长度getLength
    //4 得到size后 从链表的第一个开始遍历（size-index）个，就可以得到
    //5 如果找到了 返回节点 否则返回null
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size-index位置，倒数第k个节点
        //先做一个index校验，边界值判断
        if (index<0||index>size){
            return null;
        }
        //定义辅助变量 for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //面试题3 单链表反转【腾讯面试题】
    public static void reverseList(HeroNode head){
        //链表为空或者只有一个元素 无需反转
        if (head.next==null||head.next.next==null){
            return;
        }
        //定义一个辅助的指针，帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点（cur）的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表 每遍历一个节点，就将其取出，并放在心的链表的最前端
        while(cur!=null){
            next = cur.next;//先暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next =cur;
            cur = next;//cur后移，指向下一个节点
        }
        //将head.next指向reverseHead.next 实现反转
        head.next = reverseHead.next;
    }

    //面试题4 逆序打印单链表
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;//空链表 不打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}

//定义一个singlelinkedList 管理英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路 当不考虑编号的顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助指针temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next==null){//找到了，跳出循环
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }

    //第二种方式添加英雄时，根据排名将英雄插入指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助指针temp
        //因为单链表 我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在
        while (true){
            if (temp.next==null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no>heroNode.no){//位置找到，就在temp后面插入
                break;
            }else if (temp.next.no==heroNode.no){//希望添加的heroNode编号已经存在
                flag = true;//编号已经存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号修改，即no编号不能改
    public void update(HeroNode newNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到修改的节点 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//标识是否找到节点
        while (true){
            if (temp == null){
                break;//遍历完成
            }
            if (temp.no == newNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断是否找到要修改的节点
        if (flag){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else {
            System.out.printf("没有找到编号为%d的节点，不能修改\n",newNode.no);
        }

    }

    //删除节点的代码
    //- 1 先找到需要删除的节点的前一个节点temp
    //- 2 在比较时，是temp.next.no和需要删除的节点的no比较
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//标记是否找到待删除节点
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no == no){
                //找到的待删除的节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("没有找到要删除的节点%d",no);
        }
    }

    //显示链表（遍历）
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否为空
            if (temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}

//定义heroNode，每个heroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int hNo,String hName,String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}