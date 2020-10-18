<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachClass.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CoachClassService cocService = new CoachClassService();
	List<CoachClassVO> list = cocService.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有CoachClass資料</title>

<!-- ========================================================================================================================= -->
<!-- ========================================================================================================================= -->
<!-- ========================================================================================================================= -->



<!-- ========================================================================================================================= -->
<!-- ========================================================================================================================= -->
<!-- ========================================================================================================================= -->



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

						<h4>
							<a href="<%=request.getContextPath()%>/back-end/coachClass/select_page.jsp">回 課程 查詢</a>
						</h4>

						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<table>
							<tr>
								<th>課程ID</th>
								<th>教練ID</th>
								<th>教練</th>
								<th>課程名稱</th>
								<th>照片</th>
								<th>課程開始時間</th>
								<th>價格</th>
								<th>人數</th>
								<th>地址</th>
								<th>課程內容</th>
								<th>新增日期</th>
								<th>編輯日期</th>
								<th>修改</th>
							</tr>
							<%@ include file="page1.file"%>
							<c:forEach var="coachClassVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<jsp:useBean id="cocService1" scope="page" class="com.coachClass.model.CoachClassService" />
								<tr>
									<td>${coachClassVO.coachClassID}</td>
									<td>${coachClassVO.memberID}</td>
									<td>${cocService1.getMemberName(coachClassVO.coachClassID)}</td>
									<td>${coachClassVO.className}</td>
									<td><img
										src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
										width="100" height="100"></td>
									<td>${coachClassVO.startTime}</td>
									<td>${coachClassVO.price}</td>
									<td>${coachClassVO.quantity}</td>
									<td>${coachClassVO.address}</td>
									<td>${coachClassVO.classContext}</td>
									<td>${coachClassVO.addDate}</td>
									<td>${coachClassVO.editDate}</td>

									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="coachClassID" value="${coachClassVO.coachClassID}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
						<%@ include file="page2.file"%>


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