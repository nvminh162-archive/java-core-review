package com.nvminh162.p05_static_block;

public class DatabaseConfig {
    public static String url;

    static {
        System.out.println("Static block is running...");
        url = "jdbc:mysql://localhost:3306/mydb";
    }

    public DatabaseConfig() {
        System.out.println("Constructor is running...");
    }
}