package com.epam.engx.jam.task5;

import org.springframework.stereotype.Component;

@Component
public record Consumer(Buffer buffer) {

    public void consume() {
        synchronized (buffer) {
            if (buffer.isEmpty()) {
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer.get();
            buffer.notify();
        }
    }
}
