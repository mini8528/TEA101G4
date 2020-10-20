<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	CoachClassVO coachClassVO = (CoachClassVO) request.getAttribute("coachClassVO");

MemberVO userVO= (MemberVO) session.getAttribute("userVO");
if(userVO!=null){
	System.out.println("（one_Coach_Class.jsp）當前會員= "+userVO.getMemberid());
}else{
	System.out.println("userVO 是空的 ~~~~");
};

%>
<!DOCTYPE html>
<html>
<head>
<title>教練課程新增 - addCoachClass.jsp</title>

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

img{
	max-width: 200px;
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
<jsp:include page="/front-end/header.jsp" flush="true" />

	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/coachClass/listAllCoachClass.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do" name="form1" enctype="multipart/form-data">
	
				<!-- enctype="multipart/form-data"這段沒寫, 會跑出multipart 錯誤 -->
		<table>


			
			

			<tr>
				<td>會員ID:</td>
				<td>${userVO.getMemberid()}</td>
			</tr>

			<tr>
				<td>課程名稱:</td>
				<td><input type="TEXT" name="className" size="45"
					value="<%=(coachClassVO == null) ? "not null" : coachClassVO.getClassName()%>" /></td>
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
				<td>課程內容:</td>
				<td><input type="TEXT" name="classContext" size="45"
					value="<%=(coachClassVO == null) ? "not null" : coachClassVO.getClassContext()%>" /></td>
			</tr>
			
			<tr>
				<td>課程開始時間:</td>
				<td><input type="datetime" name="startTime" 
				value="<%=(coachClassVO == null) ? "1987-09-10 10:00:00" : coachClassVO.getStartTime()%>"/></td>
			</tr>

			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" id="p_file"> <br>
					<div id="preview">
						<span class="text"> 預覽 </span>
					</div>
			</tr>





			<jsp:useBean id="cocService" scope="page" class="com.coachClass.model.CoachClassService" />

		</table>
		<br> 
		
			<input type="hidden" name="memberID" value="${userVO.getMemberid()}">
		
			<input type="hidden" name="action" value="insert"> 
			<input type="submit" value="送出新增">
	</FORM>
	
	
	<jsp:include page="/front-end/footer.jsp" flush="true" />
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