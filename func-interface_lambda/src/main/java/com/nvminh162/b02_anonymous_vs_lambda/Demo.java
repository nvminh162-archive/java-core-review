package com.nvminh162.b02_anonymous_vs_lambda;

public class Demo {
    public static void main(String[] args) {

        // =====================================================
        // Cách cũ: Anonymous class
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
        // Cách mới: Lambda
        // Vì Greeting chỉ có 1 abstract method
        // nên có thể viết ngắn gọn bằng lambda
        // =====================================================
        Greeting newGreeting = name -> System.out.println("Hello " + name);
        newGreeting.sayHello("An");
    }
}
