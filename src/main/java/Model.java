import bll.*;
import model.Client;
import model.Product;

import java.util.List;
import java.util.Objects;

public class Model {

ClientBLL clientBLL = new ClientBLL();
ProductBLL productBLL = new ProductBLL();
OrderBLL orderBLL = new OrderBLL();
ProductInOrderBLL productInOrderBLL = new ProductInOrderBLL();
BillBLL billBLL = new BillBLL();


public void addClient(String name, int age, String password, String email){

    Client newClient = new Client();
    newClient.setName(name);
    newClient.setAge(age);
    newClient.setPassword(password);
    newClient.setEmail(email);

    clientBLL.insertClient(newClient);
}

public void updateFieldClient(String name, String value, String field){

    if(Objects.equals(field, "age")){
        clientBLL.updateAge(name, Integer.parseInt(value));
    } else if(Objects.equals(field, "name")){
        clientBLL.updateName(name,value);
    }else if(Objects.equals(field, "password")){
        clientBLL.updatePassword(name,value);
    }else{
        clientBLL.updateEmail(name,value);
    }
}

public void deleteClient(String name){
    clientBLL.deleteClientByName(name);
}

public List<Client> showClients(){
    List<Client> clients = clientBLL.selectAllClients();

    return clients;
}

public void addProduct(String name, float price, int stock){
    Product product = new Product();
    product.setName(name);
    product.setPrice(price);
    product.setStock(stock);
    productBLL.insertProduct(product);
}

    public void updateFieldProduct(String name, String value, String field){

        if(Objects.equals(field, "name")){
            productBLL.updateName(name, value);
        } else if(Objects.equals(field, "price")){
            productBLL.updatePrice(name, Float.parseFloat(value));
        }else if(Objects.equals(field, "stock")){
           productBLL.updateStock(name, Integer.parseInt(value));
        }
    }
    public void deleteProduct(String name){
    productBLL.deleteProductByName(name);
}

    public List<Product> showProducts(){
        List<Product> product  = productBLL.selectAllProducts();

        return product;
    }

    public int getClientId(String name){
    return clientBLL.returnIdByName(name);
    }

    public void addClientToOrder(int id){
    orderBLL.addClient(id);
    }

    public int getOrderId(int idClient){
    return orderBLL.returnOrderId(idClient);
    }

    public int getProductId(String name){
    return productBLL.returnIdByName(name);
    }

    public void addProductToOrder(int idProduct, int idOrder, int quantity){
    productInOrderBLL.addProduct(idProduct,idOrder,quantity);
    }

    public List<String> productsList(int id){
        List<String> products = productInOrderBLL.selectProductsName(id);

        return products;
    }

    public List<Integer> quantityList(int id){
    List<Integer> quantity = productInOrderBLL.selectProductsQuantity(id);

    return quantity;
    }

    public float totalPrice(int id){
    return billBLL.totalPrice(id);
    }
}
