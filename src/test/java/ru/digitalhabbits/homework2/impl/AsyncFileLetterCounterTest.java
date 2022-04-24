package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.futures.LetterCounterProcessor;
import ru.digitalhabbits.homework2.futures.MapsReducer;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AsyncFileLetterCounterTest {

    @Test
    public void verifyNumberOfSubmodulesInvocationsTest() {
        File file = mock(File.class);
        FileReader reader = mock(FileReader.class);
        LetterCounterProcessor lineProcessor = mock(LetterCounterProcessor.class);
        MapsReducer reducer = mock(MapsReducer.class);
        FileLetterCounter counter = new AsyncFileLetterCounter(reader, lineProcessor, reducer);
        Stream<String> mockFileStream = Stream.of("abc", "def");
        Map<Character, Long> mockMap = Collections.singletonMap('a', 1L);
        CompletableFuture<Map<Character, Long>> mockMapResult = CompletableFuture.completedFuture(mockMap);
        when(reader.readLines(any())).thenReturn(mockFileStream);
        when(lineProcessor.processLine(any())).thenReturn(mockMapResult);
        when(reducer.apply(any(), any())).thenReturn(mockMapResult);

        Map<Character, Long> result = counter.count(file);

        verify(reader, times(1)).readLines(file);
        verify(lineProcessor, times(2)).processLine(any());
        verify(reducer, times(1)).apply(any(), any());
        assertEquals(mockMap, result);
    }

}