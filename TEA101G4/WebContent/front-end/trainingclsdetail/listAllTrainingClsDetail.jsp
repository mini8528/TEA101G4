<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
TrainingClsDetailService tcdSvc = new TrainingClsDetailService();
    List<TrainingClsDetailVO> list = tcdSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="tcdSvc" scope="page" class="com.trainingclsdetail.model.TrainingClsDetailService" /> --%>

<!DOCTYPE html>
<html lang="en">


<title>所有訓練課程內容資料 - listAllEmp.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有訓練課程內容資料 - listAlltrainingclsdetail.jsp</h3>
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
		<th>訓練課程內容編號</th>
		<th>訓練課程編號</th>
		<th>修改</th>
		<th>刪除</th>
	
	</tr>
	<%@ include file="frontendpage1.file" %> 
	<c:forEach var="tcdVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${tcdVO.trainingclsdetailid}</td>
			<td>${tcdVO.trainingclsid}</td>
			<td>${tcdVO.actionid}</td>

			
			
<%-- 			<td><c:forEach var="trainingclsVO" items="${tcSvc.all}"> --%>
<%--                     <c:if test="${tcdVO.trainingclsid==trainingclsVO.trainingclsid}"> --%>
<%-- 	                    ${trainingclsVO.trainingclsid}【${trainingclsVO.trainingclsnm} - ${trainingclsVO.loc}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trainingclsdetail/TrainingClsDetailServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="trainingclsdetailid"  value="${tcdVO.trainingclsdetailid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trainingclsdetail/TrainingClsDetailServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="trainingclsdetailid"  value="${tcdVO.trainingclsdetailid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="frontendpage2.file" %>
    <jsp:include page="/front-end/footer.jsp" flush="true" />

</body>

</html>