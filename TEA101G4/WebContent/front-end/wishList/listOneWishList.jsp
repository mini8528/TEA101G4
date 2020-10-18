<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishList.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
WishListVO wishListVO = (WishListVO) request.getAttribute("wishListVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	System.out.println(productVO);
%>
<<jsp:useBean id="productSvc" class="com.product.model.ProductService"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<title>WishList資料 - listOneWishList.jsp</title>

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
	width: 600px;
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
<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>WishList資料 - ListOneWishList.jsp</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/front-end/wishList/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

<section class="py-8 py-md-10">
  <div class="container">
    <div class="table-responsive-sm table-cart">
      <table class="table mb-0">
        <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col"><center>商品名稱</center></th>
<%--             <th scope="col"><center>商品編號</center></th> --%>
            <th scope="col"><center>新增日期</center></th>
            <th scope="col"><center>更新日期</center></th>
            <th scope="col"><center>刪除</center></th>
            <th scope="col"><center>詳細</center></th>
          </tr>
        </thead>
                <tbody>
          <tr>
            <td>
              <img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${wishListVO.productId}" alt="image" class="cart-image d-none d-md-block"  width="100" height="70">
            </td>
<%--             <td><%=productVO.getName()%></td> --%>
            <td class="td-product-name">${productSvc.getOneProduct(wishListVO.productId).name}</td>
            <td><%=wishListVO.getAddDate()%></td>
            <td><%=wishListVO.getEditDate()%></td>
            <td>
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger mb-2">
			     <input type="hidden" name="wishListId"  value="${wishListVO.wishListId}">
			     <input type="hidden" name="action" value="delete"></FORM>
            </td>
            <td>
				<a class="btn btn-link text-hover-warning text-underline text-underline pl-0" href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${wishListVO.productId}&action=getOne_For_Display_Front">
            	<i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More
          		</a>
			</td>
          </tr>
		 </tbody>
		</table>
	   </div>
	 </div>
</section>
<table>

<!-- 	<tr> -->
<!-- 		<th>wishListId</th> -->
<!-- 		<th>memberId</th> -->
<!-- 		<th>productId</th> -->
<!-- 		<th>likeStatus</th> -->
<!-- 		<th>addDate</th> -->
<!-- 		<th>editDate</th> -->
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><%=wishListVO.getWishListId()%></td> --%>
<%-- 		<td><%=wishListVO.getMemberId()%></td> --%>
<%-- 		<td><%=wishListVO.getProductId()%></td> --%>
<%-- 		<td><%=wishListVO.getLikeStatus()%></td> --%>
<%-- 		<td><%=wishListVO.getAddDate()%></td> --%>
<%-- 		<td><%=wishListVO.getEditDate()%></td> --%>
		
<!-- 		<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="wishListId"  value="${wishListVO.wishListId}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="wishListID"  value="${wishListVO.wishListId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		
<!-- 	</tr> -->
</table>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>