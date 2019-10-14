<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>${not empty data.code?'豆子充值订单修改':'豆子充值订单添加'}</title>
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
                	
                	var amount = $('input[name="amountM"]').val();
                	$('input[name="amount"]').val(parseInt(parseFloat(amount) * 100, 10));
                	
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
            <li><a href="${ctx}/ad/beansOrder/">豆子充值订单列表</a></li>
            <li class="active"><a href="${ctx}/ad/beansOrder/form?code=${data.code}">${not empty data.code?'豆子充值订单修改':'豆子充值订单添加'}</a></li>
        </ul>
        <tags:message content="${message}" />
        <form id="inputForm" action="${ctx}/ad/beansOrder/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
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
                	<label class="control-label">客户名:</label>
                	<div class="controls">
                		<input type="text" name="nickName" maxlength="200" class="input-xlarge" value="" disabled="disabled">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">手机号:</label>
                	<div class="controls">
                		<input type="text" name="mobile" maxlength="15"class="input-xlarge" value="${data.mobile }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">充值金额(元):</label>
                	<div class="controls">
                		<input type="number" name="amountM" maxlength="11" class="required input-xlarge" min="0" value="<fmt:formatNumber value="${data.amount / 100 }" pattern="#.##" type="number" />">
                		<input type="hidden" name="amount" maxlength="11" class="input-xlarge" min="0" value="${data.amount }">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">支付流水编号:</label>
                	<div class="controls">
                		<input type="text" name="serialNum" maxlength="64"class="input-xlarge" value="${data.serialNum }">
                	</div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">充值凭证:</label>
                	<div class="controls">
                		<div id="image_btn" style="border: 1px solid #e0e6eb;width:180px;height:180px;line-height:100px;text-align:center">
                			<c:choose>
                				<c:when test="${not empty data.payCert }">
                					<img width="180px" height="180px" src="${data.payCert }"/>
                				</c:when>
                				<c:otherwise>选择充值凭证</c:otherwise>
                			</c:choose>
                		</div>
                		<input type="hidden" id="input_image" name="link" value="${data.payCert }" htmlEscape="false" maxlength="255" class="required input-xlarge">
                		<span class="help-inline"><font color="red">*</font></span>
                	</div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">付款方式:</label>
                    <div class="controls">
                        <span><label><input name="payType" class="required" type="radio" value="WX" ${'WX' eq data.payType or empty data.payType ? 'checked="checked"' : '' }> 微信</label></span>
                        <span><label><input name="payType" class="required" type="radio" value="ALI" ${'ALI' eq data.payType ? 'checked="checked"' : '' }> 支付宝</label></span>
                        <span><label><input name="payType" class="required" type="radio" value="BANK" ${'BANK' eq data.payType ? 'checked="checked"' : '' }> 银行卡</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">审核状态:</label>
                    <div class="controls">
                        <span><label><input name="status" class="required" type="radio" value="WAIT" ${'WAIT' eq data.status ? 'checked="checked"' : '' }> 待审</label></span>
                        <span><label><input name="status" class="required" type="radio" value="PASS" ${'PASS' eq data.status ? 'checked="checked"' : '' }> 通过</label></span>
                        <span><label><input name="status" class="required" type="radio" value="UNPASS" ${'UNPASS' eq data.status or empty data.status ? 'checked="checked"' : '' }> 拒绝</label></span>
                        <span class="help-inline"><font color="red">*</font></span>
                    </div>
                </div>
                
                <div class="control-group">
                	<label class="control-label">付款时间:</label>
                	<div class="controls">
                		<input name="payTime" type="text" readonly="readonly" maxlength="20" class="required input-xlarge Wdate"
							value="<fmt:formatDate value="${data.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
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
	        url: '${ctx}/file/uploadNotLimit?dirName=beansOrderImage',
	        multi_selection: false,
	        auto_start: true,
	        flash_swf_url: '${ctxStatic}/common/Moxie.swf',
	        silverlight_xap_url: '${ctxStatic}/common/Moxie.xap',
	        filters: {
	            mime_types: [ //只允许上传图片文件
	                { title: "图片文件", extensions: "jpg,gif,png" },
	            ],
	            max_file_size: '50000kb',
	            prevent_duplicates: true
	        },
	        multipart_params: {
	            fileType: 'image',
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
	    
	    $(document).ready(function() {
	    	$('#assignButton').click(function() {
	    		top.$.jBox.open("iframe:${ctx}/rw/rwUser/selectList", "选择用户", 810, $(top.document).height() - 290, {
	    		    buttons: { "确定选择": "ok", "关闭": true },
	    		    bottomText: "通过选择部门，然后为列出的人员。", submit: function (v, h, f) {
	    		        var rsRecord = h.find("iframe")[0].contentWindow.rsRecord;
						
	    		        //nodes = selectedTree.getSelectedNodes();
	    		        if (v == "ok") {

	    		        	if (rsRecord['memberNo'] == '') {
	    		        		top.$.jBox.tip("未选中用户！", 'info');
	    		                return false;
	    		        	}   		        	

	    		            $('input[name="memberNoGuid"]').val(rsRecord['memberNoGuid']);
	    		            $('input[name="nickName"]').val(rsRecord['nickName']);
	    		            $('input[name="mobile"]').val(rsRecord['mobile']);
	    		            
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