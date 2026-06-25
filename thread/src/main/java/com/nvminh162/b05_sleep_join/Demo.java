package com.nvminh162.b05_sleep_join;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Worker running: " + i);

                try {
                    // sleep() làm thread hiện tại tạm ngủ 500ms
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Worker was interrupted");
                }
            }
        });
        worker.start();

        // =====================================================
        // join() làm main thread chờ worker chạy xong
        // Nếu không có join(), main có thể in finished trước worker
        // =====================================================

        worker.join();

        System.out.println("Main finished after worker completed");
    }
}
