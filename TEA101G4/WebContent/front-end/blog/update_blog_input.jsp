<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.blog.model.*"%>

<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html lang="en">
<head>

<body id="body" class="up-scroll" data-spy="scroll"
	data-target=".element-right-sidebar">
	<jsp:include page="/front-end/header.jsp" flush="true" />

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>文章資料修改 - update_blog_input.jsp</h3> -->
<!-- 				<h4> -->
<!-- 					<a -->
<%-- 						href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a> --%>
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料修改:</h3> -->

<%-- 	<%-- 錯誤表列 --%> 
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>

<!-- 	<FORM METHOD="post" -->
<%-- 		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1" --%>
<!-- 		enctype="multipart/form-data"> -->
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>文章編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=blogVO.getBlogno()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>一般會員編號:</td> -->
<!-- 				<td><input type="TEXT" name="memberid" size="45" -->
<%-- 					value="<%=blogVO.getMemberId()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>文章分類:</td> -->
	
<!-- 				<td><select name="blogclass">	 -->
<%-- 						<option value="心得交流" ${(blogVO.blogClass eq '心得交流')?'selected':''}>心得交流</option> --%>
<%-- 						<option value="健康食譜" ${(blogVO.blogClass eq '健康食譜')?'selected':''}>健康食譜</option> --%>
<%-- 						<option value="健身影片" ${(blogVO.blogClass eq "健身影片")?'selected':''}>健身影片</option> --%>
<!-- 				</select></td> -->
				
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>發文日期:</td> -->
<!-- 				<td><input type="TEXT" name="xx" size="45" -->
<%-- 					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>' --%>
<!-- 					readonly="readonly" style="background-color: lightgrey" />  -->
<!-- 					<input type="hidden" name="postdate" size="45" -->
<%-- 					value='${blogVO.getPostDate()}' /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>標題:</td> -->
<!-- 				<td><input type="TEXT" name="title" size="45" -->
<%-- 					value="<%=blogVO.getTitle()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>內文:</td> -->
<!-- 				<td><input type="TEXT" name="text" size="45" -->
<%-- 					value="<%=blogVO.getText()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>照片:</td> -->
<!-- 				<td><input type="file" name="photo" id="photo2" /></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 				<td><img width="400" id="photopreview"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>影片:</td> -->
<!-- 				<td><input type="file" name="video" id="video2" size="45" /></td> -->
<!-- 			</tr> -->
			


<!-- 		</table> -->
<!-- 		<br> <input type="hidden" name="action" value="update"> <input -->
<%-- 			type="hidden" name="blogno" value="<%=blogVO.getBlogno()%>"> --%>
<!-- 		<input type="submit" value="送出修改"> -->
<!-- 	</FORM> -->
	
	        <!-- ====================================
———	BLOG GRID LEFT SIDEBAR
===================================== -->
        <section class="py-8 py-md-10">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-9 order-md-1">

                        <%-- 錯誤表列 --%>
                        <c:if test="${not empty errorMsgs}">
                            <font style="color: red">請修正以下錯誤:</font>
                            <ul>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li style="color: red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>

                       <jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
                        

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
                            enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">會員名稱:</label> <input type="TEXT" name="memberid2"
                                    size="45" class="form-control"
                                    value="${memberSvc.getOneMember(blogVO.memberId).getName()}" readonly />
                                     <input type="hidden" name="memberid"
                                    size="45" class="form-control"
                                    value="${blogVO.memberId}"  />

                            </div>
                            <div class="form-group check-step-gray">
                                <label for="exampleFormControlSelect1">文章分類:</label>
                                 <input type="text" name="blogclass" size="45" class="form-control"
                                    value="${blogVO.blogClass}" id="updateBlog" readonly/>
<!--                                  <select class="form-control" -->
<!--                                     name="blogclass" id="updateBlog"> -->
<%--                                     <option value="心得交流" ${(blogVO.blogClass eq '心得交流')?'selected':''}>心得交流</option> --%>
<%-- 									<option value="健康食譜" ${(blogVO.blogClass eq '健康食譜')?'selected':''}>健康食譜</option> --%>
<%-- 									<option value="健身影片" ${(blogVO.blogClass eq "健身影片")?'selected':''}>健身影片</option> --%>
<!--                                 </select> -->
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">標題:</label> <input type="TEXT" name="title"
                                    size="45" class="form-control" value="${blogVO.title}"/>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">內文:</label>
                                <textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="6"><%=blogVO.getText()%></textarea>
                               
                            </div>
                            <div></div>

                            <div class="custom-file" id="showPhoto" style="display:none"> 
                                <input type="file" name="photo" class="custom-file-input" id="photo2">
                                <label class="custom-file-label" for="customFile">Choose
                                    photo</label>
                                    <img width="400" id="photopreview" style="margin-top:30px; margin-left:220px">
                            </div>

                            <div class="custom-file" id="showVideo" style="display:none"
                                style="margin-top: 46px; padding-left: 10.4px;">
                                <input type="file" name="video" class="custom-file-input" id="video2"">
								<label class=" custom-file-label" for="customFile">Choose
                                video</label>
                            </div>

                            <div style=" margin-top:200px; margin-left:350px">
                                <input type="hidden" name="action" value="insert">
                                <input type="submit" value="submit" class="btn btn-secondary mb-2" >
                            </div>

                        </FORM>


                    </div>

                    <div class="col-md-4 col-lg-3">
                        <form class="" action="search-result.html">
                            <div class="card shadow-none bg-transparent">
                                <h3 class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
                                <div class="card-body border border-top-0 rounded-bottom">
                                    <div class="input-group border-bottom pb-3 pt-4">
                                        <input type="text" class="form-control border-0 px-1"
                                            placeholder="Enter Your Search" aria-describedby="basic-addon2"> <span
                                            class="input-group-addon" id="basic-addon2"> <input
                                                class="btn btn-sm btn-warning text-uppercase text-white shadow-sm"
                                                type="submit" value="Search">
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="card shadow-none bg-transparent">
                            <h4 class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
                            <div class="card-body border border-top-0 rounded-bottom">
                                <ul class="list-unstyled mb-0">
                                    <li class="mb-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=心得交流">心得交流</a>
                                    </li>

                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健康食譜">健康食譜</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健身影片">健身影片</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet?action=getUserSaveBlog&memberId=M001">文章收藏</a>
                                    </li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">發文</a></li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getUserBlog&memberId=M001">我的文章</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="mb-4">
                            <!-- 					<h3 class="bg-purple rounded-top font-weight-bold text-white mb-0 py-2 px-4">Related Course</h3> -->
                            <div class="border border-top-0 rounded">
                                <ul class="list-unstyled mb-0">



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	
	
	
	
	<script>
		var pictureForm = document.getElementById("photo2");
		pictureForm.addEventListener("change", function() {
			var file = this.files[0];
			var reader = new FileReader();
			reader.onload = function(event) {
				var source = event.target.result;
				var img = document.getElementById("photopreview");
				img.src = source;
			};
			reader.readAsDataURL(file);

		});
		

//         $("#updateBlog").change(function () {
//             let video = $("#updateBlog").val();

//             if (video == '健身影片') {
//                 $("#showPhoto").hide();
//                 $("#showVideo").show();
//             } else {
//                 console.log('photo');
//                 $("#showPhoto").show();
//                 $("#showVideo").hide();
//             }

//         });
        
        $(document).ready(function(){
        	let video = $("#updateBlog").val();
            if (video == '健身影片') {
                $("#showPhoto").hide();
                $("#showVideo").show();
            } else {
                console.log('photo');
                $("#showPhoto").show();
                $("#showVideo").hide();
            }
        	
        });
        

    </script>
		
	</script>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>