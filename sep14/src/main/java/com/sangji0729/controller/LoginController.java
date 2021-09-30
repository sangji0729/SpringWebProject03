package com.sangji0729.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangji0729.common.CommandMap;
import com.sangji0729.service.LoginServiceImpl;

@Controller
public class LoginController {
	@Resource(name = "loginService")
	private LoginServiceImpl loginService;
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login.do")
	public String login(CommandMap map, HttpServletRequest request) {
		//String id = request.getParameter("id");
		//String pw = request.getParameter("pw");
		//System.out.println(id);
		//System.out.println(pw);
		//System.out.println(map.getMap());//변환해서 사용
		
		Map<String, Object> login = loginService.login(map.getMap());
		System.out.println("나온 값 : " + login);
		
		HttpSession session = request.getSession();
		session.setAttribute("sm_id", login.get("sm_id"));
		session.setAttribute("sm_name", login.get("sm_name"));
		
		return "redirect:/main.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(CommandMap map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		
		if(session.getAttribute("sm_id") != null ) {
			session.removeAttribute("sm_id");			
		}
		if(session.getAttribute("sm_name") != null) {
			session.removeAttribute("sm_name");
		}
		
		
		return "redirect:/main.do";
	}
}
