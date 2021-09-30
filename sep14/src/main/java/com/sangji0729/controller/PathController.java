package com.sangji0729.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sangji0729.common.CommandMap;

@Controller
public class PathController {
	//템플릿 변수
	//spring3에서 추가된 기능
	//url경로에 변수를 넣어주는 기능
	@GetMapping("/myinfo_{id}.do") ///myinfo/sangji0729.do
	public String myinfo(@PathVariable String id, CommandMap map) {
		if(id.equals("sangji0729")) {
			System.out.println("들어왔습니다 : " + id);			
		}else {
			System.out.println("없는 id입니다");
		}
		return "myinfo";
	}
	
	@GetMapping("data.do")
	public ModelAndView data() throws Exception {
		ModelAndView mv = new ModelAndView("data");
		String serviceKey = "D6l9xTSFpmLXdKoOwhrKYkx3i3R3BMzNJ4HlaP61AwqZlKxAcD%2FLE9pgnIWqkKLqeeKdj4sWrGHkgWoVVJQvpQ%3D%3D";
		StringBuilder sb = new StringBuilder();
		sb.append("http://apis.data.go.kr/B552584/ArpltnStatsSvc/getMsrstnAcctoRDyrg");
		sb.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
		sb.append("&" + URLEncoder.encode("returnType","UTF-8") + "=json");
		sb.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=100");
		sb.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
		sb.append("&" + URLEncoder.encode("inqBginDt","UTF-8") + "=20210912");
		sb.append("&" + URLEncoder.encode("inqEndDt","UTF-8") + "=20210923");
		sb.append("&" + URLEncoder.encode("msrstnName","UTF-8") + "=" + URLEncoder.encode("성동구","UTF-8"));
		
		URL url = new URL(sb.toString());
		//json simple로 하는 방법
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
		//System.out.println("JSONObject에 담았습니다============");
		//System.out.println(jsonObject.toJSONString());
		//System.out.println("===================================");
		
		//mv에 담아서 data.jsp에 출력하기
		Map<String, Object> map = (Map<String, Object>)jsonObject.get("response");
		map = (Map<String, Object>) map.get("body");
		//map = (Map<String, Object>) map.get("items");//이거 에러
		ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) map.get("items");
		
		//System.out.println(list);
		mv.addObject("list", list);
		
		
		//자바 방법
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		//System.out.println("응답 코드 : " + conn.getResponseCode());
		
		BufferedReader br = null;
		
		if(conn.getResponseCode() == 200 && conn.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder result = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			result.append(line);
		}
		br.close();
		conn.disconnect();
		//System.out.println("결과 : " +result.toString());
		
		return mv;
	}
	
	//data에서 corona 읽어오기
	@GetMapping("/corona.do")
	public ModelAndView corona() throws Exception {
		ModelAndView mv = new ModelAndView("corona");
		
		String serviceKey = "D6l9xTSFpmLXdKoOwhrKYkx3i3R3BMzNJ4HlaP61AwqZlKxAcD%2FLE9pgnIWqkKLqeeKdj4sWrGHkgWoVVJQvpQ%3D%3D";
		
		StringBuilder sb = new StringBuilder();
		sb.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
		sb.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
		sb.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=100");
		sb.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
		sb.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=20210912");
		sb.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=20210924");
		
		URL url = new URL(sb.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		System.out.println("응답 코드 : " + conn.getResponseCode());
		
		BufferedReader br = null;
		
		if(conn.getResponseCode() == 200 && conn.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder result = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			result.append(line);
		}
		br.close();
		conn.disconnect();
		System.out.println("결과 : " +result.toString());
		
		
		return mv;
	}
	
	@GetMapping("/corona2.do")
	public ModelAndView corona2() throws Exception {
		ModelAndView mv = new ModelAndView("corona");
		System.out.println("코로나2 입니다");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse("C:\\temp2\\corona.xml");
		
		System.out.println(document.getDocumentElement().getNodeName());
		
		Element root = document.getDocumentElement();
		System.out.println("root = " + root.getNodeName());
		
		NodeList list = document.getElementsByTagName("item");
		System.out.println("item 길이 : "+list.getLength());
		
		//담을 리스트
		ArrayList<Map<String, Object>> coronaList = new ArrayList<Map<String,Object>>();
		
		//Node node = list.item(0);
		for (int i = 0; i < list.getLength(); i++) {
			NodeList childList = list.item(i).getChildNodes();
			System.out.println("list 길이 : " + i);
			
			//각각 요소를 담을 map
			Map<String, Object> value = new HashMap<String, Object>();
			
			for (int j = 0; j < childList.getLength(); j++) {
				Node node = childList.item(j);
				if(node.getNodeType() != Node.ELEMENT_NODE) { //실제 노드
					continue;
				}else {//정상 값이라면 출력
					//System.out.println("NodeName : " + node.getNodeName());
					//System.out.println("value : " + node.getTextContent());
					value.put(node.getNodeName(), node.getTextContent());
				}
			}
			coronaList.add(value);
		}
		//System.out.println("결과 : " + coronaList);
		mv.addObject("list", coronaList);
		
		
		return mv;
	}
	
	
}
