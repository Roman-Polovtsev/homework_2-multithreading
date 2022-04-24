package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LetterCounterImplTest {

    @Test
    void checkCountingLettersCorrect() {
        String input = "abca";
        LetterCounter counter = new LetterCounterImpl();

        Map<Character, Long> actual = counter.count(input);

        assertEquals(expected(), actual);
    }

    private Map<Character, Long> expected() {
        Map<Character, Long> map = new HashMap<>();
        map.put('a', 2L);
        map.put('b', 1L);
        map.put('c', 1L);
        return map;
    }
}