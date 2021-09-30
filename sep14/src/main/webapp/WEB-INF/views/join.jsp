<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
#joinForm{
	border: 1px solid gray;
	width: 500px;
	min-height: 260px;
	margin-left: 800px;
	vertical-align: middle;
}
#joinForm input{
	margin : 0 auto;
	padding: 0;
	margin-top: 10px;
	margin-left: 10px;
	margin-bottom: 10px;
	margin-right: 10px;
	width: 300px;
	height: 20px;
}
#joinForm button{
	margin-left: 170px;
}
</style>
</head>
<body>
	<h1 style="text-align: center;">회원가입</h1>
	<hr>
	<div id="joinForm">
		<form action="./join.do" method="post">
			아이디 <input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" required="required"><br> 
			비밀번호 <input type="password" id="pw1" name="pw1" placeholder="비밀번호를 입력해주세요" required="required"><br>
			비밀번호 확인 <input type="password" id="pw2" name="pw2" placeholder="비밀번호를 한번 더 입력해주세요" required="required"><br>
			이름 <input type="text" id="name" name="name" placeholder="이름을 입력해주세요" required="required"><br>
			이메일 <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요" required="required"><br>
			<button type="submit">회원가입</button>
		</form>
	</div>
</body>
</html>