<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog_mes.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Blog_MesVO blogMesVO = (Blog_MesVO) request.getAttribute("blog_MesVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>������d����� - listOneBlog_Mes.jsp</title>

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
		 <h3>������d����� - ListOneBlog_Mes.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog_mes/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>������d���s��</th>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>�d�����e</th>
		<th>�d�����</th>
		<th>��s���</th>
		<th>�d�����A</th>
		
	</tr>
	<tr>
		<td><%=blogMesVO.getBlogMesno()%></td>
		<td><%=blogMesVO.getBlogno()%></td>
		<td><%=blogMesVO.getMemberId()%></td>
		<td><%=blogMesVO.getText()%></td>
		<td><%=blogMesVO.getPostDate()%></td>
		<td><%=blogMesVO.getUpdateTime()%></td>
		<td><%=blogMesVO.getStatus()%></td>
		
	</tr>
</table>

</body>
</html>