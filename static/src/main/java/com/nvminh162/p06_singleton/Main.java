package com.nvminh162.p06_singleton;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Hello Java");

        System.out.println(logger1 == logger2);
    }
}
