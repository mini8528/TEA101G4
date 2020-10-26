<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.classDetail.model.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");

	CoachClassService cocService = new CoachClassService();
	pageContext.setAttribute("cocService", cocService);
	
	CoachCommentService ccService = new CoachCommentService();
	pageContext.setAttribute("ccService", ccService);
	
%>
<!DOCTYPE html>
<html>
<head>
<title>課程資訊 - listOneCoachClass.jsp</title>

</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />


	<section class="py-8 py-md-1">
		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/cart/cartClass.do"
			style="margin-bottom: 0px;">
			<div class="container">

				<div class="card shadow-none bg-transparent mb-0">
					<div class="position-relative">
						<img class="card-img-top"
							src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
							alt="Card image">
						<div class="card-img-overlay">
							<span class="badge badge-purple badge-rounded-circle">${coachClassVO.price}</span>
						</div>
					</div>


				</div>
				<div class="row">
				
					<div class="card-body border-top-5 border-purple p-3 p-md-5">
						<span class="text-purple font-weight-bold mb-5"><font size="60">${ccService.getCoachStarAVG(coachClassVO.memberID) }</font>分</span>

					</div>
				
					<div class="card-body border-top-5 border-purple p-3 p-md-5">
						<h3 class="text-purple font-weight-bold mb-5">${coachClassVO.className}　${cocService.getMemberName(coachClassVO.coachClassID)}</h3>

						<p class="font-weight-bold">${coachClassVO.classContext}</p>

					</div>
					<div class="card-body border-top-5 border-purple p-3 p-md-5">

						<div class="section-title align-items-baseline mb-4">
							<h3 class="text-purple font-weight-bold mb-0">課程資訊</h3>
						</div>

						<div>
							<b class="text-purple font-weight-bold">教練 : </b><b>${cocService.getMemberName(coachClassVO.coachClassID)}</b>
						</div>

						<div>
							<b class="text-purple font-weight-bold">價格 : </b>
							${coachClassVO.price}
						</div>

						<div>
							<b class="text-purple font-weight-bold">上課時間 : </b>
							<fmt:formatDate value="${coachClassVO.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>

						<div>
							<b class="text-purple font-weight-bold">上課人數 : </b>
							${coachClassVO.quantity} 人
						</div>

						<div>
							<b class="text-purple font-weight-bold">上課地址 : </b>
							${coachClassVO.address}
						</div>

						<div class="d-flex justify-content-left mt-7">
							<input class="btn btn-danger text-uppercase" type="submit"
								name="Submit" value="放入購物車">
						</div>

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
			<input type="hidden" name="coachName" value="${cocService.getMemberName(coachClassVO.coachClassID)}"> 
			<input type="hidden" name="action" value="ADD">

		</FORM>
	</section>



	<jsp:include page="/front-end/coachComment/listOneCoachComment.jsp"	flush="true" >
	  <jsp:param name="coachClassID" value="${coachClassVO.coachClassID}" />
	</jsp:include>

	<jsp:include page="/front-end/coachComment/addCoachComment.jsp"
		flush="true" />


	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>