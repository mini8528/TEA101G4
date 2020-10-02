<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.blog.model.*"%>

<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章資料修改 - update_blog_input.jsp</title>

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
				<h3>文章資料修改 - update_blog_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
				<td>文章編號:<font color=red><b>*</b></font></td>
				<td><%=blogVO.getBlogno()%></td>
			</tr>
			<tr>
				<td>一般會員編號:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=blogVO.getMemberId()%>" /></td>
			</tr>
			<tr>
				<td>文章分類:</td>
	
				<td><select name="blogclass">	
						<option value="心得交流" ${(blogVO.blogClass eq '心得交流')?'selected':''}>心得交流</option>
						<option value="健康食譜" ${(blogVO.blogClass eq '健康食譜')?'selected':''}>健康食譜</option>
						<option value="健身影片" ${(blogVO.blogClass eq "健身影片")?'selected':''}>健身影片</option>
				</select></td>
				
			</tr>
			<tr>
				<td>發文日期:</td>
				<td><input type="TEXT" name="xx" size="45"
					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>'
					readonly="readonly" style="background-color: lightgrey" /> 
					<input type="hidden" name="postdate" size="45"
					value='${blogVO.getPostDate()}' /></td>
			</tr>
			<tr>
				<td>標題:</td>
				<td><input type="TEXT" name="title" size="45"
					value="<%=blogVO.getTitle()%>" /></td>
			</tr>
			<tr>
				<td>內文:</td>
				<td><input type="TEXT" name="text" size="45"
					value="<%=blogVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" id="photo2" /></td>
			</tr>
			<tr>
				<td></td>
				<td><img width="400" id="photopreview"></td>
			</tr>
			<tr>
				<td>影片:</td>
				<td><input type="file" name="video" id="video2" size="45" /></td>
			</tr>
			
			
			<!-- 	<tr> -->
			<!-- 		<td>文章狀態:</td> -->
			<%-- 		<td><input type="TEXT" name="status" size="45" value="<%=blogVO.getStatus()%>" /></td> --%>
			<!-- 	</tr> -->
			<!-- 	<tr> -->
			<!-- 		<td>更新日期:</td> -->
			<!-- 		<td><input name="postdate" id="f_date2" type="text" ></td> -->
			<!-- 	</tr> -->

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(blogVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->
			<!-- 	<td>圖片:</td> -->
			<!-- 		<td><input type="file" name="picture"/></td> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="blogno" value="<%=blogVO.getBlogno()%>">
		<input type="submit" value="送出修改">
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