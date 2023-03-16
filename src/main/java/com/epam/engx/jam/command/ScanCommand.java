package com.epam.engx.jam.command;

import com.epam.engx.jam.printer.CustomFileVisitorPrinter;
import com.epam.engx.jam.task3.CustomFileVisitor;
import com.epam.engx.jam.validator.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class ScanCommand {
    private static final int TIMEOUT_MILLIS = 500;
    private final CustomFileVisitorPrinter printer;

    @ShellMethod("Scans a specified folder and provides detailed statistics")
    public String scan(@Folder String path) throws InterruptedException {
        var dir = Paths.get(path);
        var fileVisitor = new CustomFileVisitor();

        var thread = new Thread(() -> {
            try {
                Files.walkFileTree(dir, fileVisitor);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        var scanner = new Scanner(System.in);

        while (thread.isAlive()) {
            System.out.printf("Processed: %,d%n", fileVisitor.getFileCount().get());
            if (scanner.hasNext() && scanner.next().equals("s")) {
                thread.interrupt();
                System.out.println("Interrupting...");
            }
            Thread.sleep(TIMEOUT_MILLIS);
        }
        thread.join();

        return thread.isInterrupted() ? "Scan interrupted." : printer.print(fileVisitor);
    }
}
