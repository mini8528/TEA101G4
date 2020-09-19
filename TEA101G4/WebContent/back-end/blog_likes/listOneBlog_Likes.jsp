<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog_likes.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Blog_LikesVO blogLikesVO = (Blog_LikesVO) request.getAttribute("blog_LikesVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>部落格按讚資料 - listOneBlog_Likes.jsp</title>

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
		 <h3>部落格按讚資料 - ListOneBlog_Likes.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/blog_likes/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>部落格按讚編號</th>
		<th>文章編號</th>
		<th>一般會員編號</th>
		<th>按讚狀態</th>
		<th>按讚日期</th>
		<th>更新日期</th>
		
	</tr>
	<tr>
		<td><%=blogLikesVO.getBlogLikesno()%></td>
		<td><%=blogLikesVO.getBlogno()%></td>
		<td><%=blogLikesVO.getMemberId()%></td>
		<td><%=blogLikesVO.getStatus()%></td>
		<td><%=blogLikesVO.getLikesDate()%></td>
		<td><%=blogLikesVO.getUpdateTime()%></td>
		
		
	</tr>
</table>

</body>
</html>