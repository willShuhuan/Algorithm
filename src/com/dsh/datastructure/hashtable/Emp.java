package com.dsh.datastructure.hashtable;

/**
 * @author DSH
 * @date 2020/5/9
 * @description 雇员
 */
public class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        this.id = id;
        this.name = name;
    }
}
