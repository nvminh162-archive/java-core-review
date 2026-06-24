package com.nvminh162.b03_generic_pair;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Pair<String, Integer>
        // K là String, V là Integer
        // =====================================================
        Pair<String, Integer> studentScore = new Pair<>("Minh", 9);
        String studentName = studentScore.getKey();
        Integer score = studentScore.getValue();
        System.out.println(studentName + " = " + score);

        // =====================================================
        // Pair<Integer, String>
        // K là Integer, V là String
        // =====================================================
        Pair<Integer, String> user = new Pair<>(1, "Nguyen Van A");
        Integer userId = user.getKey();
        String userName = user.getValue();
        System.out.println(userId + " - " + userName);

        // =====================================================
        // Liên hệ thực tế:
        // Map<K, V> cũng là Generic kiểu key-value
        // Map<Integer, User>
        // Map<String, Product>
        // =====================================================
    }
}
