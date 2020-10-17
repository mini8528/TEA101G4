<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spec.model.*"%>

<%
	SpecVO specVO = (SpecVO) request.getAttribute("specVO");
	System.out.println(specVO);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>規格資料修改 - update_spec.jsp</title>

<style>
table#table-1 {
	background-color: #e7e3ff;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block;
	width: 100px;
	min-height: 100px;
	position: relative;
}

#preview span.text {
	position: absolute;
	display: inline-block;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: -1;
	color: lightgray;
}

#preview img.preview_img {
	width: 100%;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr>
		<td>
			<h3>規格資料修改 - update_spec.jsp</h3>
		</td>
		<td>
			<h4>
				<a href="select_page.jsp"> <img src="images/Pisuke.png"
					width="80" height="80" border="0">返回規格首頁
				</a>
			</h4>
		</td>
	</tr>
</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="get" ACTION="spec.do" name="form1">
		<table>
			<tr>
				<td>商品ID：</td>
				<td><%=specVO.getProductid()%> 
				<input type="hidden" name="specid" value="<%=specVO.getSpecid()%>"></td>
			</tr>
			<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
			<tr>
				<td>商品名稱:<font color=red><b>*</b></font></td>
				<td><select size="1" name="productid">
						<c:forEach var="productVO" items="${productSvc.all}">
							<option value="${productVO.productid}" ${(specVO.productid==productVO.productid)? 'selected':'' }>${productVO.name}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>規格：</td>
				<td><input type="TEXT" name="specific" size="45" value="<%=specVO.getSpecific()%>" /></td>
			</tr>
			<tr>
				<td>庫存：</td>
				<td><input type="TEXT" name="stock" size="45" value="<%=specVO.getStock()%>" /></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="submit" value="Send">
	</FORM>
</body>
</html>