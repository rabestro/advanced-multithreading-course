package com.epam.engx.jam.task5;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public record Producer(Buffer buffer) {

    public void produce() {
        synchronized (buffer) {
            if (buffer.isFull()) {
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int element = ThreadLocalRandom.current().nextInt();
            buffer.push(element);
            buffer.notify();
        }
    }
}
