<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog_save.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Blog_SaveVO blogSaveVO = (Blog_SaveVO) request.getAttribute("blog_SaveVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>部落格收藏資料 - listOneBlog_Save.jsp</title>

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
		 <h3>部落格留言資料 - ListOneBlog_Save.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog_save/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章收藏編號</th>
		<th>一般會員編號</th>
		<th>文章編號</th>
		<th>收藏狀態</th>
		<th>收藏日期</th>
		<th>更新日期</th>
		
	</tr>
	<tr>
		<td><%=blogSaveVO.getBlogSaveno()%></td>
		<td><%=blogSaveVO.getMemberId()%></td>
		<td><%=blogSaveVO.getBlogno()%></td>
		<td><%=blogSaveVO.getStatus()%></td>
		<td><%=blogSaveVO.getSaveDate()%></td>
		<td><%=blogSaveVO.getUpdateTime()%></td>
		
		
	</tr>
</table>

</body>
</html>