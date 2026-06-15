Cốt lõi cần nhớ:

> `static` = thuộc về **class**, không thuộc riêng object nào.
> Dùng khi dữ liệu/hàm đó **dùng chung** hoặc **không cần tạo object vẫn dùng được**.

---

# Case 1: `static variable` dùng để chia sẻ dữ liệu chung

## Package

```text
com.nvminh162.p01_variable
```

## Student.java

```java
package com.nvminh162.p01_variable;

public class Student {
    // static: dùng chung cho tất cả Student
    public static String schoolName = "ABC University";
    public static int totalStudents = 0;

    // non-static: mỗi object có name riêng
    private String name;

    public Student(String name) {
        this.name = name;
        totalStudents++;
    }

    public void printInfo() {
        System.out.println(name + " - " + schoolName);
    }
}
```

## Main.java

```java
package com.nvminh162.p01_variable;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Minh");
        Student s2 = new Student("An");

        s1.printInfo();
        s2.printInfo();

        System.out.println("Total students: " + Student.totalStudents);
    }
}
```

## Kết quả

```text
Minh - ABC University
An - ABC University
Total students: 2
```

## Hiểu code

`name` không static nên mỗi object có tên riêng:

```java
s1 -> Minh
s2 -> An
```

Nhưng `schoolName` và `totalStudents` là static nên thuộc về class `Student`, cả `s1` và `s2` dùng chung.

## Câu phỏng vấn

> Static variable là biến thuộc về class, chỉ có một bản copy duy nhất và được chia sẻ cho tất cả object. Ví dụ `totalStudents` dùng để đếm tổng số object `Student` đã được tạo.

---

# Case 2: `public static final` dùng cho hằng số

## Package

```text
com.nvminh162.p02_constant
```

## AppConfig.java

```java
package com.nvminh162.p02_constant;

public class AppConfig {
    public static final String APP_NAME = "Java Interview App";
    public static final int MAX_LOGIN_ATTEMPTS = 3;
}
```

## Main.java

```java
package com.nvminh162.p02_constant;

public class Main {
    public static void main(String[] args) {
        System.out.println(AppConfig.APP_NAME);
        System.out.println(AppConfig.MAX_LOGIN_ATTEMPTS);
    }
}
```

## Hiểu code

`APP_NAME` và `MAX_LOGIN_ATTEMPTS` là hằng số.

Dùng:

```java
public static final
```

vì:

`public` → nơi khác dùng được
`static` → gọi trực tiếp qua class, không cần tạo object
`final` → không cho thay đổi giá trị

## Câu phỏng vấn

> Với những giá trị cố định như tên app, số lần đăng nhập tối đa, em thường dùng `public static final` để khai báo hằng số. Vì nó thuộc class và không cần tạo object để sử dụng.

---

# Case 3: `static method` dùng cho hàm tiện ích

## Package

```text
com.nvminh162.p03_static_method
```

## MathUtils.java

```java
package com.nvminh162.p03_static_method;

public class MathUtils {
    public static int square(int number) {
        return number * number;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }
}
```

## Main.java

```java
package com.nvminh162.p03_static_method;

public class Main {
    public static void main(String[] args) {
        System.out.println(MathUtils.square(5));
        System.out.println(MathUtils.max(10, 20));
    }
}
```

## Kết quả

```text
25
20
```

## Hiểu code

Hàm `square()` và `max()` không cần dữ liệu riêng của object.

Nó chỉ nhận input rồi trả output.

Vì vậy không cần tạo object kiểu:

```java
MathUtils utils = new MathUtils();
```

Mà gọi thẳng:

```java
MathUtils.square(5);
```

## Câu phỏng vấn

> Static method thường dùng cho utility method, tức là những hàm xử lý độc lập, không phụ thuộc vào dữ liệu riêng của object. Ví dụ `Math.max()` hoặc một hàm tính toán đơn giản.

---

# Case 4: `private static` dùng cho xử lý nội bộ

## Package

```text
com.nvminh162.p04_private_static
```

## PasswordUtils.java

```java
package com.nvminh162.p04_private_static;

public class PasswordUtils {
    private static final int MIN_LENGTH = 6;

    public static boolean isValidPassword(String password) {
        return password != null && hasEnoughLength(password);
    }

    private static boolean hasEnoughLength(String password) {
        return password.length() >= MIN_LENGTH;
    }
}
```

## Main.java

```java
package com.nvminh162.p04_private_static;

public class Main {
    public static void main(String[] args) {
        System.out.println(PasswordUtils.isValidPassword("123"));
        System.out.println(PasswordUtils.isValidPassword("123456"));
    }
}
```

## Kết quả

```text
false
true
```

## Hiểu code

Bên ngoài chỉ cần gọi:

```java
PasswordUtils.isValidPassword("123456");
```

Còn hàm:

```java
private static boolean hasEnoughLength(...)
```

là hàm phụ bên trong class, không cần cho bên ngoài biết.

## Câu phỏng vấn

> `private static` thường dùng cho helper method hoặc dữ liệu nội bộ trong class. Nó giúp chia nhỏ logic nhưng vẫn che giấu chi tiết xử lý bên trong.

---

# Case 5: `static block` chạy một lần khi class được load

## Package

```text
com.nvminh162.p05_static_block
```

## DatabaseConfig.java

```java
package com.nvminh162.p05_static_block;

public class DatabaseConfig {
    public static String url;

    static {
        System.out.println("Static block is running...");
        url = "jdbc:mysql://localhost:3306/mydb";
    }

    public DatabaseConfig() {
        System.out.println("Constructor is running...");
    }
}
```

## Main.java

```java
package com.nvminh162.p05_static_block;

public class Main {
    public static void main(String[] args) {
        System.out.println(DatabaseConfig.url);

        DatabaseConfig db1 = new DatabaseConfig();
        DatabaseConfig db2 = new DatabaseConfig();
    }
}
```

## Kết quả

```text
Static block is running...
jdbc:mysql://localhost:3306/mydb
Constructor is running...
Constructor is running...
```

## Hiểu code

`static block` chạy **1 lần duy nhất** khi class `DatabaseConfig` được load.

Constructor chạy **mỗi lần tạo object**.

Ở đây tạo 2 object nên constructor chạy 2 lần, nhưng static block chỉ chạy 1 lần.

## Câu phỏng vấn

> Static block dùng để khởi tạo dữ liệu static phức tạp. Nó chạy một lần khi class được load và chạy trước constructor.

---

# Case 6: Singleton dùng `private static`

## Package

```text
com.nvminh162.p06_singleton
```

## Logger.java

```java
package com.nvminh162.p06_singleton;

public class Logger {
    private static final Logger INSTANCE = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
```

## Main.java

```java
package com.nvminh162.p06_singleton;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Hello Java");

        System.out.println(logger1 == logger2);
    }
}
```

## Kết quả

```text
LOG: Hello Java
true
```

## Hiểu code

```java
private static final Logger INSTANCE = new Logger();
```

Dòng này tạo sẵn một object `Logger` duy nhất.

Constructor để `private` nên bên ngoài không thể:

```java
new Logger();
```

Muốn lấy object thì phải gọi:

```java
Logger.getInstance();
```

`logger1 == logger2` là `true` vì cả hai cùng trỏ tới một object duy nhất.

## Câu phỏng vấn

> Singleton là pattern đảm bảo một class chỉ có một object duy nhất. Trong Java có thể dùng `private static final instance`, `private constructor` và `public static getInstance()` để triển khai.

---

# Tổng kết để nhớ khi phỏng vấn

Bạn chỉ cần nhớ các trường hợp dùng `static` như sau:

```text
static variable     -> dữ liệu dùng chung
static final        -> hằng số
static method       -> hàm tiện ích, không cần object
private static      -> helper nội bộ
static block        -> chạy 1 lần khi class load
static + singleton  -> tạo object dùng chung duy nhất
```

Câu trả lời phỏng vấn đầy đủ, ngắn gọn:

> Trong Java, `static` dùng để khai báo thành phần thuộc về class thay vì thuộc về object. Biến static chỉ có một bản copy và được chia sẻ giữa tất cả object. Method static có thể gọi trực tiếp thông qua tên class mà không cần tạo object. Static thường dùng cho hằng số, biến đếm, utility method, helper method, static block hoặc singleton. Tuy nhiên không nên lạm dụng static vì nó là dữ liệu dùng chung, có thể khó test và không phù hợp khi cần đa hình.
