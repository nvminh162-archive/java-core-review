package com.nvminh162.b04_builtin_functional_interface;

import java.util.function.*;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // 1. Predicate<T>
        // Nhận vào T, trả về boolean
        // Dùng để kiểm tra điều kiện
        // =====================================================
        Predicate<Integer> isAdultAge = age -> age >= 18;
        System.out.println(isAdultAge.test(20)); // true
        System.out.println(isAdultAge.test(16)); // false

        // =====================================================
        // 2. Function<T, R>
        // Nhận vào T, trả về R
        // Dùng để chuyển đổi dữ liệu
        // =====================================================
        Function<String, Integer> getLength = text -> text.length();
        System.out.println(getLength.apply("Java")); // 4

        // =====================================================
        // 3. Consumer<T>
        // Nhận vào T, không trả về gì
        // Dùng để xử lý, in, log dữ liệu
        // =====================================================
        Consumer<String> printer = text -> System.out.println(text);
        printer.accept("Hello Consumer");

        // =====================================================
        // 4. Supplier<T>
        // Không nhận vào gì, trả về T
        // Dùng để tạo hoặc cung cấp dữ liệu
        // =====================================================
        Supplier<String> supplier = () -> "Hello Supplier";
        System.out.println(supplier.get());

        // =====================================================
        // 5. BiFunction<T, U, R>
        // Nhận vào 2 tham số, trả về 1 kết quả
        // =====================================================
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(10, 20)); // 30

        // =====================================================
        // 6. UnaryOperator<T>
        // Nhận vào T, trả về T
        // Input và output cùng kiểu
        // =====================================================
        UnaryOperator<String> upper = String::toUpperCase;
        System.out.println(upper.apply("java")); // JAVA

        // =====================================================
        // 7. BinaryOperator<T>
        // Nhận vào 2 tham số cùng kiểu T, trả về T
        // =====================================================
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println(max.apply(10, 30)); // 30
    }
}
