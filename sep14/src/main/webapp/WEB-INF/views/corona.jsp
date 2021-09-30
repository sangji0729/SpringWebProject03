<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>corona</title>
<style type="text/css">
#table{
	border: 1px solid gray;
	border-collapse: collapse;
	text-align: center;
}
#table th{
	border: 1px solid gray;
	border-collapse: collapse;
}
#table td{
	border: 1px solid gray;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h1>corona</h1>

	<table id="table">
		<tr>
			<th>기준일</th>
			<th>기준시간</th>
			<th>확진자 수</th>
			<th>격리해제 수</th>
			<th>검사 진행 수</th>
			<th>사망자 수</th>
			<th>치료중 환자 수</th>
			<th>결과 음성 수</th>
			<th>누적 검사 수</th>
			<th>누적 검사 완료 수</th>
			<th>누적 확진률</th>
			<th>등록 시분초</th>
			<th>수정 일시분초</th>
		</tr>
		<c:forEach items="${list }" var="l">
			<tr>
				<td>
				<fmt:parseDate var="dateString" value="${l.stateDt }" pattern="yyyyMMdd"/>
				<fmt:formatDate value="${dateString }" pattern="yyyy.MM.dd"/>
				</td>
				<td>${l.stateTime }</td>
				<td>
				<fmt:formatNumber value="${l.decideCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.clearCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.examCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.deathCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.careCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.resutlNegCnt }" type="number"/>
				</td>
				<td>
				<fmt:formatNumber value="${l.accExamCnt }" type="number"/>
				</td>
				<td>
				<fmt:formatNumber value="${l.accExamCompCnt }" type="number" />
				</td>
				<td>
				<fmt:formatNumber value="${l.accDefRate }" pattern=".00" />%
				</td>
				<td>${l.createDt }</td>
				<td>${l.updateDt }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>