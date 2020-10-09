<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blog_mes.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	String flag = (String) request.getAttribute("flag");
	List<BlogVO> list = null;
	if (flag == null) {
		BlogService blogSvc = new BlogService();
		list = blogSvc.getAll();
	} else {
		list = (List<BlogVO>) request.getAttribute("list");
	}
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>


<style >

#ellipsis {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1; /*最多顯示5行*/
            -webkit-box-orient: vertical;
            white-space: normal;
        }
        
 .list-wrapper {
            padding: 15px;
            overflow: hidden;
        }

        .list-item {
            border: 1px solid #EEE;
            background: #FFF;
            margin-bottom: 10px;
            padding: 10px;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .list-item h4 {
            color: #2f3c43;
            font-size: 18px;
            margin: 0 0 5px;
        }

        .list-item p {
            margin: 0;
        }

        .simple-pagination ul {
            margin: 0 0 20px;
            padding: 0;
            list-style: none;
            text-align: center;
        }

        .simple-pagination li {
            display: inline-block;
            margin-right: 5px;
        }

        .simple-pagination li a,
        .simple-pagination li span {
            color: #666;
            padding: 5px 10px;
            text-decoration: none;
            border: 1px solid #EEE;
            background-color: #FFF;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .simple-pagination .current {  //改顏色
            color: #FFF;
            background-color: #B5B5B5;
            border-color: #B5B5B5;
        }

        .simple-pagination .prev.current,
        .simple-pagination .next.current {
            background: #2f3c43;
        }

</style>

</head>
<body id="body" class="up-scroll" data-spy="scroll"
	data-target=".element-right-sidebar">
	<jsp:include page="/front-end/header.jsp" flush="true" />
  


<!-- 				<h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- 				<table id="table-1"> -->
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<h3>所有文章資料 - listAllBlog.jsp</h3> -->
<!-- 							<h4> -->
<!-- 								<a -->
<%-- 									href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a> --%>
<!-- 							</h4> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->

<!-- 				 錯誤表列 -->
<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 		 			<font style="color: red">請修正以下錯誤:</font>  -->
<!-- 					<ul>  -->
<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 							<li style="color: red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul>  -->
<%-- 				</c:if> --%>

<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<th>文章編號</th> -->
<!-- 						<th>一般會員編號</th> -->
<!-- 						<th>文章分類</th> -->
<!-- 						<th>發文日期</th> -->
<!-- 						<th>標題</th> -->
<!-- 						<th>內文</th> -->
<!-- 								<th>影片</th> -->
<!-- 						<th>文章狀態</th> -->
<!-- 						<th>更新日期</th> -->
<!-- 						<th>照片</th> -->
<!-- 						<th>影片</th> -->
<!-- 						<th>修改</th> -->
<!-- 						<th>刪除</th> -->
<!-- 					</tr> -->
<%-- 					<%@ include file="page1.file"%> --%>
<%-- 						<c:forEach var="blogVO" items="${list}"> --%>
<%-- <%--  				<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"end="<%=pageIndex+rowsPerPage-1%>">  --%> 

<!-- 						<tr>  -->
<%-- 							<td>${blogVO.blogno}</td> --%>
<%-- 							<td>${blogVO.memberId}</td> --%>
<%-- 							<td>${blogVO.blogClass}</td> --%>
<%-- 							<td>${blogVO.postDate}</td> --%>
<%-- 							<td>${blogVO.title}</td> --%>
<%-- 							<td>${blogVO.text}</td> --%>
<%-- <%-- 										<td>${blogVO.photo}</td> --%> 
<%-- <%-- 										<td>${blogVO.video}</td> --%> 
<%-- 							<td>${blogVO.status}</td> --%>
<%-- 							<td>${blogVO.updateTime}</td> --%>
<!-- 							<td><img class="blog" width="400" -->
<%-- 								src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td> --%>
<!-- 							<td><video width="400" height="240" controls class="blog"> -->
<!-- 									<source -->
<%-- 										src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" --%>
<!-- 										type="video/mp4"> -->
<!-- 								</video></td> -->
<!-- 							<td> -->
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=request.getContextPath()%>/blog/BlogServlet" --%>
<!-- 									style="margin-bottom: 0px;"> -->
<!-- 									<input type="submit" value="修改"> <input type="hidden" -->
<%-- 										name="blogno" value="${blogVO.blogno}"> <input --%>
<!-- 										type="hidden" name="action" value="getOne_For_Update"> --> 
<!-- 								</FORM> -->
<!--  							</td>  -->
<!--  							<td>  -->
<!--  								<FORM METHOD="post"  -->
<%-- 									ACTION="<%=request.getContextPath()%>/blog/BlogServlet" --%>
<!-- 								style="margin-bottom: 0px;">  -->
<!--  									<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 										name="blogno" value="${blogVO.blogno}"> <input --%>
<!-- 										type="hidden" name="action" value="delete">  -->
<!-- 								</FORM>  -->
<!-- 							</td>  -->
<!-- 						</tr>  -->
<%-- 					</c:forEach> --%>
<!-- 				</table>  -->
<%-- 				<%@ include file="page2.file"%>  --%>


		<!-- ====================================
———	BLOG GRID LEFT SIDEBAR
===================================== -->

		<section class="py-8 py-md-10">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-9 order-md-1">
						<div class="row list-wrapper">
							<jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />

							<jsp:useBean id="blogmesSvc" scope="page"
								class="com.blog_mes.model.Blog_MesService" />
								
							<c:forEach var="blogVO" items="${list}">
							<c:if test="${blogVO.status eq 'N' }">
								<div class="col-md-6 col-lg-4 list-item">
									<div class="card">
										<div class="position-relative">
											<c:choose>
												<c:when test='${blogVO.blogClass eq "健身影片"}'>
													<video class="card-img-top" width="240" height="180"
														controls alt="Card image">
														<source
															src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}"
															type="video/mp4">
													</video>
												</c:when>
												<c:otherwise>
													<a href="blog-single.html"> <img class="card-img-top"
														width="240" height="180"
														src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"
														alt="Card image">
													</a>

												</c:otherwise>
											</c:choose>
											<div class="card-img-overlay p-0">
												<span class="badge badge-rounded badge-warning m-4"><fmt:formatDate
														value="${blogVO.postDate}" pattern="MMM" /><br> <fmt:formatDate
														value="${blogVO.postDate}" pattern="d日" /> </span>
											</div>
										</div>

										<div
											class="card-body border-top-5 px-3 rounded-bottom border-warning">
											<h3 class="card-title text-capitalize">
												<a class="text-warning d-block text-truncate"
													href="<%=request.getContextPath()%>/blog/BlogServlet?action=getOne_For_Display&blogno=${blogVO.blogno}">${blogVO.title}</a>
											</h3>
											<ul
												class="list-unstyled d-flex flex-md-column  flex-xl-row mb-1">

												<li class="mr-2"><a class="text-muted"
													href="blog-single.html"> <i class="fa fa-user mr-2"
														aria-hidden="true"></i>${memberSvc.getOneMember(blogVO.memberId).getName()}
												</a></li>

												<li class="mr-2"><i
														class="fa fa-comments-o mr-2" aria-hidden="true"></i>${blogmesSvc.getOneBlognoMes(blogVO.blogno).size() }
														Comments
												</li>
											</ul>
											<p class="mb-2" id="ellipsis" >${blogVO.text}</p>
											<a class="btn btn-link text-hover-warning pl-0"
												href="<%=request.getContextPath()%>/blog/BlogServlet?action=getOne_For_Display&blogno=${blogVO.blogno}">
												<i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i>
												Read More
											</a>
										</div>
									</div>
								</div>
								</c:if>
							</c:forEach>
							
						</div>
					</div>

					<div class="col-md-4 col-lg-3">
						<form method="post" class="" action="<%=request.getContextPath()%>/blog/BlogServlet?action=searchBlog">
							<div class="card shadow-none bg-transparent">
								<h3
									class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
								<div class="card-body border border-top-0 rounded-bottom">
									<div class="input-group border-bottom pb-3 pt-4">
										<input type="text" class="form-control border-0 px-1"
											placeholder="Enter Your Search" name="searchWord"
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
											href="<%=request.getContextPath()%>/front-end/blog/userArticle.jsp">
											我的文章
										</a>
									</li>
									<li class="mt-2">
										<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
											href="<%=request.getContextPath() %>/chat.do?memberid=${userVO.memberid}">
											聊天室
										</a>
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

			<div class="mt-6">
				<!-- ====================================
———	PAGINATION
===================================== -->

	<div id="pagination-container"></div>
<!-- 				<section class="py-5"> -->
<!-- 					<div class="container"> -->
<!-- 						<nav aria-label="Page navigation example"> -->
<!-- 							<nav aria-label="Page navigation example"> -->
<!-- 								<ul class="pagination justify-content-center align-items-center"> -->
<!-- 									<li class="page-item"><a class="page-link" href="#"> <i -->
<!-- 											class="fa fa-arrow-left mr-1" aria-hidden="true"></i> Prev -->
<!-- 									</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">1</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">...</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">15</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#"> -->
<!-- 											Next <i class="fa fa-arrow-right ml-1" aria-hidden="true"></i> -->
<!-- 									</a></li> -->
<!-- 								</ul> -->
<!-- 							</nav> -->
<!-- 						</nav> -->
<!-- 					</div> -->
<!-- 				</section> -->

			</div>
		</section>

	</div>
	<!-- element wrapper ends -->


	<script>
		$(function(){
			<c:if test="${not empty errorMsgs}"> 
				<c:forEach var="message" items="${errorMsgs}"> 
			 		alert(${message});
				</c:forEach>	
			</c:if> 
		});
		
		
		var items = $(".list-wrapper .list-item");
        var numItems = items.length;
        var perPage = 9;  //列9筆
        items.slice(perPage).hide();

        $('#pagination-container').pagination({
            items: numItems,
            itemsOnPage: perPage,
            prevText: "&laquo;",
            nextText: "&raquo;",
            onPageClick: function (pageNumber) {
                var showFrom = perPage * (pageNumber - 1);
                var showTo = showFrom + perPage;
                items.hide().slice(showFrom, showTo).show();
            }
        });
	
	</script>
	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>