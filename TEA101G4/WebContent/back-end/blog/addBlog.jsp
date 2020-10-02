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
<title>�峹��Ʒs�W - addBlog.jsp</title>

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
				<h3>�峹��Ʒs�W - addBlog.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>�s�W�峹:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
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
				<td>�@��|���s��:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=(blogVO == null) ? "M001" : blogVO.getMemberId()%>" /></td>
				<td><c:forEach var="memberVO" items="${memberSvc.all}">
						<c:if test="${blogVO.memberId eq memberVO.memberid}">
							${memberVO.name}
						</c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td>�峹����:</td>
				<td><select name="blogclass">
						<option value="�߱o����">�߱o����</option>
						<option value="���d����">���d����</option>
						<option value="�����v��">�����v��</option>
				</select></td>
				<!-- 				<td><input type="TEXT" name="blogclass" size="45" -->
				<%-- 					value="<%=(blogVO == null) ? "�߱o��y" : blogVO.getBlogClass()%>" /></td> --%>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>�o����:</td> -->
			<!-- 				<td><input type="TEXT" name="xx" size="45" -->
			<%-- 					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>' --%>
			<!-- 					readonly="readonly" style="background-color: lightgrey" />  -->
			<!-- 					<input type="hidden" name="postdate" size="45" -->
			<%-- 					value='${blogVO.getPostDate()}' /></td> --%>
			<!-- 			</tr> -->

			<tr>
				<td>���D:</td>
				<td><input type="TEXT" name="title" size="45"
					value="<%=(blogVO == null) ? "�G��" : blogVO.getTitle()%>" /></td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXTAREA" name="text" size="45"
					value="<%=(blogVO == null) ? "��Φ��\" : blogVO.getText()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="photo" id="photo2" size="45" /></td>
			</tr>
			<tr>
				<td></td>
				<td><img width="400" id="photopreview"></td>
			</tr>
			<tr>
				<td>�v��:</td>
				<td><input type="file" name="video" size="45" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(blogVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

			<!-- 	<tr> -->
			<!-- 		<td>�Ϥ�:</td>> -->
			<!-- 		<td><input type="file" name="picture"/></td>> -->
			<!-- 	</tr>> -->


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
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



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
// 	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
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

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
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

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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