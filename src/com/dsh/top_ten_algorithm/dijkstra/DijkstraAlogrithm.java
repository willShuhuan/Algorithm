package com.dsh.top_ten_algorithm.dijkstra;


import java.util.Arrays;

/**
 * @author DSH
 * @date 2020/10/10
 * @description 迪杰斯特拉算法 解决最短路径问题
 */
public class DijkstraAlogrithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建graph对象
        Graph graph = new Graph(vertex, matrix);
        //测试邻接矩阵显示
        graph.showGraph();
        graph.dijkstra(6);//出发顶点是G
        graph.showDijistra();
    }


}

//创建邻接矩阵 图
class Graph {
    char[] vertex;//顶点数组
    int[][] matrix;//邻接矩阵
    VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图的邻接矩阵
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法
    /**
     * @param index 表示出发顶点对应的下标
     */
    public void dijkstra(int index){
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int j = 0; j < vertex.length; j++) {
            index = visitedVertex.updateArr();//选择并返回新的访问顶点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵的 matrix[index] 行
        for (int j = 0; j < matrix[index].length; j++) {
            len = visitedVertex.getDis(index)+matrix[index][j];
            //如果j顶点没有被访问过,并且len小于出发顶点到j顶点的距离,就需要更新
            if (!visitedVertex.in(j) && len < visitedVertex.getDis(j)){
                visitedVertex.updatePre(j,index);//更新j顶点的前驱为index的顶点
                visitedVertex.updateDis(j,len);//更新出发顶点到j顶点的距离
            }
        }
    }

    public void showDijistra(){
        visitedVertex.show();
    }
}

//已访问顶点的集合
class VisitedVertex{
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
    public int[] dis;

    /**
     *
     * @param length 表示顶点的个数
     * @param index 出发顶点对应的下标,比如G顶点,下标就是6
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }

    /**
     * 功能: 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过 返回true,否则false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index 哪一个顶点
     * @param len 距离多少
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 功能: 更新pre这个顶点的前驱为index的节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index){
        pre_visited[pre] = index;
    }

    /**
     * 功能: 返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点, 比如这里的G之后, 就是A点作为新的访问顶点(注意不是出发顶点)
     * @return
     */
    public int updateArr(){
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示最后的结果
     * 即将三个数组的情况输出
     */
    public void show(){
        System.out.println("=================");
        //already_arr
        for (int i = 0; i < already_arr.length; i++) {
            System.out.print(already_arr[i]+" ");
        }
        System.out.println();
        //pre_visited
        for (int pre: pre_visited) {
            System.out.print(pre+" ");
        }
        System.out.println();
        //dis
        for (int d: dis) {
            System.out.print(d+" ");
        }
        System.out.println();
        //输出整理
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int d: dis) {
            if (d!=65535){
                System.out.print(vertex[count]+"("+d+") ");
            }else {
                System.out.print("N");
            }
            count++;
        }
        //输出结果 A(2) B(3) C(9) D(10) E(4) F(6) G(0)
        System.out.println();
    }

}