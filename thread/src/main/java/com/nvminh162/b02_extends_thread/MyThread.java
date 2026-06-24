package com.nvminh162.b02_extends_thread;

// Cách 1: tạo class kế thừa Thread
public class MyThread extends Thread {

    @Override
    public void run() {
        // Code trong run() là công việc thread sẽ làm
        System.out.println("MyThread running in: " + Thread.currentThread().getName());
    }
}