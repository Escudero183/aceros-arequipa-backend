package com.app.acerosarequipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.acerosarequipa.mappers.RequirementRequestMapper;
import com.app.acerosarequipa.model.RequirementRequest;

@Service
public class RequirementRequestService {
	
	@Autowired
	private RequirementRequestMapper requirementRequestMapper;
	
	public List<RequirementRequest> findAll(String query) {
		if (!query.equals("")) {
			query = " WHERE t1.code = " + query + " or t1.title like '%" + query + "%' or (t2.first_name like '%" + query + "%' or t2.last_name like '%" + query + "%' or t2.document '%" + query + "%')";
		}
		return requirementRequestMapper.findAll(query);
	}
	
	public RequirementRequest findById(Integer id) {
		String query = " WHERE t1.id_requirement_request = " + id;
		return requirementRequestMapper.findById(query);
	}
	
	public Boolean insert(RequirementRequest bean) {
		return requirementRequestMapper.insert(bean);
	}
	
	public Boolean update(RequirementRequest bean) {
		return requirementRequestMapper.update(bean);
	}
	
	public Boolean updateFile(RequirementRequest bean) {
		return requirementRequestMapper.updateFile(bean);
	}
	
	public Boolean delete (Integer id) {
		return requirementRequestMapper.delete(id);		
	}

}
