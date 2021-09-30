package com.sangji0729.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sangji0729.common.CommandMap;
import com.sangji0729.service.TestServiceImp;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class TestController {

	@Resource(name = "testService")
	private TestServiceImp testService;

	@RequestMapping(value = "/main.do")
	public ModelAndView main(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("main");
		//System.out.println("::::::::::::::::::::" + commandMap.getMap());
		int listScale = 10;
		int pageScale = 10;
		int sb_cate = 1;
		if (!commandMap.containsKey("sb_cate")) {
			commandMap.put("sb_cate", sb_cate);
		}
		int pageNo = 1;
		if (commandMap.containsKey("pageNo")) {
			pageNo = Integer.parseInt(String.valueOf(commandMap.getMap().get("pageNo")));
		}

		int totalCount = testService.totalCount(commandMap.getMap());

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastPage = paginationInfo.getRecordCountPerPage();
		//commandMap.put("startPage", startPage);
		//commandMap.put("lastPage", lastPage);
		paginationInfo.setTotalRecordCount(totalCount);

		//List<Map<String, Object>> list = testService.boardList(commandMap.getMap());
		//mv.addObject("list", list);
		//System.out.println("뽑힌 리스트 : " + list);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		mv.addObject("totalCount", totalCount);// ?
		mv.addObject("sb_cate", sb_cate);// ?

		return mv;
	}

	// ajax 연결
	//@PostMapping("/mainAJAX.do")
	@PostMapping(value="/mainAJAX2.do", produces = "text/plain;charset=utf-8")
	public @ResponseBody String mainAJAX2(CommandMap commandMap) {
		System.out.println("ajax로 들어온 값 : " + commandMap.getMap());
		
		int sb_cate = 1;
		if (!commandMap.containsKey("sb_cate")) {
			commandMap.put("sb_cate", sb_cate);
		}
		int pageNo = 1;
		if (commandMap.containsKey("pageNo")) {
			pageNo = Integer.parseInt(String.valueOf(commandMap.getMap().get("pageNo")));
		}
		
		int listScale = 10;
		int pageScale = 10;
		
		//int totalCount = testService.totalCount(commandMap.getMap());

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastPage = paginationInfo.getRecordCountPerPage();
		commandMap.put("startPage", startPage);
		commandMap.put("lastPage", lastPage);
		//paginationInfo.setTotalRecordCount(totalCount);

		List<Map<String, Object>> list = testService.boardList(commandMap.getMap());
		
		// JSON으로 변환하기
		JSONObject jsonList = new JSONObject();// 제이슨 객체
		jsonList.put("sb_cate", sb_cate);
		jsonList.put("pageNo", pageNo);
		//jsonList.put("totalCount", totalCount);
		jsonList.put("list", list);
		
		System.out.println(jsonList.toJSONString());

		return jsonList.toJSONString();
	}
	

	//새로 만들기
	@RequestMapping(value="/mainAJAX.do", method=RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String mainAJAX(CommandMap map) {
		//페이징 번호 받기 = 전자정부페이징이 아니라서 페이지 번호만 받음
		int pageNo = 1;
		if(map.containsKey("pageNo")) {
			pageNo = Integer.parseInt((String)map.get("pageNo"));
		}
		map.put("pageNo", (pageNo - 1) * 10); //1
		
		//카테고리
		int sb_cate = 1;
		if(!map.containsKey("sb_cate")) {
			map.put("sb_cate", sb_cate);			
		}
		
		
		//서버에 질의 = sql문을 수정해야합니다
		List<Map<String, Object>> list = testService.boardList(map.getMap());
		//totalCount
		int totalCount = testService.totalCount(map.getMap());
		
		//json형식으로 변환
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sb_cate", sb_cate);// 지금은 없어도 될 값
		jsonObject.put("pageNo", pageNo);
		jsonObject.put("list", list);
		jsonObject.put("totalCount", totalCount);//전체 글 수
		
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject.toJSONString();
	}
	
	
	
	
	@RequestMapping("/detail.do")
	public ModelAndView detail(CommandMap map) {
		// mv객체 만들기
		ModelAndView mv = new ModelAndView("detail");

		// DB에 질의하기. ~에 담기
		// 서비스에게 질의를 던질 때 commandMap을 던지는 것이 아니라 그 속에 map만 뽑아서 던집니다
		Map<String, Object> detail = testService.detail(map.getMap());
		System.out.println("map : " + map);
		System.out.println("map.getMap() : " + map.getMap());
		System.out.println("detail : " + detail);
		// mv에 붙이기
		mv.addObject("detail", detail);
		// spring 생명주기, 커낵션 풀

		// return

		return mv;
	}

	@RequestMapping("/delete.do")
	public String delete(CommandMap map) {
		// 해야 할 일?
		// System.out.println("map : " + map.getMap()); sl_no값이 넘어옴
		testService.delete(map.getMap());

		String url = "redirect:/main.do";

		return url;
	}
}
