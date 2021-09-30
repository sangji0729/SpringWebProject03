package com.sangji0729.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("joinDAO")
public class JoinDAO extends AbstractDAO{

	public int join(Map<String, Object> map) {
		return insert("join.join", map);
	}
	
}
