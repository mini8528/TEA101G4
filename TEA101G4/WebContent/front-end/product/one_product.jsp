<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.pro.model.*"%>
<%
MemberVO userVO = (MemberVO) session.getAttribute("userVO");
if (userVO != null) {
System.out.println("（one_product.jsp）當前會員= " + userVO.getMemberid());
};
ProService proSvc = new ProService();
List<ProVO> listComm = proSvc.getAllStatusYById(request.getParameter("productid"));
pageContext.setAttribute("listComm",listComm);

MemberService memSvc = new MemberService();
pageContext.setAttribute("memSvc", memSvc);

%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/06d8daefdc.js" crossorigin="anonymous"></script>
<style>
.qty .count {
    color: #000;
    display: inline-block;
    vertical-align: top;
    font-size: 25px;
    font-weight: 700;
    line-height: 30px;
    padding: 0 2px
    ;min-width: 35px;
    text-align: center;
}
.qty .plus {
    cursor: pointer;
    display: inline-block;
    vertical-align: top;
    color: white;
    width: 30px;
    height: 30px;
    font: 30px/1 Arial,sans-serif;
    text-align: center;
    border-radius: 50%;
    }
.qty .minus {
    cursor: pointer;
    display: inline-block;
    vertical-align: top;
    color: white;
    width: 30px;
    height: 30px;
    font: 30px/1 Arial,sans-serif;
    text-align: center;
    border-radius: 50%;
    background-clip: padding-box;
}
/* div {
    text-align: center;
} */
.minus:hover{
    background-color: #717fe0 !important;
}
.plus:hover{
    background-color: #717fe0 !important;
}
/*Prevent text selection*/
/* span{
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
}
input{  
    border: 0;
    width: 2%;
}
nput::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
input:disabled{
    background-color:white;
}
          */
 /* heart */         
.fa-heart-o {
  color: red;
  cursor: pointer;
}

.fa-heart {
  color: red;
  cursor: pointer;
}
/* heart-end */
</style>
</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- ============================================================================================================
———	SINGLE PRODUCT
================================================================================================================= -->
<table>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/cart/cart.do" style="margin-bottom: 0px;">
<section class="pt-5 pb-7 pt-md-10 pb-md-9">
				<div class="container">
					<div class="row">
						<div class="col-md-7 col-lg-6">
							<div class="row">
								<div class="col-4 col-lg-3 px-lg-2 pr-2">
									<div class="nav flex-column nav-pills nav-tab" id="v-pills-tab"
										role="tablist" aria-orientation="vertical">
										<a class="nav-link active p-0" id="v-pills-home-tab"
											data-toggle="pill" href="#v-pills-home" role="tab"
											aria-controls="v-pills-home" aria-selected="true"> <img
											class="img-fluid px-1 py-2 py-md-3 d-block m-auto"
											src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}"
											alt="thumb01.jpg">
										</a> <a class="nav-link p-0" id="v-pills-profile-tab"
											data-toggle="pill" href="#v-pills-profile" role="tab"
											aria-controls="v-pills-profile" aria-selected="false"> <img
											class="img-fluid px-1 py-2 py-md-3 d-block m-auto"
											src="<%=request.getContextPath()%>/back-end/product/productshow2.do?productid=${productVO.productid}"
											alt="thumb01.jpg">
										</a> <a class="nav-link p-0" id="v-pills-messages-tab"
											data-toggle="pill" href="#v-pills-messages" role="tab"
											aria-controls="v-pills-messages" aria-selected="false"> <img
											class="img-fluid px-1 py-2 py-md-3 d-block m-auto"
											src="<%=request.getContextPath()%>/back-end/product/productshow3.do?productid=${productVO.productid}"
											alt="thumb01.jpg">
										</a>
									</div>
								</div>

								<div class="col-8 col-lg-9 pl-0">
									<div class="tab-content border-4 rounded-lg mb-7 mb-md-0"
										id="v-pills-tabContent">
										<div class="tab-pane fade show active" id="v-pills-home"
											role="tabpanel" aria-labelledby="v-pills-home-tab">
											<img class="img-fluid py-9"
												src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}"
												alt="image-1.png">
										</div>

										<div class="tab-pane fade" id="v-pills-profile"
											role="tabpanel" aria-labelledby="v-pills-profile-tab">
											<img class="img-fluid py-9"
												src="<%=request.getContextPath()%>/back-end/product/productshow2.do?productid=${productVO.productid}"
												alt="image-2.png">
										</div>

										<div class="tab-pane fade" id="v-pills-messages"
											role="tabpanel" aria-labelledby="v-pills-messages-tab">
											<img class="img-fluid py-9"
												src="<%=request.getContextPath()%>/back-end/product/productshow3.do?productid=${productVO.productid}"
												alt="image-3.png">
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-5 col-lg-6">
							<div class="product-single">
								<h1>${productVO.name}</h1>
								<%-- <jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" /> --%>
								<!-- brandid=${productVO.brandid}&type=searchByBrandname&action=getAll_select-->
								<a class="text-purple d-block font-weight-bold font-size-18 mb-2" href="<%=request.getContextPath()%>/back-end/product/product.do?brandid=${productVO.brandid}&type=searchByBrandname&action=getAll_select">${brandSvc.getOneBrand(productVO.brandid).name}</a>
								<input type="hidden" name="brandname" value="${brandSvc.getOneBrand(productVO.brandid).name}">
								<span class="pricing font-size-55">$${productVO.price} <del>$${productVO.price+200}</del></span>

								<p class="font-size-15 mb-7">${productVO.intro}</p>

								<div class="col-12 col-md-6 col-lg-4">
									<div class="dropdown mb-8">
										<!-- ---------------------------------- -->
										<%-- <jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" /> --%>
										<select class="btn btn-success dropdown-toggle" name="specid">
											<c:forEach var="specVO" items="${specSvc.all}">
												<c:if test="${productVO.productid==specVO.productid}">
													<option value="${specVO.specid}">${specVO.specific}
												</c:if>
											</c:forEach>
										</select>
										<%-- ${specSvc.getSome(productVO.productid).specific} --%>
										<!-- ---------------------------------- -->
									</div>
								</div>
									<div class="add-cart mb-5">
										<!-- <div align="center">
											<input type="text" name="quantity" size="3" value=1>
										</div> -->
										<div class="qty mt-5">
											<span class="minus bg-dark">-</span>
												<input type="text" class="count" name="quantity" size="3" value="1" readonly>
											<span class="plus bg-dark">+</span>
										</div>
									<p>&emsp;
									<p>&emsp;
									<p>&emsp;
									<input class="btn btn-danger text-uppercase" type="submit" name="Submit" value="放入購物車"> 
									<!-- <p>Click on the heart to love or un-love this post.</p> -->
									<span id = heart><i class="fa fa-heart-o" aria-hidden="true" ></i> </span>
									<!--  -->
									<input type="hidden" name="productid" value="${productVO.productid}"> 
									<input type="hidden" name="productname" value="${productVO.name}">
									<input type="hidden" name="price" value="${productVO.price}">
									<input type="hidden" name="memberid" value="${userVO.memberid}">
									<input type="hidden" name="action" value="ADD">
								</div>
<!-- ============================================================================================================
================================================================================================================= -->
								<div class="mb-7">
									<span class="text-dark">類別:</span>
									<a class="text-danger" href="product-category.html">${productVO.category}</a> 
								</div>

								<div class="">
									<ul
										class="list-inline d-flex align-items-center mb-0 flex-wrap">
										<li class="mr-2">
											<h3 class="d-inline-block text-dark font-weight-medium">Share:</h3>
										</li>

										<li class="mr-1"><a
											class="medium-icon-rounded-circle bg-warning"
											href="javascript:void(0)"> <i
												class="fa fa-facebook text-white" aria-hidden="true"></i>
										</a></li>

										<li class="mr-1"><a
											class="medium-icon-rounded-circle bg-danger"
											href="javascript:void(0)"> <i
												class="fa fa-twitter text-white" aria-hidden="true"></i>
										</a></li>

										<li class="mr-1"><a
											class="medium-icon-rounded-circle bg-success"
											href="javascript:void(0)"> <i
												class="fa fa-google-plus text-white" aria-hidden="true"></i>
										</a></li>

										<li class="mr-1"><a
											class="medium-icon-rounded-circle bg-info"
											href="javascript:void(0)"> <i
												class="fa fa-pinterest-p text-white" aria-hidden="true"></i>
										</a></li>

										<li class=""><a
											class="medium-icon-rounded-circle bg-purple"
											href="javascript:void(0)"> <i
												class="fa fa-vimeo text-white" aria-hidden="true"></i>
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
	</FORM>
	<!-- ====================================
———	TAB SECTION
===================================== -->
<section class="pt-4 pb-8">
  <div class="container">
    <ul class="nav nav-pills mb-5" id="pills-tab" role="tablist">
      <li class="nav-item">
        <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">商品介紹</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">評論</a>
      </li>
    </ul>

    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show active px-5 py-6 bg-light rounded shadow-sm" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
        <h3 class="text-danger font-weight-medium mb-3">商品描述${productVO.intro}</h3>
        <p class="font-size-15 mb-3">-內容-${productVO.intro}</p>
      </div>

      <div class="tab-pane fade px-5 py-7 bg-light rounded shadow-sm" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
 <!-- for each -->
 <c:forEach var="proVO" items="${listComm}">
        <div class="media flex-column flex-lg-row border-bottom mb-4 pb-4">
          <div class="image mb-3 mb-lg-0 mr-lg-4">
            <img class="rounded-circle" src="<%=request.getContextPath()%>/images/usagi.png" alt="User Image" width="70px">
            <!-- <img class="rounded-circle" src="" alt="User Image"> -->
          </div>
          <div class="media-body">
            <div class="d-flex align-items-center justify-content-between mb-2"><!-- memSvc.getOneMember(proVO.memberid).name -->
              <span class="media-heading text-danger font-dosis font-weight-medium mb-0 text-uppercase">${memSvc.getOneMember(proVO.memberid).name}</span>
              <span class="product-rating font-size-18">
                <small><i class="fa fa-star text-warning" aria-hidden="true"></i></small>
                <small><i class="fa fa-star text-warning" aria-hidden="true"></i></small>
                <small><i class="fa fa-star text-warning" aria-hidden="true"></i></small>
                <small><i class="fa fa-star text-warning" aria-hidden="true"></i></small>
                <small><i class="fa fa-star-half-full text-warning" aria-hidden="true"></i></small>
              </span>
            </div>
            <p class="font-size-15 mb-5">${proVO.commtext}</p>
            <%-- <span class="text-muted opacity-75 mr-1">${proVO.commtext}</span> --%>
            <span class="text-muted opacity-75">${proVO.adddate}</span>
          </div>
        </div>
</c:forEach>
<!--  -->
      </div>
    </div>
  </div>
</section>
	
</table>
<script>
$(document).ready(function() {
// 	$('.count').prop('disabled', true);
	$(document).on('click', '.plus', function() {
		var value = parseInt($('.count').val()) + 1;
		$('.count').val(value);
		$('.count').attr("value", value);
	});
	$(document).on('click', '.minus', function() {
		var value = parseInt($('.count').val()) - 1;
		$('.count').val(value);
		$('.count').attr("value", value);
		if ($('.count').val() == 0) {
			$('.count').val(1);
			$('.count').attr("value", value);
		}
	});
});

//It checks to see if the span id #heart has "liked" class, if not it run the else statement and adds the "liked" class, on a 2nd click it see that it has the "liked" class so it replaces the ihherHTML and removes class, on 3rd click it runs the else statement again cause there is no "liked" class(remomved on 2nd click).

$(document).ready(function(){
  $("#heart").click(function(){
    if($("#heart").hasClass("liked")){
      $("#heart").html('<i class="fa fa-heart-o" aria-hidden="true"></i>');
      $("#heart").removeClass("liked");
    }else{
      $("#heart").html('<i class="fa fa-heart" aria-hidden="true"></i>');
      $("#heart").addClass("liked");
    }
  });
});
</script>
<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>