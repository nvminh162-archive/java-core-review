package com.nvminh162.b06_groupingby;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // groupingBy() dùng để nhóm dữ liệu theo một key nào đó
        // Ví dụ: nhóm user theo department
        // =====================================================
        Map<String, List<User>> usersByDepartment = users.stream()
                .collect(Collectors.groupingBy(User::getDepartment));
        System.out.println("Users by department:");
        usersByDepartment.forEach((department, userList) -> {
            System.out.println(department + " -> " + userList);
        });

        // =====================================================
        // Đếm số user trong mỗi department
        // =====================================================
        Map<String, Long> countByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("\nCount by department:");
        System.out.println(countByDepartment);

        // =====================================================
        // Tính lương trung bình theo department
        // =====================================================
        Map<String, Double> averageSalaryByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.averagingDouble(User::getSalary)
                ));
        System.out.println("\nAverage salary by department:");
        System.out.println(averageSalaryByDepartment);

        // =====================================================
        // Nhóm theo department nhưng chỉ lấy danh sách tên
        // =====================================================
        Map<String, List<String>> namesByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.mapping(User::getName, Collectors.toList())
                ));
        System.out.println("\nNames by department:");
        System.out.println(namesByDepartment);
    }
}
