package com.nvminh162.b07_interview_summary;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> { System.out.println("Task running in: " + Thread.currentThread().getName()); });
        thread.setName("Worker-Thread");

        System.out.println("Before start: " + thread.getState());
        thread.start();
        System.out.println("After start: " + thread.getState());

        thread.join();
        System.out.println("After join: " + thread.getState());
        System.out.println("Main finished");
    }
}
