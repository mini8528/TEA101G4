<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog_likes.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Blog_LikesVO blogLikesVO = (Blog_LikesVO) request.getAttribute("blog_LikesVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>��������g��� - listOneBlog_Likes.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>��������g��� - ListOneBlog_Likes.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/blog_likes/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��������g�s��</th>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>���g���A</th>
		<th>���g���</th>
		<th>��s���</th>
		
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