```text
src/main/java/com/nvminh162/collectiondemo/
├── b01_why_collection/
│   └── Demo.java
├── b02_list/
│   └── Demo.java
├── b03_set/
│   └── Demo.java
├── b04_map/
│   └── Demo.java
├── b05_queue/
│   └── Demo.java
└── b06_choose_collection/
    └── Demo.java
```

---

# Bài 1 — Vì sao cần Collection thay cho Array?

## `b01_why_collection/Demo.java`

```java
package com.nvminh162.collectiondemo.b01_why_collection;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // 1. ARRAY: kích thước cố định
        // =========================

        String[] studentsArray = new String[3];

        studentsArray[0] = "Minh";
        studentsArray[1] = "An";
        studentsArray[2] = "Binh";

        // Array có size cố định là 3.
        // Nếu thêm phần tử thứ 4 sẽ bị lỗi:
        // studentsArray[3] = "Long"; // ArrayIndexOutOfBoundsException

        System.out.println("Array:");
        for (String student : studentsArray) {
            System.out.println(student);
        }

        // =========================
        // 2. ARRAYLIST: kích thước động
        // =========================

        ArrayList<String> studentsList = new ArrayList<>();

        studentsList.add("Minh");
        studentsList.add("An");
        studentsList.add("Binh");

        // ArrayList có thể thêm tiếp, không cần khai báo size ban đầu
        studentsList.add("Long");

        System.out.println("\nArrayList:");
        for (String student : studentsList) {
            System.out.println(student);
        }

        // ArrayList có sẵn nhiều method tiện lợi
        studentsList.remove("An");

        System.out.println("\nAfter remove An:");
        System.out.println(studentsList);
    }
}
```

## Cần hiểu

`Array` phù hợp khi số lượng phần tử cố định.
`ArrayList` phù hợp khi danh sách có thể tăng/giảm.

Câu phỏng vấn:

> Collections Framework sinh ra để xử lý nhóm object linh hoạt hơn Array. Array có kích thước cố định, còn Collection như ArrayList có kích thước động và có sẵn nhiều method như `add`, `remove`, `contains`.

---

# Bài 2 — List: có thứ tự, cho phép trùng

## `b02_list/Demo.java`

```java
package com.nvminh162.collectiondemo.b02_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // List là interface
        // ArrayList là class triển khai List
        List<String> names = new ArrayList<>();

        names.add("Minh");
        names.add("An");
        names.add("Binh");
        names.add("Minh"); // List cho phép duplicate

        System.out.println("List values:");
        System.out.println(names);

        // List giữ thứ tự thêm vào
        // Index bắt đầu từ 0
        System.out.println("First element: " + names.get(0));
        System.out.println("Second element: " + names.get(1));

        // Có thể sửa phần tử theo index
        names.set(1, "Long");

        System.out.println("After set index 1:");
        System.out.println(names);

        // Có thể xóa theo index hoặc theo value
        names.remove(2); // xóa phần tử ở index 2

        System.out.println("After remove index 2:");
        System.out.println(names);

        // =========================
        // ArrayList vs LinkedList
        // =========================

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("A");
        arrayList.add("B");

        linkedList.add("A");
        linkedList.add("B");

        // Về cách dùng cơ bản, ArrayList và LinkedList khá giống nhau
        System.out.println("\nArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);
    }
}
```

## Cần hiểu

`List` dùng khi:

```text
- Cần giữ thứ tự
- Cho phép phần tử trùng
- Muốn truy cập bằng index
```

Câu phỏng vấn:

> List là Collection có thứ tự, cho phép duplicate và có thể truy cập phần tử bằng index. ArrayList thường được dùng nhiều vì truy cập nhanh và dễ dùng. LinkedList cũng implement List nhưng phù hợp hơn khi thao tác thêm/xóa ở đầu hoặc cuối danh sách.

---

# Bài 3 — Set: không cho trùng

## `b03_set/Demo.java`

```java
package com.nvminh162.collectiondemo.b03_set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // 1. HashSet
        // =========================

        Set<String> hashSet = new HashSet<>();

        hashSet.add("Minh");
        hashSet.add("An");
        hashSet.add("Binh");
        hashSet.add("Minh"); // bị bỏ qua vì Set không cho duplicate

        System.out.println("HashSet:");
        System.out.println(hashSet);

        // HashSet không đảm bảo thứ tự in ra giống thứ tự add


        // =========================
        // 2. LinkedHashSet
        // =========================

        Set<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("Minh");
        linkedHashSet.add("An");
        linkedHashSet.add("Binh");
        linkedHashSet.add("Minh"); // vẫn bị bỏ qua

        System.out.println("\nLinkedHashSet:");
        System.out.println(linkedHashSet);

        // LinkedHashSet giữ thứ tự thêm vào


        // =========================
        // 3. TreeSet
        // =========================

        Set<String> treeSet = new TreeSet<>();

        treeSet.add("Minh");
        treeSet.add("An");
        treeSet.add("Binh");
        treeSet.add("Minh"); // vẫn bị bỏ qua

        System.out.println("\nTreeSet:");
        System.out.println(treeSet);

        // TreeSet tự động sắp xếp tăng dần
    }
}
```

## Cần hiểu

`Set` dùng khi bạn cần dữ liệu **không trùng**.

Khác nhau nhanh:

```text
HashSet       -> nhanh, không đảm bảo thứ tự
LinkedHashSet -> không trùng, giữ thứ tự thêm vào
TreeSet       -> không trùng, tự động sort
```

Câu phỏng vấn:

> Set là Collection không cho phép phần tử trùng. HashSet thường nhanh nhất nhưng không đảm bảo thứ tự. LinkedHashSet giữ thứ tự thêm vào, còn TreeSet tự động sắp xếp phần tử.

---

# Bài 4 — Map: key-value, key không trùng

## `b04_map/Demo.java`

```java
package com.nvminh162.collectiondemo.b04_map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {

        // Map lưu dữ liệu theo dạng key-value
        // Ví dụ: studentId -> studentName

        Map<Integer, String> students = new HashMap<>();

        students.put(1, "Minh");
        students.put(2, "An");
        students.put(3, "Binh");

        System.out.println("HashMap:");
        System.out.println(students);

        // Lấy value bằng key
        System.out.println("Student id 1: " + students.get(1));

        // Key không được trùng
        // Nếu put lại cùng key, value cũ sẽ bị ghi đè
        students.put(1, "Minh Updated");

        System.out.println("After update key 1:");
        System.out.println(students);

        // Value có thể trùng
        students.put(4, "An");

        System.out.println("After add duplicate value:");
        System.out.println(students);

        // Kiểm tra key có tồn tại không
        System.out.println("Contains key 2: " + students.containsKey(2));

        // Duyệt Map
        System.out.println("\nLoop Map:");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // =========================
        // LinkedHashMap
        // =========================

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(3, "Binh");
        linkedHashMap.put(1, "Minh");
        linkedHashMap.put(2, "An");

        System.out.println("\nLinkedHashMap:");
        System.out.println(linkedHashMap);

        // LinkedHashMap giữ thứ tự thêm vào


        // =========================
        // TreeMap
        // =========================

        Map<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "Binh");
        treeMap.put(1, "Minh");
        treeMap.put(2, "An");

        System.out.println("\nTreeMap:");
        System.out.println(treeMap);

        // TreeMap tự động sort theo key
    }
}
```

## Cần hiểu

`Map` dùng khi bạn muốn tìm dữ liệu theo key.

Ví dụ thực tế:

```text
userId -> user
username -> password
productId -> product
word -> meaning
```

Câu phỏng vấn:

> Map lưu dữ liệu dạng key-value. Key là duy nhất, nếu put trùng key thì value cũ sẽ bị ghi đè. HashMap thường dùng nhiều nhất vì lookup nhanh. LinkedHashMap giữ thứ tự thêm vào, còn TreeMap tự động sort theo key.

---

# Bài 5 — Queue: xử lý theo hàng đợi

## `b05_queue/Demo.java`

```java
package com.nvminh162.collectiondemo.b05_queue;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // 1. Queue bình thường: FIFO
        // =========================
        // FIFO = First In First Out
        // Ai vào trước thì xử lý trước

        Queue<String> taskQueue = new ArrayDeque<>();

        taskQueue.offer("Send email");
        taskQueue.offer("Generate report");
        taskQueue.offer("Backup database");

        System.out.println("Queue:");
        System.out.println(taskQueue);

        // peek(): xem phần tử đầu tiên nhưng không xóa
        System.out.println("Next task: " + taskQueue.peek());

        // poll(): lấy phần tử đầu tiên ra và xóa khỏi queue
        System.out.println("Processing: " + taskQueue.poll());

        System.out.println("After poll:");
        System.out.println(taskQueue);

        // =========================
        // 2. PriorityQueue
        // =========================
        // Không xử lý theo thứ tự add vào
        // Mà xử lý theo độ ưu tiên / thứ tự sort tự nhiên

        Queue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);

        System.out.println("\nPriorityQueue:");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

        // Kết quả: 10, 20, 30
        // Vì PriorityQueue tự ưu tiên số nhỏ hơn trước
    }
}
```

## Cần hiểu

`Queue` dùng khi cần xử lý tuần tự:

```text
task queue
message queue
print queue
request queue
```

Câu phỏng vấn:

> Queue dùng để xử lý dữ liệu theo hàng đợi, thường là FIFO: phần tử vào trước được xử lý trước. ArrayDeque thường dùng cho queue thông thường, còn PriorityQueue dùng khi phần tử cần được xử lý theo độ ưu tiên.

---

# Bài 6 — Nên chọn Collection nào?

## `b06_choose_collection/Demo.java`

```java
package com.nvminh162.collectiondemo.b06_choose_collection;

import java.util.*;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // Case 1: Danh sách sản phẩm, cho phép trùng, cần giữ thứ tự
        // Dùng List
        // =========================

        List<String> cartItems = new ArrayList<>();

        cartItems.add("Laptop");
        cartItems.add("Mouse");
        cartItems.add("Mouse"); // mua 2 con chuột thì duplicate là hợp lý

        System.out.println("Cart items:");
        System.out.println(cartItems);


        // =========================
        // Case 2: Danh sách email đã đăng ký, không cho trùng
        // Dùng Set
        // =========================

        Set<String> registeredEmails = new HashSet<>();

        registeredEmails.add("minh@gmail.com");
        registeredEmails.add("an@gmail.com");
        registeredEmails.add("minh@gmail.com"); // bị bỏ qua

        System.out.println("\nRegistered emails:");
        System.out.println(registeredEmails);


        // =========================
        // Case 3: Tìm user theo id
        // Dùng Map
        // =========================

        Map<Integer, String> userMap = new HashMap<>();

        userMap.put(1, "Minh");
        userMap.put(2, "An");
        userMap.put(3, "Binh");

        int userId = 2;

        System.out.println("\nFind user by id:");
        System.out.println(userMap.get(userId));


        // =========================
        // Case 4: Xử lý task lần lượt
        // Dùng Queue
        // =========================

        Queue<String> tasks = new ArrayDeque<>();

        tasks.offer("Send email");
        tasks.offer("Create invoice");
        tasks.offer("Notify customer");

        System.out.println("\nProcess tasks:");

        while (!tasks.isEmpty()) {
            String task = tasks.poll();
            System.out.println("Processing: " + task);
        }
    }
}
```

## Cần hiểu nhanh

```text
Cần giữ thứ tự + cho trùng      -> List
Cần không trùng                 -> Set
Cần key-value, tìm theo key     -> Map
Cần xử lý hàng đợi              -> Queue
```

---

# Câu trả lời phỏng vấn ngắn gọn

Bạn có thể nói như này:

> Collections Framework trong Java là tập hợp các interface và class trong `java.util` dùng để lưu trữ và thao tác với nhiều object. Các interface chính gồm `List`, `Set`, `Queue` và `Map`. `List` có thứ tự và cho phép duplicate, `Set` không cho duplicate, `Map` lưu dữ liệu dạng key-value, còn `Queue` dùng để xử lý dữ liệu theo hàng đợi. Trong thực tế, em hay dùng `ArrayList` khi cần danh sách, `HashSet` khi cần dữ liệu không trùng, `HashMap` khi cần tìm nhanh theo key, và `ArrayDeque` hoặc `PriorityQueue` khi cần xử lý queue.
