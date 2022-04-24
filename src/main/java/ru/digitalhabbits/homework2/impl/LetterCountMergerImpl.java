package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;

public class LetterCountMergerImpl implements LetterCountMerger {
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        second.keySet().forEach(key -> {
            if (first.containsKey(key))
                first.compute(key, (k, v) -> v + second.get(key));
            else first.put(key, second.get(key));
        });
        return first;
    }
}
