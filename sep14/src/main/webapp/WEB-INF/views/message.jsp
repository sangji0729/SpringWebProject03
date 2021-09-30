<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 메시지</title>
<style type="text/css">
* {
	text-decoration: none;
}
#send{
	width: 50%;
	float: left;
}
#view{
	width: 50%;
	float: right;
}
#sendId{
	margin: 0 auto;
	padding: 0;
	width: 300px;
	height: 20px;
}
#content{
	margin: 0 auto;
	padding: 0;
	margin-top: 10px;
	margin-bottom: 10px;
	width: 300px;
	height:25px;
}

</style>
</head>
<body>
	<h1>받은 메시지</h1>
	<table>
		<tr>
			<td>번호</td>
			<td>내용</td>
			<td>보낸사람</td>
			<td>보낸날짜</td>
			<td>수신여부</td>
		</tr>

		<c:forEach items="${list }" var="l">
			<tr onclick="location.href='./message.do?openmsg=${l.me_no}'"
			<c:if test="${l.me_read eq 0 }">style="font-weight: bold; color: blue;"</c:if>
			<c:if test="${l.me_read eq 1 }">style="color:gray;"</c:if>
			>
				<td>${l.me_no }</td>
				<td>${l.me_content }</td>
				<td>${l.sm_name } (${l.sm_id })</td>
				<td>${l.me_date }</td>
				<td>
					<c:if test="${l.me_read eq 0 }">읽지않음</c:if>
					<c:if test="${l.me_read eq 1 }">읽음</c:if>
				</td>
			</tr>

		</c:forEach>
	</table>
	<button onclick="location.href='./main.do'">게시판으로 돌아가기</button>
	<br>
	<hr>
	<div id="send">
		<form action="./message.do" method="post">
			<input type="text" name="sendId" id="sendId" value="${sendmsg }" placeholder="받는 사람의 아이디를 입력하세요." required="required"><br>
			<input type="text" name="content" id="content" placeholder="내용을 입력하세요." required="required"><br>
			<button onclick="location.href='./messageSend.do'">보내기</button>
		</form>
	</div>
	<div id="view">
		<c:choose>
			<c:when test="${detail ne null }">
				번호 : ${detail.me_no }<br>
				보낸 사람 : ${detail.sm_name }<br>
				보낸 날짜 : ${detail.me_date }<br>
				보낸 내용 : ${detail.me_content }<br>
				<button onclick="location.href='./message.do?sendmsg=${detail.me_sendp }'">답장하기</button>
				<button onclick="location.href='./message.do?deletemsg=${detail.me_no}'">삭제</button>
			</c:when>
		</c:choose>
	</div>
</body>
</html>