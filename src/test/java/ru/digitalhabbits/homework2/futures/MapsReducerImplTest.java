package ru.digitalhabbits.homework2.futures;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MapsReducerImplTest {

    @Test
    void verifyMergerInvokedOnceTest() {
        LetterCountMerger merger = mock(LetterCountMerger.class);
        MapsReducer reducer = new MapsReducerImpl(merger);
        Map<Character, Long> expectedResult = Collections.emptyMap();
        when(merger.merge(any(), any())).thenReturn(expectedResult);

        Map<Character, Long> actual = reducer.apply(
                CompletableFuture.completedFuture(Collections.singletonMap('c', 2L)),
                CompletableFuture.completedFuture(Collections.singletonMap('b', 3L))).join();

        verify(merger, times(1)).merge(any(), any());
        assertEquals(expectedResult, actual);
    }

    @Test
    void verifyExecutorInvokedOnceTest() {
        LetterCountMerger merger = mock(LetterCountMerger.class);
        ExecutorService executor = mock(ExecutorService.class);
        MapsReducer reducer = new MapsReducerImpl(merger, executor);
        doNothing().when(executor).execute(any());

        reducer.apply(
                CompletableFuture.completedFuture(Collections.singletonMap('c', 2L)),
                CompletableFuture.completedFuture(Collections.singletonMap('b', 3L)));

        verify(executor, times(1)).execute(any());
    }
}