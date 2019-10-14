<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商家预设问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg);
				$("#repMsg").val("");
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ai/merchantPreProblem/">商家预设问题列表</a></li>		
			<li><a href="${ctx}/ai/merchantPreProblem/form">商家预设问题添加</a></li>	
			<li ><a href="${ctx}/ai/merchantPreProblem/selectAutoReplyList">托管列表</a></li>
		<li ><a href="${ctx}/ai/merchantPreProblem/toAddAutoReply">新增托管</a></li> 
	</ul>
	<form id="searchForm" action="${ctx}/ai/merchantPreProblem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>问题表述：</label>
				<input type="text" name="problemContent" class="input-medium" value="${findMerchantPreProblemPage.problemContent}" maxlength="2000" placeholder="问题表述">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>问题类型</th>
				<th>问题表述</th>
				<th>关键词</th>
				<th>回答内容</th>
				<th>状态</th>
				<th>范围</th>
				<shiro:hasPermission name="ai:merchantPreProblem:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  	 <c:if test="${'1' eq item.problemType}">预设类型</c:if>
					 <c:if test="${'2' eq item.problemType}">收录类型</c:if>
				</td>
				<td>
					 ${item.problemContent}
				</td>
				<td>
					 <c:forEach items="${item.words}" var="preWord" varStatus="number">
					 <c:if test="${number.last}"><c:if test="${not empty preWord.word}">${preWord.word}</c:if></c:if>
					 <c:if test="${not number.last}"><c:if test="${not empty preWord.word}">${preWord.word},</c:if></c:if>
					 </c:forEach>
				</td>
				<td>
					 ${item.answerContent}
				</td>
				<td>
					<c:if test="${'1' eq item.status}">正常</c:if>
					<c:if test="${'2' eq item.status}">禁用</c:if>
				</td>
				<td>
					<c:if test="${'1' eq item.isAll}">非全局</c:if>
					<c:if test="${'2' eq item.isAll}">全局</c:if>
				</td>
				<shiro:hasPermission name="ai:merchantPreProblem:edit">		
				<td>
					<a href="${ctx}/ai/merchantPreProblem/form?code=${item.code}">修改</a>
					<%-- &nbsp;&nbsp;<a href="${ctx}/ai/merchantPreProblem/delete?code=${item.code}" onclick="return confirmx('确认要删除该预设问题吗？', this.href)">删除</a>&nbsp;&nbsp; --%>
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