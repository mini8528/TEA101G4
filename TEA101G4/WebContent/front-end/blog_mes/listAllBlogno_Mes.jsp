<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_mes.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	List <Blog_MesVO> list = (List <Blog_MesVO>) request.getAttribute("blogMesList");
	System.out.println(list);
// 	Blog_MesService blogmesSvc = new Blog_MesService();
//     List<Blog_MesVO> list = blogmesSvc.getAll();
//     pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ�������d����� - listAllBlog_Mes.jsp</title>

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
		 <h3>�Ҧ�������d����� - listAllBlog_Mes.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog_mes/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~���C --%>
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
		<th>������d���s��</th>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>�d�����e</th>
		<th>�d�����</th>
		<th>��s���</th>
		<th>�d�����A</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>


	<c:forEach var="blog_MesVO" items="${blogMesList}" >
<%-- 	<c:forEach var="blog_MesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
	
		<tr>
			<td>${blog_MesVO.blogMesno}</td>
			<td>${blog_MesVO.blogno}</td>
			<td>${blog_MesVO.memberId}</td>
			<td>${blog_MesVO.text}</td>
			<td>${blog_MesVO.postDate}</td>
			<td>${blog_MesVO.updateTime}</td>
			<td>${blog_MesVO.status}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>