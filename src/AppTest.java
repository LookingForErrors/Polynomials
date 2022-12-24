import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

public class AppTest {
    
    //PolynomTests

    @Test
    public void clearZeroesTest() {
        double[] expected = new double[] {-6.0, 11, -6, 1};

        double[] c = new double[] {-6.0, 11, -6, 1, 0, 0, 0};
        Polynom p = new Polynom(c);
        p.clearZeroes();

        double[] actual = p.getCoefs();

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void valueAtPointTest(){
        double[] c = {-6.0, 11, -6, 1};
        Polynom p = new Polynom(c);

        double expected = 0;
        double actual = p.valueAtPoint(3);

        assertEquals(expected, actual, 0);
    }

    @Test
    public void sumTest(){
        double[] c = {6, 3, 5};
        double[] s = {6, 6, -5};
        Polynom firts = new Polynom(c);
        Polynom second = new Polynom(s);
        double[] t = {12,9};

        Polynom expected = new Polynom(t);
        Polynom actual = firts.sum(second);

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    @Test
    public void differenceTest(){
        double[] c = {6, 3, 5};
        Polynom firts = new Polynom(c);
        double[] s = {6, 6, 5};
        Polynom second = new Polynom(s);

        double[] t = {0,-3};
        Polynom expected = new Polynom(t);

        Polynom actual = firts.difference(second);

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    @Test
    public void coefMultiplicationTest(){
        double[] c = {6, 3, 5};
        Polynom tmp = new Polynom(c);

        Polynom actual = tmp.coefMultiplication(-0.02);

        double[] t = {-0.12, -0.06 , -0.1};
        Polynom expected = new Polynom(t);

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    @Test
    public void coefMultiplicationZeroTest(){
        double[] c = {6, 3, 5};
        Polynom tmp = new Polynom(c);

        Polynom actual = tmp.coefMultiplication(0);

        Polynom expected = new Polynom();

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    @Test
    public void divisionTest(){
        double[] c = {-15, -3, 5, 1};
        Polynom firts = new Polynom(c);
        double[] s = {-3, 0, 1};
        Polynom second = new Polynom(s);

        Polynom actual = firts.division(second);

        double[] t = {5, 1};
        Polynom expected = new Polynom(t);

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    @Test
    public void multiplicationTest(){
        double[] c = {5, 1};
        Polynom firts = new Polynom(c);
        double[] s = {-3, 0, 1};
        Polynom second = new Polynom(s);

        Polynom actual = firts.multiplication(second);

        double[] t = {-15, -3, 5, 1};
        Polynom expected = new Polynom(t);

        assertArrayEquals(expected.getCoefs(), actual.getCoefs(), 0);
        assertEquals(expected.getDegree(), actual.getDegree());
    }

    //LagrangePolynomTests

    @Test
    public void findWholeBasisTest(){
        double[] c = {0, 1.0/12, -4.0/6, 1};
        Polynom expected = new Polynom(c);

        LagrangePolynom tmp = new LagrangePolynom();

        LinkedHashMap<Double, Double> act = new LinkedHashMap<>();
        act.put(0.0,0.0);
        act.put(1.0/6, 1.0/2);
        act.put(1.0/2, 1.0);

        tmp.SetPoints(act);
        Polynom actual = tmp.findWholeBasis();

        assertArrayEquals(expected.coefs, actual.coefs, 0);
    }

    @Test
    public void findBasisPolynomialsTest(){
        List<Polynom> expected = new ArrayList<Polynom>();
        double[] c1 = {-36/-36.0, 49/-36.0, -14/-36.0, 1/-36.0};
        double[] c2 = {0/24.0, 36/24.0, -13/24.0, 1/24.0};
        double[] c3 = {0/-60.0, 9/-60.0, -10/-60.0, 1/-60.0};
        double[] c4 = {0/360.0, 4/360.0, -5/360.0, 1/360.0};
        Polynom P1 = new Polynom(c1);
        Polynom P2 = new Polynom(c2);
        Polynom P3 = new Polynom(c3);
        Polynom P4 = new Polynom(c4);
        expected.add(P1);
        expected.add(P2);
        expected.add(P3);
        expected.add(P4);


        LagrangePolynom tmp = new LagrangePolynom();

        LinkedHashMap<Double, Double> act = new LinkedHashMap<>();
        act.put(0.0,0.0);
        act.put(1.0, 1.0);
        act.put(4.0, 2.0);
        act.put(9.0, 3.0);

        tmp.SetPoints(act);
        List<Polynom> actual = tmp.findBasisPolynomials();

        assertArrayEquals(expected.get(0).coefs, actual.get(0).coefs, 1.e-4);
        assertArrayEquals(expected.get(1).coefs, actual.get(1).coefs, 1.e-4);
        assertArrayEquals(expected.get(2).coefs, actual.get(2).coefs, 1.e-4);
        assertArrayEquals(expected.get(3).coefs, actual.get(3).coefs, 1.e-4);
    }

    @Test
    public void buildLagrangePolynom(){
        LinkedHashMap<Double, Double> act = new LinkedHashMap<>();
        act.put(0.0,0.0);
        act.put(1.0/6, 1.0/2);
        act.put(1.0/2, 1.0);

        Polynom actual = new LagrangePolynom(act);

        double[] c = {0, 7.0/2, -3};
        Polynom expected = new Polynom(c);

        assertArrayEquals(expected.coefs, actual.coefs, 1.e-4);
    }

}
