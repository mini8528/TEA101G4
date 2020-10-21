<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);

	SpecVO specVO = (SpecVO) request.getAttribute("specVO");
	System.out.println(specVO);
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
	<FORM METHOD="post" ACTION="spec.do" name="form1" >
	<table>
	
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
			<tr>
				<td>產品ID:<font color=red><b>*</b></font></td>
				<td><select size="1" name="productid">
						<c:forEach var="productVO" items="${productSvc.all}">
							<option value="${productVO.productid}" ${(specVO.productid==productVO.productid)? 'selected':'' }>${productVO.name}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>規格：</td>
				<td>
				<input type="TEXT" name="specific" size="45" value="<%=(specVO == null) ? "ex:2KG" : specVO.getSpecific()%>" /> 
				</td>
			</tr>
			<!-- ********************* -->
			<tr>
				<td>庫存：</td>
				<td><input type="TEXT" name="stock" size="45" value="<%=(specVO == null) ? "50" : specVO.getStock()%>" /></td>
			</tr>
		</table>
		<br> 
			<input type="hidden" name="action" value="insert"> 
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
</html>