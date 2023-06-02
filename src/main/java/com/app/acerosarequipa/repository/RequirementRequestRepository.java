package com.app.acerosarequipa.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.acerosarequipa.mappers.RequirementRequestMapper;
import com.app.acerosarequipa.model.RequirementRequest;

@Repository
public class RequirementRequestRepository implements RequirementRequestMapper{
	
	private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public RequirementRequestRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<RequirementRequest> findAll(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectList("findAll", hm);
    }
    
    @Override
    public RequirementRequest findById(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectOne("findById", hm);
    }
    
    @Override
    public Boolean insert(RequirementRequest bean) {
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
    public Boolean update(RequirementRequest bean){
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
    public Boolean updateFile(RequirementRequest bean){
        Boolean flag=false;
        try {
        	sqlSessionTemplate.update("updateFile", bean);            
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
