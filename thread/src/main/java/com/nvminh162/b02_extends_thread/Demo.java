package com.nvminh162.b02_extends_thread;

public class Demo {
    public static void main(String[] args) {

        MyThread thread = new MyThread();
        // Đặt tên thread cho dễ nhìn output
        thread.setName("Worker-1");

        MyThread thread2 = new MyThread();
        // Đặt tên thread cho dễ nhìn output
        thread2.setName("Worker-2");

        // start() mới tạo thread mới
        thread.start();
        thread2.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
