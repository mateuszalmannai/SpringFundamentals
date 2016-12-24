package src;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvoiceServiceTest {
  @Test
  public void testThatSendingAnInvalidInvoiceWillThrowAnException() throws Exception {

    final InvoiceService service = new InvoiceService();
    final Invoice invalidInvoice = new Invoice("38378373X", "Jack Jones");

    service.raiseInvoice(invalidInvoice);

    // test that an exception is thrown

  }

}