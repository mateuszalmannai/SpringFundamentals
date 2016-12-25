package com.virtualpairprogrammers.client;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CrmClient {
  public static void main(String[] args) {

    final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

    final CallHandlingService callHandlingService = container.getBean(CallHandlingService.class);
    final DiaryManagementService diaryManagementService = container.getBean(DiaryManagementService.class);

    final Call newCall = new Call("Larry Wall called from Acme Corp");
    final String requiredUser = "rac";
    final GregorianCalendar gregorianCalendar = new GregorianCalendar(2016, 1, 1);
    final Action action1 = new Action("Call back Larry to ask how things are going", gregorianCalendar, requiredUser);
    final Action action2 = new Action("Check our sales dept to make sure Larry is being tracked", gregorianCalendar, requiredUser);
    List<Action> actions = new ArrayList<>();
    actions.add(action1);
    actions.add(action2);

    final String customerId = "CS03939";
    try {
      callHandlingService.recordCall(customerId, newCall, actions);
    } catch (CustomerNotFoundException e) {
      System.err.println("Customer with id: " +  customerId + " doesn't exist");
    }

    System.out.println("Here are the outstanding actions: ");
    final List<Action> incompleteActions = diaryManagementService.getAllIncompleteActions(requiredUser);
    for (Action action : incompleteActions) {
      System.out.println(action);
    }

    container.close();
  }
}
