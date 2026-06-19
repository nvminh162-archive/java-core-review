package com.nvminh162.b06_choose_collection;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // =========================
        // Case 1: Danh sách sản phẩm, cho phép trùng, cần giữ thứ tự
        // Dùng List
        // =========================

        List<String> cartItems = new ArrayList<>();

        cartItems.add("Laptop");
        cartItems.add("Mouse");
        cartItems.add("Mouse"); // mua 2 con chuột thì duplicate là hợp lý

        System.out.println("Cart items:");
        System.out.println(cartItems);


        // =========================
        // Case 2: Danh sách email đã đăng ký, không cho trùng
        // Dùng Set
        // =========================

        Set<String> registeredEmails = new HashSet<>();

        registeredEmails.add("minh@gmail.com");
        registeredEmails.add("an@gmail.com");
        registeredEmails.add("minh@gmail.com"); // bị bỏ qua

        System.out.println("\nRegistered emails:");
        System.out.println(registeredEmails);


        // =========================
        // Case 3: Tìm user theo id
        // Dùng Map
        // =========================

        Map<Integer, String> userMap = new HashMap<>();

        userMap.put(1, "Minh");
        userMap.put(2, "An");
        userMap.put(3, "Binh");

        int userId = 2;

        System.out.println("\nFind user by id:");
        System.out.println(userMap.get(userId));


        // =========================
        // Case 4: Xử lý task lần lượt
        // Dùng Queue
        // =========================

        Queue<String> tasks = new ArrayDeque<>();

        tasks.offer("Send email");
        tasks.offer("Create invoice");
        tasks.offer("Notify customer");

        System.out.println("\nProcess tasks:");

        while (!tasks.isEmpty()) {
            String task = tasks.poll();
            System.out.println("Processing: " + task);
        }
    }
}
