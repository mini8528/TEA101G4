<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishList.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="list" scope="session" type="java.util.List<WishListVO>" />
<%
// WishListService wlService = new WishListService();
//     List<WishListVO> list = wlService.getAll();
//     pageContext.setAttribute("list",list);
%>
<jsp:useBean id="productSvc" class="com.product.model.ProductService"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<title>部分WishList資料 - listSomeWishList.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>部分ClassDetail資料 - listSomeWishList.jsp</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/front-end/wishList/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<section class="py-8 py-md-10">
  <div class="container">
    <div class="table-responsive-sm table-cart">
      <table class="table mb-0">
        <thead>
	<tr>
<!-- 		<th>wishListId</th> -->
<!-- 		<th>memberId</th> -->
		<th scope="col"></th>
		<th scope="col"><center>商品名稱</center></th>
<!-- 		<th>likeStatus</th> -->
		<th scope="col"><center>收藏日期</center></th>
		<th scope="col"><center>更新日期</center></th>
<!-- 		<th>修改</th> -->
		<th scope="col"><center>刪除</center></th>
		<th scope="col"><center>詳細</center></th>
	</tr>
	</thead>
	<%@ include file="page1.file" %> 
	<c:forEach var="wishListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
<%-- 			<td>${wishListVO.wishListId}</td> --%>
<%-- 			<td>${wishListVO.memberId}</td> --%>
			<td>
				<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${wishListVO.productId}" alt="image"  width="100" height="70">
			</td>
			<td class="td-product-name">${productSvc.getOneProduct(wishListVO.productId).name}</td>
<%-- 			<td>${wishListVO.likeStatus}</td> --%>
			<td>${wishListVO.addDate}</td>
			<td>${wishListVO.editDate}</td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="wishListId"  value="${wishListVO.wishListId}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>														
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn-danger mb-2">
			     <input type="hidden" name="wishListId"  value="${wishListVO.wishListId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
				<a class="btn btn-link text-hover-warning text-underline text-underline pl-0" href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${wishListVO.productId}&action=getOne_For_Display_Front">
            	<i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More
          		</a>
			</td>
		</tr>
	</c:forEach>
   </table>
  </div>
 </div>
</section>
<%@ include file="page2.file" %>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>