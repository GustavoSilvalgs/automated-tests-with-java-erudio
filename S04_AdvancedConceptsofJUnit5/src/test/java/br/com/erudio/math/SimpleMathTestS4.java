package br.com.erudio.math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTestS4 {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method!");
    }

    @DisplayName("Test 6.2 / 2 = 3.1")
    @ParameterizedTest
//    @MethodSource("testDivisionInputParameters")
    @MethodSource()
    void testDivision(double firstNumber, double secondNumber, double expected) {

        System.out.println("Test " + firstNumber + " / " + secondNumber + " = " + expected + "!");
        double actual = math.division(firstNumber, secondNumber);

        Assertions.assertEquals(expected, actual, 2D,
                () -> firstNumber + "/" + secondNumber +
                    " did not produce " + expected + "!");
    }

//    public static Stream<Arguments> testDivisionInputParameters() {
    public static Stream<Arguments> testDivision() {
        return Stream.of(
          Arguments.of(6.2D, 2D, 3.1D),
          Arguments.of(71D, 14D, 5.07D),
          Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }

}
