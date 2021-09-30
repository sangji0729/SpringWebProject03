package com.sangji0729.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangji0729.common.CommandMap;
import com.sangji0729.dao.TestDAO;
@Service("testService")
public class TestServiceImp implements TestService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return testDAO.boardList(map);
	}

	@Override
	public void write(Map<String, Object> map) {
		
	}

	
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return testDAO.detail(map);
	}

	@Override
	public int delete(Map<String, Object> map) {
		return testDAO.delete(map);
	}

	@Override
	public int update(Map<String, Object> map) {
		return 0;
	}

	@Override
	public String getCategory(Map<String, Object> map) {
		return null;
	}

	@Override
	public List<HashMap<String, Object>> categoryList(Map<String, Object> map) {
		return null;
	}

	@Override
	public int totalList(Map<String, Object> map) {
		return 0;
	}

	@Override
	public void viewCount(Map<String, Object> map) {
		
	}

	@Override
	public void like(Map<String, Object> map) {
		
	}

	public int totalCount(Map<String, Object> map) {
		return testDAO.totalCount(map);
	}

}
