<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Blog_Likes: Home</title>

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
			<td><h3>Blog_Likes: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Blog_Likes: Home</p>

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
		<li><a href='listAllBlog_Likes.jsp'>List</a> all Blog_Likes. <br>
			<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet">
				<b>��J���g�s�� (�pBL00001):</b> <input type="text" name="bloglikesno">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="blogLikesSvc" scope="page"
			class="com.blog_likes.model.Blog_LikesService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet">
				<b>��ܫ��g�s��:</b> <select size="1" name="bloglikesno">
					<c:forEach var="blog_LikesVO" items="${blogLikesSvc.all}">
						<option value="${blog_LikesVO.blogLikesno}">${blog_LikesVO.blogLikesno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet">
				<b>��ܤ峹�s��:</b> <select size="1" name="bloglikesno">
					<c:forEach var="blog_LikesVO" items="${blogLikesSvc.all}">
						<option value="${blog_LikesVO.blogLikesno}">${blog_LikesVO.blogno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>


	</ul>


	<h3>�峹�޲z</h3>

	<ul>
		<li><a href='addBlog_Likes.jsp'>Add</a> a new Blog_Likes.</li>
	</ul>

</body>
</html>