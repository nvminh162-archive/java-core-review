package com.nvminh162.b01_functional_interface;

// Functional Interface là interface chỉ có 1 abstract method
@FunctionalInterface
public interface Calculator {

    // Đây là abstract method duy nhất
    // Lambda sẽ implement method này
    int calculate(int a, int b);

    // Functional Interface vẫn được phép có default method
    default void printInfo() {
        System.out.println("This is a calculator");
    }

    // Functional Interface vẫn được phép có static method
    static void description() {
        System.out.println("Calculator is used to calculate numbers");
    }

    // Nếu mở dòng dưới thì sẽ lỗi
    // Vì Functional Interface chỉ được có 1 abstract method
    // int subtract(int a, int b);
}
