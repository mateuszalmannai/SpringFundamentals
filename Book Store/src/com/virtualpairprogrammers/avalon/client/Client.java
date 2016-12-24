package com.virtualpairprogrammers.avalon.client;

import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Client {
  public static void main(String[] args) {

    final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

    final BookService bookService = container.getBean(BookService.class);

    final List<Book> allBooks = bookService.getEntireCatalogue();

    allBooks.forEach(System.out::println);

    container.close();
  }
}
