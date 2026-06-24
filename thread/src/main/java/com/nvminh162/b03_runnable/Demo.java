package com.nvminh162.b03_runnable;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Runnable chỉ là công việc
        // Muốn chạy công việc đó bằng thread thì đưa vào Thread
        // =====================================================
        Runnable task1 = new DownloadTask("video.mp4");
        Runnable task2 = new DownloadTask("image.png");

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.setName("Download-Thread-1");
        thread2.setName("Download-Thread-2");

        thread1.start();
        thread2.start();


        // =====================================================
        // Vì Runnable là Functional Interface
        // nên có thể viết bằng lambda
        // =====================================================
        Thread lambdaThread = new Thread(() -> { System.out.println("Lambda task running in: " + Thread.currentThread().getName()); });
        lambdaThread.setName("Lambda-Thread");
        lambdaThread.start();
    }
}