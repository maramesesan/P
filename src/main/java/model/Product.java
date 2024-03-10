package model;
/**
 * The Product class represents a product with an ID, name, price, and stock.
 */
public class Product {

    private int id;
    private String name;
    private float price;
    private int stock;

    public Product(){

    }

    public int isStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int isId() {
        return id;
    }

    public String isName() {
        return name;
    }

    public float isPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
