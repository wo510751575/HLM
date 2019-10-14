<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>终端${not empty data.code?'修改':'添加'}</title>
    <meta name="decorator" content="default"/>
<%-- 	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script> --%>
<%-- 	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script> --%>
<%-- 	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script> --%>
<%-- 	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" /> --%>
    <script type="text/javascript">
    $(document).ready(function() {
    	/* var repMsg=$("#repMsg").val();
    	if(repMsg){
    		showTip(repMsg);
    		$("#repMsg").val("");
    	}
    	
    	$("[name='shopNo']").change(function(){
    		$("#shopName").val($(this).find("option:selected").text());
        });
        $("[name='shopNo']").change();
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#input_image").val()){
            		alertx("请选择微信头像!");
            		return false;
            	}
            	if(!$("#img_input_image").val()){
            		alertx("请选择二维码图片!");
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
        }); */
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
        <li><a href="${ctx}/member/shopTerminal">终端列表</a></li>
	    <li class="active"><a href="${ctx}/member/shopTerminal/form?code=${data.code}">终端${not empty data.code?'修改':'添加'}</a></li> 
    </ul>
    
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/member/shopTerminal/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
        <input id="code" name="code" type="hidden" value="${data.code}"/>
<%--         <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/> --%>
        <div id="base_div" class="tab_div">                   
             
             <c:if test="${not empty data.code}">  
             	<div class="control-group">
	                <label class="control-label">终端编码:</label>
	                <div class="controls">
	                    <label class="lbl">${data.terminalCode}</label>
	                </div>
            	</div>
               <div class="control-group">
	                <label class="control-label">终端名称:</label>
	                <div class="controls">
	                    <input type="text" id="shopName" name="shopName"  maxlength="100" class="required input-xlarge" value="${data.shopName}"/>
	                    <span class="help-inline"><font color="red">*</font></span>
	                </div>
            	</div>
          <%--   <div class="control-group">
				<label class="control-label">微信头像:</label>
				<div class="controls">
					<div id="image_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center;float: left;">
						<c:if test="${not empty data.headAddress}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.headAddress}" />
						</c:if>
						<c:if test="${empty data.headAddress}">选择图片</c:if>
					</div>  
					<span class="help-inline" style="width: 10px; height: 120px; line-height: 100px; text-align: center;float: left;"><font color="red">*</font></span>
					<input id="input_image" name="headAddress" type="hidden" value="${data.headAddress}"/>
				</div>
			</div> 
            <div class="control-group">
                <label class="control-label">微信号:</label>
                <div class="controls">
                    <input type="text" id="noWx" name="noWx"  maxlength="100" class="required input-xlarge" value="${data.noWx}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>--%>
          <%--   <div class="control-group">
                <label class="control-label">微信昵称:</label>
                <div class="controls">
                    <input type="text" id="wxNickname" name="wxNickname"  maxlength="50" class="required input-xlarge" value="${data.wxNickname}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> 
            <div class="control-group">
                <label class="control-label">手机串号:</label>
                <div class="controls">
		            <c:choose>
		            	<c:when test="${empty data.code}">
		                    <input type="text" id="imei" name="imei"  maxlength="100" class="required input-xlarge number" value="${data.imei}"/>
		                    <span class="help-inline"><font color="red">*</font></span>
		            	</c:when>
		            	<c:otherwise>
		                    <label class="lbl">${data.imei}</label>
		            	</c:otherwise>
		            </c:choose>
                </div>
            </div>
            
            <div class="control-group">
               <label class="control-label">性别:</label>
                <div class="controls">
                  <select name="sex" class="select valid" aria-required="true" aria-invalid="false">
						<c:forEach items="${genders}" var="item">
						<option value="${item}" <c:if test="${item eq data.sex}">selected="selected"</c:if>>${item.name}</option>
					    </c:forEach>
					</select>
                </div>
            </div>--%>
            <div class="control-group" >
				<label class="control-label">状态：</label>
				<div class="controls">
					 <select  name="status" id="status" class="input-xlarge required" style="width:220px">
						<option value=1 <c:if test="${data.status eq 1}">selected</c:if> >正常</option>
						<option value=0 <c:if test="${data.status eq 0}">selected</c:if>>未激活</option>
						<option value=2 <c:if test="${data.status eq 2}">selected</c:if> >注销</option>
					 </select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
            <div class="control-group">
				<label class="control-label">二维码图片:</label>
				<div class="controls">
					<div id="img_image_btn"
						style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center;float: left;">
						<c:if test="${!empty data.qcord}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.qcord}" />
						</c:if>
						<c:if test="${empty data.qcord}">
					                                  选择图片
					       </c:if>
					</div>
					<span class="help-inline" style="width: 10px; height: 120px; line-height: 100px; text-align: center;float: left;"><font color="red">*</font></span>
					<input id="img_input_image" name="qcord" type="hidden"value="${data.qcord}" />
				</div>
			</div>
			
 		</c:if>  
 			<!--  新增 -->
	 		<c:if test="${empty data.code }">
	 		 	<%-- <div class="control-group">
	                <label class="control-label">选择门诊:</label>
	                <div class="controls">
	                 <div class="form-group form-group-select2">
						<select name="shopNo" id="shopList" class="required"  style="width:220px" <c:if test="${not empty data.code}">disabled="disabled"</c:if>>
				 			<c:forEach items="${shops}" var="item">
				 			<option class="MaterialInfo" value="${item.shopNo}"<c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
				 			</c:forEach>
						</select>
						</div>
	                </div>
            	</div> --%>
            	<div class="control-group">
	                <label class="control-label">二维码:</label>
	                <div class="controls">
	                 <div class="form-group form-group-select2">
						<div style="float: left;">
						 <img id="qcImg" src="${ctx}/member/shopTerminal/zkLoginToQcode" width="200" height="200"/>
						</div>
	                </div>
            	</div>
            	<div class="control-group">
	                 <div class="controls">
		                 <div class="form-group form-group-select2" style="color: red;">
		                 	打开中控手机，登录微信，扫一扫添加门诊微信。
						 </div>
					 </div>
            	</div>
	 		</c:if>
        </div>
        
        
        <div class="form-actions">
        	<c:if test="${not empty data.code }">
            	<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
             </c:if>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
       
    </form>
    </div>
    
    
    <script>
    	/*	LOGO begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=tidwx',
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
		/*	LOGO end*/
    	/*	QCORD begin*/
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'img_image_btn',
			url : '${ctx}/file/upload?dirName=tidQcord',
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
		/*	QCORD end*/
	</script>
</body>
</html>