<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>素材模版管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#label").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled","disabled");form.submit()
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
	</script>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/materialcommen/setting/">素材模版列表</a></li>
		<li class="active"><a href="${ctx}/business/materialcommen/settingForm?id=${data.id}">素材模版添加</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/business/materialcommen/settingSave" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${data.id}"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标签:</label>
			<div class="controls">
				<input name="label" value="${data.label}" maxlength="50" class="required  input-xxlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<input name="description" value="${data.description}" maxlength="50" class="required  input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<input name="sort" value="${data.sort}" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
                <label class="control-label">模版图:</label>
                <div class="controls">
					<div id="showImgAddr_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
						<c:if test="${!empty data.value}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.value}" />
						</c:if>
						<c:if test="${empty data.value}">选择图片</c:if>
					</div>
					<input id="input_showImgAddr" type="hidden" name="value" value="${data.value}">
					<span class="help-inline">建议图片尺寸640*1999</span>
				</div>
            </div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">备注:</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		var uploader_show = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'showImgAddr_btn',
			url : '${ctx}/file/upload?dirName=activity',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '10240kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image'
			}
		});
		uploader_show.init(); //初始化
		uploader_show.bind('FilesAdded',function(uploader_show,files){
			if(files.length>0){
				uploader_show.start();	
			}
		});
		uploader_show.bind('Error',function(uploader_show,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		uploader_show.bind('FileUploaded',function(uploader_show,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#showImgAddr_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#input_showImgAddr").val("/oms" + response.url);
		});
	</script>
</body>
</html>