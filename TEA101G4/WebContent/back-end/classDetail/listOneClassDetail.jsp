<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.classDetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ClassDetailVO classDetailVO = (ClassDetailVO) request.getAttribute("classDetailVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
%>

<html>
<head>
<title>ClassDetail資料 - listOneClassDetail.jsp</title>

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
		 <h3>ClassDetail資料 - ListOneClassDetail.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>classDetailID 123</th>
		<th>classOrderID</th>
		<th>coachClassID</th>
		<th>quantity</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%=classDetailVO.getClassDetailID()%></td>
		<td><%=classDetailVO.getClassOrderID()%></td>
		<td><%=classDetailVO.getCoachClassID()%></td>
		<td><%=classDetailVO.getQuantity()%></td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/classDetail/classDetail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="classDetailID"  value="${classDetailVO.classDetailID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/classDetail/classDetail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="classDetailID"  value="${classDetailVO.classDetailID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		
	</tr>
</table>

</body>
</html>