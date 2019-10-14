<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户试用信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
	</script>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/probation/">商户试用信息列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="findMerchant" action="${ctx}/sys/probation/edit" method="post" class="form-horizontal">
		<form:hidden path="code"/>
		<div class="control-group">
			<label class="control-label">商户名称:</label>
			<div class="controls">
				<input type="text" name="merchantName" value="${data.merchantName}"   readonly="readonly" class="input-medium" maxlength="100">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试用开始时间:</label>
			<div class="controls">
				<input type="text" id="beginProbationTime" name="beginProbationTime"  maxlength="100" class="input-mini Wdate"
				value="<fmt:formatDate value="${data.beginProbationTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endProbationTime\')||\'2020-10-01\'}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试用结束时间:</label>
			<div class="controls">
				<input id="endProbationTime" name="endProbationTime" type="text" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${data.endProbationTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'beginProbationTime\')}'});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<select style="width: 177px;" name="probationStatus">
					<option value="" <c:if test="${data.probationStatus eq ''}">selected="selected"</c:if>>正常用户</option>
					<option value="PROBATION" <c:if test="${data.probationStatus eq 'PROBATION'}">selected="selected"</c:if>>试用期</option>
					<option value="END" <c:if test="${data.probationStatus eq 'END'}">selected="selected"</c:if>>试用结束</option>
					<option value="RENEW" <c:if test="${data.probationStatus eq 'RENEW'}">selected="selected"</c:if>>续费</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电商状态:</label>
			<div class="controls">
				<select class="selectEnum"  name="eshopStatus" id="eshopStatus">
                    <option value="">全部</option>
                    <c:forEach items="${statuss}" var="item">
						<option value="${item}" <c:if test="${item eq data.eshopStatus}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
                </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电商网站:</label>
			<div class="controls">
				<input type="text" name="eshopUrl" value="${data.eshopUrl}"  class="input-medium url" maxlength="500">
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	</div>
</body>
</html>