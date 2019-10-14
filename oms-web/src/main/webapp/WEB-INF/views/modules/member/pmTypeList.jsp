<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户分类管理</title>
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
	.label-success{
	background-color: #2ecc71;
	}
	.label-danger{
	background-color: #e74c3c;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/pmType/">客户分类列表</a></li>
		<shiro:hasPermission name="member:pmType:edit">
			<li><a href="${ctx}/member/pmType/form">客户分类添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/member/pmType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户分类：</label>
				<input type="text" name="typeName" value="${findPmTypePage.typeName}" class="input-medium" maxlength="100" placeholder="客户分类名称">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
<!-- 				<th>分类编码</th> -->
				<th>分类名称</th>
				<th>分类排序值</th>
<!-- 				<th>跟进频率值(小时)</th> -->
				<th>状态</th>
				<shiro:hasPermission name="member:pmType:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
<!-- 				<td> -->
<%-- 					${item.pmTypeType} --%>
<!-- 				</td> -->
				<td>
					${item.typeName}
				</td>
				<td>
					${item.seq}
				</td>
<!-- 				 <td> -->
<%-- 					${item.freValue} --%>
<!-- 				</td> -->
				<td>
						<span class="label label-${item.status eq 'Y'?'success':'danger'}">${item.status eq 'Y'?'启用':'禁用'}</span>
				</td>	
				<shiro:hasPermission name="member:pmType:edit">
					<td>
						<a href="${ctx}/member/pmType/form?code=${item.code}">修改</a>
						<a href="${ctx}/member/pmType/edit?code=${item.code}&status=${item.status eq 'N'?'Y':'N'}&typeName=${item.typeName}"onclick="return confirmx('确认要${item.status eq 'N'?'启用':'禁用'}该分类吗？', this.href)">${item.status eq 'N'?'启用':'禁用'}</a>			  				
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