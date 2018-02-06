/*
 Microsoft: DEV277x - Object Oriented Programming in Java Edx Course Module 2 Project
 ******************
 * Fraction Class *
 ******************
 Author: Emma Byrne
 */
package FractionCalculator;

public class Fraction {
    private int numerator;
    private int denominator;

    // A two-parameter constructor
    public Fraction(int numerator, int denominator){
        this.numerator = numerator;
        if (denominator == 0){
            throw new IllegalArgumentException("The denominator must not be 0");
        } else if (denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        } else {
            this.denominator = denominator;
        }
    }

    // A one-parameter constructor
    public Fraction(int numerator){
        this(numerator,1);
    }

    // A no-parameter, default constructor
    public Fraction() {
        this(0,1);
    }

    // Returns the numerator
    public int getNumerator(){
        return this.numerator;
    }

    // Returns the denominator
    public int getDenominator(){
        return this.denominator;
    }

    // Represents the fraction as a string
    public String toString(){
        return this.numerator  + "/" + this.denominator;
    }

    // Converts the fraction to a double
    public double toDouble(){
        return (double)(numerator/denominator);
    }

    // Method to return the greatest common divisor of the two ints
    public static int gcd(int num, int den){
        if (num == 0 || den == 0) {
            return num + den; // base case
        }
        return gcd(den,num % den); // keep dividing until the numerator can be divided by the denominator
    }

    // Method to add two fractions together
    public Fraction add(Fraction other){
        // To add fractions, multiply the denominators of both fractions together
        // Then, multiply first numerator by second denominator and second numerator by first denominator and add the values
        // Then divide num and denom by the gcd
        int commonDenominator = this.denominator * other.numerator;
        int sumOfNumerators = (this.numerator * other.numerator) + (this.denominator * other.numerator);
        int greatestMultiple = gcd(sumOfNumerators,commonDenominator);
        int finalNumerator = sumOfNumerators/greatestMultiple;
        int finalDenominator = commonDenominator/greatestMultiple;

        // Make new fraction out of these values
        return new Fraction(finalNumerator,finalDenominator);
    }

    // Method to subtract one fraction from another
    public Fraction subtract(Fraction other){
        // Same method as add but subtract the numerators
        int commonDenominator = this.denominator * other.numerator;
        int sumOfNumerators = (this.numerator * other.numerator) - (this.denominator * other.numerator);
        int greatestMultiple = gcd(sumOfNumerators,commonDenominator);
        int finalNumerator = sumOfNumerators/greatestMultiple;
        int finalDenominator = commonDenominator/greatestMultiple;

        // Make new fraction out of these values
        return new Fraction(finalNumerator,finalDenominator);
    }

    // Mehtod to multiply two fractions together
    public Fraction multiply(Fraction other){
        // Multiply numerators and denominators, then divide by the greatest common factor
        int multNumerators = this.numerator * other.numerator;
        int multDenominators = this.denominator * other.numerator;
        int greatestMultiple = gcd(multNumerators,multDenominators);
        return new Fraction((multNumerators/greatestMultiple), (multDenominators/greatestMultiple));
    }

    // Method to divide one fraction by another
    public Fraction divide(Fraction other){
        // get inverse of first fraction, multiple by the second one
        int inverseNumerator = this.numerator * other.denominator;
        int inverseDenominator = this.denominator * other.numerator;
        int greatestMultiple = gcd(inverseNumerator,inverseDenominator);
        return new Fraction(inverseNumerator/greatestMultiple,inverseDenominator/greatestMultiple);
    }

    // Method to convert a fraction to its lowest terms
    public void toLowestTerms(){
        int greatestMultiple = gcd(this.numerator,this.denominator); // Get gcd
        this.numerator /= greatestMultiple;
        this.denominator /= greatestMultiple;
        // Make sure that the denominator isn't 0
        if (this.denominator < 0){
            this.numerator *= -1;
            this.denominator += -1;
        }
    }

    // Method which returns true if two fractions are equal, false otherwise
    public boolean equals(Object other){
        // Two fractions are equal if they represent the same number (i.e. 3/6 = 1/2 and -2/3 = 2/-3)
        // Convert to lowest terms, then check if the lowest terms match
        // First check if the input is a fraction or not
        if (other instanceof Fraction){
            Fraction otherFraction = (Fraction)other; // cast other to a fraction so we can access numerator and denominator
            this.toLowestTerms();
            otherFraction.toLowestTerms();
            // This will return true if the fractions match, false otherwise
            return (this.numerator == otherFraction.numerator) && (this.denominator == otherFraction.denominator);
       } else {
            throw new IllegalArgumentException("The object was not a fraction");
        }
    }
}
