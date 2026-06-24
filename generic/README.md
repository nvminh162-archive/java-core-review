# Java Generics — Bản thực hành để hiểu và trả lời phỏng vấn

## Cấu trúc package

```text
src/main/java/com/nvminh162/genericdemo
├── b01_why_generics
│   └── Demo.java
├── b02_generic_class
│   ├── Box.java
│   └── Demo.java
├── b03_generic_pair
│   ├── Pair.java
│   └── Demo.java
├── b04_generic_method
│   └── Demo.java
├── b05_bounded_type
│   └── Demo.java
├── b06_wildcard
│   ├── Animal.java
│   ├── Dog.java
│   ├── Cat.java
│   └── Demo.java
└── b07_interview_summary
    └── Demo.java
```

---

# Bài 1 — Vì sao cần Generics?

## `b01_why_generics/Demo.java`

```java
package com.nvminh162.genericdemo.b01_why_generics;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Cách cũ: không dùng Generics
        // List có thể chứa mọi kiểu dữ liệu
        // =====================================================

        List oldList = new ArrayList();

        oldList.add("Java");
        oldList.add("Spring");
        oldList.add(100); // vẫn add được, nhưng dễ lỗi

        // Vì oldList không khai báo kiểu dữ liệu
        // nên khi lấy ra phải ép kiểu thủ công

        String text1 = (String) oldList.get(0);
        System.out.println(text1);

        // Dòng này chạy sẽ lỗi ClassCastException
        // Vì phần tử index 2 là Integer, không phải String

        // String text2 = (String) oldList.get(2);


        // =====================================================
        // Cách mới: dùng Generics
        // List<String> nghĩa là list này chỉ chứa String
        // =====================================================

        List<String> names = new ArrayList<>();

        names.add("Java");
        names.add("Spring");

        // names.add(100); // Compile error

        // Lấy ra không cần ép kiểu
        String name = names.get(0);

        System.out.println(name);
    }
}
```

## Cần hiểu

```text
Không dùng Generics:
-> Có thể add sai kiểu
-> Phải ép kiểu khi lấy dữ liệu
-> Dễ lỗi lúc runtime

Dùng Generics:
-> Kiểm tra kiểu ngay lúc compile
-> Không cần ép kiểu
-> Code rõ ràng và an toàn hơn
```

## Câu phỏng vấn

Generics trong Java cho phép khai báo kiểu dữ liệu cho class, interface hoặc method. Nó giúp code type-safe hơn, tức là phát hiện lỗi sai kiểu ngay lúc compile thay vì runtime. Ví dụ `List<String>` chỉ cho phép lưu String, khi lấy dữ liệu ra cũng không cần ép kiểu thủ công.

---

# Bài 2 — Generic Class

## `b02_generic_class/Box.java`

```java
package com.nvminh162.genericdemo.b02_generic_class;

// T là type parameter
// T có thể là String, Integer, User, Product...
public class Box<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

## `b02_generic_class/Demo.java`

```java
package com.nvminh162.genericdemo.b02_generic_class;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Box<String>
        // Lúc này T được hiểu là String
        // =====================================================

        Box<String> stringBox = new Box<>();

        stringBox.setValue("Hello Generic");

        String text = stringBox.getValue();

        System.out.println(text);


        // =====================================================
        // Box<Integer>
        // Lúc này T được hiểu là Integer
        // =====================================================

        Box<Integer> intBox = new Box<>();

        intBox.setValue(100);

        Integer number = intBox.getValue();

        System.out.println(number);


        // =====================================================
        // Lợi ích:
        // Cùng một class Box<T>
        // nhưng dùng được cho nhiều kiểu dữ liệu khác nhau
        // =====================================================

        // stringBox.setValue(123); // lỗi vì stringBox chỉ nhận String
        // intBox.setValue("Java"); // lỗi vì intBox chỉ nhận Integer
    }
}
```

## Cần hiểu

```text
Box<T> là khuôn mẫu chung.

Box<String>  -> T là String
Box<Integer> -> T là Integer
Box<User>    -> T là User
```

## Câu phỏng vấn

Generic class là class có type parameter, ví dụ `Box<T>`. Khi sử dụng, mình truyền kiểu cụ thể như `Box<String>` hoặc `Box<Integer>`. Nhờ vậy cùng một class có thể tái sử dụng cho nhiều kiểu dữ liệu khác nhau nhưng vẫn đảm bảo type-safe.

---

# Bài 3 — Generic với nhiều type: Pair<K, V>

## `b03_generic_pair/Pair.java`

```java
package com.nvminh162.genericdemo.b03_generic_pair;

// K = Key type
// V = Value type
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
```

## `b03_generic_pair/Demo.java`

```java
package com.nvminh162.genericdemo.b03_generic_pair;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Pair<String, Integer>
        // K là String, V là Integer
        // =====================================================

        Pair<String, Integer> studentScore = new Pair<>("Minh", 9);

        String studentName = studentScore.getKey();
        Integer score = studentScore.getValue();

        System.out.println(studentName + " = " + score);


        // =====================================================
        // Pair<Integer, String>
        // K là Integer, V là String
        // =====================================================

        Pair<Integer, String> user = new Pair<>(1, "Nguyen Van A");

        Integer userId = user.getKey();
        String userName = user.getValue();

        System.out.println(userId + " - " + userName);


        // =====================================================
        // Liên hệ thực tế:
        // Map<K, V> cũng là Generic kiểu key-value
        //
        // Map<Integer, User>
        // Map<String, Product>
        // =====================================================
    }
}
```

## Cần hiểu

```text
<T>     -> một kiểu dữ liệu
<K, V>  -> hai kiểu dữ liệu
<E>     -> thường dùng cho element trong collection
```

## Câu phỏng vấn

Generics có thể có nhiều type parameter, ví dụ `Pair<K, V>` hoặc `Map<K, V>`. `K` thường đại diện cho key, còn `V` đại diện cho value. Điều này giúp class linh hoạt hơn nhưng vẫn kiểm soát kiểu dữ liệu rõ ràng.

---

# Bài 4 — Generic Method

## `b04_generic_method/Demo.java`

```java
package com.nvminh162.genericdemo.b04_generic_method;

public class Demo {

    // =====================================================
    // Generic method
    // <T> đặt trước kiểu trả về
    //
    // Method này nhận vào T và trả về T
    // Tùy lúc gọi mà T có thể là String, Integer, Double...
    // =====================================================

    public static <T> T printAndReturn(T value) {
        System.out.println("Value: " + value);
        return value;
    }

    // Generic method với mảng
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {

        // T là String
        String text = printAndReturn("Java");

        // T là Integer
        Integer number = printAndReturn(100);

        // T là Double
        Double price = printAndReturn(99.5);

        System.out.println(text);
        System.out.println(number);
        System.out.println(price);


        // =====================================================
        // Demo printArray
        // =====================================================

        String[] names = {"Minh", "An", "Binh"};
        Integer[] numbers = {1, 2, 3};

        System.out.println("\nNames:");
        printArray(names);

        System.out.println("\nNumbers:");
        printArray(numbers);
    }
}
```

## Cần hiểu

```java
public static <T> T printAndReturn(T value)
```

Trong đó:

```text
<T> đầu tiên  -> khai báo đây là generic method
T thứ hai     -> kiểu trả về
T value       -> tham số có kiểu T
```

## Câu phỏng vấn

Generic method là method có type parameter riêng, thường viết `<T>` trước kiểu trả về. Nó giúp một method có thể làm việc với nhiều kiểu dữ liệu khác nhau mà không cần viết lại nhiều version cho từng kiểu.

---

# Bài 5 — Bounded Type: `<T extends Number>`

## `b05_bounded_type/Demo.java`

```java
package com.nvminh162.genericdemo.b05_bounded_type;

import java.util.Arrays;
import java.util.List;

public class Demo {

    // =====================================================
    // <T extends Number>
    //
    // Nghĩa là T chỉ được là Number hoặc class con của Number
    // Ví dụ: Integer, Double, Long, Float...
    // =====================================================

    public static <T extends Number> double sum(List<T> numbers) {
        double total = 0;

        for (T number : numbers) {
            // Vì T extends Number
            // nên chắc chắn gọi được doubleValue()
            total += number.doubleValue();
        }

        return total;
    }

    public static void main(String[] args) {

        List<Integer> intNumbers = Arrays.asList(1, 2, 3);
        List<Double> doubleNumbers = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println(sum(intNumbers));    // 6.0
        System.out.println(sum(doubleNumbers)); // 7.5

        // List<String> strings = Arrays.asList("A", "B");
        // sum(strings); // lỗi vì String không extends Number
    }
}
```

## Cần hiểu

```text
<T>                -> T là kiểu bất kỳ
<T extends Number> -> T chỉ được là Number hoặc con của Number
```

Ví dụ hợp lệ:

```text
Integer
Double
Long
Float
```

Không hợp lệ:

```text
String
User
Product
```

## Câu phỏng vấn

Bounded type parameter dùng để giới hạn kiểu dữ liệu của generic. Ví dụ `<T extends Number>` nghĩa là T chỉ được là `Number` hoặc class con của `Number`. Nhờ đó bên trong method mình có thể dùng các method của `Number` như `doubleValue()`.

---

# Bài 6 — Wildcards: `?`, `? extends`, `? super`

## `b06_wildcard/Animal.java`

```java
package com.nvminh162.genericdemo.b06_wildcard;

public class Animal {
    public void eat() {
        System.out.println("Animal is eating");
    }

    @Override
    public String toString() {
        return "Animal";
    }
}
```

## `b06_wildcard/Dog.java`

```java
package com.nvminh162.genericdemo.b06_wildcard;

public class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking");
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
```

## `b06_wildcard/Cat.java`

```java
package com.nvminh162.genericdemo.b06_wildcard;

public class Cat extends Animal {
    public void meow() {
        System.out.println("Cat is meowing");
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
```

## `b06_wildcard/Demo.java`

```java
package com.nvminh162.genericdemo.b06_wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    // =====================================================
    // () {
        return "Cat";
    }
}
```

## b061. Unbounded wildcard: List<?>

```
//
// Nhận List của bất kỳ kiểu nào
// Chỉ nên dùng khi chỉ cần đọc/in dữ liệu
// =====================================================

public static void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }

    // Không add được dữ liệu cụ thể
    // Vì Java không biết list thật sự đang chứa kiểu gì

    // list.add("Hello"); // lỗi
    // list.add(123);     // lỗi
}


// =====================================================
// 2. Upper bounded wildcard: List<? extends Animal>
//
// Nhận List<Animal>, List<Dog>, List<Cat>
// Dùng khi cần đọc dữ liệu như Animal
// =====================================================

public static void printAnimals(List<? extends Animal> animals) {
    for (Animal animal : animals) {
        animal.eat();
        System.out.println(animal);
    }

    // Không add được Dog/Cat vào
    // Vì Java không biết list thật sự là List<Dog> hay List<Cat>

    // animals.add(new Dog()); // lỗi
    // animals.add(new Cat()); // lỗi
}


// =====================================================
// 3. Lower bounded wildcard: List<? super Dog>
//
// Nhận List<Dog>, List<Animal>, List<Object>
// Dùng khi muốn add Dog vào list
// =====================================================

public static void addDogs(List<? super Dog> dogs) {
    dogs.add(new Dog());
    dogs.add(new Dog());

    // Khi đọc ra, chỉ chắc chắn là Object
    Object item = dogs.get(0);

    System.out.println(item);
}

public static void main(String[] args) {

    // =====================================================
    // Demo List<?>
    // =====================================================

    List<String> names = Arrays.asList("Minh", "An");
    List<Integer> numbers = Arrays.asList(1, 2, 3);

    System.out.println("Print names:");
    printList(names);

    System.out.println("\nPrint numbers:");
    printList(numbers);


    // =====================================================
    // Demo ? extends Animal
    // =====================================================

    List<Dog> dogList = Arrays.asList(new Dog(), new Dog());
    List<Cat> catList = Arrays.asList(new Cat(), new Cat());

    System.out.println("\nPrint dogs:");
    printAnimals(dogList);

    System.out.println("\nPrint cats:");
    printAnimals(catList);


    // =====================================================
    // Demo ? super Dog
    // =====================================================

    List<Animal> animalList = new ArrayList<>();

    addDogs(animalList);

    System.out.println("\nAnimal list after addDogs:");
    printList(animalList);
}
```

}

````

## Cần hiểu

```text
List<?> 
-> nhận list kiểu gì cũng được
-> đọc được dạng Object
-> không add được dữ liệu cụ thể

List<? extends Animal>
-> nhận Animal hoặc class con của Animal
-> tốt cho đọc dữ liệu
-> không add Dog/Cat cụ thể được

List<? super Dog>
-> nhận Dog hoặc class cha của Dog
-> tốt cho ghi/add Dog vào
````

## Mẹo nhớ PECS

```text
Producer Extends
Consumer Super
```

Nghĩa là:

```text
Nếu list chủ yếu để đọc dữ liệu ra  -> dùng extends
Nếu list chủ yếu để thêm dữ liệu vào -> dùng super
```

## Câu phỏng vấn

Wildcard là ký hiệu `?` trong Generics, đại diện cho một kiểu chưa biết. `<?>` dùng khi không quan tâm kiểu cụ thể, thường chỉ đọc dữ liệu. `<? extends T>` nhận T hoặc class con của T, phù hợp khi đọc dữ liệu. `<? super T>` nhận T hoặc class cha của T, phù hợp khi muốn thêm T vào collection. Quy tắc dễ nhớ là PECS: Producer Extends, Consumer Super.

---

# Bài 7 — Tổng hợp phỏng vấn

## `b07_interview_summary/Demo.java`

```java
package com.nvminh162.genericdemo.b07_interview_summary;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Generics dùng rất nhiều trong Collection
        // =====================================================

        List<String> names = new ArrayList<>();

        names.add("Minh");
        names.add("An");

        // names.add(100); // Compile error

        String firstName = names.get(0);

        System.out.println(firstName);


        // =====================================================
        // Generics cũng dùng trong Map, Optional, Stream...
        //
        // Map<Integer, User>
        // Optional<User>
        // List<Product>
        // ApiResponse<User>
        // =====================================================
    }
}
```

## Tổng kết cần nhớ

```text
Generics
-> Cho phép truyền kiểu dữ liệu vào class, interface, method
-> Ví dụ: List<String>, Box<T>, Pair<K, V>
-> Giúp type-safe, reusable, không cần casting

Generic Class
-> class Box<T>

Generic Method
-> public static <T> T methodName(T value)

Bounded Type
-> <T extends Number>

Wildcard
-> ?
-> ? extends T
-> ? super T
```

## Câu phỏng vấn tổng quát

Generics trong Java cho phép định nghĩa class, interface hoặc method với tham số kiểu dữ liệu như `<T>`, `<E>`, `<K, V>`. Nó giúp code tái sử dụng tốt hơn và type-safe hơn, vì lỗi sai kiểu được phát hiện ở compile-time. Ví dụ `List<String>` chỉ cho phép lưu String và khi lấy dữ liệu ra không cần ép kiểu. Ngoài ra Generics còn có bounded type như `<T extends Number>` để giới hạn kiểu, và wildcard như `<?>`, `<? extends T>`, `<? super T>` để xử lý linh hoạt hơn khi làm việc với collection.
