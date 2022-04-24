package ru.digitalhabbits.homework2.futures;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface LetterCounterProcessor {

    CompletableFuture<Map<Character, Long>> processLine(String line);
}
