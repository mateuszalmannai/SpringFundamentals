package com.virtualpairprogrammers.services.customers;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerManagementServiceMockImpl implements CustomerManagementService {

  private Map<String, Customer> customerMap = new HashMap<>();

  public CustomerManagementServiceMockImpl() {
    customerMap.put("CS03939", new Customer("CS03939", "Acme Ltd", "Some notes."));
    customerMap.put("CS03940", new Customer("CS03940", "VirtualPairProgrammers", "Some notes."));
    customerMap.put("CS03941", new Customer("CS03941", "Microsoft", "Some notes."));
  }

  @Override
  public void newCustomer(Customer newCustomer) {
    customerMap.put(newCustomer.getCustomerId(), newCustomer);
  }

  @Override
  public void updateCustomer(Customer changedCustomer) {
    customerMap.put(changedCustomer.getCustomerId(), changedCustomer);
  }

  @Override
  public void deleteCustomer(Customer oldCustomer) {
    customerMap.remove(oldCustomer.getCustomerId());
  }

  @Override
  public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
    final Customer foundCustomer = customerMap.get(customerId);

    if (foundCustomer == null) throw new CustomerNotFoundException();

    return foundCustomer;
  }

  @Override
  public List<Customer> findCustomersByName(String name) {
    List<Customer> resultList = customerMap.values().stream().filter(
      customer -> customer.getCompanyName().equals(name)).collect(Collectors.toList()
    );
    return resultList;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return new ArrayList<>(customerMap.values());
  }

  @Override
  public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
    return findCustomerById(customerId);
  }

  @Override
  public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
    // get customer together with all of their calls
    final Customer customer = getFullCustomerDetail(customerId);
    // either we get a customer or an exception, which is good
    customer.addCall(callDetails);
  }
}
