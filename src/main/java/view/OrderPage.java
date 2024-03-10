package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The OrderPage class represents the graphical user interface for ordering products.
 * It allows users to add clients, add products, and generate bills.
 */
public class OrderPage {

    public JFrame frame = new JFrame("Order");
    private JButton backButton;
    private JButton addOrderClient;
    private JButton addProduct;
    private JButton billButton;
    private JTextField clientNameText;
    private JLabel clientNameLabel;
    private JTextField productNameText;
    private JLabel productNameLabel;
    private JTextField productQuantityText;
    private JLabel productQuantityLabel;
    public JLabel error;
    public OrderPage(){
        backButton = new JButton("BACK");
        backButton.setBounds(50,20,100,30);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new frame
                MainPage newFrame = new MainPage();
                // Show the new frame
                newFrame.frame.setVisible(true);
            }
        });

        billButton = new JButton("BILL");
        billButton.setBounds(700,20,100,30);
        frame.add(billButton);

        addOrderClient = new JButton("Add Client");
        addOrderClient.setBounds(550,100,100,30);
        frame.add(addOrderClient);

        addProduct = new JButton("Add Product");
        addProduct.setBounds(550,170,100,30);
        frame.add(addProduct);

        clientNameText=new JTextField("");
        clientNameText.setBounds(400,100, 130,30);
        frame.add(clientNameText);

        clientNameLabel = new JLabel("Name Client");
        clientNameLabel.setBounds(300,100,130,30);
        frame.add(clientNameLabel);

        productNameText=new JTextField("");
        productNameText.setBounds(400,150, 130,30);
        frame.add(productNameText);

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(300,150,130,30);
        frame.add(productNameLabel);

        productQuantityText=new JTextField("");
        productQuantityText.setBounds(400,200, 130,30);
        frame.add(productQuantityText);

        productQuantityLabel = new JLabel("Quantity");
        productQuantityLabel.setBounds(340,200,130,30);
        frame.add(productQuantityLabel);

        error = new JLabel("Not enough products in stock");
        error.setVisible(false);
        error.setBounds(360,300, 200,30);
        frame.add(error);

        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrame(){
        frame.setVisible(true);
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    public String getName(){return clientNameText.getText();}

    public String getProductName(){return productNameText.getText();}
    public String getQuantity(){return productQuantityText.getText();}
    public void addClientToOrder(ActionListener addClientToOrder){
        addOrderClient.addActionListener(addClientToOrder);
    }

    public void addProductToOrder(ActionListener addProductToOrder){
        addProduct.addActionListener(addProductToOrder);
    }
    public void openBill(ActionListener OpenBillPage){billButton.addActionListener(OpenBillPage);
    }

}
