<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coachComment.model.*"%>

<%
	CoachClassService cocService = new CoachClassService();
	List<CoachClassVO> list = cocService.getAll();
	pageContext.setAttribute("list", list);

	MemberVO userVO = (MemberVO) session.getAttribute("userVO");

	pageContext.setAttribute("userVO", userVO);
	
	CoachCommentService ccService = new CoachCommentService();
	pageContext.setAttribute("ccService", ccService);
%>

<!DOCTYPE html>
<html>
<head>
<title>所有CoachClass資料 - listAllCoachClass.jsp</title>


</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />




	<%@ include file="page1.file"%>


	<section class="py-8 py-md-10">
		<div class="container">
			<div class="row">

				<c:forEach var="coachClassVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<jsp:useBean id="cocService1" scope="page"
						class="com.coachClass.model.CoachClassService" />

					<!-- Card -->
					<div class="col-sm-6 col-lg-3 col-xs-12">
						<div class="card">
							<a 
								class="position-relative"> <img class="card-img-top"
								src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
								alt="Card image">
								<div class="card-img-overlay">
									<span class="badge badge-purple badge-rounded-circle">${coachClassVO.price}</span>
								</div>
							</a>
							<div
								class="card-body border-top-5 px-3 rounded-bottom border-purple">
								<h3 class="card-title">
									<span class="text-purple text-capitalize d-block text-truncate"
										>${coachClassVO.className}</span>
								</h3>
								<h3 class="card-title">
									<span class="text-purple text-capitalize d-block text-truncate fa fa-user">
										${cocService1.getMemberName(coachClassVO.coachClassID)}
									教練</span>
								</h3>	
								
								<h3 class="text-purple font-weight-bold size=12 mb-5">${ccService.getCoachStarAVG(coachClassVO.memberID) } 分</h3>
						
								<ul class="list-unstyled text-muted">
									<li><i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>上課
										${coachClassVO.quantity} 人</li>
									<li><i class="fa fa-clock-o mr-2" aria-hidden="true"></i>
									<fmt:formatDate value="${coachClassVO.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul>
								<p>${coachClassVO.classContext}</p>


								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger text-uppercase" type="submit" value="詳細課程"> <input type="hidden"
										name="coachClassID" value="${coachClassVO.coachClassID}">
									<input type="hidden" name="action"
										value="getOne_For_Display_front">
								</FORM>
							</div>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
		</section>
		</table>


		<%@ include file="page2.file"%>


		<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>