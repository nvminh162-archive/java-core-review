package com.nvminh162.b06_wildcard;

public class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking");
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
