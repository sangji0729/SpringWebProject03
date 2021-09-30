<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>data.go.kr에서 뽑아온 값 입니다</title>
<style type="text/css">
#table{
	border: 1px solid gray;
	border-collapse: collapse;
}
#table th{
	border: 1px solid gray;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h1>data</h1>

	<table id="table">
		<tr>
			<th>측정일</th>
			<th>측정소명</th>
			<th>아황산 가스</th>
			<th>일산화탄소</th>
			<th>오존</th>
			<th>이산화질소</th>
			<th>PM10 미세먼지</th>
			<th>PM25 미세먼지</th>
		</tr>
		<c:forEach items="${list }" var="l">
			<tr>
				<th>${l.msurDt }</th>
				<th>${l.msrstnName }</th>
				<th>${l.so2Value }</th>
				<th>${l.coValue }</th>
				<th>${l.o3Value }</th>
				<th>${l.no2Value }</th>
				<th>${l.pm10Value }</th>
				<th>${l.pm25Value }</th>
			</tr>
		</c:forEach>
	</table>

</body>
</html>