package com.nvminh162.b06_wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    // =====================================================
    // 1. Unbounded wildcard: List<?>
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
    // Nhận List<Dog>, List<Animal>, List<Object>
    // Dùng khi muốn add Dog vào list
    // =====================================================
    public static void addDogs(List<? super Dog> dogs) {
        dogs.add(new Dog());
        dogs.add(new Dog());
        // Khi đọc ra, Java chỉ chắc chắn kiểu là Object
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
        System.out.println("Print numbers:");
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
}
