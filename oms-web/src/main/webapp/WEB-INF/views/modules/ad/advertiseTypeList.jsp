<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告类型管理</title>
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
		<li class="active"><a href="${ctx}/ad/advertiseType/">广告类型列表</a></li>		
		<li><a href="${ctx}/ad/advertiseType/form">广告类型添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/ad/advertiseType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>类型：</label>
				<input type="text" name="typeName" class="input-medium" value="${findAdvertiseTypePage.param.typeName}" maxlength="50" placeholder="类型">
			</li>
			
			<li>
				<label>状态：</label>
				<select style="width: 177px;" name="status">
                    <option value="">全部</option>
                    <option value="Y" ${'Y' eq findAdvertiseTypePage.param.status ? 'selected="selected"' : '' }>启用</option>
                    <option value="N" ${'N' eq findAdvertiseTypePage.param.status ? 'selected="selected"' : '' }>停用</option>
                </select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型名称</th>
				<th>状态</th>
				<th>排序</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ad:advertiseType:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>${item.typeName }</td>
				<td>${'Y' eq item.status ? '启用' : 'N' eq item.status ? '停用' : '' }</td>
				<td>${item.seq }</td>
				<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="ad:advertiseType:edit">		
				<td>
					<a href="${ctx}/ad/advertiseType/form?code=${item.code}">修改</a>
					<a href="${ctx}/ad/advertiseType/delete?code=${item.code}" onclick="return confirmx('确认要删除该广告类型吗？', this.href)">删除</a>
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