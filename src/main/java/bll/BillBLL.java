package bll;

import dao.BillDAO;
/**

 The BillBLL class represents the business logic layer for handling bill-related operations.
 It interacts with the BillDAO class to retrieve bill data and perform calculations.
 */
public class BillBLL {

    private BillDAO billDAO = new BillDAO();
    /**
     * Calculates the total price for a bill with the given ID.
     *
     * @param id The ID of the bill.
     * @return The total price of the bill.
     */

    public float totalPrice(int id){
        return billDAO.totalPrice(id);
    }
}
