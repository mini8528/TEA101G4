<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lat.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
LatService latSvc = new LatService();
    List<LatVO> list = latSvc.getAll();
    pageContext.setAttribute("list",list);
    for (LatVO e : list) {
    	System.out.print(e.getLatestnewsid() + ",");
		System.out.print(e.getAdminid() + ",");
		System.out.print(e.getAdmin2id() + ",");
		System.out.print(e.getText() + ",");
		System.out.print(e.getImage() + ",");
		System.out.print(e.getAdddate() + ",");
		System.out.print(e.getUpdatetime()+",");
		System.out.print(e.getUploaddate() + ",");
		System.out.println();
    }
%>


<html>
<head>
<title>所有活動資料 - listAllLat.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
 <!-- ---------------------------------------------------------------- -->

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
          <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->       
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有活動資料  - listAllLat.jsp</h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>活動消息編號</th>
		<th>管理員編號</th>
		<th>管理員編號(更新)</th>
		<th>內容</th>
		<th>圖片1</th>
		<th>新增日期</th>
		<th>更新日期</th>
		<th>文章上傳日期</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="latVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			<td>${latVo.latestnewsid}</td>
			<td>${latVo.adminid}</td>
			<td>${latVo.admin2id}</td>
			<td>${latVo.text}</td>
			<td>
			<img  src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVo.latestnewsid}"  width="100" height="100" >
			</td>
		<td><fmt:formatDate value="${latVo.adddate}" pattern="yyyy-MM-dd"/></td>
			<td>${latVo.updatetime}</td>
			<td>${latVo.uploaddate}</td>
		

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/lat/lat.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="latestnewsid"  value="${latVo.latestnewsid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/lat/lat.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="latestnewsid"  value="${latVo.latestnewsid}">
			     <input type="hidden" name="action" value="delete"></FORM>   
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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