package com.virtualpairprogrammers.dataaccess;

import java.util.List;

import com.virtualpairprogrammers.domain.Action;

// FOR USE IN A LATER CHAPTER - PLEASE IGNORE UNTIL THEN

public interface ActionDao 
{
	void create(Action newAction);
	List<Action> getIncompleteActions(String userId);
	void update(Action actionToUpdate) throws RecordNotFoundException;
	void delete(Action oldAction) throws RecordNotFoundException;
}
