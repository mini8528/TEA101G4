<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Blog_Save: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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

	<table id="table-1">
		<tr>
			<td><h3>Blog_Save: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Blog_Save: Home</p>

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllBlog_Save.jsp'>List</a> all Blog_Mes. <br>
			<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet">
				<b>��J�峹���ýs�� (�pBS00001):</b> <input type="text" name="blogsaveno">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="blogSaveSvc" scope="page"
			class="com.blog_save.model.Blog_SaveService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet">
				<b>��ܤ峹���ýs��:</b> <select size="1" name="blogsaveno">
					<c:forEach var="blog_SaveVO" items="${blogSaveSvc.all}">
						<option value="${blog_SaveVO.blogSaveno}">${blog_SaveVO.blogSaveno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet">
				<b>��ܷ|���s��:</b> <select size="1" name="blogsaveno">
					<c:forEach var="blog_SaveVO" items="${blogSaveSvc.all}">
						<option value="${blog_SaveVO.blogSaveno}">${blog_SaveVO.memberId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>


	</ul>


	<h3>�峹�޲z</h3>

	<ul>
		<li><a href='addBlog_Save.jsp'>Add</a> a new Blog_Save.</li>
	</ul>

</body>
</html>