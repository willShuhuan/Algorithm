package com.dsh.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author DSH
 * @date 2020/9/24
 * @description 快速入门 图的创建
 */
public class Graph {

    private ArrayList<String> vertextList;//存储顶点的集合
    private int[][] edges; //存储图的对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组boolean[] 记录某个节点是否被访问
    private boolean isVisited[];

    public static void main(String[] args) {
        //测试创建图
        String Vertexts[] = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(Vertexts.length);
        //循环添加顶点
        for (String value:Vertexts) {
            graph.insertVertex(value);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示邻接矩阵
        graph.showGraph();
        //深度遍历
        System.out.println("深度优先");
        graph.dfs();
        System.out.println( );
        //广度遍历
        System.out.println("广度优先");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和顶点集合
        edges = new int[n][n];
        vertextList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标
    /**
     * 如果存在, 就返回对应的下标否则返回-1
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertextList.size(); j++) {
            if (edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2+1; j < vertextList.size(); j++) {
            if (edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited,int index){
        //首先访问该节点 输出
        System.out.print(getValueByIndex(index)+"->");
        //将该节点设为已访问
        isVisited[index] = true;
        //查找节点i的第一个邻接节点
        int w = getFirstNeighbor(index);
        while (w!=-1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(index,w);
        }
    }

    //对dfs进行一个重载,遍历所有的节点 并进行dfs
    public void  dfs(){
        isVisited = new boolean[getNumOfVertex()];
        //遍历所有的节点 进行dsf(回溯)
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行官渡优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u ; //表示队列的头节点对应的下标
        int w ; //表示邻接节点
        //队列, 记录节点访问的顺序
        LinkedList queue  = new LinkedList<>();
        //访问节点,输出节点信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入对列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出对列的头节点下标
            u = (int) queue.removeFirst();
            //得到第一个邻节点的下标
            w = getFirstNeighbor(u);
            while (w!=-1){//找到了
                //是否访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点,找w后面的下一个邻接点
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }

    //遍历所有的节点 , 都进行广度优先搜索
    private void bfs(){
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertextList.size();
    }
    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回节点i(下标)对应的数据 0->A 1->B
    public String getValueByIndex(int i){
        return vertextList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int [] link: edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    
    //插入节点
    public void insertVertex(String vertex){
        vertextList.add(vertex);
    }
    //添加边

    /**
     *
     * @param v1 点的下标即是第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2 , int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges ++;
    }

}
