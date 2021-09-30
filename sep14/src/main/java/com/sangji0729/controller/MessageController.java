package com.sangji0729.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sangji0729.common.CommandMap;
import com.sangji0729.service.MessageServiceImpl;

@Controller
public class MessageController {
	@Resource(name="messageService")
	private MessageServiceImpl messageService;
	
	//@RequestMapping(value="/message.do")
	@GetMapping("/message.do")
	public ModelAndView messageList(CommandMap map ,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("message");
		HttpSession session = request.getSession();
		//Map<String, Object> map = new HashMap<String, Object>();
		//System.out.println("map::::::" + map.getMap());
		if(session.getAttribute("sm_id") != null) {
			//데이터베이스 질의문
			map.put("sm_id", session.getAttribute("sm_id"));
			if(map.getMap().containsKey("openmsg")) {
				//수신확인 처리
				messageService.readMessage(map.getMap());
				//사용자가 메시지 읽기를 원함
				Map<String, Object> detail = messageService.messageDetail(map.getMap());//sm_id, openmsg
				mv.addObject("detail", detail);
			}
			//쪽지 삭제
			if(map.getMap().containsKey("deletemsg")) {
				messageService.deleteMessage(map.getMap());
			}
			//쪽지리스트 불러오기
			List<Map<String, Object>> list = messageService.messageList(map.getMap());
			mv.addObject("list", list);
			//답장버튼 눌렀을 때 해당 값을 다시 mv에 붙이기
			if(map.containsKey("sendmsg")) {
				//해당 번호로 아이디 묻기
				String sendmsg = messageService.getName(map.getMap());
				mv.addObject("sendmsg", sendmsg);
			}
			
			//System.out.println("리스트" + list);
		}else {
			//비로그인시 로그인.do 화면으로
			mv.setViewName("redirect:/login.do");
		}
		
		
		return mv;
	}
	
	@PostMapping("/message.do")
	public String sendMessage(CommandMap commandMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sm_id");
		
		if(id != null) {
			//map에 있는 값 = sendId/ content / sm_id
			commandMap.put("sm_id", id);
			messageService.sendMessage(commandMap.getMap());
			return "redirect:/message.do";
		}else {
			return "redirect:/login.do";
		}
		
	
	}
	
	
}
