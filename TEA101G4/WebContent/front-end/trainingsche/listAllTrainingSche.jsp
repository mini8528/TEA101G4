<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingsche.model.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%

    TrainingScheService tsSvc = new TrainingScheService();
    List<TrainingScheVO> list = tsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="trainingclsSvc" scope="page" class="com.trainingcls.model.TrainingClsService"/>

<!DOCTYPE html>
<html lang="en">

 <jsp:include page="/front-end/header.jsp" flush="true" />

<style>
  table#table-1 {
	background-color: #FF66CC;
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

<table id="table-1">
	<tr><td>
		 <h3>所有訓練行程 </h3>
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

		<th>訓練課程名稱</th>
<!-- 		<th>會員編號</th> -->
		<th>訓練開始時間</th>
		<th>訓練結束時間</th>
	
		
	</tr>
	<%@ include file="frontendpage1.file" %> 
	<c:forEach var="trainingscheVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
<%-- 				<c:forEach var="tcVO" items="${tcSvc.all}"> --%>
<%-- 					<c:if test="${trainingscheVO.trainingclsid==trainingclsVO.trainingclsid}"> --%>
<%-- 						<input type="hidden" name="trainingclsnm" value="${trainingclsVO.trainingclsnm}"> --%>
<%-- 						<td>${trainingclsVO.trainingclsnnm}</td> --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<td>${trainingclsSvc.getOneTrainingCls(trainingscheVO.trainingclsid).trainingclsnm}</td>
<%-- 			<td>${trainingscheVO.trainingclsid}</td> --%>
<%-- 			<td>${trainingscheVO.memberid}</td> --%>
			<td>${trainingscheVO.starttime}</td>
			<td>${trainingscheVO.endtime}</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="frontendpage2.file" %>


    
    <jsp:include page="/front-end/footer.jsp" flush="true" />
</body>

</html>