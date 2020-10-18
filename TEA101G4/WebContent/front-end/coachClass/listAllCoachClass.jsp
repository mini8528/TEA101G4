<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.member.model.*"%>

<%

System.out.println("==================================================");
System.out.println("======  列出所有課程    listAllCoachClass   ================");
System.out.println("==================================================");

	CoachClassService cocService = new CoachClassService();
	List<CoachClassVO> list = cocService.getAll();
	pageContext.setAttribute("list", list);
	
	MemberVO userVO= (MemberVO) session.getAttribute("userVO");
	if(userVO!=null)
		{System.out.println("（one_product.jsp）當前會員= "+userVO.getMemberid());
	}else{
		System.out.println("userVO 是空的 ~~~~");
	};
	
	pageContext.setAttribute("userVO", userVO);
%>

<!DOCTYPE html>
<html>
<head>
<title>所有CoachClass資料 - listAllCoachClass.jsp</title>


</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />


<%-- 	<%-- 錯誤表列 --%> 
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>課程名稱</th> -->
<!-- 			<th>教練</th> -->
<!-- 			<th>照片</th> -->
<!-- 			<th>上課時間</th> -->
<!-- 			<th>價格</th> -->
<!-- 			<th>上課人數</th> -->
<!-- 			<th>地址</th> -->
<!-- 			<th>課程內容</th> -->
<!-- 			<th>詳細課程</th> -->
<!-- 		</tr> -->


		<%@ include file="page1.file"%>
		
		
<!-- 			<tr> -->
<%-- 				<td>${coachClassVO.className}</td> --%>
<%-- 				<td>${cocService1.getMemberName(coachClassVO.coachClassID)}</td> --%>
<!-- 				<td><img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}" --%>
<!-- 					width="100" height="100"></td> -->
<%-- 				<td>${coachClassVO.startTime}</td> --%>
<%-- 				<td>${coachClassVO.price}</td> --%>
<%-- 				<td>${coachClassVO.quantity}</td> --%>
<%-- 				<td>${coachClassVO.address}</td> --%>
<%-- 				<td>${coachClassVO.classContext}</td> --%>
				
<!-- 				<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="詳細課程"> -->
<%-- 			     <input type="hidden" name="coachClassID"  value="${coachClassVO.coachClassID}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Display_front"> -->
<!-- 			  </FORM> -->
<!-- 			</td> -->
<!-- 			</tr> -->
			
			
			
			
			
			
			
<!-- ====================================
———	COURSES SECTION
===================================== -->
<section class="py-8 py-md-10">
  <div class="container">
    <div class="row">

<c:forEach var="coachClassVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<jsp:useBean id="cocService1" scope="page" class="com.coachClass.model.CoachClassService" />

      <!-- Card -->
      <div class="col-sm-6 col-lg-3 col-xs-12">
        <div class="card">
          <a href="course-single-left-sidebar.html" class="position-relative">
            <img class="card-img-top" src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}" alt="Card image">
            <div class="card-img-overlay">
              <span class="badge badge-purple badge-rounded-circle">${coachClassVO.price}</span>
            </div>
          </a>
          <div class="card-body border-top-5 px-3 rounded-bottom border-purple">
            <h3 class="card-title">
              <a class="text-purple text-capitalize d-block text-truncate" href="course-single-left-sidebar.html">${coachClassVO.className}  ${cocService1.getMemberName(coachClassVO.coachClassID)}</a>
            </h3>
            <ul class="list-unstyled text-muted">
              <li>
                <i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>上課 ${coachClassVO.quantity} 人
              </li>
              <li>
                <i class="fa fa-clock-o mr-2" aria-hidden="true"></i>${coachClassVO.startTime}
              </li>
            </ul>
            <p> ${coachClassVO.classContext} </p>
              
              
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do" style="margin-bottom: 0px;">
			     <input type="submit" value="詳細課程">
			     <input type="hidden" name="coachClassID"  value="${coachClassVO.coachClassID}">
			     <input type="hidden" name="action"	value="getOne_For_Display_front">
			  </FORM>
          </div>
        </div>
      </div>

</c:forEach>

    </div>
  </div>

			
		
		
		<ul>
		<li><a href='<%=request.getContextPath()%>/front-end/coachClass/addCoachClass.jsp'>Add</a> a new CoachClass.</li>
	</ul>
	</table>
	
	
	<%@ include file="page2.file"%>


<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>