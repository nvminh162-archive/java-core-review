package com.nvminh162.p04_private_static;

public class PasswordUtils {
    private static final int MIN_LENGTH = 6;

    public static boolean isValidPassword(String password) {
        return password != null && hasEnoughLength(password);
    }

    private static boolean hasEnoughLength(String password) {
        return password.length() >= MIN_LENGTH;
    }
}
