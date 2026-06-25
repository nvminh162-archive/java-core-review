package com.nvminh162.b07_interview_summary;

public class Demo2 {

    // Biến này dùng để giữ Runnable-Thread chạy một lúc
    // volatile giúp thread khác thấy được khi main đổi keepRunning = false
    private static volatile boolean keepRunning = true;

    public static void main(String[] args) throws InterruptedException {

        // =====================================================
        // 1. NEW -> RUNNABLE -> TERMINATED
        // =====================================================

        // BƯỚC 1:
        // Tạo Thread object.
        // Lúc này thread chỉ mới được tạo ra, CHƯA chạy.
        Thread runnableThread = new Thread(() -> {

            // Code trong này sẽ chạy khi gọi start()
            // Dùng vòng while để giữ thread chạy một lúc
            // để main kịp kiểm tra trạng thái RUNNABLE.
            while (keepRunning) {
                // Không làm gì, chỉ giữ thread còn sống
            }

        }, "Runnable-Thread");

        // BƯỚC 2:
        // Vì chưa gọi start(), thread đang ở trạng thái NEW.
        System.out.println("1. NEW:");
        System.out.println(runnableThread.getState()); // NEW


        // BƯỚC 3:
        // Gọi start() để tạo thread mới.
        // Sau start(), JVM sẽ cho thread chạy code trong run().
        runnableThread.start();

        // Cho thread có thời gian bắt đầu chạy.
        Thread.sleep(100);

        // BƯỚC 4:
        // Thread đã start và đang chạy vòng while.
        // Trạng thái thường thấy là RUNNABLE.
        System.out.println("2. RUNNABLE:");
        System.out.println(runnableThread.getState()); // RUNNABLE


        // BƯỚC 5:
        // Đổi keepRunning = false để vòng while dừng lại.
        // Khi vòng while dừng, thread chạy xong.
        keepRunning = false;

        // main chờ runnableThread chạy xong hoàn toàn.
        runnableThread.join();

        // BƯỚC 6:
        // Thread đã chạy xong -> TERMINATED.
        System.out.println("3. TERMINATED:");
        System.out.println(runnableThread.getState()); // TERMINATED

        printLine();


        // =====================================================
        // 2. BLOCKED
        // =====================================================

        // lock là object dùng để khóa synchronized.
        Object lock = new Object();

        // BƯỚC 1:
        // holderThread sẽ vào synchronized(lock) trước.
        // Khi vào synchronized, nó giữ lock trong 1 giây.
        Thread holderThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Holder-Thread is holding lock...");
                sleep(3000);
            }
        }, "Holder-Thread");

        // BƯỚC 2:
        // blockedThread cũng muốn vào synchronized(lock).
        // Nhưng nếu lock đang bị holderThread giữ,
        // blockedThread phải chờ -> trạng thái BLOCKED.
        Thread blockedThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Blocked-Thread got lock");
            }
        }, "Blocked-Thread");


        // BƯỚC 3:
        // Cho holderThread chạy trước để nó lấy lock.
        holderThread.start();

        // BƯỚC 4:
        // Bây giờ start blockedThread.
        // Nó sẽ cố vào synchronized(lock),
        // nhưng lock đang bị holderThread giữ.
        blockedThread.start();
        // Chờ một chút để blockedThread kịp bị chặn.
        Thread.sleep(100);

        // BƯỚC 5:
        // blockedThread đang chờ lấy lock -> BLOCKED.
        System.out.println("4. BLOCKED:");
        System.out.println(blockedThread.getState()); // BLOCKED


        // BƯỚC 6:
        // Chờ 2 thread kết thúc để qua demo tiếp theo.
        holderThread.join();
        blockedThread.join();

        printLine();


        // =====================================================
        // 3. WAITING
        // =====================================================

        Object waitLock = new Object();

        // BƯỚC 1:
        // waitingThread vào synchronized(waitLock).
        Thread waitingThread = getThread(waitLock);

        // Chờ một chút để waitingThread kịp gọi wait().
        Thread.sleep(100);

        // BƯỚC 4:
        // Vì thread đang wait() nên trạng thái là WAITING.
        System.out.println("5. WAITING:");
        System.out.println(waitingThread.getState()); // WAITING


        // BƯỚC 5:
        // main lấy lại waitLock rồi gọi notify()
        // để đánh thức waitingThread.
        synchronized (waitLock) {
            waitLock.notify();
        }

        // Chờ waitingThread kết thúc.
        waitingThread.join();

        printLine();


        // =====================================================
        // 4. TIMED_WAITING
        // =====================================================

        // BƯỚC 1:
        // Thread này sẽ sleep 1 giây.
        Thread timedWaitingThread = new Thread(() -> {
            System.out.println("TimedWaiting-Thread is sleeping...");

            // sleep(1000) làm thread chờ có giới hạn thời gian.
            // Trong lúc sleep, trạng thái là TIMED_WAITING.
            sleep(1000);

            System.out.println("TimedWaiting-Thread woke up");
        }, "TimedWaiting-Thread");


        // BƯỚC 2:
        // Start thread.
        timedWaitingThread.start();

        // Chờ một chút để thread kịp vào sleep().
        Thread.sleep(100);

        // BƯỚC 3:
        // Vì thread đang sleep nên trạng thái là TIMED_WAITING.
        System.out.println("6. TIMED_WAITING:");
        System.out.println(timedWaitingThread.getState()); // TIMED_WAITING


        // BƯỚC 4:
        // Chờ thread sleep xong và kết thúc.
        timedWaitingThread.join();

        printLine();

        System.out.println("Main finished");
    }

    private static Thread getThread(Object waitLock) {
        Thread waitingThread = new Thread(() -> {
            synchronized (waitLock) {
                try {
                    System.out.println("Waiting-Thread is waiting...");

                    // BƯỚC 2:
                    // wait() làm thread nhả lock và chờ vô thời hạn.
                    // Thread sẽ ở trạng thái WAITING
                    // cho đến khi có thread khác gọi notify().
                    waitLock.wait();

                    System.out.println("Waiting-Thread woke up");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Waiting-Thread");


        // BƯỚC 3:
        // Start waitingThread.
        waitingThread.start();
        return waitingThread;
    }

    // Method sleep phụ để code gọn hơn
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void printLine() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
    }
}
