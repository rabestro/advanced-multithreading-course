package com.epam.engx.jam.printer;

import com.epam.engx.jam.task3.CustomFileVisitor;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CustomFileVisitorPrinter implements Printer<CustomFileVisitor> {

    @Override
    public String print(CustomFileVisitor fileVisitor, Locale locale) {
        return """
            File count: %,d
            Dirs count: %,d
            Total size: %,d
            """.formatted(
            fileVisitor.getFileCount().get(),
            fileVisitor.getFolderCount(),
            fileVisitor.getTotalSize());
    }

    public String print(CustomFileVisitor fileVisitor) {
        return print(fileVisitor, Locale.US);
    }
}
