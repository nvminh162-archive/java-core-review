package com.nvminh162.b02_generic_class;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Box<String>
        // Lúc này T được hiểu là String
        // =====================================================
        Box<String> stringBox = new Box<>();
        stringBox.setValue("Hello Generic");
        String text = stringBox.getValue();
        System.out.println(text);

        // =====================================================
        // Box<Integer>
        // Lúc này T được hiểu là Integer
        // =====================================================
        Box<Integer> intBox = new Box<>();
        intBox.setValue(100);
        Integer number = intBox.getValue();
        System.out.println(number);

        // =====================================================
        // Lợi ích:
        // Cùng một class Box<T>
        // nhưng dùng được cho nhiều kiểu dữ liệu khác nhau
        // =====================================================
        // stringBox.setValue(123); // lỗi vì stringBox chỉ nhận String
        // intBox.setValue("Java"); // lỗi vì intBox chỉ nhận Integer
    }
}
