package com.virtualpairprogrammers.services.customers;

import com.virtualpairprogrammers.dataaccess.CustomerDao;
import com.virtualpairprogrammers.dataaccess.RecordNotFoundException;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

import java.util.List;

/**
 * Because this is a training course, these methods simply delegate to the DAO.
 * On a real project you will find some kind of logic appearing on these methods.
 * I.e. there may be logic connecting the output of several DAOs together.
 */
public class CustomerManagementServiceProductionImpl implements CustomerManagementService {

  private CustomerDao customerDao;

  public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  @Override
  public void newCustomer(Customer newCustomer) {
    customerDao.create(newCustomer);
  }

  @Override
  public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException {
    try {
      customerDao.update(changedCustomer);
    } catch (RecordNotFoundException e) {
      throw new CustomerNotFoundException();
    }
  }

  @Override
  public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException {
    try {
      customerDao.delete(oldCustomer);
    } catch (RecordNotFoundException e) {
      throw new CustomerNotFoundException();
    }
  }

  @Override
  public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
    try {
      return customerDao.getById(customerId);
    } catch (RecordNotFoundException e) {
      throw new CustomerNotFoundException();
    }
  }

  @Override
  public List<Customer> findCustomersByName(String name) {
    return customerDao.getByName(name);
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerDao.getAllCustomers();
  }

  @Override
  public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
    try {
      return customerDao.getFullCustomerDetail(customerId);
    } catch (RecordNotFoundException e) {
      throw new CustomerNotFoundException();
    }
  }

  @Override
  public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
    try {
      customerDao.addCall(callDetails, customerId);
    } catch (RecordNotFoundException e) {
      throw new CustomerNotFoundException();
    }
  }
}
