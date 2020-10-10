package com.dsh.top_ten_algorithm.kruskal;

import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/10/9
 * @description 克鲁斯卡尔算法解决公交站问题
 */
public class KruskaCase {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] martix;//邻接矩阵
    //使用INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexts = {'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ { INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };
        //创建KruskaCase对象实例
        KruskaCase kruskaCase = new KruskaCase(vertexts, matrix);
        //输出构建的矩阵
        kruskaCase.print();

        //排序测试代码
//        EData[] edges = kruskaCase.getEdges();
//        System.out.println("排序前"+Arrays.toString(edges));
//        kruskaCase.sortEdges(edges);
//        System.out.println("排序后"+Arrays.toString(edges));

        kruskaCase.kruskal();

    }

    //构造器
    public KruskaCase(char[] vertexs, int[][] martix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        //初始化顶点,复制拷贝的方式, 用for循环是为了不修改传入的vertexs
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.martix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.martix[i][j] = martix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.martix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }



    //对边进行排序,冒泡排序
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if (edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 传入的顶点的值 比如'A', 'B'等
     * @return 返回ch顶点对应的下标, 如果找不到,返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边放到EData[] 数组中, 后面需要遍历该数组
     * 是通过matrix邻接矩阵来获取
     * EData[] 形式 [{'A','B','12'}...]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (martix[i][j]!=INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],martix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点,用于后面判断两个顶点的终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个, ends 数组是在遍历过程中,逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i = ends[i];
        }
        return i;
    }

    //
    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        int ends[] = new int[edgeNum];//用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        //创建结果数组,保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合, 一共有12条边
        EData[] edges = getEdges();

        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);
        //遍历edges数组,将边添加到最小生成树中,判断是准备加入的边是否形成了回路,如果没有,就加入到rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);// E : 4
            //获取到第i条边的第二个顶点(终点)
            int p2 = getPosition(edges[i].end);// F : 5
            //获取p1这个顶点在已有的最小生成树中的终点是哪一个
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有的最小生成树中的终点是哪一个
            int n = getEnd(ends,p2);
            //判断是否构成回路
            if (m != n){//没有构成回路
                ends[m] = n;//设置m在已有最小生成树中的终点 <E,F> [0,0,0,0,0,0,0,0,0,0,0,0] => [0,0,0,0,5,0,0,0,0,0,0,0] (E的终点是F)
                rets[index++] = edges[i];//有一条边加入到rets数组
            }
        }

        //统计并打印"最小生成树",输出rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
//        System.out.println("最小生成树为 = "+Arrays.toString(rets));

    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%15d",martix[i][j]);
            }
            System.out.println();
        }
    }

}

//创建一个类EData, 它的对象实例表示一条边
class EData{
    char start;//边的起点(一个点)
    char end;//边的终点(另外一个点)
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">=" + weight +
                '}';
    }
}