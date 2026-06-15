package com.nvminh162.p05_static_block;

public class Main {
    public static void main(String[] args) {
        System.out.println(DatabaseConfig.url);
        DatabaseConfig.url = "new url";

        DatabaseConfig db1 = new DatabaseConfig();
        DatabaseConfig db2 = new DatabaseConfig();
    }
}
