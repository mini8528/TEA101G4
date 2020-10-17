<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<!DOCTYPE html>
<html lang="en">

<head>


<style>
  table#table-1 {
	width: 450px;
	background-color: #ffc0cb;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<jsp:include page="/front-end/header.jsp" flush="true" />


<table id="table-1">
   <tr><td><h3>GYMPAYZ Action: Home</h3><h4>( MVC )</h4></td></tr>
</table>
 <h4><a href="${pageContext.request.contextPath}/front-end/trainingcls/select_page.jsp">回訓練課表頁面</a></h4>
<p>Front-End Action: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllAction.jsp'>List</a> all Actions. <br>
		<br></li>


		<jsp:useBean id="actionSvc" scope="page"
			class="com.action.model.ActionService" />

	</ul>

	</div> <!-- element wrapper ends -->

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>