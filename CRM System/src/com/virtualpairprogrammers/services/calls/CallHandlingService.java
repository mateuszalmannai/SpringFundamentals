package com.virtualpairprogrammers.services.calls;

import java.util.Collection;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;

public interface CallHandlingService {

  /**
   * Records a call with the customer management service, and also records
   * any actions in the diary service
   */
  void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}
