package com.virtualpairprogrammers.services.diary;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.domain.Action;

import java.util.List;

public class DiaryManagementServiceProductionImpl implements DiaryManagementService {

  private ActionDao actionDao;

  public DiaryManagementServiceProductionImpl(ActionDao actionDao) {
    this.actionDao = actionDao;
  }

  @Override
  public void recordAction(Action action) {
    actionDao.create(action);
  }

  @Override
  public List<Action> getAllIncompleteActions(String requiredUser) {
    return actionDao.getIncompleteActions(requiredUser);
  }
}
