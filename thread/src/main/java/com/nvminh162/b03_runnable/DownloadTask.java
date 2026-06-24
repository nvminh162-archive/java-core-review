package com.nvminh162.b03_runnable;

// Cách 2: implements Runnable
// Runnable đại diện cho một task/công việc cần chạy
public class DownloadTask implements Runnable {

    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("Downloading " + fileName + " in thread: " + Thread.currentThread().getName());
    }
}
