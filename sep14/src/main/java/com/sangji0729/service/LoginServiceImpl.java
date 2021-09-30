package com.sangji0729.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangji0729.dao.LoginDAO;
/*
 * 객체생성
 * @Controller	객체 생성,	컨트롤러 역할
 * @Service		객체 생성,	서비스 역할
 * @Repository	객체 생성, 	DAO 역할
 * @Component	객체 생성, 	그외
 * 
 * 객체 주입
 * @Inject				데이터 타입으로 찾아요. java에서 제공
 * @Autowired			데이터 타입으로 찾아요. Spring에서 제공
 * @Resource(name="")	name으로 찾아요.
 * 
 * 
 * 생성순서
 * 컨트롤러 -> 서비스Impl -> 서비스(인터페이스) -> DAO -> mapper ->DB연결 
 * 
 * 역순
 * DAO -> 서비스 -> 서비스Impl ->컨트롤러 -> mapper
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return loginDAO.login(map);
	}

	
}
