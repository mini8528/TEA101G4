<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachComment.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%



	CoachCommentService ccService = new CoachCommentService();
    List<CoachCommentVO> list = ccService.getAll();
    pageContext.setAttribute("list",list);
    
    
%>

<!DOCTYPE html>
<html>
<head>
<title>所有CoachComment資料 - listAllCoachComment.jsp</title>

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
	<tr><td>
		 <h3>所有CoachComment資料 - listAllCoachComment.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>課程ID</th>
		<th>教練</th>
		<th>學員</th>
		<th>評論</th>
		<th>評分</th>
		<th>add</th>
		<th>edit</th>
		<th>狀態</th>
		<th>詳細課程</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="coachCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coachCommentVO.coachCommentID}</td>
			<td>${coachCommentVO.memberID}</td>
			<td>${coachCommentVO.memberID2}</td>
			<td>${coachCommentVO.commText}</td>
			<td>${coachCommentVO.commStar}</td>
			<td><fmt:formatDate value="${coachCommentVO.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
			<td><fmt:formatDate value="${coachCommentVO.editDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
			<td>${coachCommentVO.status}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachComment/coachComment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="詳細課程">
			     <input type="hidden" name="coachCommentID"  value="${coachCommentVO.coachCommentID}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>