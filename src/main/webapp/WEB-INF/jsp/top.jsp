<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">	
	 <script type="text/javascript">
    	$(function(){
    		//如果用户不为空
    		if(${sessionScope.user!=null}){
    			//将用户名显示在页面
    			$("#login").text('${sessionScope.user.user_Name}');
    			
    			if(${sessionScope.user.user_Name!='admin'})
    			//将“个人中心”显示在页面
    			$("#personal").show();
    			
    			if(${sessionScope.user.user_Name=='admin'})
    			//将“管理活动”显示在页面
    			$("#adminoperate").show();
    			
    			//将“注销”文字显示在页面
    			$("#logout").show();
    			
    			//将用户名的链接设置为用户详情页面
    			$("#login").attr('href','user/detail/projects');
    		}
    	});
    </script>
  </head>
  
  <body>
   		  <div class="navbar navbar-default navbar-fixed-top">

        <div class="navbar-header">
           　        	<a href="##" class="navbar-brand">爱心公益网 </a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="##">公益首页</a></li>
            <li><a href="project/list/all/1">公益活动</a></li>     
            <li><a href="user/detail/projects" id="personal" style="display: none;">个人中心</a></li>
            <li><a href="admin/project/list/all/1" id="adminoperate" style="display: none;">管理活动</a></li>
        </ul>

        <div class="navbar-right text-danger">
        	欢迎，<span id="user_Name"></span><a href="user/tologin" id="login">请登录</a>&nbsp;&nbsp;<span><a href="user/logout" class="text-danger" id="logout" style="display: none;">注销</a></span>
        </div>
    </div>
  </body>
</html>
