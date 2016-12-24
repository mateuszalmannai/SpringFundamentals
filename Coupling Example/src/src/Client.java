package src;

public class Client {
  // make sure to delete leftover HSQL .script and .properties files when running other/new project
  public static void main(String[] args) {

    // 3. Centralise Configuration of the application
    InvoiceService invoices = CrudeContainer.getInvoiceService();


    Invoice newInvoice = new Invoice("11080", "Bill McGregor");

    invoices.raiseInvoice(newInvoice);
  }
}
