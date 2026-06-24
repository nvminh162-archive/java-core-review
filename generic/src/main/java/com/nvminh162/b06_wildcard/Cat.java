package com.nvminh162.b06_wildcard;

public class Cat extends Animal {
    public void meow() {
        System.out.println("Cat is meowing");
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
