package app;

import largenumber.Addition;
import largenumber.Arithmetic;
import largenumber.Division;
import largenumber.LargeNumber;
import largenumber.Multiplication;
import largenumber.Subtraction;

import java.util.Scanner;

public class App {
    private static final int DIVISION_DECIMAL_PLACES = 20;
    private static final Arithmetic ADDITION = new Addition();
    private static final Arithmetic SUBTRACTION = new Subtraction();
    private static final Arithmetic MULTIPLICATION = new Multiplication();
    private static final Arithmetic DIVISION = new Division(DIVISION_DECIMAL_PLACES);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                runCalculation(scanner);
            } else if (choice.equals("2")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Large Number Arithmetic Using Doubly Linked List ===");
        System.out.println("1. Enter two integers and calculate");
        System.out.println("2. Exit");
        System.out.print("Choice: ");
    }

    private static void runCalculation(Scanner scanner) {
        LargeNumber m = readLargeNumber(scanner, "Enter m: ");
        LargeNumber n = readLargeNumber(scanner, "Enter n: ");

        System.out.println();
        System.out.println("addition = " + ADDITION.calculate(m, n));
        System.out.println("subtraction = " + SUBTRACTION.calculate(m, n));
        System.out.println("multiplication = " + MULTIPLICATION.calculate(m, n));
        printDivision(m, n);
    }

    private static void printDivision(LargeNumber m, LargeNumber n) {
        try {
            System.out.println("division = " + DIVISION.calculate(m, n));
        } catch (ArithmeticException exception) {
            System.out.println("division = undefined (" + exception.getMessage() + ")");
        }
    }

    private static LargeNumber readLargeNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return LargeNumber.parse(input);
            } catch (IllegalArgumentException exception) {
                System.out.println("Only integers are allowed.");
            }
        }
    }
}
