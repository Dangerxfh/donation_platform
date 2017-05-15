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
    <title>项目详细</title>
    <link rel="stylesheet" href="<%=basePath%>css/slide-tool.css">
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nav.css">	
 	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/detail.css">
 	<link rel="stylesheet" type="text/css" href="css/iconfont.css">
	<link rel="stylesheet" type="text/css" href="css/ishare.css">
 	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js" ></script>
    
  </head>
  <body>
      <c:import url="../top.jsp"/>
     <div class="container">
	    <div class="row col-xs-10 col-xs-offset-1" > 
	    	<!-- 项目详情 -->
	    	<div class="row">
		     	   <ol class="breadcrumb">
				        <li><a href="#" class="text-a">首页</a></li>
				        <li><a href="project/list/all/1" class="text-a">项目列表</a></li>
				        <li class="active">${requestScope.project.pro_Title}</li>
				   </ol>
		    </div>
		    <div class="row detailrow">
		    	<div class="col-xs-6">
		    		<img src="img/${requestScope.project.id }.jpg">
		    		<span class="real-icon"></span>
		    	</div>
				<div class="col-xs-6 detailinfo">
					 <!-- 捐款状态 -->
					 <div class="text-muted">状态：<span>募捐中</span></div>
					 <hr class="divider"/>
					 <!-- 目标数 -->
					 <p>
					 	<img src="<%=basePath%>img/icon1.png">
					 	<span class="text-danger">${requestScope.project.pro_TargetNumber}</span>元
					 </p>
					 <!-- 已筹数 -->
					 <p>
					 	<img src="<%=basePath%>img/icon2.png">
					 	<span class="text-danger">${requestScope.project.pro_CurNumber}</span>元
					 </p>
					 <!-- 捐款起止时间 -->
					 <div class="text-muted">时间：<span>${requestScope.project.pro_StartTime}</span>至<span>${requestScope.project.pro_EndTime}</span></div>
					 <!-- 已捐人次 -->
					 <div class="text-muted">捐款人次：<span>${requestScope.project.pro_CurPeoples}</span>人次</div>
					 <hr class="divider"/>
					 <c:if test="${requestScope.project.pro_Status=='donate'}">
						 <!-- 选择要捐的金额 -->
						 <div class="moneydiv">
							 <form action="user/donate/${requestScope.project.id}" method="post">
							 		金额：
							 	<input type="number" step="20" min="20" required="required" name="mon_Number">
							 	<button type="submit" class="btn btn-success btn-sm">我要捐钱</button>
							 </form>
						 </div>
					 </c:if>
					 <c:if test="${requestScope.project.pro_Status!='donate'}">
					 	<button class="btn btn-lg" disabled="disabled">活动已结束</button>
					 </c:if>
				</div>
		     	
		    </div>
		    <!-- 项目进展 -->
		    <div class="row brow">
				<div class="col-xs-7">
				    <ul id="myTab" class="nav nav-tabs ">
				        <li class="active"><a href="#tab1" data-toggle="tab">捐助说明</a></li>
				        <li><a href="#tab2" data-toggle="tab">项目进展</a></li>
	    		    </ul>	 
				    <div id="myTabContent" class="tab-content">
				        <div class="tab-pane fade in  active" id="tab1">
				        	<!-- 项目详情 -->
				        	<div class="detailtab">
				        		不能直接感受外面的空气，即使在夏天，都要戴上厚厚的口罩；
								不能随意离开无聊的医院，即使偶尔外出，也只能在医院附近。
								这是白血病儿童的日常。当然，还是在病情比较稳定的情况下。
								家的温暖，早已成了他们的回忆。
				        	</div>
				        </div>
				         <c:if test="${requestScope.project.pro_Status=='donate'}">
				        	<div class="tab-pane fade" id="tab2">本活动正在募捐中</div> 
				         </c:if>

				         <c:if test="${requestScope.project.pro_Status=='execute'}">
				       	 	<div class="tab-pane fade" id="tab2">本活动已结束，所募捐到的善款将交付到${requestScope.project.pro_Sponsor }手中</div> 
				       	 </c:if>
				       	 
				       	 <c:if test="${requestScope.project.pro_Status=='end'}">
				       	 	<div class="tab-pane fade" id="tab2">本活动已结束，所募捐到的善款已被${requestScope.project.pro_Sponsor }用于所需款项</div> 
				       	 </c:if>
				     </div>
				
				</div>
				<div class="col-xs-5">
					<div class="rightinfo">
						<p class="lead">发起方</p>
						<p>${requestScope.project.pro_Sponsor }</p>
					</div>
					<!-- 滚动信息列表 -->
					<div class="gundong" id="layer1">
						<div class="title">
					   		<span class="gundongtitle h2">信息跟踪</span>	
					   	</div>
					   	  <div id="box" class="maquee">
						    <ul id="cont1" class="list-group">
						   <c:forEach items="${requestScope.do_Records }" var="record">
						   		<li class="list-group-item">
									<!-- 募捐人姓名 -->
									<span>${record.userByUserId.user_Name}</span>
									<!-- 募捐的时间 -->
									在
									<span class="text-info">${record.do_Time}</span>
									<!-- 募捐的金额 -->
									捐助了
									<span class="money">${record.mon_Number}</span>元
								</li>
						   </c:forEach>
						    </ul>
						    <ul id="cont2"></ul>
						 </div>


				</div>
			</div>
	    </div>
    </div>
    </div>
    <!-- 回到顶端和分享 --> 
<div class="slide-tool">
    <ul id="btns" >
        <li id="goTopBtn" data-container="body" style="display: none;">
            <a href="javascript:void(0)" class="function-button">
                <i class="fa fa-angle-up fa-2x"></i>
            </a>
        </li>
        <li id="myTooltip1" data-container="body">
            <a id="mypopover" class="function-button" href="javascript:void(0);">
                <i class="fa fa-share"></i>
            </a>
        </li>
    </ul>
</div>
      <footer>
  		<div class="layout partner">
			<div class="hd"><h2>联系我们</h2></div>
			<div class="bd"></div>
			<div class="text-center">@版权归徐富豪 ，王小婷，王春晓所有</div>
		</div>
 	 </footer>
<div id="share" class="iShare iShare-24 " style="position: fixed;left: 90%;top: 25%;display: none;" data-sites="">
	<a href="#" class="iShare_qzone"><i class="iconfont qzone">&#xe610;</i></a>
	<a href="#" class="iShare_tencent"><i class="iconfont tencent" style="vertical-align: -2px;">&#xe608;</i></a>
	<a href="#" class="iShare_weibo"><i class="iconfont weibo">&#xe609;</i></a>
	<a href="#" class="iShare_douban"><i class="iconfont douban" style=" vertical-align: -2px;">&#xe612;</i></a>
	<a href="#" class="iShare_renren"><i class="iconfont renren">&#xe603;</i></a>
	<a href="#" class="iShare_youdaonote"><i class="iconfont youdaonote" style="vertical-align: -2px;">&#xe604;</i></a>
	<a href="#" class="iShare_facebook"><i class="iconfont facebook" style="vertical-align: 1px;">&#xe601;</i></a>
	<a href="#" class="iShare_twitter"><i class="iconfont twitter" style="vertical-align: 1px;">&#xe60a;</i></a>
	<a href="#" class="iShare_googleplus"><i class="iconfont googleplus" style="vertical-align: -1px;">&#xe60b;</i></a>
	<a href="#" class="iShare_linkedin"><i class="iconfont linkedin" style="vertical-align: 2px;">&#xe607;</i></a>
	<a href="#" class="iShare_wechat"><i class="iconfont wechat" style="vertical-align: -2px;">&#xe613;</i></a>
</div>
<script href="javascript:;" type="text/javascript">
	var iShare_config = {
		container:'.iShare',
		config:{
			title: '${requestScope.project.pro_Title}',
			 description: '${requestScope.project.pro_Des}',
			url: '<%=basePath%>project/detail/${requestScope.project.id}',
			// isAbroad: false,
			// isTitle: true,
			// initialized: false,
			WXoptions:{
				evenType: 'click',
				isTitleVisibility: true,
				isTipVisibility: true,
				tip: '这是一段测试文本',
				title: 'QR CODE'
			}
		}
	}
	
$(function() {
        //想法：滚动条一滚动就掩藏,但是就一直隐藏了，
        $(window).scroll(function() {
            $('#mypopover').popover("hide");
            $('#goTopBtn').tooltip("hide");
            $('#myTooltip1').tooltip("hide");
        });
        $(window).scroll(function() {

            if ($(this).scrollTop() != 0) {
                $('#goTopBtn').fadeIn();
            } else {
                $('#goTopBtn').fadeOut();
            }
        });
        //点击下面按钮的时候给它显示一下，反正不管之前怎样，只要点击该按钮，弹出框都会显示
        $('#goTopBtn').click(function() {
            $('body,html').animate({ scrollTop: 0 }, 800);
        })

        $('#goTopBtn').tooltip({
            title:"回到顶部",
            placement:'left',
            trigger:"hover"

        });
        $('#myTooltip1').tooltip({
            title:"分享文章",
            placement:'left',
            trigger:"hover"
        });
        $('#myTooltip1').click(function(){
            $('#mypopover').popover("show");
        });
     $('#mypopover').click(function(){
     	$('#share').toggle(800);
     
     });
          
     

    });	
	var area = document.getElementById('box');
	var cont1 = document.getElementById('cont1');
	var cont2 = document.getElementById('cont2');
	var len=cont1.getElementsByTagName('li').length;
	area.scrollTop = 0;
	if(len<=5){
	cont2.innerHTML =null;
	}
	else{
	// 克隆cont1给cont2
	cont2.innerHTML = cont1.innerHTML;
	}
	function myScroll() {
	    if(area.scrollTop >= cont1.scrollHeight) {
	        area.scrollTop = 0;
	    }else {
	        area.scrollTop++;
	    }
	}
	
	var time = 50;
	var interval = setInterval('myScroll()', time);
	
	area.onmouseover = function () {
	    clearInterval(interval);
	};
	
	area.onmouseout = function () {
	    // 继续执行之前的定时器
	    interval = setInterval('myScroll()', time);
	}; 	
 
</script>
<script href="javascript:;" type="text/javascript" src="js/iShare_tidy.js"></script> 
  </body>
</html>
