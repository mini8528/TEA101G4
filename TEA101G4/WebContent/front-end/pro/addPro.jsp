<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.product.model.*"%>
<%
ProVO proVO = (ProVO) request.getAttribute("proVO");
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
MemberVO userVO= (MemberVO) session.getAttribute("userVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>



</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>課服資料新增 - addPro.jsp</h3></td><td>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="pro.do" name="form1" enctype="multipart/form-data">

<table>
	
	
	<tr>
		<td>商品編號:</td>
		<td><input type="hidden" name="productid" size="45" 
			 value="<%=productVO.getProductid()%>"/></td>
			 
			 
	</tr>
	
	<tr>
		<td>一般會員編號:</td>
		<td><input type="hidden" name="memberid" size="45" 
			 value="<%=userVO.getMemberid()%>"/></td>
			
	</tr>
	
	<tr>
		<td>評論內容 :</td>
		<td><input type="TEXT" name="commtext" size="45" 
			 value="<%= proVO.getCommtext()%>"/></td>
	</tr>
	
	<tr>
		<td>星級數:</td>
		<td><input type="TEXT" name="commstar" size="45" 
			 value="<%= proVO.getCommstar()%>" /></td>
	</tr>

	<tr>
		<td>新增日期:</td>
		<td><input name="adddate" id="adddate" type="hidden"
		 value="<%=proVO.getAdddate()%>" /></td>
	</tr>
	
	<tr>
		
		<td><input type="hidden" name="status" size="45" 
			 value="Y"/></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = proVO.getAdddate();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
  
        
        
</script>
</html>