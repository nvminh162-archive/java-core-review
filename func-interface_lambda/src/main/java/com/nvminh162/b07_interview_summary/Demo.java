package com.nvminh162.b07_interview_summary;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Minh", 22, true),
                new User("An", 17, true),
                new User("Binh", 25, false),
                new User("Long", 20, true)
        );

        // Predicate là Functional Interface
        // Nó có t1 abstrac method là test(T t)
        // Lambda bên dưới chính là implementation của test()
        Predicate<User> isAdult = user -> user.getAge() >= 18;

        // Method reference
        // Tương đương: user -> user.isActive()
        Predicate<User> isActive = User::isActive;

        List<String> result = users.stream()
                .filter(isAdult)          // giữ user >= 18 tuổi
                .filter(isActive)         // giữ user active
                .map(User::getName)       // User -> name
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
