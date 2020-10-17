<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);

	OrdermasterVO ordermasterVO = (OrdermasterVO) request.getAttribute("ordermasterVO");
	System.out.println(ordermasterVO);
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
    <h3 class="card-title text-warning">訂單收件資料：</h3>
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
<table class="table table-bordered text-center">
<thead>
	<tr>
		<th class="bg-dark text-white text-uppercase" scope="col">訂單編號</th>
		<th class="bg-dark text-white text-uppercase" scope="col">會員編號</th>
		<th class="bg-dark text-white text-uppercase" scope="col">付款方式</th>
		<th class="bg-dark text-white text-uppercase" scope="col">付款狀態</th>
		<th class="bg-dark text-white text-uppercase" scope="col">下單日期</th>
		<th class="bg-dark text-white text-uppercase" scope="col">付款期限</th>
		<th class="bg-dark text-white text-uppercase" scope="col">超商付款代碼</th>
		<!-- <th class="bg-dark text-white text-uppercase" scope="col">收貨人</th>
		<th class="bg-dark text-white text-uppercase" scope="col">收貨人電話</th>
		<th class="bg-dark text-white text-uppercase" scope="col">收貨地址</th> -->
		<th class="bg-dark text-white text-uppercase" scope="col">訂單狀態</th>
		<th class="bg-dark text-white text-uppercase" scope="col">編輯</th>
	</tr>
</thead>
	<tr>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.ordermasterid}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">
		<a href="<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do?ordermasterid=${ordermasterVO.ordermasterid}&action=getSomeList_ordermasterid">${ordermasterVO.ordermasterid}</a>
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.memberid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.payment}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paystatus}</td>
		<%-- <td>${ordermasterVO.orderdate}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.orderdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<%-- <td>${ordermasterVO.payexpire}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.payexpire}" pattern="yyyy-MM-dd"/></td>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paycode}</td> --%>
		<c:choose>
			<c:when test="${ordermasterVO.payment == '超商付款'}">
			<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paycode}</td>
			</c:when>
			<c:otherwise>
			<td class="py-5 align-middle text-muted font-weight-medium">-</td>
			</c:otherwise>
		</c:choose>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.receiver}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.tel}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.address}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.orderstatus}</td>
		
		<td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "ordermasterid" value = "${ordermasterVO.ordermasterid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
	</tr>
</table>
<!-- product section -->
<table class="table table-bordered text-center">
	<thead>
	<tr>
		<th class="bg-dark text-white text-uppercase" scope="col">收貨人</th>
		<th class="bg-dark text-white text-uppercase" scope="col">收貨人電話</th>
		<th class="bg-dark text-white text-uppercase" scope="col">收貨地址</th>
	</tr>
	</thead>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.receiver}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.tel}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.address}</td>
	</tr>
</table>
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
</html>