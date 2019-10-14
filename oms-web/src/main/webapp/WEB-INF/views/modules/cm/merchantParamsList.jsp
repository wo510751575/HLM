<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cm/merchantParams/">商户配置列表</a></li>		
			<li><a href="${ctx}/cm/merchantParams/form">商户配置添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/cm/merchantParams/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>系统参数名：</label>
				<input type="text" name="sysParamName" class="input-medium" value="${findMerchantParamsPage.sysParamName}" maxlength="30" placeholder="系统参数名">
			</li>
			<li><label>分组信息：</label>
				<input type="text" name="groupName" class="input-medium" value="${findMerchantParamsPage.groupName}" maxlength="30" placeholder="分组信息">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
				<th>系统参数名</th>
				<th>分组信息</th>
				<th>系统参数值</th>
				<th>参数信息备注</th>
				<th>是否仅运维<br>能修改</th>
				<th>创建时间</th>	
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.merchantName}
				</td>
				<td>
					 ${item.sysParamName}
				</td>
				<td>
					 ${item.groupName}
				</td>
				<td style="width:50%;word-wrap:break-word;word-break:break-all;" >
					 ${item.sysParamValue}
				</td>
				<td>
					 ${item.remark}
				</td>
				<td>
					 ${item.onlyAdminModify}
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>			
				<td>
					<a href="${ctx}/cm/merchantParams/form?code=${item.code}">修改</a>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>