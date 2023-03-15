package com.epam.engx.jam.command;

import com.epam.engx.jam.task5.Buffer;
import com.epam.engx.jam.task5.Consumer;
import com.epam.engx.jam.task5.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ProdConsCommand {
    private final Buffer buffer;
    private final Producer producer;
    private final Consumer consumer;

    @ShellMethod
    public void prodcons() throws InterruptedException {
        buffer.clear();

        var consumerThread = new Thread(consumeTask(45));
        var producerThread = new Thread(produceTask(50));

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer: " + buffer.count());
    }

    private Runnable produceTask(int elements) {
        return () -> {
            for (int i = 0; i < elements; i++) {
                producer.produce();
            }
            System.out.println("Done producing");
        };
    }

    private Runnable consumeTask(int elements) {
        return () -> {
            for (int i = 0; i < elements; i++) {
                consumer.consume();
            }
            System.out.println("Done consuming");
        };
    }
}
