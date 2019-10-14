<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>${not empty data.code?'服务修改':'服务添加'}</title>
    <meta name="decorator" content="default" />
    <style type="text/css">
        .container {
            padding: 10px 30px;
            width: 100%;
            min-height: 800px;
            background: #fff;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
    <script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#inputForm").validate({
                submitHandler: function (form) {
                    //loading('正在提交，请稍等...');
                    /* $.ajax({
                         type:"POST",
                         url:$("#inputForm").attr("action"),
                         data:$(form).serialize(),
                         dataType:'JSON',
                         async:false,
                         success:function(result){
                             if(result.errorCode=='0'){
                                 alertx("保存成功！");
                                 window.location.href="${ctx}/business/materialtype?timestamp="+new Date().getTime();
                             }else{
                                 alertx(result.errorMsg);
                             }
                         }
                     }); */
                    
                    var price = $('#price_f').val();
                    var reg = new RegExp("^[0-9]*(.[0-9]{2})$");
                    if (!reg.test(price)) {
                    	alertx('请输入正确的金额');
                    	return;
                    }
                   	$('#price').val(Math.floor(price * 100));
                     
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
    </script>
</head>

<body>
    <div class="container">
        <ul class="nav nav-tabs">
            <li><a href="${ctx}/hx/serverInfo">服务列表</a></li>
            <li class="active"><a href="${ctx}/hx/serverInfo/form?code=${data.code}">${not empty data.code?'服务修改':'服务添加'}</a></li>
        </ul><br />
        <form id="inputForm" action="${ctx}/hx/serverInfo/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
            <input id="code" name="code" type="hidden" value="${data.code}" />

            <tags:message content="${message}" />
            <div id="base_div" class="tab_div">

                <div class="control-group">
                    <label class="control-label">产品名称:</label>
                    <div class="controls">
                        <input type="text" id="serverName" name="serverName" maxlength="15" class="required input-xxlarge" value="${data.serverName}" />
                        <span class="help-inline"> <font color="red">*</font> </span>
                        <span class="help-inline"> <font>限定最多15个字符</font> </span>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">价格（单位：元）:</label>
                    <div class="controls">
                        <input type="number" id="price_f" max="99999999" min="0" step="0.01" class="required input-xxlarge" value="<fmt:formatNumber value="${data.price / 100}" pattern="#.00" />" />
                        <input type="hidden" id="price" name="price" max="99999999" min="0" value="${data.price }" />
                        <span class="help-inline"> <font color="red">*</font> </span>
                        <span class="help-inline"> <font>限定最多15个字符</font> </span>
                    </div>
                </div>
                
                <div class="control-group" >
					<label class="control-label">是否启用：</label>
					<div class="controls">
						 <select  name="status" id="status" class="input-xlarge required" style="width:220px">
							<option value="use" <c:if test="${data.status eq 'use'}">selected</c:if> >启用</option>
							<option value="unuse" <c:if test="${data.status eq 'unuse'}">selected</c:if>>禁用</option>
						 </select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>

                <div class="control-group">
                    <label class="control-label">产品说明:</label>
                    <div class="controls">
                        <textarea rows="9" cols="300" id="ctx" name="ctx" maxlength="1000" class="required input-xxlarge">${data.ctx}</textarea>
                        <span class="help-inline"> <font color="red">*</font> </span>
                    </div>
                </div>

            </div>
            
            <div id="details_page">
            	<c:choose>
            		<c:when test="${fn:length(data.serverDetails) > 0}">
            			<c:forEach items="${data.serverDetails }" var="temp" varStatus="number">
            			
            				<div class="tab_div">
				            	<input type="hidden" name="serverDetails[${number.index }].code" value="${temp.code }" />
				                <div class="control-group">
				                    <label class="control-label" name="servicename_type" style="font-weight: bold; font-size: 16px;" num="${number.index }">服务类型 - ${number.index + 1 }</label>
				                    <div class="controls">
				                    	<input id="btnCancel" class="btn" style="margin-left: 300px;" type="button" value="新  增" onclick="savePage();" />
				                        <input class="btn" type="button" value="删 除" onclick="removePage(this);" />
				                    </div>
				                </div>
				                <div class="control-group">
				                    <label class="control-label">名称:</label>
				                    <div class="controls">
				                    	<select class="input-xxlarge" name="serverDetails[${number.index }].userType">
				                    		<c:forEach items="${findShopConfigs }" var="shopConfig">
				                    			<option value="${shopConfig.code }" ${temp.userType eq shopConfig.code ? 'selected="selected"' : ''} >${shopConfig.lableName }</option>
				                    		</c:forEach>
				                    	</select>
				                    </div>
				                </div>
				                <div class="control-group">
				                    <label class="control-label">数量:</label>
				                    <div class="controls">
				                        <input type="number" name="serverDetails[${number.index }].serverNum" max="99999" min="0" class="required input-xxlarge" value="${temp.serverNum }" />
				                        <span class="help-inline"> <font color="red">*</font> </span>
				                    </div>
				                </div>
				                <div class="control-group">
				                    <label class="control-label">描述:</label>
				                    <div class="controls">
				                        <textarea rows="3" cols="300" name="serverDetails[${number.index }].serverDesc" maxlength="200" class="input-xxlarge">${temp.serverDesc }</textarea>                        
				                    </div>
				                </div>
				            </div>
            			
            			</c:forEach>
            		</c:when>
            		
            		<c:otherwise>
            		
            			<div class="tab_div">
			            	<input type="hidden" name="serverDetails.code" value="" />
			                <div class="control-group">
			                    <label class="control-label" name="servicename_type" style="font-weight: bold; font-size: 16px;" num="0">服务类型 - 1</label>
			                    <div class="controls">
			                    	<input id="btnCancel" class="btn" style="margin-left: 300px;" type="button" value="新  增" onclick="savePage();" />
			                        <input class="btn" type="button" value="删 除" onclick="removePage(this);" />
			                    </div>
			                </div>
			                <div class="control-group">
			                    <label class="control-label">名称:</label>
			                    <div class="controls">
			                    	<select class="input-xxlarge" name="serverDetails[0].userType">
			                    		<c:forEach items="${findShopConfigs }" var="shopConfig">
			                    			<option value="${shopConfig.code }" >${shopConfig.lableName }</option>
			                    		</c:forEach>
			                    	</select>
			                    </div>
			                </div>
			                <div class="control-group">
			                    <label class="control-label">数量:</label>
			                    <div class="controls">
			                        <input type="number" name="serverDetails[0].serverNum" max="99999" min="0" class="required input-xxlarge" value="" />
			                        <span class="help-inline"> <font color="red">*</font> </span>
			                    </div>
			                </div>
			                <div class="control-group">
			                    <label class="control-label">描述:</label>
			                    <div class="controls">
			                        <textarea rows="3" cols="300" name="serverDetails[0].serverDesc" maxlength="200" class="input-xxlarge"></textarea>                        
			                    </div>
			                </div>
			            </div>
            		
            		</c:otherwise>
            	
            	</c:choose>
            
	            
            </div>
            <div class="form-actions">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
            </div>
        </form>
    </div>
    
    <script type="text/javascript">
    	// 删除当前模块
    	function removePage(thia) {
    		if ($('#details_page').find('.tab_div').size() <= 1) {
    			return;
    		}
    		$(thia).parents('.tab_div').remove();
    		$('#details_page').find('.tab_div').each(function(i, temp) {
    			var _this = $(temp);
    			var num = _this.find('label[name="servicename_type"]').attr('num');
    			_this.find('label[name="servicename_type"]').text('服务类型 - ' + (i + 1));
    			_this.find('label[name="servicename_type"]').attr('num', i);
    			_this.find('input[name="serverDetails[' + num + '].code"]').val('Hello World!!!');//.attr('name', 'serverDetails[' + i + '].code');
    			_this.find('select[name="serverDetails[' + num + '].userType"]').attr('name', 'serverDetails[' + i + '].userType');
    			_this.find('input[name="serverDetails[' + num + '].serverNum"]').attr('name', 'serverDetails[' + i + '].serverNum');
    			_this.find('textarea[name="serverDetails[' + num + '].serverDesc"]').attr('name', 'serverDetails[' + i + '].serverDesc');
    			
    		});
    	}
    	
    	function savePage() {
    		
    		var root = $('#details_page');
    		var num = root.find('.tab_div').size();
    		if (num > 50) {
    			return;
    		}
    		
    		var node = $(root.find('.tab_div').get(0)).clone();
    		node.find('input[name="serverDetails[0].code"]').attr('name', 'serverDetails[' + num + '].code').val(null);
    		node.find('select[name="serverDetails[0].userType"]').attr('name', 'serverDetails[' + num + '].userType').val(null);
    		node.find('input[name="serverDetails[0].serverNum"]').attr('name', 'serverDetails[' + num + '].serverNum').val(null);
    		node.find('textarea[name="serverDetails[0].serverDesc"]').attr('name', 'serverDetails[' + num + '].serverDesc').val(null);
    		
    		node.find('label[name="servicename_type"]').attr('num', num).text('服务类型 - ' + (num + 1));
    		root.append(node);
    	}
    </script>
    
</body>

</html>