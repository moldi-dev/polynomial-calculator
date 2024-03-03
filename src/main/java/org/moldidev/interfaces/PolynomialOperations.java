package org.moldidev.interfaces;

import org.moldidev.model.Polynomial;

import java.util.Map;
import java.util.TreeMap;

public interface PolynomialOperations {
    static String addPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the terms of the first polynomial
        for (Map.Entry<Integer, Integer> term : pMap.entrySet()) {
            int power = term.getKey();
            int coefficient = term.getValue();

            // Add the term to the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Iterate through the terms of the second polynomial
        for (Map.Entry<Integer, Integer> term : qMap.entrySet()) {
            int power = term.getKey();
            int coefficient = term.getValue();

            // Add the term to the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    static String subtractPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the terms of the first polynomial
        for (Map.Entry<Integer, Integer> term : pMap.entrySet()) {
            int power = term.getKey();
            int coefficient = term.getValue();

            // Subtract the term from the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Iterate through the terms of the second polynomial with opposite signs
        for (Map.Entry<Integer, Integer> term : qMap.entrySet()) {
            int power = term.getKey();
            int coefficient = -term.getValue(); // Subtract instead of add

            // Subtract the term from the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    static String multiplyPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the terms of the first polynomial
        for (Map.Entry<Integer, Integer> termP : pMap.entrySet()) {
            int powerP = termP.getKey();
            int coefficientP = termP.getValue();

            // Iterate through the terms of the second polynomial
            for (Map.Entry<Integer, Integer> termQ : qMap.entrySet()) {
                int powerQ = termQ.getKey();
                int coefficientQ = termQ.getValue();

                // Multiply the coefficients and add to the result polynomial
                int powerResult = powerP + powerQ;
                int coefficientResult = coefficientP * coefficientQ;

                result.getPolynomialMap().put(powerResult, result.getPolynomialMap().getOrDefault(powerResult, 0) + coefficientResult);
            }
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    // TODO: implement the dividePolynomials method and test it
    static String dividePolynomials(Polynomial p, Polynomial q) {
        return "NULL";
    }

    static String differentiatePolynomial(Polynomial p) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representation of the given polynomial
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();

        // Polynomials differentiation:
        // [1]: ax^n -> (n*a)x^(n-1) => if power > 0, then coefficient = coefficient * power, power -= 1
        // [2]: a -> 0 => if power == 0, coefficient = 0

        // Iterate through the terms of the given polynomial
        for (Map.Entry<Integer, Integer> term : pMap.entrySet()) {
            int power = term.getKey();
            int coefficient = term.getValue();

            // Case 1: ax^n
            if (power > 0) {
                coefficient = coefficient * power;
                power -= 1;
            }

            else if (power == 0) {
                coefficient = 0;
            }

            result.getPolynomialMap().put(power, coefficient);
        }

        return result.polynomialToString();
    }

    static String integratePolynomial(Polynomial p) {
        StringBuilder result = new StringBuilder();

        // Get the TreeMap representation of the given polynomial
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();

        // Polynomials integration:
        // newPower = power + 1, coefficient = coefficient / newPower

        // Iterate through the terms of the given polynomial in reverse order
        for (Map.Entry<Integer, Integer> term : pMap.descendingMap().entrySet()) {
            int power = term.getKey();
            int coefficient = term.getValue();

            int newPower = power + 1;
            double newCoefficient = (double)coefficient / newPower;

            // Check if the new coefficient is negative
            if (newCoefficient < 0) {
                result.append("-");
            }

            // If the new coefficient is positive
            else {
                // If the result string isn't empty, meaning we don't print the first coefficient
                if (!result.isEmpty()) {
                    result.append("+");
                }
            }

            // Append the new coefficient
            if (Math.abs(newCoefficient) != 1) {
                if (Math.abs(newCoefficient) != (int)Math.abs(newCoefficient)) {
                    result.append(String.format("%.3f", Math.abs(newCoefficient)));
                }

                else {
                    result.append((int)Math.abs(newCoefficient));
                }
            }

            // Append the 'x'
            result.append("x");

            // Append '^newPower' if newPower > 1
            if (newPower > 1) {
                result.append('^');
                result.append(newPower);
            }
        }

        // Finally, append the +C from the integration
        result.append("+C");

        return result.toString();
    }
}
