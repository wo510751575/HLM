<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>新增任务</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			initView();
			$("#name").focus();
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
		
		function initView(){
	    	$('.form-group-select-css').select2({
	    		allowClear: true
	    	});
	    }
	</script>
<style type="text/css">
	.container{
	padding: 20px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.nav-tabs > li > a {
    padding-top: 0px;
}
::-webkit-scrollbar {
		width: 5px;
		height: 12px;
		-webkit-border-radius: 40%;
}
.select-medium{
			width: 100%;
		}
	</style>
</head>
<body>
<div class="container">
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<input id="importMsg" name="importMsg" style="display:none" value="${importMsg}"/>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/addFriendsTask/selectAddFriendsTaskList">任务列表</a></li>
			<li class="active"><a href="${ctx}/member/addFriendsTask/toAddFriendsTaskPage"">新增任务</a></li>
	</ul>
	<tags:message content="${message}"/>
	<form id="inputForm" action="${ctx}/member/addFriendsTask/importMobilePhone" method="post" enctype="multipart/form-data" class="form-horizontal"  onsubmit="return submitFrom(this);">
		<div id="base_div" class="tab_div">
			<div class="control-group">
				<label class="control-label"><span class="help-inline"><font color="red">*</font></span>任务名称:</label>
				<div class="controls">
					<input type="text" id="name" name="name" maxlength="100"
						class="required input-xxlarge" value="${data.name}" /> 
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><span class="help-inline"><font color="red">*</font></span>任务微信:</label>
				<div class="controls">
					<select class="required form-group-select-css select-medium " name="noWxArrays" id="noWxSec" multiple>
						<c:forEach items="${findShopTerminalReturns}" var="item">
							<option value="${item.noWx}">${item.alias} - ${item.wxNickname}</option>
						</c:forEach>
					</select>
				</div>
			</div>


			<div class="control-group" >
					<label class="control-label">验证话术:</label>
					<div class="controls">
					 <textarea id="sendMessage" name="sendMessage" class="editor input-xxlarge" rows="5" ></textarea>
					</div>
			</div>

			<div class="control-group">
				<label class="control-label"><font color="red">*</font></span>手机号码:</label>
				<div class="controls">
					<input id="file"  name="file" type="file" style="width:330px"/>
					<span class="help-inline">
						<a href="${ctx}/member/addFriendsTask/template" >下载模板</a>
						<font color="red">（请选择xls或者xlsx格式文件）</font>
					</span>
				</div>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"  />&nbsp;
			<input id="btnCancel" class="btn" type="button" value="取消" onclick="history.go(-1)">
		</div>
	</form>
	</div>
</body>
</html>
