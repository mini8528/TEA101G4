<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>TEA101G4 ORDERDETAIL: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #d9ffef;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: grey;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>TEA101G4 ORDERDETAIL: Home</h3>
				<h4>
					<a><img src="images/kanahei.png" width="80" height="65"
						border="0">MVC</a>
				</h4></td>
		</tr>
	</table>
	<p>This is the Home page for TEA101G4 ORDERDETAIL: Home</p>
	<h3>訂單資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
		<li><a href='listAllorderdetail.jsp'>List</a> All OrderDetail. <br>
			<br>
		</li>
		<!-- <li>
			<FORM METHOD="post" ACTION="orderdetail.do">
				<b>輸入訂單明細編號 (如TL00001):</b> 
					<input type="text" name="orderdetailid"> 
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="送出">
			</FORM>
		</li> -->
	<!--  -->	
<%-- <jsp:useBean id="orderdetailSvc" scope="page" class="com.orderdetail.model.OrderdetailService" /> --%>
	<li>
		<FORM METHOD="get" ACTION="orderdetail.do">
			<b>用訂單編號查詢 (如OR00001):</b> 
				<input type="text" name="ordermasterid"> 
				<input type="hidden" name="action" value="getSomeList_ordermasterid"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
	
	</ul>
	<h3>訂單明細管理</h3>
	<ul>
		<li><a href='addOrderDetail.jsp'>Add</a> a New OrderDetail.</li>
	</ul>

</body>
</html>