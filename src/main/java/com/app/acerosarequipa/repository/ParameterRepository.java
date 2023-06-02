package com.app.acerosarequipa.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.acerosarequipa.mappers.ParameterMapper;
import com.app.acerosarequipa.model.Parameter;

@Repository
public class ParameterRepository implements ParameterMapper{
	
	private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ParameterRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<Parameter> findAll(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectList("findAll", hm);
    }
    
    @Override
    public Parameter findById(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectOne("findById", hm);
    }
    
    @Override
    public Boolean insert(Parameter bean) {
        Boolean flag = false;
        try {
        	sqlSessionTemplate.insert("insert", bean);            
        	sqlSessionTemplate.commit();
            flag = true;
        } finally {
        	sqlSessionTemplate.close();
        }
        return flag;
    }
    
    @Override
    public Boolean update(Parameter bean){
        Boolean flag=false;
        try {
        	sqlSessionTemplate.update("update", bean);            
        	sqlSessionTemplate.commit();
            flag=true;
        } finally {
        	sqlSessionTemplate.close();
        }
        return flag;
    }
    
    @Override
    public Boolean delete(Integer id){
        Boolean flag=false;
        try {
        	sqlSessionTemplate.delete("delete", id);            
        	sqlSessionTemplate.commit();
            flag=true;
        } finally {
        	sqlSessionTemplate.close();
        }
        return flag;
    }

}
