package com.virtualpairprogrammers.services.calls;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

import java.util.Collection;

public class CallHandlingServiceImpl implements CallHandlingService {

  private CustomerManagementService customerManagementService;
  private DiaryManagementService diaryManagementService;

  public CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService) {
    this.customerManagementService = customerManagementService;
    this.diaryManagementService = diaryManagementService;
  }

  @Override
  public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
    // 1. Call the customer service to record the call
    customerManagementService.recordCall(customerId, newCall);
    // 2. Call the diary service to record the actions
    for (Action action : actions) {
      diaryManagementService.recordAction(action);
    }
  }
}
