<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.action.model.ActionService"%>
<%@ page import="com.action.model.ActionVO"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="com.trainingclsdetail.model.*"%>


<%
	ActionService actionSvc = new ActionService();
    List<ActionVO> list = actionSvc.getAll();

    pageContext.setAttribute("list",list);
    pageContext.setAttribute("actSvc", actionSvc);
    String classId;
    TrainingClsVO tcVO = (TrainingClsVO)request.getAttribute("tcVO");
    if (tcVO == null) {
    	classId = request.getParameter("classId"); 
    	TrainingClsDetailJDBCDAO dao = new TrainingClsDetailJDBCDAO();
		List<TrainingClsDetailVO> listaction = dao.select(classId);
		pageContext.setAttribute("listaction", listaction);

		request.getSession().setAttribute("listaction", listaction);
    } else {
    	classId = tcVO.getTrainingclsid();
    }
        
    TrainingClsService traClsSvc = new TrainingClsService();
	TrainingClsVO clsVO = traClsSvc.getOneTrainingCls(classId);
//		pageContext.setAttribute("tcVO", clsVO);
    session.setAttribute("clsVO", clsVO);
    
    TrainingClsDetailService tcsSvc = new TrainingClsDetailService();
    TrainingClsDetailVO tcdVO = (TrainingClsDetailVO)request.getAttribute("tcdVO");
    // session.setAttribute("listaction2", session.getAttribute("listaction2"));
    // session.setAttribute("tcdList", session.getAttribute("tcdList"));
    
    List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listActions3");
    // session.setAttribute("listaction2", session.getAttribute("listaction2"));
%>


<!DOCTYPE html>
<html lang="en">

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
  
  form.the_relative{
/*   border:1px solid red; */

  position: relative;
  
  bottom: 350px;
  left:300px;
  
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

</head>
<body bgcolor=white>
<jsp:include page="/front-end/header.jsp" flush="true" />

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

    <section class="ftco-section ftco-no-pb ftco-no-pt ftco-program bg-light" id="programs-section">
      <div class="container">
        <div class="row no-gutters" style="width:500px">
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
          
<form class="the_relative" action="<%=request.getContextPath()%>/trainingclsdetail/TrainingClsDetailServlet">
	
	<table>
	
		<tr>
			<th>運動部位</th>
			<th style="width:500px">健身動作</th>
		</tr>
<%-- 		<%@ include file="frontendpage1.file" %>  --%>
<%-- 		<c:forEach var="partName" items="${partList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		<c:forEach var="partName" items="${partList}">	
			<tr>
				<td>${partName}</td>
				<td>
						<table>
							<c:forEach var="actionVO" items="${list}">
								<c:if test="${actionVO.part == partName}" > 
		 							   <tr>
		 							   		<td style="width:500px">${actionVO.actionnm}</td>
			 							 	<td style="width:300px">
												<input type="checkbox" name="actionId" value="${actionVO.actionid}" ${tcdList.contains(actionVO.actionid)? "checked":""} >
											</td> 
		 							   </tr>
								</c:if> 
							</c:forEach>
						</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="action" value="insertaction">
	<input type="submit" value="增加運動" >
	<input type="hidden" name="trainingclsid" value="${tcVO.trainingclsid}">
</form>
<form class="the_relative">
<h3 class="the_relative">健身課程名稱: ${tcVO.trainingclsnm} </h3>
						<table >
							<tr>
								<th>健身動作</th>
								<FORM> 
									<input type="hidden" name="trainingclsid"  value="${tcVO.trainingclsnm}">
									<input type="hidden" name="actionidnm"  value="${actionVO2.actionidnm}">
						  	    </FORM>
							</tr>
						
							<c:forEach var="actionVO" items="${listActions3}">
								<tr>
									<td>${actionVO.actionidnm }</td>
								</tr>
							</c:forEach>
						</table>

								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trainingcls/TrainingClsServlet" style="margin-bottom: 0px;">
									<input type="hidden" name="actionid"  value="${actionVO.actionid}">
									<input type="hidden" name="trainingclsid"  value="${tcVO.trainingclsid}">
									<input type="hidden" name="memberid"  value="${tcVO.memberid}">
									<input type="hidden" name="action"	value="save">
						  	    	<input type="submit" value="儲存完成">
						  	    </FORM>
	</form>					  	   
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