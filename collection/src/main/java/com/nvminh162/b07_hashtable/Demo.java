package com.nvminh162.b07_hashtable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // 1. Hashtable là Map dạng key-value
        // =========================

        Map<Integer, String> students = new Hashtable<>();

        students.put(1, "Minh");
        students.put(2, "An");
        students.put(3, "Binh");

        System.out.println("Hashtable:");
        System.out.println(students);

        // Lấy value theo key
        System.out.println("Student id 1: " + students.get(1));

        // Key không được trùng
        // Nếu put trùng key, value cũ sẽ bị ghi đè
        students.put(1, "Minh Updated");

        System.out.println("After update key 1:");
        System.out.println(students);


        // =========================
        // 2. HashMap cho phép null
        // =========================

        Map<String, String> hashMap = new HashMap<>();

        hashMap.put(null, "Null key is allowed");
        hashMap.put("email", null);

        System.out.println("\nHashMap allows null:");
        System.out.println(hashMap);


        // =========================
        // 3. Hashtable KHÔNG cho phép null key hoặc null value
        // =========================

        Map<String, String> hashtable = new Hashtable<>();

        hashtable.put("username", "minh");

        try {
            hashtable.put(null, "Null key");
        } catch (NullPointerException e) {
            System.out.println("\nHashtable does not allow null key");
        }

        try {
            hashtable.put("email", null);
        } catch (NullPointerException e) {
            System.out.println("Hashtable does not allow null value");
        }


        // =========================
        // 4. Hashtable là synchronized
        // =========================

        // Hashtable thread-safe vì các method của nó được synchronized.
        // Nhưng nó là class cũ, hiện nay ít dùng trong project mới.
        // Nếu cần thread-safe Map, thường ưu tiên ConcurrentHashMap hơn.
    }
}
