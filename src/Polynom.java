

public class Polynom {
    
    protected int degree;
    protected double[] coefs;

    public Polynom(){
        coefs = new double[]{0};
        degree = 0;
    }
    
    public Polynom(double[] c){
        coefs = c;
        degree = coefs.length - 1;
    }

    public Polynom(int d){
        coefs = new double[d+1];
        degree = d;
    }

    public void setThisCoef(int position, double c){
        coefs[position] = c;
    }

    public double getThisCoef(int position){
        return coefs[position];
    }

    public void setCoefs(double[] c){
        coefs = c;
    }

    public double[] getCoefs(){
        return coefs;
    }

    public int getDegree(){
        return degree;
    }


    public void randomFill(){
        for(int i = 0; i <= degree; i++){
            coefs[i] = Math.random()*61-30;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        if(degree == 0) return coefs[0] + "";
        for (int i=degree; i>=0; i--) {
            if (coefs[i] == 0) continue;
            if ((coefs[i] > 0) && (i!=degree))
                s.append("+");

            if((int)coefs[i] == 1) s.append("");
            else if ((int)coefs[i] == -1) s.append("-");
            else if ((int)coefs[i] == coefs[i]) s.append((int)coefs[i]);
            else s.append(coefs[i]);

            if (i == 1) s.append("x");
            else if (i != 0) s.append("x^" + i);
        }
        if(coefs[0]==1) s.append("1");
        return s.toString();
    }

    public void clearZeroes(){
        if(degree == 0) return;
        int i;
        double[] tmparr = coefs;
        for (i = degree; i >= 0; i--) if (coefs[i] != 0) break;
        if(i == degree) return;
        if(i == -1){
            degree = 0;
            coefs = new double[]{0};
            return;
        }
        degree = i;
        coefs = new double[degree + 1];
        System.arraycopy(tmparr, 0, coefs, 0, degree+1);
        return;
    }

    private static double valRec(double[] c, int index, double x, double ans){
        if(index == 0){return ans;}
        ans = ans*x + c[index - 1];
        index -= 1;
        return valRec(c, index, x, ans);
    }

    public double valueAtPoint(double x){
        return valRec(coefs, coefs.length - 1, x, coefs[coefs.length - 1]);
    }

    public Polynom sum(Polynom g){
        int sum_distance = Math.min(degree, g.degree);
        int deg = Math.max(degree, g.degree);

        Polynom ans = new Polynom(deg);

        for(int i = 0; i <=sum_distance; i++){
            ans.coefs[i] = coefs[i] + g.coefs[i];
        }        

        if(degree > g.degree) System.arraycopy(coefs, sum_distance+1, ans.coefs, sum_distance+1, deg-sum_distance);
        else if(degree < g.degree) System.arraycopy(g.coefs, sum_distance+1, ans.coefs, sum_distance+1, deg-sum_distance);
        ans.clearZeroes();
        return ans;
    }

    public Polynom difference(Polynom g){
        int sum_distance = Math.min(degree, g.degree);
        int deg = Math.max(degree, g.degree);

        Polynom ans = new Polynom(deg);

        for(int i = 0; i <= sum_distance; i++){
            ans.coefs[i] = coefs[i] - g.coefs[i];
        }        

        if(degree > g.degree) System.arraycopy(coefs, sum_distance+1, ans.coefs, sum_distance+1, deg-sum_distance);
        else if(degree < g.degree) System.arraycopy(g.coefs, sum_distance+1, ans.coefs, sum_distance+1, deg-sum_distance);
        ans.clearZeroes();
        return ans;
    }

    public Polynom coefMultiplication(double a){
        if(Math.abs(a) < 0.0001){
            return new Polynom();
        }
        double[] c = coefs;
        for (int i = 0; i < c.length; i++) {
            c[i] *= a;
        }
        Polynom ans = new Polynom(c);
        return ans;
    }

    public Polynom multiplication(Polynom g){
        double[] c = new double[degree + g.degree + 1];
        
        for (int i = degree; i >= 0; i--) {
            for (int j = g.degree; j >= 0 ; j--) {
                c[i+j] += coefs[i] * g.coefs[j];
            }
        }

        Polynom ans = new Polynom(c);
        ans.clearZeroes();
        return ans;
    }

    public Polynom division(Polynom g){
        double[] firstP = new double[degree+1];
        System.arraycopy(coefs, 0, firstP, 0, degree+1);
        double[] secondP = new double[g.degree+1];
        System.arraycopy(g.coefs, 0, secondP, 0, g.degree+1);
        double[] test = {0};
        if(g.coefs.equals(test)) throw new ArithmeticException("Division by zero");
        double[] c = new double[degree - g.degree + 1];
        double tmp;

        for (int i = 0; i < c.length; i++) {
            tmp = firstP[firstP.length - 1 - i] / secondP[secondP.length - 1];
            c[c.length - 1 - i] = tmp;
            for(int j = 0; j < secondP.length; j++){
                
                firstP[firstP.length - 1 - j - i] -= tmp * secondP[secondP.length - 1 - j];
            }
        }
        
        Polynom ans = new Polynom(c);
        return ans;
    }
}
