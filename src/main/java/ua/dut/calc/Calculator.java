package ua.dut.calc;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Не можна взяти корінь з від'ємного числа: " + a);
        }
        return Math.sqrt(a);
    }

    // перетворення рядка в число з перевіркою
    public double parse(String text) throws InvalidInputException {
        if (text == null || text.isBlank()) {
            throw new InvalidInputException("Порожнє значення");
        }
        try {
            return Double.parseDouble(text.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Це не число: " + text);
        }
    }

    public double calculate(double a, double b, String op) throws InvalidInputException {
        switch (op) {
            case "+":
                return add(a, b);
            case "-":
                return sub(a, b);
            case "*":
                return mul(a, b);
            case "/":
                return div(a, b);
            case "sqrt":
                return sqrt(a);
            default:
                throw new InvalidInputException("Невідома операція: " + op);
        }
    }
}
