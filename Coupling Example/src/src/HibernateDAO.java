package src;

public class HibernateDAO implements InvoicingDAO{

  @Override
  public void save(Invoice newInvoice) {
    System.out.println("Saving an invoice using Hibernate.");
  }

  @Override
  public void delete(Invoice oldInvoice) {
    System.out.println("Deleting an invoice using Hibernate.");
  }

  @Override
  public void update(Invoice invoiceToCancel) {
    System.out.println("Updating an invoice using Hibernate.");
  }
}
