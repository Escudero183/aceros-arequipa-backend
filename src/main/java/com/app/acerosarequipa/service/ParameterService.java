package com.app.acerosarequipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.acerosarequipa.mappers.ParameterMapper;
import com.app.acerosarequipa.model.Parameter;

@Service
public class ParameterService {
	
	@Autowired
	private ParameterMapper parameterMapper;
	
	public List<Parameter> findAll(String query) {
		if (!query.equals("")) {
			query = " WHERE t1.keyparam like '%" + query + "%'";
		}
		return parameterMapper.findAll(query);
	}
	
	public Parameter findById(Integer id) {
		String query = " WHERE t1.id_parameter = " + id;
		return parameterMapper.findById(query);
	}
	
	public Boolean insert(Parameter bean) {
		return parameterMapper.insert(bean);
	}
	
	public Boolean update(Parameter bean) {
		return parameterMapper.update(bean);
	}
	
	public Boolean delete (Integer id) {
		return parameterMapper.delete(id);		
	}

}
