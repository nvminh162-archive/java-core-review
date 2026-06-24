Đúng hướng. Dựa trên danh sách bạn gửi, mình sẽ chia thành **7 package đúng theo 7 bài Stream API**, mỗi bài có code demo + comment để bạn chạy và hiểu luồng xử lý.

Mình dùng `Collectors.toList()` thay vì `.toList()` để tương thích tốt với **Java 8+**.

---

# Cấu trúc package

```text
src/main/java/com/nvminh162/streamdemo
├── common
│   ├── User.java
│   └── DemoData.java
├── b01_stream_intro
│   └── Demo.java
├── b02_map
│   └── Demo.java
├── b03_flatmap
│   └── Demo.java
├── b04_filter
│   └── Demo.java
├── b05_reduce
│   └── Demo.java
├── b06_groupingby
│   └── Demo.java
└── b07_sorted_distinct_limit_skip
    └── Demo.java
```

---

# Common Model dùng chung

## `common/User.java`

```java
package com.nvminh162.streamdemo.common;

import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean active;
    private String department;
    private double salary;
    private List<String> skills;

    public User(int id, String name, int age, boolean active,
                String department, double salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
        this.department = department;
        this.salary = salary;
        this.skills = skills;
    }

    public int getId() {
        return id;
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

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name + " - " + age + " - " + department + " - " + salary;
    }
}
```

## `common/DemoData.java`

```java
package com.nvminh162.streamdemo.common;

import java.util.Arrays;
import java.util.List;

public class DemoData {
    public static List<User> users() {
        return Arrays.asList(
                new User(1, "Minh", 22, true, "IT", 1000,
                        Arrays.asList("Java", "Spring", "SQL")),

                new User(2, "An", 17, true, "HR", 700,
                        Arrays.asList("Excel", "Communication")),

                new User(3, "Binh", 25, false, "IT", 1500,
                        Arrays.asList("Java", "Docker", "AWS")),

                new User(4, "Long", 30, true, "Finance", 2000,
                        Arrays.asList("Excel", "Accounting", "SQL")),

                new User(5, "Hoa", 28, true, "IT", 1800,
                        Arrays.asList("Java", "Spring", "AWS")),

                new User(6, "Lan", 20, false, "HR", 900,
                        Arrays.asList("Recruitment", "Communication"))
        );
    }
}
```

---

# Bài 1 — Stream API là gì? Luồng chạy thế nào?

## `b01_stream_intro/Demo.java`

```java
package com.nvminh162.streamdemo.b01_stream_intro;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // CÁCH CŨ: Imperative style
        // Tức là mình nói rõ từng bước "làm như thế nào"
        // =====================================================

        List<String> namesOldWay = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() > 18) {
                namesOldWay.add(user.getName());
            }
        }

        Collections.sort(namesOldWay);

        System.out.println("Old way:");
        System.out.println(namesOldWay);


        // =====================================================
        // CÁCH MỚI: Stream API
        // Tức là mình mô tả "muốn kết quả gì"
        // =====================================================

        List<String> namesStream = users.stream()              // Source: lấy dữ liệu từ List
                .filter(user -> user.getAge() > 18)            // Intermediate: lọc user > 18 tuổi
                .map(User::getName)                            // Intermediate: chuyển User -> String name
                .sorted()                                      // Intermediate: sắp xếp tên
                .collect(Collectors.toList());                 // Terminal: gom kết quả thành List

        System.out.println("\nStream way:");
        System.out.println(namesStream);


        // =====================================================
        // DEMO LAZY EVALUATION
        // filter/map chưa chạy ngay nếu chưa có terminal operation
        // =====================================================

        System.out.println("\nLazy demo:");

        users.stream()
                .filter(user -> {
                    System.out.println("Filtering: " + user.getName());
                    return user.getAge() > 18;
                })
                .map(user -> {
                    System.out.println("Mapping: " + user.getName());
                    return user.getName();
                });

        // Đến đây chưa in Filtering/Mapping
        // Vì stream chưa có terminal operation như collect(), forEach(), count()


        System.out.println("\nAdd terminal operation:");

        users.stream()
                .filter(user -> {
                    System.out.println("Filtering: " + user.getName());
                    return user.getAge() > 18;
                })
                .map(user -> {
                    System.out.println("Mapping: " + user.getName());
                    return user.getName();
                })
                .collect(Collectors.toList());

        // Khi có collect(), pipeline mới thật sự chạy
    }
}
```

## Cần hiểu

```text
Stream pipeline = Source -> Intermediate operations -> Terminal operation
```

```text
Source       -> users.stream()
Intermediate -> filter(), map(), sorted()
Terminal     -> collect(), forEach(), count(), reduce()
```

Câu phỏng vấn:

> Stream API là tính năng từ Java 8 dùng để xử lý collection theo phong cách functional programming. Stream không lưu dữ liệu, mà tạo pipeline xử lý dữ liệu. Các operation như `filter`, `map`, `sorted` là intermediate operation và có tính lazy, chỉ khi gặp terminal operation như `collect`, `forEach`, `count` thì pipeline mới thực sự chạy.

---

# Bài 2 — `map()` trong Stream API

## `b02_map/Demo.java`

```java
package com.nvminh162.streamdemo.b02_map;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // map() dùng để biến đổi dữ liệu
        // Ví dụ: User -> String name
        // =====================================================

        List<String> names = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("Names:");
        System.out.println(names);


        // =====================================================
        // map() cũng có thể transform String -> String khác
        // =====================================================

        List<String> upperNames = users.stream()
                .map(User::getName)              // User -> name
                .map(String::toUpperCase)        // name -> uppercase name
                .collect(Collectors.toList());

        System.out.println("\nUpper names:");
        System.out.println(upperNames);


        // =====================================================
        // mapToDouble(): dùng khi muốn lấy dữ liệu dạng double
        // Ví dụ: User -> salary
        // =====================================================

        List<Double> salaries = users.stream()
                .map(User::getSalary)
                .collect(Collectors.toList());

        System.out.println("\nSalaries:");
        System.out.println(salaries);

        double totalSalary = users.stream()
                .mapToDouble(User::getSalary)
                .sum();

        System.out.println("\nTotal salary:");
        System.out.println(totalSalary);
    }
}
```

## Cần hiểu

`map()` dùng để **biến đổi mỗi phần tử thành dạng khác**.

```text
User -> name
User -> salary
String -> uppercase String
```

Câu phỏng vấn:

> `map()` trong Stream dùng để transform dữ liệu. Ví dụ từ `User` lấy ra `name` thì dùng `map(User::getName)`. Kết quả sau `map` là một stream mới chứa kiểu dữ liệu đã được chuyển đổi.

---

# Bài 3 — `flatMap()` trong Stream API

## `b03_flatmap/Demo.java`

```java
package com.nvminh162.streamdemo.b03_flatmap;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // Vấn đề:
        // Mỗi user có nhiều skills: List<String>
        //
        // users là List<User>
        // user.getSkills() là List<String>
        //
        // Nếu dùng map(User::getSkills)
        // kết quả sẽ là List<List<String>>
        // =====================================================

        List<List<String>> skillLists = users.stream()
                .map(User::getSkills)
                .collect(Collectors.toList());

        System.out.println("Using map():");
        System.out.println(skillLists);


        // =====================================================
        // flatMap() dùng để làm phẳng dữ liệu
        //
        // Từ:
        // List<List<String>>
        //
        // Thành:
        // List<String>
        // =====================================================

        List<String> allSkills = users.stream()
                .flatMap(user -> user.getSkills().stream())
                .collect(Collectors.toList());

        System.out.println("\nUsing flatMap():");
        System.out.println(allSkills);


        // =====================================================
        // Kết hợp distinct() để loại bỏ skill trùng
        // =====================================================

        List<String> uniqueSkills = users.stream()
                .flatMap(user -> user.getSkills().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("\nUnique skills:");
        System.out.println(uniqueSkills);
    }
}
```

## Cần hiểu

```text
map()     -> biến đổi từng phần tử, có thể tạo Stream<List<String>>
flatMap() -> biến nhiều list con thành một stream phẳng
```

Ví dụ:

```text
[[Java, SQL], [Excel], [Java, AWS]]
```

sau `flatMap()` thành:

```text
[Java, SQL, Excel, Java, AWS]
```

Câu phỏng vấn:

> `flatMap()` dùng khi mỗi phần tử sau khi map trả về một collection hoặc stream con, và mình muốn làm phẳng chúng thành một stream duy nhất. Ví dụ mỗi user có nhiều skills, dùng `flatMap` để lấy tất cả skills của tất cả user thành một list.

---

# Bài 4 — `filter()` trong Stream API

## `b04_filter/Demo.java`

```java
package com.nvminh162.streamdemo.b04_filter;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // filter() dùng để lọc dữ liệu theo điều kiện
        // Điều kiện trả về true thì giữ lại
        // Điều kiện trả về false thì bỏ qua
        // =====================================================

        List<User> adultUsers = users.stream()
                .filter(user -> user.getAge() >= 18)
                .collect(Collectors.toList());

        System.out.println("Adult users:");
        adultUsers.forEach(System.out::println);


        // =====================================================
        // Lọc nhiều điều kiện
        // User phải active và thuộc phòng IT
        // =====================================================

        List<User> activeITUsers = users.stream()
                .filter(User::isActive)
                .filter(user -> user.getDepartment().equals("IT"))
                .collect(Collectors.toList());

        System.out.println("\nActive IT users:");
        activeITUsers.forEach(System.out::println);


        // =====================================================
        // findFirst(): tìm phần tử đầu tiên thỏa điều kiện
        // Trả về Optional vì có thể không tìm thấy
        // =====================================================

        Optional<User> firstAdult = users.stream()
                .filter(user -> user.getAge() >= 18)
                .findFirst();

        System.out.println("\nFirst adult user:");
        firstAdult.ifPresent(System.out::println);


        // =====================================================
        // anyMatch(): có ít nhất 1 phần tử thỏa điều kiện không?
        // =====================================================

        boolean hasITUser = users.stream()
                .anyMatch(user -> user.getDepartment().equals("IT"));

        System.out.println("\nHas IT user:");
        System.out.println(hasITUser);


        // =====================================================
        // allMatch(): tất cả phần tử có thỏa điều kiện không?
        // =====================================================

        boolean allActive = users.stream()
                .allMatch(User::isActive);

        System.out.println("\nAll users active:");
        System.out.println(allActive);
    }
}
```

## Cần hiểu

`filter()` dùng để **lọc phần tử**.

```text
true  -> giữ lại
false -> bỏ qua
```

Câu phỏng vấn:

> `filter()` dùng để lọc các phần tử theo điều kiện. Nó nhận vào một predicate trả về boolean. Nếu điều kiện đúng thì phần tử được giữ lại trong stream, nếu sai thì bị loại bỏ.

---

# Bài 5 — `reduce()` trong Stream API

## `b05_reduce/Demo.java`

```java
package com.nvminh162.streamdemo.b05_reduce;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // reduce() dùng để gộp nhiều phần tử thành 1 kết quả
        // Ví dụ: tổng, tích, max, nối chuỗi
        // =====================================================

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum:");
        System.out.println(sum);

        // Luồng chạy:
        // ban đầu a = 0
        // b = 1 -> a + b = 1
        // b = 2 -> 1 + 2 = 3
        // b = 3 -> 3 + 3 = 6
        // b = 4 -> 6 + 4 = 10
        // b = 5 -> 10 + 5 = 15


        // =====================================================
        // Viết ngắn hơn bằng Integer::sum
        // =====================================================

        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("\nSum 2:");
        System.out.println(sum2);


        // =====================================================
        // Tìm max bằng reduce
        // =====================================================

        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

        System.out.println("\nMax:");
        System.out.println(max);


        // =====================================================
        // Nối chuỗi bằng reduce
        // =====================================================

        List<String> names = Arrays.asList("Minh", "An", "Binh");

        String result = names.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("\nJoin names:");
        System.out.println(result.trim());


        // =====================================================
        // Tính tổng salary bằng reduce
        // Thực tế dùng mapToDouble().sum() sẽ gọn hơn
        // =====================================================

        List<User> users = DemoData.users();

        double totalSalary = users.stream()
                .map(User::getSalary)
                .reduce(0.0, Double::sum);

        System.out.println("\nTotal salary:");
        System.out.println(totalSalary);
    }
}
```

## Cần hiểu

`reduce()` dùng để **gom nhiều phần tử thành một kết quả duy nhất**.

```text
[1, 2, 3, 4, 5] -> 15
```

Câu phỏng vấn:

> `reduce()` dùng để gộp các phần tử trong stream thành một giá trị duy nhất, ví dụ tính tổng, tìm max hoặc nối chuỗi. Nó thường nhận giá trị khởi tạo và một hàm xử lý để kết hợp kết quả hiện tại với phần tử tiếp theo.

---

# Bài 6 — `groupingBy()` trong Collectors

## `b06_groupingby/Demo.java`

```java
package com.nvminh162.streamdemo.b06_groupingby;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<User> users = DemoData.users();

        // =====================================================
        // groupingBy() dùng để nhóm dữ liệu theo một key nào đó
        // Ví dụ: nhóm user theo department
        // =====================================================

        Map<String, List<User>> usersByDepartment = users.stream()
                .collect(Collectors.groupingBy(User::getDepartment));

        System.out.println("Users by department:");
        usersByDepartment.forEach((department, userList) -> {
            System.out.println(department + " -> " + userList);
        });


        // =====================================================
        // Đếm số user trong mỗi department
        // =====================================================

        Map<String, Long> countByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.counting()
                ));

        System.out.println("\nCount by department:");
        System.out.println(countByDepartment);


        // =====================================================
        // Tính lương trung bình theo department
        // =====================================================

        Map<String, Double> averageSalaryByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.averagingDouble(User::getSalary)
                ));

        System.out.println("\nAverage salary by department:");
        System.out.println(averageSalaryByDepartment);


        // =====================================================
        // Nhóm theo department nhưng chỉ lấy danh sách tên
        // =====================================================

        Map<String, List<String>> namesByDepartment = users.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.mapping(User::getName, Collectors.toList())
                ));

        System.out.println("\nNames by department:");
        System.out.println(namesByDepartment);
    }
}
```

## Cần hiểu

`groupingBy()` giống như `GROUP BY` trong SQL.

Ví dụ:

```text
IT      -> Minh, Binh, Hoa
HR      -> An, Lan
Finance -> Long
```

Câu phỏng vấn:

> `Collectors.groupingBy()` dùng để nhóm dữ liệu theo một key. Ví dụ nhóm user theo department thì kết quả là `Map<String, List<User>>`, trong đó key là department và value là danh sách user thuộc department đó. Có thể kết hợp với `counting`, `averagingDouble`, `mapping` để thống kê dữ liệu.

---

# Bài 7 — `sorted()`, `distinct()`, `limit()`, `skip()`

## `b07_sorted_distinct_limit_skip/Demo.java`

```java
package com.nvminh162.streamdemo.b07_sorted_distinct_limit_skip;

import com.nvminh162.streamdemo.common.DemoData;
import com.nvminh162.streamdemo.common.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // distinct(): loại bỏ phần tử trùng
        // =====================================================

        List<Integer> numbers = Arrays.asList(5, 3, 1, 3, 2, 5, 4);

        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Distinct numbers:");
        System.out.println(distinctNumbers);


        // =====================================================
        // sorted(): sắp xếp tăng dần theo thứ tự tự nhiên
        // =====================================================

        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nSorted numbers:");
        System.out.println(sortedNumbers);


        // =====================================================
        // distinct() + sorted()
        // Vừa loại trùng, vừa sort
        // =====================================================

        List<Integer> uniqueSortedNumbers = numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nUnique sorted numbers:");
        System.out.println(uniqueSortedNumbers);


        // =====================================================
        // Sort object theo salary tăng dần
        // =====================================================

        List<User> users = DemoData.users();

        List<User> usersBySalaryAsc = users.stream()
                .sorted(Comparator.comparing(User::getSalary))
                .collect(Collectors.toList());

        System.out.println("\nUsers by salary ascending:");
        usersBySalaryAsc.forEach(System.out::println);


        // =====================================================
        // Sort object theo salary giảm dần
        // =====================================================

        List<User> usersBySalaryDesc = users.stream()
                .sorted(Comparator.comparing(User::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("\nUsers by salary descending:");
        usersBySalaryDesc.forEach(System.out::println);


        // =====================================================
        // limit(): lấy N phần tử đầu tiên
        // Ví dụ: lấy top 3 user lương cao nhất
        // =====================================================

        List<User> top3SalaryUsers = users.stream()
                .sorted(Comparator.comparing(User::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nTop 3 salary users:");
        top3SalaryUsers.forEach(System.out::println);


        // =====================================================
        // skip(): bỏ qua N phần tử đầu tiên
        // Thường dùng cho phân trang
        // =====================================================

        int page = 2;
        int pageSize = 2;

        List<User> page2Users = users.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        System.out.println("\nPage 2 users:");
        page2Users.forEach(System.out::println);
    }
}
```

## Cần hiểu

```text
distinct() -> loại phần tử trùng
sorted()   -> sắp xếp
limit(n)   -> lấy n phần tử đầu
skip(n)    -> bỏ qua n phần tử đầu
```

Câu phỏng vấn:

> `distinct()` dùng để loại bỏ phần tử trùng, `sorted()` dùng để sắp xếp, `limit()` dùng để giới hạn số lượng kết quả, còn `skip()` dùng để bỏ qua một số phần tử đầu. `skip` và `limit` thường dùng chung để làm phân trang dữ liệu.

---

# Tổng kết để trả lời phỏng vấn

```text
Stream API = xử lý collection theo functional style
Pipeline = source -> intermediate -> terminal
Intermediate = filter, map, flatMap, sorted, distinct, limit, skip
Terminal = collect, forEach, count, reduce, findFirst, anyMatch
Intermediate có lazy evaluation
Terminal mới kích hoạt pipeline
```

Câu trả lời phỏng vấn tổng quát:

> Stream API là tính năng từ Java 8 dùng để xử lý dữ liệu dạng collection theo phong cách functional programming. Stream không phải data structure và không lưu dữ liệu, mà tạo pipeline xử lý gồm source, intermediate operations và terminal operation. Các intermediate operation như `filter`, `map`, `sorted` có tính lazy, tức là chưa chạy ngay. Khi gặp terminal operation như `collect`, `forEach`, `count`, pipeline mới thực sự được thực thi. Em thường dùng Stream để lọc, biến đổi, sắp xếp, nhóm dữ liệu hoặc tính toán trên collection, giúp code ngắn gọn và dễ đọc hơn vòng lặp truyền thống.
