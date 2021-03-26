package com.zx.testClass;

import lombok.Data;

import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    static class NestNode {
        private NestedInteger nest;
        private NestNode next;

        public NestNode(){}
        public NestNode(NestedInteger nest){
            this.nest = nest;
        }
        public NestNode(NestedInteger nest, NestNode next){
            this.nest = nest;
            this.next = next;
        }
    }

    private boolean searched;
    private final NestNode head;

    public NestedIterator(List<NestedInteger> nestedList){
        this.searched = false;
        this.head = new NestNode();
        appendList(nestedList);
    }

    @Override
    public boolean hasNext() {
        searched = true;
        while (head.next != null){
            NestedInteger nest = head.next.nest;
            if (nest.isInteger()){
                return true;
            }
            head.next = head.next.next;
            if (!nest.getList().isEmpty()){
                appendList(nest.getList());
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (head.next==null || (!searched && !hasNext())){
            return null;
        }
        searched = false;
        NestNode next = head.next;
        head.next = next.next;
        return next.nest.getInteger();
    }

    private void appendList(List<NestedInteger> nestedList){
        NestNode p = this.head;
        NestNode tail = p.next;
        for (NestedInteger next : nestedList) {
            p.next = new NestNode(next);
            p = p.next;
        }
        p.next = tail;
    }
}


interface NestedInteger{
     boolean isInteger();
     Integer getInteger();
     List<NestedInteger> getList();
}
