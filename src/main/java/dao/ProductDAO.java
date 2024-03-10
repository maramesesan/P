package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends AbstractDAO<Product> {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

    /**

     Finds a product by name.

     @param name The name of the product.

     @return The found product, or null if not found.
     */
    public Product findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**

     Updates a product by name.

     @param p The updated product.

     @param name The name of the product to update.

     @return The updated product.
     */
    public Product updateByName(Product p, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery("name");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateParameters(statement, p);
            statement.setString(5, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return p;
    }

    private void setUpdateStringParameters(PreparedStatement statement, String newField, String initialName) throws SQLException {
        statement.setString(1, newField);
        statement.setString(2, initialName);
    }


    public Product updateOneField(String initialName, String newField, String fieldName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateFieldQuery(fieldName);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateStringParameters(statement, newField, initialName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private void setUpdateIntegerParameters(PreparedStatement statement, int newField, String initialName) throws SQLException {
        statement.setInt(1, newField);
        statement.setString(2, initialName);
    }

    public Product updateOneFieldInteger(String initialName, int newField, String fieldName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateFieldQuery(fieldName);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateIntegerParameters(statement, newField, initialName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private void setUpdateFloatParameters(PreparedStatement statement, float newField, String initialName) throws SQLException {
        statement.setFloat(1, newField);
        statement.setString(2, initialName);
    }

    public Product updateOneFieldFloat(String initialName, float newField, String fieldName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateFieldQuery(fieldName);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateFloatParameters(statement, newField, initialName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public void decrementStock(int idProduct, int decrement) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE product SET stock = stock - ? WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, decrement);
            statement.setInt(2, idProduct);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing decrementStock query: " + e.getMessage(), e);
        }
    }

    public int checkStock(int idProduct) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT stock FROM product WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idProduct);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int currentStock = rs.getInt("stock");
                return currentStock;
//                if (givenQuantity <= currentStock) {
//                    System.out.println("There are enough products in stock.");
//                } else {
//                    System.out.println("Not enough products available.");
//                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private String selectByNameQuery(String fieldName){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(fieldName);
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE name =?");
        return sb.toString();
    }

    public float selectPrice(String name) {
        float price = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = selectByNameQuery("price");;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("price");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

}
