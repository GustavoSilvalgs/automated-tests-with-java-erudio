package br.com.erudio.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

    @Test
    void testSum() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        Double actual = math.sum(firstNumber, secondNumber);
        double expected = 8.2D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "+" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    void testSubtraction() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 8D;
        double secondNumber = 2D;

        Double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 6D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "-" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    void testMultiplication() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 5D;
        double secondNumber = 5D;

        Double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 25D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "*" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    void testDivision() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 10D;
        double secondNumber = 2D;

        double actual = math.division(firstNumber, secondNumber);
        double expected = 5D;

        Assertions.assertEquals(expected, actual,
                () -> firstNumber + "/" + secondNumber +
                    " did not produce " + expected + "!");
    }

    @Test
    void testMean() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 10D;
        double secondNumber = 10D;

        double actual = math.mean(firstNumber, secondNumber);
        double expected = 10D;

        Assertions.assertEquals(expected, actual,
                () -> "(" + firstNumber + "+" + secondNumber + ")/2" +
                    " did not produce " + expected + "!");
    }

    @Test
    void testSquareRoot() {
        SimpleMath math = new SimpleMath();
        double number = 81D;

        double actual = math.squareRoot(number);
        double expected = 9D;

        Assertions.assertEquals(expected, actual,
                () -> "Square root of " + number +
                    " did not produce " + expected + "!");
    }

}
