package com.java.concurrency;

import java.util.PriorityQueue;
import java.util.Queue;

abstract class Student {
    protected String name;
    protected int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class sbc extends Student {

    public sbc(String name, int id) {
        super(name, id);
    }
}

public class Main {

    public static void main(String[] args) {
        // Create a Priority Queue
        Queue<Integer> numbers = new PriorityQueue<>();

        // Add items to a Priority Queue (ENQUEUE)
        numbers.add(750);
        numbers.add(500);
        numbers.add(900);
        numbers.add(100);

        // Remove items from the Priority Queue (DEQUEUE)
        while (!numbers.isEmpty()) {
            System.out.println(numbers.remove());
        }

        Queue<String> strings = new PriorityQueue<>();

        // Add items to a Priority Queue (ENQUEUE)
        strings.add("tuan");
        strings.add("dat");
        strings.add("dung");

        System.out.println(strings);


        while (!strings.isEmpty()) {
            System.out.println(strings.poll());
        }

    }
}
