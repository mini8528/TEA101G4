<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    TrainingClsService tcSvc = new TrainingClsService();
    List<TrainingClsVO> list = tcSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>Front-end所有訓練課程</title>

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
		 <h3>所有訓練課程 </h3>
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
		<th>訓練課程編號</th>
		<th>會員編號</th>
		<th>課程名稱</th>
		<th>上傳日期</th>
		<th>刪除課程</th>
		
	</tr>
	<%@ include file="frontendpage1.file" %> 
	<c:forEach var="trainingclsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${trainingclsVO.trainingclsid}</td>
			<td>${trainingclsVO.memberid}</td>
			<td>${trainingclsVO.trainingclsnm}</td>
			<td>${trainingclsVO.posttime}</td>
<%-- 			<td>${trainingclsVO.updatetime}</td> --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trainingcls/TrainingClsServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="trainingclsid"  value="${trainingclsVO.trainingclsid}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>front-end/trainingcls/frontendtrainingcls/TrainingClsServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="trainingclsid"  value="${trainingclsVO.trainingclsid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="frontendpage2.file" %>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>