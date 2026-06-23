package com.nvminh162.b00_common;

import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean active;
    private String department;
    private double salary;
    private List<String> skills;

    public User(int id, String name, int age, boolean active, String department, double salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
        this.department = department;
        this.salary = salary;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name + " - " + age + " - " + department + " - " + salary;
    }
}
