package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BillDAO {

    protected static final Logger LOGGER = Logger.getLogger(BillDAO.class.getName());


/**

 Calculates the total price of an order based on the given order ID.

 @param idOrder The ID of the order for which to calculate the total price.

 @return The total price of the order.
  */

    public float totalPrice(int idOrder){
        float totalPrice =0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT SUM(po.quantity * p.price) as bill FROM productinorder po, product p, \"order\" o WHERE o.id=po.idorder AND po.idproduct=p.id AND o.id=?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1,idOrder);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalPrice = resultSet.getFloat("bill");
            }


        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
return totalPrice;
    }

    public float selectPrice(int id){
        float price = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT price FROM bill WHERE idorder = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getFloat("price");
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return price;
    }
}
