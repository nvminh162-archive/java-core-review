package com.nvminh162.p02_constant;

public class Main {
    public static void main(String[] args) {
        // dùng static
        System.out.println(AppConfig.APP_NAME);
        System.out.println(AppConfig.MAX_LOGIN_ATTEMPTS);
        // khong dùng static
        System.out.println(new AppConfig(999).TEST);
        System.out.println(new AppConfig(999).TEST);
    }
}
