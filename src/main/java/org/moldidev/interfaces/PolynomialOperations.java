package org.moldidev.interfaces;

import org.moldidev.model.Polynomial;

import java.util.Map;
import java.util.TreeMap;

public interface PolynomialOperations {
    /*
     * @param p
     * @param q
     * @return String
     *
     * Add two polynomials and return a readable string
     * e.g. P(x) = x^2 + 2x, Q(x) = 2x + 4 => x^2 + 4x + 4
     */
    static String addPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the monomials of the first polynomial
        for (Map.Entry<Integer, Integer> monomial : pMap.entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            // Add the term to the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Iterate through the monomials of the second polynomial
        for (Map.Entry<Integer, Integer> monomial : qMap.entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            // Add the term to the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    /*
     * @param p
     * @param q
     * @return String
     *
     * Subtract two polynomials and return a readable string
     * e.g. P(x) = x^2 + 2x, Q(x) = 2x + 4 => x^2 - 4
     */
    static String subtractPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the monomials of the first polynomial
        for (Map.Entry<Integer, Integer> monomial : pMap.entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            // Subtract the term from the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Iterate through the monomials of the second polynomial with opposite signs
        for (Map.Entry<Integer, Integer> monomial : qMap.entrySet()) {
            int power = monomial.getKey();
            int coefficient = -monomial.getValue(); // Subtract instead of add

            // Subtract the term from the result polynomial
            result.getPolynomialMap().put(power, result.getPolynomialMap().getOrDefault(power, 0) + coefficient);
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    /*
     * @param p
     * @param q
     * @return String
     *
     * Multiply two polynomials and return a readable string
     * e.g. P(x) = x + 1, Q(x) = x + 1 => x^2 + 2x + 1
     */
    static String multiplyPolynomials(Polynomial p, Polynomial q) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representations of the polynomials
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();
        TreeMap<Integer, Integer> qMap = q.getPolynomialMap();

        // Iterate through the monomials of the first polynomial
        for (Map.Entry<Integer, Integer> monomialP : pMap.entrySet()) {
            int powerP = monomialP.getKey();
            int coefficientP = monomialP.getValue();

            // Iterate through the monomials of the second polynomial
            for (Map.Entry<Integer, Integer> monomialQ : qMap.entrySet()) {
                int powerQ = monomialQ.getKey();
                int coefficientQ = monomialQ.getValue();

                // Multiply the coefficients and add to the result polynomial
                int powerResult = powerP + powerQ;
                int coefficientResult = coefficientP * coefficientQ;

                result.getPolynomialMap().put(powerResult, result.getPolynomialMap().getOrDefault(powerResult, 0) + coefficientResult);
            }
        }

        // Return the result polynomial
        return result.polynomialToString();
    }

    /*
     * @param p
     * @param q
     * @return String
     *
     * Divide two polynomials and return a readable string
     * e.g. P(x) = x^2 + 2x + 1, Q(x) = x + 1 => Quotient: x + 1; Remainder: 0
     */
    static String dividePolynomials(Polynomial p, Polynomial q) {
        // Division of two polynomials step by step:
        // Step 1 - Order the monomials of the two polynomials P and Q in descending order according to their degree.
        // Step 2 - Divide the polynomial with the highest degree to the other polynomial having a lower degree (let’s consider
        // that P has the highest degree)
        // Step 3 – Divide the first monomial of P to the first monomial of Q and obtain the first term of the quotient
        // Step 4 – Multiply the quotient with Q and subtract the result of the multiplication from P obtaining the remainder of
        // the division
        // Step 5 – Repeat the procedure from step 2 considering the remainder as the new dividend of the division, until the
        // degree of the remainder is lower than Q.

        // Division of two polynomials pseudocode:
        // function n / d is
        // require d != 0
        // q <- 0
        // r <- n    // At each step n = d * q + r
        // while (r != 0 and degree(r) >= degree(d) do
        //   t <- lead(r) / lead(n)    // Divide the leading terms
        // q <- q + t
        // r <- r - t * d
        // end while
        // return (q, r)
        // end function

        if (q.polynomialToString().equals("0")) {
            return "ERROR: Can't divide by 0!";
        }

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial(p.polynomialToString());

        while (!remainder.getPolynomialMap().isEmpty() && remainder.getPolynomialMap().lastKey() >= q.getPolynomialMap().lastKey() && remainder.getPolynomialMap().lastKey() != 0) {
            int leadR = remainder.getPolynomialMap().lastKey();
            int leadQ = q.getPolynomialMap().lastKey();

            int tCoefficient = remainder.getPolynomialMap().get(leadR) / q.getPolynomialMap().get(leadQ);
            int tPower = leadR - leadQ;

            // Create a temporary polynomial for the term t
            Polynomial tTerm = new Polynomial();
            tTerm.getPolynomialMap().put(tPower, tCoefficient);

            // Add the term t to the quotient
            quotient.getPolynomialMap().put(tPower, tCoefficient);

            // Multiply the divisor q with the term t
            Polynomial tTimesQ = new Polynomial(multiplyPolynomials(tTerm, q));

            // Subtract t * q from the remainder p
            remainder = new Polynomial(subtractPolynomials(remainder, tTimesQ));
        }

        // Return the result
        return "Quotient: " + quotient.polynomialToString() + "; Remainder: " + remainder.polynomialToString();
    }

    /*
     * @param p
     * @return String
     *
     * Compute the first order derivative of a polynomial and return a readable string
     * e.g. P(x) = x^2 - 4 => 2x
     */
    static String differentiatePolynomial(Polynomial p) {
        Polynomial result = new Polynomial();

        // Get the TreeMap representation of the given polynomial
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();

        // Monomials differentiation:
        // [1]: ax^n -> (n*a)x^(n-1) => if power > 0, then coefficient = coefficient * power, power -= 1
        // [2]: a -> 0 => if power == 0, coefficient = 0

        // Iterate through the monomials of the given polynomial
        for (Map.Entry<Integer, Integer> monomial : pMap.entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            // Case 1: ax^n
            if (power > 0) {
                coefficient = coefficient * power;
                power -= 1;
            }

            // Case 2: a
            else if (power == 0) {
                coefficient = 0;
            }

            result.getPolynomialMap().put(power, coefficient);
        }

        return result.polynomialToString();
    }

    /*
     * @param p
     * @return String
     *
     * Compute the anti-derivative of a polynomial and return a readable string
     * e.g. P(x) = x^3 - 3x^2 + 1 => 0.250x^4 - x^3 + x + C
     */
    static String integratePolynomial(Polynomial p) {
        StringBuilder result = new StringBuilder();

        // Get the TreeMap representation of the given polynomial
        TreeMap<Integer, Integer> pMap = p.getPolynomialMap();

        // Monomials integration:
        // newPower = power + 1, coefficient = coefficient / newPower

        // Iterate through the monomials of the given polynomial in reverse order
        for (Map.Entry<Integer, Integer> monomial : pMap.descendingMap().entrySet()) {
            int power = monomial.getKey();
            int coefficient = monomial.getValue();

            int newPower = power + 1;
            double newCoefficient = (double)coefficient / newPower;

            // Skip over the monomials with a coefficient equal to 0
            if (newCoefficient == 0) {
                continue;
            }

            // Check if we print the first monomial or not
            if (result.isEmpty()) {
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
            }

            else {
                if (newCoefficient < 0) {
                    result.append(" - ");
                }

                // If the new coefficient is positive
                else {
                    // If the result string isn't empty, meaning we don't print the first coefficient
                    if (!result.isEmpty()) {
                        result.append(" + ");
                    }
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
        if (result.toString().isEmpty()) {
            result = new StringBuilder();
            result.append("C");
        }

        else {
            result.append(" + C");
        }

        return result.toString();
    }
}
