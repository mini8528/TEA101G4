<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog_save.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Blog_SaveVO blogSaveVO = (Blog_SaveVO) request.getAttribute("blog_SaveVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�����榬�ø�� - listOneBlog_Save.jsp</title>

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
		 <h3>������d����� - ListOneBlog_Save.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog_save/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�峹���ýs��</th>
		<th>�@��|���s��</th>
		<th>�峹�s��</th>
		<th>���ê��A</th>
		<th>���ä��</th>
		<th>��s���</th>
		
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