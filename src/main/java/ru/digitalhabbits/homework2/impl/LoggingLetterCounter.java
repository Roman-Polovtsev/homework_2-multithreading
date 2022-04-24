package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

public class LoggingLetterCounter implements LetterCounter {
    private final LetterCounter delegate;

    public LoggingLetterCounter(LetterCounter delegate) {
        this.delegate = delegate;
    }

    @Override
    public Map<Character, Long> count(String input) {
        System.out.println("Letter counter thread: " + Thread.currentThread().getName());
        return delegate.count(input);
    }
}
