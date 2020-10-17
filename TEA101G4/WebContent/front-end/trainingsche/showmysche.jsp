<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trainingcls.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%@ page import="com.trainingsche.model.*"%>


<%

MemberVO userVO= (MemberVO) session.getAttribute("userVO");
pageContext.setAttribute("userVO", userVO);


String memberid = new String(userVO.getMemberid());
TrainingScheService tsSvc = new TrainingScheService();
List<TrainingScheVO> list = tsSvc.SELECT_BY_MEMBERID(memberid);
System.out.println("MEMBERid="+memberid);
System.out.println("list="+list);
pageContext.setAttribute("list", list);

%>


<html>
<head>
<title>�|�����Ҧ��V�mhist</title>

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
<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�|���Ҧ��V�m�ҵ{ </h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>

		<th>�V�m�ҵ{</th>
		
	</tr>
	<c:forEach var="tsVO" items="${list}">

		<tr>


			<td>${tcSvc.getOneTrainingcls(tsVO.trainingclsid).trainingclsnm}</td>
			

		</tr>
	</c:forEach>
</table>

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>