package com.epam.engx.jam.command;

import com.epam.engx.jam.task3.CustomFileVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
@RequiredArgsConstructor
public class ScanCommand {

    @ShellMethod("Scans a specified folder and provides detailed statistics")
    public String scan(String path) throws IOException {
        var dir = Paths.get(path);
        var fileVisitor = new CustomFileVisitor();

        Files.walkFileTree(dir, fileVisitor);

        return """
            File count: %,d
            Dirs count: %,d
            Total size: %,d
            """.formatted(
            fileVisitor.getFileCount(),
            fileVisitor.getFolderCount(),
            fileVisitor.getTotalSize());
    }
}
