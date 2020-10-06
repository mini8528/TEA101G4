<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_classDetail_input.jsp</title>

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

img{
	max-width: 200px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>memberID修改 - update_member_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp"><img src="images/back1.gif"
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>memberid:<font color=red><b>*</b></font></td>
				<td><%=memberVO.getMemberid()%></td>
			</tr>
			
			
			<tr>
				<td>新增日：<font color=blue><b>*</b></font></td>
				<td><%=memberVO.getAdddate()%> <input type="hidden"
					name="adddate" value="<%=memberVO.getAdddate()%>" /></td>
			</tr>
			
			<tr>
				<td>姓名:</td>
				<td><input type="TEXT" name="name" size="45" value="<%=memberVO.getName()%>" /></td>
			</tr>
			
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="account" size="45" value="<%=memberVO.getAccount()%>" /></td>
			</tr>

			<tr>
				<td>密碼:</td>
				<td><input type="password" name="password" size="45" value="<%=memberVO.getPassword()%>" /></td>
			</tr>


			<tr>
				<td>性別：</td>
				<td><select name="gender">
						<option value="F" ${(memberVO.gender=="F")? 'selected':'' }>女</option>
						<option value="M" ${(memberVO.gender=="M")? 'selected':'' }>男</option>
				</select></td>
			</tr>

			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="phone" size="45" value="<%=memberVO.getPhone()%>" /></td>
			</tr>
			
			<tr>
				<td>生日:</td>
				<td><input type="date" name="birthday" size="45" value="<%=memberVO.getPhone()%>" /></td>
			</tr>

			
			<tr>
				<td>email:</td>
				<td><input type="email" name="email" size="45" value="<%=memberVO.getEmail()%>" /></td>
			</tr>


			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo" id="p_file"> <br>
					<div id="preview">
						<span class="text"> 預覽 </span>
					</div>
			</tr>
			
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="address" size="45" value="<%=memberVO.getAddress()%>" /></td>
			</tr>

			<tr>
				<td>權限：</td>
				<td><select name="authority">
						<option value="N" ${(memberVO.gender=="N")? 'selected':'' }>N</option>
						<option value="Y" ${(memberVO.gender=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>
			
			<tr>
				<td>資歷:</td>
				<td><input type="TEXT" name="qualifications" size="45" value="<%=memberVO.getQualifications()%>" /></td>
			</tr>
			
			<tr>
				<td>專長:</td>
				<td><input type="TEXT" name="expertise" size="45" value="<%=memberVO.getExpertise()%>" /></td>
			</tr>
			
			<tr>
				<td>個人介紹:</td>
				<td><input type="TEXT" name="introduction" size="45" value="<%=memberVO.getIntroduction()%>" /></td>
			</tr>
			
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo1" id="p_file1"> <br>
					<div id="preview1">
						<span class="text"> 預覽 </span>
					</div>
			</tr>
			
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo2" id="p_file2"> <br>
					<div id="preview2">
						<span class="text"> 預覽 </span>
					</div>
			</tr>
			
			<tr>
				<td>照片:</td>
				<td><input type="file" name="photo3" id="p_file3"> <br>
					<div id="preview3">
						<span class="text"> 預覽 </span>
					</div>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="memberid"
			value="<%=memberVO.getMemberid()%>"> <input
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
	    
	    
	    
	    var dd = document.getElementById('p_file1');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file1");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview1").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview1").innerHTML = "";
	      }
	    });
	    
	    
	    
	    var dd = document.getElementById('p_file2');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file2");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview2").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview2").innerHTML = "";
	      }
	    });
	    
	    
	    
	    var dd = document.getElementById('p_file3');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file3");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview3").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview3").innerHTML = "";
	      }
	    });
	});
</script>


</script>
</html>