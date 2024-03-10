package view;

import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientPage {

    public JFrame frame = new JFrame("Client");
    public JFrame clientsFrame = new JFrame("All Clients");
    private JButton backButton;
    private JButton insertionButton;
    private JButton updateDeleteButton;
    private JButton makeUpdateButton;
    private JButton makeDeletionButton;
    private JButton showClients;
    private JButton backToClients;
    private JTextField nameClientToUpdate;
    private JLabel nameClientToUpdateLabel;
    private JTextField fieldToUpdate;
    private JLabel fieldToUpdateLabel;

    private JTextField newValue;
    private JLabel newValueLabel;
    private JTextField nameText;
    private JLabel nameLabel;
    private JTextField ageText;
    private JLabel ageLabel;
    private JTextField passwordText;
    private JLabel passwordLabel;
    private JTextField emailText;
    private JLabel emailLabel;
    private JTable clientTable;

    /**
     * Constructs a ClientPage object.
     */
    public ClientPage(){

        frame.setSize(1000,850);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        showClients = new JButton("SHOW ALL");
        showClients.setBounds(630,300,100,30);
        frame.add(showClients);

        showClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientsFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        makeUpdateButton = new JButton("MAKE UPDATE");
        makeUpdateButton.setBounds(560,380,150,30);
        frame.add(makeUpdateButton);
        makeUpdateButton.setVisible(false);

        makeDeletionButton = new JButton("DELETE");
        makeDeletionButton.setBounds(560,450,150,30);
        frame.add(makeDeletionButton);
        makeDeletionButton.setVisible(false);

        backToClients = new JButton("BACK");
        backToClients.setBounds(50,20,100,30);
        clientsFrame.add(backToClients);
        backToClients.setVisible(true);

        backToClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                clientsFrame.setVisible(false);
            }
        });

        nameText=new JTextField("");
        nameText.setBounds(400,100, 130,30);
        frame.add(nameText);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(360,100,130,30);
        frame.add(nameLabel);

        ageText=new JTextField("");
        ageText.setBounds(400,150, 130,30);
        frame.add(ageText);

        ageLabel = new JLabel("Age");
        ageLabel.setBounds(360,150,130,30);
        frame.add(ageLabel);

        passwordText=new JTextField("");
        passwordText.setBounds(400,200, 130,30);
        frame.add(passwordText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(340,200,130,30);
        frame.add(passwordLabel);

        emailText=new JTextField("");
        emailText.setBounds(400,250, 130,30);
        frame.add(emailText);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(360,250,130,30);
        frame.add(emailLabel);


        nameClientToUpdate=new JTextField("");
        nameClientToUpdate.setBounds(400,360, 130,30);
        frame.add(nameClientToUpdate);
        nameClientToUpdate.setVisible(false);

        nameClientToUpdateLabel = new JLabel("Name");
        nameClientToUpdateLabel.setBounds(360,360,130,30);
        frame.add(nameClientToUpdateLabel);
        nameClientToUpdateLabel.setVisible(false);

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
                nameClientToUpdate.setVisible(true);
                nameClientToUpdateLabel.setVisible(true);
                fieldToUpdate.setVisible(true);
                fieldToUpdateLabel.setVisible(true);
                makeUpdateButton.setVisible(true);
                newValue.setVisible(true);
                newValueLabel.setVisible(true);
                makeDeletionButton.setVisible(true);
            }
        });

        clientTable = new JTable();
        clientTable.setVisible(true);
        clientTable.setBounds(50,100,1000,850);
        clientsFrame.add(clientTable);

        clientsFrame.setSize(1000,850);
        clientsFrame.setLayout(null);
        clientsFrame.setVisible(false);
        clientsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void showFrame(){
        frame.setVisible(true);
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    public void clientTableView(List<Client> allClients){
        Object[][] data = new Object[allClients.size()][];

        for(int i =0; i<allClients.size(); i++){
            Client newClient = allClients.get(i);
            Object[] row = {newClient.isId(), newClient.isName(), newClient.isAge(), newClient.isPassword(), newClient.isEmail()};
            data[i] = row;
        }

        String[] columns = {"Id", "Name", "Age", "Password", "Email"};

        clientTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public String getClientName(){
        return nameText.getText();
    }
    public String getClientAge(){
        return ageText.getText();
    }
    public String getClientPassword(){
        return passwordText.getText();
    }
    public String getClientEmail(){
        return emailText.getText();
    }

    public String getUpdateName(){
        return nameClientToUpdate.getText();
    }

    public String getFieldToUpdate(){
        return fieldToUpdate.getText();
    }

    public String getNewValue(){
        return newValue.getText();
    }

    public void insertClient (ActionListener ClientInsertion) {
        insertionButton.addActionListener(ClientInsertion);
    }

    public void updateFieldClient(ActionListener ClientFieldUpdate){
        makeUpdateButton.addActionListener(ClientFieldUpdate);
    }

    public void deleteClient(ActionListener ClientDelete){
        makeDeletionButton.addActionListener(ClientDelete);
    }

    public void findAllClients(ActionListener FindAllClients){
        showClients.addActionListener(FindAllClients);
    }


}
