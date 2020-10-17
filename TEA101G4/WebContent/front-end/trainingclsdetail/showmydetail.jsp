<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%@ page import="com.action.model.*"%>


<%
ActionService actionSvc = new ActionService();
List<ActionVO> list = actionSvc.getAll();
MemberVO userVO= (MemberVO) session.getAttribute("userVO");
pageContext.setAttribute("userVO", userVO);
TrainingClsService tcSvc = new TrainingClsService();

String memberid = new String(userVO.getMemberid());
TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
List<TrainingClsDetailVO> list2 = tcdSvc.select_memberid(memberid);
System.out.println("MEMBERid="+memberid);
System.out.println("list="+list);
pageContext.setAttribute("list", list);

TrainingClsDetailVO tcdVO = (TrainingClsDetailVO)request.getAttribute("tcdVO");
%>


<html>
<head>
<title>會員的所有訓練課程</title>

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

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<img src="images/bg_1.jpg" style="float:right;"  width="150" height="250" border="0" >
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員所有訓練課程 </h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		
		<th>課程名稱</th>
		<th>訓練動作</th>
		
		
	</tr>
<c:forEach var = "tcdVO" items = "${list}">
		<tr>
		    <td>${tcdVO.trainingclsid}</td>
			<td>${clsVO.trainingclsnm}</td>
			<td>${actSvc.getOneAction(actionVO.actionid).actionnm}</td>

		</tr>
</c:forEach>
</table>

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>