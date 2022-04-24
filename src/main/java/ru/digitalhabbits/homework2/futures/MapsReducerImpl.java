package ru.digitalhabbits.homework2.futures;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsReducerImpl implements MapsReducer{

    private final ExecutorService executorService;
    private final LetterCountMerger merger;

    public MapsReducerImpl(LetterCountMerger merger) {
        this(merger, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1));
    }

    public MapsReducerImpl(LetterCountMerger merger, ExecutorService executorService) {
        this.executorService = executorService;
        this.merger = merger;
    }

    @Override
    public CompletableFuture<Map<Character, Long>> apply(CompletableFuture<Map<Character, Long>> first,
                                                         CompletableFuture<Map<Character, Long>> second) {
        return first.thenCombineAsync(second, merger::merge, executorService);
    }
}
