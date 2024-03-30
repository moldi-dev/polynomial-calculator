package org.moldidev.model;

import org.moldidev.interfaces.PolynomialOperations;

import java.util.*;

public class Polynomial implements PolynomialOperations {
    // Map's key: the power of x
    // Map's value: the coefficient of x
    // e.g: the monomial -2x^3 has the key 3 and value -2
    private TreeMap<Integer, Integer> polynomialMap = new TreeMap<>();

    public Polynomial() {

    }

    public Polynomial(String string) {
        stringToPolynomial(string);
    }

    public TreeMap<Integer, Integer> getPolynomialMap() {
        return this.polynomialMap;
    }

    public void setPolynomialMap(TreeMap<Integer, Integer> polynomialMap) {
        this.polynomialMap = polynomialMap;
    }

    /*
     * @return String
     *
     * Convert the TreeMap representation of a polynomial into a readable string
     * e.g. {0=1, 1=-1, 2=5} => 5x^2 - x + 1
     */
    public String polynomialToString() {
        StringBuilder result = new StringBuilder();

        // Traverse the polynomial map from the end towards the beginning in order to show first the monomials with the highest degree
        for (Map.Entry<Integer, Integer> monomial : this.polynomialMap.descendingMap().entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            // Skip the terms with a zero coefficient
            if (coefficient == 0) {
                continue;
            }

            // Append the sign with no spaces if we print the first monomial
            if (result.isEmpty()) {
                if (coefficient < 0) {
                    result.append("-");
                }
            }

            // Append the sign with spaces to pretty print the rest of the polynomial
            else {
                if (coefficient < 0) {
                    result.append(" - ");
                }

                else if (!result.isEmpty()) {
                    result.append(" + ");
                }
            }

            // Append the coefficient if not 1 or -1, or if it's the constant term
            if (Math.abs(coefficient) != 1 || power == 0) {
                result.append(Math.abs(coefficient));
            }

            // Append 'x' if power is greater than 0
            if (power > 0) {
                result.append("x");
            }

            // Append '^' and the power if power is greater than 1
            if (power > 1) {
                result.append("^").append(power);
            }
        }

        // If the final result is an empty string, append a 0 to it
        if (result.isEmpty()) {
            result.append("0");
        }

        // If the first character from the result string is a '+' sign, remove it
        if (result.toString().toCharArray()[0] == '+') {
            result.deleteCharAt(0);
        }

        // Return the result string
        return result.toString();
    }

    /*
     * @param string
     *
     * Parse an input string and convert it into a TreeMap representation
     * e.g. x^2 - 5x + 4 => {0=4, 1=-5, 2=1}
     */
    private void stringToPolynomial(String string) {
        // Remove all the spaces from the input string
        string = string.replaceAll("\\s", "");

        // Get the signs of the coefficients in the input string
        List<Character> coefficientSigns = new ArrayList<>();

        // The firstCharacter variable is used for appending the correct sign of the first monomial in the input string
        // If the very first character in the input string is a '-' sign, then the first given monomial has a negative coefficient
        // Otherwise, if the sign is not specified or if it is a plus, the first monomial is considered positive
        boolean firstCharacter = true;

        for (char character : string.toCharArray()) {
            if (firstCharacter) {
                if (character == '-') {
                    coefficientSigns.add('-');
                }

                else {
                    coefficientSigns.add('+');
                }

                firstCharacter = false;
            }

            else if (character == '+') {
                coefficientSigns.add('+');
            }

            else if (character == '-') {
                coefficientSigns.add('-');
            }
        }

        // Store the current index of the signs list
        int i = 0;

        // Monomial input possibilities:
        // [1]: ax^n -> coefficient = a, power = n
        // [2]: -ax^n -> coefficient = -a, power = n

        // [3]: x^n -> coefficient = 1, power = n
        // [4]: -x^n -> coefficient = -1, power = n

        // [5]: ax -> coefficient = a, power = 1
        // [6]: -ax -> coefficient = -a, power = 1

        // [7]: x -> coefficient = 1, power = 1
        // [8]: -x -> coefficient = -1, power = 1

        // [9]: a -> coefficient = a, power = 1

        // Split the input string into monomials based on the '+' and '-' signs
        String[] monomials = string.split("[+\\-]");

        for (String monomial : monomials) {
            if (!monomial.isEmpty()) {
                // Parse the monomial to extract coefficient and power
                int coefficient = 1;
                int power = 0;

                // Cases 1 and 2: the monomial is of type ax^n or -ax^n
                if (monomial.matches("[0-9]+x\\^[0-9]+")) {
                    String[] parts = monomial.split("x\\^");

                    coefficient = Integer.parseInt(parts[0]);
                    power = Integer.parseInt(parts[1]);
                }

                // Cases 3 and 4: monomial is of type x^n or -x^n
                if (monomial.matches("x\\^[0-9]+")) {
                    String[] parts = monomial.split("x\\^");

                    coefficient = 1;
                    power = Integer.parseInt(parts[1]);
                }

                // Cases 5 and 6: monomial is of type ax or -ax
                else if (monomial.matches("[0-9]+x")) {
                    String[] parts = monomial.split("x");

                    coefficient = Integer.parseInt(parts[0]);
                    power = 1;
                }

                // Cases 7 and 8: monomial is of type x or -x
                else if (monomial.equals("x")) {
                    power = 1;
                    coefficient = 1;
                }

                // Case 9: monomial is of type a
                else if (monomial.matches("[0-9]+")) {
                    coefficient = Integer.parseInt(monomial);
                    power = 0;
                }

                if (coefficientSigns.get(i) == '-') {
                    coefficient *= -1;
                }

                // Check if the power already is in the polynomial map and update the new coefficient (e.g. 2x^2 - x^2 => x^2)
                if (this.polynomialMap.containsKey(power)) {
                    int monomialCoefficient = this.polynomialMap.get(power);
                    int newPowerCoefficient = coefficient + monomialCoefficient;

                    this.polynomialMap.remove(power);
                    this.polynomialMap.put(power, newPowerCoefficient);
                }

                // Add a new key, value pair in the polynomial map
                else {
                    this.polynomialMap.put(power, coefficient);
                }

                // Go to the sign of the next coefficient
                i++;
            }
        }
    }
}
