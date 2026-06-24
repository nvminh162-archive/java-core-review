package com.nvminh162.b02_generic_class;

// T là type parameter
// T có thể là String, Integer, User, Product...
public class Box<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
