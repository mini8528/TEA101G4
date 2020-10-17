<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);
	
	BrandVO brandVO = (BrandVO) request.getAttribute("brandVO");
	System.out.println("listOneBrand.jsp_brandVO:"+brandVO);
	List<String> errorMsgs = new LinkedList<String>();
	request.setAttribute("errorMsgs", errorMsgs);
	if (brandVO == null) {
		errorMsgs.add("查無資料!! ️️❤ヽ(´▽`)/❤ ");
		System.out.println("errorMsgs:查無資料!!");
	}
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
	<a href="<%=request.getContextPath()%>/back-end/brand/listAllbrand.jsp">
	<img src="images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ -->  
    <h3 class="card-title text-warning">品牌列表：</h3>
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
<!-- <h4>
	<a href="select_page.jsp">
	<img src="images/usagi.png" width="100" height="100" border="0">返回首頁</a>
</h4> -->
<!-- ============================================================ -->  
<div class="table-responsive table-class-schedule">
<table class="table table-bordered text-center">
	<tr>
		<th class="bg-info text-white text-uppercase" scope="col">品牌編號</th>
		<th class="bg-info text-white text-uppercase" scope="col">名稱</th>
	</tr>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${brandVO.brandid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${brandVO.name}</td>
	</tr>
</table>
</div>
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