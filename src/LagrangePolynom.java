import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class LagrangePolynom extends Polynom {

    private LinkedHashMap<Double, Double> points;

    public LagrangePolynom(){
        super();
    }

    public void SetPoints(LinkedHashMap<Double, Double> c){
        points = c;
    }

    public LagrangePolynom(LinkedHashMap<Double,Double> c){
        //super(c.size() - 1);
        points = c;
        degree = c.size() - 1;
        buildPolynom();
    }
    
    public Polynom findWholeBasis(){
        double[] c = {1};
        Polynom tmp = new Polynom(c);
        double[] mult = new double[2];
        mult[1] = 1;
        for(Double key: points.keySet()){
            mult[0] = -key;
            tmp = tmp.multiplication(new Polynom(mult));
        }
        return tmp;
    }

    public List<Polynom> findBasisPolynomials(){
        
        Polynom tmp = findWholeBasis();

        double[] mult = new double[2];
        mult[1] = 1;

        List<Polynom> ans = new ArrayList<Polynom>();
        for(Double key: points.keySet()){
            mult[0] = -key;
            Polynom push = tmp.division(new Polynom(mult));
            
            double value = 1.0;
            for(Double coef : points.keySet()){
                if(!coef.equals(key)){
                    value /= (key - coef);
                }
            }
            push = push.coefMultiplication(value);
            ans.add(push);
        }

        return ans;
    }

    private void buildPolynom(){
        List<Polynom> tmp = findBasisPolynomials();
        Polynom ans = new Polynom();
        int i = 0;

        for (Double value: points.values()) {
            ans = ans.sum(tmp.get(i).coefMultiplication(value));
            i++;
        }
        
        coefs = ans.coefs;
    }
}
