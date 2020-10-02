<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.blog.model.*"%>

<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�峹��ƭק� - update_blog_input.jsp</title>

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
				<h3>�峹��ƭק� - update_blog_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">�^����</a>
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
		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>�峹�s��:<font color=red><b>*</b></font></td>
				<td><%=blogVO.getBlogno()%></td>
			</tr>
			<tr>
				<td>�@��|���s��:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=blogVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>�峹����:</td>
	
				<td><select name="blogclass">	
						<option value="�߱o��y" ${(blogVO.blogClass eq '�߱o��y')?'selected':''}>�߱o��y</option>
						<option value="���d����" ${(blogVO.blogClass eq '���d����')?'selected':''}>���d����</option>
						<option value="�����v��" ${(blogVO.blogClass eq "�����v��")?'selected':''}>�����v��</option>
				</select></td>
				
			</tr>
			<tr>
				<td>�o����:</td>
				<td><input type="TEXT" name="xx" size="45"
					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>'
					readonly="readonly" style="background-color: lightgrey" /> 
					<input type="hidden" name="postdate" size="45"
					value='${blogVO.getPostDate()}' /></td>
			</tr>
			<tr>
				<td>���D:</td>
				<td><input type="TEXT" name="title" size="45"
					value="<%=blogVO.getTitle()%>" /></td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=blogVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="photo" id="photo2" /></td>
			</tr>
			<tr>
				<td></td>
				<td><img width="400" id="photopreview"></td>
			</tr>
			<tr>
				<td>�v��:</td>
				<td><input type="file" name="video" id="video2" size="45" /></td>
			</tr>
			
			
			<!-- 	<tr> -->
			<!-- 		<td>�峹���A:</td> -->
			<%-- 		<td><input type="TEXT" name="status" size="45" value="<%=blogVO.getStatus()%>" /></td> --%>
			<!-- 	</tr> -->
			<!-- 	<tr> -->
			<!-- 		<td>��s���:</td> -->
			<!-- 		<td><input name="postdate" id="f_date2" type="text" ></td> -->
			<!-- 	</tr> -->

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(blogVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->
			<!-- 	<td>�Ϥ�:</td> -->
			<!-- 		<td><input type="file" name="picture"/></td> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="blogno" value="<%=blogVO.getBlogno()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
	<script>
		var pictureForm = document.getElementById("photo2");
		pictureForm.addEventListener("change", function() {
			var file = this.files[0];
			var reader = new FileReader();
			reader.onload = function(event) {
				var source = event.target.result;
				var img = document.getElementById("photopreview");
				img.src = source;
			};
			reader.readAsDataURL(file);

		});
		
	</script>
</body>
</html>