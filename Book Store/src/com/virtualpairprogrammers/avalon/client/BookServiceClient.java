package com.virtualpairprogrammers.avalon.client;

import com.virtualpairprogrammers.avalon.services.PurchasingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookServiceClient {
  public static void main(String[] args) {

    System.out.println("Testing buying a book...");
    String requiredIsbn = "ISBN1"; // we know this isbn is present in the mock
    final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

    final PurchasingService purchasingService = container.getBean(PurchasingService.class);

    purchasingService.buyBook(requiredIsbn);

    container.close();
  }
}
