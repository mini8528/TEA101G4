<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coachComment.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CoachCommentService ccService = new CoachCommentService();
    List<CoachCommentVO> list = ccService.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有CoachComment資料 - listAllCoachComment.jsp</title>


 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  
  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/css/sb-admin-2.min.css" rel="stylesheet">
  
  <style >
      
 .list-wrapper {
            padding: 15px;
            overflow: hidden;
        }

        .list-item {
            border: 1px solid #EEE;
            background: #FFF;
            margin-bottom: 10px;
            padding: 10px;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .list-item h4 {
            color: #2f3c43;
            font-size: 18px;
            margin: 0 0 5px;
        }

        .list-item p {
            margin: 0;
        }

        .simple-pagination ul {
            margin: 0 0 20px;
            padding: 0;
            list-style: none;
            text-align: center;
        }

        .simple-pagination li {
            display: inline-block;
            margin-right: 5px;
        }

        .simple-pagination li a,
        .simple-pagination li span {
            color: #666;
            padding: 5px 10px;
            text-decoration: none;
            border: 1px solid #EEE;
            background-color: #FFF;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .simple-pagination .current {  //改顏色
            color: #FFF;
            background-color: #B5B5B5;
            border-color: #B5B5B5;
        }

        .simple-pagination .prev.current,
        .simple-pagination .next.current {
            background: #2f3c43;
        }

</style>
  

</head>

<body id="page-top">
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
    
<div>




<h1 class="h3 mb-2 text-gray-800">會員評論管理</h1>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table  class="table table-bordered list-item" id="dataTable" width="100%" cellspacing="0">
	<tr>
		<th>課程編號</th>
		<th>教練ID</th>
		<th>會員ID</th>
		<th>評論內容</th>
		<th>評分</th>
		<th>新增日期</th>
		<th>編輯日期</th>
		<th>狀態</th>
		<th>修改狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="coachCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coachCommentVO.coachCommentID}</td>
			<td>${coachCommentVO.memberID}</td>
			<td>${coachCommentVO.memberID2}</td>
			<td>${coachCommentVO.commText}</td>
			<td>${coachCommentVO.commStar}</td>
			<td><fmt:formatDate value="${coachCommentVO.addDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${coachCommentVO.editDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${coachCommentVO.status}</td>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachComment/coachComment.do" style="margin-bottom: 0px;">
										<td><select name="status">
												<option value="N"
													${coachCommentVO.status=="N"? 'selected' : ''}>隱藏</option>
												<option value="Y"
													${coachCommentVO.status=="Y"? 'selected' : ''}>顯示</option>

										</select></td>
										
						  <td>
						     <input type="submit" value="修改" >
						     <input type="hidden" name="coachCommentID"  value="${coachCommentVO.coachCommentID}">
						     <input type="hidden" name="action"	value="Comment_Status"">
						 </td>
	             	     </FORM>
			
			
			
			
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>




<div id="pagination-container"></div>
  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%=request.getContextPath()%>/back-assets/js/sb-admin-2.min.js"></script>
 

</body>
</html>