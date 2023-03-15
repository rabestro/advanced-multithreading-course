package com.epam.engx.jam.task5;

import org.springframework.stereotype.Component;

@Component
public final class Buffer {
    private static final int CAPACITY = 10;
    private final int[] storage = new int[CAPACITY];
    private int count;

    public int count() {
        return count;
    }

    public void clear() {
        count = 0;
    }

    void push(int element) {
        storage[count++] = element;
    }

    int get() {
        return storage[--count];
    }

    boolean isEmpty() {
        return count == 0;
    }

    boolean isFull() {
        return count == storage.length;
    }
}
