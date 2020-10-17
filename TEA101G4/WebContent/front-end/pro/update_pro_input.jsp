<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_emp_input.jsp</title>

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
	<tr><td>
		 <h3>克服資料修改 - update_pro_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>商品評價編號:<font color=red><b>*</b></font></td>
		<td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
<!-- 		<td>商品編號:</td> -->
<!-- 		<td><input type="hidden" name="productid" size="45"  -->
<%-- 			 value="<%=proVO.getProductid()%>" /></td> --%>
			 <td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>一般會員編號:</td>
<!-- 		<td><input type="TEXT" name="memberid" size="45"  -->
<%-- 			 value="<%=proVO.getMemberid()%>" /></td> --%>
			 <td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>評論內容:</td>
<!-- 		<td><input type="TEXT" name="commtext" size="45"  -->
<%-- 			 value="<%=proVO.getCommtext()%>" /></td> --%>
			 <td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>星級數:</td>
<!-- 		<td><input type="TEXT" name="commstar" size="45"  -->
<%-- 			 value="<%=proVO.getCommstar()%>" /></td> --%>
			 <td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>新增日期:</td>
		<td><input type="hidden" name="adddate" size="45" 
			 value="<%=proVO.getAdddate()%>" /></td>
	</tr>
	
	<tr>
		<td>修改日期:</td>
		<td><input type="hidden" name="editdate" size="45" 
			 value="<%=proVO.getEditdate()%>" /></td>
	</tr>
	
	<tr>
		<td>評論狀態:</td>
		<td><input type="TEXT" name="status" size="45" 
			 value="<%=proVO.getStatus()%>"/></td>
	</tr>

	
	


</table>
<br>
<input type="hidden" name="action" value="updatetext">
<input type="hidden" name="prodcommid" value="<%=proVO.getProdcommid()%>">
<input type="submit" value="送出修改">
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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