package src;

/**
 * A very basic demonstration of what a container does.
 * The container takes care of the 'coupling' for the system.
 */
public class CrudeContainer {

  public static InvoiceService getInvoiceService() {

    // set-up the Data Access Object
//    InvoicingDAO dao = new HibernateDAO();
    InvoicingDAO dao = new InvoicingDAOJdbcImplementation();


    // Create the service
    InvoiceService service = new InvoiceService();


    // Configure the service to use the specific DAO instance
    // Dependency Injection using setter injection
    service.setDao(dao);

    return service;

  }



}
