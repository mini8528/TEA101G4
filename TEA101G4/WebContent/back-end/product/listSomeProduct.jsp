<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);
%>
<jsp:useBean id="list" scope="session" type="java.util.List<ProductVO>" />
<%
/* 	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getProducts(request.getParameter("name"));
	System.out.println("符合的Product共：" + list.size());
	pageContext.setAttribute("list", list); */
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
	<img src="images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">所有商品列表：</h3>
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
<!--  -->
<ul>
<%-- <li>
	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
		<b>查詢商品:</b> 
			<select size="1" name="status">
				<option value="Y" ${(productVO.status=="Y")? 'selected':'' }>上架中</option>
				<option value="N" ${(productVO.status=="N")? 'selected':'' }>已下架</option>
			</select> 
			<input type="hidden" name="action" value="getSomeListByStatus">
			<input type="submit" value="送出">
	</FORM>
</li> --%>
<!--  -->
<li>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
		<b>關鍵字搜尋商品 (如蛋白):</b> 
			<input type="text" name="name"> 
			<input type="hidden" name="action" value="getSomeList"> 
			<input type="submit" value="送出">
	</FORM>
</li>
</ul>
<!--  -->
<!-- ============================================================ --> 
<div class="table-responsive table-class-schedule">
<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do"> 
<input type="hidden" name="action" value="change_status_pds"> 
<input type="submit" value="多筆上/下架">
<br>
<table class="table table-bordered text-center">
<thead>
	<tr>
		<th class="alert-info" scope="col"></th>
		<th class="alert-info" scope="col">商品ID</th>
		<th class="alert-info" scope="col">照片</th>
		<th class="alert-info" scope="col">名稱</th>
		<!-- <th>規格</th> join?-->
		<th class="alert-info" scope="col">價格</th>
		<!-- <th>庫存</th> join?-->
		<th class="alert-info" scope="col">品牌</th>
		<th class="alert-info" scope="col">種類</th>
		<!-- <th class="alert-info" scope="col">商品描述</th> -->
		<th class="alert-info" scope="col">上架日</th>
		<th class="alert-info" scope="col">上架者</th>
		<!-- <th class="alert-info" scope="col">編輯日期</th>
		<th class="alert-info" scope="col">編輯人員</th> -->
		<th class="alert-info" scope="col">狀態</th>
		<th class="alert-info" scope="col">編輯</th>
		<!-- <th>刪除</th> -->
	</tr>
</thead>
	<%@ include file = "page1.file" %>
	<c:forEach var = "productVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tbody>
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<input type="checkbox" name="productid" value="${productVO.productid}">
			<input type="hidden" name="status" value="${productVO.status}"> 
		</td>
		<td class="py-5 align-middle text-muted font-weight-medium">
		<a href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Display">${productVO.productid}</a>
		</td>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${productVO.productid}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">
			<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="50" height="50">
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
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${productVO.intro}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adddate}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adminid}</td>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">${productVO.editdate}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adminid2}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">${productVO.status}</td>

		<td class="py-5 align-middle text-muted font-weight-medium">
		<a href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Update">Edit</a>
		</td>
		<%-- <td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/product/product.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "productid" value = "${productVO.productid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td> --%>
		<%-- <td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/product/product.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Delete">
				<input type = "hidden" name = "productid" value = "${productVO.productid}">
				<input type = "hidden" name = "action" value = "delete">
			</FORM>
		</td>	 --%>
	</tr>
</tbody>
	</c:forEach>
</table>
</FORM>
</div>
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