<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO userVO= (MemberVO) session.getAttribute("userVO");
if(userVO!=null){System.out.println("（addOrderMaster.jsp）當前會員= "+userVO.getMemberid());};
pageContext.setAttribute("userVO", userVO);

OrdermasterVO ordermasterVO = (OrdermasterVO) request.getAttribute("ordermasterVO");
System.out.println("ordermasterVO=>"+ordermasterVO);

long long1 = System.currentTimeMillis();
java.sql.Date ds1 = new java.sql.Date(long1);

long long2 = long1+ 3*24*60*60*1000L;
java.sql.Date ds2 = new java.sql.Date(long2);

List<CartVO> buylist = (List<CartVO>) session.getAttribute("shoppingcart"); 
List<String> detailidAll = new ArrayList<String>();
List<String> quantityAll = new ArrayList<String>();

for(CartVO aa:buylist){
	System.out.println("CartVO aa="+aa.getSpecid()+";"+aa.getQuantity());
	String specid = aa.getSpecid();
	detailidAll.add(specid);
	
	Integer quantity = aa.getQuantity();
	quantityAll.add(quantity.toString());
	pageContext.setAttribute("aa", aa);
	pageContext.setAttribute("detailidAll", detailidAll);
	pageContext.setAttribute("quantityAll", quantityAll);
}
for(String ss:detailidAll){
	/* ss.toString(); */
	pageContext.setAttribute("ss", ss);
}
for(String tt:quantityAll){
	/* tt.toString(); */
	pageContext.setAttribute("tt", tt);
}

%>
<!DOCTYPE html>
<html>
<!-- <head>
</head> -->
<body onload="ShowTime()">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- ============================================================================================================
———	CHECK-OUT SECTION
============================================================================================================= -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do" name="form1">
<section class="py-7 py-md-10">
  <div class="container">
		<div class="row">
			<div class="col-md-7 col-lg-8 order-1 order-md-0">
          <div class="card bg-transparent shadow-none">
						<div class="card-header card-header-lg bg-danger text-white p-6 rounded-top">
              <h4 class="font-weight-bold mb-0">填寫收件資料</h4>
          	</div>

            <div class="card-body border border-top-0 rounded-bottom-sm p-7">
              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">收件人姓名</label>
                  <input type="text" class="form-control border-info rounded-sm" id="first-name" name="receiver" value="<%=(ordermasterVO == null) ? userVO.getName() : ordermasterVO.getReceiver()%>" />
<%--                   <input type="text" class="form-control border-info rounded-sm" id="first-name" name="receiver" value="<%=(ordermasterVO == null) ? "葉大雄" : ordermasterVO.getReceiver()%>" /> --%>
                </div>
                <div class="form-group form-group-icon col-lg-6">
                  <label for="last-name">電話</label>
                  <input type="text" class="form-control border-info rounded-sm" id="last-name" name="tel" value="<%=(ordermasterVO == null) ? userVO.getPhone() : ordermasterVO.getTel()%>" />
<%--                   <input type="text" class="form-control border-info rounded-sm" id="last-name" name="tel" value="<%=(ordermasterVO == null) ? "0988-888-888" : ordermasterVO.getTel()%>" /> --%>
                </div>
              </div>
              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">地址</label>
                  <input type="text" class="form-control border-info rounded-sm" id="first-name" name="address" value="<%=(ordermasterVO == null) ? userVO.getAddress() : ordermasterVO.getAddress()%>" />
<%--                   <input type="text" class="form-control border-info rounded-sm" id="first-name" name="address" value="<%=(ordermasterVO == null) ? "台北市中正區重慶南路一段122號" : ordermasterVO.getAddress()%>" /> --%>
                </div>
              </div>
            </div>
          </div>

		<div class="pull-right mt-4">
			<input class="btn btn-danger text-uppercase" type="submit" value="確定送出">
            <!-- <a class="btn btn-danger text-white text-uppercase">next</a> -->
        </div>
        
        <div class="alert alert-info alert-dismissible fade show col-lg-6" role="alert">
           <strong>訂單下單日：</strong><%=ds1 %>
           <br>
           <strong>付款截止日：</strong><%=ds2 %>
<%--            <strong>&nbsp;&nbsp;&nbsp;付款截止日：</strong><%=ds2 %> --%>
         </div>
         
         <div class="alert alert-info alert-dismissible fade show col-lg-6" role="alert" id="now"></div>
         
         <tr>
			<!-- <td>超商付款代碼：</td> -->
			<td>
			<input type="hidden" name="paycode" size="45" value="<%=(ordermasterVO == null) ? "code9999" : ordermasterVO.getPaycode()%>" />
			</td>
		</tr>
		<tr>
			<!-- <td>付款狀態：</td> -->
			<td>
			<input type="hidden" name="paystatus" size="45" value="<%=(ordermasterVO == null) ? "Y" : ordermasterVO.getPaystatus()%>" />
			</td>
			<%-- <td>
			<select name="paystatus">
			<option value="N" ${(ordermasterVO.paystatus=="N")? 'selected':'' }>N</option>
			<option value="Y" ${(ordermasterVO.paystatus=="Y")? 'selected':'' }>Y</option>
			</select> 
			</td> --%>
		</tr>
         <tr>
			<!-- <td>＊會員編號：</td> -->
			<td>
			<input type=hidden name="memberid" size="45" value="<%=userVO.getMemberid()%>" />
			</td>
		</tr>
		<tr>
			<td>付款方式：</td>
			<td>
			<input type="radio" name="payment" value="信用卡" checked>
	 		 <label for="信用卡" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>信用卡</label>
	 		 <input type="radio" name="payment" value="超商付款">
	 		 <label for="超商付款" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>超商付款</label>
			</td>
		</tr>
         <input type="hidden" name="action" value="customer_insert">
			<!-- ------- -->
		<c:forEach var="ss" items="${detailidAll}">
		 		<input type="hidden" name="specid" value="${ss}">
		</c:forEach>
		<c:forEach var="tt" items="${quantityAll}">
				<input type="hidden" name="quantity" value="${tt}">
		 </c:forEach>
		 	<!-- ------- -->
		<!-- </FORM> -->
		</div>

		<div class="col-md-5 col-lg-4">
        <div class="card">
          <div class="card-header card-header-lg bg-danger text-white p-6 rounded-top">
            <h4 class="font-weight-medium font-size-24 mb-0">Note</h4>
          </div>
          <div class="card-body pt-7">
            <ul class="list-unstyled list-item-lg mb-0">
              <li class="d-flex align-items-center">
                <i class="fa fa-taxi mr-2" aria-hidden="true"></i>
                免運費
              </li>
            </ul>
        
            <div class="border-top-0 pt-2">
              <ul class="list-unstyled mb-1">
                <li class="d-flex justify-content-between align-items-center p-0 border-0">
                  <a href="" class="">
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
			</div>
		</div>
<!-- 信用卡 -->
	<div>
	<div class="col-md-7 col-lg-8 order-1 order-md-0">
          <div class="card shadow-none">
						<div class="card-header card-header-lg bg-danger text-white p-6 rounded-top">
            	<h4 class="font-weight-bold mb-0">Payment Mathod</h4>
          	</div>

            <div class="card-body border border-top-0 rounded-bottom-sm p-7">
              <div class="mb-5">
                <div class="row align-items-center">
                  <div class="col-6 col-md-7 col-lg-4 col-xl-5">
                    <h3 class="font-weight-bold mb-0">
                      Card
                      <span class="text-danger ml-2">
                        <i class="fa fa-credit-card" aria-hidden="true"></i>
                      </span>
                    </h3>
                  </div>
                
                  <div class="col-6 col-lg-5 col-xl-3 d-none d-lg-block"></div>
                
                </div>
              </div>
              
              <div class="row">
                <div class="col-12">
                  <label for="first-name">Card Number</label>
                  <div class="form-group form-group-icon">
                    <i class="fa fa-lock"></i>
                    <input type="text" class="form-control border-warning" placeholder="1234 5678 9012 3456" required="">
                  </div>
                </div>                
              </div>

              <div class="row">
                <div class="col-lg-4">
                  <label for="first-name">Expiry Date</label>
                  <div class="form-group form-group-icon">
                    <input type="text" class="form-control border-pink" placeholder="MM/YY" required="">
                  </div>
                </div>

                <div class="col-lg-4">
                  <label for="first-name">Full Name</label>
                  <div class="form-group form-group-icon">
                    <input type="text" class="form-control border-purple" placeholder="John Doe" required="">
                  </div>
                </div>

                <div class="col-lg-4">
                  <label for="first-name">CV Code</label>
                  <div class="form-group form-group-icon">
                    <input type="text" class="form-control border-info" placeholder="123" required="">
                  </div>
                </div>
                <!--  -->
                <div class="checkbox col-12">
		            <label>
		              <input id="autowrite" type="checkbox">AUTO TYPE<a class="text-danger" ></a>
		            </label>
		        </div>
		        <!--  -->
              </div>
            </div>
          </div>

          <ul class="list-unstyled d-flex justify-content-between mt-4">
            <!-- <li>
              <a href="product-checkout-step-1.html" class="btn btn-light text-uppercase">Back</a>
			</li> -->

            <!-- <li>
              <a href="product-checkout-step-3.html" class="btn btn-danger text-white text-uppercase px-10">Pay</a>
			</li> -->
            <li>
              <input id="load123" class="btn btn-danger text-uppercase" type="submit" value="確定送出">
			</li>
          </ul>
	</div>
</div>	
</FORM>
<!--  -->
  </div>
</section>
</body>
<!-- ============================================================================================================
================================================================================================================= -->
<%
Timestamp orderdate = null;
try {
	orderdate = ordermasterVO.getOrderdate();
} catch (Exception e) {
	orderdate = new Timestamp(System.currentTimeMillis());
}
%>
<script>
   	function ShowTime(){
 		　document.getElementById('now').innerHTML = new Date();
 		　setTimeout('ShowTime()',1000);
 		} 
/* 	var Today=new Date();
		document.write("下單日期： " + Today.getFullYear()+ " 年 " + (Today.getMonth()+1) + " 月 " + Today.getDate() + " 日\n");
	
	var Today2=new Date();
		document.write("/"+"付款期限： " + Today2.getFullYear()+ " 年 " + (Today2.getMonth()+1) + " 月 " +(Today2.getDate()+3)+ " 日前"); */

	$(document).ready(function(){
		  $("#load123").on('click',function(){
			alert("付款成功!");
/* 		  $("#load123").load(function(){ */
			/* setTimeout(() => {  alert("付款成功"); }, 5000); */
			/* a(); */
		  	console.log("成功！！");
		  	
		  	/* sleep(5000); */
		    /* alert("<img src=\"http://localhost:8081/TEA101G4/front-end/ordermaster/loading.gif\" />"); */
		  /* }); */
		});
	});
		
	$(document).ready(function(){
	  $("#autowrite").on('click',function(){
		  /* alert("autowrite!"); */
		  document.getElementById("div").
		  text1.value = "123";
		});
	});

</script>
</html>

