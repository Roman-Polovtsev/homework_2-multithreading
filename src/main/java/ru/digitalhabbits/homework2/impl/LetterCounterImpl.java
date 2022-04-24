package ru.digitalhabbits.homework2.impl;

import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

public class LetterCounterImpl implements LetterCounter {

    @SneakyThrows
    @Override
    public Map<Character, Long> count(String input) {
        Map<Character, Long> map = new HashMap<>();
        input.chars().forEach(symbol -> map.compute((char) symbol, (k, v) -> (v == null) ? 1L : (++v)));
        return map;
    }
}
