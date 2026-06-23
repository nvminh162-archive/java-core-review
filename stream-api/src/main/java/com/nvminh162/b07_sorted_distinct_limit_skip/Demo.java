package com.nvminh162.b07_sorted_distinct_limit_skip;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5, 3, 1, 3, 2, 5, 4);

        // =====================================================
        // distinct(): loại bỏ phần tử trùng
        // =====================================================
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct numbers:");
        System.out.println(distinctNumbers);


        // =====================================================
        // sorted(): sắp xếp tăng dần theo thứ tự tự nhiên
        // =====================================================
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nSorted numbers:");
        System.out.println(sortedNumbers);

        // =====================================================
        // distinct() + sorted()
        // Vừa loại trùng, vừa sort
        // =====================================================
        List<Integer> uniqueSortedNumbers = numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nUnique sorted numbers:");
        System.out.println(uniqueSortedNumbers);


        // =====================================================
        // Sort object theo salary tăng dần
        // =====================================================
        List<User> users = DemoData.users();
        List<User> usersBySalaryAsc = users.stream()
                .sorted(Comparator.comparing(User::getSalary))
                .collect(Collectors.toList());
        System.out.println("\nUsers by salary ascending:");
        usersBySalaryAsc.forEach(System.out::println);

        // =====================================================
        // Sort object theo salary giảm dần
        // =====================================================
        List<User> usersBySalaryDesc = users.stream()
                .sorted(Comparator.comparing(User::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("\nUsers by salary descending:");
        usersBySalaryDesc.forEach(System.out::println);


        // =====================================================
        // limit(): lấy N phần tử đầu tiên
        // Ví dụ: lấy top 3 user lương cao nhất
        // =====================================================
        List<User> top3SalaryUsers = users.stream()
                .sorted(Comparator.comparing(User::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\nTop 3 salary users:");
        top3SalaryUsers.forEach(System.out::println);

        // =====================================================
        // skip(): bỏ qua N phần tử đầu tiên
        // Thường dùng cho phân trang
        // =====================================================
        int page = 2;
        int pageSize = 2;
        List<User> page2Users = users.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        System.out.println("\nPage 2 users:");
        page2Users.forEach(System.out::println);
    }
}
