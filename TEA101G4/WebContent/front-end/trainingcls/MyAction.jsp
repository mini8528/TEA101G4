<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.action.model.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%@ page import="com.member.model.*"%>


<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	pageContext.setAttribute("userVO", userVO);

	ActionService actionSvc = new ActionService();
	pageContext.setAttribute("actSvc", actionSvc);

	TrainingClsService tcSvc = new TrainingClsService();
	String memberid = new String(userVO.getMemberid());
	List<TrainingClsVO> list = tcSvc.getSomeByMemberid(memberid);
	TrainingClsDetailService tcsSvc = new TrainingClsDetailService();
	TrainingClsDetailVO tcdVO = (TrainingClsDetailVO) request.getAttribute("tcdVO");

	List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listAction3");
%>


<html lang="en">

<head>

<style>
table#table-1 {
	background-color: #ADADAD;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #ADADAD;
}

th, td {
	padding: 5px;
	text-align: center;
}
div.the_relative{
/*   border:1px solid red; */

  position: relative;
  
  top: 50px;
  left:80px;
  
}
</style>

</head>
<body bgcolor='white' onload="showalert()">

	<jsp:include page="/front-end/header.jsp" flush="true" />


    <section class="ftco-section ftco-no-pb ftco-no-pt ftco-program bg-light" id="programs-section">
      <div class="container">
        <div class="row no-gutters">
          <div class="col-md-4 ftco-animate py-5 nav-link-wrap js-fullheight">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
              <a class="nav-link px-4" id="v-pills-1-tab"  href="${pageContext.request.contextPath}/front-end/trainingcls/addMyCls.jsp"role="button" aria-controls="v-pills-1" aria-selected="true"><span class="mr-3 flaticon-gym"></span>健身訓練計畫</a>

              <a class="nav-link px-4" id="v-pills-2-tab"  href="${pageContext.request.contextPath}/front-end/action/listAllAction.jsp" role="tab" aria-controls="v-pills-2" aria-selected="false"><span class="mr-3 flaticon-body"></span>健身訓練運動</a>

              <a class="nav-link px-4" id="v-pills-3-tab"  href="${pageContext.request.contextPath}/front-end/trainingcls/showmycls.jsp" role="tab" aria-controls="v-pills-3" aria-selected="false"><span class="mr-3 flaticon-woman"></span>我的健身課表</a>

              <a class="nav-link px-4" id="v-pills-4-tab"  href="${pageContext.request.contextPath}/front-end/traininghist/showmyhist.jsp" role="tab" aria-controls="v-pills-4" aria-selected="false"><span class="mr-3 flaticon-abs"></span>我的健身紀錄</a>

              <a class="nav-link px-4" id="v-pills-5-tab" data-toggle="pill" href="#v-pills-5" role="tab" aria-controls="v-pills-5" aria-selected="false"><span class="mr-3 flaticon-running"></span> </a>

              <a class="nav-link px-4" id="v-pills-6-tab" data-toggle="pill" href="#v-pills-6" role="tab" aria-controls="v-pills-6" aria-selected="false"><span class="mr-3 flaticon-meditation"></span></a>

              <a class="nav-link px-4" id="v-pills-7-tab" data-toggle="pill" href="#v-pills-7" role="tab" aria-controls="v-pills-7" aria-selected="false"><span class="mr-3 flaticon-aerobic"></span></a>

            	<a class="nav-link px-4" id="v-pills-08-tab" data-toggle="pill" href="#v-pills-08" role="tab" aria-controls="v-pills-08" aria-selected="false"><span class="mr-3 flaticon-gym"></span> </a>
            </div>
          </div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div>
			<div class="the_relative">
			<h3>課程名稱: ${tcVO.trainingclsnm}</h3>
			<table style="width:380px">
				<tr>
					<th>健身訓練動作 </th>
				</tr>
				<c:forEach var="tcdVO" items="${listActions3}">
				<tr>	
						<td>${tcdVO.actionidnm } </td>
				</tr>	
				</c:forEach>
				
			</table>
			
		
			<FORM METHOD="post" 
				ACTION="<%=request.getContextPath()%>/trainingsche/TrainingScheServlet"
				style="margin-bottom: 0px;">
				<input type="hidden" name="actionid" value="${actionVO.actionnm}">
				<input type="hidden" name="trainingclsid" value="${clsVO.trainingclsid}"> 
				<input type="hidden" name="memberid" value="${clsVO.memberid}"> 
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="開始運動" >
			</FORM>
	   </div>
	   </div>

				</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>

</html>