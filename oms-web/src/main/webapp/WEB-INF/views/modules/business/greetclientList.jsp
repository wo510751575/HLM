<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>问候客户</title>
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
		<li class="active"><a href="${ctx}/business/greetclient/">客户问候列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/business/greetclient/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>消息标题：</label> 
				<input type="text" name="title" class="input-medium" maxlength="100" value="${findGreetClientPage.title}" placeholder="消息标题">
			</li>
			<li>
				<label>导购姓名：</label> 
				<input type="text" name="memberNameGm" class="input-medium" maxlength="100" value="${findGreetClientPage.memberNameGm}" placeholder="导购姓名">
			</li>
			
		    <li>
				<label>发送时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findGreetClientPage.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findGreetClientPage.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>		
			<li class="clearfix"></li>
		</ul>
	</form>
	<%-- <tags:message content="${message}" /> --%>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导购姓名</th>
				<th>消息标题</th>
				<th>消息内容</th>
				<th>发送范围</th>
				<th>发送时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item" varStatus="number">
				<tr>
					<td>${item.memberNameGm}</td>
					<td>${item.title}</td>
					<td>${item.content}</td>
					<td>
					<c:if test="${item.sendType=='SINGLE'}">单人</c:if> 
					<c:if test="${item.sendType=='ALL'}">所有人</c:if> 
					</td>	
					<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>			
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>