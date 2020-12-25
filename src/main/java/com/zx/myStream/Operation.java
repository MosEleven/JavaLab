package com.zx.myStream;

import java.util.*;
import java.util.function.Consumer;

public class Operation<S_IN,S_OUT> extends MyPipeLine<S_IN,S_OUT> {

    Operation(){
        operation = this;
    }

    Operation(MyPipeLine<?,?> up){
        super(up);
        operation = this;
    }

    protected void begin(int size){
        next.operation.begin(size);
    }
    protected void accept(S_IN s_in){
        System.out.println("accept in base class");
    }
    protected void end(){
        next.operation.end();
    }

    static <S_IN,S_OUT> Operation<S_OUT,S_OUT> makeDistinct(MyPipeLine<S_IN,S_OUT> up){
        return new Operation<S_OUT,S_OUT>(up){
            Set<S_OUT> seen;

            @Override
            protected void begin(int size) {
                seen = new HashSet<>(size);
                next.operation.begin(size);
            }

            @Override
            protected void accept(S_OUT s_out) {
                if (seen.add(s_out)){
                    next.operation.accept(s_out);
                }
            }
        };
    }

    static<S_IN,S_OUT> Operation<S_OUT,S_OUT> makeSort(MyPipeLine<S_IN,S_OUT> up,Comparator<S_OUT> comparator){
        return new Operation<S_OUT,S_OUT>(up){
            List<S_OUT> list;

            @Override
            protected void begin(int size) {
                System.out.println("sorting");
                list = new ArrayList<>(size);
            }

            @Override
            protected void accept(S_OUT s_out) {
                list.add(s_out);
            }

            @Override
            protected void end() {
                list.sort(comparator);
                next.operation.begin(list.size());
                for (S_OUT s_out : list) {
                    next.operation.accept(s_out);
                }
                next.operation.end();
            }
        };
    }

    static<S_OUT> Operation<S_OUT,S_OUT> makeForeach(Consumer<S_OUT> consumer){
        return new Operation<S_OUT,S_OUT>(){
            @Override
            protected void begin(int size) {
                System.out.println("Stream is beginning");
            }

            @Override
            protected void accept(S_OUT s_out) {
                consumer.accept(s_out);
            }

            @Override
            protected void end() {
                System.out.println("Stream is end");
            }
        };
    }

}
