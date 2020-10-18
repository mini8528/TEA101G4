<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wishList.model.*"%>
<%@ page import="com.product.model.*"%>
<%
WishListVO wishListVO = (WishListVO) request.getAttribute("wishListVO");
%>
<<jsp:useBean id="productSvc" class="com.product.model.ProductService"></jsp:useBean>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addWishList.jsp</title>

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
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>WishListId新增 - addWishList.jsp</h3></td><td> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <h3>資料新增:</h3> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wishList/WishListServlet" name="form1" enctype="multipart/form-data">

<section class="py-8 py-md-10 list-fullwidth">
  <div class="container">
		<div class="media media-list-view mb-5">
			<a class="media-img" href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${wishListVO.productId}&action=getOne_For_Display_Front">
				<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${wishListVO.productId}" alt="Image" aria-hidden="true">
<!-- 				<div class="media-img-overlay"> -->
<!-- 					<span class="badge badge-warning badge-rounded-circle">$50</span> -->
<!-- 				</div> -->
			</a>

		  <div class="media-body">
		    <h3>
          <a class="text-warning" href="#" class="td-product-name">商品名稱</a>
        </h3>

				<ul class="list-unstyled d-flex text-muted mb-3">
			<tr>
				<td>memberId:</td>
				<td><input type="TEXT" name="memberId" class="form-control" 
			 	value="<%= (wishListVO==null)? "M001" : wishListVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>productId:</td>
				<td><input type="TEXT" name="productId" class="form-control"
			 	value="<%= (wishListVO==null)? "PD0001" : wishListVO.getProductId()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>狀態：</td> -->
<!-- 				<td><select name="likeStatus"> -->
<!-- 						<option value="N" -->
<%-- 							${(wishListVO.likeStatus=="N")? 'selected':'' }>N</option> --%>
<!-- 						<option value="Y" -->
<%-- 							${(wishListVO.likeStatus=="Y")? 'selected':'' }>Y</option> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->
<!-- 					<li class="mr-3"> -->
<!-- 						<i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>Age 2 to 4 Years -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<i class="fa fa-clock-o mr-2" aria-hidden="true"></i>9.00AM-11.00AM -->
<!-- 					</li> -->
				</ul>

		    <p class="text-justify mb-lg-2">They must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a com plete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself.#商品描述</p>

				<div class="">
					<input type="hidden" name="action" value="insert" >
					<input type="submit" value="新增收藏" class="btn btn-sm btn-white text-uppercase mb-1 mr-2 btn-hover-warning">
				<td>
					<a class="btn btn-link text-hover-warning text-underline text-underline pl-0" href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${wishListVO.productId}&action=getOne_For_Display_Front">
            <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More
          </a>
          </td>
       			</div>
	  		</div>
		</div>
	</div>
</section>
</FORM>


<!-- 	<tr> -->
<!-- 		<td>memberId:</td> -->
<!-- 		<td><input type="TEXT" name="memberId" size="45"  -->
<%-- 			 value="<%= (wishListVO==null)? "M001" : wishListVO.getMemberId()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>productId:</td> -->
<!-- 		<td><input type="TEXT" name="productId" size="45" -->
<%-- 			 value="<%= (wishListVO==null)? "PD0001" : wishListVO.getProductId()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 				<td>狀態：</td> -->
<!-- 				<td><select name="likeStatus"> -->
<!-- 						<option value="N" -->
<%-- 							${(wishListVO.likeStatus=="N")? 'selected':'' }>N</option> --%>
<!-- 						<option value="Y" -->
<%-- 							${(wishListVO.likeStatus=="Y")? 'selected':'' }>Y</option> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->


	<jsp:useBean id="wlService" scope="page" 
		class="com.wishList.model.WishListService" />

<!-- <br> -->
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="送出新增"></FORM> -->

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>




</html>