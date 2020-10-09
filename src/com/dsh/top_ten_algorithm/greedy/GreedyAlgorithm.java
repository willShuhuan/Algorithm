package com.dsh.top_ten_algorithm.greedy;

import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author DSH
 * @date 2020/9/30
 * @description 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台集合,放入到map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hs1 = new HashSet<>();
        hs1.add("北京");
        hs1.add("上海");
        hs1.add("天津");
        HashSet<String> hs2 = new HashSet<>();
        hs2.add("广州");
        hs2.add("北京");
        hs2.add("深圳");
        HashSet<String> hs3 = new HashSet<>();
        hs3.add("成都");
        hs3.add("上海");
        hs3.add("杭州");
        HashSet<String> hs4 = new HashSet<>();
        hs4.add("上海");
        hs4.add("天津");
        HashSet<String> hs5 = new HashSet<>();
        hs5.add("杭州");
        hs5.add("大连");
        //加入到map
        broadcasts.put("K1",hs1);
        broadcasts.put("K2",hs2);
        broadcasts.put("K3",hs3);
        broadcasts.put("K4",hs4);
        broadcasts.put("K5",hs5);

        //存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        for (String area: hs1) {
            allAreas.add(area);
        } for (String area: hs2) {
            if (!allAreas.contains(area))
            allAreas.add(area);
        } for (String area: hs3) {
            if (!allAreas.contains(area))
            allAreas.add(area);
        } for (String area: hs4) {
            if (!allAreas.contains(area))
            allAreas.add(area);
        } for (String area: hs5) {
            if (!allAreas.contains(area))
            allAreas.add(area);
        }
        System.out.println(allAreas.toString());

        //创建arraylist 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合,在遍历的过程中,存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> temSet = new HashSet<>();

        //定义一个MaxKey, 保存在一次遍历过程中,能够覆盖最大未覆盖的地区对应的电台的key
        String  maxKey = null;
        //如果maxKey不为空,则会加入到selects
        while (allAreas.size()!=0){//如果allAreas不为0,则表示还没有覆盖到所有的地区
            //每进行一次while,需要将maxKey置空
            maxKey = null;
            //遍历broadcasts, 取出对应key
            for (String key: broadcasts.keySet()) {
                //每进行一次for
                temSet.clear();
                //当前key能够覆盖的区域
                HashSet<String> areas = broadcasts.get(key);
                temSet.addAll(areas);
                //求出两个tempSet和allAreas集合的交集, 交集会赋给tempSet
                temSet.retainAll(allAreas);

                //如果当前这个集合包含的未覆盖的地区的数量,比maxKey指向的集合未覆盖的地区还多
                //就需要重置maxKey
                //temSet.size() > broadcasts.get(maxKey).size()体现出贪心算法的特点,每次都选择最优的
                if (temSet.size()>0&&(maxKey==null||temSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey!=null,就应该将maxKey加入到selects
            if (maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台区域集合从allAreas中清除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果为="+selects.toString());//[K1, K2, K3, K5]

    }

}
