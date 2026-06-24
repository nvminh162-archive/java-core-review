package com.nvminh162.b01_functional_interface;

// Functional Interface là interface chỉ có 1 abstract method
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    // Được phép có default method
    default void printInfo() {
        System.out.println("This is a calculator");
    }

    // Được phép có static method
    static void description() {
        System.out.println("Calculator is used to calculate numbers");
    }

    // Nếu thêm abstract method thứ 2 thì sẽ lỗi
    // int subtract(int a, int b);
}

public class Demo {
    public static void main(String[] args) {

        // Vì Calculator chỉ có 1 abstract method
        // nên Java cho phép dùng lambda để implement method đó

        Calculator sum = (a, b) -> a + b;

        int result = sum.calculate(10, 5);

        System.out.println(result); // 15

        sum.printInfo();

        Calculator.description();
    }
}