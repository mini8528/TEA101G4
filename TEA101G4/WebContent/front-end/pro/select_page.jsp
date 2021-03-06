<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Pro: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Pro: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Cus: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPro.jsp'>List</a> all Cuss.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="pro.do" >
        <b>輸入商品編號 (如PD0001):</b>
        <input type="text" name="productid">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
   
  <li>
     <FORM METHOD="post" ACTION="pro.do" >
       <b>選擇商品評價編號:</b>
       <select size="1" name="productid">
         <c:forEach var="ProVO" items="${proSvc.all}" > 
          <option value="${PusVO.productid}">${PusVO.productid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
 
</ul>


<h3>克服管理</h3>

<ul>
  <li><a href='addPro.jsp'>Add</a> a new Pro.</li>
</ul>

</body>
</html>