<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    BlogService blogSvc = new BlogService();
    List<BlogVO> list = blogSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��峹��� - listAllBlog.jsp</title>

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
		 <h3>�Ҧ��峹��� - listAllBlog.jsp</h3>
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
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>�峹����</th>
		<th>�o����</th>
		<th>���D</th>
		<th>����</th>
<!-- 		<th>�v��</th> -->
		<th>�峹���A</th>
		<th>��s���</th>
		<th>�Ӥ�</th>
		<th>�v��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
<%-- 	<c:forEach var="blogVO" items="${list}"> --%>
	<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${blogVO.blogno}</td>
			<td>${blogVO.memberId}</td>
			<td>${blogVO.blogClass}</td>
			<td>${blogVO.postDate}</td>
			<td>${blogVO.title}</td>
			<td>${blogVO.text}</td> 
<%-- 			<td>${blogVO.photo}</td> --%>
<%-- 			<td>${blogVO.video}</td> --%>
			<td>${blogVO.status}</td>
			<td>${blogVO.updateTime}</td>
			<td><img class="blog" width="400" src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td>
			<td>
				<video width="400" height="240" controls class="blog">
  					<source src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" type="video/mp4">
				</video>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="blogno"  value="${blogVO.blogno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="blogno"  value="${blogVO.blogno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>