package ru.digitalhabbits.homework2.futures;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface MapsReducer {

    CompletableFuture<Map<Character, Long>> apply(CompletableFuture<Map<Character, Long>> first,
                                                  CompletableFuture<Map<Character, Long>> second);
}
