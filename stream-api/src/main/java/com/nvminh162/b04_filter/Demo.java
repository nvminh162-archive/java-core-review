package com.nvminh162.b04_filter;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // filter() dùng để lọc dữ liệu theo điều kiện
        // Điều kiện trả về true thì giữ lại
        // Điều kiện trả về false thì bỏ qua
        // =====================================================

        List<User> adultUsers = users.stream()
                .filter(user -> user.getAge() >= 18)
                .collect(Collectors.toList());

        System.out.println("Adult users:");
        adultUsers.forEach(System.out::println);


        // =====================================================
        // Lọc nhiều điều kiện
        // User phải active và thuộc phòng IT
        // =====================================================

        List<User> activeITUsers = users.stream()
                .filter(User::isActive)
                .filter(user -> user.getDepartment().equals("IT"))
                .collect(Collectors.toList());

        System.out.println("\nActive IT users:");
        activeITUsers.forEach(System.out::println);

        // =====================================================
        // findFirst(): tìm phần tử đầu tiên thỏa điều kiện
        // Trả về Optional vì có thể không tìm thấy
        // =====================================================

        Optional<User> firstAdult = users.stream()
                .filter(user -> user.getAge() >= 18)
                .findFirst();

        System.out.println("\nFirst adult user:");
        firstAdult.ifPresent(System.out::println);


        // =====================================================
        // anyMatch(): có ít nhất 1 phần tử thỏa điều kiện không?
        // =====================================================

        boolean hasITUser = users.stream()
                .anyMatch(user -> user.getDepartment().equals("IT"));

        System.out.println("\nHas IT user:");
        System.out.println(hasITUser);


        // =====================================================
        // allMatch(): tất cả phần tử có thỏa điều kiện không?
        // =====================================================

        boolean allActive = users.stream()
                .allMatch(User::isActive);

        System.out.println("\nAll users active:");
        System.out.println(allActive);
    }
}
