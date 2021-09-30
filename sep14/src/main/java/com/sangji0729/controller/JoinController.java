package com.sangji0729.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sangji0729.common.CommandMap;
import com.sangji0729.service.JoinServiceImpl;

@Controller
public class JoinController {
	@Autowired
	private JoinServiceImpl joinService;
	
	@GetMapping("/join.do")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join.do")
	public String join(CommandMap map, HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw1 = request.getParameter("pw1");
		String pw2 = request.getParameter("pw2");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		//System.out.println("id : " + id);
		//System.out.println("pw1 : " + pw1);
		//System.out.println("pw2 : " + pw2);
		//System.out.println("name : " + name);
		//System.out.println("email : " + email);
		
		if(id != null && pw1 != null && pw2 != null && name != null && email != null) {
			if(pw1.equals(pw2)) {
				map.put("id", id);
				map.put("pw", pw1);
				map.put("name", name);
				map.put("email", email);
				
				joinService.join(map.getMap());
				
				return "redirect:./login.do";
			}else {
				return "join";
			}
		}else {
			return "join"; 
		}
			
	}
}
