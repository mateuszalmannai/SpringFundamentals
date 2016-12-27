package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

/**
 * This class delegates to the accounts and book services,
 * hence there is no difference between mock and production-standard implementations
 */
public class PurchasingServiceImpl implements PurchasingService{

  private AccountsService accountsService;
  private BookService bookService;

  public PurchasingServiceImpl(AccountsService accountsService, BookService bookService) {
    this.accountsService = accountsService;
    this.bookService = bookService;
  }

  @Override
  public void buyBook(String isbn) throws BookNotFoundException {

    // find the correct book
    final Book requiredBook = bookService.getBookByIsbn(isbn);

    // now raise the invoice
    accountsService.raiseInvoice(requiredBook);
  }
}
