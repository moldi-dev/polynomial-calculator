import org.junit.jupiter.api.Test;
import org.moldidev.model.Polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {
    @Test
    public void stringToPolynomialTest1() {
        Polynomial polynomial = new Polynomial("-7x^7 - 5x^6 + x^5 - x^4 + 3x - 2");

        assertEquals("{0=-2, 1=3, 4=-1, 5=1, 6=-5, 7=-7}", polynomial.getPolynomialMap().toString());
    }

    @Test
    public void polynomialToStringTest1() {
        Polynomial polynomial = new Polynomial();

        polynomial.getPolynomialMap().put(0, -2);
        polynomial.getPolynomialMap().put(1, 3);
        polynomial.getPolynomialMap().put(4, -1);
        polynomial.getPolynomialMap().put(5, 1);
        polynomial.getPolynomialMap().put(6, -5);
        polynomial.getPolynomialMap().put(7, -7);

        assertEquals("-7x^7 - 5x^6 + x^5 - x^4 + 3x - 2", polynomial.polynomialToString());
    }

    @Test
    public void stringToPolynomialToStringTest1() {
        Polynomial polynomial = new Polynomial("-7x^7 - 5x^6 + x^5 - x^4 + 3x - 2");

        assertEquals("-7x^7 - 5x^6 + x^5 - x^4 + 3x - 2", polynomial.polynomialToString());
    }
}
