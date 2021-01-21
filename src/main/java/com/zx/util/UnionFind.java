package com.zx.util;

public class UnionFind {

    private final int[] parent;

    public UnionFind(int len){
        this.parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    public int findRoot(int n){
        return n==parent[n]? n : (parent[n] = findRoot(parent[n]));
    }

    public void mergeRoot(int x, int y){
        parent[y] = x;
    }
}
