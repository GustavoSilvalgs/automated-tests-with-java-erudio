package br.com.erudio.constructor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PaymentProcessorTest {

    @Test
    void mockObjectConstruction() {

        System.out.println(new PaymentProcessor().chargeCustomer("42", BigDecimal.valueOf(42)));

        try(MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {

            PaymentProcessor paymentProcessor= new PaymentProcessor();

            when(paymentProcessor.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            Assertions.assertEquals(BigDecimal.TEN, paymentProcessor.chargeCustomer("42", BigDecimal.valueOf(42)));
        }

        System.out.println(new PaymentProcessor().chargeCustomer("42", BigDecimal.valueOf(42)));
    }

    @Test
    void mockDifferentObjectConstruction() {
        try(MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {

            PaymentProcessor firstInstance = new PaymentProcessor("Paypal", BigDecimal.TEN);
            PaymentProcessor secondInstance = new PaymentProcessor(true ,"Paypal", BigDecimal.TEN);

            when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
            when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            Assertions.assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            Assertions.assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            assertEquals(2, mocked.constructed().size());
        }
    }

    @Test
    void mockDifferentObjectConstructionWithAnswer() {
        try(MockedConstruction<PaymentProcessor> mocked = mockConstructionWithAnswer(PaymentProcessor.class,
                invocation -> new BigDecimal("500.00"),
                invocation -> new BigDecimal("42.00"))) {

            PaymentProcessor firstInstance = new PaymentProcessor();
            PaymentProcessor secondInstance = new PaymentProcessor();
            PaymentProcessor thirdInstance = new PaymentProcessor();

            Assertions.assertEquals(new BigDecimal("500.00"), firstInstance.chargeCustomer("42", BigDecimal.ZERO));
            Assertions.assertEquals(new BigDecimal("42.00"), secondInstance.chargeCustomer("42", BigDecimal.ZERO));
            Assertions.assertEquals(new BigDecimal("42.00"), thirdInstance.chargeCustomer("42", BigDecimal.ZERO));
        }
    }
}
