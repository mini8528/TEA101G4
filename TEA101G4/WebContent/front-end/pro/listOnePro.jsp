<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pro.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOnepro.jsp</title>

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
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�����s��</th>
		<th>�ӫ~�s��</th>
		<th>�@��|���s��</th>
		<th>���פ��e</th>
		<th>�P�ż�</th>
		<th>�s�W���</th>
		<th>�ק���</th>
		<th>���ת��A</th>
	</tr>
	<tr>
		<td><%=proVO.getProdcommid()%></td>
		<td><%=proVO.getProductid()%></td>
		<td><%=proVO.getMemberid()%></td>
		<td><%=proVO.getCommtext()%></td>
		<td><%=proVO.getCommstar()%></td>
		<td><%=proVO.getAdddate()%></td>
		<td><%=proVO.getEditdate()%></td>
		<td><%=proVO.getStatus()%></td>
	
	</tr>
</table>

</body>
</html>