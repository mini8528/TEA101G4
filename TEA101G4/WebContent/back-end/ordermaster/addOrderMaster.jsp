<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>

<%
OrdermasterVO ordermasterVO = (OrdermasterVO) request.getAttribute("ordermasterVO");
System.out.println(ordermasterVO);

//java.sql.Date(今天)(如:2010-08-18)
long long1 = System.currentTimeMillis();
java.sql.Date ds1 = new java.sql.Date(long1);
System.out.println("java.sql.Date()  = "+ ds1);

//java.sql.Date(3天後)(如:2010-09-17)
long long2 = long1+ 3*24*60*60*1000L;
java.sql.Date ds2 = new java.sql.Date(long2);
System.out.println("java.sql.Date()  = "+ ds2);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單成立 - addOrderMaster.jsp</title>

<style>
table#table-1 {
	background-color: #fbffb5;
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
			<h3>訂單成立 - addOrderMaster.jsp</h3>
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

<FORM METHOD="post" ACTION="ordermaster.do" name="form1">
	<table>
		<tr>
			<td>會員編號：</td>
			<td>
			<input type="TEXT" name="memberid" size="45" value="<%=(ordermasterVO == null) ? "M004" : ordermasterVO.getMemberid()%>" />
			</td>
		</tr>
		<tr>
			<td>付款方式：</td>
			<td>
			<input type="radio" name="payment" value="信用卡" checked>
	 		 <label for="信用卡" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>信用卡</label>
	 		 <input type="radio" name="payment" value="超商付款">
	 		 <label for="超商付款" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>超商付款</label>
			</td>
		</tr>

		<tr>
			<td>下單日：<font color=blue><b>*</b></font></td>
			<td>
			<%=ds1 %>
			</td>
		</tr>
		<tr>
			<td>付款期限：<font color=blue><b>*</b></font></td>
			<td>
			<%=ds2 %>
			</td>
		</tr>
		<tr>
			<td>超商付款代碼：</td>
			<td>
			<input type="TEXT" name="paycode" size="45" value="<%=(ordermasterVO == null) ? "code9999" : ordermasterVO.getPaycode()%>" />
			</td>
		</tr>
		<tr>
			<td>付款狀態：</td>
			<td>
			<select name="paystatus">
			<option value="N" ${(ordermasterVO.paystatus=="N")? 'selected':'' }>N</option>
			<option value="Y" ${(ordermasterVO.paystatus=="Y")? 'selected':'' }>Y</option>
			</select> 
			</td>
		</tr>
		<tr>
			<td>收貨人：</td>
			<td>
			<input type="TEXT" name="receiver" size="45" value="<%=(ordermasterVO == null) ? "小白" : ordermasterVO.getReceiver()%>" />
			</td>
		</tr>
		<tr>
			<td>電話：</td>
			<td>
			<input type="TEXT" name="tel" size="45" value="<%=(ordermasterVO == null) ? "0900-000-000" : ordermasterVO.getTel()%>" />
			</td>
		</tr>
		<tr>
			<td>地址：</td>
			<td>
			<input type="TEXT" name="address" size="45" value="<%=(ordermasterVO == null) ? "地址_test" : ordermasterVO.getAddress()%>" />
			</td>
		</tr>
	</table>
	<br> 
	<input type="hidden" name="action" value="insert"> 
	<input type="submit" value="Send">
	</FORM>
</body>

<%
Timestamp orderdate = null;
try {
	orderdate = ordermasterVO.getOrderdate();
} catch (Exception e) {
	orderdate = new Timestamp(System.currentTimeMillis());
}



%>
<script>
   	function ShowTime(){
 		　document.getElementById('now').innerHTML = new Date();
 		　setTimeout('ShowTime()',1000);
 		} 
/* 	var Today=new Date();
		document.write("下單日期： " + Today.getFullYear()+ " 年 " + (Today.getMonth()+1) + " 月 " + Today.getDate() + " 日\n");
	
	var Today2=new Date();
		document.write("/"+"付款期限： " + Today2.getFullYear()+ " 年 " + (Today2.getMonth()+1) + " 月 " +(Today2.getDate()+3)+ " 日前"); */
</script>

</html>