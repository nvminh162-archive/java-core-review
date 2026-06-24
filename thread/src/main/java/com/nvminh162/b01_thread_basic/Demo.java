package com.nvminh162.b01_thread_basic;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Thread hiện tại đang chạy main()
        // Mặc định chương trình Java có main thread
        // =====================================================
        System.out.println("Main thread: " + Thread.currentThread().getName());


        // =====================================================
        // Tạo thread mới bằng lambda
        // Code trong lambda sẽ chạy ở thread khác
        // =====================================================
        Thread thread1 = new Thread(() -> {
            System.out.println("Task 1 running in: " + Thread.currentThread().getName());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("Task 2 running in: " + Thread.currentThread().getName());
        });

        // =====================================================
        // start() dùng để bắt đầu thread mới
        // JVM sẽ tự gọi run() bên trong thread mới
        // =====================================================

        thread1.start();
        thread2.start();

        System.out.println("Main thread finished");
    }
}