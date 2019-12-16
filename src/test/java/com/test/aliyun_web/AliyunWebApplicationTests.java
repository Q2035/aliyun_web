package com.test.aliyun_web;

import com.test.aliyun_web.util.TrieNode;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
class AliyunWebApplicationTests {

    @Test
    void contextLoads() {
        List files =new ArrayList();
        File file = new File("D:" +File.separator + "Software" + File.separator);
        if (!file.exists()){
        }else {
            String[] list = file.list();
            for (String s : list) {
                files.add(s);
            }
            for (Object o : files) {
                System.out.println((String) o);
            }
        }
    }

    @Test
    void test01(){
//        String msg ="jdk11.tar.gz";
//        System.out.println("hello".hashCode());
//        System.out.println("helo".hashCode());
//        System.out.println(msg.hashCode());
        System.out.println(new Date());
    }

    @Test
    void  test02(){
//        输出UTF-8格式编码
        String msg ="hello";
        StringBuffer stringBuffer =new StringBuffer();
        System.out.println(msg+"的UTF-8编码:");
        for (int i =0;i<msg.length();i++){
            stringBuffer.append(" "+Integer.toString(msg.charAt(i),16));
        }
        System.out.println(stringBuffer);
    }

    @Test
    void test03(){
        HashMap<String, String> map = new HashMap<>();
        map.put("hello","world");
        map.put("hello","world,se");
        map.put("helloo","world");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        System.out.println(entries);
    }

    @Test
    void test04(){
        TrieNode trieNode =new TrieNode('a',false,new HashMap<String ,TrieNode>());
        Stack stack =new Stack();
        stack.push(trieNode);
        HashMap<String ,TrieNode> hashMap =trieNode.getChildNodes();
        hashMap.put("hello",new TrieNode());
        System.out.println(hashMap.get("ww"));
        System.out.println(trieNode.getChildNodes());
    }
}
