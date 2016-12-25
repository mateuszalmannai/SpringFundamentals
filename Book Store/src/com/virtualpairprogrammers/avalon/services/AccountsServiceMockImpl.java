package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;

public class AccountsServiceMockImpl implements AccountsService {

  @Override
  public void raiseInvoice(Book requiredBook) {
    // a very basic implementation for testing
    System.out.printf("Raised the invoice for " + requiredBook);
  }
}
