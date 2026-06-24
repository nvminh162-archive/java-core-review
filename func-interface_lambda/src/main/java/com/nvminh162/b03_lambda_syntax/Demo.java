package com.nvminh162.b03_lambda_syntax;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // 1. Lambda không có tham số
        // =====================================================
        NoParam task = () -> System.out.println("Running task");
        task.run();

        // =====================================================
        // 2. Lambda có 1 tham số
        // Nếu chỉ có 1 tham số thì có thể bỏ dấu ()
        // =====================================================
        OneParam printer = System.out::println;
        // OneParam printer = message -> System.out.println(message);
        printer.print("Hello Lambda");

        // =====================================================
        // 3. Lambda có 2 tham số
        // Nếu có từ 2 tham số trở lên thì dùng dấu ()
        // =====================================================
        // TwoParams sum = (a, b) -> a + b;
        TwoParams sum = Integer::sum;
        System.out.println(sum.calculate(10, 20)); // 30

        // =====================================================
        // 4. Lambda có nhiều dòng xử lý
        // Nếu dùng {} và method có return thì phải return rõ ràng
        // =====================================================
        TwoParams multiply = (a, b) -> {
            int result = a * b;
            return result;
        };
        System.out.println(multiply.calculate(5, 4)); // 20
    }
}
