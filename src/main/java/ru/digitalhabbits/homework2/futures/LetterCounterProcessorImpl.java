package ru.digitalhabbits.homework2.futures;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LetterCounterProcessorImpl implements LetterCounterProcessor {

    private final ExecutorService executorService;
    private final LetterCounter counter;

    public LetterCounterProcessorImpl(LetterCounter counter) {
        this(counter, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1));
    }

    public LetterCounterProcessorImpl(LetterCounter counter, ExecutorService executorService) {
        this.executorService = executorService;
        this.counter = counter;
    }

    @Override
    public CompletableFuture<Map<Character, Long>> processLine(String line) {
        return CompletableFuture.supplyAsync(() -> counter.count(line), executorService);
    }
}
