package com.zx.myStream;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyPipeLine<S_IN,S_OUT> implements MyStream<S_OUT>{
    List<S_OUT> list;
    int size;
    int index;

    Supplier<S_OUT> supplier;

    MyPipeLine head;

    Operation next;


    MyPipeLine(){}

    MyPipeLine(MyPipeLine<?,?> up){
        this.head = up.head;
    }

    MyPipeLine(List<S_OUT> newList){
        size = newList.size();
        list = newList;
        supplier = ()-> newList.get(index);
        head = this;
    }



    @Override
    public MyStream<S_OUT> filter(Predicate<S_OUT> predicate) {
        return new Operation<S_OUT,S_OUT>(this){

            @Override
            protected void accept(S_OUT s_out) {
                if (predicate.test(s_out)){
                    next.accept(s_out);
                }
            }
        };
    }

    @Override
    public MyStream<S_OUT> distinct() {
        return Operation.makeDistinct(this);
    }

    @Override
    public <R> MyStream<R> map(Function<S_OUT, R> function) {
        return new Operation<S_OUT,R>(this){
            @Override
            protected void accept(S_OUT sot) {
                this.next.accept(function.apply(sot));
            }
        };
    }

    @Override
    public MyStream<S_OUT> sort(Comparator<S_OUT> comparator) {
        return Operation.makeSort(this,comparator);
    }

    @Override
    public void foreach(Consumer<S_OUT> consumer) {
        this.next = Operation.makeForeach(consumer);
        execute(head);
    }



    protected void execute(MyPipeLine p){
        p.next.begin(p.size);
        for (p.index=0;p.index<p.size;p.index++){
            p.next.accept(p.supplier.get());
        }
        p.next.end();
    }

}
