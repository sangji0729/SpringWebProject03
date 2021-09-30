package com.sangji0729.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{
	//AbstractDAO가 가지고 있는 모든 기능을 내것처럼 사용합니다(ex. sqlSession)

	public Map<String, Object> login(Map<String, Object> map) {
		return selectOne("login.login", map);
	}

	
}
