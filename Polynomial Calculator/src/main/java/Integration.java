import java.util.ArrayList;
import java.util.HashMap;

public class Integration {

    public static Polynome integration(Polynome pol1) {

        Polynome result = new Polynome();

        for (Integer i : pol1.map.keySet()
        ) {
            if(i==0){
                result.map.put(0, 0.0);

            }

            result.map.put(i + 1, pol1.map.get(i)/(i+1));

        }

        return result;
    }
}
