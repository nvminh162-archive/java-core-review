# Demo 01 - Static Variable trong Java

## 1. Mục tiêu

Package này dùng để demo `static variable` trong Java.

Ý chính cần hiểu:

> Biến `static` thuộc về class, không thuộc về object.
> Vì vậy nó chỉ có một bản duy nhất và được dùng chung cho tất cả object.

---

## 2. Code đang có

Trong class `Student`:

```java
static String schoolName = "ABC University";
static int totalStudents = 0;

String name;
```

Ở đây có 2 loại biến:

### Biến static

```java
static String schoolName;
static int totalStudents;
```

Hai biến này thuộc về class `Student`.

Điều đó có nghĩa là tất cả object `Student` đều dùng chung 2 biến này.

### Biến thường

```java
String name;
```

Biến `name` là biến thường, hay còn gọi là instance variable.

Mỗi object `Student` sẽ có một `name` riêng.

---

## 3. Quy trình chạy code

Trong `Main`:

```java
Student s1 = new Student("Minh");
Student s2 = new Student("An");
```

Khi dòng này chạy:

```java
new Student("Minh");
```

Java tạo ra object `s1`, có `name = "Minh"`.

Sau đó constructor chạy:

```java
totalStudents++;
```

Lúc này `totalStudents` tăng từ `0` lên `1`.

Tiếp theo:

```java
new Student("An");
```

Java tạo ra object `s2`, có `name = "An"`.

Constructor tiếp tục chạy:

```java
totalStudents++;
```

Lúc này `totalStudents` tăng từ `1` lên `2`.

---

## 4. Kết quả mong đợi

Khi chạy chương trình:

```java
s1.printInfo();
s2.printInfo();

System.out.println("Total students: " + Student.totalStudents);
```

Kết quả có thể là:

```text
Minh - ABC University
An - ABC University
Total students: 2
```

---

## 5. Vì sao `totalStudents` là static?

Vì `totalStudents` dùng để đếm tổng số sinh viên đã được tạo.

Nếu `totalStudents` là biến thường, mỗi object sẽ có một biến riêng, khi đó không đếm tổng được.

Ví dụ sai ý nghĩa:

```java
int totalStudents = 0;
```

Khi đó `s1` có `totalStudents` riêng, `s2` cũng có `totalStudents` riêng.

Nhưng khi dùng:

```java
static int totalStudents = 0;
```

thì cả `s1` và `s2` dùng chung một biến `totalStudents`.

---

## 6. Vì sao gọi bằng `Student.totalStudents`?

Vì `totalStudents` là biến static, tức là thuộc về class `Student`.

Nên cách gọi đúng và rõ ràng là:

```java
Student.totalStudents
```

Không nên gọi như này:

```java
s1.totalStudents
```

Dù Java có thể cho phép, nhưng cách này dễ gây hiểu nhầm rằng biến đó thuộc về object `s1`.

---

## 7. Cách trả lời phỏng vấn

`static variable` là biến thuộc về class thay vì thuộc về object. Nó chỉ có một bản copy duy nhất và được chia sẻ cho tất cả object của class đó. Ví dụ trong class `Student`, biến `totalStudents` là static để đếm tổng số object `Student` đã được tạo. Mỗi object có `name` riêng, nhưng tất cả object dùng chung `totalStudents`.

---

## 8. Ghi nhớ nhanh

```text
static variable  = biến của class
normal variable  = biến của object

static            = dùng chung
non-static        = mỗi object một bản riêng
```
