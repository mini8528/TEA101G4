<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classOrder.model.*"%>

<%
	ClassOrderVO classOrderVO = (ClassOrderVO) request.getAttribute("classOrderVO"); //ClassOrderServlet.java (Concroller) 存入req的ClassOrderVO物件 (包括幫忙取出的ClassOrderVO, 也包括輸入資料錯誤時的ClassOrderVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>課程訂單修改 - update_classOrder_input.jsp</title>

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
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>classOrderID修改 - update_classOrder_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="classOrder.do" name="form1">
		<table>


			<tr>
				<td>訂單編號：<font color=blue><b>*</b></font></td>
				<td><%=classOrderVO.getClassOrderID()%></td>
			</tr>
			<tr>
				<td>會員編號：<font color=blue><b>*</b></font></td>
				<td><%=classOrderVO.getMemberID()%> <input type="hidden"
					name="memberID" value="<%=classOrderVO.getMemberID()%>" /></td>
			</tr>


			<tr>
				<td>下單日：<font color=blue><b>*</b></font></td>
				<td><%=classOrderVO.getOrderDate()%></td>
			</tr>
			<tr>
				<td>付款期限：<font color=blue><b>*</b></font></td>
				<td><%=classOrderVO.getPayExpire()%></td>
			</tr>

			<tr>
				<td>付款方式：</td>
				<td><input type="radio" name="payment" value="信用卡" checked>
					<label for="信用卡" ${(classOrderVO.payment=="信用卡")? 'selected':'' }>信用卡</label>
					<input type="radio" name="payment" value="超商付款"> <label
					for="超商付款" ${(classOrderVO.payment=="超商付款")? 'selected':'' }>超商付款</label>
				</td>
			</tr>


			<tr>
				<td>付款狀態：</td>
				<td><select name="paymentStatus">
						<option value="N"
							${(classOrderVO.paymentStatus=="N")? 'selected':'' }>N</option>
						<option value="Y"
							${(classOrderVO.paymentStatus=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>

			<tr>
				<td>超商付款代碼：</td>
				<td><input type="TEXT" name="payCode" size="45"
					value="<%=(classOrderVO == null) ? "code9999" : classOrderVO.getPayCode()%>" />
				</td>
			</tr>


		</table>
		<br> 
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="orderDate"	value="<%=classOrderVO.getOrderDate()%>" /> 
			<input type="hidden" name="payExpire" value="<%=classOrderVO.getPayExpire()%>" /> 
			<input type="hidden" name="classOrderID" value="<%=classOrderVO.getClassOrderID()%>"> 
			<input type="submit" value="送出修改">
	</FORM>
</body>


</html>