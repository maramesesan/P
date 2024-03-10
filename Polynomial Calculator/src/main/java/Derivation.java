import java.util.ArrayList;
import java.util.HashMap;

public class Derivation {


    public static Polynome derivation(Polynome polin1){

        Polynome result = new Polynome();

        for (Integer i: polin1.map.keySet()
        ) {
            if (i == 0) {
                continue;
            }
            result.map.put(i-1, polin1.map.get(i)*i);
        }

        return result;
    }

}
