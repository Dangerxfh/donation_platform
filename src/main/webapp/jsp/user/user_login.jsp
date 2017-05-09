<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="./css/login.css">
 	 <!-- Custom Fonts -->
    <link href="./css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">	
   	<script src="js/jquery-2.2.3.min.js"></script>
   	<script type="text/javascript">
   		$(function(){
   		//(${user});
   			//如果登陆失败失败，显示提示信息。  
   			if(${requestScope.login==false}){
   				$("#alert").show();
   			}
   			//点击input标签提示信息消失
   			$("input").click(function(){
  				$("#alert").hide();
  			});
   		});
   	</script>
  </head>

  <body>
 	<c:import url="../top.jsp"/>
 	 
    <div class="container main">
     
	    <div class="row sign">
		    <h4 class="title">
			    <a class="active">登录</a>
			    <b>.</b>
			    <a href="<%=basePath%>jsp/user/user_register.jsp">注册</a>
		    </h4>
	    </div>
        <div class="row">
            <div class="col-xs-12">
                <form action="user/login" method="post">
                    <div class="input-group form-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input class="form-control" type="text" name="user_Name" value="${user.user_Name }" placeholder="用户名" pattern="^[A-Za-z0-9_\-\u4e00-\u9fa5]{2,16}" title="中文或字母或数字2-16位" required="required">
                    </div>
                    <div class="input-group form-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input class="form-control" type="password" name="user_Pass" value="${user.user_Pass }" placeholder="密码" pattern="^[A-Za-z0-9]{2,16}" title="字母或数字2-16位" required="required">
                    </div>					
                    <div class="form-group">
                        <button class="btn btn-info btn-lg btn-block" type="submit">登录</button>
                    </div>
                </form>
                 <div class="alert alert-danger" id="alert" style="display: none;">
		    		用户名或密码错误！！！
		   	  	 </div>
              </div>
                
            </div>

    </div>


  </body>
</html>
