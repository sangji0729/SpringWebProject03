<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.sb_title } : 자유게시판</title>
<script type="text/javascript">
function move(){
	location.href="./main.do";
}
function del(no){
	location.href="./delete.do?sb_no="+no;
}
</script>
</head>
<body>
	값찍기
	<hr>
	<table>
		<tr>
			<th>글 번호 : ${detail.sb_no }</th>
			<th>글 제목 : ${detail.sb_title }</th>
			<th>글쓴이 : ${detail.sb_name } (${detail.sb_id })</th>
			<th>작성일 : ${detail.sb_date } <button onclick="del(${detail.sb_no})">삭제</button></th>
		</tr>
	</table>
	<hr>
	<div>
		${detail.sb_content }
	</div>
	<hr>
	<button onclick="move()">게시판으로 돌아가기</button>
</body>
</html>