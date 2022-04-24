package ru.digitalhabbits.homework2.futures;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LetterCounterProcessorImplTest {

    private final LetterCounter counter = mock(LetterCounter.class);

    @Test
    void verifyOneInvocationOfCounterTest() {
        String input = "abc";
        LetterCounterProcessor processor = new LetterCounterProcessorImpl(counter);
        Map<Character, Long> expectedMap = Collections.singletonMap('a', 1L);
        when(counter.count(any())).thenReturn(expectedMap);

        Map<Character, Long> actual = processor.processLine(input).join();

        assertEquals(expectedMap, actual);
        verify(counter, times(1)).count(any());
    }

    @Test
    void verifyOneInvocationOfExecutorTest() {
        String input = "abc";
        ExecutorService executor = mock(ExecutorService.class);
        LetterCounterProcessor processor = new LetterCounterProcessorImpl(counter, executor);
        doNothing().when(executor).execute(any());

        processor.processLine(input);

        verify(executor, times(1)).execute(any());
    }
}