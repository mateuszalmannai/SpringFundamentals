package com.virtualpairprogrammers.services.customers;

import java.util.List;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

/**
 * This interface defines the functionality we want the Customer Management Service
 * to offer.
 * <p/>
 * Your job is to implement it. In the early sessions you will write a "Mock" to simulate
 * a Customer Management Service. In later sessions you'll provide a Database backed implementation.
 */
public interface CustomerManagementService {
  /**
   * Takes a customer domain object and saves it in the database
   */
  void newCustomer(Customer newCustomer);

  /**
   * The specified customer is updated in the database.
   */
  void updateCustomer(Customer changedCustomer);

  /**
   * The specified customer is removed from the database
   */
  void deleteCustomer(Customer oldCustomer);

  /**
   * Finds the customer by Id
   */
  Customer findCustomerById(String customerId) throws CustomerNotFoundException;

  /**
   * Finds customers by their name. Note that in a full system, we'd
   * probably offer more sophisticated searching functionality, but for the
   * practical this will do for now.
   */
  List<Customer> findCustomersByName(String name);

  /**
   * Returns a complete list of the customers in the system.
   */
  List<Customer> getAllCustomers();

  /**
   * For the specified customer, returns the customer object together
   * will all calls associated with that customer
   *
   * @throws CustomerNotFoundException
   */
  Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException;


  /**
   * Records a call against a particular customer
   * <p>
   * (note that this could be achieved by calling the udpate method, but this is a more
   * convenient interface)
   */
  void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException;
}
