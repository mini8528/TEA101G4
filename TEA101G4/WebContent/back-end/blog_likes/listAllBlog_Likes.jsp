<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_likes.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Blog_LikesService bloglikesSvc = new Blog_LikesService();
    List<Blog_LikesVO> list = bloglikesSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ���������g��� - listAllBlog_Likes.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ���������g��� - listAllBlog_Likes.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>��������g�s��</th>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>���g���A</th>
		<th>���g���</th>
		<th>��s���</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 

	<c:forEach var="blog_LikesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${blog_LikesVO.blogLikesno}</td>
			<td>${blog_LikesVO.blogno}</td>
			<td>${blog_LikesVO.memberId}</td>
			<td>${blog_LikesVO.status}</td>
			<td>${blog_LikesVO.likesDate}</td>
			<td>${blog_LikesVO.updateTime}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="blogLikesno"  value="${blog_LikesVO.blogLikesno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="blogLikesno"  value="${blog_LikesVO.blogLikesno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>