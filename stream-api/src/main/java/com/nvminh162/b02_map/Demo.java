package com.nvminh162.b02_map;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // map() dùng để biến đổi dữ liệu
        // Ví dụ: User -> String name
        // =====================================================

        List<String> names = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("Names:");
        System.out.println(names);


        // =====================================================
        // map() cũng có thể transform String -> String khác
        // =====================================================

        List<String> upperNames = users.stream()
                .map(User::getName)              // User -> name
                .map(String::toUpperCase)        // name -> uppercase name
                .collect(Collectors.toList());

        System.out.println("\nUpper names:");
        System.out.println(upperNames);


        // =====================================================
        // mapToDouble(): dùng khi muốn lấy dữ liệu dạng double
        // Ví dụ: User -> salary
        // =====================================================

        List<Double> salaries = users.stream()
                .map(User::getSalary)
                .collect(Collectors.toList());

        System.out.println("\nSalaries:");
        System.out.println(salaries);

        double totalSalary = users.stream()
                .mapToDouble(User::getSalary)
                .sum();

        System.out.println("\nTotal salary:");
        System.out.println(totalSalary);
    }
}
