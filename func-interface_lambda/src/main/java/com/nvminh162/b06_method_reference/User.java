package com.nvminh162.b06_method_reference;

public class User {
    private String name;

    public User() {
        this.name = "Default User";
    }

    public User(String name) {
        this.name = name;
    }

    public static boolean isLongName(String name) {
        return name.length() >= 4;
    }

    public String getName() {
        return name;
    }

    public void printName() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
