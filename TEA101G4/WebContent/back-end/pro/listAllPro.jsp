<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>


<%
ProService proSvc = new ProService();
    List<ProVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>

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
    <FORM METHOD="post" ACTION="pro.do" >
        <b>輸入商品編號:</b>
        <input type="text" name="productid">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
 

 
 <tr>
  <th>商品評價編號</th>
  <th>商品編號</th>
  <th>一般會員編號</th>
  <th>評論內容</th>
  <th>星級數</th>
  <th>新增日期</th>
  <th>修改日期</th>
  <th>評論狀態</th>
  <th>操作</th>
 </tr>
 <%@ include file="page1.file" %> 
 <c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  
 <tr>
   
     
   <td>${proVO.prodcommid}</td>
   <td>${proVO.productid}</td>
   <td>${proVO.memberid}</td>
   <td>${proVO.commtext}</td>
   <td>${proVO.commstar}</td>
   <td>${proVO.adddate}</td>
   <td>${proVO.editdate}</td>
   <td>${proVO.status}</td>
   
   <td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pro/pro.do" style="margin-bottom: 0px;">
        <input type="submit" value="修改">
        <input type="hidden" name="prodcommid"  value="${proVO.prodcommid}">
        <input type="hidden" name="action" value="getOne_For_Update"></FORM>
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