<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ------------------------------------------------------------------------------ -->
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>GYMPAYZ管理員</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/css/sb-admin-2.min.css" rel="stylesheet">
  <!-- ------------------------------------------------------------------------------ -->
  
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/back-end/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">GYMPAYZ管理員</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">



      <!-- Divider -->
      <hr class="sidebar-divider">
      
    <!--       會員管理 -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMember" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>會員管理</span>
        </a>
        <div id="collapseMember" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/member/listAllMember.jsp">管理會員</a>
          </div>
        </div>
      </li>

       <!-- 最新消息 -->
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>最新消息管理</span>
        </a>
        <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/lat/listAllLat.jsp">查詢全部最新消息</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/lat/addLat.jsp">新增最新消息</a>
          </div>
        </div>
      </li>
      
      <!--============================ 商城 ============================-->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-cog"></i>
          <span>商城管理</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Utilities:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/brand/listAllbrand.jsp">品牌管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/product/listAllproduct.jsp">商品管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/ordermaster/listAllordermaster.jsp">訂單管理</a>
          </div>
        </div>
      </li>
      <!-- =================================================================== -->
<!--       部落格管理 -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapselistAllBlog" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>部落格管理</span>
        </a>
        <div id="collapselistAllBlog" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/blog/listAllBlog_Admin.jsp">文章管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/blog_mes/listAllBlog_Mes_admin.jsp">部落格留言管理</a>
    
          </div>
        </div>
      </li>
      
      <!-- 健身動作管理 -->
   
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapselistAllAction" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>健身動作管理</span>
        </a>
        <div id="collapselistAllAction" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/action/listAllAction.jsp">查詢全部健身動作</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/action/addAction.jsp">新增健身動作</a>
          </div>
        </div>
      </li>
      <!-- 課程預約管理 -->
       <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapselistAllCoachComment" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>課程預約管理</span>
        </a>
        <div id="collapselistAllCoachComment" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/coachComment/listAllCoachComment.jsp">課程留言管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/coachClass/listAllCoachClass.jsp">課程管理</a>
          </div>
        </div>
      </li>
      
         <!-- 客服 -->
   
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapselistAllCus" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>客服管理</span>
        </a>
        <div id="collapselistAllCus" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/cus/listAllCus.jsp">查詢全部管理員</a>
          </div>
        </div>
      </li>
      <!-- 商品評論管理 -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFive" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>商品評論管理</span>
        </a>
        <div id="collapseFive" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
      		<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/pro/listAllPro.jsp">查詢商品</a>
          </div>
        </div>
      </li>
      
      <!-- 管理員 -->
      
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>員工管理</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/adm/listAllEmp.jsp">查詢全部管理員</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/adm/addEmp.jsp">新增管理員工</a>
          </div>
        </div>
      </li>
      


      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->