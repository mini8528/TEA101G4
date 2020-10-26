<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lat.model.*"%>


<%
LatService latSvc = new LatService();
    List<LatVO> list = latSvc.getAll();
    pageContext.setAttribute("list",list);
  
%>


<!DOCTYPE html>
<html>
<head>
<title>所有活動資料</title>

<style>
#ellipsis {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 5; /*最多顯示5行*/
            -webkit-box-orient: vertical;
            white-space: normal;
        }


</style>


<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
</head>
<body bgcolor='white'>
<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
  <!-- Page Wrapper -->
       <div id="wrapper">
 <%@ include file="/back-end/component/sidebar.jsp" %>

       <div id="content-wrapper" class="d-flex flex-column">

    
      
         <div id="content">
       <%@ include file="/back-end/component/topbar.jsp" %>
         <!-- Begin Page Content -->
        <div class="container-fluid">

<div>
<!-- ---------------------------------------------------------------- -->       
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
 <font style="color:red">請修正以下錯誤:</font>
 <ul>
  <c:forEach var="message" items="${errorMsgs}">
   <li style="color:red">${message}</li>
  </c:forEach>
 </ul>
</c:if>


<div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
<!-- <table class="table table-bordered text-center"> -->
 
 <tr>
  <th>活動消息編號</th>
  <th>管理員編號</th>
  <th>管理員編號(更新)</th>
  <th>內容</th>
  <th>圖片1</th>
  <th>新增日期</th>
  <th>更新日期</th>
  <th>文章上傳日期</th>
  <th>編輯</th>
  <th>移除</th>
 </tr>
 <%@ include file="page1.file" %> 
 <c:forEach var="latVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  
 <tr>
   <td>${latVo.latestnewsid}</td>
   <td>${latVo.adminid}</td>
   <td>${latVo.admin2id}</td>
   <td id="ellipsis">${latVo.text}</td>
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
        <input type="hidden" name="action" value="getOne_For_Update"></FORM>
   </td>
   <td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/lat/lat.do" style="margin-bottom: 0px;">
        <input type="submit" value="刪除">
        <input type="hidden" name="latestnewsid"  value="${latVo.latestnewsid}">
        <input type="hidden" name="action" value="delete"></FORM>   
   </td>
  </tr>
  
 
 </c:forEach>
 </thead>
</table>

<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
   
   </div>
   </div>
      </div>
      <%@ include file="page2.file" %>
    </div>
  </div>
</div>
  </div>
</div>

 
<!-- ---------------------------------------------------------------- -->
</body>
</html>