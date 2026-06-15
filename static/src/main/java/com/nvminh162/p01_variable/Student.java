package com.nvminh162.p01_variable;

public class Student {
    // static: dùng chung cho tất cả Student
    public static String schoolName = "ABC University";
    public static int totalStudents = 0;

    // non-static: mỗi object có name riêng
    private String name;

    public Student(String name) {
        this.name = name;
        totalStudents++;
    }

    public void printInfo() {
        System.out.println(name + " - " + schoolName);
    }
}