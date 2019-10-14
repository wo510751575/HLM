<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'服务人员作品修改':'服务人员作品添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#personListSec').select2();
    	$('#shopListSec').select2();
    	
    	$("[name='shopNo']").change(function(){
    		$("#shopName").val($(this).find("option:selected").text());
        });
        $("[name='shopNo']").change();
        
    	$("[name='personNo']").change(function(){
    		$("#personName").val($(this).find("option:selected").text());
        });
        $("[name='personNo']").change();
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#personListSec").val()){
            		alertx("请选择服务人员!");
            		return false;
            	}
            	if(!$("#shopListSec").val()){
            		alertx("请选择终端!");
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
        <li><a href="${ctx}/member/servicePerson">服务人员作品列表</a></li>
	    <li class="active"><a href="${ctx}/member/servicePersonProduct/form?code=${data.code}">${not empty data.code?'服务人员作品修改':'服务人员作品添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/member/servicePersonProduct/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="personName" name="personName" type="hidden" value="${data.personName}"/>
        <input id="shopName" name="shopName" type="hidden" value="${data.shopName}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <div id="base_div" class="tab_div">                   
                 
                         
               <div class="control-group">
                <label class="control-label">选择服务人员:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="personNo" id="personListSec"  style="width:220px">
			 			<c:forEach items="${servicePersons}" var="item">
			 			<option class="MaterialInfo" value="${item.personNo}"<c:if test="${item.personNo eq data.personNo}">selected="selected"</c:if>>${item.personName}</option>
			 			</c:forEach>
					</select>
					</div>
                </div>
            </div>
               <div class="control-group">
                <label class="control-label">选择终端:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="shopNo" id="shopListSec"  style="width:220px">
			 			<c:forEach items="${shops}" var="item">
			 			<option class="MaterialInfo" value="${item.shopNo}"<c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
			 			</c:forEach>
					</select>
					</div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">成交价:</label>
                <div class="controls">
                    <input type="text" id="price" name="price"  maxlength="20" class="required input-xlarge number" value="${data.price}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">描述:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" name="description"  maxlength="200">${data.description }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
				<label class="control-label">封面:</label>
				<div class="controls">
					<div id="image_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
						<c:if test="${!empty data.coverAddr}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.coverAddr}" />
						</c:if>
						<c:if test="${empty data.coverAddr}">选择图片</c:if>
					</div>  
					<input id="input_image" name="coverAddr" type="hidden" value="${data.coverAddr}" />
				</div>
			</div>
            <div class="control-group">
				<label class="control-label">照片:</label>
				<div class="controls">
					<div id="img_image_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
						<c:if test="${!empty data.imgAddr}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.imgAddr}" />
						</c:if>
						<c:if test="${empty data.imgAddr}">选择图片</c:if>
					</div>
					<input id="img_input_image" name="imgAddr" type="hidden" value="${data.imgAddr}" />
				</div>  
			</div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    <script>
    	/*	封面图 begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=servicePersonProductCover',
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
				prevent_duplicates : true
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
		/*	封面图 end*/
		
		/*	照片图 begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'img_image_btn',
			url : '${ctx}/file/upload?dirName=servicePersonProductImg',
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
				prevent_duplicates : true
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
							$("#img_image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
							$("#img_input_image").val("/oms" + response.url);
						});
		/*	照片图 end*/
	</script>
</body>
</html>