<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>${not empty data.code?'会员修改':'会员添加'}</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
    <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
    <script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    
        //是否包含字符串
        function isContains(str, substr) {
            return str.indexOf(substr) >= 0;
        }
        $(document).ready(function () {

            var repMsg = $("#repMsg").val();
            if (repMsg) {
                showTip(repMsg);
                $("#repMsg").val("");
            }

            $("#inputForm").validate({
                submitHandler: function (form) {
                	
                	var pwd = $('input[name="pwd"]').val();
                	var confimPwd = $('input[name="confimPwd"]').val();
                	if (pwd != '' || confimPwd != '') {
                		
                	}
                	
                	var advType = $('select[name="typeCode"] option:selected').text();
                	$('input[name="type"]').val(advType);
                	
                    $("#btnSubmit").attr("disabled", "disabled");
                    form.submit()
                    return false;
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

        function tabChange(id, ths) {
            $(".tab_div").hide();
            $(".nav-child li").removeClass("active");
            $(id).show();
            $(ths).addClass("active");
        }
    </script>
    <style type="text/css">
        .nav-child li a {
            line-height: 10px;
        }

        .nav-child li.active a {
            border: 1px dotted #ddd;
            border-bottom-color: transparent;
        }

        .photo-file {
            position: absolute;
            top: 350px;
            left: 190px;
            opacity: 0;
            filter: alpha(opacity:0);
            cursor: pointer;
        }

        .container {
            padding: 10px 30px;
            width: 100%;
            min-height: 800px;
            background: #fff;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
    </style>
</head>

<body>
    <div class="container">
        <input id="repMsg" name="repMsg" style="display:none" value="${repMsg}" />
        <ul class="nav nav-tabs">
            <li><a href="${ctx}/rw/rwUser/list">会员列表</a></li>
            <li class="active"><a href="${ctx}/rw/rwUser/form?code=${data.code}">${not empty data.code?'会员修改':'会员添加'}</a></li>
        </ul>
        <tags:message content="${message}" />
        <form id="inputForm" action="${ctx}/rw/rwUser/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
            <input id="code" name="code" type="hidden" value="${data.code}" />
            <div id="base_div" class="tab_div">
            
            	<div class="control-group">
                	<label class="control-label">用户头像</label>
                	<div class="controls">
                		<div id="image_btn" style="border: 1px solid #e0e6eb;width:180px;height:180px;line-height:100px;text-align:center">
                			<c:choose>
                				<c:when test="${not empty data.headAddress }">
                					<img width="180px" height="180px" src="${data.headAddress }"/>
                				</c:when>
                				<c:otherwise>选择图片</c:otherwise>
                			</c:choose>
                		</div>
                		<input type="hidden" id="input_image" name="headAddress" value="${data.headAddress }" htmlEscape="false" maxlength="255" class="required input-xlarge">
                		<!-- <span class="help-inline"><font color="red">*</font></span> -->
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">会员姓名（备注）:</label>
                	<div class="controls">
                		<input type="text" name="nickName" maxlength="200" class="required input-xlarge" value="${data.nickName }">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">微信号:</label>
                	<div class="controls">
                		<input type="text" name="noWx" maxlength="200" class="input-xlarge" value="${data.noWx }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">手机号:</label>
                	<div class="controls">
                		<input type="text" name="mobile" maxlength="200"  value="${data.mobile }">
                		<!-- <span class="help-inline"><font color="red">*</font></span> -->
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">登陆名:</label>
                	<div class="controls">
                		<input type="text" name="loginName" maxlength="200" class="required input-xlarge" value="${data.loginName }">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">密码:</label>
                	<div class="controls">
                		<input type="password" id="newPassword" name="pwd" maxlength="20" minlength="6" class="${empty data.code ? 'required' : '' } input-xlarge" value="">
                		<c:if test="${empty data.code }">
                			<span class="help-inline"><font color="red">*</font></span>
                		</c:if>
                		<c:if test="${not empty data.code }">
                			<span class="help-inline"><font color="red">若不修改密码，请留空。</font></span>
                		</c:if>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">确认密码:</label>
                	<div class="controls">
                		<input type="password" id="confirmNewPassword" name="confimPwd" maxlength="20" minlength="6" equalTo="#newPassword" class="${empty data.code ? 'required' : '' } input-xlarge" value="">
                		<c:if test="${empty data.code }">
                			<span class="help-inline"><font color="red">*</font></span>
                		</c:if>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">邮箱:</label>
                	<div class="controls">
                		<input type="text" name="email" maxlength="200"class="input-xlarge" value="${data.email }">
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">会员类型:</label>
                    <div class="controls">
                    	<select style="width: 177px;" name="userLevel">
		                    <option value="1" ${"1" eq data.userLevel ? 'selected="selected"' : '' }>普通用户</option>
                    		<option value="2" ${"2" eq data.userLevel ? 'selected="selected"' : '' }>个体用户</option>
                    		<option value="3" ${"3" eq data.userLevel ? 'selected="selected"' : '' }>商户</option>
		                </select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">性别:</label>
                    <div class="controls">
                        <span><label><input name="gender" class="required" type="radio" value="male" ${'male' eq data.gender ? 'checked="checked"' : '' }> 男</label></span>
                        <span><label><input name="gender" class="required" type="radio" value="female" ${'female' eq data.gender or empty data.gender ? 'checked="checked"' : '' }> 女</label></span>
                    </div>
                </div>
                
                <div class="control-group">
					<label class="control-label">所属区域:</label>
					<div class="controls">
						<tags:treeselect url="/sys/area/treeData" id="areaCode" name="areaCode" value="${data.areaCode }" labelName="areaName" labelValue="${data.areaName }" title="所属区域" allowClear="true" />
						<!-- <span class="help-inline"><font color="red">*</font></span> -->
					</div>
				</div>
				
				<div class="control-group">
                	<label class="control-label">住址:</label>
                	<div class="controls">
                		<input type="text" name="address" maxlength="200"class="input-xlarge" value="${data.address }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">所在公司:</label>
                	<div class="controls">
                		<input type="text" name="companyName" maxlength="200"class="input-xlarge" value="${data.companyName }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">公司网址:</label>
                	<div class="controls">
                		<input type="text" name="websiteUrl" maxlength="200"class="input-xlarge" value="${data.websiteUrl }">
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">状态:</label>
                    <div class="controls">
                        <span><label><input name="status" class="required" type="radio" value="NORMAL" ${'NORMAL' eq data.status ? 'checked="checked"' : '' }> 正常</label></span>
                        <span><label><input name="status" class="required" type="radio" value="CANCEL" ${'CANCEL' eq data.status ? 'checked="checked"' : '' }> 注销</label></span>
                        <span><label><input name="status" class="required" type="radio" value="FREEZE" ${'FREEZE' eq data.status or empty data.status ? 'checked="checked"' : '' }> 冻结</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">备注:</label>
                	<div class="controls">
                		<textarea rows="5" cols="" name="remark">${data.remark }</textarea>
                	</div>
                </div>

            </div>
            <div class="form-actions">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
            </div>
        </form>
    </div>
    
    <script type="text/javascript">
    
	    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	        browse_button: 'image_btn',
	        url: '${ctx}/file/upload?dirName=rwUserHeadAddress',
	        multi_selection: false,
	        auto_start: true,
	        flash_swf_url: '${ctxStatic}/common/Moxie.swf',
	        silverlight_xap_url: '${ctxStatic}/common/Moxie.xap',
	        filters: {
	            mime_types: [ //只允许上传图片文件
	                { title: "图片文件", extensions: "jpg,gif,png" }
	            ],
	            max_file_size: '1024kb',
	            prevent_duplicates: true
	        },
	        multipart_params: {
	            fileType: 'image',
	            width: 400,
	            height: 400
	        }
	    });
	    uploader.init(); //初始化
	    uploader.bind('FilesAdded', function (uploader, files) {
	        if (files.length > 0) {
	            uploader.start();
	        }
	    });
	    uploader.bind('Error', function (uploader, errObject) {
	        if (errObject.code != -602) {
	            showTip(errObject.message, "info");
	        }
	    });
	
	    uploader.bind('FileUploaded', function (uploader, file, responseObject) {
	        var response = $.parseJSON(responseObject.response);
	        $("#image_btn").html('<img width="180px" height="180px" src="' + uploadUrl + '/oms' + response.url + '"/>');
	        $("#input_image").val(uploadUrl + '/oms' + response.url);
	    });
	    
	    
	    
    </script>
    
</body>

</html>