package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookDao;
import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

import java.util.List;

/**
 * This is the production implementation
 * It will be calling a real database
 */
public class BookServiceProductionImpl implements BookService {

  private BookDao bookDao;

  public BookServiceProductionImpl(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public List<Book> getAllBooksByAuthor(String author) {
    return bookDao.findBooksByAuthor(author);
  }

  @Override
  public List<Book> getAllRecommendedBooks(String userId) {
    return bookDao.allBooks();
  }

  @Override
  public Book getBookByIsbn(String isbn) throws BookNotFoundException {
    return bookDao.findByIsbn(isbn);
  }

  @Override
  public List<Book> getEntireCatalogue() {
    return bookDao.allBooks();
  }

  @Override
  public void registerNewBook(Book newBook) {
    // we want this book to be put into the database.
    bookDao.create(newBook);
  }
}
