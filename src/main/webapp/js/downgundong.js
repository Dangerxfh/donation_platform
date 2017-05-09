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