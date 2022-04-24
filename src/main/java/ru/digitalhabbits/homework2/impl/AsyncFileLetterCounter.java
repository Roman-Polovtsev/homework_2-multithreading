package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.futures.LetterCounterProcessor;
import ru.digitalhabbits.homework2.futures.LetterCounterProcessorImpl;
import ru.digitalhabbits.homework2.futures.MapsReducer;
import ru.digitalhabbits.homework2.futures.MapsReducerImpl;

import java.io.File;
import java.util.Map;

//todo Make your impl
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final LetterCounterProcessor letterCounterProcessor;
    private final MapsReducer reducer;

    public AsyncFileLetterCounter() {
        this(new FileReaderImpl(),
                new LetterCounterProcessorImpl(new LetterCounterImpl()),
                new MapsReducerImpl(new LetterCountMergerImpl()));
    }

    public AsyncFileLetterCounter(FileReader fileReader, LetterCounterProcessor letterCounterProcessor, MapsReducer reducer) {
        this.fileReader = fileReader;
        this.letterCounterProcessor = letterCounterProcessor;
        this.reducer = reducer;
    }

    @Override
    public Map<Character, Long> count(File input) {
        return fileReader.readLines(input)
                .map(letterCounterProcessor::processLine)
                .reduce(reducer::apply)
                .orElseThrow(() -> new IllegalStateException("Smth went wrong! Cannot aggregate result maps")).join();
    }
}
