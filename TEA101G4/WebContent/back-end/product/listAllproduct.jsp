<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);
	String adminid = new String(adminVO.getAdminid());

	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	System.out.println("Product筆數：" + list.size());
	pageContext.setAttribute("list", list);
	SpecVO specVO = (SpecVO) request.getAttribute("specVO");
	System.out.println(specVO);
	pageContext.setAttribute("specVO", specVO);
	
	/* ProductVO productVO = (ProductVO)request.getAttribute("productVO");
	pageContext.setAttribute("productVO", productVO); */
	
	/* ProductVO productVO = new ProductVO(); */
	String productid = request.getParameter("productid");
	session.setAttribute("productid", productid);
	
	session.setAttribute("list", list);	
	
%>
<html>
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
	<img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">所有商品列表：</h3>
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
<ul> 
<li><a href='<%=request.getContextPath()%>/back-end/product/addProduct.jsp'>新增商品</a></li> 
<!-- ============================================================ --> 
<li><a href='<%=request.getContextPath()%>/back-end/spec/addspec.jsp'>新增規格</a></li> 
<!-- ============================================================ --> 
<li><a href='<%=request.getContextPath()%>/back-end/spec/listAllspec.jsp'>所有規格列表</a></li> 
<!-- ============================================================ --> 
<li>
	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
		<b>查詢商品:</b> 
			<select size="1" name="status">
				<option value="Y" ${(productVO.status=="Y")? 'selected':'' }>上架中</option>
				<option value="N" ${(productVO.status=="N")? 'selected':'' }>已下架</option>
			</select> 
			<input type="hidden" name="action" value="getSomeListByStatus">
			<input type="submit" value="送出">
	</FORM>
</li>
<!--  -->
<!--  -->	
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<li>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
		<b>關鍵字搜尋商品 (如蛋白):</b> 
			<input type="text" name="name"> 
			<input type="hidden" name="action" value="getSomeList"> 
			<input type="submit" value="送出">
	</FORM>
</li>
<li>
    <label>
      <input type="checkbox" name="CheckAll" value="核取方塊" id="CheckAll" />
      全選</label>
    </li>
</ul>
<!--  -->
<!-- ============================================================ -->  
<div class="table-responsive table-class-schedule">
<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
<input type="hidden" name="action" value="change_status_pds"> 
<input type="submit" value="多筆上/下架">
<br>
<table class="table table-bordered text-center">
<thead>
	<tr>
		<th class="bg-info text-white text-uppercase" scope="col"></th>
		<th class="bg-info text-white text-uppercase" scope="col">庫存</th>
		<th class="bg-info text-white text-uppercase" scope="col">商品ID</th>
		<th class="bg-info text-white text-uppercase" scope="col">照片</th>
		<!-- <th class="bg-info text-white text-uppercase" scope="col">照片2</th> -->
		<!-- <th class="bg-info text-white text-uppercase" scope="col">照片3</th> -->
		<th class="bg-info text-white text-uppercase" scope="col">名稱</th>
		<th class="bg-info text-white text-uppercase" scope="col">*規格</th>
		<th class="bg-info text-white text-uppercase" scope="col">價格</th>
		<!-- <th>庫存</th> join-->
		<th class="bg-info text-white text-uppercase" scope="col">品牌</th>
		<th class="bg-info text-white text-uppercase" scope="col">種類</th>
		<!-- <th>商品描述</th> -->
		<th class="bg-info text-white text-uppercase" scope="col">上架日</th>
		<th class="bg-info text-white text-uppercase" scope="col">上架者</th>
		<!-- <th>編輯日期</th>
		<th>編輯人員</th> -->
		<th class="bg-info text-white text-uppercase" scope="col">狀態</th>
		<!-- <th class="bg-info text-white text-uppercase" scope="col">瀏覽</th> -->
		<th class="bg-info text-white text-uppercase" scope="col">編輯</th>
		<!-- <th>刪除</th> -->
	</tr>
 </thead>
<%@ include file = "page1.file" %>
<c:forEach var = "productVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tbody>
<tr>
	<td class="py-5 align-middle text-muted font-weight-medium">
		<input type="checkbox" name="productid" value="${productVO.productid}">
		<%-- <input type="hidden" name="status" value="${productVO.status}">  --%>
	</td>
	<td class="py-5 align-middle text-muted font-weight-medium">
		<input type="button" value="更改" onclick="location.href='<%=request.getContextPath()%>/back-end/spec/spec.do?action=getSomeList&productid=${productVO.productid}'">
		<%-- <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/spec/spec.do">
		</FORM> --%>
	</td> 
	<td class="py-5 align-middle text-muted font-weight-medium">
	<a href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Display">${productVO.productid}</a>
	</td>
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">${productVO.productid}</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium">
		<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="50" height="50">
	</td>
	<td class="py-5 align-middle text-muted font-weight-medium">${productVO.name}</td>
<!-- ---------------------------------- -->
	<td class="py-5 align-middle text-muted font-weight-medium">
		<a href="<%=request.getContextPath()%>/back-end/spec/addOneSpec.jsp?productid=${productVO.productid}">add+</a>
		<br>
		<select size="1" name="specid">
			<c:forEach var="specVO" items="${specSvc.all}">
				<c:if test="${productVO.productid==specVO.productid}">
				<%-- <option>${specVO.specific} --%>
				<option value="${specVO.specid}">${specVO.specific}
		 		</c:if>
			</c:forEach>
		</select>
	</td>
<!-- ---------------------------------- -->	
	<td class="py-5 align-middle text-muted font-weight-medium">${productVO.price}</td>
	<%-- <td>${productVO.brandid}</td> --%>
		<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />
			<c:forEach var="brandVO" items="${brandSvc.all}">
				<c:if test="${productVO.brandid==brandVO.brandid}">
				 <td class="py-5 align-middle text-muted font-weight-medium">${brandVO.name}</td>
		 		</c:if>
			</c:forEach>
	<td class="py-5 align-middle text-muted font-weight-medium">${productVO.category}</td>
	<%-- <td>${productVO.intro}</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${productVO.adddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td class="py-5 align-middle text-muted font-weight-medium">${productVO.adminid}</td>
	<%-- <td>${productVO.editdate}</td>
	<td>${productVO.adminid2}</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium">${productVO.status}</td>
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">
		<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/product/product.do" style = "margin-bottom: 0px;">
			<input type = "submit" value = "View">
			<input type = "hidden" name = "productid" value = "${productVO.productid}">
			<input type = "hidden" name = "action" value = "getOne_For_Display">
		</FORM>
	</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium">
	<a href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Update">Edit</a>
	</td>
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">
		<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/product/product.do" style = "margin-bottom: 0px;">
			<input type = "submit" value = "Edit">
			<input type = "hidden" name = "productid" value = "${productVO.productid}">
			<input type = "hidden" name = "specid" value = "${specVO.specid}">
			<input type = "hidden" name = "action" value = "getOne_For_Update">
		</FORM>
	</td> --%>
</tr>
</tbody>
</c:forEach>
</table>
<!-- <input type="hidden" name="action" value="change_status_pds"> 
<input type="submit" value="多筆上/下架"> -->
</FORM>
</div>
<%@ include file = "page2.file" %>
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
<script>
 $(document).ready(function(){
  $("#CheckAll").click(function(){
   if($("#CheckAll").prop("checked")){//如果全選按鈕有被選擇的話（被選擇是true）
    $("input[name='productid']").each(function(){
     $(this).prop("checked",true);//把所有的核取方框的property都變成勾選
    })
   }else{
    $("input[name='productid']").each(function(){
     $(this).prop("checked",false);//把所有的核方框的property都取消勾選
    })
   }
  })
 })
</script>
</body>
</html>