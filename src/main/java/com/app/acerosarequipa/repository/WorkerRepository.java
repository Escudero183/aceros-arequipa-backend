package com.app.acerosarequipa.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.acerosarequipa.mappers.WorkerMapper;
import com.app.acerosarequipa.model.Worker;

@Repository
public class WorkerRepository implements WorkerMapper{
	
	private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public WorkerRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<Worker> findAll(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectList("findAll", hm);
    }
    
    @Override
    public Worker findById(String query) {
    	HashMap<String, Object> hm = new HashMap<>();
        hm.put("q", query);
        return sqlSessionTemplate.selectOne("findById", hm);
    }
    
    @Override
    public Boolean insert(Worker bean) {
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
    public Boolean update(Worker bean){
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
