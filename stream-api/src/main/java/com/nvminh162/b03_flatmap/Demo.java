package com.nvminh162.b03_flatmap;

import com.nvminh162.b00_common.DemoData;
import com.nvminh162.b00_common.User;

import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // Vấn đề:
        // Mỗi user có nhiều skills: List<String>
        //
        // users là List<User>
        // user.getSkills() là List<String>
        //
        // Nếu dùng map(User::getSkills)
        // kết quả sẽ là List<List<String>>
        // =====================================================

        List<List<String>> skillLists = users.stream()
                .map(User::getSkills)
                .collect(Collectors.toList());

        System.out.println("Using map():");
        System.out.println(skillLists);


        // =====================================================
        // flatMap() dùng để làm phẳng dữ liệu
        //
        // Từ:
        // List<List<String>>
        //
        // Thành:
        // List<String>
        // =====================================================

        List<String> allSkills = users.stream()
                .flatMap(user -> user.getSkills().stream())
                .collect(Collectors.toList());

        System.out.println("\nUsing flatMap():");
        System.out.println(allSkills);


        // =====================================================
        // Kết hợp distinct() để loại bỏ skill trùng
        // =====================================================

        List<String> uniqueSkills = users.stream()
                .flatMap(user -> user.getSkills().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("\nUnique skills:");
        System.out.println(uniqueSkills);
    }
}
