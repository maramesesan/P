package dao;

import connection.ConnectionFactory;
import model.Order;
import model.Product;
import model.ProductInOrder;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The ProductInOrderDAO class provides data access methods for interacting with the 'productinorder' table in the database.
 * It inherits from the AbstractDAO class and is parameterized with the ProductInOrder model class.
 */

public class ProductInOrderDAO extends AbstractDAO<ProductInOrder>{
    protected static final Logger LOGGER = Logger.getLogger(ProductInOrderDAO.class.getName());

    private String createAddProductQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO productinorder (idproduct, idorder, quantity) VALUES (?, ?, ?)");
        return sb.toString();
    }

    public ProductInOrder addProduct(int idProduct, int idOrder, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createAddProductQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idProduct);
            statement.setInt(2, idOrder);
            statement.setInt(3, quantity);
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

    private String createAddQuantityQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO productinorder (quantity) VALUES (?)");
        return sb.toString();
    }

    public ProductInOrder addQuantity(int quantity){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createAddQuantityQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, quantity);
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


    public int selectQuantity(int idProduct) {
        int quantity = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT quantity FROM productinorder WHERE idproduct = 6";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
           // statement.setInt(1, idProduct);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quantity;
    }

    public List<String> selectProductsInorder(int idOrder) {
        List<String> listProduct = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT p.name AS prodName FROM product p, productinorder o WHERE o.idproduct = p.id AND idorder = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idOrder);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String productName = resultSet.getString("prodName");
                listProduct.add(productName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return listProduct;
    }

    public List<Integer> selectProductsQuantity(int idOrder){
        List<Integer> listQuantity = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT o.quantity AS prodQuantity FROM product p, productinorder o WHERE o.idproduct = p.id AND idorder = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idOrder);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productQuant = resultSet.getInt("prodQuantity");
                listQuantity.add(productQuant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return listQuantity;
    }

}
