package com.app.acerosarequipa.mappers;

import java.util.List;

import com.app.acerosarequipa.model.Parameter;

public interface ParameterMapper {
	
	List<Parameter> findAll(String query);
	
	Parameter findById(String query);
	
	Boolean insert(Parameter bean);
	
	Boolean update(Parameter bean);
	
	Boolean delete(Integer id);

}
