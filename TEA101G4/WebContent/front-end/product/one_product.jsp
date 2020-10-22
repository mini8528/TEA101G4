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
									<!--  -->
									<!-- <p>Click on the heart to love or un-love this post.</p> -->
									<span id = heart>
									<!-- <svg width="2.5em" height="2.5em" aria-hidden="true" focusable="false" data-prefix="far" data-icon="heart" class="svg-inline--fa fa-heart fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path fill="currentColor" d="M458.4 64.3C400.6 15.7 311.3 23 256 79.3 200.7 23 111.4 15.6 53.6 64.3-21.6 127.6-10.6 230.8 43 285.5l175.4 178.7c10 10.2 23.4 15.9 37.6 15.9 14.3 0 27.6-5.6 37.6-15.8L469 285.6c53.5-54.7 64.7-157.9-10.6-221.3zm-23.6 187.5L259.4 430.5c-2.4 2.4-4.4 2.4-6.8 0L77.2 251.8c-36.5-37.2-43.9-107.6 7.3-150.7 38.9-32.7 98.9-27.8 136.5 10.5l35 35.7 35-35.7c37.8-38.5 97.8-43.2 136.5-10.6 51.1 43.1 43.5 113.9 7.3 150.8z"></path></svg> -->
									<i class="fa fa-heart-o" aria-hidden="true" ></i>
									</span>
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
        <h3 class="font-weight-medium mb-3">商品描述${productVO.intro}</h3>
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
<%-- <script>
	$(function(){
		likeChange();
		saveChange()
	});
	
	$("textarea#usermessage").on("keydown", function(event) {
		if (event.which == 13) {
			$("input#msgsubmit").click();
		}
		;
	});
	function likeChange(){
		$('#showLike').on('click', function(){
			var status = $('#heartlike').attr('name');
			console.log(status);
			$.ajax({
			     url: "<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet",
			     type: "get",
			     data: { 
			       action: 'clickheart', 
			       blogno: '${blogVO.blogno}',
			       memberid:'${userVO.memberid}',
			       status: status
			      },
			     dataType: 'json',
			     success: function(res){
			    	console.log(res.status);
			    	if('Y' == res.status){
			    		var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart-fill" id="heartlike" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/></svg>';
			    		$('#showLike').html( htmlString );
					}else{
						var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart" id="heartlike" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M8 6.236l.894-1.789c.222-.443.607-1.08 1.152-1.595C10.582 2.345 11.224 2 12 2c1.676 0 3 1.326 3 2.92 0 1.211-.554 2.066-1.868 3.37-.337.334-.721.695-1.146 1.093C10.878 10.423 9.5 11.717 8 13.447c-1.5-1.73-2.878-3.024-3.986-4.064-.425-.398-.81-.76-1.146-1.093C1.554 6.986 1 6.131 1 4.92 1 3.326 2.324 2 4 2c.776 0 1.418.345 1.954.852.545.515.93 1.152 1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/></svg>';
			    		$('#showLike').html( htmlString );
					}
			    	$('#showLikeCount').text( res.likescount );
			     },
			     error: function(res){
			       alert("出現問題!");
			     }
			 });
		});
	}
	
	function saveChange(){
		$('#showSave').on('click', function(){
			var status = $('#collection').attr('name');
			console.log(status);
			$.ajax({
			     url: "<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet",
			     type: "get",
			     data: { 
			       action: 'clickCollection', 
			       blogno: '${blogVO.blogno}',
			       memberid:'${userVO.memberid}',
			       status: status
			      },
			     dataType: 'json',
			     success: function(res){
			    	console.log(res.status);
			    	if('Y' == res.status){
			    		var htmlString = '<svg id="showSave" width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart-fill" id="collection" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4 0a2 2 0 0 0-2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4zm4 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/></svg>';
			    		$('#showSave').html( htmlString );
					}else{
						var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart" id="collection" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/><path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>';
			    		$('#showSave').html( htmlString );
					}
			     },
			     error: function(res){
			       alert("出現問題!");
			     }
			 });
		});
	}
</script> --%>
<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>