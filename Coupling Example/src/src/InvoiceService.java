package src;

public class InvoiceService {
  public void raiseInvoice(Invoice newInvoice) {
    HibernateDAO dao = new HibernateDAO();
    dao.save(newInvoice);

    // apply some logic to the inovice

  }

}