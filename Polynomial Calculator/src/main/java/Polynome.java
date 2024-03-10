import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Polynome {

    public HashMap<Integer, Double> map;

    public Polynome() {
        map = new HashMap<Integer, Double>();
    }

    public Polynome spltCoeff(String poly) {

        Polynome rez = new Polynome();
        // Split the polynomial string into individual terms
        String[] terms = poly.split("(?=[+-])\\s*");

        for (String term : terms) {
           // System.out.println(term);

            String[] parts = term.split("x\\^");
            Double aux = Double.parseDouble(parts[1]);
            int power = aux.intValue();
            double coeff = Double.parseDouble(parts[0]);
            rez.map.put(power, coeff);
            //System.out.println(rez.map);
        }
        return rez;

    }

}
