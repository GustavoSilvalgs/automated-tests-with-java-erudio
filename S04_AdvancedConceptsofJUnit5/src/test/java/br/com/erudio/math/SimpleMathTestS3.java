package br.com.erudio.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTestS3 {

    SimpleMath math;

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method!");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @BeforeAll method!");
    }

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method!");
    }

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() {
        // AAA Arrange, Act, Assert
        // Given / Arrange

        System.out.println("Test 6.2 + 2 = 8.2");

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        // When / Act
        Double actual = math.sum(firstNumber, secondNumber);

        // Then / Assert
        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "+" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtraction() {

        System.out.println("Test 6.2 - 2 = 4.2");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 4.2D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "-" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 6.2 * 2 = 12.4")
    void testMultiplication() {

        System.out.println("Test 6.2 * 2 = 12.4");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 12.4D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "*" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 6.2 / 2 = 3.1")
    void testDivision() {

        System.out.println("Test 6.2 / 2 = 3.1");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.division(firstNumber, secondNumber);
        double expected = 3.1D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "/" + secondNumber +
                    " did not produce " + expected + "!");
    }

    //@Disabled("TODO: We need still work on it!")
    @Test
    @DisplayName("Test Division by Zero")
    void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException() {

        // given
        double firstNumber = 6.2D;
        double secondNumber = 0D;

        var expectedMessage = "Impossible to divide by zero!";

        // when & then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            // when & then
            math.division(firstNumber, secondNumber);
        }, ()-> "Division by zero should throw an ArithmeticException");

        assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message!");
    }

    @Test
    @DisplayName("Test (6.2 + 2) / 2 = 4.1")
    void testMean() {

        System.out.println("Test (6.2 + 2) / 2 = 4.1");

        double firstNumber = 6.2D;
        double secondNumber = 2D;

        double actual = math.mean(firstNumber, secondNumber);
        double expected = 4.1D;

        Assertions.assertEquals(expected, actual,
                () -> "(" + firstNumber + "+" + secondNumber + ")/2" +
                    " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test Square Root of 81 = 9")
    void testSquareRoot() {

        System.out.println("Test Square Root of 81 = 9");

        double number = 81D;

        double actual = math.squareRoot(number);
        double expected = 9D;

        Assertions.assertEquals(expected, actual,
                () -> "Square root of " + number +
                    " did not produce " + expected + "!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @Test
    @DisplayName("Display Name")
    void testABCD_when_XYZ_Should() {

        System.out.println("Test BDD");

        // Given / Arrange
        // When / Act
        // Then / Assert
    }

}
