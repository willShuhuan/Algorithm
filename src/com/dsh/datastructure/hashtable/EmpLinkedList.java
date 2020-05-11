package com.dsh.datastructure.hashtable;

/**
 * @author DSH
 * @date 2020/5/9
 * @description 表示链表
 */
public class EmpLinkedList {
    //头指针，指向第一雇员，因此head 是有效的，直接指向第一个雇员
    private Emp head;

    //添加雇员
    //1. 假定，当添加雇员时，id是自增的，即id的分配总是从小到大
    //2. 因此，将该雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果是添加第一个雇员
        if (head==null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，使用辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true){
            if (curEmp.next==null){
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head==null){
            //链表为空
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.println("第"+(no+1)+"条链表的信息为==");
        Emp curEmp = head;
        while (true){
            System.out.printf("Employee: id = %d，name = %s\t\n",curEmp.id,curEmp.name);
            if (curEmp.next==null){
                break;
            }
            curEmp = curEmp.next;
        }

    }

    //根据id查找雇员
    //如果查到，就返回emp，如果没找到，就返回null
    public Emp findEmpById(int id){
        //如果链表为空
        if (head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id==id){//找到了
                break;
            }
            if (curEmp.next==null){//没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id删除雇员
    public boolean deleteEmpById(int id){
        if (head==null){
            System.out.println("链表为空,无法删除");
            return false;
        }
        Emp curEmp = head;
        Emp preEmp = head;//前节点
        boolean flag = false;//标记是否找到
        while (true) {
            if (curEmp.id == id) {//找到了
                flag = true;
                break;
            }
            if (curEmp.next==null){//没有找到该雇员
                flag = false;
                break;
            }
            preEmp = curEmp;
            curEmp = curEmp.next;
        }

        if (flag) {//删除操作
            if (curEmp == head) {
                head = head.next;
            } else {
                preEmp.next = curEmp.next;
            }
        }
        return flag;
    }

}
