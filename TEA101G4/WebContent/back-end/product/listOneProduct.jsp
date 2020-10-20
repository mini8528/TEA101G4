<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);

	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	System.out.println(productVO);
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
	<a href="<%=request.getContextPath()%>/back-end/product/listAllproduct.jsp">
	<img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">商品內容：</h3>
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
		<th class="bg-dark text-white text-uppercase" scope="col">商品編號</th>
		<th class="bg-dark text-white text-uppercase" scope="col">照片</th>
		<th class="bg-dark text-white text-uppercase" scope="col">照片2</th>
		<th class="bg-dark text-white text-uppercase" scope="col">照片3</th>
		<th class="bg-dark text-white text-uppercase" scope="col">名稱</th>
		<!-- <th>規格</th> join-->
		<th class="bg-dark text-white text-uppercase" scope="col">價格</th>
		<!-- <th>庫存</th> join-->
		<th class="bg-dark text-white text-uppercase" scope="col">品牌</th>
		<th class="bg-dark text-white text-uppercase" scope="col">種類</th>
		<th class="bg-dark text-white text-uppercase" scope="col">商品描述</th>
		<!-- <th>上架日期</th>
		<th>上架人員</th>
		<th>編輯日期</th>
		<th>編輯人員</th> -->
		<th class="bg-dark text-white text-uppercase" scope="col">商品狀態</th>
		<th class="bg-dark text-white text-uppercase" scope="col">編輯</th>
	</tr>
</thead>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.productid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="100" height="100">
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<img src="<%=request.getContextPath()%>/back-end/product/productshow2.do?productid=${productVO.productid}" width="100" height="100">
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<img src="<%=request.getContextPath()%>/back-end/product/productshow3.do?productid=${productVO.productid}" width="100" height="100">
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.name}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.price}</td>
		<%-- <td>${productVO.brandid}</td> --%>
		<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
		<c:forEach var="brandVO" items="${brandSvc.all}">
			<c:if test="${productVO.brandid==brandVO.brandid}">
			 <td class="py-5 align-middle text-muted font-weight-medium">${brandVO.name}</td>
	 		</c:if>
		</c:forEach>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.category}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.intro}</td>
		<%-- <td>${productVO.adddate}</td>
		<td>${productVO.adminid}</td>
		<td>${productVO.editdate}</td>
		<td>${productVO.adminid2}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.status}</td>
		
		<td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/product/product.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "productid" value = "${productVO.productid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
	</tr>
</table>
<!-- product section -->
<table class="table table-bordered text-center">
	<thead>
	<tr>
		<th class="bg-dark text-white text-uppercase" scope="col">上架日期</th>
		<th class="bg-dark text-white text-uppercase" scope="col">上架人員</th>
		<th class="bg-dark text-white text-uppercase" scope="col">編輯日期</th>
		<th class="bg-dark text-white text-uppercase" scope="col">編輯人員</th>
	</tr>
	</thead>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adddate}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adminid}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.editdate}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adminid2}</td>
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