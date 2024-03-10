import bll.ProductBLL;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class handles the interactions between the model and view components.
 * It manages the flow of data and user interactions, ensuring that actions performed by the user
 * trigger the appropriate methods in the model and update the views accordingly.
 */
public class Controller {

    private Model model;
    private MainPage view;
    private ClientPage clientPage;
    private ProductPage productPage;
    private OrderPage orderPage;
    private BillPage billPage;
    ProductBLL productBLL = new ProductBLL();

    public Controller(Model model, MainPage view) {
        this.model = model;
        this.view = view;
        clientPage=new ClientPage();
        productPage=new ProductPage();
        billPage=new BillPage();
        orderPage=new OrderPage();

        view.openClient(new OpenClientClass());
        view.openOrder(new OpenOrderClass());
        view.openProduct(new OpenProductClass());
        clientPage.insertClient(new ClientInsertion());
        clientPage.updateFieldClient(new ClientFieldUpdate());
        clientPage.deleteClient(new ClientDelete());
        clientPage.findAllClients(new FindAllClients());
        productPage.insertProduct(new ProductInsertion());
        productPage.updateFieldProduct(new ProductFieldUpdate());
        productPage.deleteProduct(new ProductDelete());
        productPage.findAllProducts(new FindAllProducts());
        orderPage.addClientToOrder(new AddClientToOrder());
        orderPage.addProductToOrder(new AddProductToOrder());
        orderPage.openBill(new OpenBillClass());
        billPage.findBillComponents(new ShowBillComponents());
        billPage.backToOrder(new BackToOrder());
    }

    // Inner classes for ActionListener implementations

    /**
     * ActionListener implementation for opening the client page.
     */
    class OpenClientClass implements ActionListener{
        /**
         * Invoked when the client button is clicked.
         *
         * @param e The action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientPage.showFrame();
        }
    }

    class OpenOrderClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            orderPage.showFrame();
        }
    }

    class OpenProductClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            productPage.showFrame();
        }
    }

    class OpenBillClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //orderPage.hideFrame();
            billPage.showFrame();
        }
    }

    class BackToOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            billPage.hideFrame();
            orderPage.showFrame();
        }
    }
    class ClientInsertion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = clientPage.getClientName();
            int age = Integer.parseInt(clientPage.getClientAge());
            String password = clientPage.getClientPassword();
            String email = clientPage.getClientEmail();

            model.addClient(name,age,password,email);
        }
    }

    class ClientFieldUpdate implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = clientPage.getUpdateName();
            String field = clientPage.getFieldToUpdate();
            String value = clientPage.getNewValue();

            model.updateFieldClient(name,value,field);
        }
    }
    class ClientDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = clientPage.getUpdateName();

            model.deleteClient(name);
        }


    }

    class FindAllClients implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientPage.clientTableView(model.showClients());
        }
    }

    class ProductInsertion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = productPage.getProductName();
            float price = Float.parseFloat(productPage.getProductPrice());
            int stock = Integer.parseInt(productPage.getProductStock());

            model.addProduct(name,price,stock);
        }
    }

    class ProductFieldUpdate implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = productPage.getUpdateName();
            String value = productPage.getNewValue();
            String field = productPage.getFieldToUpdate();

            model.updateFieldProduct(name,value,field);
        }
    }

    class ProductDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = productPage.getUpdateName();

            model.deleteProduct(name);
        }


    }

    class FindAllProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            productPage.productTableView(model.showProducts());
        }
    }

    class AddClientToOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = orderPage.getName();
            int id = model.getClientId(name);
            model.addClientToOrder(id);
        }
    }

    class AddProductToOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        int productId = model.getProductId(orderPage.getProductName());
        int idClient = model.getClientId(orderPage.getName());
        int orderId = model.getOrderId(idClient);
        int quantity = Integer.parseInt(orderPage.getQuantity());
       boolean ok = productBLL.checkStock(productId,quantity);

       if(ok == true){
           productBLL.decrementStock(productId,quantity);
           model.addProductToOrder(productId,orderId,quantity);
       } else {
           orderPage.error.setVisible(true);
       }
        }
    }

    class ShowBillComponents implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int idClient = model.getClientId(orderPage.getName());
            int orderId = model.getOrderId(idClient);
            billPage.productTableView(model.productsList(orderId),model.quantityList(orderId));
            billPage.totalPrice.setText(Float.toString(model.totalPrice(orderId)));
        }
    }
}
