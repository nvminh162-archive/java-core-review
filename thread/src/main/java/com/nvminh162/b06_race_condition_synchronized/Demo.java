package com.nvminh162.b06_race_condition_synchronized;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        // Demo race condition
        demoUnsafeCounter();
        System.out.println("------------------");
        // Demo synchronized
        demoSafeCounter();
    }

    private static void demoUnsafeCounter() throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Unsafe counter result: " + counter.getCount());

        // Kỳ vọng: 20000
        // Nhưng thực tế có thể nhỏ hơn 20000
        // Vì 2 thread cùng sửa count một lúc
    }

    private static void demoSafeCounter() throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Safe counter result: " + counter.getCount());

        // Kỳ vọng: 20000
        // Vì increment() đã synchronized
        // Mỗi lần chỉ một thread được vào method increment()
    }
}
