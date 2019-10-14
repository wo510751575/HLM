<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>好友设置列表</title>
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
		<li class="active"><a href="${ctx}/setFriendNum/">好友设置列表</a></li>
		<li><a href="${ctx}/setFriendNum/form">新增好友设置</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/setFriendNum/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
			
		<ul class="ul-form">
			<li><label>微信：</label> <input type="text" name="alias" value="${findSetFriendNumPage.param.alias}"
				class="input-medium" maxlength="100" placeholder="微信"></li>
				
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
				
				<th>微信</th>
				<th>好友通过时间段</th>
				<th>好友通过数量/天</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item" varStatus="number">
				<tr>
					<td>${item.alias}</td>
					<td><fmt:formatDate value="${item.startDate}" pattern="HH:mm"/> - <fmt:formatDate value="${item.endDate}" pattern="HH:mm"/></td>
					<td>${item.num}</td>
					<td><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="${ctx}/setFriendNum/form?code=${item.code}">修改
					<a href="${ctx}/setFriendNum/del?code=${item.code}&noWx=${item.noWx}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>