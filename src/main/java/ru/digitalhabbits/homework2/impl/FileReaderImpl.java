package ru.digitalhabbits.homework2.impl;

import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {
    @Override
    @SneakyThrows
    public Stream<String> readLines(File file) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            Stream.Builder<String> streamBuilder = Stream.builder();
            String line;
            while ((line = reader.readLine()) != null)
                streamBuilder.add(line);
            return streamBuilder.build();
        }
    }

}
