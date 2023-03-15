package com.epam.engx.jam.task3;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public final class CustomFileVisitor implements FileVisitor<Path> {
    private final Map<String, Long> fileTypes = new HashMap<>();
    private final AtomicLong fileCount = new AtomicLong(0);
    private long folderCount;
    private long totalSize;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        ++folderCount;
        return visitResult();
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fileCount.getAndIncrement();
        totalSize += attrs.size();
        var fileType = Files.probeContentType(file);
        fileTypes.merge(fileType, 1L, Long::sum);

        return visitResult();
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return visitResult();
    }

    private FileVisitResult visitResult() {
        return Thread.currentThread().isInterrupted()
            ? FileVisitResult.TERMINATE
            : FileVisitResult.CONTINUE;
    }
}
