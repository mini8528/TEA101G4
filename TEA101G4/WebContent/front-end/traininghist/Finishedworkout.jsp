<%@page import="com.trainingclsdetail.model.TrainingClsDetailVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.traininghist.model.*"%>
<%@ page import="com.trainingsche.model.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%@ page import="com.action.model.*"%>
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
	TrainingScheService tsSvc = new TrainingScheService();
	TrainingScheVO tsVO = (TrainingScheVO) request.getAttribute("tsVO");
	TrainingHistService thSvc = new TrainingHistService();
	TrainingHistVO thVO = (TrainingHistVO) request.getAttribute("thVO");

	///////
	List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listAction3");
	///////
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<style>
  table#table-1 {
	background-color: #CCCCFF;
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
</style>
<meta charset="UTF-8">
<title>運動完成</title>
</head>
<body>
 <jsp:include page="/front-end/header.jsp" flush="true" />
 
  
   
   <section class="py-7 py-md-10">
	<div class="container">
		<div class="row"> 
  <div class="col-sm-6 col-xs-12 order-md-1">
	 <div class="image mb-4 mb-md-0">
	   <img class="img-fluid rounded" src="<%=request.getContextPath()%>/assets/img/002.jpg">
	 </div>
  </div>
  
  <div class="col-sm-6 col-xs-12">
				<div class="">
          <div class="section-title mb-4">
           <div class="section-title justify-content-center mb-4 mb-md-8 wow fadeInUp">
       <span class="shape shape-left bg-info"></span>
         <h1 class="text-danger">Complete WorkOut</h1>
       <span class="shape shape-right bg-info"></span>
  </div>

          </div>
  </div>
  <p class="text-dark font-size-15 mb-4">恭喜你!完成了個人化健身課表，為了維持更好的體態，別忘了每周回來紀錄您的健身歷程</p>
          <p class="text-danger font-size-15 mb-4">Gympayz 隨時掌握您的體態紀錄，讓您傭有更好的身材</p>

          <ul class="list-unstyled mb-5">
            <li class="d-flex align-items-baseline text-muted mb-2">
							<i class="fa fa-check mr-2" aria-hidden="true"></i>
							提供最專業的教學
						</li>
            <li class="d-flex align-items-baseline text-muted mb-2">
							<i class="fa fa-check mr-2" aria-hidden="true"></i>
							提供各式健身影片
						</li>
            <li class="d-flex align-items-baseline text-muted mb-2">
							<i class="fa fa-check mr-2" aria-hidden="true"></i>
							最準確掌握您的健身紀錄
            </li>
            <li class="d-flex align-items-baseline text-muted mb-2">
              <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                                                          個人化課表設計
            </li>
            <li class="d-flex align-items-baseline text-muted mb-2">
              <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                                                       全方位訓練身體各部位肌群
            </li>
          </ul>
        </div>
			</div>
		</div>
 
 <jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>