<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachClass.model.*"%>

<html>
<head>
<title>GYMPAYZ CoachClass: Home</title>


</head>
<body bgcolor='white'>


<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
  <!-- Page Wrapper -->
  <div id="wrapper">
	<%@ include file="/back-end/component/sidebar.jsp" %>


    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      
      <div id="content">
		

       <%@ include file="/back-end/component/topbar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">搜尋 - 課程查詢 (MVC)</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->         




	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul><h4>
		<li><a href='listAllCoachClass.jsp'>列出所有課程</a><br><br></li>

		<jsp:useBean id="cocService" scope="page"
			class="com.coachClass.model.CoachClassService" />

		<li>
			<FORM METHOD="post" ACTION="coachClass.do">
				<b>輸入coachClassID (如COC00001):</b> <input type="text"
					name="coachClassID"> <input type="hidden" name="action"
					value="getOne_For_Display"> <input type="submit" value="送出">
			</FORM>
		</li>



		<li>
			<FORM METHOD="post" ACTION="coachClass.do">
				<b>選擇coachClassID:</b> <select size="1" name="coachClassID">
					<c:forEach var="coachClassVO" items="${cocService.all}">
						<option value="${coachClassVO.coachClassID}">${coachClassVO.coachClassID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</h4>
	</ul>




<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

 
<!-- ---------------------------------------------------------------- -->




</body>
</html>