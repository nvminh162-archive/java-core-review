package com.nvminh162.b01_why_collection;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // =========================
        // 1. ARRAY: kích thước cố định
        // =========================

        String[] studentsArray = new String[3];

        studentsArray[0] = "Minh";
        studentsArray[1] = "An";
        studentsArray[2] = "Binh";

        // Array có size cố định là 3.
        // Nếu thêm phần tử thứ 4 sẽ bị lỗi:
        // studentsArray[3] = "Long"; // ArrayIndexOutOfBoundsException

        System.out.println("Array:");
        for (String student : studentsArray) {
            System.out.println(student);
        }

        // =========================
        // 2. ARRAYLIST: kích thước động
        // =========================

        ArrayList<String> studentsList = new ArrayList<>();

        studentsList.add("Minh");
        studentsList.add("An");
        studentsList.add("Binh");

        // ArrayList có thể thêm tiếp, không cần khai báo size ban đầu
        studentsList.add("Long");

        System.out.println("\nArrayList:");
        for (String student : studentsList) {
            System.out.println(student);
        }

        // ArrayList có sẵn nhiều method tiện lợi
        studentsList.remove("An");

        System.out.println("\nAfter remove An:");
        System.out.println(studentsList);
    }
}
