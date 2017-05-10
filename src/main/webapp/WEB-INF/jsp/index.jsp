<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>主页</title>
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css"> 
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
  </head>
  <body>
   	<c:import url="top.jsp"></c:import>
    <div class="container">
	    <!-- 轮播图部分 -->
	    <div class="row col-xs-10 col-xs-offset-1">
		   <div class="carousel slide" id="slidershow" data-ride="carousel">
			    <!-- 设置图片轮播的顺序 -->
			    <ol class="carousel-indicators">
			        <li class="active" data-target="#slidershow" data-slide-to="0"></li>
			        <li data-target="#slidershow" data-slide-to="1"></li>
			        <li data-target="#slidershow" data-slide-to="2"></li>
			    </ol>
		    <!-- 设置轮播图片 -->
		    <div class="carousel-inner">
		        <div class="item active">
		            <a href="##"><img src="<%=basePath%>img/c01.jpg" alt=""></a>
		                <div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-success btn-sm">专题</a>
		                    	<span>贫困孤儿助养</span>
		                    </h4>
		                </div>

		        </div>
		        <div class="item">
		            <a href="##"><img src="<%=basePath%>img/c02.jpg" alt=""></a>
		        			<div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-danger btn-sm">活动</a>
		                    	<span>99公益日 一起爱</span>
		                    </h4>
		                </div>
		        </div>
		         <div class="item">
		            <a href="##"><img src="<%=basePath%>img/c04.jpg" alt=""></a>
		        			<div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-danger btn-sm">活动</a>
		                    	<span>免费午餐，小善大爱</span>
		                    </h4>
		                </div>
		        </div>
		    </div>
		    <!-- 设置轮播图片控制器 -->
		    <a class="left carousel-control" href="#slidershow" data-slide="prev">
		        <span class="fa fa-left fa-angle-left fa-3x"></span>
		    </a>
		    <a class="right carousel-control" href="#slidershow" data-slide="next">
		        <span class="fa fa-right fa-angle-right fa-3x"></span>
		    </a>
			</div>
		</div>
			<div class="row">
				<div class="col-xs-10 col-xs-offset-1">
					<div class="col-xs-8">
					<!-- 信息列表 -->
						<c:forEach items="${projects}"  begin="0" end="5" var="project">
						<div class="media">
							<h4 class="media-heading h3"> <a class="text-a" href="project/detail/${project.id }"> ${project.pro_Title}</a></h4>
							<!--活动标题  -->
							<!-- 图片 -->
				        	<a class="pull-left  fixedimg" href="project/detail/${project.id }">
				            	<img class="media-object" src="img/${project.id }.jpg">
				        	</a>
				        
				        	<div class="media-body">
				        		<!-- 活动描述 -->
				            	<div class="descript">
					            	${project.pro_Des}
								</div>
								<br>
								<p class="text-muted small">
									<!-- 活动最后更新时间 -->
									<span class="date">${project.pro_StartTime}发布</span>								
								</p>
								<p class="text-right text-muted lead1">
									<i class="fa fa-commenting-o fa-fw"></i>
									<a class="text-muted" href="project/detail?pro_id=${project.id }">详情</a>
								</p>
								
					        </div>
	    				</div>		
	    				</c:forEach>
					<!-- 重复重复 -->
					
					<!-- 重复重复 -->					
					</div>
					<div class="col-xs-4">
					<div class="list2">
						<div class="imgtop"></div>
						<div class="imgbody">
						  <dl>
							<dt>历史善款总额：</dt>
							<dd><span>${requestScope.total_money }</span>元</dd>
						</dl>
						<dl>
							<dt>历史爱心总人次：</dt>
							<dd><span>${requestScope.total_people }</span>人次</dd>
						</dl>
						</div>
					</div>
					<div class="list3">
					   <div class="title">
					   		<span class="listtitle h2">乐捐</span>
					   		<span class="more pull-right">更多</span>
					   </div>
					   <div class="body">
					   		<img src="./img/list22.jpg">
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
		<div class="style123">
		<p>公益项目咨询：gongyi@qq.com|公益项目合作：gongyi@qq.com</p>
		<p>主办：徐富豪 ，王小婷，王春晓</p>
		<p><a>意见反馈</a>|<a>网友投诉</a></p>
		</div>
		<div class="bd"></div><br>
		<div class="text-center">@版权归徐富豪 ，王小婷，王春晓所有</div>
	</div>
  </footer>  
  

  </body>
</html>
