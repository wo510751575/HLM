<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'服务人员修改':'服务人员添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#codeListSec').select2();
    	
    	$("[name='shopNo']").change(function(){
    		$("#shopName").val($(this).find("option:selected").text());
        });
        $("[name='shopNo']").change();
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#codeListSec").val()){
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
        <li><a href="${ctx}/member/servicePerson">服务人员列表</a></li>
	    <li class="active"><a href="${ctx}/member/servicePerson/form?code=${data.code}">${not empty data.code?'服务人员修改':'服务人员添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/member/servicePerson/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="personNo" name="personNo" type="hidden" value="${data.personNo}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <input id="shopName" name="shopName" type="hidden" value="${data.shopName}"/>
        <div id="base_div" class="tab_div">                   
                 
                 <div class="control-group">
                <label class="control-label">人员姓名:</label>
                <div class="controls">
                    <input type="text" id="personName" name="personName"  maxlength="100" class="required input-xlarge" value="${data.personName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
                         
               <div class="control-group">
                <label class="control-label">选择终端:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="shopNo" id="codeListSec"  style="width:220px">
			 			<c:forEach items="${shops}" var="item">
			 			<option class="MaterialInfo" value="${item.shopNo}"<c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
			 			</c:forEach>
					</select>
					</div>
                </div>
            </div>
            <div class="control-group">
				<label class="control-label">头像:</label>
				<div class="controls">
					<div id="image_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
						<c:if test="${!empty data.headAddress}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.headAddress}" />
						</c:if>
						<c:if test="${empty data.headAddress}">选择图片</c:if>
					</div>  
					<input id="input_image" name="headAddress" type="hidden" value="${data.headAddress}" />
				</div>
			</div>
            <div class="control-group">
                <label class="control-label">职称:</label>
                <div class="controls">
                    <input type="text" id="title" name="title"  maxlength="20" class="required input-xlarge" value="${data.title}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">服务价格:</label>
                <div class="controls">
                    <input type="text" id="title" name="servicePrice"  maxlength="20" class="required input-xlarge number" value="${data.servicePrice}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">标签:</label>
                <div class="controls">
                    <input type="text" id="hcLabel" name="hcLabel"  maxlength="50" class="required input-xlarge" value="${data.hcLabel}"/>
                    <span class="help-inline"><font color="red">* 多个请用英文逗号隔开</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">简介:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" name="summary" maxlength="200" >${data.summary }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
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
    	/*	头像 begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=servicePersonHead',
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
		/*	头像 end*/
		
	</script>
</body>
</html>