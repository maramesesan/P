import java.util.ArrayList;
import java.util.HashMap;

public class Multiplication {


    public static Polynome multiplication(Polynome polin1, Polynome polin2) {

        Polynome result = new Polynome();

        for (Integer i : polin1.map.keySet()
        ) {

            for (Integer j : polin2.map.keySet()
            ) {
                double mul = polin1.map.get(i) * polin2.map.get(j);

                if(!result.map.containsKey(i+j)){
                    result.map.put(i+j, mul);
                } else {
                    double aux = result.map.get(i+j)+mul;
                    result.map.put(i+j, aux);
                }
            }

        }

        return result;
    }

}
