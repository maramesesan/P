package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {

    public static JFrame frame = new JFrame("Home");
    private JButton orderButton;

    private JButton clientButton;
    private JButton productButton;
    public MainPage(){

        orderButton = new JButton("ORDER");
        orderButton.setBounds(50,300,150,30);
        frame.add(orderButton);

        clientButton = new JButton("CLIENT");
        clientButton.setBounds(260,300,150,30);
        frame.add(clientButton);

        productButton = new JButton("PRODUCT");
        productButton.setBounds(460,300,150,30);
        frame.add(productButton);


        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Adds an ActionListener to the clientButton.
     *
     * @param OpenClientPage The ActionListener to be added.
     */
    public void openClient(ActionListener OpenClientPage){
        clientButton.addActionListener(OpenClientPage);
    }
    /**
     * Adds an ActionListener to the orderButton.
     *
     * @param OpenOrderPage The ActionListener to be added.
     */
    public void openOrder(ActionListener OpenOrderPage){
        orderButton.addActionListener(OpenOrderPage);
    }
    public void openProduct(ActionListener OpenProductPage){
        productButton.addActionListener(OpenProductPage);
    }


}
