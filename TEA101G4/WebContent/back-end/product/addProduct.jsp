<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO userVO = (AdminnoVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+userVO.getAdminid());};
	pageContext.setAttribute("userVO", userVO);

	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	System.out.println(productVO);
	SpecVO specVO = (SpecVO) request.getAttribute("specVO");
	System.out.println(specVO);
%>
<html>
<head>
<style>
img.preview_img {
    height: 100px;/* ??????? */
    }
</style>
</head>
<body id="page-top">
<!-- =================================Page Wrapper(include)================================= -->
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
<!-- ============================================================ -->  
<h4>
	<a href="<%=request.getContextPath()%>/back-end/product/listAllproduct.jsp">
	<img src="images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">商品內容：</h3>
<div>
<!-- ============================================================ -->         
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- ============================================================ --> 

<!-- ============================================================ --> 

<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
<table class="table table-bordered text-center">
	<thead>
		<tr>
			<td>目前管理員：</td>
			<td>
			<%=userVO.getAdminid()%>
			<input type="hidden" name="adminid"  value="<%=userVO.getAdminid()%>" />
			</td>
			<%-- <td>目前管理員：</td>
			<td>
			<select name="adminid">
			<option value="AD001" ${(productVO.adminid=="AD001")? 'selected':'' }>AD001</option>
			<option value="AD002" ${(productVO.adminid=="AD002")? 'selected':'' }>AD002</option>
			<option value="AD003" ${(productVO.adminid=="AD003")? 'selected':'' }>AD003</option>
			<option value="AD004" ${(productVO.adminid=="AD004")? 'selected':'' }>AD004</option>
			<option value="AD005" ${(productVO.adminid=="AD005")? 'selected':'' }>AD005</option>
			<option value="AD006" ${(productVO.adminid=="AD006")? 'selected':'' }>AD006</option>
			
			<select name="adminid" ${productVO.adminid}>
				<option>AD001</option>
				<option>AD002</option>
				<option>AD003</option>
				<option>AD004</option>
				<option>AD005</option>
				<option>AD006</option> 
			</select> 
			</td> --%>
		</tr>
	</thead>	
		<tr>
			<td>商品名稱：</td>
			<td><input type="TEXT" name="name" size="45" value="<%=(productVO == null) ? "新商品名稱" : productVO.getName()%>" />
			</td>
		</tr>
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
		<tr>
			<td>品牌:<font color=red><b>*</b></font></td>
			<td><select size="1" name="brandid">
					<c:forEach var="brandVO" items="${brandSvc.all}">
						<option value="${brandVO.brandid}" ${(productVO.brandid==brandVO.brandid)? 'selected':'' }>${brandVO.name}
					</c:forEach>
			</select></td>
		</tr>
<!-- ********************* -->
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<tr>
<td>規格:<font color=green><b>***</b></font></td>
		<td><input type="TEXT" name="specific" size="45" value="<%=(specVO == null) ? "2KG" : specVO.getSpecific()%>" />
		</td>
</tr>
<!-- ********************* -->
<tr>
	<td>庫存：<font color=green><b>***</b></font></td>
	<td><input type="TEXT" name="stock" size="45" value="<%=(specVO == null) ? "50" : specVO.getStock()%>" /></td>
</tr>
<!-- ********************* -->
		<tr>
			<td>種類：</td>
			<td><select name="category" ${productVO.category}> <!-- 這樣寫對嗎？ -->
					<option >健身食品</option>
					<option >健身用品</option>
			</select>
			</td>
		</tr> 

		<tr>
			<td>商品描述：</td>
			<td>
			<textarea cols="45" rows="5" name="intro"> <%=(productVO == null) ? "描述..." : productVO.getIntro()%>
			</textarea>
			</td>
		</tr>
		<!-- ********************* -->
		<tr>
			<td>單價：</td>
			<td><input type="TEXT" name="price" size="45" value="<%=(productVO == null) ? "9999" : productVO.getPrice()%>" /></td>
		</tr>
		<!-- ********************* -->
		<tr>
			<td>照片1:</td>
			<td><input type="file" name="photo1" class='p_file' multiple/> <br>
				<div id="preview_photo1">
					<span class="text"> 預覽 </span>
				</div>
		</tr>
		<!-- ********************* -->		
		<tr>
			<td>照片2:</td>
			<td><input type="file" name="photo2" class="p_file" multiple/> <br>
				<div id="preview_photo2">
					<span class="text"> 預覽 </span>
				</div>
		</tr>
		<!-- ********************* -->		
		<tr>
			<td>照片3:</td>
			<td><input type="file" name="photo3" class="p_file" multiple/> <br>
				<div id="preview_photo3">
					<span class="text"> 預覽 </span>
				</div>
		</tr>
		<!-- ********************* -->		
		
		<tr>
			<td>商品上架：</td>
			<td><select name="status"  ${productVO.status}> <!-- 這樣寫對嗎？ -->
					<option>Y</option>
					<option>N</option>
			</select>
		</tr>

	</table>
	<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="Send">
</FORM>

<!-- product section end -->
<!-- ---------------------------------------------------------------- -->
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

<script>
/* window.addEventListener("load", function(){
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
	}); */
</script>
<script>
  window.addEventListener("load", function(){
	    var list = document.getElementsByClassName('p_file');
	    for(var j=0;j<list.length;j++){
	    	list[j].addEventListener("change", function(e){
	    		let photo = $(this).attr('name');
	  	      if(this.files.length > 0 ){
	  	        let reader = new FileReader();
	  	        reader.readAsDataURL(this.files[0]);
	  	        reader.addEventListener("load", function () {
	  	          console.log(reader.result);
	  	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	  	          document.getElementById("preview_" + photo).innerHTML = hh;
	  	        });
	  	      }else{
	  	        document.getElementById("preview_" + photo).innerHTML = "";
	  	      }
	  	    });
	    }
	}); 
</script>
<% 
  Timestamp adddate = null;
  try {
	  adddate = productVO.getAdddate();
   } catch (Exception e) {
	   adddate = new Timestamp(System.currentTimeMillis());
   }
%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style> -->
<script>
	   	function ShowTime(){
  		　document.getElementById('now').innerHTML = new Date();
  		　setTimeout('ShowTime()',1000);
  		} 
	   	<%--$.dateti mepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=adddate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        }); --%>
  
  </script>
</html>