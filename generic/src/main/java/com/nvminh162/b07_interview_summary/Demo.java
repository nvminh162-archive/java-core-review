package com.nvminh162.b07_interview_summary;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Generics dùng rất nhiều trong Collection
        // =====================================================
        List<String> names = new ArrayList<>();
        names.add("Minh");
        names.add("An");
        // names.add(100); // Compile error

        String firstName = names.get(0);
        System.out.println(firstName);


        // =====================================================
        // Generics cũng dùng trong Map, Optional, Stream...
        //
        // Map<Integer, User>
        // Optional<User>
        // List<Product>
        // ApiResponse<User>
        // =====================================================
    }
}
