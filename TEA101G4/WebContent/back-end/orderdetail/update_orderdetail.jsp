<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);
	
	OrderdetailVO orderdetailVO = (OrderdetailVO) request.getAttribute("orderdetailVO");
	System.out.println(orderdetailVO);
%>
<html>
<body id="page-top">
<!-- =================================Page Wrapper(include)================================= -->
   <div id="wrapper">
	<%@ include file="/back-end/component/sidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
    <%@ include file="/back-end/component/topbar.jsp" %>
    <!-- Begin Page Content -->
    <div class="container-fluid">
    <!-- Page Heading -->
<!-- ============================================================ -->  
<h4>
	<a href="<%=request.getContextPath()%>/back-end/ordermaster/listAllordermaster.jsp">
	<img src="images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">編輯訂單內容：</h3>
<div>
<!-- ============================================================ -->         
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- ============================================================ --> 

<!-- ============================================================ -->
<FORM METHOD="post" ACTION="orderdetail.do" name="form1">
	<table>
		<tr>
			<td>訂單明細編號:<font color=red><b>*</b></font></td>
			<td><%=orderdetailVO.getOrderdetailid()%>
			<input type="hidden" name="orderdetailid"  value="<%=orderdetailVO.getOrderdetailid()%>" />
			</td>
		</tr>
		<tr>
			<td>訂單編號:<font color=red><b>*</b></font></td>
			<td><%=orderdetailVO.getOrdermasterid()%>
			<input type="hidden" name="ordermasterid"  value="<%=orderdetailVO.getOrdermasterid()%>" />
			</td>
		</tr>
		
		<tr>
			<td>商品規格編號：<font color=red><b>*</b></font></td>
			<td><%=orderdetailVO.getSpecid()%>
			<input type="hidden" name="specid"  value="<%=orderdetailVO.getSpecid()%>" />
			</td>
		</tr>

		<tr>
			<td>購買數量：</td>
			<td>
			<input type="TEXT" name="quantity" size="45" value="<%=orderdetailVO.getQuantity()%>" />
			</td>
		</tr>
	</table>
	<br> 
	<input type="hidden" name="action" value="update"> 
	<input type="submit" value="Send">
	</FORM>
<!-- product section end -->
<!-- ---------------------------------------------------------------- -->
</div>
</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- End of Footer -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
<!-- ---------------------------------------------------------------- -->
	
</body>
<script>
   	function ShowTime(){
 		　document.getElementById('now').innerHTML = new Date();
 		　setTimeout('ShowTime()',1000);
 		} 
 </script>
</html>