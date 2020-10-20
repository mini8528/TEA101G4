<%@page import="com.trainingsche.model.TrainingScheVO"%>
<%@page import="com.traininghist.model.TrainingHistVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%@ page import="com.traininghist.model.*"%>
<%@ page import="com.trainingsche.model.*"%>



<%

MemberVO userVO= (MemberVO) session.getAttribute("userVO");
pageContext.setAttribute("userVO", userVO);
TrainingClsService tcSvc = new TrainingClsService();

String memberid = new String(userVO.getMemberid());
TrainingHistService thSvc = new TrainingHistService();
 TrainingHistVO thVO = (TrainingHistVO)request.getAttribute("thVO");
// TrainingScheService tsSvc = new TrainingScheService();
// TrainingScheVO tsVO = (TrainingScheVO)request.getAttribute("tsVO");



 List<TrainingHistVO> list = thSvc.SELECT_BY_MEMBERID(memberid);
 pageContext.setAttribute("list", list);
TrainingClsDetailService tcsSvc = new TrainingClsDetailService();
TrainingClsDetailVO tcdVO = (TrainingClsDetailVO)request.getAttribute("tcdVO");
%>
<jsp:useBean id="actionSvc" scope="page" class="com.action.model.ActionService" />
<jsp:useBean id="tsSvc" scope="page" class="com.trainingsche.model.TrainingScheService" />

<html>
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
	width: 500px;
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
</style>

</head>
<body bgcolor='white'>
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
     
          <div class="col-md-8 ftco-animate p-4 p-md-5 d-flex align-items-center js-fullheight">
            
            <div class="tab-content pl-md-5" id="v-pills-tabContent">

<!--               <div class="tab-pane fade show active py-0" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab"> -->
                <span class="icon mb-3 d-block flaticon-gym"></span>
                <h2 class="mb-4"></h2>
                <main role="main" class="main-content">
        <div class="container-fluid">
          <div class="row justify-content-center">
            <div class="col-12">
               <h2 class="page-title">健身紀錄</h2>
               <p>My training History</p>
              <div class="row">
                <div class="col-md-6 my-4">
                  <div class="card shadow" style="width:600px">
                    <div class="card-body" style="width:600px">
                    
                      <table class="table table-hover" style="width:500px">
                          <tr>
                                <th>訓練動作名稱 </th>
                            	<th>訓練運動組數</th>
		               			<th>訓練運動次數</th>
								<th>訓練運動重量</th>
								<th>訓練時間</th>
                          </tr>
                 <%@ include file="frontendpage1.file" %> 
		         <c:forEach var="thVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                               <tr>
                                  <td>${thVO.actionnm}</td>
                                  <td>${thVO.trainingset}</td> 
			                      <td>${thVO.trainingrep}</td>
			                      <td>${thVO.trainingwt}</td>
			                      <td>${tsSvc.getOneTrainingSche(thVO.trainingscheid).endtime}</td>
                               </tr>
<!-- 	    <tr> -->
<%-- 		<td>${actionSvc.getOneAction(traininghistVO.actionid).actionnm}</td> --%>
<%-- 			<td>${traininghistVO.trainingset}</td>  --%>
<%-- 			<td>${traininghistVO.trainingrep}</td> --%>
<%-- 			<td>${traininghistVO.trainingwt}</td> --%>
<%-- 			<td>${tsSvc.getOneTrainingSche(thVO.trainingscheid).endtime}</td> --%>
<!-- 		</tr> -->
                            </c:forEach>
                      </table>
                      <%@ include file="frontendpage2.file" %>
                   </div>
                  </div>
                 </div>
                </div>
               </div>
              </div>
         </div>
        </main>
    </section>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>