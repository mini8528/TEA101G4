<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%
	OrderdetailService orderdetailSvc = new OrderdetailService();
	List<OrderdetailVO> list = orderdetailSvc.getAll();
	System.out.println("訂單明細總筆數：" + list.size());
	pageContext.setAttribute("list", list);
	List<String> errorMsgs = new LinkedList<String>();
	request.setAttribute("errorMsgs", errorMsgs);
	if (list.size() == 0) {
		errorMsgs.add("查無資料!! ️️❤ヽ(´▽`)/❤ ");
		System.out.println("errorMsgs:查無資料!!");
	}
%>
<html>
<head>
<title>所有訂單明細資料 - listAllorderdetail.jsp</title>

<style>
table#table-1 {
	background-color: #d9ffef;
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
<table id="table-1">
	<tr>
		<td>
			<h3>所有訂單明細資料 - listAllorderdetail.jsp</h3>
			<h4>
				<a href="select_page.jsp"> 
				<img src="images/usagi.png" width="100" height="100" border="0">返回訂單明細首頁</a>
			</h4>
		</td>
	</tr>
</table>
<!-- 錯誤列表 -->
<c:if test="${not empty errorMsgs}">
	<font style = "color: red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var = "message" items = "${errorMsgs}">
			<li style = "color: green">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>訂單明細編號</th>
		<th>訂單編號</th>
		<th>商品規格編號</th>
		<th>購買數量</th>
		<th>編輯</th>
		<th>刪除</th>
	</tr>
	<%@ include file = "page1.file" %>
<c:forEach var = "orderdetailVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${orderdetailVO.orderdetailid}</td>
		<td>${orderdetailVO.ordermasterid}</td>
		<td>${orderdetailVO.specid}</td>
		<td>${orderdetailVO.quantity}</td>
		
		<td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "orderdetailid" value = "${orderdetailVO.orderdetailid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Delete">
				<input type = "hidden" name = "orderdetailid" value = "${orderdetailVO.orderdetailid}">
				<input type = "hidden" name = "action" value = "delete">
			</FORM>
		</td>	
	</tr>
</c:forEach>
</table>
<%@ include file = "page2.file" %>
</body>
</html>