package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO extends AbstractDAO<Client> {

    /**
     Data Access Object for managing Client entities in the database.
     */
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

    /**

     Finds a client by name.

     @param name The name of the client to find.

     @return The found client, or null if not found.
     */
    public Client findByName(String name){
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

        public Client updateByName(Client c, String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery("name");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateParameters(statement, c);
            statement.setString(5, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return c;
    }

    private void setUpdateStringParameters(PreparedStatement statement, String newField, String initialName) throws SQLException {
        statement.setString(1, newField);
        statement.setString(2, initialName);
    }


    public Client updateOneField(String initialName, String newField, String fieldName) {
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

    public Client updateOneFieldInteger(String initialName, int newField, String fieldName) {
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




}
