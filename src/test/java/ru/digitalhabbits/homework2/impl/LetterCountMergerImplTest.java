package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LetterCountMergerImplTest {

    @Test
    void checkMergingCorrectTest() {
        LetterCountMerger merger = new LetterCountMergerImpl();
        expectedResult();

        Map<Character, Long> actual = merger.merge(first(), second());

        assertEquals(expectedResult(), actual);
    }

    private Map<Character, Long> expectedResult() {
        Map<Character, Long> expected = new HashMap<>();
        expected.put('a', 3L);
        expected.put('b', 5L);
        expected.put('c', 7L);
        return expected;
    }

    private Map<Character, Long> second() {
        Map<Character, Long> secondMap = new HashMap<>();
        secondMap.put('a', 2L);
        secondMap.put('b', 3L);
        secondMap.put('c', 4L);
        return secondMap;
    }

    private Map<Character, Long> first() {
        Map<Character, Long> firstMap = new HashMap<>();
        firstMap.put('a', 1L);
        firstMap.put('b', 2L);
        firstMap.put('c', 3L);
        return firstMap;
    }

}