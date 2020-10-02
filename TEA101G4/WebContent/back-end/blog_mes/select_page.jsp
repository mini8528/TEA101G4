<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Blog_Mes: Home</title>

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
			<td><h3>Blog_Mes: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Blog_Mes: Home</p>

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
		<li><a href='listAllBlog_Mes.jsp'>List</a> all Blog_Mes. <br>
			<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet">
				<b>輸入部落格留言編號 (如BM00001):</b> <input type="text" name="blogmesno">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="blogMesSvc" scope="page"
			class="com.blog_mes.model.Blog_MesService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet">
				<b>選擇部落格留言編號:</b> <select size="1" name="blogmesno">
					<c:forEach var="blog_MesVO" items="${blogMesSvc.all}">
						<option value="${blog_MesVO.blogMesno}">${blog_MesVO.blogMesno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet">
				<b>選擇文章編號:</b> <select size="1" name="blogmesno">
					<c:forEach var="blog_MesVO" items="${blogMesSvc.all}">
						<option value="${blog_MesVO.blogMesno}">${blog_MesVO.blogno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet">
				<b>選擇文章編號列出所有留言:</b> <select size="1" name="blogno">
					<c:forEach var="blog_MesVO" items="${blogMesSvc.all}">
						<option value="${blog_MesVO.blogno}">${blog_MesVO.blogno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getAll_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>


	</ul>


	<h3>文章管理</h3>

	<ul>
		<li><a href='addBlog_Mes.jsp'>Add</a> a new Blog_Mes.</li>
	</ul>

</body>
</html>