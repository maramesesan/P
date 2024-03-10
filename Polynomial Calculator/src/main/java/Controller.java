import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.makeAddition(new PolynomialAddition());
        view.makeSubtraction(new PolynomialSubtraction());
        view.makeMultiplication(new PolynomialMultiplication());
        view.makeDerivation(new PolynomialDerivation());
        view.makeIntegration(new PolynomialIntegration());


    }

    class PolynomialAddition implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String pol1 = view.getPolyn1();
            String pol2 = view.getPolyn2();

            Polynome p1 = new Polynome();
            Polynome result1 = p1.spltCoeff(pol1);

            Polynome p2 = new Polynome();
            Polynome result2 = p2.spltCoeff(pol2);

            Polynome result = model.add(result1, result2);

            view.setNr(model.returnPolyn(result));

            model.coefficients.map.clear();

        }
    }


    class PolynomialSubtraction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String pol1 = view.getPolyn1();
            String pol2 = view.getPolyn2();

            Polynome p1 = new Polynome();
            Polynome result1 = p1.spltCoeff(pol1);

            Polynome p2 = new Polynome();
            Polynome result2 = p2.spltCoeff(pol2);

            Polynome result = model.sub(result1, result2);

            view.setNr(model.returnPolyn(result));

            model.coefficients.map.clear();

        }
    }


    class PolynomialMultiplication implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String pol1 = view.getPolyn1();
            String pol2 = view.getPolyn2();

            Polynome p1 = new Polynome();
            Polynome result1 = p1.spltCoeff(pol1);

            Polynome p2 = new Polynome();
            Polynome result2 = p2.spltCoeff(pol2);

            Polynome result = model.mul(result1, result2);

            view.setNr(model.returnPolyn(result));

            model.coefficients.map.clear();

        }
    }


    class PolynomialDerivation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String pol1 = view.getPolyn1();
            Polynome p1 = new Polynome();
            Polynome result1 = p1.spltCoeff(pol1);


            Polynome result = model.deriv(result1);

            view.setNr(model.returnPolyn(result));

            model.coefficients.map.clear();

        }
    }

    class PolynomialIntegration implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String pol1 = view.getPolyn1();
            Polynome p1 = new Polynome();
            Polynome result1 = p1.spltCoeff(pol1);


            Polynome result = model.integr(result1);

            view.setNr(model.returnPolyn(result));

            model.coefficients.map.clear();

        }
    }
}
