 function doUpload() {  //Ajax异步上传图片  
	     var formData = new FormData($("#uploadForm")[0]);    
	     $.ajax({    
	          url:'<%=basePath%>UploadImage', 
	          type: 'POST',    
	          data:  new FormData($('#uploadForm')[0]),   
	          async: false,    
	          cache: false,    
	          contentType: false,    
	          processData: false,    
	          success: function (returndata) {    
	              document.getElementById("showpic").src="${PIC}";/*这是预览图片用的，自己在文件上传表单外添加*/  
	          },    
	          error: function (returndata) {    
	              alert(returndata);    
	          }    
     });    
}
   
 function end(){
     var endtime = $('#endTime').val();
     var starttime = $('#startTime').val();
     var start = new Date(starttime.replace("-", "/").replace("-", "/"));
     var end = new Date(endtime.replace("-", "/").replace("-", "/"));
     if (end <=start) {
         alert('结束日期不能小于开始日期！');
         $('#endTime').val(null);
         return false;
     }
     else {
         return true;
     }};