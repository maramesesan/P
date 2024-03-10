public class Model {

    Addition addition = new Addition();
    Subtraction subtraction = new Subtraction();
    Multiplication multiplication = new Multiplication();
    Polynome coefficients = new Polynome();
    Derivation derivation = new Derivation();
    Integration integration = new Integration();

    public Polynome getPolynome(String p){
        Polynome aux = new Polynome();
        Polynome rez;
        rez = aux.spltCoeff(p);

        return rez;
    }

    public String returnPolyn(Polynome result){
        String output = " ";
        for (Integer j: result.map.keySet()
        ) {

            String res =  result.map.get(j) + " *x^" + j + " + ";
            output = output + res;

        }
        return output;
    }

    public Polynome add(Polynome pol1, Polynome pol2){

        return addition.addition(pol1, pol2);

    }

    public Polynome sub(Polynome pol1, Polynome pol2){

        return subtraction.subtraction(pol1, pol2);
    }

    public Polynome mul(Polynome pol1, Polynome pol2){

        return multiplication.multiplication(pol1, pol2);
    }

    public Polynome deriv(Polynome pol1){

        return derivation.derivation(pol1);
    }

    public Polynome integr(Polynome pol1){

        return integration.integration(pol1);
    }
}
