<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);
	String adminid = new String(userVO.getAdminid());
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
    <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
<div>
<!-- ============================================================ -->         
<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">品牌管理</h1>
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
	<li><a href='listAllbrand.jsp'>List</a> all Brands. <br> <br></li>
	<li>
		<FORM METHOD="post" ACTION="brand.do">
			<b>輸入品牌編號 (如BD01):</b> <input type="text" name="brandid"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
	<!--  -->	
	<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
	<li>
		<FORM METHOD="post" ACTION="brand.do">
			<b>關鍵字搜尋品牌 (如Alex):</b> <input type="text" name="name"> 
				<input type="hidden" name="action" value="getSomeList"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
	<!--  -->
	<%-- <jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" /> --%>
	<li>
		<FORM METHOD="post" ACTION="brand.do">
			<b>選擇品牌編號:</b> 
			<select size="1" name="brandid">
				<c:forEach var="brandVO" items="${brandSvc.all}">
					<option value="${brandVO.brandid}">${brandVO.brandid}
				</c:forEach>
			</select> 
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</FORM>
	</li>
	<!--  -->
	<li>
		<FORM METHOD="post" ACTION="brand.do">
			<b>選擇品牌名稱:</b> 
				<select size="1" name="brandid">
				<c:forEach var="brandVO" items="${brandSvc.all}">
					<option value="${brandVO.brandid}">${brandVO.name}
				</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
		</FORM>
	</li>
	<!--  -->
	<li><a href='addBrand.jsp'>Add</a> a new Brand.</li>
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