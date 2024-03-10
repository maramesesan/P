package bll;

import dao.ProductDAO;
import model.Client;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**

 The ProductBLL class represents the business logic layer for handling product-related operations.
 It interacts with the ProductDAO class to retrieve and manipulate product data.
 */
public class ProductBLL {


    private ProductDAO productDAO;

    /**
     * Constructs a new ProductBLL object.
     * Creates an instance of ProductDAO.
     */
    public ProductBLL() {

        this.productDAO = new ProductDAO();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product.
     * @return The found product.
     * @throws NoSuchElementException If the product with the specified ID is not found.
     */
    public Product findProductById(int id) {
        Product st = (Product) productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Finds a product by its name.
     *
     * @param name The name of the product.
     * @return The found product.
     * @throws NoSuchElementException If the product with the specified name is not found.
     */

    public Product findProductByName(String name){
        Product st = (Product) productDAO.findByName(name);
        if (st == null) {
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return st;
    }
    /**
     * Retrieves all products.
     *
     * @return The list of all products.
     * @throws NoSuchElementException If there are no products in the table.
     */
    public List<Product> selectAllProducts() {
        List<Product> st = productDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("No products in table");
        }
        return st;
    }
    /**
     * Inserts a new product.
     *
     * @param newProduct The new product to insert.
     */
    public void insertProduct(Product newProduct){
        productDAO.insertElement(newProduct);
    }

    /**
     * Updates a product with the specified ID.
     *
     * @param newProduct The updated product.
     * @param id The ID of the product to update.
     */
    public void updateProduct(Product newProduct, int id){
        productDAO.update(newProduct, id);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteClientById(int id){
        productDAO.deleteById(id,"id");
    }
    /**
     * Deletes a product by its name.
     *
     * @param name The name of the product to delete.
     */
    public void deleteProductByName(String name){
        productDAO.deleteByString(name,"name");
    }
    /**
     * Updates a product by its name.
     *
     * @param newProduct The updated product.
     * @param name The name of the product to update.
     */
    public void updateProductById(Product newProduct, String name){
        productDAO.updateByName(newProduct,name);
    }
    /**
     * Updates the name of a product.
     *
     * @param initialName The initial name of the product.
     * @param newName The new name of the product.
     */
    public void updateName(String initialName, String newName) {
        productDAO.updateOneField(initialName,newName,"name");
    }
    /**
     * Updates the stock of a product.
     *
     * @param name The initial name of the product.
     * @param newStock The new name of the product.
     */
    public void updateStock(String name, int newStock){
        productDAO.updateOneFieldInteger(name,newStock,"stock");
    }
    /**
     * Updates the pric of a product.
     *
     * @param name The initial name of the product.
     * @param newPrice The new name of the product.
     */
    public void updatePrice(String name, float newPrice){
        productDAO.updateOneFieldFloat(name,newPrice,"price");
    }

    /**
     * Decrements a stock
     *
     * @param idProduct The initial name of the product.
     * @param decrementValue The new name of the product.
     */
    public void decrementStock(int idProduct, int decrementValue){
        productDAO.decrementStock(idProduct,decrementValue);

    }

    /**
     * Returns Id
     *
     * @param name The initial name of the product.
     */
    public int returnIdByName(String name){
        return productDAO.findIdByName(name);
    }

    /**
     * Checks the stock
     *
     */
    public boolean checkStock(int idProduct, int giveQuantity){
        if(productDAO.checkStock(idProduct)>=giveQuantity){
            return true;
        } else return false;

    }




}
