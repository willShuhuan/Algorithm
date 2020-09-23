package com.dsh.datastructure.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author DSH
 * @date 2020/9/10
 * @description 赫夫曼编码
 */
public class HUffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);//40

//        //1 拿到nodes
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("contentBytes=="+nodes);
//
//        //2 创建二叉树 赫夫曼树
//        Node root = createHuffmanTree(nodes);
//        System.out.println("赫夫曼树==");
//        root.preOrder();
//
//        //3 测试是否生成了对应的赫夫曼编码
//        getCodes(root);
//        System.out.println("生成的赫夫曼编码表=="+huffmanCodes);
//
//        //4 赫夫曼编码 数据解压
//        byte[] huffmanCodeBytes  = zip(contentBytes,huffmanCodes);
//        System.out.println("huffmanCodeBytes=="+Arrays.toString(huffmanCodeBytes));


// ----------------------------------------------------------------------------------------------------------
        //上述4个步骤可精简为以下代码
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("数据压缩后的结果 => huffmanCodeBytes==" + Arrays.toString(huffmanCodeBytes));
        // 输出 [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        // 长度 17
        // 压缩率 (40-17) 40 = 57.5%

        //解压
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串==" + new String(sourceBytes));//i like like like java do you like a java


// ----------------------------------------------------------------------------------------------------------
        //测试文件压缩
        String srcFile = "/Users/dongshuhuan/Desktop/20200917002027.jpg";
        String dstFile = "/Users/dongshuhuan/Desktop/zip_20200917002027.jpg";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件OK");

        //解压文件
//        String zipFile = "/Users/dongshuhuan/Desktop/zip_20200917002027.jpg";
//        String dstFile2 = "/Users/dongshuhuan/Desktop/unzip_20200917002027.jpg";
//        unzipFile(zipFile, dstFile2);
//        System.out.println("解压成功");

    }


    //使用一个方法,将方法封装起来 便于调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 拿到nodes
        List<Node> nodes = getNodes(bytes);
        // 根据nodes创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        //对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        //根据生成的赫夫曼编码  压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    // 1 接收一个字节数组
    // 返回的就是List [Node[data='97 weight = 5]， Node[date=32，weight=9].....]
    private static List<Node> getNodes(byte[] bytes) {
        // 创建arraylist
        ArrayList<Node> nodes = new ArrayList<>();
        // 存储每一个byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                //map中没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成Node对象,并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 2 通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //取出最后第一个最小的二叉树
            Node leftNode = nodes.get(0);
            //取出最后第二个最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一个新的二叉树,它的根节点没有data 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两颗二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //返回最后的节点 即根节点
        return nodes.get(0);
    }

    // 3 生成赫夫曼树对应的赫夫曼编码
    //思路
    //1. 将赫夫曼编码表存放在Map<Byte,String>形式
    //    32->01 97->100 100->11000等等[形式]
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2. 在生成赫夫曼编码表时,需要去拼接路径,定义一个StringBuilder,存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便,重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能: 将传入的node节点的所有叶子节点的赫夫曼编码得到,并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径: 左子节点是0,右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到StringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//空节点不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //4 数据解压
    // 编写一个方法, 将字符串对应的byte[] 数组,通过生成的赫夫曼编码表, 返回一个赫夫曼编码  压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例 String content = "i like like like java do you like a java"; => byte[] contentBytes = str.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的byte[] huffmanCodeBytes, 即8位对应一个byte, 放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) =>byte [推导 10101000 => 10101000 -1 => 10100111(反码) => 11011000 = -88]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1. 利用huffmanCodes 将bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder1 = new StringBuilder();
        // 遍历byte数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("stringBuilder=="+stringBuilder.toString());//1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        //将1010100010111111... 转成byte[]
        //统计返回byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建一个存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位一个byte  所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte, 放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);//二进制
            index++;
        }

        return huffmanCodeBytes;
    }


    // 5 完成数据的解压
    // 思路
    // 1. 将huffmanCodeBytes :[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    // 重新转成赫夫曼编码对应的二进制字符串  "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
    // 2. 赫夫曼编码对应的二进制的字符串 "101010001011111111001..." => 对照赫夫曼编码 => "i like like like java do you like a java"

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 返回原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1 先得到huffmanBytes 对应的二进制的字符串 形如 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //2 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串="+stringBuilder1);//1010100010111...
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换, 因为要反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map=="+map);//{000=108, 01=32, 100=97, 101=105, 11010=121, 0011=111, 1111=107, 11001=117, 1110=101, 11000=100, 11011=118, 0010=106}
        List<Byte> list = new ArrayList<>();
        // i 可以理解成是索引 , 扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;// 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100010111...
                //取出一个'1'或'0'
                String key = stringBuilder.substring(i, i + count);// i不动 count移动  直到匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//让i直接移动到count
        }
        //当for循环结束后, list中就存放了所有的字符 "i like java..."
        //把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * flag 标志是否需要补高位 如果是true 表示需要补高位  flase表示不补  如果是最后一个字节 无需补高位
     * return 是该b 对应的二进制的字符串, (注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数 还存在补高位的问题
        if (flag) {
            temp |= 256;//按位与 256 1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
//        System.out.println("str=" + str);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    // 7 编写一个方法 将一个文件进行压缩

    /**
     * @param srcFile 传入的文件路径
     * @param dstFile 压缩后的文件目标输出目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用 //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //8 编写一个方法 完成对压缩文件的解压
    /**
     * @param zipFile 压缩文件路径
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unzipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取 byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes); //将 bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

}


//创建node ,带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身 比如a=97 ' ' = 32
    int weight;//权值

    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}