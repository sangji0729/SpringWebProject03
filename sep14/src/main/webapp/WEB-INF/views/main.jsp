<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<style type="text/css">
* {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function linkPage(pageNo) {
		//location.href = "./main.do?pageNo=" + pageNo;
		ajax1(pageNo);
	}
</script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(
		function(){
			ajax1(2);
		});
		
function ajax1(pageNo){
	$.ajax({
		url:"./mainAJAX.do",
		type:"POST",
		cache:false,
		dataType:"json",
		data:{"pageNo":pageNo},
		success: function(data){
			//alert("정상 pageNo : " + data.pageNo);
			//alert("정상 sb_cate : " + data.sb_cate);
			//alert("정상 list : " + data.list);
			emp(data);
		},
		error: function(data, status, error){
			//alert("error : " + requst);
			alert("error : " + error);
		}
	});
}

function emp(data){
	$("#mainTable").empty();
	$("#paging").empty();//모두 지우고 다시만들기
	
	var pageNo = data.pageNo;
	var sb_cate = data.sb_cate;
	var totalCount = data.totalCount;
	var list = data.list;
	var temp = "<h1>페이지 번호 : " + pageNo + "</h1>";
	temp += "<h2>카테고리 번호 : " + sb_cate + "</h2>";
	temp += "<h3>전체 글 수 : " + totalCount + "</h3>";
	temp += "<table>";
	temp += "<tr><td>번호</td><td>제목</td><td>글쓴이</td><td>날짜</td>";
	temp += "</tr>";
	for (var i = 0; i < list.length; i++) {
		temp += "<tr>";
		temp += "<td>" + list[i].sb_no + "</td>";
		temp += "<td>" + list[i].sb_title + "</td>";
		temp += "<td>" + list[i].sm_id + "</td>";
		temp += "<td>" + list[i].sb_date + "</td>";
		temp += "</tr>";
	}
	temp += "</table>";
	$("#mainTable").append(temp);
	
	//페이징 찍기
	//pageNo, totalCount
	var totalPage = parseInt(totalCount / 10);
	if(totalCount % 10 != 0){
		totalPage += 1;
	}
	var startPage = parseInt(pageNo / 10);//혹시나 여긴 나중에 수정
	if(pageNo % 10 != 0){
		startPage = parseInt(pageNo / 10) + 1;
	}
	var endPage = startPage + 9;//여기도 나중에 수정
	
	//for출력
	var paging = "";
	if(pageNo > 1){
		paging += "<button onclick=ajax1(" + (pageNo - 1) + ")>이전</button>"
	}else{
		paging += "<button disabled=\"disabled\">이전</button>"
	}
	for(var i = startPage; i <= endPage; i++){
		if(pageNo == i){
			paging += "<button onclick=ajax1("+i+")><b>"+ i +"</b></button>"
		}else{
			paging += "<button onclick=ajax1("+i+")>"+ i +"</button>"			
		}
	}
	if(pageNo < totalPage){
	paging += "<button onclick=ajax1(" + (pageNo + 1) + ")>다음</button>"
	}else{
		paging += "<button disabled=\"disabled\">다음</button>"
	}
	
	$("#paging").append(paging);
}		

</script>
</head>
<body>
	<h1>메인</h1>
	<c:if test="${sessionScope.sm_id eq null }">
		<button onclick="location.href='./login.do'">로그인</button>
		<button onclick="location.href='./join.do'">회원 가입</button>
	</c:if>
	<c:if test="${sessionScope.sm_id ne null }">
		<h2>${sessionScope.sm_name }님반갑습니다.</h2>
		<br>
		<a href="./message.do">쪽지</a>
		<br>
		<button onclick="location.href='./logout.do'">로그아웃</button>
	</c:if>
	<hr>
	<div id="mainTable">
		<!-- 여기다 찍기 -->
		여기다 찍어줄 겁니다
	</div>
	<!-- 페이징 찍어줄 곳 -->
	<div id="paging">
		<%-- <ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage" /> --%>
	</div>
</body>
</html>