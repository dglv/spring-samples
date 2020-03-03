package ru.dglv.springxmlcore;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileEventLogger implements EventLogger {
    private final String fileName;
    private final String lineSeparator;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        this.lineSeparator = System.getProperty("line.separator");
    }

    private void init() {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new RuntimeException("The application is NOT allowed to write to the file " + file);
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file
                    , event.toString() + lineSeparator
                    , StandardCharsets.UTF_8.name()
                    , true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
