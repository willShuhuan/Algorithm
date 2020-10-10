package com.dsh.top_ten_algorithm.prim;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/10/9
 * @description 普利姆算法解决修路问题
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000表示两个点不连通
        int [][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        //创建一个MGraph对象
        MGraph mGraph = new MGraph(verxs);
        //创建最小生成树对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        //输出
        minTree.showGraph(mGraph);

        //测试普里姆算法
        minTree.prim(mGraph,0);//<A,G,B,E,F,D,C>
    }
}

//创建最小生成树->村庄的图
class MinTree{
    //创建图的邻接矩阵
    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data  图的各个定点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs, char data[], int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法, 得到最小生成树
    /**
     *
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        //1. 标记节点(顶点)是否被访问
        int[] visited = new int[graph.verxs];
        //visited[] 默认元素的值都是0,表示没有访问过
        //把当前节点标记为已访问
        visited[v] = 1;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始为一个大数,遍历过程中, 会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs个顶点, 普里姆算法结束后,有graph.verxs-1条边

            //这个是确定每一次生成的子图和哪个此次遍历的节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {// j表示还没有访问过的节点
                    if (visited[i]==1 && visited[j]==0 && graph.weight[i][j]<minWeight){
                        //替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //退出此次for循环,找到了一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:"+minWeight);
            //将当前节点标记为已经访问过了
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;
        }

    }

}

//创建邻接矩阵 图
class MGraph{
    int verxs;//表示图的节点个数
    char[] data;//保存节点数据
    int[][] weight;//存放边,就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }



}