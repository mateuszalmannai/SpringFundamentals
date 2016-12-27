package com.virtualpairprogrammers.avalon.data;

import com.virtualpairprogrammers.avalon.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoCleanerJdbcImpl implements BookDao {


  private JdbcTemplate jdbcTemplate;
  // SQL
  private static final String INSERT_BOOK_SQL = "INSERT INTO BOOK (ISBN, TITLE, AUTHOR,PRICE) VALUES (?, ?, ?, ?) ";
  private static final String CREATE_TABLE_SQL = "CREATE TABLE BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
  private static final String GET_ALL_BOOKS_SQL = "SELECT * FROM BOOK";
  private static final String BOOKS_BY_AUTHOR_SQL = "SELECT * FROM BOOK WHERE AUTHOR=?";
  private static final String BOOKS_BY_ISBN_SQL = "SELECT * FROM BOOK WHERE ISBN=?";
  private static final String DELETE_BOOK_BY_ISBN = "DELETE FROM BOOK WHERE ISBN=?";

  // constructor injection
  public BookDaoCleanerJdbcImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;

  }

  private void createTables() {
    try {
      jdbcTemplate.update(CREATE_TABLE_SQL);
    } catch (BadSqlGrammarException e) {
      System.err.println("Assuming that the table already exists.");
    }

  }

  @Override
  public List<Book> allBooks() {
    // Spring doesn't know how to convert a result set into a domain object
    return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
  }

  @Override
  public Book findByIsbn(String isbn) throws BookNotFoundException {
    try {
      return jdbcTemplate.queryForObject(BOOKS_BY_ISBN_SQL, new BookMapper(), isbn);
    } catch (EmptyResultDataAccessException e) {
      throw new BookNotFoundException();
    }
  }

  @Override
  public void create(Book newBook) {
    // using varargs instead of Object[]
    jdbcTemplate.update(INSERT_BOOK_SQL,
      newBook.getIsbn(),
      newBook.getTitle(),
      newBook.getAuthor(),
      newBook.getPrice());
  }

  @Override
  public void delete(Book redundantBook) {
    jdbcTemplate.update(DELETE_BOOK_BY_ISBN, redundantBook.getIsbn());
  }

  @Override
  public List<Book> findBooksByAuthor(String author) {
    return jdbcTemplate.query(BOOKS_BY_AUTHOR_SQL, new BookMapper(), author);
  }

  private class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
      final String isbn = resultSet.getString("ISBN");
      final String title = resultSet.getString("TITLE");
      final String author = resultSet.getString("AUTHOR");
      final double price = resultSet.getDouble("PRICE");
      return new Book(isbn, title, author, price);
    }
  }
}
