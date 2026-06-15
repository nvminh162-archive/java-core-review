package com.nvminh162.p01_variable;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Minh");
        Student s2 = new Student("An");
        Student s3 = new Student("Ngọc");

        s1.printInfo();
        s2.printInfo();

        System.out.println("Total students: " + Student.totalStudents);
    }
}
