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
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>

<%
ActionService actionSvc = new ActionService();
List<ActionVO> list = actionSvc.getAll();
for(ActionVO tempVO: list) {
	byte[] v_byte = tempVO.getVideo();
	StringBuilder sb = new StringBuilder();
	sb.append("data:video/mp4;base64,");
	sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(v_byte, false)));
	String videoUrl = sb.toString();
	tempVO.setVideoBase64Url(videoUrl);
}
pageContext.setAttribute("listxx",list);

	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	pageContext.setAttribute("userVO", userVO);

	TrainingHistService thSvc = new TrainingHistService();
	List<TrainingHistVO> listHistVO = thSvc.getAll();
	pageContext.setAttribute("list", list);

	TrainingHistVO thVO2 = (TrainingHistVO) request.getAttribute("thVO2");

	TrainingClsVO trainingVO = (TrainingClsVO) request.getAttribute("tcVO");
	List<TrainingHistVO> thList = (List<TrainingHistVO>) session.getAttribute("thList");
	List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
%>
<jsp:useBean id="trainingclsSvc" scope="page" class="com.trainingcls.model.TrainingClsService" />
<jsp:useBean id="trainingscheSvc" scope="page" class="com.trainingsche.model.TrainingScheService" />
<jsp:useBean id="traininghistSvc" scope="page" class="com.traininghist.model.TrainingHistService" />

<html>
<head>
<style>
/* table#table-1 { */
/* 	background-color: #CCCCFF; */
/* 	border: 2px solid black; */
/* 	text-align: center; */
/* } */


form{
/*  background-color: #CCCCFF; */
  display: inline;
  padding: 20px;
  
  margin-top: 10px;
  display: inline-block; 
}
</style>

<style>
table {
	width: 400px;
/* 	background-color: white; */
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
/*  	border: 1px solid #CCCCFF;  */
}

th, td {
/*     border:1px solid #CCCCFF;   */
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
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

<form></form>
<!-- 	<FORM  METHOD="post"  -->
<%-- 		ACTION="<%=request.getContextPath()%>/trainingsche/TrainingScheServlet" --%>
<!-- 		style="margin-bottom: 0px;"> -->
<%-- 		<input type="hidden" name="trainingclsid" value="${tcVO.trainingclsid}">  --%>
<!-- 		<input type="hidden" name="action" value="mytraininghist">  -->
<!-- 		<input type="submit" value="開始運動"> -->
<!-- 	</FORM> -->
	

    <FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/trainingsche/TrainingScheServlet"
		style="margin-bottom: 0px;">

	<table >
<!-- 使用巢狀迴圈方式比對		 -->
		<c:forEach var="thVO" items="${thList}">
		  <h3>健身訓練動作:  ${actionMap.get(thVO.actionid).actionnm}</h3>
		   <c:forEach var="actionVOxx" items="${listxx}">
		      <c:if test="${thVO.actionid eq actionVOxx.actionid}">
		
				   <tr> 
				      <td>
							<video id="addVideo" controls autoplay width="320" height="240" muted>
				               <source src="${actionVOxx.videoBase64Url}"/>
				            </video>
					  </td>
					</tr>
		      </c:if>
		   
		   </c:forEach>
		</table>
		
			<table>

				<tr>
					<td>組數:</td>
					<td><input type="TEXT" name="trainingset" size="15"
						value="${thVO.trainingset}"></td>
				</tr>
				<tr>
					<td>次數:</td>
					<td><input type="TEXT" name="trainingrep" size="15"
						value="${thVO.trainingrep}"></td>
				</tr>
				<tr>
					<td>重量:</td>
					<td><input type="TEXT" name="trainingwt" size="15"
						value="${thVO.trainingwt}"></td>
				</tr>

			</table>
			
			<input type="hidden" name="traininghistid" value="${thVO.traininghistid}">
			
		</c:forEach>


	
		 <input type="hidden" name="trainingscheid" value="${tsVO.trainingscheid}"> 
		 <input type="hidden" name="action" value="update"> 
		 <input type="submit" value="完成運動">
	</FORM>
	
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