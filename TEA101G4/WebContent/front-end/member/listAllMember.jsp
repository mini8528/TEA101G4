<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberService mService = new MemberService();
	List<MemberVO> list = mService.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有Member資料 - listAllMember.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有Member資料 - listAllMember.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>memberid</th>
			
			<th>name</th>
			<th>account</th>
			<th>password</th>
			<th>gender</th>
			<th>phone</th>
			
			<th>birthday</th>
			<th>email</th>
			<th>photo</th>
			<th>address</th>
			<th>authority</th>
			
			<th>qualifications</th>
			<th>expertise</th>
			<th>introduction</th>
			<th>photo1</th>
			<th>photo2</th>
			
			<th>photo3</th>
			<th>adddate</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memberVO.memberid}</td>
				
				<td>${memberVO.name}</td>
				<td>${memberVO.account}</td>
				<td>${memberVO.password}</td>
				<td>${memberVO.gender}</td>
				<td>${memberVO.phone}</td>
				
				<td>${memberVO.birthday}</td>
				<td>${memberVO.email}</td>
				<td>
				<img
					src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${memberVO.memberid}"
					width="100" height="100">
				</td>
				<td>${memberVO.address}</td>
				<td>${memberVO.authority}</td>
				
				<td>${memberVO.qualifications}</td>
				<td>${memberVO.expertise}</td>
				<td>${memberVO.introduction}</td>
				<td>
				<img
					src="<%=request.getContextPath()%>/front-end/member/memberShow1.do?memberid=${memberVO.memberid}"
					width="100" height="100">
				</td>
				<td>
				<img
					src="<%=request.getContextPath()%>/front-end/member/memberShow2.do?memberid=${memberVO.memberid}"
					width="100" height="100">
				</td>
				
				<td>
				<img
					src="<%=request.getContextPath()%>/front-end/member/memberShow3.do?memberid=${memberVO.memberid}"
					width="100" height="100">
				</td>
				<td>${memberVO.adddate}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/MemberServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memberid" value="${memberVO.memberid}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/MemberServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="memberid" value="${memberVO.memberid}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>