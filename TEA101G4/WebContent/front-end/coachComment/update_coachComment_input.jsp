<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coachComment.model.*"%>

<%
	CoachCommentVO coachCommentVO = (CoachCommentVO) request.getAttribute("coachCommentVO"); //CoachCommentServlet.java (Concroller) 存入req的coachCommentVO物件 (包括幫忙取出的coachCommentVO, 也包括輸入資料錯誤時的coachCommentVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_coachComment_input.jsp</title>

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
				<h3>coachCommentID修改 - update_coachComment_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

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
				<td>新增時間：</font></td>
				<td><%=coachCommentVO.getAddDate()%> <input type="hidden"
					name="addDate" value="<%=coachCommentVO.getAddDate()%>" /></td>
			</tr>

			<tr>
				<td>評論編號:<font color=red><b>*</b></font></td>
				<td><%=coachCommentVO.getCoachCommentID()%></td>
			</tr>



			<jsp:useBean id="ccService" scope="page"
				class="com.coachComment.model.CoachCommentService" />

			<tr>
				<td>被評論教練ID:</td>
				<td><input type="TEXT" name="memberID" size="45"
					value="<%=coachCommentVO.getMemberID()%>" /></td>
			</tr>

			<tr>
				<td>評論會員ID:</td>
				<td><input type="TEXT" name="memberID2" size="45"
					value="<%=coachCommentVO.getMemberID2()%>" /></td>
			</tr>


			<tr>
				<td>評論描述:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="commText" size="45"
					value="<%=coachCommentVO.getCommText()%>" /></td>
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
				<td>status：<font color=red><b>*</b></td>
				<td><select name="status" ${coachCommentVO.status}>
						<option>Y</option>
						<option>N</option>
				</select>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="coachCommentID"
			value="<%=coachCommentVO.getCoachCommentID()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>
</html>