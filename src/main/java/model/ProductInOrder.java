package model;
/**
 * The ProductInOrder class represents a product in an order, identified by the order ID, product ID, and quantity.
 */
public class ProductInOrder {

    private int idorder;
    private int idproduct;
    private int quantity;

//    public productinorder(int idOrder, int idProduct) {
//        this.idOrder = idOrder;
//        this.idProduct = idProduct;
//    }

    public ProductInOrder(){

    }

    public int isIdorder() {
        return idorder;
    }

    public int isIdproduct() {
        return idproduct;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int isQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
