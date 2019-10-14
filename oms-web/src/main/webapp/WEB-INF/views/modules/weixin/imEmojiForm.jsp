<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'表情修改':'表情添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	var repMsg=$("#repMsg").val();
    	if(repMsg){
    		showTip(repMsg);
    		$("#repMsg").val("");
    	}
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#input_image").val()){
            		alertx("请选择图片!");
            		return false;
            	}
                $("#btnSubmit").attr("disabled","disabled");form.submit()
                return false;
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        
    });
    function tabChange(id,ths){
        $(".tab_div").hide();
        $(".nav-child li").removeClass("active");
        $(id).show();
        $(ths).addClass("active");
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
.select2-container-multi .select2-choices{
    width: 281px;
}


   </style>
   <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/weixin/imEmoji?packageCode=${packageCode}">表情列表</a></li>
	    <li class="active"><a href="${ctx}/weixin/imEmoji/form?code=${data.code}&packageCode=${packageCode}">${not empty data.code?'表情修改':'表情添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/weixin/imEmoji/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
     	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="packageCode" name="packageCode" type="hidden" value="${packageCode}"/>
        <div id="base_div" class="tab_div">
        
             <div class="control-group">
                <label class="control-label">显示序号:</label>
                <div class="controls">
                    <input type="text" id="showIndex" name="showIndex"  maxlength="11" min="1" class="required input-xlarge number digits" value="${data.showIndex}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
                
        
          <div class="control-group">
                <label class="control-label">表情名称:</label>
                <div class="controls">
                    <input type="text" id="emojiName" name="emojiName"  maxlength="50" class="required input-xlarge" value="${data.emojiName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group">
				<label class="control-label">图片:</label>
				<div class="controls">
					<div id="image_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center;float: left;">
						<c:if test="${!empty data.emojiUrl}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.emojiUrl}" />
						</c:if>
						<c:if test="${empty data.emojiUrl}">选择图片</c:if>
					</div>
					<span class="help-inline" style="width: 10px; height: 120px; line-height: 100px; text-align: center;float: left;"><font color="red">*</font></span>  
					<input id="input_image" name="emojiUrl" type="hidden" value="${data.emojiUrl}" />
				</div>
			</div>
			
			<div class="control-group" >
			<label class="control-label">状态：</label>
			<div class="controls">
				 <select  name="status" id="status" class="input-xlarge required" >
					<option value=1 <c:if test="${data.status eq 1}">selected</c:if> >有效</option>
					<option value=0 <c:if test="${data.status eq 0}">selected</c:if>>无效</option>
				 </select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
           
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:window.location.href='${ctx}/weixin/imEmoji?packageCode=${packageCode}'">
        </div>
    </form>
    </div>
    <script>
    	/*	图片 begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=emoji',
			multi_selection : false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters : {
				mime_types : [ //只允许上传图片文件
				{
					title : "图片文件",
					extensions : "jpg,gif,png"
				} ],
				max_file_size : '5120kb',
				prevent_duplicates : false
			},
			multipart_params : {
				fileType : 'image',
				width : 1080,
				height : 1030
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded', function(uploader, files) {
			if (files.length > 0) {
				uploader.start();
			}
		});
		uploader.bind('Error', function(uploader, errObject) {
			alert(errObject.message);
		});

		uploader.bind('FileUploaded',
						function(uploader, file, responseObject) {
							var response = $.parseJSON(responseObject.response);
							$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
							$("#input_image").val("/oms" + response.url);
						});
		/*	图片 end*/
	</script>
</body>
</html>