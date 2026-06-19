package com.nvminh162.b05_queue;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        // =========================
        // 1. Queue bình thường: FIFO
        // =========================
        // FIFO = First In First Out
        // Ai vào trước thì xử lý trước

        Queue<String> taskQueue = new ArrayDeque<>();

        taskQueue.offer("Send email");
        taskQueue.offer("Generate report");
        taskQueue.offer("Backup database");

        System.out.println("Queue:");
        System.out.println(taskQueue);

        // peek(): xem phần tử đầu tiên nhưng không xóa
        System.out.println("Next task: " + taskQueue.peek());

        // poll(): lấy phần tử đầu tiên ra và xóa khỏi queue
        System.out.println("Processing: " + taskQueue.poll());

        System.out.println("After poll:");
        System.out.println(taskQueue);

        // =========================
        // 2. PriorityQueue
        // =========================
        // Không xử lý theo thứ tự add vào
        // Mà xử lý theo độ ưu tiên / thứ tự sort tự nhiên

        Queue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);

        System.out.println("\nPriorityQueue:");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

        // Kết quả: 10, 20, 30
        // Vì PriorityQueue tự ưu tiên số nhỏ hơn trước
    }
}
