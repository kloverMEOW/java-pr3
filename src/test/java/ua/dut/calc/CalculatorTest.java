package ua.dut.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, +, 5",
            "10, 4, -, 6",
            "2.5, 4, *, 10",
            "9, 3, /, 3"
    })
    void basicOperations(double a, double b, String op, double expected) throws InvalidInputException {
        assertEquals(expected, calculator.calculate(a, b, op), 1e-9);
    }

    @Test
    void divisionByZeroThrows() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.div(5, 0));
        assertEquals("Ділення на нуль", e.getMessage());
    }

    @Test
    void divisionByZeroThroughCalculate() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate(1, 0, "/"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a", "", "  ", "1..2"})
    void invalidStringsThrowCustomException(String input) {
        assertThrows(InvalidInputException.class, () -> calculator.parse(input));
    }

    @Test
    void parseAcceptsComma() throws InvalidInputException {
        assertEquals(3.5, calculator.parse("3,5"));
    }

    @Test
    void sqrtOfNegativeThrowsCustomException() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> calculator.sqrt(-4));
        assertTrue(e.getMessage().contains("-4"));
    }

    @Test
    void sqrtOfPositive() throws InvalidInputException {
        assertEquals(4.0, calculator.sqrt(16));
    }

    @Test
    void unknownOperationThrowsCustomException() {
        assertThrows(InvalidInputException.class, () -> calculator.calculate(1, 2, "%"));
    }
}
