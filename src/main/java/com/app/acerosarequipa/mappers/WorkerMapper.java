package com.app.acerosarequipa.mappers;

import java.util.List;

import com.app.acerosarequipa.model.Worker;

public interface WorkerMapper {
	
	List<Worker> findAll(String query);
	
	Worker findById(String query);
	
	Boolean insert(Worker bean);
	
	Boolean update(Worker bean);
	
	Boolean delete(Integer id);

}
