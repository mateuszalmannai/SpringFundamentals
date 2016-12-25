package com.virtualpairprogrammers.avalon.client;

import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class BookServiceClient {
  public static void main(String[] args) {

    final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

    final BookService bookService = container.getBean(BookService.class);

    bookService.registerNewBook(new Book("2345458098098", "War and Peace", "Leo Tolstoy", 10.99));

    final List<Book> allBooks = bookService.getEntireCatalogue();

    for (Book book : allBooks) {
      System.out.println(book);
    }

    container.close();
  }
}
