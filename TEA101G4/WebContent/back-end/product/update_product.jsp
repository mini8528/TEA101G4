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
	pageContext.setAttribute("specVO", specVO);
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
			<td>新增者：<font color=blue><b>*</b></font></td>
			<td>
			<%=productVO.getAdminid()%>
			<input type="hidden" name="adminid"  value="<%=productVO.getAdminid()%>" />
			</td>
		</tr>
		<tr>
			<td>新增時間：<font color=blue><b>*</b></font></td>
			<td>
			<%=productVO.getAdddate()%>
			<input type="hidden" name="adddate" value="<%=productVO.getAdddate()%>" />
			</td>
		</tr>
<%-- <jsp:useBean id="adminnoSvc" scope="page" class="com.adminno.model.AdminnoService" /> --%>			
		<tr>
			<td>編輯者：</td>
			<td>
			<%=userVO.getAdminid()%>
			<input type="hidden" name="adminid2"  value="<%=userVO.getAdminid()%>" />
			</td>
			<%-- <td>
			<select name="adminid2">
			<option value="AD001" ${(productVO.adminid2=="AD001")? 'selected':'' }>AD001</option>
			<option value="AD002" ${(productVO.adminid2=="AD002")? 'selected':'' }>AD002</option>
			<option value="AD003" ${(productVO.adminid2=="AD003")? 'selected':'' }>AD003</option>
			<option value="AD004" ${(productVO.adminid2=="AD004")? 'selected':'' }>AD004</option>
			<option value="AD005" ${(productVO.adminid2=="AD005")? 'selected':'' }>AD005</option>
			<option value="AD006" ${(productVO.adminid2=="AD006")? 'selected':'' }>AD006</option>
			</select> 
			</td> --%>
		</tr>
		<tr>
			<td>商品名稱：</td>
			<td><input type="TEXT" name="name" size="45" value="<%=productVO.getName()%>" />
			</td>
		</tr>
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
		<tr>
			<td>品牌:<font color=red><b>*</b></font></td>
			<td>
			<!-- <select size="1" name="brandid"> -->
					<c:forEach var="brandVO" items="${brandSvc.all}">
						<c:if test="${(productVO.brandid==brandVO.brandid)}">
						<%-- <option value="${brandVO.brandid}" ${(productVO.brandid==brandVO.brandid)? 'selected':'' }>${brandVO.name} --%>
						<input type="hidden" name="brandid"  value="${productVO.brandid}" />
						${brandVO.name}
						</c:if>
					</c:forEach>
			<!-- </select> -->
			</td>
		</tr>
<!-- ********************* -->
<%-- <jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
	<tr>
		<td>規格:<font color=green><b>***</b></font></td>
		<td><input type="TEXT" name="specific" size="45" value="<%=(specVO == null) ? "2KG" : specVO.getSpecific()%>" />
		</td>
	</tr>
<!-- ********************* -->
	<tr>
		<td>庫存：<font color=green><b>***</b></font></td>
		<td><input type="TEXT" name="stock" size="45" value="<%=(specVO == null) ? "50" : specVO.getStock()%>" /></td>
	</tr> --%>
<!-- ********************* -->
		<tr>
			<td>種類：</td>
			<td><select name="category">
				<option value="健身食品" ${(productVO.category=="健身食品")? 'selected':'' }>健身食品</option>
				<option value="健身用品" ${(productVO.category=="健身用品")? 'selected':'' }>健身用品</option>
			</select>
			</td>
		</tr>

		<tr>
			<td>商品描述：</td>
			<td>
			<textarea cols="45" rows="5" name="intro"> <%=productVO.getIntro()%>
			</textarea>
			</td>
		</tr>
		<!-- ********************* -->
		<tr>
			<td>單價：</td>
			<td><input type="TEXT" name="price" size="45" value="<%=productVO.getPrice()%>" /></td>
		</tr>
		<!-- ***************<option value="健身用品" ${(productVO.category=="健身用品")? 'selected':'' }>健身用品</option>****** -->
		<tr>
			<td>照片:</td>
			<td><input type="file" name="photo1" class="p_file"> <br>
				<div id="preview_photo1">
					<span class="text"> 預覽 </span>
					<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="100" height="100">
				</div>
		</tr>
		<!-- ********************* -->	
		<tr>
			<td>照片2:</td>
			<td><input type="file" name="photo2" class="p_file"> <br>
				<div id="preview_photo2">
					<span class="text"> 預覽 </span>
					<img src="<%=request.getContextPath()%>/back-end/product/productshow2.do?productid=${productVO.productid}" width="100" height="100">
				</div>
		</tr>
		<!-- ********************* -->	
		<tr>
			<td>照片3:</td>
			<td><input type="file" name="photo3" class="p_file"> <br>
				<div id="preview_photo3">
					<span class="text"> 預覽 </span>
					<img src="<%=request.getContextPath()%>/back-end/product/productshow3.do?productid=${productVO.productid}" width="100" height="100">
				</div>
		</tr>
		<!-- ********************* -->		
		<tr>
			<td>商品上架：</td>
			<td><select name="status">
				<option value="Y" ${(productVO.status=="Y")? 'selected':''}>Y</option>
				<option value="N" ${(productVO.status=="N")? 'selected':''}>N</option>
			</select>
		</tr>
	</thead>
</table>
	<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="productid" value="<%=productVO.getProductid()%>"> 
		<input type="submit" value="送出修改">
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

<script>
	   	function ShowTime(){
  		　document.getElementById('now').innerHTML = new Date();
  		　setTimeout('ShowTime()',1000);
  		} 
  </script>

</html>