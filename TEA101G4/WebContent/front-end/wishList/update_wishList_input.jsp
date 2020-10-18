<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wishList.model.*"%>

<%
	WishListVO wishListVO = (WishListVO) request.getAttribute("wishListVO"); //ClassDetailServlet.java (Concroller) 存入req的ClassDetailVO物件 (包括幫忙取出的ClassDetailVO, 也包括輸入資料錯誤時的ClassDetailVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_classDetail_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
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
				<h3>wishListId修改 - update_wishList_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/wishList/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>wishListId:<font color=red><b>*</b></font></td>
				<td><%=wishListVO.getWishListId()%></td>
			</tr>
			<tr>
				<td>新增日：<font color=blue><b>*</b></font></td>
				<td><%=wishListVO.getAddDate()%> <input type="hidden"
					name="addDate" value="<%=wishListVO.getAddDate()%>" /></td>
			</tr>
			<tr>
				<td>memberId:</td>
				<td><input type="TEXT" name="memberId" size="45"
					value="<%=wishListVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>productId:</td>
				<td><input type="TEXT" name="productId" size="45"
					value="<%=wishListVO.getProductId()%>" /></td>
			</tr>
			<tr>
				<td>狀態：</td>
				<td><select name="likeStatus">
						<option value="N" ${(wishListVO.likeStatus=="N")? 'selected':'' }>N</option>
						<option value="Y" ${(wishListVO.likeStatus=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>
			


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="wishListId"
			value="<%=wishListVO.getWishListId()%>"> <input type="submit"
			value="送出修改">
	</FORM>
</body>




</script>
</html>