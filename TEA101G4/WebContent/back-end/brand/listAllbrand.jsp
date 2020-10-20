<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);

	BrandService brandSvc = new BrandService();
	List<BrandVO> list = brandSvc.getAll();
	System.out.println("Brand筆數:"+list.size());
	pageContext.setAttribute("list", list);
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
	<img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">所有品牌列表：</h3>
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
<ul> 
<li><a href='<%=request.getContextPath()%>/back-end/brand/addBrand.jsp'>新增品牌</a></li> 
<!--  -->	
<li>
	<FORM METHOD="post" ACTION="brand.do">
		<b>關鍵字搜尋品牌(如Alex):</b> 
			<input type="text" name="name"> 
			<input type="hidden" name="action" value="getSomeList"> 
			<input type="submit" value="送出">
	</FORM>
</li>
</ul>
<!--  -->
<!-- ============================================================ -->  
<%@ include file="page1.file"%>
<div class="table-responsive table-class-schedule">
	<table class="table table-bordered text-center">
	  <thead>
	    <tr>
	      <th class="bg-info text-white text-uppercase" scope="col">品牌編號</th>
	      <th class="bg-info text-white text-uppercase" scope="col">名稱</th>
	      <th class="bg-info text-white text-uppercase" scope="col">修改</th>
	    </tr>
	  </thead>
	  <c:forEach var="brandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tbody>
			<tr>
				<td class="py-5 align-middle text-muted font-weight-medium">${brandVO.brandid}</td>
				<td class="py-5 align-middle text-muted font-weight-medium">${brandVO.name}</td>
				<td class="py-5 align-middle text-muted font-weight-medium">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/brand/brand.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="brandid" value="${brandVO.brandid}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
</div>
<%@ include file="page2.file"%>
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