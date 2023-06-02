package com.app.acerosarequipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.acerosarequipa.mappers.WorkerMapper;
import com.app.acerosarequipa.model.Worker;

@Service
public class WorkerService {
	
	@Autowired
	private WorkerMapper workerMapper;
	
	public List<Worker> findAll(String query, String position) {
		if (!query.equals("") || !position.equals("")) {
			query = " WHERE (t1.first_name like '%" + query + "%' or t1.last_name like '%" + query + "%' or t1.document like '%" + query + "%')"
					+ " and ('" + position + "' = '' or ('" + position + "' <> '' and t1.position = '" + position + "'))";
		}
		return workerMapper.findAll(query);
	}
	
	public Worker findById(Integer id) {
		String query = " WHERE t1.id_worker = " + id;
		return workerMapper.findById(query);
	}
	
	public Boolean insert(Worker bean) {
		return workerMapper.insert(bean);
	}
	
	public Boolean update(Worker bean) {
		return workerMapper.update(bean);
	}
	
	public Boolean delete (Integer id) {
		return workerMapper.delete(id);		
	}

}
