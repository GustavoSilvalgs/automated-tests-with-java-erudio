package br.com.erudio.staticwithparams;

import br.com.erudio.business.mockito.staticwithparams.MyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MyUtilsTest {

    @Test
    void shouldMockStaticMethodWithParams() {
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            mockedStatic.when(
                    () -> MyUtils.getWelcomeMessage(
                        eq("Gustavo"),
                        anyBoolean())).thenReturn("Howdy Gustavo");

            String result = MyUtils.getWelcomeMessage("Gustavo", false);

            assertEquals("Howdy Gustavo", result);
        }
    }
}
