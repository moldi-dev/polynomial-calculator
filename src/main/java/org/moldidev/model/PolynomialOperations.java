package org.moldidev.model;

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

    // TODO: implement the differentiatePolynomial method and test it
    static String differentiatePolynomial(Polynomial p) {
        return "NULL";
    }

    // TODO: implement the integratePolynomial method and test it
    static String integratePolynomial(Polynomial p) {
        return "NULL";
    }
}
