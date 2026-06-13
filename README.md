## 1. OOP trong Java

Phần này gần như chắc chắn bị hỏi.

Cần ôn:

* Class, object
* Constructor
* `this`, `super`
* Encapsulation, Inheritance, Polymorphism, Abstraction
* Overloading vs Overriding
* Abstract class vs Interface
* Access modifiers: `private`, `default`, `protected`, `public`
* `final`, `static`

Ví dụ câu hỏi hay gặp:

> OOP có những tính chất nào?
> Abstract class khác interface như thế nào?
> Overloading và overriding khác nhau gì?
> Static là gì?

---

## 2. Java cơ bản

Cần ôn:

* Kiểu dữ liệu nguyên thủy: `int`, `double`, `boolean`, `char`
* Wrapper class: `Integer`, `Double`, `Boolean`
* Autoboxing / Unboxing
* `String`, `StringBuilder`, `StringBuffer`
* So sánh `==` và `.equals()`
* `final`, `finally`, `finalize`
* Pass by value trong Java

Câu hỏi hay gặp:

> `String` có mutable không?
> `==` khác `.equals()` như thế nào?
> Java truyền tham trị hay tham chiếu?

---

## 3. Collection Framework

Phần này rất quan trọng.

Cần ôn:

* `List`, `Set`, `Map`
* `ArrayList` vs `LinkedList`
* `HashSet` vs `TreeSet`
* `HashMap` vs `Hashtable` vs `ConcurrentHashMap`
* `HashMap` hoạt động như thế nào?
* `equals()` và `hashCode()`
* Iterator
* Comparable vs Comparator

Câu hỏi hay gặp:

> ArrayList khác LinkedList như thế nào?
> HashMap lưu dữ liệu ra sao?
> Vì sao override equals thì nên override hashCode?

---

## 4. Exception Handling

Cần ôn:

* `try-catch-finally`
* `throw` vs `throws`
* Checked exception vs unchecked exception
* Custom exception
* Try-with-resources

Câu hỏi hay gặp:

> Checked exception và unchecked exception khác nhau gì?
> `finally` có luôn chạy không?
> `throw` và `throws` khác nhau gì?

---

## 5. Stream API

Nên ôn vì Java hiện đại dùng nhiều.

Cần ôn:

* `stream()`
* `filter()`
* `map()`
* `forEach()`
* `collect()`
* `reduce()`
* `sorted()`
* `distinct()`
* `limit()`
* `findFirst()`
* `anyMatch()`, `allMatch()`
* Stream vs Collection

Ví dụ cần tự code được:

```java
List<String> names = List.of("Minh", "An", "Nam");

List<String> result = names.stream()
        .filter(name -> name.startsWith("M"))
        .toList();
```

Câu hỏi hay gặp:

> Stream API dùng để làm gì?
> `map()` khác `filter()` như thế nào?
> Stream có làm thay đổi collection gốc không?

---

## 6. Lambda Expression & Functional Interface

Phần này liên quan trực tiếp đến Stream API.

Cần ôn:

* Lambda là gì?
* Functional interface là gì?
* `Predicate`
* `Function`
* `Consumer`
* `Supplier`
* Method reference `ClassName::methodName`

Ví dụ:

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(10));
```

Câu hỏi hay gặp:

> Lambda expression là gì?
> Functional interface là gì?
> Predicate, Function, Consumer khác nhau thế nào?

---

## 7. Thread / Multithreading

Java Intern không cần quá sâu, nhưng nên nắm nền tảng.

Cần ôn:

* Thread là gì?
* Process vs Thread
* Tạo thread bằng `Thread` và `Runnable`
* `start()` vs `run()`
* `sleep()`
* `join()`
* `synchronized`
* Race condition
* Deadlock là gì?
* `ExecutorService`
* `Callable` vs `Runnable`
* `Future`

Câu hỏi hay gặp:

> Thread là gì?
> `start()` khác `run()` như thế nào?
> Race condition là gì?
> synchronized dùng để làm gì?

---

## 8. Java Memory

Phần này giúp trả lời sâu hơn.

Cần ôn:

* Stack vs Heap
* Garbage Collection
* Object được tạo ở đâu?
* Local variable nằm ở đâu?
* Static variable nằm ở đâu?
* Memory leak trong Java
* `OutOfMemoryError`

Câu hỏi hay gặp:

> Stack và Heap khác nhau gì?
> Java có cần tự giải phóng bộ nhớ không?
> Garbage Collector hoạt động để làm gì?

---

## 9. Generics

Cần ôn vừa đủ.

* Generics là gì?
* Vì sao dùng Generics?
* `List<String>` khác `List<Object>` như thế nào?
* Wildcard: `?`, `? extends`, `? super`

Ví dụ:

```java
List<String> names = new ArrayList<>();
names.add("Minh");
```

Câu hỏi hay gặp:

> Generics dùng để làm gì?
> `List<?>` nghĩa là gì?

---

## 10. Java 8+ features

Nên biết vì hay dùng trong dự án.

Cần ôn:

* Lambda
* Stream API
* Optional
* Default method trong interface
* Method reference
* Date Time API: `LocalDate`, `LocalDateTime`

Câu hỏi hay gặp:

> Optional dùng để làm gì?
> Default method trong interface là gì?
> LocalDate khác Date như thế nào?

---

## 11. JDBC cơ bản

Nếu vị trí Java Intern có backend thì nên ôn.

Cần ôn:

* JDBC là gì?
* Các bước kết nối database
* `Connection`
* `PreparedStatement`
* `ResultSet`
* SQL Injection
* Vì sao dùng `PreparedStatement` thay vì nối chuỗi SQL?

Câu hỏi hay gặp:

> JDBC dùng để làm gì?
> PreparedStatement khác Statement như thế nào?

---

## 12. Maven / Build tool

Biết cơ bản là được.

Cần ôn:

* Maven là gì?
* `pom.xml`
* Dependency
* Repository
* Lifecycle: `clean`, `compile`, `test`, `package`, `install`
* Maven vs Gradle biết sơ qua

---