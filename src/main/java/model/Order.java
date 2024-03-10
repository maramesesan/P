package model;
/**
 * The Order class represents an order with an ID, client ID, and price.
 */
public class Order {

    private int id;
    private int idclient;
    private float price;

//    public order(int idOrder, int idClient, String timeOrder, float priceOrder) {
//        this.idOrder = idOrder;
//        this.idClient = idClient;
//        this.priceOrder = priceOrder;
//    }

    public Order(){

    }
    public int isId() {
        return id;
    }

    public int isIdclient() {
        return idclient;
    }



    public float isPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
