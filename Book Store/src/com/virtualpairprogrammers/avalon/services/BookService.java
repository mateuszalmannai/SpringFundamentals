package com.virtualpairprogrammers.avalon.services;

import java.util.List;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

public interface BookService {
  List<Book> getAllBooksByAuthor(String author);

  List<Book> getAllRecommendedBooks(String userId);

  Book getBookByIsbn(String isbn) throws BookNotFoundException;

  List<Book> getEntireCatalogue();

  void registerNewBook(Book newBook);
}
