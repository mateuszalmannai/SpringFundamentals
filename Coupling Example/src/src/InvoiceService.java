package src;

public class InvoiceService {

  // Step 1: programming to an interface: we don't care about the specific implementation
  private InvoicingDAO dao;

  // Step 2: dependency injection, i.e. allow for the object to be created externally
  public void setDao(InvoicingDAO dao) {
    this.dao = dao;
  }

  public void raiseInvoice(Invoice newInvoice) {

    dao.save(newInvoice);

    // apply some logic to the inovice

  }

  public void updateInvoice(Invoice invoiceToUpdate) {
    dao.update(invoiceToUpdate);
  }

  public void deleteInvoice(Invoice invoiceToDelete) {
    dao.delete(invoiceToDelete);
  }

}