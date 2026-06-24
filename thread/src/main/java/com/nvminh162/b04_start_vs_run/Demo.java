package com.nvminh162.b04_start_vs_run;

public class Demo {
    public static void main(String[] args) {

        Runnable task = () -> { System.out.println("Running in thread: " + Thread.currentThread().getName()); };
        Thread thread = new Thread(task);
        // =====================================================
        // run() chỉ là gọi method bình thường
        // Không tạo thread mới
        // Code vẫn chạy trong main thread
        // =====================================================
        System.out.println("Call run():");
        thread.run();

        // =====================================================
        // start() mới tạo thread mới
        // JVM sẽ tạo thread mới rồi gọi run() bên trong thread đó
        // =====================================================
        System.out.println("\nCall start():");
        thread.start();
    }
}
