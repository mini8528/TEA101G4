<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CoachCommentService cocService = new CoachCommentService();
 	String getM = cocService.getMemberIDFromCoachClassID(request.getParameter("coachClassID"));
// 	String getM = cocService.getMemberIDFromCoachClassID("COC00001");
	CoachCommentService ccService = new CoachCommentService();
	List<CoachCommentVO> list = ccService.getOneCoachCommentByMember(getM);
	pageContext.setAttribute("list", list);

	int list_number = list.size();
	pageContext.setAttribute("list_number", list.size());

	MemberService mSvc = new MemberService();
	pageContext.setAttribute("mSvc", mSvc);
%>
<!DOCTYPE html>
<html>
<head>
<title>CoachComment資料 - listOneCoachComment.jsp</title>


</head>
<body bgcolor='white'>



	<section class="py-8 py-md-1">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-lg-12 order-md-1">

					<div class="bg-light shadow-sm rounded px-3 pt-3 pb-6 mb-4">
						<h1 class="mb-4  font-weight-bold">共
							${list_number} 則評論</h1>
						<c:forEach var="coachCommentVO" items="${list}">
							<div class="media py-1">
								<div class="mr-4">
									<img class=" rounded-circle" width="40px" height="40px"
										src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${coachCommentVO.memberID2}"
										alt="User Image">
								</div>
								<div class="media-body">
									<span class="mb-3 font-weight-bold text-muted d-inline-block">${mSvc.getOneMember(coachCommentVO.memberID2).name}</span>

									<p class=" font-weight-bold font-size-13 line-hight-21">${coachCommentVO.commText}</p>
									<time class="d-block text-muted font-size-13 mb-3"><fmt:formatDate value="${coachCommentVO.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></time>

								</div>
							</div>
						</c:forEach>
						<hr>


					</div>


				</div>


			</div>
		</div>
	</section>

</body>
</html>