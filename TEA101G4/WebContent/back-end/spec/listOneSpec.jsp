<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spec.model.*"%>
<%
	SpecVO specVO = (SpecVO) request.getAttribute("specVO");
	System.out.println(specVO);
%>
<html>
<head>
<title>商品資料 - listOneSpec.jsp</title>
<style>
table#table-1 {
	background-color: #e7e3ff;
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
	<h4>TEST_SPEC_查單筆:</h4>
		<table id="table-1">
		<tr>
		<td>
			<h3>商品資料 - ListOneSpec.jsp</h3>
			<h4>
				<a href="select_page.jsp">
				<img src="images/usagi.png" width="100" height="100" border="0">返回首頁</a>
			</h4>
		</td>
		</tr>
	</table>
	
	<table>
	<tr>
		<th>規格編號</th>
		<th>商品編號</th>
		<th>規格</th>
		<th>庫存</th>
		<th>編輯</th>
	</tr>
	<tr>
		<td>${specVO.specid}</td>
		<td>${specVO.productid}</td>
		<td>${specVO.specific}</td>
		<td>${specVO.stock}</td>
		
		<td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/spec/spec.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "specid" value = "${specVO.specid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
	</tr>
	</table>
</body>
</html>