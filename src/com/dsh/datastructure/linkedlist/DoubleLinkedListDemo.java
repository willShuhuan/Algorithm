package com.dsh.datastructure.linkedlist;

/**
 * @author DSH
 * @date 2020/4/10
 * @description 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        //创建英雄
        HeroNode2 heroNode1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 heroNode4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //添加
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        System.out.println("添加链表的输出");
        doubleLinkedList.list();
        //修改
        HeroNode2 heroNode5 = new HeroNode2(4,"林冲副本","豹子头");
        doubleLinkedList.update(heroNode5);
        System.out.println("修改链表的输出");
        doubleLinkedList.list();
        //删除
        System.out.println("删除后链表的输出");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
        //顺序添加
        System.out.println("顺序添加链表后的输出");
        System.out.println("向中间添加");
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.list();
        System.out.println("向后添加");
        HeroNode2 heroNode6 = new HeroNode2(5,"公孙胜","入云龙");
        doubleLinkedList.addByOrder(heroNode6);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助指针temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next==null){//找到了，跳出循环
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    //第二种方式添加英雄时，根据排名将英雄插入指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助指针temp
        //因为单链表 我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
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
            //如果加在了链表最后，不需要浪费一个null空指针
            System.out.println("-------temp.next=="+temp.next);
            if (temp.next!=null) {
                heroNode.next = temp.next;
            }
            heroNode.prev = temp;
            temp.next = heroNode;
            temp.prev = heroNode;
        }
    }

    //修改节点的信息，根据no编号修改，即no编号不能改，与单链表几乎一样
    public void update(HeroNode2 newNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到修改的节点 根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //双向链表删除一个节点
    //1对于双向链表，可以直接找到要删除的节点
    //2找到后，自我删除即可
    public void delete(int no){
        //判断当前链表是否为空
        if (head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }


        HeroNode2 temp = head.next;//辅助指针
        boolean flag = false;//标记是否找到待删除节点
        while (true){
            if (temp==null){//已经到链表的最后节点的next
                break;
            }
            if (temp.no == no){
                //找到的待删除的节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //可以删除
            temp.prev.next = temp.next;
            //不是最后一个节点，才能将删除节点的prev节点指向待删除节点的perv
            if (temp.next!=null) {
                temp.next.prev = temp.prev;
            }
        }else {
            System.out.printf("没有找到要删除的节点%d",no);
        }
    }

    //显示双链表（遍历）
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

//定义双向链表节点heroNode2，每个heroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 prev;//指向上一个节点

    public HeroNode2(int hNo,String hName,String hNickName){
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