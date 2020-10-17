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
	
%>

<jsp:useBean id="list" scope="session" type="java.util.List<OrderdetailVO>" />

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
    <h3 class="card-title text-warning">訂單明細：</h3>
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
<div class="table-responsive table-class-schedule">
<table class="table table-bordered text-center">
<thead>
	<tr>
		<th class="bg-dark text-white text-uppercase" scope="col">訂單明細編號</th>
		<th class="bg-dark text-white text-uppercase" scope="col">訂單編號</th>
		<th class="bg-dark text-white text-uppercase" scope="col">規格ID</th>
		<th class="bg-dark text-white text-uppercase" scope="col">數量</th>
		<th class="bg-dark text-white text-uppercase" scope="col">編輯</th>
		<th class="bg-dark text-white text-uppercase" scope="col">移除</th>
	</tr>
</thead>
	<%@ include file = "page1.file" %>
	<c:forEach var = "orderdetailVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tbody>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${orderdetailVO.orderdetailid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${orderdetailVO.ordermasterid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${orderdetailVO.specid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${orderdetailVO.quantity}</td>
		
		<td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "orderdetailid" value = "${orderdetailVO.orderdetailid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "X">
				<input type = "hidden" name = "orderdetailid" value = "${orderdetailVO.orderdetailid}">
				<input type = "hidden" name = "action" value = "delete">
			</FORM>
		</td>	
	</tr>
</tbody>
</c:forEach>
</table>
<%@ include file = "page2.file" %>
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
</html>