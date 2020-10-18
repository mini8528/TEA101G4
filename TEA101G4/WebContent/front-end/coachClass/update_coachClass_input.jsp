<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coachClass.model.*"%>

<%
	CoachClassVO coachClassVO = (CoachClassVO) request.getAttribute("coachClassVO"); //coachClassServlet.java (Concroller) 存入req的coachClassVO物件 (包括幫忙取出的coachClassVO, 也包括輸入資料錯誤時的coachClassVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>教練課程資料修改 - update_coachClass_input.jsp</title>

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

img {
	max-width: 200px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>教練課程修改 - update_coachClass_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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

	<FORM METHOD="post" ACTION="coachClass.do" name="form1" enctype="multipart/form-data">
		<table>

			<tr>
				<td>課程編號：<font color=blue><b>*</b></font></td>
				<td><%=coachClassVO.getCoachClassID()%> <input type="hidden"
					name="coachClassID" value="<%=coachClassVO.getCoachClassID()%>" /></td>
			</tr>

			<tr>
				<td>新增時間：</td>
				<td><%=coachClassVO.getAddDate()%> <input type="hidden"
					name="addDate" value="<%=coachClassVO.getAddDate()%>" /></td>
			</tr>



			<tr>
				<td>會員ID:</td>
				<td><input type="TEXT" name="memberID" size="45"
					value="<%=coachClassVO.getMemberID()%>" /></td>
			</tr>

			<tr>
				<td>課程名稱:</td>
				<td><input type="TEXT" name="className" size="45"
					value="<%=coachClassVO.getCoachClassID()%>" /></td>
			</tr>
			<tr>
				<td>課程內容:</td>
				<td><input type="TEXT" name="classContext" size="45"
					value="<%=coachClassVO.getClassContext()%>" /></td>
			</tr>



			<tr>
				<td>課程開始時間:</td>
				<td><input type="datetime" name="startTime"
					value="<%=(coachClassVO == null) ? "1987-09-10 10:00:00" : coachClassVO.getStartTime()%>" /></td>
			</tr>

			<tr>
				<td>價格:</td>
				<td><input type="number" name="price" size="45"
					value="<%=(coachClassVO == null) ? "10000" : coachClassVO.getPrice()%>" /></td>
			</tr>

			<tr>
				<td>人數:</td>
				<td><input type="number" name="quantity" size="45"
					value="<%=(coachClassVO == null) ? "1" : coachClassVO.getQuantity()%>" /></td>
			</tr>

			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="address" size="45"
					value="<%=(coachClassVO == null) ? "not null" : coachClassVO.getAddDate()%>" /></td>
			</tr>

			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" id="p_file"> <br>
					<div id="preview">
						<span class="text"> 預覽 </span> <img
							src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
							width="100" height="100"></td>
				</div>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="coachClassID"
			value="<%=coachClassVO.getCoachClassID()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>

<!-- 預覽圖片 -->
<script>
window.addEventListener("load", function(){
	    var dd = document.getElementById('p_file');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview").innerHTML = "";
	      }
	    });
	});
</script>

</html>