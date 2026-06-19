package com.nvminh162.b03_set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        // =========================
        // 1. HashSet
        // =========================

        Set<String> hashSet = new HashSet<>();

        hashSet.add("Minh");
        hashSet.add("An");
        hashSet.add("Binh");
        hashSet.add("Minh"); // bị bỏ qua vì Set không cho duplicate

        System.out.println("HashSet:");
        System.out.println(hashSet);

        // HashSet không đảm bảo thứ tự in ra giống thứ tự add


        // =========================
        // 2. LinkedHashSet
        // =========================

        Set<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("Minh");
        linkedHashSet.add("An");
        linkedHashSet.add("Binh");
        linkedHashSet.add("Minh"); // vẫn bị bỏ qua

        System.out.println("\nLinkedHashSet:");
        System.out.println(linkedHashSet);

        // LinkedHashSet giữ thứ tự thêm vào


        // =========================
        // 3. TreeSet
        // =========================

        Set<String> treeSet = new TreeSet<>();

        treeSet.add("Minh");
        treeSet.add("An");
        treeSet.add("Binh");
        treeSet.add("Minh"); // vẫn bị bỏ qua

        System.out.println("\nTreeSet:");
        System.out.println(treeSet);

        // TreeSet tự động sắp xếp tăng dần
    }
}
