<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>TEA101G4 SPEC: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #e7e3ff;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: grey;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>TEA101G4 SPEC: Home</h3>
				<h4>
					<a><img src="images/kanahei.png" width="80" height="65"
						border="0">MVC</a>
				</h4></td>
		</tr>
	</table>
	<p>This is the Home page for TEA101G4 SPEC: Home</p>
	<h3>（商品）規格查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<ul>
		<li><a href='listAllspec.jsp'>List</a> All Spec. <br>
			<br>
		</li>
		<li>
			<FORM METHOD="post" ACTION="spec.do">
				<b>輸入規格編號 (如SD0001):</b> 
					<input type="text" name="specid"> 
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="送出">
			</FORM>
		</li>
	<!--  -->	
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
	<li>
		<FORM METHOD="post" ACTION="spec.do">
			<b>輸入商品編號（如PD0001）:</b> 
				<input type="text" name="productid">
				<input type="hidden" name="action" value="getSomeList">
				<input type="submit" value="送出">
		</FORM>
	</li> 
	<!--  -->
		<li>
			<FORM METHOD="post" ACTION="spec.do">
				<b>選擇規格編號:</b> 
					<select size="1" name="specid">
					<c:forEach var="specVO" items="${specSvc.all}">
						<option value="${specVO.specid}">${specVO.specid}
					</c:forEach>
					</select>
					 <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
			</FORM>
		</li>
		<!--  -->
	</ul>
	<h3>(商品)規格管理</h3>
	<ul>
		<li><a href='addspec.jsp'>Add</a> a New Spec.</li>
	</ul>
	
	
	
	
</body>
</html>