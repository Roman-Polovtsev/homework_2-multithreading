package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;

public class LoggingLetterCounterMerger implements LetterCountMerger {

    private final LetterCountMerger delegate;

    public LoggingLetterCounterMerger(LetterCountMerger delegate) {
        this.delegate = delegate;
    }

    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        System.out.println("Letter counter merger thread: " + Thread.currentThread().getName());
        return delegate.merge(first, second);
    }
}
