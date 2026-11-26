package ua.dut.calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.print("Перше число (або exit): ");
            String first = scanner.nextLine();
            if (first.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("Друге число: ");
            String second = scanner.nextLine();
            System.out.print("Операція (+ - * / sqrt): ");
            String op = scanner.nextLine().trim();

            try {
                double a = calculator.parse(first);
                double b = op.equals("sqrt") ? 0 : calculator.parse(second);
                double result = calculator.calculate(a, b, op);
                System.out.println("Результат: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Помилка обчислення: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Некоректне введення: " + e.getMessage());
            } finally {
                System.out.println("Обчислення завершено.\n");
            }
        }
        System.out.println("Роботу калькулятора завершено");
    }
}
