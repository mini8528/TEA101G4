<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>文章資料 - listOneBlog.jsp</title>

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
		 <h3>文章資料 - ListOneBlog.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>一般會員編號</th>
		<th>文章分類</th>
		<th>發文日期</th>
		<th>標題</th>
		<th>內文</th>
		<th>照片</th>
		<th>影片</th>
		<th>文章狀態</th>
		<th>更新日期</th>
		
	</tr>
	<tr>
		<td><%=blogVO.getBlogno()%></td>
		<td><%=blogVO.getMemberId()%></td>
		<td><%=blogVO.getBlogClass()%></td>
		<td><%=blogVO.getPostDate()%></td>
		<td><%=blogVO.getTitle()%></td>
		<td><%=blogVO.getText()%></td>
		<td><img class="blog" width="400"  src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td>
		<td>
			<video width="400" height="240" controls class="blog">
  				<source src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" type="video/mp4">
			</video>
		</td>
<%-- 		<td><%=blogVO.getPhoto()%></td> --%>
<%-- 		<td><%=blogVO.getVideo()%></td> --%>
		<td><%=blogVO.getStatus()%></td>
		<td><%=blogVO.getUpdateTime()%></td>
		
	</tr>
</table>

</body>
</html>