package com.nvminh162.b06_race_condition_synchronized;

// Counter an toàn hơn vì dùng synchronized
public class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}