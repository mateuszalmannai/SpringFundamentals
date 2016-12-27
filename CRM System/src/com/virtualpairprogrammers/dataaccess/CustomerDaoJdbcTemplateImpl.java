package com.virtualpairprogrammers.dataaccess;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

  private static final String CREATE_CUSTOMER_TABLE_SQL = "CREATE TABLE CUSTOMER(CUSTOMER_ID VARCHAR(20), COMPANY_NAME VARCHAR(50), EMAIL VARCHAR(50), TELEPHONE VARCHAR(20), NOTES VARCHAR(255))";
  private static final String CREATE_CALL_TABLE_SQL = "CREATE TABLE TBL_CALL(NOTES VARCHAR(255), TIME_AND_DATE DATE, CUSTOMER_ID VARCHAR(20))";
  private static final String INSERT_CUSTOMER_SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, EMAIL, TELEPHONE, NOTES) VALUES (?,?,?,?,?)";
  private static final String INSERT_CALL_SQL = "INSERT INTO TBL_CALL (NOTES, TIME_AND_DATE, CUSTOMER_ID) VALUES (?,?,?)";
  private static final String CUSTOMER_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
  private static final String CUSTOMER_BY_NAME_SQL = "SELECT * FROM CUSTOMER WHERE COMPANY_NAME=?";
  private static final String DELETE_CUSTOMER_SQL = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
  private static final String GET_ALL_CUSTOMERS = "SELECT * FROM CUSTOMER";
  private static final String UPDATE_CUSTOMER_SQL = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, TELEPHONE=?, NOTES=? WHERE CUSTOMER_ID = ?";
  private static final String ALL_CALLS_FOR_CUSTOMER_SQL = "SELECT * FROM TBL_CALL WHERE CUSTOMER_ID=?";


  private JdbcTemplate jdbcTemplate;

  public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // called automatically by Spring when the bean is created
  private void createTables() {
    try {
      this.jdbcTemplate.update(CREATE_CUSTOMER_TABLE_SQL);
    } catch (BadSqlGrammarException e) {
      System.err.println("Assuming the customer table already exists.");
    }
    try {
      // if you have two or more classes in a very tight relationship then it makes sense to use the same DAO
      this.jdbcTemplate.update(CREATE_CALL_TABLE_SQL);
    } catch (BadSqlGrammarException e) {
      System.err.println("Assuming the call table already exists.");
    }
  }

  @Override
  public void create(Customer customer) {
    jdbcTemplate.update(INSERT_CUSTOMER_SQL,
      customer.getCustomerId(),
      customer.getCompanyName(),
      customer.getEmail(),
      customer.getTelephone(),
      customer.getNotes());
  }

  @Override
  public Customer getById(String customerId) throws RecordNotFoundException {
    try {
      return jdbcTemplate.queryForObject(CUSTOMER_BY_ID_SQL, new CustomerMapper(), customerId);
    } catch (IncorrectResultSizeDataAccessException e) {
      // convert Spring exception into an exception that the client understands
      throw new RecordNotFoundException();
    }
  }

  @Override
  public List<Customer> getByName(String name) {
    return jdbcTemplate.query(CUSTOMER_BY_NAME_SQL, new CustomerMapper(), name);
  }

  @Override
  public void update(Customer customerToUpdate) throws RecordNotFoundException {
    final int rowsUpdated = jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
      customerToUpdate.getCustomerId(),
      customerToUpdate.getCompanyName(),
      customerToUpdate.getEmail(),
      customerToUpdate.getTelephone(),
      customerToUpdate.getNotes());

    if (rowsUpdated != 1) {
      throw new RecordNotFoundException();
    }
  }

  @Override
  public void delete(Customer oldCustomer) throws RecordNotFoundException {
    final int rowsUpdated = jdbcTemplate.update(DELETE_CUSTOMER_SQL, oldCustomer.getCustomerId());

    if (rowsUpdated != 1) {
      throw new RecordNotFoundException();
    }
  }

  @Override
  public List<Customer> getAllCustomers() {
    return jdbcTemplate.query(GET_ALL_CUSTOMERS, new CustomerMapper());
  }

  @Override
  public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
    final Customer customer = getById(customerId);

    if (customer == null) {
      throw new RecordNotFoundException();
    }

    List<Call> allCallsForCustomer = jdbcTemplate.query(ALL_CALLS_FOR_CUSTOMER_SQL, new CallRowMapper(), customerId);

    customer.setCalls(allCallsForCustomer);

    return customer;
  }

  @Override
  public void addCall(Call newCall, String customerId) throws RecordNotFoundException {

    getById(customerId);
    // the next line will only execute if the line above doesn't throw an exception
    final int rowsUpdated = jdbcTemplate.update(INSERT_CALL_SQL, newCall.getNotes(), newCall.getTimeAndDate(), customerId);

    if (rowsUpdated != 1) {
      throw new RecordNotFoundException();
    }
  }

  private class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
      final String customerId = resultSet.getString("CUSTOMER_ID");
      final String companyName = resultSet.getString("COMPANY_NAME");
      final String email = resultSet.getString("EMAIL");
      final String telephone = resultSet.getString("TELEPHONE");
      final String notes = resultSet.getString("NOTES");
      return new Customer(customerId, companyName, email, telephone, notes);
    }
  }

  private class CallRowMapper implements RowMapper<Call> {
    @Override
    public Call mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
      final String notes = resultSet.getString("NOTES");
      // Java.util.Date is a superclass of SQL.Date returned by resultSet.getDate()
      final Date dateAndTime = resultSet.getDate("TIME_AND_DATE");
      // CUSTOMER_ID in the table is a foreign key and java class doesn't have a reference to the owning customer
      return new Call(notes, dateAndTime);
    }
  }
}
