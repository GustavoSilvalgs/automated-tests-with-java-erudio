package br.com.erudio.business.mockito.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.util.UUID;

public class OrderServiceTest {

    OrderService service = new OrderService();
    Object defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");

    @DisplayName("Should Include Random OrderId When NoOrderIdExists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
        // Given / Arrange
        try(MockedStatic<UUID> mockedUuid = mockStatic(UUID.class)) {
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUuid);

            // When / Act
            Order result = service.createOrder("MacBook Pro", 2L, null);

            // Then / Assert
            assertEquals(defaultUuid.toString(), result.getId());
        }
    }
}
