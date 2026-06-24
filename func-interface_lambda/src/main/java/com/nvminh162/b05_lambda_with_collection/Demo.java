package com.nvminh162.b05_lambda_with_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Minh", 22),
                new User("An", 17),
                new User("Binh", 25),
                new User("Long", 20)
        ));

        // =====================================================
        // 1. forEach + lambda
        // Duyệt từng user trong list
        // =====================================================
        users.forEach(user -> System.out.println(user));

        // =====================================================
        // 2. removeIf + lambda
        // Xóa user nhỏ hơn 18 tuổi
        // removeIf nhận Predicate<T>
        // =====================================================
        users.removeIf(user -> user.getAge() < 18);
        System.out.println("\nAfter remove age < 18:");
        users.forEach(user -> System.out.println(user));

        // =====================================================
        // 3. sort + lambda
        // Sort theo tuổi tăng dần
        // sort nhận Comparator<T>
        // =====================================================
        // users.sort((u1, u2) -> u1.getAge() - u2.getAge());
        System.out.println("\nAfter sort by age:");
        users.forEach(user -> System.out.println(user));

        // =====================================================
        // 4. Stream + lambda
        // Lọc user >= 20 tuổi, lấy name
        // =====================================================
        List<String> names = new ArrayList<>();
        users.stream()
                .filter(user -> user.getAge() >= 20)
                .map(user -> user.getName())
                .forEach(name -> names.add(name));
        System.out.println("\nNames age >= 20:");
        System.out.println(names);
    }
}
