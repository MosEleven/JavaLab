package com.zx.util;


/**
 * UnionFind简介
 * 所有连通图问题都可以尝试用并查集解决
 * 并查集要解决的问题就是合并集合的传播，通过两个点就把两个集合都合并
 * 而且使用数组来模拟树的结构，从而避免使用Map等集合元素,也可以选择用Map实现
 *
 * @author zengxin
 * @date 2021-01-22 11:14
 */
public class UnionFind {

    private final int[] parent;
    private int size;

    public UnionFind(int len){
        this(len,len);
    }

    public UnionFind(int len, int size){
        this.size = size;
        this.parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    public int size(){
        return this.size;
    }

    public int findRoot(int n){
        return n==parent[n]? n : (parent[n] = findRoot(parent[n]));
    }

    public boolean mergeRoot(int x, int y){
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX != rootY){
            size--;
            parent[rootY] = rootX;
            return true;
        }
        return false;
    }
}
