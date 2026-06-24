package com.nvminh162.b06_method_reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Minh", "An", "Binh", "Long");

        // =====================================================
        // Method Reference là cách viết ngắn hơn lambda
        // khi lambda chỉ gọi lại một method có sẵn
        // =====================================================

        // =====================================================
        // 1. Static method reference
        // Lambda:
        // name -> User.isLongName(name)
        // Method reference:
        // User::isLongName
        // =====================================================
        Predicate<String> checkName = User::isLongName;
        System.out.println(checkName.test("Minh")); // true
        System.out.println(checkName.test("An"));   // false

        // =====================================================
        // 2. Instance method reference của object cụ thể
        // Lambda:
        // text -> System.out.println(text)
        // Method reference:
        // System.out::println
        // =====================================================
        Consumer<String> printer = System.out::println;
        printer.accept("Hello Method Reference");

        // =====================================================
        // 3. Instance method reference của class
        // Lambda:
        // text -> text.length()
        // Method reference:
        // String::length
        // =====================================================
        Function<String, Integer> lengthFunction = String::length;
        System.out.println(lengthFunction.apply("Java")); // 4

        // =====================================================
        // 4. Constructor reference
        // Lambda:
        // name -> new User(name)
        // Method reference:
        // User::new
        // =====================================================
        Function<String, User> userCreator = User::new;
        User user = userCreator.apply("Minh");
        System.out.println(user);

        // =====================================================
        // 5. Dùng method reference trong Stream
        // =====================================================
        List<User> users = new ArrayList<>();
        names.stream()
                .map(User::new)       // String -> User
                .forEach(users::add); // thêm user vào list
        System.out.println("\nUsers:");
        users.forEach(User::printName);
    }
}
