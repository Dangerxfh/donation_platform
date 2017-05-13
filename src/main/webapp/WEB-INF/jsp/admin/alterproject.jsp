<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/alertStyle.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/animate.css">			
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    <script src="<%=basePath%>js/project.js" ></script>
    <script src="<%=basePath%>js/alertJS.js" ></script>
    <script type="text/javascript">
     function doUpload() {  //Ajax异步上传图片  
	     var formData = new FormData($("#uploadForm")[0]);    
	     $.ajax({    
	          url:'<%=basePath%>admin/project/uploadimg', 
	          type: 'POST',    
	          data:  new FormData($('#uploadForm')[0]),   
	          async: false,    
	          cache: false,    
	          contentType: false,    
	          processData: false, 
	          success: function (returndata) {              
	        		location.reload();
	        		$("#noty_message").show();
					$("#noty_message").text("修改成功");
					$("#noty_message").fadeIn(3000);
					 $("#noty_message").removeClass('animated  fadeInUp');
			         $("#noty_message").addClass('animated fadeInUp');
			         $("#noty_message").css({
				
				'opacity':'1'
				
			});
			         //alert($("#noty_message").attr('class'));
			        setTimeout(function(){
			            $("#noty_message").removeClass('animated  fadeInUp');
			            $("#noty_message").addClass('animated fadeOutUp');
			        },2500); 
	          },    
	          error: function (returndata) {    
	              alert(returndata);    
	          }    
     });    
}   

$(function(){
		var flush=false;
		function myFunction() {
		 	 flush=true;
		}
		//alert(flush);
		//点击input标签提示信息消失
		$("input").click(function(){
			$(".alert").hide();
		});
		
		if(${requestScope.pro_update=='false'}  && flush==false){
			$("#noty_message").show();
			$("#noty_message").text("活动名已存在");
			$("#noty_message").css({
				'color':'#ea6f5a',
				'border-color':'#ea6f5a',
				'opacity':'1',		
			});
			$("#noty_message").fadeIn(300);
		}	
		if(${requestScope.pro_update=='true'}){
			$("#noty_message").show();
			$("#noty_message").text("修改成功");
			$("#noty_message").fadeIn(300);
		}
	        $("#noty_message").removeClass('animated  fadeOutUp');
	        $("#noty_message").addClass('animated fadeInUp');
	        setTimeout(function(){
	            $("#noty_message").removeClass('animated  fadeInUp');
	            $("#noty_message").addClass('animated fadeOutUp');
	        },2500)
});
</script>
    
  </head>
  <style type="text/css">
</style>

    
  <body onbeforeunload="return myFunction()">
	
	<c:import url="admin_top.jsp"/>
	 <div id="noty_message" style="display: none;">
	 </div>
    <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1">
	    	<div class="row">
	    		<ol class="breadcrumb" style="background: none;">
				    <li><a href="<%=basePath%>admin/project/list/all/1" class="text-a">首页</a></li>
				    <li>修改活动</li>
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
					<div class="col-xs-10 col-xs-offset-1">	
					<br>				 
				        <div class="col-xs-5">
					        <!-- 上传图片 -->
					        <form method="post" id="uploadForm" enctype="multipart/form-data">	
					        	<input type="hidden" name="id" value="${requestScope.project.id}"/>			        	
					       		<a href="javascript:;" class="a-upload">			        	
					       			<input type="file"  id="pic" name="pic" onchange="doUpload()"/>更改图片   
					       		</a>	   			  
					        </form>
					        <br>
					    
					     
	     		       	<div class="imgdiv">
					       		<img id="showpic" src="img/${requestScope.project.id }.jpg" >
					       	</div>
				       		<br>	
				        </div>
				        <div class="col-xs-7">	      
					    
				        	<form action="admin/project/updateP" method="post">	
				        		<input type="hidden" name="id" value="${requestScope.project.id}"/>
				        		<input type="hidden" name="pro_CurNumber" value="${requestScope.project.pro_CurNumber}"/>	
				        		<input type="hidden" name="pro_CurPeoples" value="${requestScope.project.pro_CurPeoples}"/>				
					        	<span>活动标题</span>
					        	<input class="form-control" name="pro_Title" pattern="^[A-Za-z0-9_\-\u4e00-\u9fa5]+" title="中文或字母或数字" value="${requestScope.project.pro_Title }" type="text">
					     	    <span>项目简介</span>
					        	<textarea rows="3" cols="30" name="pro_Des"  > ${requestScope.project.pro_Des }</textarea><br>
					        	<span>筹款目标</span>
								<input class="form-control" type="Number" name="pro_TargetNumber" value="${requestScope.project.pro_TargetNumber }">
								<span>筹款起止时间 </span>
								<p>
									<input class="form-control left" type="date" name="pro_StartTime" value="${requestScope.project.pro_StartTime }" id="startTime">
									<span>至</span>
									<input class="form-control right" type="date" name="pro_EndTime" value="${requestScope.project.pro_EndTime }" id="endTime" onblur="end()">
								</p>
								<span>执 行 方 </span>
								<input class="form-control" type="text" name="pro_Sponsor" pattern="^[A-Za-z0-9_\-\u4e00-\u9fa5]+" title="中文或字母或数字" value="${requestScope.project.pro_Sponsor }">
								<span>项目状态</span>
								<select class="form-control" name="pro_Status">
								<option selected="selected" value="donate">募捐中</option>
								<option value="execute">执行中</option>
								<option value="end">已结束</option>
								</select><br>
								<button class="btn btn-success pull-right">修改活动</button>															
				      		</form>	  
				        </div>				        
    				</div>
    		
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
