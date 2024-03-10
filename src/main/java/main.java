import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
import view.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
/**
 * The main class is the entry point of the application.
 * It initializes the necessary components and starts the program.
 */
public class main {

    protected static final Logger LOGGER = Logger.getLogger(main.class.getName());

    public static void main(String[] args) throws SQLException {

        ClientBLL clientBll = new ClientBLL();
        OrderBLL orderBLL = new OrderBLL();
        ProductBLL productBLL = new ProductBLL();

        Model model = new Model();
        MainPage view = new MainPage();
        ClientPage clientPage = new ClientPage();
        ProductPage productPage = new ProductPage();
        OrderPage orderPage = new OrderPage();
        BillPage billPage = new BillPage();
        Controller controller = new Controller(model,view);



    }
}
