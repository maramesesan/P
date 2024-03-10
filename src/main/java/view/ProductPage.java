package view;

import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * The ProductPage class represents the graphical user interface for managing products.
 * It allows users to insert, update, delete, and view all products.
 */

public class ProductPage {

    public JFrame frame = new JFrame("Product");
    public JFrame productsFrame = new JFrame("All Products");
    private JButton backButton;
    private JButton insertionButton;
    private JButton updateDeleteButton;
    private JButton makeUpdateButton;
    private JButton makeDeletionButton;
    private JButton showProducts;
    private JButton backToProducts;
    private JTextField nameText;
    private JLabel nameLabel;
    private JTextField priceText;
    private JLabel priceLabel;
    private JTextField stockText;
    private JLabel stockLabel;

    private JTextField nameProductToUpdate;
    private JLabel nameProductToUpdateLabel;
    private JTextField fieldToUpdate;
    private JLabel fieldToUpdateLabel;

    private JTextField newValue;
    private JLabel newValueLabel;
    private JTable productsTable;

    public ProductPage(){

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

        insertionButton = new JButton("INSERTION");
        insertionButton.setBounds(370,300,100,30);
        frame.add(insertionButton);

        updateDeleteButton = new JButton("UPDATE/DELETE");
        updateDeleteButton.setBounds(500,300,100,30);
        frame.add(updateDeleteButton);

        makeUpdateButton = new JButton("MAKE UPDATE");
        makeUpdateButton.setBounds(560,380,150,30);
        frame.add(makeUpdateButton);
        makeUpdateButton.setVisible(false);

        makeDeletionButton = new JButton("DELETE");
        makeDeletionButton.setBounds(560,450,150,30);
        frame.add(makeDeletionButton);
        makeDeletionButton.setVisible(false);

        showProducts = new JButton("SHOW ALL");
        showProducts.setBounds(630,300,100,30);
        frame.add(showProducts);

        showProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        backToProducts = new JButton("BACK");
        backToProducts.setBounds(50,20,100,30);
        productsFrame.add(backToProducts);
        backToProducts.setVisible(true);

        backToProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                productsFrame.setVisible(false);
            }
        });

        nameText=new JTextField("");
        nameText.setBounds(400,100, 130,30);
        frame.add(nameText);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(360,100,130,30);
        frame.add(nameLabel);

        priceText=new JTextField("");
        priceText.setBounds(400,150, 130,30);
        frame.add(priceText);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(360,150,130,30);
        frame.add(priceLabel);

        stockText=new JTextField("");
        stockText.setBounds(400,200, 130,30);
        frame.add(stockText);

        stockLabel = new JLabel("Stock");
        stockLabel.setBounds(360,200,130,30);
        frame.add(stockLabel);

        nameProductToUpdate=new JTextField("");
        nameProductToUpdate.setBounds(400,360, 130,30);
        frame.add(nameProductToUpdate);
        nameProductToUpdate.setVisible(false);

        nameProductToUpdateLabel = new JLabel("Name");
        nameProductToUpdateLabel.setBounds(360,360,130,30);
        frame.add(nameProductToUpdateLabel);
        nameProductToUpdateLabel.setVisible(false);

        fieldToUpdate=new JTextField("");
        fieldToUpdate.setBounds(400,410, 130,30);
        frame.add(fieldToUpdate);
        fieldToUpdate.setVisible(false);

        fieldToUpdateLabel = new JLabel("Field");
        fieldToUpdateLabel.setBounds(360,410,130,30);
        frame.add(fieldToUpdateLabel);
        fieldToUpdateLabel.setVisible(false);

        newValue=new JTextField("");
        newValue.setBounds(400,460, 130,30);
        frame.add(newValue);
        newValue.setVisible(false);

        newValueLabel = new JLabel("Value");
        newValueLabel.setBounds(360,460,130,30);
        frame.add(newValueLabel);
        newValueLabel.setVisible(false);

        updateDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameProductToUpdate.setVisible(true);
                nameProductToUpdateLabel.setVisible(true);
                fieldToUpdate.setVisible(true);
                fieldToUpdateLabel.setVisible(true);
                makeUpdateButton.setVisible(true);
                newValue.setVisible(true);
                newValueLabel.setVisible(true);
                makeDeletionButton.setVisible(true);
            }
        });

        productsTable = new JTable();
        productsTable.setVisible(true);
        productsTable.setBounds(50,100,1000,850);
        productsFrame.add(productsTable);

        productsFrame.setSize(1000,850);
        productsFrame.setLayout(null);
        productsFrame.setVisible(false);
        productsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void productTableView(List<Product> allProducts){
        Object[][] data = new Object[allProducts.size()][];

        for(int i =0; i<allProducts.size(); i++){
            Product newProduct = allProducts.get(i);
            Object[] row = {newProduct.isId(), newProduct.isName(), newProduct.isPrice(), newProduct.isStock()};
            data[i] = row;
        }

        String[] columns = {"Id", "Name", "Price", "Stock"};

        productsTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public void showFrame(){
        frame.setVisible(true);
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    public String getProductName(){
        return nameText.getText();
    }
    public String getProductPrice(){
        return priceText.getText();
    }
    public String getProductStock(){
        return stockText.getText();
    }

    public String getUpdateName(){
        return nameProductToUpdate.getText();
    }

    public String getFieldToUpdate(){
        return fieldToUpdate.getText();
    }

    public String getNewValue(){
        return newValue.getText();
    }
    public void insertProduct (ActionListener ProductInsertion) {
        insertionButton.addActionListener(ProductInsertion);
    }

    public void updateFieldProduct(ActionListener ClientFieldUpdate){
        makeUpdateButton.addActionListener(ClientFieldUpdate);
    }

    public void deleteProduct(ActionListener ProductDelete){
        makeDeletionButton.addActionListener(ProductDelete);
    }

    public void findAllProducts(ActionListener FindAllProducts){
        showProducts.addActionListener(FindAllProducts);
    }

}
