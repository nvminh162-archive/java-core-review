# Java Thread — Bản thực hành để hiểu và trả lời phỏng vấn

## Cấu trúc package

```text
src/main/java/com/nvminh162/threaddemo
├── b01_thread_basic
│   └── Demo.java
├── b02_extends_thread
│   ├── MyThread.java
│   └── Demo.java
├── b03_runnable
│   ├── DownloadTask.java
│   └── Demo.java
├── b04_start_vs_run
│   └── Demo.java
├── b05_sleep_join
│   └── Demo.java
├── b06_race_condition_synchronized
│   ├── Counter.java
│   ├── SafeCounter.java
│   └── Demo.java
└── b07_interview_summary
    └── Demo.java
```

---

# Bài 1 — Thread là gì?

## `b01_thread_basic/Demo.java`

```java
package com.nvminh162.threaddemo.b01_thread_basic;

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
```

## Cần hiểu

```text
Process = chương trình đang chạy
Thread  = luồng thực thi nhỏ bên trong process
```

Một process có thể có nhiều thread.

Ví dụ dễ hiểu:

```text
Chrome là một process

Bên trong Chrome có thể có nhiều thread:
-> thread xử lý giao diện
-> thread tải file
-> thread chạy JavaScript
-> thread xử lý network
```

Trong Java, khi chạy `main()` thì đã có sẵn một thread tên là:

```text
main
```

Khi bạn tạo thêm `new Thread(...)`, tức là tạo thêm luồng chạy khác.

## Câu phỏng vấn

Thread là đơn vị thực thi nhỏ nhất trong một process. Một process có thể có nhiều thread chạy đồng thời. Các thread trong cùng process dùng chung memory nên nhẹ hơn process, tạo nhanh hơn process, nhưng cũng dễ phát sinh lỗi nếu nhiều thread cùng sửa một dữ liệu chung.

---

# Bài 2 — Tạo Thread bằng cách extends Thread

## `b02_extends_thread/MyThread.java`

```java
package com.nvminh162.threaddemo.b02_extends_thread;

// Cách 1: tạo class kế thừa Thread
public class MyThread extends Thread {

    @Override
    public void run() {
        // Code trong run() là công việc thread sẽ làm
        System.out.println("MyThread running in: " + Thread.currentThread().getName());
    }
}
```

## `b02_extends_thread/Demo.java`

```java
package com.nvminh162.threaddemo.b02_extends_thread;

public class Demo {
    public static void main(String[] args) {

        MyThread thread = new MyThread();

        // Đặt tên thread cho dễ nhìn output
        thread.setName("Worker-1");

        // start() mới tạo thread mới
        thread.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
```

## Cần hiểu

```java
class MyThread extends Thread
```

nghĩa là class `MyThread` chính nó là một thread.

Method:

```java
public void run()
```

là nơi viết công việc cần chạy trong thread.

Nhưng để chạy thread, phải gọi:

```java
thread.start();
```

Không gọi trực tiếp `run()`.

## Nhược điểm

Cách `extends Thread` ít linh hoạt hơn vì Java chỉ cho một class `extends` một class cha.

Ví dụ nếu class đã extends class khác rồi thì không thể extends `Thread` nữa.

## Câu phỏng vấn

Có thể tạo thread bằng cách extends class `Thread` và override method `run()`. Sau đó gọi `start()` để JVM tạo thread mới và thực thi code trong `run()`. Tuy nhiên cách này ít linh hoạt vì Java không hỗ trợ đa kế thừa class, nên thực tế thường ưu tiên `Runnable`.

---

# Bài 3 — Tạo Thread bằng Runnable

## `b03_runnable/DownloadTask.java`

```java
package com.nvminh162.threaddemo.b03_runnable;

// Cách 2: implements Runnable
// Runnable đại diện cho một task/công việc cần chạy
public class DownloadTask implements Runnable {

    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("Downloading " + fileName
                + " in thread: " + Thread.currentThread().getName());
    }
}
```

## `b03_runnable/Demo.java`

```java
package com.nvminh162.threaddemo.b03_runnable;

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

        Thread lambdaThread = new Thread(() -> {
            System.out.println("Lambda task running in: "
                    + Thread.currentThread().getName());
        });

        lambdaThread.setName("Lambda-Thread");
        lambdaThread.start();
    }
}
```

## Cần hiểu

```text
Thread   -> luồng chạy
Runnable -> công việc cần chạy
```

Nói dễ hiểu:

```text
Runnable là nhiệm vụ
Thread là người thực hiện nhiệm vụ
```

Cách này tốt hơn `extends Thread` vì tách riêng:

```text
Task logic        -> Runnable
Thread management -> Thread
```

## Câu phỏng vấn

Cách recommended để tạo thread là implement `Runnable`, vì nó tách logic công việc ra khỏi Thread và linh hoạt hơn. Class vẫn có thể extends class khác nếu cần. Ngoài ra `Runnable` là functional interface nên từ Java 8 có thể viết ngắn bằng lambda.

---

# Bài 4 — start() vs run()

## `b04_start_vs_run/Demo.java`

```java
package com.nvminh162.threaddemo.b04_start_vs_run;

public class Demo {
    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println("Running in thread: "
                    + Thread.currentThread().getName());
        };

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
```

## Luồng chạy

Khi gọi:

```java
thread.run();
```

output thường là:

```text
Running in thread: main
```

Vì `run()` chạy như method bình thường trong main thread.

Khi gọi:

```java
thread.start();
```

output thường là:

```text
Running in thread: Thread-0
```

Vì `start()` tạo thread mới.

## Cần nhớ

```text
run()
-> Không tạo thread mới
-> Chạy trong current thread
-> Giống gọi method bình thường

start()
-> Tạo thread mới
-> JVM tự gọi run()
-> Chỉ được gọi một lần trên một Thread object
```

## Câu phỏng vấn

`start()` dùng để tạo thread mới và JVM sẽ gọi `run()` trong thread mới đó. Còn nếu gọi trực tiếp `run()` thì nó chỉ chạy như một method bình thường trong thread hiện tại, không tạo thread mới.

---

# Bài 5 — sleep() và join()

## `b05_sleep_join/Demo.java`

```java
package com.nvminh162.threaddemo.b05_sleep_join;

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
```

## Cần hiểu

```java
Thread.sleep(500);
```

nghĩa là thread hiện tại ngủ 500 milliseconds.

```java
worker.join();
```

nghĩa là thread hiện tại, ở đây là `main`, sẽ chờ `worker` chạy xong rồi mới chạy tiếp.

## Nếu không có join()

Có thể xảy ra:

```text
Worker running: 1
Main finished
Worker running: 2
Worker running: 3
...
```

## Nếu có join()

Luồng sẽ là:

```text
Worker chạy xong hết
Sau đó main mới in finished
```

## Câu phỏng vấn

`sleep()` làm thread hiện tại tạm dừng trong một khoảng thời gian. `join()` làm thread hiện tại chờ thread khác kết thúc rồi mới tiếp tục chạy. Ví dụ main thread gọi `worker.join()` thì main sẽ chờ worker chạy xong.

---

# Bài 6 — Race Condition và synchronized

## `b06_race_condition_synchronized/Counter.java`

```java
package com.nvminh162.threaddemo.b06_race_condition_synchronized;

// Counter chưa an toàn trong môi trường nhiều thread
public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

## `b06_race_condition_synchronized/SafeCounter.java`

```java
package com.nvminh162.threaddemo.b06_race_condition_synchronized;

// Counter an toàn hơn vì dùng synchronized
public class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

## `b06_race_condition_synchronized/Demo.java`

```java
package com.nvminh162.threaddemo.b06_race_condition_synchronized;

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
            for (int i = 0; i < 10000; i++) {
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
            for (int i = 0; i < 10000; i++) {
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
```

## Cần hiểu

Dòng này không đơn giản như mình nhìn:

```java
count++;
```

Thực chất nó gồm nhiều bước:

```text
đọc count hiện tại
tăng lên 1
ghi lại vào count
```

Nếu 2 thread cùng làm 3 bước này một lúc, có thể bị mất dữ liệu.

Đó gọi là:

```text
Race condition
```

Dùng:

```java
synchronized
```

để đảm bảo tại một thời điểm chỉ một thread được chạy method đó.

## Câu phỏng vấn

Race condition xảy ra khi nhiều thread cùng truy cập và thay đổi shared data, làm kết quả không đúng. Ví dụ hai thread cùng tăng biến `count`, kết quả cuối có thể nhỏ hơn kỳ vọng. Có thể dùng `synchronized` để đảm bảo tại một thời điểm chỉ một thread được vào vùng code quan trọng.

---

# Bài 7 — Tổng hợp phỏng vấn

## `b07_interview_summary/Demo.java`

```java
package com.nvminh162.threaddemo.b07_interview_summary;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("Task running in: "
                    + Thread.currentThread().getName());
        });

        thread.setName("Worker-Thread");

        System.out.println("Before start: " + thread.getState());

        thread.start();

        System.out.println("After start: " + thread.getState());

        thread.join();

        System.out.println("After join: " + thread.getState());

        System.out.println("Main finished");
    }
}
```

## Một số trạng thái cần nhớ

```text
NEW
-> Thread vừa tạo, chưa start

RUNNABLE
-> Thread đã start, sẵn sàng chạy hoặc đang chạy

BLOCKED / WAITING / TIMED_WAITING
-> Thread đang chờ lock, chờ thread khác, hoặc sleep

TERMINATED
-> Thread đã chạy xong
```

## Tổng kết cần nhớ

```text
Thread
-> Luồng thực thi nhỏ nhất trong process
-> Các thread trong cùng process share memory

Tạo Thread
-> extends Thread
-> implements Runnable
-> lambda với Runnable

start() vs run()
-> start() tạo thread mới
-> run() chỉ là gọi method bình thường

sleep()
-> cho thread hiện tại ngủ một khoảng thời gian

join()
-> chờ thread khác chạy xong

Race condition
-> nhiều thread cùng sửa shared data gây kết quả sai

synchronized
-> đảm bảo một thời điểm chỉ một thread vào vùng code quan trọng
```

## Câu phỏng vấn tổng quát

Thread là đơn vị thực thi nhỏ nhất trong một process. Một process có thể có nhiều thread chạy đồng thời và các thread trong cùng process dùng chung memory. Trong Java có thể tạo thread bằng cách extends `Thread` hoặc implements `Runnable`, trong đó `Runnable` thường được khuyên dùng hơn vì linh hoạt và tách biệt task với thread. Khi chạy thread phải gọi `start()` để tạo thread mới, còn gọi trực tiếp `run()` thì chỉ chạy như method bình thường trong thread hiện tại. Một số method quan trọng là `sleep()` để tạm dừng thread, `join()` để chờ thread khác kết thúc. Khi nhiều thread cùng sửa dữ liệu chung có thể gây race condition, lúc đó có thể dùng `synchronized` để đảm bảo thread-safe.

```text
NEW: tạo new Thread(...) nhưng chưa gọi start().

RUNNABLE: đã gọi start(), thread đang sẵn sàng chạy hoặc đang chạy.

TERMINATED: thread chạy xong, join() xong thì thấy trạng thái này.

BLOCKED: thread muốn vào synchronized(lock) nhưng lock đang bị thread khác giữ.

WAITING: thread gọi wait() và chờ vô thời hạn, phải có notify() đánh thức.

TIMED_WAITING: thread gọi sleep(1000), tức là chờ có thời gian giới hạn.
```