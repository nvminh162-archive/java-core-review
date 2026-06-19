package com.nvminh162.b04_map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        // Map lưu dữ liệu theo dạng key-value
        // Ví dụ: studentId -> studentName

        Map<Integer, String> students = new HashMap<>();

        students.put(1, "Minh");
        students.put(2, "An");
        students.put(3, "Binh");

        System.out.println("HashMap:");
        System.out.println(students);

        // Lấy value bằng key
        System.out.println("Student id 1: " + students.get(1));

        // Key không được trùng
        // Nếu put lại cùng key, value cũ sẽ bị ghi đè
        students.put(1, "Minh Updated");

        System.out.println("After update key 1:");
        System.out.println(students);

        // Value có thể trùng
        students.put(4, "An");

        System.out.println("After add duplicate value:");
        System.out.println(students);

        // Kiểm tra key có tồn tại không
        System.out.println("Contains key 2: " + students.containsKey(2));

        // Duyệt Map
        System.out.println("\nLoop Map:");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // =========================
        // LinkedHashMap
        // =========================

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(3, "Binh");
        linkedHashMap.put(1, "Minh");
        linkedHashMap.put(2, "An");

        System.out.println("\nLinkedHashMap:");
        System.out.println(linkedHashMap);

        // LinkedHashMap giữ thứ tự thêm vào


        // =========================
        // TreeMap
        // =========================

        Map<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "Binh");
        treeMap.put(1, "Minh");
        treeMap.put(2, "An");

        System.out.println("\nTreeMap:");
        System.out.println(treeMap);

        // TreeMap tự động sort theo key
    }
}
