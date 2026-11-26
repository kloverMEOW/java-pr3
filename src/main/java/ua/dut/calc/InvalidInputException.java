package ua.dut.calc;

// власний виняток для некоректних даних (не число, корінь з від'ємного і т.д.)
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
