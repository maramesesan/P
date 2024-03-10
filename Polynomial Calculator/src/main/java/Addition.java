import java.util.ArrayList;
import java.util.HashMap;

public class Addition{

    public static Polynome addition(Polynome polin1, Polynome polin2){
        Polynome result = new Polynome();

        for (Integer i: polin1.map.keySet()
        ) {
            //double coeff = polin1.map.get(i);
            result.map.put(i,polin1.map.get(i));

            if(polin2.map.containsKey(i)){

                result.map.put(i,polin1.map.get(i)+polin2.map.get(i));

                polin2.map.remove(i);
            }
        }

        for (Integer j: polin2.map.keySet()
        ) {
            result.map.put(j,polin2.map.get(j));
        }
        return result;

    }


}
