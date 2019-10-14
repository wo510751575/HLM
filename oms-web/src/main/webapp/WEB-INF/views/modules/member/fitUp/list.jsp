<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>装修需求信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery(){
		$(':input','#searchForm')  
		 .not(':button, :submit, :reset')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected'); 
	
		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
</script>
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
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/fitUp">装修需求信息列表</a></li>
		</ul>
		<form id="searchForm" action="${ctx}/fitUp" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" /> 
			<ul class="ul-form">
				<li><label>小区全称：</label> 
				<input type="text" name="fullName"
					class="input-medium" maxlength="150"
					value="${findFitUpInfoPage.fullName}" placeholder="小区全称"></li>

				<li><label>手机号码：</label> 
				<input type="text" name="mobile"
					class="input-medium" maxlength="150"
					value="${findFitUpInfoPage.mobile}" placeholder="手机号码"></li>
			</ul>
			<ul class="ul-form">
				<li><label>户型:</label><select type="text" id="houseType"
					name="houseType" class="input-medium" style="width: 180px">
						<option value="">请选择</option>
						<c:forEach var="item" items="${fitUpHouseType}">
							<option value="${item}" <c:if test="${item eq findFitUpInfoPage.houseType}">selected="selected"</c:if>>${item.chName}</option>
						</c:forEach>
				</select></li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary"
					type="submit" value="查询" onclick="return page();" /></li>
				<li class="clearfix"></li>
			</ul>


		</form>
		<tags:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					
					<th>手机号码</th>
					<th>装修风格</th>
					<th>小区全称</th>
					<th>户型</th>
					<th>需求描述</th>
					<th>需求图片</th>
					<th>填写日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="item">
					<tr>
						<td>${item.mobile}</td>
						
						<td>
						<c:forEach items="${fitUpStyle}" var="s">
								<c:if test="${s eq item.style}">${s.chName}</c:if>
							</c:forEach>
						</td>
						
						<td>${item.fullName}</td>
						<td>
						<c:forEach items="${fitUpHouseType}" var="s">
								<c:if test="${s eq item.houseType}">${s.chName}</c:if>
							</c:forEach>
						</td>
						<td>${item.remark}</td>
						<td>
						
						<c:forEach items="${item.imgAddrList}" var="s">
								<a href="${s}" target="_blank">查看图片</a>&nbsp;&nbsp;
							</c:forEach>
						</td>
						<td><fmt:formatDate value="${item.createDate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>