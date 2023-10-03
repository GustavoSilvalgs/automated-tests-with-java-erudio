package br.com.erudio.business.mockito.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class OrderServiceTest {

    OrderService service = new OrderService();
    Object defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    LocalDateTime defaultLocalDateTime = LocalDateTime.of(2023, 10, 2, 23, 19);

    @DisplayName("Should include random orderId when no orderId exists")
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

    @DisplayName("Should include current time when create a new order")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder() {
        // Given / Arrange
        try(MockedStatic<LocalDateTime> mockedUuid = mockStatic(LocalDateTime.class)) {
            mockedUuid.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            // When / Act
            Order result = service.createOrder("MacBook Pro", 2L, null);

            // Then / Assert
            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }
}
