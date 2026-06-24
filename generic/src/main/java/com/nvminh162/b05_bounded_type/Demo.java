package com.nvminh162.b05_bounded_type;

import java.util.Arrays;
import java.util.List;

public class Demo {

    // =====================================================
    // <T extends Number>
    // Nghĩa là T chỉ được là Number hoặc class con của Number
    // Ví dụ: Integer, Double, Long, Float...
    // =====================================================
    public static <T extends Number> double sum(List<T> numbers) {
        double total = 0;
        for (T number : numbers) {
            // Vì T extends Number
            // nên chắc chắn gọi được doubleValue()
            total += number.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {

        List<Integer> intNumbers = Arrays.asList(1, 2, 3);
        List<Double> doubleNumbers = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println(sum(intNumbers));    // 6.0
        System.out.println(sum(doubleNumbers)); // 7.5

//         List<String> strings = Arrays.asList("A", "B");
//         sum(strings); // lỗi vì String không extends Number
    }
}
