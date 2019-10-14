<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加粉任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	</script>
	<style type="text/css">
	.form-search .ul-form li label{width: 96px;}
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
			<li class="active"><a href="${ctx}/member/addFriendsTask/selectAddFriendsTaskList">任务列表</a></li>
			<li><a href="${ctx}/member/addFriendsTask/toAddFriendsTaskPage">新增任务</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/addFriendsTask/selectAddFriendsTaskList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务名称：</label>
				<input type="text" name="param.name" class="input-medium" maxlength="80" value="${findAddFriendsTaskPage.param.name}">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
		<!-- 	<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="下载模板"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务名称</th>
				<th>任务微信</th>
				<th>号码数量</th>
				<th>已执行数量</th>
				<th>成功添加好友量</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${page.list}" var="task" varStatus="number"> 
			<tr>
				<td>${task.name}</td>
				<td>${task.noWxArrays}</td>
				<td>${task.totalPhonenum}</td>
				<td>${task.completeNum}</td>
				<td>${task.successNum}</td>
				<td>
					<fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="${ctx}/member/addFriendsTask/selectAddFriendsTaskDetailList?param.taskCode=${task.code }">查看</a>
				</td>
				</tr>
		 </c:forEach> 
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
