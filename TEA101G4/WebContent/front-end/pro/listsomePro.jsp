<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%

String str = request.getParameter("productid");

ProService proSvc = new ProService();
   List<ProVO> list = proSvc.getAllStatusYById(str);
   pageContext.setAttribute("list",list);
   
   
   ProductService productSvc = new ProductService();
	List<ProductVO> lista = productSvc.getAll_byStatus("Y");
	pageContext.setAttribute("lista", lista);
	
   MemberVO userVO= (MemberVO) session.getAttribute("userVO");
   pageContext.setAttribute("userVO", userVO);
%>


<html>
<head>
<title>所有員工資料 - listAllPro.jsp</title>

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

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllPro.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>商品評價編號</th>
		<th>商品編號</th>
		<th>一般會員編號</th>
		<th>評論內容</th>
		<th>星級數</th>
		<th>新增日期</th>
		<th>修改日期</th>
		<th>評論狀態</th>
		
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
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prodcommid"  value="${proVO.prodcommid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="prodcommid"  value="${proVO.prodcommid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			     
			     
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>