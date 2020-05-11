package com.dsh.datastructure.hashtable;

/**
 * @author DSH
 * @date 2020/5/9
 * @description 哈希表 管理多条链表
 */
public class HashTable {
    private EmpLinkedList[] empLinkedListArray ;
    private int size;//表示链表个数

    public HashTable(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //增  添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应该添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历方法，遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到那条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp!=null){
            System.out.printf("在第%d条链表中找到了该雇员，雇员为%s",(empLinkedListNO+1),"id="+emp.id+" name="+emp.name);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    public void deleteEmpById(int id){
        int empLinkedListNO = hashFun(id);
        boolean success = empLinkedListArray[empLinkedListNO].deleteEmpById(id);
        if (success){
            System.out.printf("在第%d条链表中找到了要删除的雇员，删除成功",(empLinkedListNO+1));
        }else {
            System.out.println("在哈希表中，没有找到要删除的雇员");
        }
    }

    //编写散列函数，使用简单的取模法
    public int hashFun(int id){
        return id%size;
    }



}
