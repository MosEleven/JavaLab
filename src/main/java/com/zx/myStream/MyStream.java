package com.zx.myStream;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface MyStream<T> {

    MyStream<T> filter(Predicate<T> predicate);

    <R> MyStream<R> map(Function<T,R> function);

    MyStream<T> distinct();


    MyStream<T> sort(Comparator<T> comparator);

    void foreach(Consumer<T> consumer);


    static<T> MyStream<T> ofList(List<T> newList){
        return new MyPipeLine<>(newList);
    }
}
