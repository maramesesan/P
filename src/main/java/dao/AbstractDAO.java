package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    Class<T> type;

    /**
     * The class for Data Access Objects.
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creates a SELECT query for a specific field.
     * @param field The field to filter the records.
     * @return The SELECT query.
     */

    String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creates a SELECT for all records in a table.
     * @return The generated SELECT query.
     */
    private String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Method that retrieves all the fields from a table
     * @return A list of objects representing the retrieved records.
     */

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * Retrieves the records based on a given id
     * @param id The ID of the table.
     * @return The object representing the retrieved record.
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
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
     * Creates objects from the result set obtained from query.
     * @param resultSet The result set containing the records.
     * @return A list of objects.
     */

    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     Creates an SQL insert query.
     @param t The object to be inserted.
     @return The generated insert query as a String.
     */
    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] f = type.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            sb.append(f[i].getName());
            if (i < f.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < f.length; i++) {
            sb.append("?");
            if (i < f.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     Retrieves the next available ID for the specified table.
     @param table The table object.
     @param id The current ID.
     @return The next available ID.
     @throws SQLException If an SQL exception occurs during the query execution.
     */
    private int makeNextId(T table, int id) throws SQLException {
        int maxId = 0;
        String tableName = type.getSimpleName();
        String query = "SELECT MAX(id) AS max FROM " + tableName;

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

        return maxId+1;
    }

    /**
     Sets the parameters for an SQL insert statement based on the specified object.
     @param statement The PreparedStatement object.
     @param t The object containing the values to be inserted.
     @throws SQLException If an SQL exception occurs during parameter setting.
     */

    private void setInsertParameters(PreparedStatement statement, T t) throws SQLException {
        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                if (field.getName().equalsIgnoreCase("id")) {
                statement.setInt(i + 1, makeNextId(t,0));
            } else {
                Object value = field.get(t);
                statement.setObject(i + 1, value);
            }
            } catch (IllegalAccessException e) {
            }
        }
    }
    /**
     Inserts an element into the database.
     @param t The element to be inserted into the database.
     @return The inserted element.
     */
    public T insertElement(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            setInsertParameters(statement, t);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
//
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    private String createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            sb.append(fields[i].getName());
            sb.append(" = ?");
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
       sb.append(" WHERE id = ?");
        return sb.toString();
    }

    void setUpdateParameters(PreparedStatement statement, T t) throws SQLException{
        Field[] fields = type.getDeclaredFields();
        int parameterIndex = 1;

        for (Field f : fields) {
            if (!f.getName().equalsIgnoreCase("id")) {
                f.setAccessible(true);
                try {
                    Object value = f.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, "Error setting update parameters: " + e.getMessage());
                }
            }
        }

        try {
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(t);
            statement.setObject(fields.length, idValue);  // Set ID parameter at the last index
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error setting ID parameter for update: " + e.getMessage());
        }
    }

    /**
     Updates an element in the database based on the given ID.
     @param t The updated element to be saved in the database.
     @param id The ID of the element to be updated.
     @return The updated element.
     */
    public T update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateParameters(statement, t);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" = ?");
        return sb.toString();
    }


    /**
     Deletes an element from the database based on the given ID and field name.
     @param id The ID of the element to be deleted.
     @param fieldName The name of the field used for deletion.
     @return The deleted element.
     */
    public T deleteById(int id, String fieldName){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(fieldName);
        System.out.println(createDeleteQuery(fieldName));

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T deleteByString(String value, String fieldName){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(fieldName);
        System.out.println(createDeleteQuery(fieldName));

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    String createUpdateQuery(String f){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            sb.append(fields[i].getName());
            sb.append(" = ?");
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(" WHERE ");
        sb.append(f);
        sb.append(" = ?");
        return sb.toString();
    }

    String createUpdateFieldQuery(String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        sb.append(fieldName);
        sb.append(" = ?");
        sb.append(" WHERE name = ?");
        return sb.toString();
    }

    String returnIdByNameQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE name = ?");

        return sb.toString();
    }

    public int findIdByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = returnIdByNameQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return 0;
    }

    String returnNameByIdQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT name ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public String findNameById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = returnNameByIdQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

}
