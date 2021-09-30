package com.sangji0729.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangji0729.dao.MessageDAO;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public List<Map<String, Object>> messageList(Map<String, Object> map) {
		return messageDAO.messageList(map);
	}

	@Override
	public void readMessage(Map<String, Object> map) {
		messageDAO.readMessage(map);
	}

	@Override
	public int sendMessage(Map<String, Object> map) {
		int result = messageDAO.sendMessage(map);
		return result;
	}

	@Override
	public int checkMessage(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int deleteMessage(Map<String, Object> map) {
		int result = messageDAO.deleteMessage(map);
		return result;
	}

	public Map<String, Object> messageDetail(Map<String, Object> map) {
		return messageDAO.messageDetail(map);
	}

	public String getName(Map<String, Object> map) {
		return messageDAO.getName(map);
	}

}
