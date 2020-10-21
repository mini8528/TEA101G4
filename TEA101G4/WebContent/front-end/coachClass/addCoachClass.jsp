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



</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<section class="py-8 py-md-10">
  <div class="container">
  
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do" name="form1" enctype="multipart/form-data">
  
                <div class="card bg-transparent shadow-none">
						<div class="card-header card-header-lg bg-warning text-white p-6 rounded-top">
              <h4 class="font-weight-bold mb-0">新增課程</h4>
          	</div>

            <div class="card-body border border-top-0 rounded-bottom-sm p-10">
             
              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">會員ID:</label>
                  <div class="form-control border-warning rounded-sm" id="first-name" >${userVO.getMemberid()}</div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="address-1">課程名稱:</label>
                  <input class="form-control border-danger rounded-sm" type="TEXT" name="className" size="45"
					value="<%=(coachClassVO == null) ? "跑酷" : coachClassVO.getClassName()%>" />
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="city">價格:</label>
                  <input type="number" name="price" size="45" class="form-control border-purple rounded-sm"
					value="<%=(coachClassVO == null) ? "1300" : coachClassVO.getPrice()%>" />
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="state">人數:</label>
                  <input type="number" name="quantity" size="45" class="form-control border-warning rounded-sm"
					value="<%=(coachClassVO == null) ? "5" : coachClassVO.getQuantity()%>" />
                </div>
              </div>
                
                <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">地址:</label>
                  <input type="TEXT" name="address" size="45" class="form-control border-danger rounded-sm"
					value="<%=(coachClassVO == null) ? "市立美術館前的公園" : coachClassVO.getClassContext()%>" />
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="photo1">課程內容:</label>
                  <input type="TEXT"  name="classContext" size="45" class="form-control border-danger rounded-sm"
					value="<%=(coachClassVO == null) ? "帥氣的跑酷, 可以順便鍛鍊你全身的肌肉 (課程費用已包含受傷保險費用)" : coachClassVO.getClassContext()%>" />
                  </div>
                </div>

			 <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="photo2">課程開始時間:</label>
                 <input type="datetime" name="startTime"  class="form-control border-danger rounded-sm"
				value="<%=(coachClassVO == null) ? "2020-10-30 10:00:00" : coachClassVO.getStartTime()%>"/>
                  </div>
                  </div>
                  
                 <div class="row">
	                 <div class="form-group form-group-icon col-lg-6">
	                  <label for="photo3" >照片:</label>
	                  <input  type="file" name="photo" id="p_file" class="form-control border-danger rounded-sm " > <br>
						<div id="preview" >
							<span class="text"> 預覽 </span>
						</div>
	                </div>
                
                </div>
              </div>
			<jsp:useBean id="cocService" scope="page" class="com.coachClass.model.CoachClassService" />

        <table>
        	<div>
	           <input type="hidden" name="memberID" value="${userVO.getMemberid()}">
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="送出新增" class="btn btn-danger mb-2">
			</div>
		</table>
		</FORM>
      </div>
     </div>
    </div>
  </div>
</section>











	
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
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu' width='500px'>";
	          document.getElementById("preview").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview").innerHTML = "";
	      }
	    });
	});
</script>


</html>