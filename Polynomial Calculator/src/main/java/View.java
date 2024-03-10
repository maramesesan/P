import javax.swing.*;
import java.awt.event.ActionListener;

public class View {

    public static JFrame frame = new JFrame("Calculator");
    public static JTextField polynome1;
    public static JTextField polynome2;
    public static JButton start;
    public static JButton addition;
    public static JButton subtraction;
    public static JButton multiplication;
    public static JButton derivation;

    public static JButton integration;
    private static JLabel numberLabel;


    public View(){

        numberLabel = new JLabel("RESULT");
        numberLabel.setBounds(260,300,1000,200);
        frame.add(numberLabel);

        addition = new JButton("ADDITION");
        addition.setBounds(50,300,150,30);
        frame.add(addition);

        subtraction = new JButton("SUBTRACTION");
        subtraction.setBounds(260,300,150,30);
        frame.add(subtraction);

        multiplication = new JButton("MULTIPLICATION");
        multiplication.setBounds(460,300,150,30);
        frame.add(multiplication);

        derivation = new JButton("DERIVATION");
        derivation.setBounds(660,300,150,30);
        frame.add(derivation);

        integration = new JButton("INTEGRATION");
        integration.setBounds(860,300,150,30);
        frame.add(integration);

        polynome1=new JTextField(" ");  //text box
        polynome1.setBounds(260,100, 500,30);
        frame.add(polynome1);

        polynome2=new JTextField(" ");  //text box
        polynome2.setBounds(260,200, 500,30);
        frame.add(polynome2);


        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setNr(String nr){
        numberLabel.setText(nr);
    }

    public String getPolyn1(){
        return polynome1.getText();
    }

    public String getPolyn2(){
        return polynome2.getText();
    }
    public void makeAddition(ActionListener PolynomialAddition) {
        addition.addActionListener(PolynomialAddition);
    }

    public void makeSubtraction(ActionListener PolynomialSubtraction){
        subtraction.addActionListener(PolynomialSubtraction);
    }

    public void makeMultiplication(ActionListener PolynomialMultiplication){
        multiplication.addActionListener(PolynomialMultiplication);
    }

    public void makeDerivation(ActionListener PolynomialDerivation){
        derivation.addActionListener(PolynomialDerivation);
    }

    public void makeIntegration(ActionListener PolynomialIntegration){
        integration.addActionListener(PolynomialIntegration);
    }


}
