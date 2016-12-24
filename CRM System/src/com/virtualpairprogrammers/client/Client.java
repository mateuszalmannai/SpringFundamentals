package com.virtualpairprogrammers.client;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Client {
  public static void main(String[] args) {
    final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

    final CustomerManagementService customerManagementService = container.getBean(CustomerManagementService.class);

    final List<Customer> allCustomers = customerManagementService.getAllCustomers();
    allCustomers.forEach(System.out::println);

    container.close();
  }
}
