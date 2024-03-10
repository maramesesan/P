package bll;

import dao.ClientDAO;
import model.Client;
import validators.ClientAgeValidator;
import validators.EmailValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**

 The ClientBLL class represents the business logic layer for handling client-related operations.
 It interacts with the ClientDAO class to retrieve and manipulate client data.
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object.
     * Initializes the list of validators and creates a ClientDAO instance.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        this.clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by the specified ID.
     *
     * @param id The ID of the client to find.
     * @return The found client.
     * @throws NoSuchElementException If the client with the given ID is not found.
     */
    public Client findClientById(int id) {
        Client st = (Client) clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Finds a client by the specified name.
     *
     * @param name The name of the client to find.
     * @return The found client.
     * @throws NoSuchElementException If the client with the given name is not found.
     */

    public Client findClientByName(String name){
        Client st = (Client) clientDAO.findByName(name);
        if (st == null) {
            throw new NoSuchElementException("The client with name =" + name + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all clients.
     *
     * @return The list of all clients.
     * @throws NoSuchElementException If there are no clients in the table.
     */
    public List<Client> selectAllClients() {
        List<Client> st = clientDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("No clients in table");
        }
        return st;
    }



    /**
     * Inserts a new client.
     *
     * @param newClient The client to insert.
     */

    public void insertClient(Client newClient){
        clientDAO.insertElement(newClient);
        System.out.println(newClient.isName());
    }


    /**
     * Updates an existing client.
     *
     * @param newClient The updated client data.
     * @param id        The ID of the client to update.
     */
    public void updateClient(Client newClient, int id){
        clientDAO.update(newClient, id);
    }

    /**
     * Deletes a client by the specified ID.
     *
     * @param id The ID of the client to delete.
     */
    public void deleteClientById(int id){
        clientDAO.deleteById(id,"id");
    }

    /**
     * Deletes a client by the specified name.
     *
     * @param name The name of the client to delete.
     */
    public void deleteClientByName(String name){
        clientDAO.deleteByString(name,"name");
    }

    /**
     * Updates the name of a client.
     *
     * @param initialName The initial name of the client.
     * @param newName     The new name to set.
     */
    public void updateName(String initialName, String newName) {
      clientDAO.updateOneField(initialName,newName,"name");
    }

    /**
     * Updates the password of a client.
     *
     * @param initialName The initial name of the client.
     * @param newPassword     The new name to set.
     */
    public void updatePassword(String initialName, String newPassword) {
        clientDAO.updateOneField(initialName,newPassword,"password");
    }

    /**
     * Updates the email of a client.
     *
     */
    public void updateEmail(String name, String newEmail){
        clientDAO.updateOneField(name,newEmail,"email");
    }

    /**
     * Updates the age of a client.
     *

     */
    public void updateAge(String name, int newAge){
        clientDAO.updateOneFieldInteger(name,newAge,"age");
    }

    /**
     * Return the id of a client by name.
     *
     * @param name The initial name of the client.
     */
    public int returnIdByName(String name){
       return clientDAO.findIdByName(name);
    }

    /**
     * Return name by id
     *
     * @param id The initial name of the client.

     */
    public String returnNameById(int id){
        return clientDAO.findNameById(id);
    }

}
