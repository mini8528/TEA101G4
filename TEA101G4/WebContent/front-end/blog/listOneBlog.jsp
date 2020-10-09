<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blog_mes.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
// 	List<Blog_MesVO> list = (List<Blog_MesVO>) request.getAttribute("list");
// 	pageContext.setAttribute("list", list);
%>




<!DOCTYPE html>
<html lang="en">
<head>

</head>

<body id="body" class="up-scroll" data-spy="scroll"
	data-target=".element-right-sidebar">
<jsp:include page="/front-end/header.jsp" flush="true" />

		<!-- ====================================
———	BLOG DETAILS
===================================== -->

		<section class="py-8 py-md-10">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-9 order-md-1">
						<div class="card">
							<div class="position-relative">
							
								<div>
								<span class="badge badge-rounded badge-warning" style=" display: inline-block; margin-bottom: 10px"><fmt:formatDate
													value="<%=blogVO.getPostDate()%>" pattern="MMM" /><br>
												<fmt:formatDate value="<%=blogVO.getPostDate()%>"
													pattern="d日" /></span></li>
								
								<c:if test="${userVO.memberid == blogVO.memberId }">
								<div class="dropdown" style="display: inline-block; margin-left:650px; margin-bottom: 10px">
								  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" style="background-color: #B5B5B5; border: #B5B5B5" aria-expanded="false" ><i class="fa fa-pencil-square-o" style="margin-right: 10px; font-size:20px"></i></a>
								  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								    <a class="dropdown-item" href="<%=request.getContextPath()%>//blog/BlogServlet?action=getOne_For_Update&blogno=${blogVO.blogno}" style="font-size:20px">編輯</a>
								    <a class="dropdown-item" href="<%=request.getContextPath()%>//blog/BlogServlet?action=blog_Hide&blogno=${blogVO.blogno}" style="font-size:20px">刪除</a>
								  </div>
								</div>
								</c:if>
								</div>
								
								
								
<%-- 								<c:if test="${userVO.memberid == blogVO.memberId }"> --%>
<!-- 								<div class="dropdown" style="display: inline-block; margin-left:750px"> -->
<!-- 								  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">... </a> -->
<!-- 								  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink"> -->
<%-- 								    <a class="dropdown-item" href="<%=request.getContextPath()%>//blog/BlogServlet?action=getOne_For_Update&blogno=${blogVO.blogno}">編輯</a> --%>
<%-- 								    <a class="dropdown-item" href="<%=request.getContextPath()%>//blog/BlogServlet?action=blog_Hide&blogno=${blogVO.blogno}">刪除</a> --%>
<!-- 								  </div> -->
<!-- 								</div> -->
<%-- 								</c:if><br><br> --%>
								
								<c:choose>
									<c:when test="${video == null}">
										<img class="card-img-top"
											src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"
											alt="Card image cap"  width="700">
									</c:when>
									<c:otherwise>
										<video class="card-img-top" width="847" controls
											autoplay="autoplay" class="blog">
											<source
												src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}"
												type="video/mp4">
										</video>
									</c:otherwise>
								</c:choose>
								<!-- 								<div class="card-img-overlay"> -->
<%-- 								<span class="badge badge-rounded badge-warning"><fmt:formatDate --%>
<%-- 										value="<%=blogVO.getPostDate()%>" pattern="MMM" /><br> <fmt:formatDate --%>
<%-- 										value="<%=blogVO.getPostDate()%>" pattern="d日" /></span> --%>
								<!-- 								</div> -->
							</div>

							<%-- 							<jsp:include page="../blog_mes/listAllBlog_Mes.jsp" flush="true" /> --%>

							<jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
							<jsp:useBean id="bloglikesSvc" scope="page"
								class="com.blog_likes.model.Blog_LikesService" />
							<jsp:useBean id="blogsaveSvc" scope="page"
								class="com.blog_save.model.Blog_SaveService" />

							<div
								class="card-body border-top-5 px-3 rounded-bottom border-warning">
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${bloglikesSvc.getRecordStatus(blogVO.blogno, userVO.memberid) == null}"> --%>
<!-- 										<span id="showLike"> -->
<!-- 											<svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-suit-heart" id="heartlike" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> -->
<!-- 	  											<path fill-rule="evenodd" d="M8 6.236l.894-1.789c.222-.443.607-1.08 1.152-1.595C10.582 2.345 11.224 2 12 2c1.676 0 3 1.326 3 2.92 0 1.211-.554 2.066-1.868 3.37-.337.334-.721.695-1.146 1.093C10.878 10.423 9.5 11.717 8 13.447c-1.5-1.73-2.878-3.024-3.986-4.064-.425-.398-.81-.76-1.146-1.093C1.554 6.986 1 6.131 1 4.92 1 3.326 2.324 2 4 2c.776 0 1.418.345 1.954.852.545.515.93 1.152 1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/> -->
<!-- 											</svg> -->
<!-- 										</span> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<span id="showLike"> -->
<!-- 											<svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-suit-heart-fill" id="heartlike" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> -->
<!-- 												 <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/> -->
<!-- 											</svg> -->
<!-- 										</span> -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<!-- 								<span id="showLikeCount"> -->
<%-- 									${bloglikesSvc.getBlogLikes(blogVO.blogno).size()} --%>
<!-- 								</span> -->
<%-- 								<svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-bookmark-heart" id="collection" name="${blogVO.blogno}" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> --%>
<!--   									<path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/> -->
<!--   									<path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/> -->
<!-- 								</svg> -->
<%-- 								<h3 class="card-title text-warning mb-4" style="margin-left:50px"><%=blogVO.getTitle()%></h3> --%>
										
								
								
								<span style="display: inline-block">
								<h3 class="card-title text-warning mb-4"><%=blogVO.getTitle()%></h3>	
								</span>
								<c:if test="${userVO != null}">
								<c:choose>
									<c:when test="${bloglikesSvc.getRecordStatus(blogVO.blogno, userVO.memberid) == null}">
										<span id="showLike" span style="display: inline-block; margin-left:570px">
											<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart" id="heartlike" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg" >
	  											<path fill-rule="evenodd" d="M8 6.236l.894-1.789c.222-.443.607-1.08 1.152-1.595C10.582 2.345 11.224 2 12 2c1.676 0 3 1.326 3 2.92 0 1.211-.554 2.066-1.868 3.37-.337.334-.721.695-1.146 1.093C10.878 10.423 9.5 11.717 8 13.447c-1.5-1.73-2.878-3.024-3.986-4.064-.425-.398-.81-.76-1.146-1.093C1.554 6.986 1 6.131 1 4.92 1 3.326 2.324 2 4 2c.776 0 1.418.345 1.954.852.545.515.93 1.152 1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/>
											</svg>
										</span>
									</c:when>
									<c:otherwise>
										<span id="showLike" span style="display: inline-block; margin-left:570px ">
											<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart-fill" id="heartlike" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
												 <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
											</svg>
										</span>
									</c:otherwise>
								</c:choose>
								<span id="showLikeCount" span style="display: inline-block; margin-left:5px">
									${bloglikesSvc.getBlogLikes(blogVO.blogno).size()}
								</span>
								
								<c:choose>
								<c:when test="${blogsaveSvc.getBlogSaveStatus(blogVO.blogno, userVO.memberid) == null}">
									<span span id="showSave" style="display: inline-block; margin-left:30px">
										<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart" id="collection" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  									<path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
	  									<path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
										</svg>
									</span>
								</c:when>
								<c:otherwise>
									<span span id="showSave" style="display: inline-block; margin-left:30px">
									<svg  width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart-fill" id="collection" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  								<path fill-rule="evenodd" d="M4 0a2 2 0 0 0-2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4zm4 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
									</svg>
									</span>
								</c:otherwise>
								</c:choose>
								</c:if>
<!-- 								<svg id="showSave" width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart-fill" id="collection" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> -->
<!-- 	  								<path fill-rule="evenodd" d="M4 0a2 2 0 0 0-2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4zm4 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/> -->
<!-- 									</svg> -->
								

								
										<ul class="list-unstyled d-flex mb-6">

											<li class=""><a class="text-muted d-inline-block mr-3"
												href="#user"><i class="fa fa-user mr-2"
													aria-hidden="true"></i>${memberSvc.getOneMember(blogVO.memberId).getName()} </a></li>
											<li class="mr-3"><a class="text-muted d-inline-block"
												href="#comments"><i class="fa fa-comments-o mr-1"
													aria-hidden="true"></i>${list.size()} Comments</a></li>	
										</ul>
									

								<p class="card-text text-justify mb-6"><%=blogVO.getText()%></p>


								<div class="">
									<ul class="list-inline d-flex mb-0">
										<li class="mr-2"><a
											class="icon-rounded-circle-small bg-warning"
											href="javascript:void(0)"> <i
												class="fa fa-facebook text-white" aria-hidden="true"></i>
										</a></li>
										<li class="mr-2"><a
											class="icon-rounded-circle-small bg-danger"
											href="javascript:void(0)"> <i
												class="fa fa-twitter text-white" aria-hidden="true"></i>
										</a></li>
										<li class="mr-2"><a
											class="icon-rounded-circle-small bg-success"
											href="javascript:void(0)"> <i
												class="fa fa-google-plus text-white" aria-hidden="true"></i>
										</a></li>
										<li class="mr-2"><a
											class="icon-rounded-circle-small bg-info"
											href="javascript:void(0)"> <i
												class="fa fa-pinterest-p text-white" aria-hidden="true"></i>
										</a></li>
										<li class=""><a
											class="icon-rounded-circle-small bg-purple"
											href="javascript:void(0)"> <i
												class="fa fa-vimeo text-white" aria-hidden="true"></i>
										</a></li>
									</ul>
								</div>
							</div>
						</div>
						
						<jsp:useBean id="blogmesVO" scope="page"
							class="com.blog_mes.model.Blog_MesVO" />
						<jsp:useBean id="blogmesSvc" scope="page"
							class="com.blog_mes.model.Blog_MesService" />

					
						<div class="bg-light shadow-sm rounded px-3 pt-3 pb-6 mb-4">
							<h3 class="mb-4 text-danger font-weight-bold">${blogmesSvc.getOneBlognoMes(blogVO.blogno).size()}
								Comments
							</h3>
<!-- 							action=insert -->
							<c:forEach var="blog_MesVO" items="${list}"> 
								<c:if test="${blog_MesVO.status == 'N' }">
								<div class="media py-1">
									<div class="mr-4">
										<img class=" rounded-circle"
											src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img1.jpg"
											alt="User Image">
									</div>

												<div class="media-body">
													<span
														class="mb-3 font-weight-medium text-muted d-inline-block">${memberSvc.getOneMember(blog_MesVO.memberId).getName()}</span>
													<time class="d-block text-muted font-size-13 mb-3">${blog_MesVO.postDate}</time>
													<p class="font-size-13 line-hight-21">${blog_MesVO.text}</p>

												</div>
								</div>
								</c:if>
							</c:forEach>


						</div>
					
					
						<div class="bg-light shadow-sm rounded p-3">
							<h3 class="mb-4 text-danger font-weight-bold">Leave A
								Comment</h3>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet"
								name="form1" enctype="multipart/form-data">
								<!-- 							<form> -->
								<div class="row">
									<div class="col-12 col-md-4">
										<div class="form-group form-group-icon">
											<i class="fa fa-user"></i> <input type="text" name="memberid2"
												class="form-control border-warning" value="${memberSvc.getOneMember(userVO.memberid).getName()}"
												 readonly>
												 <input type="hidden" name="memberid"
												class="form-control border-warning" value="${userVO.memberid}"
												 readonly>
												
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col">
										<div class="form-group form-group-icon">
											<i class="fa fa-comments "></i>
											<textarea class="form-control border-info" name="text"
												id="usermessage" placeholder="Write message" rows="4" required=""></textarea>

										</div>
									</div>
								</div>

								<input type="hidden" name="action" value="insert"> <input
									type="hidden" name="blogno" value="<%=blogVO.getBlogno()%>">
								<input type="submit" id="msgsubmit"
									class="btn btn-danger text-uppercase" value="submit"
									style="background: #3E6B7E; border-color: #3E6B7E;">

								<!-- 								<button class="btn btn-danger text-uppercase">submit</button> -->

							</FORM>
						</div>
					</div>

					<div class="col-md-4 col-lg-3">
						<form class="" action="search-result.html">
							<div class="card shadow-none bg-transparent">
								<h3
									class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
								<div class="card-body border border-top-0 rounded-bottom">
									<div class="input-group border-bottom pb-3 pt-4">
										<input type="text" class="form-control border-0 px-1"
											placeholder="Enter Your Search"
											aria-describedby="basic-addon2"> <span
											class="input-group-addon" id="basic-addon2"> <input
											class="btn btn-sm btn-warning text-uppercase text-white shadow-sm"
											type="submit" value="Search">
										</span>
									</div>
								</div>
							</div>
						</form>

						<div class="card shadow-none bg-transparent">
							<h4
								class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
							<div class="card-body border border-top-0 rounded-bottom">
								<ul class="list-unstyled mb-0">
									<li class="mb-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=心得交流">心得交流</a></li>

									<li class="my-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健康食譜">健康食譜</a></li>
									<li class="my-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健身影片">健身影片</a></li>
									<li class="my-2">
												<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
													href="<%=request.getContextPath()%>/front-end/blog_save/listAllBlog_Save.jsp">
													文章收藏
												</a>
									</li>
									<li class="mt-2">
											<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
												href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">
												發文
											</a>
									</li>
									<li class="mt-2">
										<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
											href="<%=request.getContextPath()%>/blog/BlogServlet?action=getUserBlog&memberId=${userVO.memberid}">
											我的文章
										</a>
									</li>
								</ul>
							</div>
						</div>
					
					</div>
				</div>
			</div>
		</section>

	</div>
	<!-- element wrapper ends -->

	<script>
	$(function(){
		likeChange();
		saveChange()
	});
	
		$("textarea#usermessage").on("keydown", function(event) {
			if (event.which == 13) {
				$("input#msgsubmit").click();
			}
			;
		});
		
		
			
			
		function likeChange(){
			
			$('#showLike').on('click', function(){
				
				var status = $('#heartlike').attr('name');
				console.log(status);
				
				$.ajax({
				     url: "<%=request.getContextPath()%>/blog_Likes/Blog_LikesServlet",
				     type: "get",
				     data: { 
				       action: 'clickheart', 
				       blogno: '${blogVO.blogno}',
				       memberid:'${userVO.memberid}',
				       status: status
				      },
				     dataType: 'json',
				     success: function(res){
				    	console.log(res.status);
				    	if('Y' == res.status){
				    		var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart-fill" id="heartlike" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/></svg>';
				    		$('#showLike').html( htmlString );
						}else{
							var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-suit-heart" id="heartlike" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M8 6.236l.894-1.789c.222-.443.607-1.08 1.152-1.595C10.582 2.345 11.224 2 12 2c1.676 0 3 1.326 3 2.92 0 1.211-.554 2.066-1.868 3.37-.337.334-.721.695-1.146 1.093C10.878 10.423 9.5 11.717 8 13.447c-1.5-1.73-2.878-3.024-3.986-4.064-.425-.398-.81-.76-1.146-1.093C1.554 6.986 1 6.131 1 4.92 1 3.326 2.324 2 4 2c.776 0 1.418.345 1.954.852.545.515.93 1.152 1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/></svg>';
				    		$('#showLike').html( htmlString );
						}
				    	$('#showLikeCount').text( res.likescount );
				     },
				     error: function(res){
				       alert("出現問題!");
				     }
				 });
			});
		}
		
		
		function saveChange(){
			
			$('#showSave').on('click', function(){
				
				var status = $('#collection').attr('name');
				console.log(status);
				
				$.ajax({
				     url: "<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet",
				     type: "get",
				     data: { 
				       action: 'clickCollection', 
				       blogno: '${blogVO.blogno}',
				       memberid:'${userVO.memberid}',
				       status: status
				      },
				     dataType: 'json',
				     success: function(res){
				    	console.log(res.status);
				    	if('Y' == res.status){
				    		var htmlString = '<svg id="showSave" width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart-fill" id="collection" name="Y" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4 0a2 2 0 0 0-2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4zm4 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/></svg>';
				    		$('#showSave').html( htmlString );
						}else{
							var htmlString = '<svg width="2.5em" height="2.5em" viewBox="0 0 16 16" class="bi bi-bookmark-heart" id="collection" name="N" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/><path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>';
				    		$('#showSave').html( htmlString );
						}
				     },
				     error: function(res){
				       alert("出現問題!");
				     }
				 });
			});
		}
				
	</script>
	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>

</html>