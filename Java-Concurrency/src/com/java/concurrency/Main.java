package com.java.concurrency;

import java.util.ArrayList;
import java.util.List;

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

class sbc extends Student{

    public sbc(String name, int id) {
        super(name, id);
    }
}
public class Main {

    public static void main(String[] args) {
        Student sbc = new sbc("tuan",1);
        System.out.println(sbc.getClass().getSimpleName());
    }
}
