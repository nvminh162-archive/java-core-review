Được. Mình sẽ chia bài **Functional Interface & Lambda** thành các demo nhỏ, có comment để bạn chạy từng bài và hiểu bản chất. Nội dung bám theo 3 ý chính trong bài bạn gửi: **Functional Interface**, **Lambda Expression**, và **Method Reference**.

---

# Cấu trúc package đề xuất

```text
src/main/java/com/nvminh162/lambdademo
├── b01_functional_interface
│   └── Demo.java
├── b02_anonymous_vs_lambda
│   └── Demo.java
├── b03_lambda_syntax
│   └── Demo.java
├── b04_builtin_functional_interface
│   └── Demo.java
├── b05_lambda_with_collection
│   └── Demo.java
├── b06_method_reference
│   └── Demo.java
└── b07_interview_summary
    └── Demo.java
```

---

# Bài 1 — Functional Interface là gì?

## `b01_functional_interface/Demo.java`

```java
package com.nvminh162.lambdademo.b01_functional_interface;

// Functional Interface là interface chỉ có 1 abstract method
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    // Được phép có default method
    default void printInfo() {
        System.out.println("This is a calculator");
    }

    // Được phép có static method
    static void description() {
        System.out.println("Calculator is used to calculate numbers");
    }

    // Nếu thêm abstract method thứ 2 thì sẽ lỗi
    // int subtract(int a, int b);
}

public class Demo {
    public static void main(String[] args) {

        // Vì Calculator chỉ có 1 abstract method
        // nên Java cho phép dùng lambda để implement method đó

        Calculator sum = (a, b) -> a + b;

        int result = sum.calculate(10, 5);

        System.out.println(result); // 15

        sum.printInfo();

        Calculator.description();
    }
}
```

## Cần hiểu

```text
Functional Interface = interface chỉ có 1 abstract method
```

Nhưng nó vẫn có thể có:

```text
default method
static method
```

Vì `Calculator` chỉ có một method cần implement:

```java
int calculate(int a, int b);
```

nên lambda này:

```java
(a, b) -> a + b
```

chính là phần code implement cho method `calculate()`.

## Câu phỏng vấn

> Functional Interface là interface chỉ có một abstract method. Nó có thể có thêm default method và static method. Functional Interface là nền tảng để Java hỗ trợ lambda expression. Ví dụ interface `Calculator` có method `calculate()`, mình có thể implement bằng lambda như `(a, b) -> a + b`.

---

# Bài 2 — Anonymous class vs Lambda

## `b02_anonymous_vs_lambda/Demo.java`

```java
package com.nvminh162.lambdademo.b02_anonymous_vs_lambda;

@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Cách cũ trước Java 8: Anonymous class
        // Code dài vì phải tạo class ẩn danh và override method
        // =====================================================

        Greeting oldGreeting = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello " + name);
            }
        };

        oldGreeting.sayHello("Minh");


        // =====================================================
        // Cách mới Java 8+: Lambda
        // Vì Greeting chỉ có 1 abstract method
        // nên có thể viết ngắn gọn bằng lambda
        // =====================================================

        Greeting newGreeting = name -> System.out.println("Hello " + name);

        newGreeting.sayHello("An");
    }
}
```

## Cần hiểu

Anonymous class:

```java
new Greeting() {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
```

Lambda:

```java
name -> System.out.println("Hello " + name)
```

Hai đoạn này cùng mục đích: implement method `sayHello`.

Lambda ngắn hơn vì Java biết `Greeting` chỉ có một method cần implement.

## Câu phỏng vấn

> Trước Java 8, nếu muốn implement interface nhanh thì thường dùng anonymous class, nhưng code khá dài. Từ Java 8, nếu interface là functional interface thì có thể dùng lambda expression để viết ngắn gọn hơn. Lambda giúp code dễ đọc hơn, đặc biệt khi làm việc với Stream API hoặc Collection.

---

# Bài 3 — Cú pháp Lambda

## `b03_lambda_syntax/Demo.java`

```java
package com.nvminh162.lambdademo.b03_lambda_syntax;

@FunctionalInterface
interface NoParam {
    void run();
}

@FunctionalInterface
interface OneParam {
    void print(String message);
}

@FunctionalInterface
interface TwoParams {
    int calculate(int a, int b);
}

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // 1. Lambda không có tham số
        // =====================================================

        NoParam task = () -> System.out.println("Running task");

        task.run();


        // =====================================================
        // 2. Lambda có 1 tham số
        // Nếu chỉ có 1 tham số thì có thể bỏ dấu ()
        // =====================================================

        OneParam printer = message -> System.out.println(message);

        printer.print("Hello Lambda");


        // =====================================================
        // 3. Lambda có 2 tham số
        // Phải dùng dấu ()
        // =====================================================

        TwoParams sum = (a, b) -> a + b;

        System.out.println(sum.calculate(10, 20)); // 30


        // =====================================================
        // 4. Lambda có nhiều dòng xử lý
        // Nếu dùng {} thì phải return rõ ràng nếu method có giá trị trả về
        // =====================================================

        TwoParams multiply = (a, b) -> {
            int result = a * b;
            return result;
        };

        System.out.println(multiply.calculate(5, 4)); // 20
    }
}
```

## Cần hiểu nhanh

```text
() -> code                 // không tham số
x -> code                  // một tham số
(a, b) -> a + b            // nhiều tham số
(a, b) -> { return a + b; } // nhiều dòng xử lý
```

## Câu phỏng vấn

> Lambda expression là cách viết ngắn gọn cho anonymous function, thường dùng để implement functional interface. Cú pháp cơ bản là `(parameters) -> expression` hoặc `(parameters) -> { statements }`. Nếu chỉ có một tham số thì có thể bỏ dấu ngoặc tròn, còn nếu có nhiều dòng xử lý thì dùng `{}` và `return` nếu cần trả về giá trị.

---

# Bài 4 — Các Functional Interface có sẵn trong Java

## `b04_builtin_functional_interface/Demo.java`

```java
package com.nvminh162.lambdademo.b04_builtin_functional_interface;

import java.util.function.*;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // 1. Predicate<T>
        // Nhận vào T, trả về boolean
        // Dùng để kiểm tra điều kiện
        // =====================================================

        Predicate<Integer> isAdultAge = age -> age >= 18;

        System.out.println(isAdultAge.test(20)); // true
        System.out.println(isAdultAge.test(16)); // false


        // =====================================================
        // 2. Function<T, R>
        // Nhận vào T, trả về R
        // Dùng để chuyển đổi dữ liệu
        // =====================================================

        Function<String, Integer> getLength = text -> text.length();

        System.out.println(getLength.apply("Java")); // 4


        // =====================================================
        // 3. Consumer<T>
        // Nhận vào T, không trả về gì
        // Dùng để xử lý/in/log dữ liệu
        // =====================================================

        Consumer<String> printer = text -> System.out.println(text);

        printer.accept("Hello Consumer");


        // =====================================================
        // 4. Supplier<T>
        // Không nhận vào gì, trả về T
        // Dùng để tạo/cung cấp dữ liệu
        // =====================================================

        Supplier<String> supplier = () -> "Hello Supplier";

        System.out.println(supplier.get());


        // =====================================================
        // 5. BiFunction<T, U, R>
        // Nhận vào 2 tham số, trả về 1 kết quả
        // =====================================================

        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

        System.out.println(sum.apply(10, 20)); // 30


        // =====================================================
        // 6. UnaryOperator<T>
        // Nhận vào T, trả về T
        // Tức input và output cùng kiểu
        // =====================================================

        UnaryOperator<String> upper = text -> text.toUpperCase();

        System.out.println(upper.apply("java")); // JAVA


        // =====================================================
        // 7. BinaryOperator<T>
        // Nhận vào 2 tham số cùng kiểu T, trả về T
        // =====================================================

        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;

        System.out.println(max.apply(10, 30)); // 30
    }
}
```

## Cần nhớ để phỏng vấn

```text
Predicate<T>     -> T -> boolean
Function<T, R>   -> T -> R
Consumer<T>      -> T -> void
Supplier<T>      -> () -> T
BiFunction<T,U,R>-> (T, U) -> R
UnaryOperator<T> -> T -> T
BinaryOperator<T>-> (T, T) -> T
```

## Câu phỏng vấn

> Java cung cấp sẵn nhiều functional interface trong package `java.util.function`. Ví dụ `Predicate` dùng để kiểm tra điều kiện, `Function` dùng để transform dữ liệu, `Consumer` dùng để xử lý dữ liệu nhưng không trả về kết quả, `Supplier` dùng để cung cấp dữ liệu, còn `BiFunction` nhận hai input và trả về một output.

---

# Bài 5 — Lambda dùng với Collection

## `b05_lambda_with_collection/Demo.java`

```java
package com.nvminh162.lambdademo.b05_lambda_with_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " - " + age;
    }
}

public class Demo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Minh", 22),
                new User("An", 17),
                new User("Binh", 25),
                new User("Long", 20)
        ));

        // =====================================================
        // 1. forEach + lambda
        // =====================================================

        users.forEach(user -> System.out.println(user));


        // =====================================================
        // 2. removeIf + Predicate lambda
        // Xóa user nhỏ hơn 18 tuổi
        // =====================================================

        users.removeIf(user -> user.getAge() < 18);

        System.out.println("\nAfter remove age < 18:");
        users.forEach(user -> System.out.println(user));


        // =====================================================
        // 3. sort + Comparator lambda
        // Sort theo tuổi tăng dần
        // =====================================================

        users.sort((u1, u2) -> u1.getAge() - u2.getAge());

        System.out.println("\nAfter sort by age:");
        users.forEach(user -> System.out.println(user));


        // =====================================================
        // 4. Stream + lambda
        // Lọc user >= 20 tuổi, lấy name
        // =====================================================

        List<String> names = new ArrayList<>();

        users.stream()
                .filter(user -> user.getAge() >= 20)
                .map(user -> user.getName())
                .forEach(name -> names.add(name));

        System.out.println("\nNames age >= 20:");
        System.out.println(names);
    }
}
```

## Cần hiểu

Lambda thường xuất hiện trong:

```text
forEach()
removeIf()
sort()
stream().filter()
stream().map()
```

Ví dụ:

```java
user -> user.getAge() >= 20
```

là function nhận vào `user`, trả về `true/false`.

## Câu phỏng vấn

> Lambda thường được dùng nhiều với Collection và Stream API. Ví dụ dùng `forEach` để duyệt danh sách, `removeIf` để xóa theo điều kiện, `sort` để truyền comparator, hoặc `filter` và `map` trong Stream để xử lý dữ liệu ngắn gọn hơn.

---

# Bài 6 — Method Reference

## `b06_method_reference/Demo.java`

```java
package com.nvminh162.lambdademo.b06_method_reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

class User {
    private String name;

    public User() {
        this.name = "Default User";
    }

    public User(String name) {
        this.name = name;
    }

    public static boolean isLongName(String name) {
        return name.length() >= 4;
    }

    public String getName() {
        return name;
    }

    public void printName() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Minh", "An", "Binh", "Long");

        // =====================================================
        // Method Reference là cách viết ngắn hơn lambda
        // khi lambda chỉ gọi lại một method có sẵn
        // =====================================================


        // =====================================================
        // 1. Static method reference
        // Lambda:
        // name -> User.isLongName(name)
        //
        // Method reference:
        // User::isLongName
        // =====================================================

        Predicate<String> checkName = User::isLongName;

        System.out.println(checkName.test("Minh")); // true
        System.out.println(checkName.test("An"));   // false


        // =====================================================
        // 2. Instance method reference của object cụ thể
        // Lambda:
        // text -> System.out.println(text)
        //
        // Method reference:
        // System.out::println
        // =====================================================

        Consumer<String> printer = System.out::println;

        printer.accept("Hello Method Reference");


        // =====================================================
        // 3. Instance method reference của class
        // Lambda:
        // text -> text.length()
        //
        // Method reference:
        // String::length
        // =====================================================

        Function<String, Integer> lengthFunction = String::length;

        System.out.println(lengthFunction.apply("Java")); // 4


        // =====================================================
        // 4. Constructor reference
        // Lambda:
        // name -> new User(name)
        //
        // Method reference:
        // User::new
        // =====================================================

        Function<String, User> userCreator = User::new;

        User user = userCreator.apply("Minh");

        System.out.println(user);


        // =====================================================
        // 5. Dùng trong Stream
        // =====================================================

        List<User> users = new ArrayList<>();

        names.stream()
                .map(User::new)             // String -> User
                .forEach(users::add);       // add user vào list

        System.out.println("\nUsers:");
        users.forEach(User::printName);
    }
}
```

## Cần hiểu

Method Reference dùng toán tử:

```java
::
```

Nó là cách viết ngắn hơn lambda khi lambda chỉ gọi một method có sẵn.

```java
text -> text.length()
```

có thể viết thành:

```java
String::length
```

```java
name -> new User(name)
```

có thể viết thành:

```java
User::new
```

## Câu phỏng vấn

> Method Reference là cách viết ngắn gọn hơn lambda expression khi lambda chỉ gọi một method có sẵn. Nó dùng toán tử `::`. Ví dụ `str -> str.length()` có thể viết thành `String::length`, `x -> System.out.println(x)` có thể viết thành `System.out::println`, và `name -> new User(name)` có thể viết thành `User::new`.

---

# Bài 7 — Tổng hợp để hiểu luồng phỏng vấn

## `b07_interview_summary/Demo.java`

```java
package com.nvminh162.lambdademo.b07_interview_summary;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class User {
    private String name;
    private int age;
    private boolean active;

    public User(String name, int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }
}

public class Demo {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Minh", 22, true),
                new User("An", 17, true),
                new User("Binh", 25, false),
                new User("Long", 20, true)
        );

        // Predicate là Functional Interface
        // Nó có 1 abstract method là test(T t)
        // Lambda bên dưới chính là implementation của test()

        Predicate<User> isAdult = user -> user.getAge() >= 18;
        Predicate<User> isActive = User::isActive;

        List<String> result = users.stream()
                .filter(isAdult)          // giữ user >= 18 tuổi
                .filter(isActive)         // giữ user active
                .map(User::getName)       // User -> name
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

## Luồng hiểu

```text
Predicate<User> isAdult = user -> user.getAge() >= 18;
```

Nghĩa là:

```text
Tạo một điều kiện kiểm tra user có >= 18 tuổi không
```

```java
.filter(isAdult)
```

Nghĩa là:

```text
lọc user trưởng thành
```

```java
.map(User::getName)
```

Nghĩa là:

```text
chuyển User thành name
```

## Câu phỏng vấn tổng quát

> Functional Interface là interface chỉ có một abstract method, nên có thể được implement bằng lambda expression. Lambda là cú pháp ngắn gọn để viết anonymous function, thường dùng với Collection, Stream API hoặc các interface như `Predicate`, `Function`, `Consumer`, `Supplier`. Nếu lambda chỉ gọi một method có sẵn thì có thể viết ngắn hơn bằng Method Reference, ví dụ `User::getName` hoặc `System.out::println`. Trong thực tế, em hay dùng lambda trong `filter`, `map`, `forEach`, `sort`, `removeIf` để code ngắn gọn và dễ đọc hơn.

---

# Tóm tắt cần nhớ khi phỏng vấn

```text
Functional Interface
-> Interface chỉ có 1 abstract method
-> Có thể có default/static method
-> Dùng làm target type cho lambda

Lambda
-> Anonymous function
-> Cú pháp: (params) -> expression
-> Dùng để implement functional interface

Method Reference
-> Cách viết ngắn hơn lambda
-> Dùng khi lambda chỉ gọi method có sẵn
-> Cú pháp: ClassName::methodName
```

Câu chốt ngắn gọn:

> Functional Interface là nền tảng, Lambda là cách viết implementation ngắn gọn, còn Method Reference là cách rút gọn Lambda khi chỉ gọi một method có sẵn.
