package model;

/**
 * The Bill class represents a bill with an order ID and a price.
 */
public class Bill {

    private int idorder;
    private float price;

    public Bill() {
    }
    /**
     * Returns the order ID associated with the bill.
     *
     * @return The order ID.
     */
    public int isIdorder() {
        return idorder;
    }

    public float isPrice() {
        return price;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
