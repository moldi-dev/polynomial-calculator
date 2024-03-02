package org.moldidev.interfaces;

import org.moldidev.model.Polynomial;

public interface PolynomialOperations {
    default Polynomial addPolynomials(Polynomial p, Polynomial q) {
        return new Polynomial();
    }

    default Polynomial subtractPolynomials(Polynomial p, Polynomial q) {
        return new Polynomial();
    }

    default Polynomial multiplyPolynomials(Polynomial p, Polynomial q) {
        return new Polynomial();
    }

    default Polynomial dividePolynomials(Polynomial p, Polynomial q) {
        return new Polynomial();
    }

    default Polynomial integratePolynomial(Polynomial p) {
        return new Polynomial();
    }

    default Polynomial differentiatePolynomial(Polynomial p) {
        return new Polynomial();
    }
}
