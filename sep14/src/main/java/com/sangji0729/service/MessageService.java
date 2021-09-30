package com.sangji0729.service;

import java.util.List;
import java.util.Map;

public interface MessageService {
		
	//메시지 목록
	public List<Map<String, Object>> messageList(Map<String, Object> map);
	
	//쪽지 읽음 처리
	public void readMessage(Map<String, Object> map);
	
	//메시지 보내기
	public int sendMessage(Map<String, Object> map);
	
	//메시지 읽었다면 == 읽음처리 checkMessage
	public int checkMessage(Map<String, Object> map);
	
	//메시지 삭제했다면
	public int deleteMessage(Map<String, Object> map);
	
}
