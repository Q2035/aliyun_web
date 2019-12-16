package com.test.aliyun_web.util;

import java.util.*;

public final class MatchingCharacters {

    private static TrieNode head = new TrieNode('h',false,new HashMap<String, TrieNode>());;

    /**
     * 测试阶段还是先用三个中文试试吧
     * 1.输入姓名
     * 2.取出第一个字，查找head下的HashMap中是否有相同的字，
     *      若有，则获取其对应的HashMap，若无，则创建一个新的结点插入HashMap，对应的HashMap入栈
     * 3.取出第二个字，查找第二步的HashMap中是否存在这个key
     * 4.重复比较
     *
     * @param args
     */
    public static void main(String[] args) {
//        insertIntoTrieTree(head);
//        boolean jdk = searchStringOnTrieTree("jdk", head);
//        System.out.println(jdk);
        insertIntoTrieTree(new String[]{"dk","jre","j2ee"});
        List<String> list = listAllStringsOnTrieTree("j");
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 将字插入字典树
     * @param list 所有的字符串
     */
    public static void insertIntoTrieTree(String[] list) {
        String str;
        int count =list.length;
        int num =0;
        TrieNode trieNode;
        HashMap<String ,TrieNode> tempHashMap;
        while (count-- >0){
            char character;
            String hashMapKey;
            str = list[num ++];
            tempHashMap =head.getChildNodes();
            int length =str.length();
            for (int i=0;i<length;i++){
                character =str.charAt(i);
                hashMapKey =String.valueOf(character);
                if (i ==length -1){
                    trieNode= new TrieNode(character,true,new HashMap<String, TrieNode>());
                }else {
                    trieNode= new TrieNode(character,false,new HashMap<String, TrieNode>());
                }
                if (tempHashMap.get(hashMapKey) !=null){
                    tempHashMap =tempHashMap.get(hashMapKey).getChildNodes();
                }else {
                    tempHashMap.put(hashMapKey,trieNode);
                    tempHashMap =trieNode.getChildNodes();
                }
            }
        }
    }

    /**
     * 中序遍历所构建的字典树
     */
    public static void depthIterateWithTrieTree(){
        HashMap<String, TrieNode> tempHashMap = head.getChildNodes();
        Queue<TrieNode > queue =new ArrayDeque<>();
        TrieNode trieNode =null;
        Set<String> keySet = tempHashMap.keySet();
        for (String s : keySet) {
            tempHashMap =head.getChildNodes();
            queue.add(tempHashMap.get(s));
            while (queue.size() >0){
                trieNode =queue.poll();
                System.out.print(trieNode.getValue()+" ");
                tempHashMap =trieNode.getChildNodes();
                Set<String> keySet1 = tempHashMap.keySet();
                for (String s1 : keySet1) {
                    queue.add(tempHashMap.get(s1));
                }
            }
        }
    }

    /**
     * 在字典树中查找相应的字符串
     * @param str 目标字符串
     * @return true为存在，false为不存在
     */
    public static boolean searchStringOnTrieTree(String str){
        HashMap<String, TrieNode> tempHashMap = head.getChildNodes();
        String subString =null;
        for (int i=0;i<str.length();i++){
            subString =str.substring(i,i+1);
            if (tempHashMap.get(subString)!=null){
                tempHashMap =tempHashMap.get(subString).getChildNodes();
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过个别字符串列出字典树中所有符合条件的字符串
     * @param str 需要进行查找的字符串的前缀
     * @return 返回一个String的List集合
     */
    public static List<String> listAllStringsOnTrieTree(String str){
        List<String> result = new ArrayList<>();
        HashMap<String ,TrieNode> headHashMap =head.getChildNodes();
        HashMap<String ,TrieNode> tempHashMap =headHashMap;
        HashMap<String ,TrieNode> fromHereToIterate =headHashMap;

        TrieNode tempTrieNode ;
        Queue<TrieNode > queue =new ArrayDeque<>();
        String subString;
        for (int i=0;i<str.length(); i++){
            subString =str.substring(i,i+1);
            if (tempHashMap.get(subString) !=null){
                tempHashMap =tempHashMap.get(subString).getChildNodes();
            }else {
                return Arrays.asList(str);
            }
        }
        if (tempHashMap !=null){
            fromHereToIterate =tempHashMap;
            Set<String> keySet = tempHashMap.keySet();
            for (String key : keySet) {
                subString ="";
                tempHashMap =fromHereToIterate;
                queue.add(tempHashMap.get(key));
                while (queue.size() >0){
                    tempTrieNode =queue.poll();
                    tempHashMap =tempTrieNode.getChildNodes();
                    subString +=tempTrieNode.getValue();
                    Set<String> keySet1 = tempHashMap.keySet();
                    for (String s1 : keySet1) {
                        queue.add(tempHashMap.get(s1));
                    }
                }
                result.add(str +subString);
            }

        }
        return result;
    }
}