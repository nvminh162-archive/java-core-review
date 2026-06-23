package com.nvminh162.b00_common;

import java.util.Arrays;
import java.util.List;

public class DemoData {
    public static List<User> users() {
        return Arrays.asList(
                new User(1, "Minh", 22, true, "IT", 1000, Arrays.asList("Java", "Spring", "SQL")),
                new User(2, "An", 17, true, "HR", 700, Arrays.asList("Excel", "Communication")),
                new User(3, "Binh", 25, false, "IT", 1500, Arrays.asList("Java", "Docker", "AWS")),
                new User(4, "Long", 30, true, "Finance", 2000, Arrays.asList("Excel", "Accounting", "SQL")),
                new User(5, "Hoa", 28, true, "IT", 1800, Arrays.asList("Java", "Spring", "AWS")),
                new User(6, "Lan", 20, false, "HR", 900, Arrays.asList("Recruitment", "Communication")),
                new User(7, "Minh", 23, true, "IT", 1200, Arrays.asList("Java", "Docker", "SQL"))
        );
    }
}
