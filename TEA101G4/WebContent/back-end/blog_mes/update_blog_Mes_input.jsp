<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog_mes.model.*"%>

<%
	Blog_MesVO blogMesVO = (Blog_MesVO) request.getAttribute("blog_MesVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�d����ƭק� - update_blog_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>������d����ƭק� - update_blog_Mes_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/blog_mes/select_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet"
		name="form1">
		<table>
			<tr>
				<td>������d���s��:<font color=red><b>*</b></font></td>
				<td><%=blogMesVO.getBlogMesno()%></td>
			</tr>
			<tr>
				<td>�峹�s��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="blogno" size="45"
					value="<%=blogMesVO.getBlogno()%>" /></td>
			</tr>
			<tr>
				<td>�@��|���s��:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=blogMesVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>�d�����e:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=blogMesVO.getText()%>" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="blogMesno" value="<%=blogMesVO.getBlogMesno()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>