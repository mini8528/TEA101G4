<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.coachComment.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CoachCommentVO coachCommentVO = (CoachCommentVO) request.getAttribute("coachCommentVO"); //EmpServlet.java(Concroller), 存入req的coachCommentVO物件
%>

<html>
<head>
<title>CoachComment資料 - listOneCoachComment.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>CoachComment資料 - ListOneCoachComment.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		 <a href='listAllCoachComment.jsp'>List</a></h4>
		 
	</td></tr>
</table>

<table>
	<tr>
		<th>coachCommentID</th>
		<th>memberID</th>
		<th>memberID2</th>
		<th>commText</th>
		<th>commStar</th>
		<th>addDate</th>
		<th>editDate</th>
		<th>status</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%=coachCommentVO.getCoachCommentID()%></td>
		<td><%=coachCommentVO.getMemberID()%></td>
		<td><%=coachCommentVO.getMemberID2()%></td>
		<td><%=coachCommentVO.getCommText()%></td>
		<td><%=coachCommentVO.getCommStar()%></td>
		<td><%=coachCommentVO.getAddDate()%></td>
		<td><%=coachCommentVO.getEditDate()%></td>
		<td><%=coachCommentVO.getStatus()%></td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachComment/coachComment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coachCommentID"  value="${coachCommentVO.coachCommentID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachComment/coachComment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="coachCommentID"  value="${coachCommentVO.coachCommentID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
	</tr>
	
	<!--    只能取到一筆資料
	
	<tr>
		<td>${coachCommentVO.coachCommentID}</td>
		<td>${coachCommentVO.memberID}</td>
		<td>${coachCommentVO.memberID2}</td>
		<td>${coachCommentVO.commText}</td>
		<td>${coachCommentVO.commStar}</td>
		<td>${coachCommentVO.addDate}</td>
		<td>${coachCommentVO.editDate}</td>
		<td>${coachCommentVO.status}</td>
	</tr>
	 -->
</table>

</body>
</html>