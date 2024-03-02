import org.junit.jupiter.api.Test;
import org.moldidev.model.Polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {
    @Test
    public void testStringToPolynomial1() {
        Polynomial polynomial = new Polynomial("2x^3 - 4x^2 - 7x^1 + 2");

        assertEquals("{0=2, 1=-7, 2=-4, 3=2}", polynomial.getPolynomialMap().toString());
    }

    @Test
    public void testStringToPolynomial2() {
        Polynomial polynomial = new Polynomial("2x^3 + 4x^2 + 7x^1 + 2");

        assertEquals("{0=2, 1=7, 2=4, 3=2}", polynomial.getPolynomialMap().toString());
    }

    @Test
    public void testStringToPolynomial3() {
        Polynomial polynomial = new Polynomial("-2x^3 - 4x^2 + 7x^1 - 2");

        assertEquals("{0=-2, 1=7, 2=-4, 3=-2}", polynomial.getPolynomialMap().toString());
    }

    @Test
    public void testStringToPolynomial4() {
        Polynomial polynomial = new Polynomial("-2x^3-4x^2+7x-2");

        assertEquals("{0=-2, 1=7, 2=-4, 3=-2}", polynomial.getPolynomialMap().toString());
    }

    @Test
    public void testToString1() {
        Polynomial polynomial = new Polynomial();

        polynomial.getPolynomialMap().put(0, 2);
        polynomial.getPolynomialMap().put(1, -7);
        polynomial.getPolynomialMap().put(2, -4);
        polynomial.getPolynomialMap().put(3, 2);

        assertEquals("2x^3 - 4x^2 - 7x + 2", polynomial.toString());
    }

    @Test
    public void testToString2() {
        Polynomial polynomial = new Polynomial();

        polynomial.getPolynomialMap().put(0, 2);
        polynomial.getPolynomialMap().put(1, 7);
        polynomial.getPolynomialMap().put(2, 4);
        polynomial.getPolynomialMap().put(3, 2);

        assertEquals("2x^3 + 4x^2 + 7x + 2", polynomial.toString());
    }

    @Test
    public void testToString3() {
        Polynomial polynomial = new Polynomial();

        polynomial.getPolynomialMap().put(0, -2);
        polynomial.getPolynomialMap().put(1, 7);
        polynomial.getPolynomialMap().put(2, -4);
        polynomial.getPolynomialMap().put(3, -2);

        assertEquals("-2x^3 - 4x^2 + 7x - 2", polynomial.toString());
    }

    @Test
    public void testToString4() {
        Polynomial polynomial = new Polynomial();

        polynomial.getPolynomialMap().put(0, -4);
        polynomial.getPolynomialMap().put(1, 17);
        polynomial.getPolynomialMap().put(2, 6);
        polynomial.getPolynomialMap().put(3, -5);
        polynomial.getPolynomialMap().put(4, 16);

        assertEquals("16x^4 - 5x^3 + 6x^2 + 17x - 4", polynomial.toString());
    }

    @Test
    public void testPolynomialToStringAndToString1() {
        Polynomial polynomial = new Polynomial("-16x^4+5x^3-6x^2+17x-4");

        assertEquals("-16x^4 + 5x^3 - 6x^2 + 17x - 4", polynomial.toString());
    }
}
