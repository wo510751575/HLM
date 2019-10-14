<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行业管理</title>
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
		<li class="active"><a href="${ctx}/baseConfig/line/">行业列表</a></li>
		<shiro:hasPermission name="business:line:edit">
			<li><a href="${ctx}/baseConfig/line/form">新增行业</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/baseConfig/line/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
		    <li><label>行业名称：</label>
		    	<input type="text" name="name" class="input-medium" maxlength="100" placeholder="名称" value="${findMemLinePage.name }">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>备注</th>
				<shiro:hasPermission name="business:line:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
			    <td>
					<a href="${ctx}/baseConfig/line/form?code=${item.code}">${item.name}</a>
				</td>
				<td>
					${item.remark}
				</td>
				<shiro:hasPermission name="business:line:edit">
				<td>
					<a href="${ctx}/baseConfig/line/form?code=${item.code}">修改</a>
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