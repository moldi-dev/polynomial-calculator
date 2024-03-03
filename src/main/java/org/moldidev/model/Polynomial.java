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

    public String polynomialToString() {
        StringBuilder result = new StringBuilder();

        // Store the reverse map of the polynomial in a list
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(polynomialMap.entrySet());

        // Traverse the polynomial map from the end towards the beginning in order to show first the monomials with the highest degree
        for (int i = entryList.size() - 1; i >= 0; i--) {
            int power = entryList.get(i).getKey();
            int coefficient = entryList.get(i).getValue();

            // If the current term is not a free term and has the coefficient 0, go to the next term
            if (coefficient == 0 && power != 0) {
                continue;
            }

            // Append the sign
            if (coefficient < 0) {
                result.append("-");
            }

            else if (i < entryList.size() - 1) {
                result.append("+");
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

    private void stringToPolynomial(String string) {
        // Remove all the spaces from the input string
        string = string.replaceAll("\\s", "");

        // Get the signs of the coefficients in the input string
        List<Character> coefficientSigns = new ArrayList<>();

        for (int i = 0; i < string.toCharArray().length; i++) {
            char c = string.toCharArray()[i];

            if (i == 0) {
                if (c == '-') {
                    coefficientSigns.add('-');
                }

                else {
                    coefficientSigns.add('+');
                }
            }

            else {
                if (c == '-') {
                    coefficientSigns.add('-');
                }

                else if (c == '+'){
                    coefficientSigns.add('+');
                }
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

                // Update the polynomial map
                polynomialMap.put(power, coefficient);

                // Go to the sign of the next coefficient
                i++;
            }
        }
    }
}
