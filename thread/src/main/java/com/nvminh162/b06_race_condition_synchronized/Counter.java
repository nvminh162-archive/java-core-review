package com.nvminh162.b06_race_condition_synchronized;

// Counter chưa an toàn trong môi trường nhiều thread
public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
