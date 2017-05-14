/**
 * Created by Administrator on 2017/5/12.
 */
$(document).ready(function(){
	 function end(){
	     var endtime = $('#endTime').val();
	     var starttime = $('#startTime').val();
	     var start = new Date(starttime.replace("-", "/").replace("-", "/"));
	     var end = new Date(endtime.replace("-", "/").replace("-", "/"));
	     if (end <=start) {
	    	 $("#noty_message").show();
				$("#noty_message").text("结束日期不能小于开始日期！");
				$("#noty_message").css({
					'color':'#ea6f5a',
					'border-color':'#ea6f5a',
					'opacity':'1',		
				});
				$("#noty_message").fadeIn(300);
	    	 $("#noty_message").removeClass('animated  fadeOutUp');
		        $("#noty_message").addClass('animated fadeInUp');
		        setTimeout(function(){
		            $("#noty_message").removeClass('animated  fadeInUp');
		            $("#noty_message").addClass('animated fadeOutUp');
		        },2500)
	       
	         $('#endTime').val(null);
	         return false;
	     }
	     else {
	         return true;
	     }
     };
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
