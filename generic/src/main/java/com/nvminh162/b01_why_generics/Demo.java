package com.nvminh162.b01_why_generics;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Cách cũ: không dùng Generics
        // List có thể chứa mọi kiểu dữ liệu
        // =====================================================
        List oldList = new ArrayList();
        oldList.add("Java");
        oldList.add("Spring");
        oldList.add(100); // vẫn add được, nhưng dễ lỗi

        // Vì oldList không khai báo kiểu dữ liệu
        // nên khi lấy ra phải ép kiểu thủ công
        String text1 = (String) oldList.get(0);
        System.out.println(text1);

        // Dòng này chạy sẽ lỗi ClassCastException
        // Vì phần tử index 2 là Integer, không phải String
        // String text2 = (String) oldList.get(2);
        int text2 = (Integer) oldList.get(2);
        System.out.println(text2);

        // =====================================================
        // Cách mới: dùng Generics
        // List<String> nghĩa là list này chỉ chứa String
        // =====================================================
        List<String> names = new ArrayList<>();
        names.add("Java");
        names.add("Spring");
        // names.add(100); // Compile error
        // Lấy ra không cần ép kiểu
        String name = names.get(0);
        System.out.println(name);
    }
}
