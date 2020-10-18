<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%

System.out.println("==================================================");
System.out.println("====  列出教練評論    list One Coach Comment .jsp   ====");
System.out.println("==================================================");


// System.out.println("coachClassID 2 = "+ request.getParameter("coachClassID"));

CoachCommentService cocService = new CoachCommentService();
String getM = cocService.getMemberIDFromCoachClassID(request.getParameter("coachClassID"));
System.out.println("獲得 教練 編號 getM = "+ getM);

CoachCommentService ccService = new CoachCommentService();
List<CoachCommentVO> list = ccService.getOneCoachCommentByMember(getM);
pageContext.setAttribute("list",list);
System.out.println("從 教練 編號查出所有留言數 list = "+ list.size());

for(CoachCommentVO b : list){
	b.getMemberID2();
// 	System.out.println("b.getName(); = "+ b.getMemberID2());
}


// List<MemberVO> memberName_list = cocService.getMemberCommentName("M001");
// pageContext.setAttribute("memberName_list",memberName_list);

// System.out.println("memberName_list = "+ memberName_list);
// for (MemberVO a : memberName_list){
// 	a.getName();
// 	System.out.println("a.getName(); = "+ a.getName());
// }

MemberService mSvc = new MemberService();
pageContext.setAttribute("mSvc", mSvc);

%>

<html>
<head>
<title>CoachComment資料 - listOneCoachComment.jsp</title>


</head>
<body bgcolor='white'>

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>評論 - ListOneCoachComment.jsp</h3> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>會員</th> -->
<!-- 		<th>評論內容</th> -->
<!-- 		<th>分數評分</th> -->
<!-- 		<th>發佈日期</th> -->
<!-- 	</tr> -->
	
<%-- 	<c:forEach var="coachCommentVO" items="${list}" > --%>
<!-- 	<tr> -->
		
<%-- 		<td>${mSvc.getOneMember(coachCommentVO.memberID2).name}</td> --%>
<%-- 		<td>${coachCommentVO.commText}</td> --%>
<%-- 		<td>${coachCommentVO.commStar}</td> --%>
<%-- 		<td>${coachCommentVO.addDate}</td> --%>
	
<!-- 	</tr> -->
	
	
<%-- 	</c:forEach> --%>
<!-- </table> -->




<!-- ====================================
———	BLOG DETAILS
===================================== -->
<section class="py-8 py-md-1">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-lg-12 order-md-1">

				<div class="bg-light shadow-sm rounded px-3 pt-3 pb-6 mb-4">
					<h3 class="mb-4 text-danger font-weight-bold">4 Comments</h3>
					<c:forEach var="coachCommentVO" items="${list}" >
					<div class="media py-1">
					  <div class="mr-4">
					  	<img class=" rounded-circle" src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${mSvc.getOneMember(coachCommentVO.memberID2)}
" alt="User Image">
						</div>
					  <div class="media-body">
					    <span class="mb-3 font-weight-bold text-muted d-inline-block">${mSvc.getOneMember(coachCommentVO.memberID2).name}</span> 
					    
					    <p class=" font-weight-bold font-size-13 line-hight-21">${coachCommentVO.commText}</p>
					    <time class="d-block text-muted font-size-13 mb-3">${coachCommentVO.addDate}</time>

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