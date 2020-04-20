package com.sf.jkt.k.algorithm.face.str;


import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MTrie {
    private int SIZE = 62;//大小写和数字
    private TrieNode root;

    class TrieNode {
        private int num;//从根到此结点，单词个数（abcddd)到d为3
        private char val;//具体的char 如 a
        private TrieNode[] son;//子树
        private boolean isEnd;//判断是完全匹配，还是部分匹配

        TrieNode() {
            num = 1;
            /**
             * son 从左往右为
             * [0-9 A-Z a-z]
             * 计算单词位置
             *
             */
            son = new TrieNode[SIZE];
            isEnd = false;
        }
    }

    MTrie() {
        root = new TrieNode();
    }

    public void insert(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = getCharIndex(letters[i]);
            if (node.son[pos] == null) {
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            } else {
                node.son[pos].num++;
            }
            node = node.son[pos];//从下一个子结点开始继续往下面构建
        }
        node.isEnd = true;
    }

    /**
     * [0-9 A-Z a-z]
     * 0 asii 0
     * A asii
     *
     * @param ch
     * @return
     */
    public static int getCharIndex(char ch) {
        int c0 = '0';
        int cA = 'A';
        int ca = 'a';
        int index = 0;
        if (ch >= ca) {
            index = ch - ca + 36;
        } else if (ch >= cA) {
            index = ch - cA + 10;
        } else if (ch >= c0) {
            index = ch - c0;
        } else {
            throw new RuntimeException("not supported value:");
        }
        return index;
    }

    /**
     * 判断字典树中是否包含指定单词
     * I have a big big dream, the dream is bing superman;
     * 其中如果按照单词维度放入字典树
     * insert i
     * insert dream
     * insert dream
     * 其中 dream 的次数就是2
     *
     * @param str
     * @return
     */
    public boolean has(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        //如果找到就继续往下循环，直到找不到匹配单词为止
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = getCharIndex(letters[i]);
            if (node.son[pos] != null) {
                node = node.son[pos];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    /**
     * 前序遍历 根，左右
     *
     * @param node
     */
    public void preTraverse(TrieNode node) {
        if (node != null) {
            System.out.print(node.val + "-");
            if (node.isEnd) {
                System.out.println("");
            }
            for (TrieNode child : node.son) {
                preTraverse(child);
            }
        }
//        System.out.println("");
    }

    /**
     * 计算单词前缀的数量
     *
     * @param prefix
     * @return
     */
    public int countPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = letters.length; i < len; i++) {
            int pos = getCharIndex(letters[i]);
            if (node.son[pos] == null) {
                return 0;
            } else {
                node = node.son[pos];
            }
        }
        return node.num;
    }

    public void deleteWord(String word) {
        if (has(word) == false) {
            return;
        }
        TrieNode node = root;
        char[] letters = word.toCharArray();
        for (int i = 0, len = letters.length; i < len; i++) {
            int pos = getCharIndex(letters[i]);
            TrieNode child = node.son[pos];
            //先使用后减1
            if (child.num-- == 1) {
                node.son[pos] = null;
                return;
            }
            //遍历到下一个
            node = node.son[pos];
        }
    }

    public static void testChar() {
        int o1 = '0';
        int o2 = 'A';
        int o3 = 'a';
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);

        System.out.println(getCharIndex('0'));
        System.out.println(getCharIndex('B'));
        System.out.println(getCharIndex('b'));
    }

    public static void testTrie() throws IOException {
        MTrie tree = new MTrie();
        String[] dictionaryData = {"hello", "student", "computer", "sorry", "acm", "people", "experienced", "who", "reminds", "everyday", "almost"};
        //构建字典
        for (String str : dictionaryData) {
            tree.insert(str);
        }
//        String filePath="C:\\Users\\Administrator\\Desktop\\sourceFile.txt";
        URL url = MTrie.class.getResource("/faces/sourceFile.txt");
        String filePath = url.getFile();
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            Map<String, Integer> countMap = new HashMap<String, Integer>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                if (tree.has(lineTxt)) {
                    if (countMap.containsKey(lineTxt)) {
                        countMap.put(lineTxt, countMap.get(lineTxt) + 1);
                    } else {
                        countMap.put(lineTxt, 1);
                    }
                } else {
                    System.out.println(lineTxt + "不在字典中！");
                }
            }
            for (String s : countMap.keySet()) {
                System.out.println(s + "出现的次数" + countMap.get(s));
            }
            read.close();
        }
    }

    /**
     * 前序遍历，字符串排序
     * @param root
     */
    public void preTraverse1(TrieNode root){
        if(root!=null){
            System.out.print(root.val);
            if(root.isEnd){
                System.out.println();
            }
            for(TrieNode node:root.son){
                preTraverse1(node);
            }
        }
    }

    /***
     * 中序遍历
     * @throws IOException
     */
    public void midTraverse(TrieNode node){
        if(node!=null){
        }
    }

    public static void main(String[] args) throws IOException {
        MTrie mt = new MTrie();
        mt.insert("hello");
        mt.insert("hello");
        mt.insert("hellos");
        mt.insert("a");
        mt.insert("0");
        mt.insert("B");
        mt.insert("b");
        mt.insert("c");
        System.out.println(mt.has("hello"));
        System.out.println(mt.has("hellos"));
        System.out.println(mt.has("hellos1"));
        System.out.println(mt.countPrefix("hello"));
        System.out.println(mt.countPrefix("hellos"));
        mt.deleteWord("hellos");
        System.out.println(mt.countPrefix("hello"));
        System.out.println(mt.countPrefix("hellos"));
        System.out.println("**********************");
        mt.preTraverse1(mt.root);

    }
}
