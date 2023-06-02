package com.app.acerosarequipa.mappers;

import java.util.List;

import com.app.acerosarequipa.model.RequirementRequest;

public interface RequirementRequestMapper {
	
	List<RequirementRequest> findAll(String query);
	
	RequirementRequest findById(String query);
	
	Boolean insert(RequirementRequest bean);
	
	Boolean update(RequirementRequest bean);
	
	Boolean updateFile(RequirementRequest bean);
	
	Boolean delete(Integer id);

}
