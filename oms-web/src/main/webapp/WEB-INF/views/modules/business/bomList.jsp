<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>产品管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
</script>
<style type="text/css">
	.container{
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
		<li class="active"><a href="${ctx}/business/bom/">产品列表</a></li>
		<shiro:hasPermission name="business:bom:edit">
			<li><a href="${ctx}/business/bom/form">产品添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/business/bom/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>产品名称：</label> <input type="text" name="bomName"
				class="input-medium" maxlength="100" placeholder="产品名称"></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="reset" onclick="CancelQuery()" value="重置" /></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<%-- <tags:message content="${message}" /> --%>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>产品名称</th>
				<!-- <th>产品编号</th>
				<th>型号</th>
				<th>价格</th>
				<th>所属分类</th> -->
				<th>备注</th>
				<shiro:hasPermission name="business:bom:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item" varStatus="number">
				<tr>
					<td>${item.bomName}</td>
					<!-- <td></td>
					<td></td>
					<td></td>
					<td></td> -->
					<td>${item.remark}</td>
					<shiro:hasPermission name="business:bom:edit">
						<td><a href="${ctx}/business/bom/form?code=${item.code}">修改</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>