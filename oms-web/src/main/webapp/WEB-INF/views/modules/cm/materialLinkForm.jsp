<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap-select/bootstrap-select.min.css">
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-select/bootstrap-select.min.js"></script>
	<script src="${ctxStatic}/bootstrap-select/defaults-zh_CN.min.js"></script>
	<script type="text/javascript">
	  
		$(document).ready(function() {
			// 不能输入中文 
			jQuery.validator.addMethod("noChinese", function(value, element) { var chrnum = /^[a-zA-Z0-9_]{0,}$/; return this.optional(element) || (chrnum.test(value)); }, "不能输入中文");
			$("#no").focus();
			
			$("#btnBack").click(function(){
				location.href = "${ctx}/cm/materialLink/";
			})
			
		});
		//js正则验证密码(字母+数字组合)
		function testPwd(pwd) {
			var reg = /^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{6,20})$/;
			if (!reg.test(pwd)) {
				alertx("密码请输入字母加数字的组合!");
				return false;
			}
			return true;
		}
	
		
	</script>
	
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	
.btn-group.bootstrap-select.show-tick.show-menu-arrow.form-control {
	margin-left: 20px;
	width: auto;
}

   </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/list">用户列表</a></li>
		
	</ul><br/>
	<form:form id="inputForm" action="${ctx}/cm/materialLink/save" method="post" enctype="multipart/form-data" class="form-horizontal">
		<input type="hidden" name="code" value="${findMaterialLinkPage.code }">
		<tags:message content="${message}"/>
		<div id="base_div" class="tab_div">  
            <div class="control-group">
                <label class="control-label">标题:</label>
                <div class="controls">
                    <span class="help-inline"><font color="red">*</font></span>
                	<input type="text" required  maxlength="100" name="title" value="${findMaterialLinkPage.title }">
                </div>
                <label class="control-label">链接:</label>
                <div class="controls">
                    <span class="help-inline"><font color="red">*</font></span>
                	<input type="text"  required name="url" value="${findMaterialLinkPage.url }">
                </div>
                <label class="control-label">图片:</label>
                <div class="controls">
                    <span class="help-inline"><font color="red">*</font></span>
                    <div id="image_btn" style="border: 1px solid #e0e6eb;width:120px;height:120px;line-height:100px;text-align:center">
				       <c:if test="${!empty findMaterialLinkPage.photo}">
				       		<img width="120px" height="120px" src="${fns:getUploadUrl()}${findMaterialLinkPage.photo}"/>
				       </c:if>
				       <c:if test="${empty user.photo}">
				                                  上传图片
				       </c:if>
				    </div>
				    <input type="hidden" id="input_image" name="photo">
                </div>
            </div>
        </div>
        <div class="buttons">
            <input id="btnSubmit" class="btn btn-primary" type="submit"/>
            <input id="btnBack" class="btn btn-primary" type="button" value="返回"/>
        </div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</form:form>
	  <script>
	  
	  
	  
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			file_data_name:'files',
			url : '${ctx}/file/batchUpload?dirName=image',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '1024kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				parentId:'linkPhotoId',
				fileType: 'image',
				width:100,
				height:100
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		uploader.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		uploader.bind('FileUploaded',function(uploader,files,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+response.url+'"/>');
				$("#input_image").val(response.url);
		});

    </script>

</body>
</html>