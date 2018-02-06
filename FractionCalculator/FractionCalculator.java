/*
 Microsoft: DEV277x - Object Oriented Programming in Java Edx Course Module 2 Project
 ***********************
 * Fraction Calculator *
 ***********************
 Author: Emma Byrne
 */
package FractionCalculator;
import java.util.Scanner;

public class FractionCalculator {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("This program is the fraction calculator.\nIt will add, subtract, multiply and" +
                " divide fractions until you type Q to quit.\nPlease enter your fractions in the form a/b, where" +
                " a and b are integers.\n--------------------------------------------------------------------------");

        char operator = getOperation(); // gets what operation the user wants to do
        Fraction frac1, frac2; // where the user's fractions will be stored
        while (operator != 'Q') {
            input.nextLine(); // this is to skip the rest of the line as getOperator() only gets the first character in the line,
            // meaning that the rest of that line would be input into the first getFraction call
            frac1 = getFraction();
            frac2 = getFraction();

            switch (operator) {
                case '+':
                    System.out.println(frac1 + " + " + frac2 + " = " +frac1.add(frac2)); break;
                case '-':
                    System.out.println(frac1 + " - " + frac2 + " = " +frac1.subtract(frac2)); break;
                case '*':
                    System.out.println(frac1 + " * " + frac2 + " = " +frac1.multiply(frac2)); break;
                case '/':
                    System.out.println(frac1 + " / " + frac2 + " = " +frac1.divide(frac2)); break;
                case '=':
                    System.out.println(frac1 + " = " + frac2 + " is " + frac1.equals(frac2)); break;
                default: break;
            }
            System.out.println("--------------------------------------------------------------------------");
            operator = getOperation();
        }
        System.out.println("The program has ended.");
    }

    public static Character getOperation(){
        System.out.print("Please enter a mathematical operation (+,-,/,*,= or Q to quit): ");
        Character userInput = input.next().charAt(0);
        String validOptions = "+-/*=Q";
        // while the input is not a valid operator
        while (validOptions.indexOf(userInput) == -1){
            System.out.print("Invalid operation. (+,-,/,*,= or Q to quit): ");
            userInput = input.next().charAt(0);
        }

        if (userInput == 'Q'){
            System.out.println("Terminating the program");
            System.exit(0); // terminate the program
        }
        return userInput;
    }

    // Method to check whether the user has inputted a valid fraction or not
    public static Boolean isValidFraction(String input){
        // must be in form a/b - must have a '/' in it
        input = input.trim(); // remove any leading or trailing whitespace
        if (input.contains("/")) {
            String[] values = input.split("/"); // values[0] is the numerator and values[1] is the denominator
            // This try catch block will check if the values are numbers and that they are not empty
            try {
                Integer.parseInt(values[0]);
                int denominator = Integer.parseInt(values[1]);
                return (denominator > 0); // denominator cannot be negative or equal to 0

            } catch (NumberFormatException e) {
                return false; // if the value cannot be parsed, then it is not a number and can't be a valid fraction
            }
        } else {
            return input.matches("-?\\d+"); // Check that the values are numbers - (regex \\d+ checks for digits in a string)
        }
    }

    // Method to get the user input as a fraction and checks if it is valid by calling the above method
    public static Fraction getFraction() {
        System.out.print("Please enter a fraction (a/b) or (a): ");
        String fraction = input.nextLine();
        fraction = fraction.replaceAll("\\s",""); // remove all spaces
        while (!isValidFraction(fraction)){
            System.out.print("Invalid fraction. Please enter your fractions in the form a/b, where" +
                    " a and b are integers and b is not zero: ");
            fraction = input.nextLine();
        }
        String[] values = fraction.split("/");
        if (values.length > 1) { // values.length will be one if the user only entered in a single digit, so we will need to use a different constructor in that case
            int numerator = Integer.parseInt(values[0]);
            int denominator = Integer.parseInt(values[1]);
            return new Fraction(numerator,denominator);
        } else {
            return new Fraction(Integer.parseInt(values[0]));
        }
    }
}