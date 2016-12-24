package src;

/**
 * Defines the behaviour of any Invoicing Data Access Object in our system
 */
public interface InvoicingDAO {

  void save(Invoice newInvoice);

  void delete(Invoice oldInvoice);

  void update(Invoice invoiceToCancel);

}
