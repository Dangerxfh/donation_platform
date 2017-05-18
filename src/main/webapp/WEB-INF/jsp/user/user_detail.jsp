<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script src="<%=basePath%>js/changecolor.js" ></script>
  </head>
   <style type="text/css">

    </style>
<body>
	 
	 <c:import url="../top.jsp"></c:import>
<div class="container">
    <div class="jumbotron">
        <img src="<%=basePath%>img/personaltop.jpg">
    </div>
    <div class="row">
	    <div class="col-xs-11 col-xs-offset-1">
	    	<div class="col-xs-3" >
	            <ul class="nav nav-tabs nav-stacked text-center" id="sidebarMenu" >
	                <li id="li1" class="active"><a href="<%=basePath%>user/detail/project">我献出的爱心</a></li>
	                <li id="li3"><a href="<%=basePath%>user/detail/money">我的捐款记录</a></li>
	                <li id="li4"><a href="<%=basePath%>user/detail/myinfo">修改个人信息</a></li>
	            </ul>
	        </div>
	        <div class="col-xs-9 right">
		        <div class="row col-xs-12">
		        	<div class="listcontent">
						<div class="title">
							 <span class="listtitle h2">
							 	我的爱心记录
							 </span>						 
			</div>
					</div>
		        </div>
		        <c:forEach items="${requestScope.person_projects}" var="project">
			        <div class="col-xs-6">
		        		<div class="content">
		        			<a href="project/detail/${project.id }">
		        				<img src="<%=basePath%>img/${project.id }.jpg">
		        			</a>
			        		<div class="conbottom">
			        			<!-- 项目标题 -->
			        			<p><a href="project/detail/${project.id }">${project.pro_Title}</a></p>
			        			<!-- 已筹 -->

		        				<p class="text-muted">已筹：<span class="text-red">${project.pro_CurNumber }</span>元</p>    		
							
								<!-- 捐款人次 -->
								<p class="text-muted left">捐款人次:<span>${project.pro_CurPeoples }</span>人次</p>
								<!-- 募捐状态: -->
								<p class="text-muted right text-right">募捐状态:<span class="text-red">${pro_status[project.pro_Status] }</span></p>
			        		</div>
		        		</div>
		        	</div>
		        </c:forEach>
	        </div>	
	      </div>
	    </div>  
	    </div>      
  <footer>
  	<div class="layout partner">
		<div class="hd"><h2>联系我们</h2></div>
		<div class="style123">
		<p>公益项目咨询：gongyi@qq.com|公益项目合作：gongyi@qq.com</p>
		<p>主办：徐富豪(20142203780) ，王小婷(20142203777)，王春晓(20142203648)</p>
		<p>班级：1403,1404</p>
		<p><a>意见反馈</a>|<a>网友投诉</a></p>	
		</div>
		<div class="bd"></div><br>
		<div class="text-center">@版权归徐富豪 ，王小婷，王春晓所有</div>
	</div>
  </footer> 
</body>

</html>
