<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%
	OrderdetailVO orderdetailVO = (OrderdetailVO) request.getAttribute("orderdetailVO");
	System.out.println(orderdetailVO);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單明細成立 - addOrderDetail.jsp</title>

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
#preview {
	border: 1px solid lightgray;
	display: inline-block;
	width: 100px;
	min-height: 100px;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: -1;
	color: lightgray;
}

#preview img.preview_img {
	width: 100%;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white' onload="ShowTime()">
<div id="now"></div>
<table id="table-1">
	<tr>
		<td>
			<h3>訂單明細成立 - addOrderDetail.jsp</h3>
		</td>
		<td>
			<h4>
				<a href="select_page.jsp"> <img src="images/Pisuke.png"
					width="80" height="80" border="0">返回首頁
				</a>
			</h4>
		</td>
	</tr>
</table>

<h3>訂單資料:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="orderdetail.do" name="form1">
	<table>
<jsp:useBean id="ordermasterSvc" scope="page" class="com.ordermaster.model.OrdermasterService" />
		<tr>
			<td>訂單編號：<font color=red><b>*</b></font></td>
			<td>
			<select size="1" name="ordermasterid">
				<c:forEach var="ordermasterVO" items="${ordermasterSvc.all}">
					<option value="${ordermasterVO.ordermasterid} " ${(orderdetailVO.ordermasterid=="(ordermasterVO.ordermasterid)")? 'selected':'' }>${ordermasterVO.ordermasterid}</option>
				</c:forEach>
			</select> 
			</td>
		</tr>
 <jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
		<tr>
			<td>商品規格編號：<font color=red><b>*</b></font></td>
			<td>
	 			<select size="1" name="specid">
					<c:forEach var="specVO" items="${specSvc.all}">
						<option value="${specVO.specid} " ${(orderdetailVO.specid=="(specVO.specid)")? 'selected':'' }>${specVO.specid}</option>
					</c:forEach>
				</select> 
 			</td>
		</tr>

		<tr>
			<td>購買數量：</td>
			<td>
			<input type="TEXT" name="quantity" size="45" value="<%=(orderdetailVO == null) ? "1" : orderdetailVO.getQuantity()%>" />
			</td>
		</tr>
	</table>
	<br> 
	<input type="hidden" name="action" value="insert"> 
	<input type="submit" value="Send">
	</FORM>
</body>
<script>
   	function ShowTime(){
 		　document.getElementById('now').innerHTML = new Date();
 		　setTimeout('ShowTime()',1000);
 		} 
 </script>
</html>