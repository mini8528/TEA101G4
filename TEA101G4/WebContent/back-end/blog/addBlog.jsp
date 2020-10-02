<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章資料新增 - addBlog.jsp</title>

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
				<h3>文章資料新增 - addBlog.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>新增文章:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="memberSvc" scope="page"
		class="com.member.model.MemberService" />
	
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>一般會員編號:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=(blogVO == null) ? "M001" : blogVO.getMemberId()%>" /></td>
				<td><c:forEach var="memberVO" items="${memberSvc.all}">
						<c:if test="${blogVO.memberId eq memberVO.memberid}">
							${memberVO.name}
						</c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td>文章分類:</td>
				<td><select name="blogclass">
						<option value="心得分享">心得分享</option>
						<option value="健康食譜">健康食譜</option>
						<option value="健身影片">健身影片</option>
				</select></td>
				<!-- 				<td><input type="TEXT" name="blogclass" size="45" -->
				<%-- 					value="<%=(blogVO == null) ? "心得交流" : blogVO.getBlogClass()%>" /></td> --%>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>發文日期:</td> -->
			<!-- 				<td><input type="TEXT" name="xx" size="45" -->
			<%-- 					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>' --%>
			<!-- 					readonly="readonly" style="background-color: lightgrey" />  -->
			<!-- 					<input type="hidden" name="postdate" size="45" -->
			<%-- 					value='${blogVO.getPostDate()}' /></td> --%>
			<!-- 			</tr> -->

			<tr>
				<td>標題:</td>
				<td><input type="TEXT" name="title" size="45"
					value="<%=(blogVO == null) ? "瘦身" : blogVO.getTitle()%>" /></td>
			</tr>
			<tr>
				<td>內文:</td>
				<td><input type="TEXTAREA" name="text" size="45"
					value="<%=(blogVO == null) ? "減肥成功" : blogVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" id="photo2" size="45" /></td>
			</tr>
			<tr>
				<td></td>
				<td><img width="400" id="photopreview"></td>
			</tr>
			<tr>
				<td>影片:</td>
				<td><input type="file" name="video" size="45" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(blogVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

			<!-- 	<tr> -->
			<!-- 		<td>圖片:</td>> -->
			<!-- 		<td><input type="file" name="picture"/></td>> -->
			<!-- 	</tr>> -->


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	Timestamp postdate = null;
	try {
		postdate = blogVO.getPostDate();
	} catch (Exception e) {
		postdate = new Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

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

	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
// 	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>