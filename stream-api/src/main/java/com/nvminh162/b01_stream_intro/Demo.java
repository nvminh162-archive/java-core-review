package com.nvminh162.b01_stream_intro;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // CÁCH CŨ: Imperative style
        // Tức là mình nói rõ từng bước "làm như thế nào"
        // =====================================================

        List<String> namesOldWay = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() > 18) {
                namesOldWay.add(user.getName());
            }
        }

        Collections.sort(namesOldWay);

        System.out.println("Old way:");
        System.out.println(namesOldWay);


        // =====================================================
        // CÁCH MỚI: Stream API
        // Tức là mình mô tả "muốn kết quả gì"
        // =====================================================

        List<String> namesStream = users.stream()              // Source: lấy dữ liệu từ List
                .filter(user -> user.getAge() > 18)            // Intermediate: lọc user > 18 tuổi
                .map(User::getName)                            // Intermediate: chuyển User -> String name
                .sorted()                                      // Intermediate: sắp xếp tên
                .collect(Collectors.toList());                 // Terminal: gom kết quả thành List

        System.out.println("\nStream way:");
        System.out.println(namesStream);


        // =====================================================
        // DEMO LAZY EVALUATION
        // filter/map chưa chạy ngay nếu chưa có terminal operation
        // =====================================================

        System.out.println("\nLazy demo:");

        users.stream()
                .filter(user -> {
                    System.out.println("Filtering: " + user.getName());
                    return user.getAge() > 18;
                })
                .map(user -> {
                    System.out.println("Mapping: " + user.getName());
                    return user.getName();
                });

        // Đến đây chưa in Filtering/Mapping
        // Vì stream chưa có terminal operation như collect(), forEach(), count()


        System.out.println("\nAdd terminal operation:");

        users.stream()
                .filter(user -> {
                    System.out.println("Filtering: " + user.getName());
                    return user.getAge() > 18;
                })
                .map(user -> {
                    System.out.println("Mapping: " + user.getName());
                    return user.getName();
                })
                .collect(Collectors.toList());

        // Khi có collect(), pipeline mới thật sự chạy
    }
}