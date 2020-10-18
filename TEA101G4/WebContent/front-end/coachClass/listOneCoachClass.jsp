<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.classDetail.model.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	System.out.println("==================================================");
	System.out.println("====  列出單一課程    list One Coach Class.jsp   ====");
	System.out.println("==================================================");

	MemberVO userVO= (MemberVO) session.getAttribute("userVO");
	if(userVO!=null){
		System.out.println("（one_Coach_Class.jsp）當前會員= "+userVO.getMemberid());
	}else{
		System.out.println("userVO 是空的 ~~~~");
	};
//  	CoachClassVO coachClassVO= (CoachClassVO) request.getAttribute("coachClassID");
// 	System.out.println("coachClassID 1 = "+ request.getParameter("coachClassID"));

	
%>

<html>
<head>
<title>課程資訊 - listOneCoachClass.jsp</title>

</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />

<!-- 	<table> -->
<%-- 	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/cart/cartClass.do" style="margin-bottom: 0px;"> --%>

<!-- 			<tr> -->
<!-- 				<th>教練</th> -->
<!-- 				<th>課程名稱</th> -->
<!-- 				<th>課程內容</th> -->
<!-- 				<th>照片</th> -->
<!-- 				<th>上課時間</th> -->
<!-- 				<th>價格</th> -->
<!-- 				<th>人數</th> -->
<!-- 				<th>地址</th> -->

<!-- 			</tr> -->
			
<%-- 			<jsp:useBean id="coachClassService" scope="page" class="com.coachClass.model.CoachClassService" /> --%>
		
			
<!-- 			<tr> -->
<%-- 				<td>${coachClassService.getMemberName(coachClassVO.coachClassID)}</td> --%>
<%-- 				<td>${coachClassVO.className}</td> --%>
<%-- 				<td>${coachClassVO.classContext}</td> --%>
<!-- 				<td><img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}" --%>
<!-- 					width="100" height="100"></td> -->
<%-- 				<td>${coachClassVO.startTime}</td> --%>
<%-- 				<td>${coachClassVO.price}</td> --%>
<%-- 				<td>${coachClassVO.quantity}</td> --%>
<%-- 				<td>${coachClassVO.address}</td> --%>

<!-- 				<td width="80"><div align="center"><input type="submit" name="Submit" value="放入購物車"> -->
<!-- 					</div></td> -->


<!-- 			</tr> -->
<%-- 			<input type="hidden" name="memberid_Coach" value="${coachClassVO.memberID}"> --%>
<%-- 			<input type="hidden" name="coachClassID" value="${coachClassVO.coachClassID}"> --%>
<%-- 			<input type="hidden" name="className" value="${coachClassVO.className}"> --%>
<%-- 			<input type="hidden" name="price" value="${coachClassVO.price}"> --%>
<%-- 			<input type="hidden" name="memberid" value="${userVO.memberid}"> --%>
<%-- 			<input type="hidden" name="address" value="${coachClassVO.address}"> --%>
<%-- 			<input type="hidden" name="quantity" value="${coachClassVO.quantity}"> --%>
<%-- 			<input type="hidden" name="startTime" value="${coachClassVO.startTime}"> --%>
<!-- 			<input type="hidden" name="action" value="ADD"> -->

<!-- 			</FORM> -->

<!-- 		</table> -->




<!-- ====================================
———	COURSES SECTION
===================================== -->
<section class="py-8 py-md-1">
<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/cart/cartClass.do" style="margin-bottom: 0px;">
  <div class="container">
    <div class="card shadow-none bg-transparent mb-0">
			<div class="position-relative">
				<img class="card-img-top" src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}" alt="Card image">
				<div class="card-img-overlay">
					<span class="badge badge-purple badge-rounded-circle">${coachClassVO.price}</span>
				</div>
			</div>

      <div class="card-body border-top-5 border-purple p-3 p-md-5">
				<h3 class="card-title text-purple mb-4">${coachClassVO.className}  ${coachClassService.getMemberName(coachClassVO.coachClassID)} </h3>

        <p class="font-weight-bold">${coachClassVO.classContext}</p>

<!-- 				<p class="mb-0">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.</p> -->
      </div>
		</div>
		
		<div class="px-3 px-md-8">
			<div class="section-title align-items-baseline mb-4">
				<h3 class="text-purple font-weight-bold mb-0">課程資訊</h3>
			</div>
			
			<div>
				<b class="text-purple font-weight-bold" >教練 : </b><b >${coachClassService.getMemberName(coachClassVO.coachClassID)}</b>
			</div>
			
			<div>
				<b class="text-purple font-weight-bold" >價格 : </b> ${coachClassVO.price}
			</div>
			
			<div>
				<b class="text-purple font-weight-bold" >上課時間 : </b> ${coachClassVO.startTime}
			</div>
			
			<div>
				<b class="text-purple font-weight-bold" >上課人數 : </b> ${coachClassVO.quantity} 人
			</div>
			
			<div>
				<b class="text-purple font-weight-bold" >上課地址 : </b> ${coachClassVO.address}
			</div>
			
			<div class="d-flex justify-content-left mt-7">
				<input class="btn btn-danger text-uppercase" type="submit" name="Submit" value="放入購物車">
			</div>
			
		</div>
		
		
  </div>
  
  </tr>
			<input type="hidden" name="memberid_Coach" value="${coachClassVO.memberID}">
			<input type="hidden" name="coachClassID" value="${coachClassVO.coachClassID}">
			<input type="hidden" name="className" value="${coachClassVO.className}">
			<input type="hidden" name="price" value="${coachClassVO.price}">
			<input type="hidden" name="memberid" value="${userVO.memberid}">
			<input type="hidden" name="address" value="${coachClassVO.address}">
			<input type="hidden" name="quantity" value="${coachClassVO.quantity}">
			<input type="hidden" name="startTime" value="${coachClassVO.startTime}">
			<input type="hidden" name="action" value="ADD">

			</FORM>
</section>













		<jsp:include page="/front-end/coachComment/listOneCoachComment.jsp" flush="true" />

		<jsp:include page="/front-end/coachComment/addCoachComment.jsp" flush="true" />
	

		<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>