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
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/myrecord.css">
 	 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/alterperson.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script src="<%=basePath%>js/changecolor.js" ></script>
    <script>
    $(function(){
    
    	if(${userUpdate==false}){
    		$(".alert").show();
    		$("#ondisplay").css('display','none');
    	 	$("#update").css('display','block');
    	}
    	
 		//点击input标签提示信息消失
		$("input").click(function(){
			$("#alert").hide();
		});
    	$("#qiehuan").click(function(){
    	 $("#ondisplay").css('display','none');
    	 $("#update").css('display','block');
    	});
    
    });
    </script>

  </head>
   <style type="text/css">

    </style>
<body onload="load()">
<c:import url="../top.jsp"/>
<div class="container">
    <div class="jumbotron">
        <img src="<%=basePath%>img/personaltop.jpg">
    </div>
    <div class="row">
	    <div class="col-xs-11 col-xs-offset-1">
	    	<div class="col-xs-3">
	            <ul class="nav nav-tabs nav-stacked text-center" id="sidebarMenu">
	                 <li id="li1" class="active"><a href="<%=basePath%>user/detail">我献出的爱心</a></li>
	                <li id="li2"><a href="<%=basePath%>user/detail?type=goods">我捐出的物资</a></li>
	                <li id="li3"><a href="<%=basePath%>user/detail?type=money">我的捐款记录</a></li>
	            	<li id="li4"><a href="<%=basePath%>user/detail?type=myinfo">修改个人信息</a></li>
	            </ul>
	        </div>
	        <div class="col-xs-9">
		        <div class="alert alert-danger" style="display: none;">
		        	用户名已存在！！！
		        </div>
	        	<div class="row">
		        	<div class="content col-xs-10 col-xs-offset-1">
						<div class="title">
							 <span class="listtitle h2">
							 	基本资料					
							 </span>	
						</div>
					</div>
	        	</div>
	        	<div class="row col-xs-10 col-xs-offset-1"> 
	        	<div class="panel" id="ondisplay">
				   <div class="panel-body display">
		        		<div>用户名:
		        			<p>${user.user_Name }</p>
		        		</div>
		        		<div>密码:
		        			<p>${user.user_Pass }</p>
		        		</div>
		        		<div>手机号:
		        			<p>${user.user_Phone }</p>
		        		</div>
		        		<div>性别:
		        			<p>${user.user_Sex=='female'?'女':'男' }</p>
		        		</div>
		        		<div>邮箱:
		        			<p>${user.user_Email }</p>
		        		</div>
		        		<div>地址:
		        			<p>${user.user_Address}</p>
		        		</div>  
						<button id="qiehuan" class="btn btn-success pull-right">修改信息</button>      		
		        	</div>
		        	</div>     		 	
						<div id="update" class="panel" style="display: none;">
						  <div class="panel-body">
						    <form action="user/update">
						    	<input type="hidden" name="id" value="${user.id}"/>
								用户名:<input type="text" class="form-control" name="user_Name" value="${user.user_Name}" pattern="^[A-Za-z0-9_\-\u4e00-\u9fa5]{2,16}" title="中文或字母或数字2-16位" required="required"><br>
								密码:<input type="password" class="form-control" name="user_Pass" value="${user.user_Pass }" pattern="^[A-Za-z0-9]{2,16}" title="字母或数字2-16位" required="required"><br>
								手机号:<input type="text" class="form-control" name="user_Phone" value="${user.user_Phone }" pattern="0?(13|14|15|18)[0-9]{9}" title="请输入正确的手机号码" required="required"><br>
					
								性别:<select class="form-control" name="user_Sex">
								        <option>男</option>
								        <option>女</option>
							      	</select><br>
							            邮箱:<input type="email" class="form-control" name="user_Email" value="${user.user_Email }" title="请输入正确的邮箱"><br>
								地址:<input type="text" class="form-control" name="user_Address" value="${user.user_Address }" pattern="^[\u4e00-\u9fa5]+" title="请输入中文部分地址"><br>
								<button class="btn btn-success pull-right" >修改</button>

							</form>
						  </div>	
					</div>
	        	
	        	</div>	        	

			</div>
	    </div>
        
    </div>
</div>
<footer>
    <div class="layout partner">
		<div class="hd"><h2>联系我们</h2></div>
		<div class="bd"></div>
		<div class="text-center">@版权归</div>
	</div>
</footer> 
</body>

</html>
