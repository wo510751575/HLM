<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>${not empty data.code?'广告修改':'广告添加'}</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
    	var ctx =  '${ctx}';
    </script>
    <script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
    <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
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
                	
                	var advType = $('select[name="advTypeCode"] option:selected').text();
                	$('input[name="advType"]').val(advType);
                	
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
            <li><a href="${ctx}/ad/advertise/">广告列表</a></li>
            <li class="active"><a href="${ctx}/ad/advertise/form?code=${data.code}">${not empty data.code?'广告修改':'广告添加'}</a></li>
        </ul>
        <tags:message content="${message}" />
        <form id="inputForm" action="${ctx}/ad/advertise/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
            <input id="code" name="code" type="hidden" value="${data.code}" />
            <div id="base_div" class="tab_div">
            
            	<div class="control-group">
                	<label class="control-label">会员ID:</label>
                	<div class="controls">
                		<input type="text" name="memberNoGuid" maxlength="200"class="required input-xlarge" value="${data.memberNoGuid }">
                		<input id="assignButton" class="btn btn-primary" type="submit" value="选择用户，商户用户选择，否则直接复制会员ID"/>
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">广告类型:</label>
                    <div class="controls">
                        <span><label><input name="advState" class="required" type="radio" value="pay" ${'pay' eq data.advState ? 'checked="checked"' : '' }> 互助广告</label></span>
                        <span><label><input name="advState" class="required" type="radio" value="free" ${'free' eq data.advState or empty data.type ? 'checked="checked"' : '' }> 自助广告</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">类型:</label>
                    <div class="controls">
                        <span><label><input name="type" class="required" type="radio" value="video" ${'video' eq data.type ? 'checked="checked"' : '' }> 视频</label></span>
                        <span><label><input name="type" class="required" type="radio" value="image" ${'image' eq data.type or empty data.type ? 'checked="checked"' : '' }> 图片</label></span>
                        <%-- <span><label><input name="type" class="required" type="radio" value="other" ${'other' eq data.type or empty data.type ? 'checked="checked"' : '' }> 其他</label></span> --%>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                <%-- 
                <div class="control-group">
                	<label class="control-label">来源:</label>
                	<div class="controls">
                		<input type="text" name="source" maxlength="200"class="input-xlarge" value="${data.source }">
                	</div>
                </div>
                 --%>
                <div class="control-group">
                	<label class="control-label">图片或视频地址:</label>
                	<div class="controls">
                		<div id="image_btn" style="border: 1px solid #e0e6eb;width:180px;height:180px;line-height:100px;text-align:center">
                			<c:choose>
                				<c:when test="${not empty data.link }">
                					<img width="180px" height="180px" src="${data.link }"/>
                				</c:when>
                				<c:otherwise>选择图片</c:otherwise>
                			</c:choose>
                		</div>
                		<input type="text" id="input_image" name="link" value="${data.link }" htmlEscape="false" maxlength="255" class="required input-xlarge">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">状态:</label>
                    <div class="controls">
                        <span><label><input name="state" class="required" type="radio" value="normal" ${'normal' eq data.state ? 'checked="checked"' : '' }> 正常</label></span>
                        <span><label><input name="state" class="required" type="radio" value="forbid" ${'forbid' eq data.state ? 'checked="checked"' : '' }> 禁用</label></span>
                        <span><label><input name="state" class="required" type="radio" value="review" ${'review' eq data.state or empty data.state ? 'checked="checked"' : '' }> 审核</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">总价:</label>
                	<div class="controls">
                		<input type="number" name="priceSum" maxlength="10"class="required input-xlarge" min="0" value="${data.priceSum }">
                		<span class="help-inline"><font color="red">广告豆</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">展示单价:</label>
                	<div class="controls">
                		<input type="number" name="priceView" maxlength="10"class="required input-xlarge" min="0" value="${data.priceView }">
                		<span class="help-inline"><font color="red">广告豆/次</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">点击单价:</label>
                	<div class="controls">
                		<input type="number" name="priceClick" maxlength="10"class="required input-xlarge" min="0" value="${data.priceClick }">
                		<span class="help-inline"><font color="red">广告豆/次</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">广告分类:</label>
                    <div class="controls">
                    	<select style="width: 177px;" name="advTypeCode">
		                    <c:forEach items="${findAdvertiseTypeList }" var="item">
		                    	<option value="${item.code }" ${item.code eq data.advTypeCode ? 'selected="selected"' : '' }>${item.typeName }</option>
		                    </c:forEach>
		                </select>
		                <input type="hidden" name="advType" value="${data.advType }">                        
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">广告地址:</label>
                	<div class="controls">
                		<input type="text" name="advLink" maxlength="200"class="input-xlarge" value="${data.advLink }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">上架时间:</label>
                	<div class="controls">
                		<input name="upDate" type="text" readonly="readonly" maxlength="20" class="required input-xlarge Wdate"
							value="<fmt:formatDate value="${data.upDate }" pattern="yyyy-MM-dd HH:mm"/>" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">下架时间:</label>
                	<div class="controls">
                		<input name="downDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
							value="<fmt:formatDate value="${data.downDate }" pattern="yyyy-MM-dd HH:mm"/>" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">排序:</label>
                	<div class="controls">
                		<input type="number" name="numOrder" maxlength="10"class="input-xlarge" value="${data.numOrder }">
                		<span class="help-inline"><font color="red">数字越大排序越优先</font></span>
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
	        url: '${ctx}/file/uploadNotLimit?dirName=advertiseImage',
	        multi_selection: false,
	        auto_start: true,
	        flash_swf_url: '${ctxStatic}/common/Moxie.swf',
	        silverlight_xap_url: '${ctxStatic}/common/Moxie.xap',
	        filters: {
	            mime_types: [ //只允许上传图片文件
	                { title: "图片文件", extensions: "jpg,gif,png" },
	                { title: "视频文件", extensions: "mp4" }
	            ],
	            max_file_size: '50000kb',
	            prevent_duplicates: true
	        },
	        multipart_params: {
	            fileType: $('input[name="type"]:checked').val(), //'image',
	            //width: 400,
	            //height: 400
	        },
	        resize: {
	        	crop: false, 
	        	quality: 100,
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
	        if (response.url) {
	        	$("#image_btn").html('<img width="180px" height="180px" src="' + uploadUrl + '/oms' + response.url + '"/>');
		        $("#input_image").val(uploadUrl + '/oms' + response.url);
	        } else {
	        	showTip(responseObject.response);
	        }
	    });
	    
	    // 重新绑定参数
	    uploader.bind('BeforeUpload', function(uploader, file) {
	    	uploader.getOption().multipart_params.fileType = $('input[name="type"]:checked').val();
	    });
	    
	    $(document).ready(function() {
	    	$('#assignButton').click(function() {
	    		top.$.jBox.open("iframe:${ctx}/sys/role/usertorole?checkUser=yes", "选择用户", 810, $(top.document).height() - 240, {
	    		    buttons: { "确定选择": "ok", "关闭": true },
	    		    bottomText: "通过选择部门，然后为列出的人员。", submit: function (v, h, f) {
	    		        var ids = h.find("iframe")[0].contentWindow.ids;
						
	    		        //nodes = selectedTree.getSelectedNodes();
	    		        if (v == "ok") {

	    		            if (ids[0] == '') {
	    		                top.$.jBox.tip("未选中用户！", 'info');
	    		                return false;
	    		            }

	    		            $('input[name="memberNoGuid"]').val(ids[0]);
	    		            return true;
	    		        }
	    		    }, loaded: function (h) {
	    		        $(".jbox-content", top.document).css("overflow-y", "hidden");
	    		    }
	    		});
	    		//
	    	});
	    });
	    
    </script>
    
</body>

</html>