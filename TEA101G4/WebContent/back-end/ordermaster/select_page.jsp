<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);
	String adminid = new String(userVO.getAdminid());
%> --%>
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
    <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
<div>
<!-- ============================================================ -->         
<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">訂單管理</h1>
	</div>
<!-- Content Row -->
<div class="row">
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
<ul>
	<li><a href='listAllordermaster.jsp'>List</a> All OrderMaster. <br>
		<br>
	</li>
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>輸入訂單編號 (如OR00001):</b> 
				<input type="text" name="ordermasterid"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
	<!--  -->	
<jsp:useBean id="ordermasterSvc" scope="page" class="com.ordermaster.model.OrdermasterService" />
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>會員ID查看訂單列表 (如M004):</b> 
				<input type="text" name="memberid"> 
				<input type="hidden" name="action" value="getSomeList_memberid"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
	
	<li>
	<FORM METHOD="post" ACTION="ordermaster.do">
		<b>查看超過付款期限訂單:</b> 
			<input type="hidden" name="paystatus" value = "${ordermasterVO.paystatus}"> 
			<input type="hidden" name="action" value="list_ByOrderstatus"> 
			<input type="submit" value="查詢">
	</FORM>
</li>
<!--  -->
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>依訂單出貨狀態篩選:</b> 
				<select size="1" name="orderstatus">
					<option value="未出貨" ${(ordermasterVO.orderstatus=="未出貨")? 'selected':'' }>未出貨</option>
					<option value="已出貨" ${(ordermasterVO.orderstatus=="已出貨")? 'selected':'' }>已出貨</option>
				<%-- 
				<c:forEach var="ordermasterVO" items="${ordermasterSvc.all}">
					<option value="${ordermasterVO.orderstatus}">${ordermasterVO.orderstatus}
				</c:forEach> 
				--%>
				</select> 
				<input type="hidden" name="action" value="getSomeList_orderstatus">
				<input type="submit" value="送出">
		</FORM>
	</li>
	<li><a href='addOrderMaster.jsp'>Add</a> a New Product.</li>
</ul>
<!-- ---------------------------------------------------------------- -->
</div>
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
</html>