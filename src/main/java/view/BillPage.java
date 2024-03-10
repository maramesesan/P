package view;

import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The BillPage class represents the graphical user interface for displaying the bill.
 */
public class BillPage {

    public JFrame frame = new JFrame("Bill");
    private JButton backButton;
    private JButton showBillButton;
    private JTable productsTable;
    public JLabel totalPrice;

    public BillPage(){
        backButton = new JButton("BACK");
        backButton.setBounds(50,20,100,30);
        frame.add(backButton);

        showBillButton = new JButton("Sow");
        showBillButton.setBounds(160,20,100,30);
        frame.add(showBillButton);


        productsTable = new JTable();
        productsTable.setVisible(true);
        productsTable.setBounds(200,100,800,600);
        frame.add(productsTable);

        totalPrice = new JLabel("");
        totalPrice.setVisible(true);
        totalPrice.setBounds(20,100,100,100);
        frame.add(totalPrice);


        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Displays the product table view on the GUI.
     *
     * @param productName    The list of product names.
     * @param productQuantity The list of product quantities.
     */
    public void productTableView(List<String> productName, List<Integer> productQuantity){
        Object[][] data = new Object[productName.size()][];

        for(int i =0; i<productName.size(); i++){
           String newProduct = productName.get(i);
           int newQuantity = productQuantity.get(i);
            Object[] row = {newProduct, newQuantity};
            data[i] = row;
        }

        String[] columns = {"Name", "Quantity"};

        productsTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public void showFrame(){
        frame.setVisible(true);
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    /**
     * Sets up the action listener for the Show Bill button.
     *
       The action listener for the Show Bill button.
     */
    public void findBillComponents(ActionListener ShowBill){
        showBillButton.addActionListener(ShowBill);
    }

    public void backToOrder(ActionListener OpenOrderPage) {
        backButton.addActionListener(OpenOrderPage);
    }

}
