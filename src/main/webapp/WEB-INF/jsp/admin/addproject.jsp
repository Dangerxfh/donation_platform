<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>项目列表</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/addproject.css">		
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script type="text/javascript">
$(function(){
	var t; 
	$(".news").hover(function(){ 
		 clearInterval(t);
	},function(){ 
		 t = setInterval(function(){ 
				var ul = $(".list"); 
				var liHeight = ul.find("li:last").height();
				ul.animate({marginTop : liHeight +"px"},1000,function(){ 
					ul.find("li:last").prependTo(ul);
					ul.find("li:first").hide(); 
					ul.css({marginTop:0}); 
					ul.find("li:first").fadeIn(800); 
				});         
		},3000); 
	 }).trigger("mouseleave"); 
});
function end(){
            var endtime = $('#endTime').val();
            var starttime = $('#startTime').val();
            var start = new Date(starttime.replace("-", "/").replace("-", "/"));
            var end = new Date(endtime.replace("-", "/").replace("-", "/"));
            if (end < start) {
                alert('结束日期不能小于开始日期！');
                return false;
            }
            else {
                return true;
            }};


</script>
    
  </head>
  <style type="text/css">
</style>

    
  <body>
	<jsp:include page="admin_top.jsp"></jsp:include>
    <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1">
	    	<div class="row">
	    		<ol class="breadcrumb" style="background: none;">
				    <li><a href="<%=basePath%>admin/allproject.jsp" class="text-a">首页</a></li>
				    <li>添加活动</li>
				</ol>
	    	</div>
	    	<div class="row rowtop" style="margin-top: -18px;">	
		    	<div class="news">
					<ul class="list">
						<li>腾讯公益大数据揭秘：看看哪里爱心最爆棚？</li>
						<li>垃圾分类从鼓励到强制，你准备好了吗？，你准备好了吗？你准备好了吗？重要的事情说三遍</li>
						<li>爱无国度，救助来自第三世界的人们，他们需要你的帮助！</li>
						<li>世界地球日 讲好我们的地球故事</li>
						<li>99公益日 一起爱！</li>
					</ul>
				</div>
	    	 		   
	    	</div>
	    	<div class="row">
	    	<form action="">				
					<div class="col-xs-10 col-xs-offset-1">	
					<br>				 
				        <div class="col-xs-5">
				       		<div class="imgdiv">
				       			<img src="" >
				       		</div>
				       		<br>
				       		<p class="pull-right">
				        		<button class="btn btn-success">添加图片</button>
				            </p>
				        </div>
				        <div class="col-xs-7">
				        						
					        	<span>项目标题</span>
					        	<input class="form-control" type="text" placeholder="请输入项目标题" required="required">	    
					     	    <span>项目简介</span>
					        	<textarea rows="3" cols="30" required="required"></textarea><br>
					        	<span>募捐目标</span>
								<input class="form-control" type="text" placeholder="请输入你的目标" required="required">
								<span>项目起止时间 </span>
								<p>
									<input class="form-control left" type="date" required="required" id="startTime">
									<span>至</span>
									<input class="form-control right" type="date" required="required" id="endTime" onblur="end()">
								</p>
								<span>执 行 方 </span>
								<input class="form-control" type="text" placeholder="请输入项目执行方" required="required">
								<span>项目状态</span>
								<select class="form-control">
								<option value="1">募捐中</option>
								<option value="1">执行中</option>
								<option value="1">已结束</option>
								</select><br>
								<button class="btn btn-success pull-right">添加活动</button>															
				        </div>				        
    				</div>
    			</form>	  
			</div>	
		   
	    </div>
	</div>
 	<footer>
  		<div class="layout partner">
			<div class="hd"><h2>联系我们</h2></div>
			<div class="bd"></div>
			<div class="text-center">@版权归XXX所有</div>
	    </div>
    </footer>
  

  </body>
</html>
