package com.nvminh162.p03_static_method;

public class Main {
    public static void main(String[] args) {
        String str = "Hello";

        str.concat(" Java");

        System.out.println(str);
        // static method: gọi trực tiếp qua tên class, không cần tạo object
        System.out.println(MathUtils.square(5));

        // non-static method: phải tạo object trước rồi mới gọi được
        System.out.println(new MathUtils().max(10, 20));
    }
}
