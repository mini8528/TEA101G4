<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pro.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOnepro.jsp</title>

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
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品評價編號</th>
		<th>商品編號</th>
		<th>一般會員編號</th>
		<th>評論內容</th>
		<th>星級數</th>
		<th>新增日期</th>
		<th>修改日期</th>
		<th>評論狀態</th>
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