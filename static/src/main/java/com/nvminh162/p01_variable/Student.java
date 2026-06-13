package com.nvminh162.p01_variable;

public class Student {
    static String schoolName = "IUH University";
    static int totalStudents = 0;

    String name;

    public Student(String name) {
        this.name = name;
        totalStudents++;
    }

    public void printInfo() {
        System.out.println(name + " - " + schoolName);
    }
}