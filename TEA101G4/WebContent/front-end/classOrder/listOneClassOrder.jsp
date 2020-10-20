<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.classOrder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ClassOrderVO classOrderVO = (ClassOrderVO) request.getAttribute("classOrderVO"); //ClassOrderServlet.java(Concroller), 存入req的ClassOrderVO物件
%>
<!DOCTYPE html>
<html>
<head>
<title>ClassOrder資料 - listOneClassOrder.jsp</title>

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
	width: 700px;
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
		 <h3>ClassOrder資料 - ListOneClassOrder.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>課程明細ID</th>
		<th>會員ID</th>
		<th>付款方式</th>
		<th>付款狀態</th>
		<th>下訂時間</th>
		<th>付款到期</th>
		<th>繳費代碼</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	<tr>
		<td><%=classOrderVO.getClassOrderID()%></td>
		<td><%=classOrderVO.getMemberID()%></td>
		<td><%=classOrderVO.getPayment()%></td>
		<td><%=classOrderVO.getPaymentStatus()%></td>
		<td><%=classOrderVO.getOrderDate()%></td>
		<td><%=classOrderVO.getPayExpire()%></td>
		<td><%=classOrderVO.getPayCode()%></td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/classOrder/classOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="classOrderID"  value="${classOrderVO.classOrderID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/classOrder/classOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="classOrderID"  value="${classOrderVO.classOrderID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		
	</tr>
</table>

</body>
</html>