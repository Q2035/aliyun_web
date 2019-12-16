package com.test.aliyun_web.util;

import java.util.HashMap;

/**
 * 作为Node结点存储中文
 */
public class TrieNode {
    /**
     * 汉字
     * 是否构成名字
     * 子结点
     */
    private Character value;
    private Boolean isNames;
    private HashMap<String ,TrieNode> childNodes;

    public TrieNode(Character value, Boolean isNames, HashMap<String, TrieNode> childNodes) {
        this.value = value;
        this.isNames = isNames;
        this.childNodes = childNodes;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                ", value=" + value +
                ", isNames=" + isNames +
                ", childNodes=" + childNodes +
                '}';
    }


    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Boolean getNames() {
        return isNames;
    }

    public void setNames(Boolean names) {
        isNames = names;
    }

    public HashMap<String, TrieNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(HashMap<String, TrieNode> childNodes) {
        this.childNodes = childNodes;
    }

    public TrieNode() {
    }

    public TrieNode(Character value, Boolean isNames) {
        this.value = value;
        this.isNames = isNames;
    }
}
