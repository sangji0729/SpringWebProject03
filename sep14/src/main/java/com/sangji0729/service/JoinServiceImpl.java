package com.sangji0729.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangji0729.dao.JoinDAO;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Autowired
	private JoinDAO joinDAO;
	
	@Override
	public int join(Map<String, Object> map) {
		return joinDAO.join(map);
	}

	

	
	
}
