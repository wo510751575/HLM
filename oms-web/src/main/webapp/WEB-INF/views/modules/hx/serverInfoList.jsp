<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务维护</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.container { padding: 10px 30px; width: 100%; min-height: 800px; background: #fff; -webkit-box-sizing: border-box; box-sizing: border-box; }
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				width: 380,
			    height: 'auto',
			}
			$.jBox.setDefaults(jBoxConfig);
			
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg, "error", 5000);
				$("#repMsg").val("");
			}
			
			var importMsg=$("#importMsg").val();
			if(importMsg){
				$.jBox.success(importMsg, '提示');
				$("#importMsg").val("");
			}
			
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
			});
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/hx/serverInfo/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div class="container">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hx/serverInfo/">服务列表</a></li>
		<li class=""><a href="${ctx}/hx/serverInfo/form">新增服务</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/hx/serverInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>产品名称：</label>
		    	<input type="text" name="serverName" value="${paramServerInfo.serverName}" class="input-medium" maxlength="100" placeholder="产品名称">
			</li>
				
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>

			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>价格（单位：元）</th>
				<th>客户类型</th>
				<th>数量</th>
				<th width="400px">描述</th>
				<th>产品说明</th>
				<th>状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="hx:serverinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="info" varStatus="number">
			<c:set var="_temp_num" value="${fn:length(info.serverDetails) }" />
	 		<c:forEach items="${info.serverDetails }" var="record" varStatus="renumber">
	 		
			<tr>
				<c:if test="${renumber.index == 0 }">
				    <td rowspan="${_temp_num }" nowrap>${info.serverName}</td>
					<td rowspan="${_temp_num }" nowrap><fmt:formatNumber value="${info.price / 100}" pattern="#.00" /></td>
				</c:if>
					<td nowrap>${shopConfigMap[record.userType].lableName }</td>
					<td nowrap>${record.serverNum}</td>
					<td>${record.serverDesc}</td>
				<c:if test="${renumber.index == 0 }">
					<td rowspan="${_temp_num }">${info.ctx}</td>
					<td rowspan="${_temp_num }" nowrap>${info.status == 'use' ? '启用' : info.status == 'unuse' ? '禁用' : ''}</td>
					<td rowspan="${_temp_num }" nowrap><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<shiro:hasPermission name="hx:serverinfo:edit">
						<td rowspan="${_temp_num }">
							<a href="${ctx}/hx/serverInfo/form?code=${info.code}">修改</a>
							<a href="${ctx}/hx/serverInfo/editByStatus?code=${info.code}&status=${info.status eq 'use' ? 'unuse' : 'use' }" onclick="return confirmx('确定是否【${info.status eq 'use' ? '禁用' : '启用'}】吗？', this.href)">${info.status eq 'use' ? '禁用' : '启用'}</a>
						</td>
					</shiro:hasPermission>
				</c:if>
			</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
	
</body>
</html>