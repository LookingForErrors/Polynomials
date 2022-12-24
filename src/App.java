import java.util.LinkedHashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // LinkedHashMap<Double, Double> act = new LinkedHashMap<>();
        // act.put(0.0,0.0);
        // act.put(1.0/6, 1.0/2);
        // act.put(1.0/2, 1.0);

        // Polynom actual = new LagrangePolynom(act);

        // System.out.println(actual);


        // double[] c = {1, 1, 1, 1};
        // Polynom a = new Polynom(c);
        // Polynom b = new Polynom(c);
        // Polynom dd = a.division(b);
        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(dd);

        LagrangePolynom tmp = new LagrangePolynom();

        LinkedHashMap<Double, Double> act = new LinkedHashMap<>();
        act.put(0.0,0.0);
        act.put(1.0, 1.0);
        act.put(4.0, 2.0);
        act.put(9.0, 3.0);

        tmp.SetPoints(act);
        List<Polynom> actual = tmp.findBasisPolynomials();

    }
}
