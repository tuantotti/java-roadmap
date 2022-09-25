package com.java.concurrency.collection_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * https://javarevisited.blogspot.com/2016/05/what-is-difference-between-synchronized.html#axzz7edjOfB5Z
 */
public class SynchronizedCollectionVSConcurrentCollection {
    public static void main(String[] args) {
        // lock the whole collection by using the intrisic lock
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // never lock the whole collection --> higher performance (by using Reentrant Lock)
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }
}
