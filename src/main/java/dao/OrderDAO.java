package dao;

import connection.ConnectionFactory;
import model.Order;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**

 Data Access Object for managing Order entities in the database.
 */

public class OrderDAO extends AbstractDAO<Order>{
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    private String createAddClientQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO \"order\" (id, idclient) VALUES (?, ?)");
        return sb.toString();
    }

    private int setId() throws SQLException {
        int maxId = 0;
        String query = "SELECT MAX(id) AS max FROM \"order\"";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
        ) {
            if (rs.next()) {
                maxId = rs.getInt("max");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxId + 1;
    }
    /**

     Inserts a client into an order.

     @param id The ID of the client.

     @return The inserted order.
     */
    public Order insertClient(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createAddClientQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            int newId = setId(); // Get the new ID using the setId() function
            statement.setInt(1, newId);
            statement.setInt(2, id);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**

     Returns the ID of the last order associated with a client.

     @param idClient The ID of the client.

     @return The ID of the last order associated with the client.
     */
    public int returnOrderId(int idClient){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT MAX(id) as max FROM \"order\" WHERE idclient = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("max");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return 0;
    }

}
