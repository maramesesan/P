package bll;

import dao.OrderDAO;
/**

 The OrderBLL class represents the business logic layer for handling order-related operations.
 It interacts with the OrderDAO class to insert and retrieve order data.
 */
public class OrderBLL {

    private OrderDAO orderDAO;

    /**
     * Constructs a new OrderBLL object.
     * Creates an instance of OrderDAO.
     */
    public OrderBLL() {

        this.orderDAO = new OrderDAO();
    }
    /**
     * Adds a client to the order.
     *
     * @param id The ID of the client to add.
     */
    public void addClient(int id){
        orderDAO.insertClient(id);
    }

    /**
     * Returns the order ID for the specified client ID.
     *
     * @param idClient The ID of the client.
     * @return The order ID.
     */
    public int returnOrderId(int idClient){
        return orderDAO.returnOrderId(idClient);
    }


}
