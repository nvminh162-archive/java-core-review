package com.nvminh162.b05_reduce;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // reduce() dùng để gộp nhiều phần tử thành 1 kết quả
        // Ví dụ: tổng, tích, max, nối chuỗi
        // =====================================================

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum:");
        System.out.println(sum);

        // Luồng chạy:
        // ban đầu a = 0
        // b = 1 -> a + b = 1
        // b = 2 -> 1 + 2 = 3
        // b = 3 -> 3 + 3 = 6
        // b = 4 -> 6 + 4 = 10
        // b = 5 -> 10 + 5 = 15


        // =====================================================
        // Viết ngắn hơn bằng Integer::sum
        // =====================================================

        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("\nSum 2:");
        System.out.println(sum2);


        // =====================================================
        // Tìm max bằng reduce
        // =====================================================

        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

        System.out.println("Max:");
        System.out.println(max);

        // Luồng chạy:
        // ban đầu a = Integer.MIN_VALUE (-2147483648)
        // b = 1 -> -2147483648 > 1 ? false -> max hiện tại = 1
        // b = 2 -> 1 > 2 ? false -> max hiện tại = 2
        // b = 3 -> 2 > 3 ? false -> max hiện tại = 3
        // b = 4 -> 3 > 4 ? false -> max hiện tại = 4
        // b = 5 -> 4 > 5 ? false -> max hiện tại = 5


        // =====================================================
        // Nối chuỗi bằng reduce
        // =====================================================

        List<String> names = Arrays.asList("Minh", "An", "Binh");

        String result = names.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("\nJoin names:");
        System.out.println(result.trim());


        // =====================================================
        // Tính tổng salary bằng reduce
        // Thực tế dùng mapToDouble().sum() sẽ gọn hơn
        // =====================================================

        List<User> users = DemoData.users();

        double totalSalary = users.stream()
                .map(User::getSalary)
                .reduce(0.0, Double::sum);

        System.out.println("\nTotal salary:");
        System.out.println(totalSalary);
    }
}
