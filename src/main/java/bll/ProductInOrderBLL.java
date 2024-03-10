package bll;

import dao.ClientDAO;
import dao.ProductInOrderDAO;
import model.Client;
import model.ProductInOrder;

import java.util.List;

/**

 The ProductInOrderBLL class represents the business logic layer for handling product in order-related operations.
 It interacts with the ProductInOrderDAO class to insert and retrieve product in order data.
 */

public class ProductInOrderBLL {

    private ProductInOrderDAO productInOrderDAO;

    public ProductInOrderBLL() {

        this.productInOrderDAO = new ProductInOrderDAO();
    }

    public void addProduct(int idProduct, int idOrder, int quantity){
        productInOrderDAO.addProduct(idProduct,idOrder,quantity);
    }

    public void addQuantity(int quantity){
        productInOrderDAO.addQuantity(quantity);
    }

    public void insertProduct(ProductInOrder newInstance){
        productInOrderDAO.insertElement(newInstance);
    }
public List<String> selectProductsName(int id){
        return productInOrderDAO.selectProductsInorder(id);
}

    public List<Integer> selectProductsQuantity(int id){
        return productInOrderDAO.selectProductsQuantity(id);
    }
}
