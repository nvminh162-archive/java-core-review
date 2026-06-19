package com.nvminh162.b02_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // List là interface
        // ArrayList là class triển khai List
        List<String> names = new ArrayList<>();

        names.add("Minh");
        names.add("An");
        names.add("Binh");
        names.add("Minh"); // List cho phép duplicate

        System.out.println("List values:");
        System.out.println(names);

        // List giữ thứ tự thêm vào
        // Index bắt đầu từ 0
        System.out.println("First element: " + names.get(0));
        System.out.println("Second element: " + names.get(1));

        // Có thể sửa phần tử theo index
        names.set(1, "Long");

        System.out.println("After set index 1:");
        System.out.println(names);

        // Có thể xóa theo index hoặc theo value
        names.remove(2); // xóa phần tử ở index 2

        System.out.println("After remove index 2:");
        System.out.println(names);

        // =========================
        // ArrayList vs LinkedList
        // =========================

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("A");
        arrayList.add("B");

        linkedList.add("A");
        linkedList.add("B");

        // Về cách dùng cơ bản, ArrayList và LinkedList khá giống nhau
        System.out.println("\nArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);
    }
}
