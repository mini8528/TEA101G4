<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);
	String adminid = new String(adminVO.getAdminid());

	SpecService specSvc = new SpecService();
	List<SpecVO> list = specSvc.getAll();
	System.out.println("Spec筆數：" + list.size());
	pageContext.setAttribute("list", list);
%>
<html>
<head>
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
    <h3 class="card-title text-warning">所有規格列表：</h3>
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
		<th>規格編號</th>
		<th>商品編號</th>
		<th>規格</th>
		<th>庫存</th>
		<!-- <th>編輯</th>
		<th>刪除</th> -->
	</tr>
 </thead>
<%@ include file = "page1.file" %>
<c:forEach var = "specVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tbody>
	<tr>
		<td>${specVO.specid}</td>
		<td>${specVO.productid}</td>
		<td>${specVO.specific}</td>
		<td>${specVO.stock}</td>
		
		<%-- <td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/spec/spec.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Edit">
				<input type = "hidden" name = "specid" value = "${specVO.specid}">
				<input type = "hidden" name = "action" value = "getOne_For_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/spec/spec.do" style = "margin-bottom: 0px;">
				<input type = "submit" value = "Delete">
				<input type = "hidden" name = "specid" value = "${specVO.specid}">
				<input type = "hidden" name = "action" value = "delete">
			</FORM>
		</td> --%>	
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