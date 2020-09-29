package com.dsh.top_ten_algorithm.kmp;

/**
 * @author DSH
 * @date 2020/9/29
 * @description 暴力匹配算法实现
 */
public class VolienceMatch {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int index = volienceMatch(str1,str2);
        System.out.println("index="+index);//15
    }

    public static int volienceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2

        while (i < s1Len && j < s2Len){//保证在匹配时不越界
            if (s1[i]==s2[j]){//匹配成功
                i++;
                j++;
            }else {//匹配失败
                //如果失配(即str1[i]!=str2[j])，令i=i-(j-1)，j=0。相当于每次匹配失败时，i回溯，j被置为0。
                i = i - (j-1);
                j = 0;
            }
        }
        System.out.println("i="+i);//22
        System.out.println("j="+j);//7
        //判断是够匹配成功
        if (j == s2Len){
            return i-j;
        }else {
            return -1;
        }

    }
}
