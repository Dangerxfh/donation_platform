(function ($) {
	/* 获取地址栏的参数 */
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
})(jQuery);
	$(function load() {
	    if($.getUrlParam('list')=='li1'){
	    	 $("#li2").removeClass("active");
	    	 $("#li3").removeClass("active");
	    	 $("#li4").removeClass("active");
	         $("#li1").addClass("active");
	    }
	    if($.getUrlParam('list')=='li2'){
	    	 $("#li1").removeClass("active");
	    	 $("#li3").removeClass("active");
	    	 $("#li4").removeClass("active");
	         $("#li2").addClass("active");
	    }
	    if($.getUrlParam('list')=='li3'){
	    	 $("#li1").removeClass("active");
	    	 $("#li2").removeClass("active");
	    	 $("#li4").removeClass("active");
	         $("#li3").addClass("active");
	    }
	    if($.getUrlParam('list')=='li4'){
	    	 $("#li1").removeClass("active");
	    	 $("#li2").removeClass("active");
	    	 $("#li3").removeClass("active");
	         $("#li4").addClass("active");
	    }
	});
