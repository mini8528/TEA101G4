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
	// TrainingClsVO tcVO = (TrainingClsVO)request.getAttribute("tcVO");
	String memberid = new String(userVO.getMemberid());
	List<TrainingClsVO> list = tcSvc.getSomeByMemberid(memberid);
	// OrdermasterService ordermasterSvc = new OrdermasterService();

	TrainingClsDetailService tcsSvc = new TrainingClsDetailService();
	TrainingClsDetailVO tcdVO = (TrainingClsDetailVO) request.getAttribute("tcdVO");

	///////
	List<TrainingClsDetailVO> listActions3 = (List<TrainingClsDetailVO>) session.getAttribute("listAction3");
	///////
%>


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
</style>

<style>
table {
	width: 400px;
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
<body bgcolor='white' onload="showalert()">

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
	

	<h3>課程名稱: ${tcVO.trainingclsnm}</h3>
	<table>
		<tr>
			<th>健身訓練動作</th>

		</tr>
		
          
		<c:forEach var="tcdVO" items="${listActions3}">
		<tr>	
				<td>${tcdVO.actionidnm }</td>
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

	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>

</html>