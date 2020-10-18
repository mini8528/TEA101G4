<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coachComment.model.*"%>

<%
	CoachCommentVO coachCommentVO = (CoachCommentVO) request.getAttribute("coachCommentVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addCoachComment.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>CoachCommentID新增 - addCoachComment.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="coachComment.do" name="form1">
		<table>
			<tr>
				<td>memberID:</td>
				<td><input type="TEXT" name="memberID" size="45"
					value="<%=(coachCommentVO == null) ? "M001" : coachCommentVO.getMemberID()%>" /></td>
			</tr>
			<tr>
				<td>memberID2:</td>
				<td><input type="TEXT" name="memberID2" size="45"
					value="<%=(coachCommentVO == null) ? "M002" : coachCommentVO.getMemberID2()%>" /></td>
			</tr>
			<tr>
				<td>評論:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="commText" size="45"
					value="<%=(coachCommentVO == null) ? "addCoachComment" : coachCommentVO.getCommText()%>" /></td>
			</tr>
			<tr>
				<td>星星數：<font color=red><b>*</b></td>
				<td><select name="commStar">
						<option value="1"
							${(coachCommentVO.commStar=="1")? 'selected':'' }>1</option>
						<option value="2"
							${(coachCommentVO.commStar=="2")? 'selected':'' }>2</option>
						<option value="3"
							${(coachCommentVO.commStar=="3")? 'selected':'' }>3</option>
						<option value="4"
							${(coachCommentVO.commStar=="4")? 'selected':'' }>4</option>
						<option value="5"
							${(coachCommentVO.commStar=="5")? 'selected':'' }>5</option>
				</select></td>
			</tr>

			<tr>
				<td>付款狀態：</td>
				<td><select name="status">
						<option value="N"
							${(coachCommentVO.status=="N")? 'selected':'' }>N</option>
						<option value="Y"
							${(coachCommentVO.status=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>

			<jsp:useBean id="ccService" scope="page"
				class="com.coachComment.model.CoachCommentService" />
			<!-- <tr> 
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr> -->

		</table>
		<br> 
		
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
		
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>