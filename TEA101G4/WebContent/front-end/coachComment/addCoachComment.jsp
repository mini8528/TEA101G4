<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");

	CoachCommentService cocService = new CoachCommentService();
	String getM = cocService.getMemberIDFromCoachClassID(request.getParameter("coachClassID"));
	pageContext.setAttribute("getM", getM);

	CoachCommentService ccService = new CoachCommentService();
	List<CoachCommentVO> list = ccService.getOneCoachCommentByMember(getM);
	pageContext.setAttribute("list", list);

	MemberService mSvc = new MemberService();
	pageContext.setAttribute("mSvc", mSvc);

	CoachCommentVO coachCommentVO = (CoachCommentVO) request.getAttribute("coachCommentVO");
%>
<!DOCTYPE html>
<html>
<head>
<title>員工資料新增 - addCoachComment.jsp</title>


</head>
<body bgcolor='white'>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/coachComment/coachComment.do"
		name="form1">
		<jsp:useBean id="coachClassService" scope="page"
			class="com.coachClass.model.CoachClassService" />
		<br>



		<section class="py-8 py-md-1">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-12 order-md-1">

						<div class="bg-light shadow-sm rounded p-3">
							<h3 class="mb-4 text-danger font-weight-bold">留言 :</h3>

							<div class="row">

								<div class="col-12 col-md-6">
									<div class="form-group form-group-icon">
										<i class="fa fa-user"></i>
										<p class="form-control border-purple font-weight-bold">
											${coachClassService.getMemberName(coachClassVO.coachClassID)}
											教練</p>
									</div>
								</div>

								<div class="col-12 col-md-3">
									<div class="form-group form-group-icon">
										<i class="fa fa-chrome"></i> <select name="commStar"
											class="form-control border-purple">
											<option value="5"
												${(coachCommentVO.commStar=="5")? 'selected':'' }>5
												星</option>
											<option value="4"
												${(coachCommentVO.commStar=="4")? 'selected':'' }>4
												星</option>
											<option value="3"
												${(coachCommentVO.commStar=="3")? 'selected':'' }>3
												星</option>
											<option value="2"
												${(coachCommentVO.commStar=="2")? 'selected':'' }>2
												星</option>
											<option value="1"
												${(coachCommentVO.commStar=="1")? 'selected':'' }>1
												星</option>
										</select>
									</div>
								</div>

								<div class="col-12 col-md-3">
									<div class="form-group form-group-icon">
										<i class="fa fa-chrome"></i> <select name="status"
											class="form-control border-purple">
											<option value="Y"
												${(coachCommentVO.status=="Y")? 'selected':'' }>狀態：發文</option>
											<option value="N"
												${(coachCommentVO.status=="N")? 'selected':'' }>狀態：隱藏</option>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col">
									<div class="form-group form-group-icon">
										<i class="fa fa-comments "></i>
										<textarea class="form-control border-info"
											placeholder="Write message" rows="6"></textarea>
									</div>
								</div>
							</div>

							<button class="btn btn-danger text-uppercase">submit</button>
						</div>

					</div>


				</div>
			</div>
		</section>


		<input type="hidden" name="memberID" value="${getM}"> <input
			type="hidden" name="memberID2" value="<%=userVO.getMemberid()%>">

		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出評論">
	</FORM>
</body>


</html>