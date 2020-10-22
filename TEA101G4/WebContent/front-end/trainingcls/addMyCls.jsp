<%@page import="com.trainingclsdetail.model.TrainingClsDetailVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingcls.model.TrainingClsService"%>
<%@ page import="com.trainingcls.model.TrainingClsVO"%>
<%@ page import="com.member.model.*"%>


<%

MemberVO userVO= (MemberVO) session.getAttribute("userVO");

pageContext.setAttribute("userVO", userVO);
TrainingClsVO tcVO = (TrainingClsVO) request.getAttribute("tcVO");

List<TrainingClsDetailVO> listActions3 = new ArrayList<>();
session.setAttribute("listActions3", listActions3);

%>


<!DOCTYPE html>
<html lang="en">

<head>

<!-- <style> -->
<!-- /*   table#table-1 { */ -->
<!-- /* 	background-color: #CCCCFF; */ -->
<!-- /*     border: 2px solid black; */ -->
<!-- /*     text-align: center; */ -->
<!-- /*   } */ -->
<!-- /*   table#table-1 h4 { */ -->
<!-- /*     color: red; */ -->
<!-- /*     display: block; */ -->
<!-- /*     margin-bottom: 1px; */ -->
<!-- /*   } */ -->
<!-- /*   h4 { */ -->
<!-- /*     color: blue; */ -->
<!-- /*     display: inline; */ -->
<!-- /*   } */ -->
<!-- </style> -->

<!-- <style> -->
<!-- /*   table { */ -->
<!-- /* 	width: 400px; */ -->
<!-- /* 	background-color: white; */ -->
<!-- /* 	margin-top: 5px; */ -->
<!-- /* 	margin-bottom: 5px; */ -->
<!-- /*   } */ -->
<!-- /*   table, th, td { */ -->
<!-- /*     border: 1px solid #CCCCFF; */ -->
<!-- /*   } */ -->
<!-- /*   th, td { */ -->
<!-- /*     padding: 5px; */ -->
<!-- /*     text-align: center; */ -->
<!-- /*   } */ -->
<!-- </style> -->



</head>
<body bgcolor=white>



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

              <div class="tab-pane fade show active py-0" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">
                <span class="icon mb-3 d-block flaticon-gym"></span>
                <h2 class="mb-4">開始我的健身訓練計畫</h2>
                <p>新增一個屬於自己的健身計畫，建議新手將健身將計畫以胸、背和臀以上三個部位區分您的課程，由於大肌群的訓練可以提高我們人體的基礎代謝，大肌群必須以複合動作為主，不太方便進行孤立訓練，所以消耗的體能也相對多一些</p>
                <p>初學者建議：先練大肌肉群，比如胸肌、背闊肌、大腿肌肉。練習1到2個月後，待身體力量上升後，再穿插小肌肉和大肌肉群里一起訓練，以上的健身訓練安排可以讓您達到事倍功半的效果</p>
              
              
               <p class="text-dark font-size-15 mb-4">新增我的健身計畫</p>
               <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/trainingcls/TrainingClsServlet" name="form1">
		<table>
			<tr>
				<td>輸入健身計畫名稱:</td>
				<td>
				<input type="TEXT" name="trainingclsnm" size="30" value="<%=(tcVO == null) ? "" : tcVO.getTrainingclsnm()%>" /></td>
			    <input type=hidden name="memberid" size="30" value="<%=userVO.getMemberid()%>" />
			</tr>
<!-- 			<tr> -->
<!-- 				<td>會員編號:</td> -->
<!-- 			</tr> -->
		</table>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
              
              </div>

         





      

   
            </div>
          </div>
        </div>
      </div>
    </section>



<!--                <p class="text-dark font-size-15 mb-4">開始設計一個屬於自己的健身課表</p> -->
               <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/trainingcls/TrainingClsServlet" name="form1">


    
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