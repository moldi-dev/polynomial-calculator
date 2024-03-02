import org.junit.jupiter.api.Test;
import org.moldidev.model.Polynomial;
import org.moldidev.model.PolynomialOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialOperationsTest {

    @Test
    public void addPolynomialsTest1() {
        Polynomial p = new Polynomial("x^2 + 2x + 3");
        Polynomial q = new Polynomial("3x^5 - x^2 - 3");

        assertEquals("3x^5 + 2x + 0", PolynomialOperations.addPolynomials(p, q));
    }

    @Test
    public void addPolynomialsTest2() {
        Polynomial p = new Polynomial("-x^2 - 2x - 3");
        Polynomial q = new Polynomial("2x + 3");

        assertEquals("-x^2 + 0", PolynomialOperations.addPolynomials(p, q));
    }

    @Test
    public void addPolynomialsTest3() {
        Polynomial p = new Polynomial("7");
        Polynomial q = new Polynomial("5");

        assertEquals("12", PolynomialOperations.addPolynomials(p, q));
    }

    @Test
    public void addPolynomialsTest4() {
        Polynomial p = new Polynomial("x^4 + 4x + 3");
        Polynomial q = new Polynomial("-x^4 - 4x - 3");

        assertEquals(" + 0", PolynomialOperations.addPolynomials(p, q));
    }

    @Test
    public void subtractPolynomialsTest() {
        Polynomial p = new Polynomial("x^4 + 4x + 3");
        Polynomial q = new Polynomial("-x^4 - 4x - 3");

        assertEquals("2x^4 + 8x + 6", PolynomialOperations.subtractPolynomials(p, q));
    }

    @Test
    public void multiplyPolynomialsTest() {
        Polynomial p = new Polynomial("x + 2");
        Polynomial q = new Polynomial("x + 2");

        assertEquals("x^2 + 4x + 4", PolynomialOperations.multiplyPolynomials(p, q));
    }

    @Test
    public void multiplyPolynomialsTest2() {
        Polynomial p = new Polynomial("x + 2");
        Polynomial q = new Polynomial("0");

        assertEquals(" + 0", PolynomialOperations.multiplyPolynomials(p, q));
    }

    @Test
    public void multiplyPolynomialsTest3() {
        Polynomial p = new Polynomial("x^2 + 2x - 1");
        Polynomial q = new Polynomial("2x^2 - 3x + 6");

        assertEquals("2x^4 + x^3 - 2x^2 + 15x - 6", PolynomialOperations.multiplyPolynomials(p, q));
    }

}
