package com.nvminh162.b01_functional_interface;

public class Demo {
    public static void main(String[] args) {

        // Vì Calculator chỉ có 1 abstract method là calculate()
        // nên Java cho phép dùng lambda để implement method đó

        Calculator sum = (a, b) -> a + b;
        Calculator sub = (a, b) -> a - b;

        System.out.println(sum.calculate(10, 5)); // 15
        System.out.println(sub.calculate(10, 5));

        // Gọi default method
        sum.printInfo();

        // Gọi static method
        Calculator.description();
    }
}
