package com.sangji0729.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	protected void printQueryId(String queryId) {
		if(log.isDebugEnabled()) {
			log.debug("\t QueryId \t : " + queryId);
		}
	}
	
	public List<Map<String, Object>> selectList(String queryId, Map<String, Object> map) {
		//printQueryId(queryId);
		return sqlSession.selectList(queryId, map);
	}
	
	public Map<String, Object> selectOne(String queryId, Map<String, Object> map){
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, map);
	}
	
	public int delete(String queryId, Map<String, Object> map) {
		printQueryId(queryId);
		return sqlSession.delete(queryId, map);
	}
	
	public int insert(String queryId, Map<String, Object> map){
		printQueryId(queryId);
		return sqlSession.insert(queryId, map);
	}
	
	public void update(String queryId, Map<String, Object> map){
		printQueryId(queryId);
		sqlSession.selectOne(queryId, map);
	}
	
	public String getName(String queryId, Map<String, Object> map){
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, map);
	}
	
	public int sendMessage(String queryId, Map<String, Object> map) {
		printQueryId(queryId);
		return sqlSession.insert(queryId, map);
	}
	
	public int deleteMessage(String queryId, Map<String, Object> map) {
		printQueryId(queryId);
		return sqlSession.insert(queryId, map);
	}
}
