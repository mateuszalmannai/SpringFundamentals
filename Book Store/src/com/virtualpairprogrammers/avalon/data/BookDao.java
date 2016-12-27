package com.virtualpairprogrammers.avalon.data;

import com.virtualpairprogrammers.avalon.domain.Book;

import java.util.List;

public interface BookDao {
  List<Book> allBooks();

  Book findByIsbn(String isbn) throws BookNotFoundException;

  void create(Book newBook);

  void delete(Book redundantBook);

  List<Book> findBooksByAuthor(String author);
}
