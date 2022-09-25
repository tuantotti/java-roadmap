package com.java.concurrency.collection_framework;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * link: https://medium.com/double-pointer/guide-to-concurrenthashmap-in-java-9ba810b5182d
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        Map<Integer, String> map = new Hashtable<>();
    }
}
