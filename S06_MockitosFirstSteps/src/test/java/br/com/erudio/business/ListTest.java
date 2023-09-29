package br.com.erudio.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);

        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);

        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnGustavo() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Gustavo");

        // When / Act & Then / Assert
        assertEquals("Gustavo", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnGustavo() {
        // Given / Arrange
        var list = mock(List.class);

        // If you are using argument matchers, all arguments
        // have tobe provided by matchers
        when(list.get(anyInt())).thenReturn("Gustavo");

        // When / Act & Then / Assert
        assertEquals("Gustavo", list.get(0));
        assertEquals("Gustavo", list.get(anyInt()));
    }
}
