package com.epam.engx.jam.command;

import com.epam.engx.jam.task3.CustomFileVisitor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
public class ScanCommand {

    @ShellMethod("Scans a specified folder and provides detailed statistics")
    public String scan(String path) throws InterruptedException {
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
        thread.join();
        return """
            File count: %,d
            Dirs count: %,d
            Total size: %,d
            """.formatted(
            fileVisitor.getFileCount().get(),
            fileVisitor.getFolderCount(),
            fileVisitor.getTotalSize());
    }
}
