<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>${not empty data.code?'热文修改':'热文添加'}</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
    	var editor;
    	var ctx =  '${ctx}';
    </script>
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
            <li><a href="${ctx}/rw/article/">热文列表</a></li>
            <li class="active"><a href="${ctx}/rw/article/form?code=${data.code}">${not empty data.code?'热文修改':'热文添加'}</a></li>
        </ul>
        <tags:message content="${message}" />
        <form id="inputForm" action="${ctx}/rw/article/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
            <input id="code" name="code" type="hidden" value="${data.code}" />
            <div id="base_div" class="tab_div">
            
            	<div class="control-group">
                	<label class="control-label">GET URL:</label>
                	<div class="controls">
                		<input type="text" id="climbUrl" maxlength="2000"class="input-xlarge" value="">
                		<input type="button" id="climbBtn" class="btn btn-primary" value="获取内容">
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">文章分类:</label>
                    <div class="controls">
                    	<select style="width: 177px;" name="typeCode">
		                    <c:forEach items="${findArticleTypeList }" var="item">
		                    	<option value="${item.code }" ${item.code eq data.typeCode ? 'selected="selected"' : '' }>${item.typeName }</option>
		                    </c:forEach>
		                </select>
		                <input type="hidden" name="type" value="${data.type }">                        
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>

                <%-- <div class="control-group">
                    <label class="control-label">类型:</label>
                    <div class="controls">
                        <span><label><input name="type" class="required" type="radio" value="HOT" ${'HOT' eq data.type ? 'checked="checked"' : '' }> 热文</label></span>
                        <span><label><input name="type" class="required" type="radio" value="MY" ${'MY' eq data.type ? 'checked="checked"' : '' }> 用户</label></span>
                        <span><label><input name="type" class="required" type="radio" value="OTHER" ${'OTHER' eq data.type or empty data.type ? 'checked="checked"' : '' }> 其他</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div> --%>
                
                <div class="control-group">
                	<label class="control-label">标题:</label>
                	<div class="controls">
                		<input type="text" name="title" maxlength="200"class="required input-xlarge" value="${data.title }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">略缩图:</label>
                	<div class="controls">
                		<div id="image_btn" style="border: 1px solid #e0e6eb;width:180px;height:180px;line-height:100px;text-align:center">
                			<c:choose>
                				<c:when test="${not empty data.mainImage }">
                					<img width="180px" height="180px" src="${data.mainImage }"/>
                				</c:when>
                				<c:otherwise>选择图片</c:otherwise>
                			</c:choose>
                		</div>
                		<input type="text" id="input_image" name="mainImage" value="${data.mainImage }" htmlEscape="false" maxlength="255" class="required input-xlarge">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">来源:</label>
                	<div class="controls">
                		<input type="text" name="source" maxlength="200"class="input-xlarge" value="${data.source }">
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">状态:</label>
                    <div class="controls">
                        <span><label><input name="rwState" class="required" type="radio" value="normal" ${'normal' eq data.rwState ? 'checked="checked"' : '' }> 正常</label></span>
                        <span><label><input name="rwState" class="required" type="radio" value="forbid" ${'forbid' eq data.rwState ? 'checked="checked"' : '' }> 禁用</label></span>
                        <span><label><input name="rwState" class="required" type="radio" value="review" ${'review' eq data.rwState or empty data.rwState ? 'checked="checked"' : '' }> 审核</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">备注:</label>
                	<div class="controls">
                		<textarea rows="5" cols="" name="remark">${data.remark }</textarea>
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">内容:</label>
                    <div class="controls">
                        <textarea class="editor input-xxlarge required" rows="30" style="width: 98%;" maxlength="20000" name="articleHtml" id="editor">${data.articleHtml }</textarea>
                        <span class="help-inline"><font color="red">*</font></span>
                        <input type="button" id="advBtn" class="btn btn-primary" value="在光标处插入广告位">
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
	        url: '${ctx}/file/upload?dirName=mainImage',
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
	    
	    $(document).ready(function() {
	    	$('#climbBtn').click(function() {
	    		var uri = $('#climbUrl').val();
	    		if (isEmptyObject(uri)) {
	    			showTip('请填写 GET URL 内容;');
	    			return ;
	    		}
	    		
	    		$.ajax({
	    			type: 'POST',
	    			url: '${ctx}/rw/article/climb',
	    			data: {
	    				uri: uri
	    			},
	    			dataType: 'JSON',
	    			success: function(data) {
	    				
	    				if (data.type != 0) {
	    					showTip(data.msg);
	    					return ;
	    				}
	    				var rs = data.msg;
	    				if (isEmptyObject(rs)) {
	    					return;
	    				}	    				
	    				editor.html(rs);
	    				
	    				getFirstTitle();
	    				getFirstImg();
	    				
	    			}
	    		});
	    	});
	    	
	    	$('#advBtn').click(function() {
	    		// 获取光标所在处
	    		var startOffset = editor.cmd.range.startOffset;
	    		editor.insertHtml('<div rw_center="adv" rw_gg="adv" style="width:100%; height:120px; background-color: whitesmoke;"></div>');
	    	});
	    });
	    
	    
	    function getFirstTitle() {
	    	var titles = editor.edit.iframe.get().contentWindow.document.body.getElementsByTagName('title');
	    	if (titles.length > 0) {
	    		$('input[type="text"][name="title"]').val(titles[0].innerText);
	    	}
	    }
	    
	    function getFirstImg() {
	    	// 获取第一张图片；
			var imgs = editor.edit.iframe.get().contentWindow.document.body.getElementsByTagName('img');
	    	if (imgs.length > 0) {
	    		var imgsrc = imgs[0].src;
	    		$("#image_btn").html('<img width="180px" height="180px" src="' + imgsrc + '"/>');
		        $("#input_image").val(imgsrc);
	    	}
	    }
	    
    </script>
    
</body>

</html>