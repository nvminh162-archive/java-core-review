package com.nvminh162.b04_generic_method;

public class Demo {

    // =====================================================
    // Generic method
    // <T> đặt trước kiểu trả về
    // Method này nhận vào T và trả về T
    // Tùy lúc gọi mà T có thể là String, Integer, Double...
    // =====================================================
    public static <T> T printAndReturn(T value) {
        System.out.println("Value: " + value);
        return value;
    }

    // Generic method với mảng
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        // T là String
        String text = printAndReturn("Java");
        // T là Integer
        Integer number = printAndReturn(100);
        // T là Double
        Double price = printAndReturn(99.5);

        System.out.println(text);
        System.out.println(number);
        System.out.println(price);

        // =====================================================
        // Demo printArray
        // =====================================================
        String[] names = {"Minh", "An", "Binh"};
        Integer[] numbers = {1, 2, 3};
        System.out.println("\nNames:");
        printArray(names);
        System.out.println("\nNumbers:");
        printArray(numbers);
    }
}
