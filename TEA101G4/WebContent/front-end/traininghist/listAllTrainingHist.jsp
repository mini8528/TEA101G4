<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.traininghist.model.*"%>
<%@ page import="com.action.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
System.out.println("xxxxx");
    TrainingHistService thSvc = new TrainingHistService();
    List<TrainingHistVO> list = thSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="actionSvc" scope="page" class="com.action.model.ActionService"/>

<html>
<head>
<title>所有訓練歷程</title>

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
<table id="table-1">
	<tr><td>
		 <h3>所有訓練歷程課程 </h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

		<th>訓練動作名稱</th>
		<th>訓練時間</th>
		<th>訓練運動組數</th>
		<th>訓練運動次數</th>
		<th>訓練運動重量</th>
	</tr>
<%-- 	<%@ include file="frontendpage1.file" %>  --%>
	<c:forEach var="traininghistVO" items="${list}">
<%-- 	begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>

		<tr>
<%-- 				<c:forEach var="actionVO" items="${actionSvc.all}"> --%>
<%-- 					<c:if test="${traininghistVO.actionid==actionVO.actionid}"> --%>
<%-- 						<input type="hidden" name="actionnm" value="${actionVO.actionnm}"> --%>
<%-- 						<td>${actionVO.actionnm}</td> --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>

			<td>${actionSvc.getOneAction(traininghistVO.actionid).actionnm}</td>
			<td>${traininghistVO.trainingtime}</td>
			<td>${traininghistVO.trainingset}</td> 
			<td>${traininghistVO.trainingrep}</td>
			<td>${traininghistVO.trainingwt}</td>

		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="frontendpage2.file" %> --%>
  <jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>