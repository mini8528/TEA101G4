<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
</head>
<body bgcolor='white'>
<jsp:include page="header.jsp" flush="true" />
<!-- ====================================
———	SIGN-UP OR LOG-IN
===================================== -->
<section class="py-8 py-md-10">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 offset-lg-2 col-lg-4">
                <div class="mb-4 mb-sm-0">
                </div>
            </div>

            <div class="col-sm-6 col-lg-4" style="margin-left: 400px">
                <div class="mb-4 mb-sm-0">
                    <div class="bg-warning rounded-top p-2">
                        <h3 class="text-white font-weight-bold mb-0 ml-2">Login</h3>
                    </div>

                    <div class="border rounded-bottom-sm border-top-0">
                        <div class="p-3">
                            <form action="<%=request.getContextPath()%>/loginhandler" method="POST" role="form">
                       <div class="form-group form-group-icon">
                           <input type="text" class="form-control border" name="account"
                               value="${account == null ? '' : account}" placeholder="User name"
                               data-error="請輸入帳號" required="required">
                       </div>

                       <div class="form-group form-group-icon">
                           <input type="password" class="form-control border" name="password"
                               value="${password == null ? '' : password}" placeholder="Password"
                                            data-error="請輸入密碼" required="">
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-danger text-uppercase w-100">Log
                                    In</button>
                            </div>

                            <div class="form-group text-center text-secondary mb-0">
                                <a class="text-danger" href="<%=request.getContextPath() %>/front-end/member/addMember.jsp">Creat a new account</a>
                            </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<p>

<jsp:include page="footer.jsp" flush="true" />
</body>
</html>