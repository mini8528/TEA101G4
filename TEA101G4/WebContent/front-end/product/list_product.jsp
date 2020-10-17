<%@page import="com.brand.model.BrandService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.member.model.*"%>

<%-- <jsp:useBean id="list" scope="session" type="java.util.List<ProductVO>" /> --%>
<%
/* ProductService productSvc = new ProductService();
List<ProductVO> list = productSvc.getAll_byStatus("Y");
System.out.println("Product(上架）筆數：" + list.size());
/* pageContext.setAttribute("list", list);
pageContext.setAttribute("list", list); */


/* 	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	System.out.println("Product筆數：" + list.size());
	pageContext.setAttribute("list", list); */

	
	
MemberVO userVO = (MemberVO) session.getAttribute("userVO");
if (userVO != null) {
	pageContext.setAttribute("login", true);
} else {
	pageContext.setAttribute("login", false);
};
/* System.out.println("login_memberid= "+userVO.getMemberid()); */
/* session.setAttribute("userVO", userVO); */
%>
<!DOCTYPE html>
<html>
<head>
<style>
/* .card-product .img-link img */
/* .list_pds1 .list_pds2 img{
max-height:195px;
} */
.interior.container .row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.blogBox .item {
    background: #f4f4f4;
    -webkit-transition: all 0.15s ease-in-out;
    transition: all 0.15s ease-in-out;
    height: 100%;
}
img {
    border: 0;
    -ms-interpolation-mode: bicubic;
}
.blogBox .item .blogTxt {
    padding: 25px;
}
.row {
    margin-right: -15px;
    margin-left: -15px;
}
.interior {
    border-bottom: 1px solid #ccc;
    position: relative;
    z-index: 1;
}
.blogBox .item img {
    width: 100%;
    min-height: 188px;
}
.interior {
    border-bottom: 1px solid #ccc;
    position: relative;
    z-index: 1;
}
.blogBox .item .blogCategory a {
    padding: 5px 10px 2px;
    border: 1px solid #616161;
    color: #616161;
    text-transform: uppercase;
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
    -webkit-transition: all 0.15s ease-in-out;
    transition: all 0.15s ease-in-out;
}
.blogBox .item p {
    padding-bottom: 40px;
}
p {
    margin: 0 0 10px;
    font-family: 'Roboto', sans-serif;
}
p, pre {
    margin: 0 0 1em 0;
}
a, a:link, a:focus, a:active, a:visited {
    outline: 0;
}
a, a:link, a:focus, a:active, a:visited {
    outline: 0;
}
.blogBox .item {
    background: #f4f4f4;
    -webkit-transition: all 0.15s ease-in-out;
    transition: all 0.15s ease-in-out;
    height: 100%;
}
.blogBox .item img {
    width: 100%;
}
.blogBox .item .blogTxt {
    padding: 25px;
}
.blogBox .item h2 {
    margin: 15px 0;
    font-family: 'Roboto', sans-serif;
}
.interior.blog .container .row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.blogBox {
    margin-bottom: 30px;
    box-sizing: border-box;
}
.blogBox .item .blogCategory a:hover {
    background: #616161;
    color: #fff;
    text-decoration: none;
}
.blogBox .item:hover {
    background: #e8e8e8;
    cursor: pointer;
}
#loadMore {
    padding-bottom: 30px;
    padding-top: 30px;
    text-align: center;
    width: 100%;
}
#loadMore a {
    background: #faa635;
    border-radius: 3px;
    color: white;
    display: inline-block;
    padding: 10px 30px;
    transition: all 0.25s ease-out;
    -webkit-font-smoothing: antialiased;
}
#loadMore a:hover {
    background-color: #042a63;
}

@media screen and (min-width: 991px)
.blogBox .featured .blogTxt {
    max-width: 50%;
    width: 100%;
    padding: 50px;
    float: left;
    background: inherit;
    min-height: 378px;
}
@media screen and (min-width: 991px)
.blogBox .featured h2 {
    font-size: 30px;
    font-style: italic;
}
@media screen and (min-width: 1200px)
.blogBox .featured h2 {
    font-size: 42px;
}
@media screen and (min-width: 991px)
.blogBox .featured .blogTxt {
    max-width: 50%;
    width: 100%;
    padding: 50px;
    float: left;
    background: inherit;
    min-height: 378px;
}
@media screen and (min-width: 991px)
.blogBox .featured img {
    max-width: 50%;
    width: 100%;
    float: left;
    min-height: 378px;
}
@media screen and (min-width: 991px)
.blogBox .featured .blogTxt {
    max-width: 50%;
    width: 100%;
    padding: 50px;
    float: left;
    background: inherit;
    min-height: 378px;
}
@media (min-width: 992px)
.col-md-12 {
    width: 100%;
}
@media (min-width: 992px)
.col-md-12 {
    width: 100%;
}
@media (min-width: 992px)
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
@media (min-width: 768px)
.col-sm-6 {
    width: 50%;
}


</style>
</head>
<body>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- 錯誤列表 -->
<c:if test="${not empty errorMsgs}">
<font style="color: red">請修正以下錯誤：</font>
<ul>
	<c:forEach var="message" items="${errorMsgs}">
	<li style="color: green">${message}</li>
</c:forEach>
</ul>
</c:if>
<!-- ================================================================================================================================================
———	PRODUCTS SECTION
==================================================================================================================================================== -->
<section class="py-8 py-md-10">
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
<div class="container">
<div class="row">
	<div class="col-md-8 col-lg-9 order-md-1">
        <div class="row">
<c:forEach var="productVO" items="${list}">
		<div class="col-md-6 col-lg-4 blogBox moreBox" style="display: none;"><!-- col-md-6 col-lg-4 blogBox moreBox --><!-- col-md-8 col-lg-9 order-md-1 -->
			<div class="card card-product border-info card-hover list_pds1">
				<a class="img-link list_pds2"
					href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Display_Front">
					<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" alt="Card image">
				</a>
					<div class="card-body bg-info px-5 py-6">
						<h5 class="mb-1">
							<a href="">${brandSvc.getOneBrand(productVO.brandid).name}</a>
							<br>
							<a href="">${productVO.name}</a>
						</h5>
						<big class="d-block mb-1"><a>$</a>${productVO.price}</big>
						<a href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Display_Front"
							class="btn btn-sm btn-white text-hover-warning text-uppercase d-inline-block">
							<i class="fa fa-eye mr-2" aria-hidden="true"></i> quick shop
						</a>
					</div>
			</div>
		</div>
</c:forEach>
<div id="loadMore" style="">
      <a href="#">Load More</a>
    </div>
</div>
</div>
	<div class="col-md-4 col-lg-3">
        <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
          <div class="card shadow-none bg-transparent">
            <h3 class="card-header bg-dark font-weight-bold rounded-top text-white">Search</h3>
            <div class="card-body border border-top-0 rounded-bottom">
              <div class="input-group border-bottom pb-3 pt-4">
                <input type="text" class="form-control border-0 px-1" name="name" placeholder="Enter Your Search">
                <input type="hidden" name="type" value="searchByPdname"> 
				<input type="hidden" name="action" value="getAll_select"> 
                <span class="input-group-addon" id="basic-addon2">
                  <input class="btn btn-sm btn-warning text-uppercase text-white shadow-sm" type="submit" value="Search">
                </span>
              </div>
            </div>
          </div>
        </FORM>
<!--  -->


   <div class="card shadow-none bg-transparent overflow-hidden">
	<h3 class="card-header bg-dark font-weight-bold rounded-top text-white">品牌</h3>
		  <div class="card-body border border-top-0 rounded-bottom">
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
			<div class="filter">
				<div class="mb-4 pt-3">
					<select class="select2-select w-100 bg-white" name="brandid">
						<c:forEach var="brandVO" items="${brandSvc.all}">
							<option value="${brandVO.brandid}">${brandVO.name}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="type" value="searchByBrandname"> 
					<input type="hidden" name="action" value="getAll_select"> 
					<input class="btn btn-warning text-white text-uppercase" type="submit" value="SEARCH">
				</div>
			</div>
		 </FORM>
<!-- <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
		<b>Price:</b> 
			<input type="hidden" name="priceLowerBound" value="$('#item1 lower-value').text()"> ~
			<input type="hidden" name="priceUpperBound" value="$('#item1 upper-value').text()"> 
			<input type="hidden" name="action" value="searchByPrice"> 
			<input type="submit" value="SEARCH_P">
	</FORM> -->	
	  <%-- <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
            <div class="price-range mt-6" id="price-range">
              <div class="mb-6" id="slider-non-linear-step"></div>
              <div class="price-range-content">
              	<input type="hidden" name="priceLowerBound" value="">
				<input type="hidden" name="priceUpperBound" value=""> 
				<input type="hidden" name="action" value="searchByPrice"> 
                <input class="btn btn-warning text-white text-uppercase" type="submit" value="filter">
                <span class="price-text">Price:</span>
                <span class="price-value" id="lower-value"></span>
                <span class="price-value" id="upper-value"></span>
              </div>
            </div>
         </FORM>   --%>
	  </div>
	</div>

<!-- -->
	<div class="card shadow-none bg-transparent">
		<h4 class="card-header font-weight-bold bg-dark rounded-top text-white">價格</h4>
		<div class="card-body border border-top-0 rounded-bottom">
		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
			<input type="text" name="priceLowerBound" value=""> ~
			<input type="text" name="priceUpperBound" value=""> 
			<input type="hidden" name="action" value="searchByPrice"> 
			<input class="btn btn-warning text-white text-uppercase" type="submit" value="SEARCH">
		</FORM>
		</div>
	</div>
<!--  -->
	<div class="card shadow-none bg-transparent">
		<h4 class="card-header font-weight-bold bg-dark rounded-top text-white">種類</h4>
		<div class="card-body border border-top-0 rounded-bottom">
			<ul class="list-unstyled mb-0">
				<li class="mb-2">
					<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="<%=request.getContextPath()%>/back-end/product/product.do?category=健身食品&type=searchByMuti&action=getAll_select">健身食品</a>
				</li>
				<li class="my-2">
					<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="<%=request.getContextPath()%>/back-end/product/product.do?category=健身用品&type=searchByMuti&action=getAll_select">健身用品</a>
				</li>
			</ul>
		</div>
	</div>
<!--  -->
</div>
</div>
</div>
</section>
<!--  -->
<!--  -->
<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
<script>
$( document ).ready(function () {
	  $(".moreBox").slice(0, 6).show();
	    if ($(".blogBox:hidden").length != 0) {
	      $("#loadMore").show();
	    }   
	    $("#loadMore").on('click', function (e) {
	      e.preventDefault();
	      $(".moreBox:hidden").slice(0, 6).slideDown();
	      if ($(".moreBox:hidden").length == 0) {
	        $("#loadMore").fadeOut('slow');
	      }
	    });
	  });
	  
/*======== 11.PRICE SLIDER RANGER  ========*/
var nonLinearStepSlider = document.getElementById('slider-non-linear-step');
if(nonLinearStepSlider){
  noUiSlider.create(nonLinearStepSlider, {
    connect: true,
    start: [125, 750],
    range: {
        'min': [0],
        'max': [3500]
    }
  });
}

var sliderValue = [
  document.getElementById('lower-value'), // 0
  document.getElementById('upper-value')  // 1
];

// Display the slider value and how far the handle moved
// from the left edge of the slider.
var priceRange = document.getElementById('price-range');
if (priceRange) {
  nonLinearStepSlider.noUiSlider.on('update', function(values, handle) {
    sliderValue[handle].innerHTML = '$' + Math.floor(values[handle]);
  });
}
</script>
</body>
</html>