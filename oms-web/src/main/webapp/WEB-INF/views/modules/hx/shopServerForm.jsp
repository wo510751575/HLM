<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <title>开通服务</title>
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
            <li><a href="${ctx}/hx/shop/server">服务列表</a></li>
            <li class="active"><a href="${ctx}/hx/shop/server/form">开通服务</a></li>
        </ul><br />
        <form id="inputForm" action="${ctx}/hx/shop/server/save" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">

            <tags:message content="${message}" />
            <div id="base_div" class="tab_div">
                
                <div class="control-group" >
					<label class="control-label">诊所：</label>
					<div class="controls">
						 <select  name="merchantNo" id="merchantNo" class="input-xlarge required">
						 	<option value="">--请选择--</option>
						 	<c:forEach items="${officeList }" var="merchant">
						 		<option value="${merchant.id }" data-code="${merchant.code }">${merchant.name }</option>
						 	</c:forEach>
						 </select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				
				<div class="control-group" >
					<label class="control-label">服务名称：</label>
					<div class="controls">
						 <select  name="serverCode" id="serverCode" class="input-xlarge required">
						 	<option value="" data-price="">--请选择--</option>
						 	<c:forEach items="${serverInfoList }" var="info">
						 		<option value="${info.code }" data-price="${info.price }">${info.serverName }</option>
						 	</c:forEach>
						 </select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				
				<div class="control-group">
                    <label class="control-label">价格（单位：元）:</label>
                    <div class="controls">
                        <input type="number" id="price" disabled class="input-xxlarge" value="" />
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
    	$(document).ready(function () {
    		$('#serverCode').change(function() {
    			var price = $('#serverCode').find('option:selected').data('price');
    			$('#price').val(price / 100);
    		});
    	});
    </script>
    
</body>

</html>