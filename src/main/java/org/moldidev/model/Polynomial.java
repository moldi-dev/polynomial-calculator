package org.moldidev.model;

import org.moldidev.interfaces.PolynomialOperations;

import java.util.*;

public class Polynomial implements PolynomialOperations {
    // Map's key: the power of x
    // Map's value: the coefficient of x
    // e.g: -2x^3 has the key 3 and value -2
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

    @Override
    public String toString() {
        StringBuilder polynomialString = new StringBuilder();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(polynomialMap.entrySet());

        // Iterate through each term in the polynomial map in reverse order so we always print the term with the highest power
        for (int i = entryList.size() - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> term = entryList.get(i);

            // Obtain the current term's coefficient and power
            int coefficient = term.getValue();
            int power = term.getKey();

            // Append the current coefficient
            if (coefficient < 0 && i != 0 && i != entryList.size() - 1) {
                polynomialString.append("- ").append(Math.abs(coefficient));
            }

            else if (coefficient < 0 && i != 0 && i == entryList.size() - 1) {
                polynomialString.append("-").append(Math.abs(coefficient));
            }

            else if (coefficient < 0 && i == 0) {
                polynomialString.append("- ").append(Math.abs(coefficient));
            }

            else {
                if (i != entryList.size() - 1) {
                    polynomialString.append("+ ").append(coefficient);
                }

                else {
                    polynomialString.append(coefficient);
                }
            }

            // Append 'x' if the power is greater than 0
            if (power > 0) {
                polynomialString.append("x");

                // Append '^' and the power if the power is greater than 1
                if (power > 1) {
                    polynomialString.append("^").append(power);
                }
            }

            if (i != 0) {
                polynomialString.append(" ");
            }
        }

        return polynomialString.toString();
    }

    private void stringToPolynomial(String string) {
        // Get the signs of the coefficients in the string
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

        // Split the input string by '+' or '-'
        String[] terms = string.split("\\s*([+-])\\s*");

        // Iterate through each term
        for (String term : terms) {
            // Skip empty terms
            if (term.isEmpty()) {
                continue;
            }

            // Check if the current term contains x (to make the difference between the temrs 7x and 7)
            boolean hasX = term.contains("x");

            // Split each term into coefficient and power
            String[] parts = term.split("x\\^?");
            int coefficient;
            int power;

            // If there is no 'x', it's a constant term
            if (parts.length == 1 && !hasX) {
                coefficient = Integer.parseInt(parts[0]);
                power = 0;

                if (coefficientSigns.get(i) == '-') {
                    coefficient *= -1;
                }
            }

            // If there's an x, it's to the power of 1 implicitly
            else if (parts.length == 1 && hasX) {
                coefficient = Integer.parseInt(parts[0]);
                power = 1;

                if (coefficientSigns.get(i) == '-') {
                    coefficient *= -1;
                }
            }

            // If the power of x is explicitly defined, as in the term 5x^2
            else {
                // Parse coefficient and power
                coefficient = Integer.parseInt(parts[0]);
                power = Integer.parseInt(parts[1]);

                if (coefficientSigns.get(i) == '-') {
                    coefficient *= -1;
                }
            }

            // Add the term to the polynomialMap
            polynomialMap.put(power, coefficient);

            i++;
        }
    }
}
