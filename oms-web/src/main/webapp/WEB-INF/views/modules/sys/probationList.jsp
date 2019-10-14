<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户试用信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/probation/">商户试用信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="findMerchantPage" action="${ctx}/sys/probation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>商户名 ：</label><form:input path="merchantName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>商户名称</th><th>试用开始时间</th><th>试用结束时间</th><th>状态</th><th>电商状态</th><th>电商网站</th><shiro:hasPermission name="sys:dict:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="data">
			<tr>
				<td>${data.merchantName}</td>
				<td><fmt:formatDate value="${data.beginProbationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${data.endProbationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${data.probationStatus eq ''}">正常用户</c:if>
					<c:if test="${data.probationStatus eq 'PROBATION'}">试用期</c:if>
					<c:if test="${data.probationStatus eq 'END'}">试用结束</c:if>
					<c:if test="${data.probationStatus eq 'RENEW'}">续费</c:if>
				</td>
				<td>
					<c:forEach items="${statuss}" var="item">
						<c:choose> 
							<c:when test="${item eq data.eshopStatus}">
								${item.name}
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<td>${data.eshopUrl}</td>
				<shiro:hasPermission name="sys:dict:edit"><td>
    				<a href="${ctx}/sys/probation/form?code=${data.code}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>