<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/list");
			$("#searchForm").submit();
	    	return false;
	    }
		
		function updateUser(id){
			var searchCompanyId = $("#companyId").val() ;
			var searchOfficeId = $("#officeId").val();
			var searchName = $("#name").val();
			var searchLoginName = $("#loginName").val();
			var id = id;
			location.href="${ctx}/sys/user/form?id="+id+"&searchCompanyId="+searchCompanyId+"&searchOfficeId="+searchOfficeId+"&searchName="+searchName+"&searchLoginName="+searchLoginName;
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<tags:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>归属公司：</label><tags:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
			<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>归属部门：</label><tags:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员ID</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th class="sort-column login_name">登录名</th>
				<th class="sort-column name">姓名</th>
				<th>电话</th>
				<th>手机</th>
				
				<th>会员类型</th>
				<th>会员开通时间</th>
				<th>会员到期时间</th>
				<th>剩余广告豆</th>
				
				<th>账号状态</th>
				<th>注册日期</th>
				
				<th>角色</th>
				<shiro:hasPermission name="sys:user:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.company.name}</td>
				<td>${user.office.name}</td>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<c:choose>
					<c:when test="${not empty returnGuidMemberRwMap and not empty returnGuidMemberRwMap[user.id] }">
						<td>${returnGuidMemberRwMap[user.id].userLevel eq 1 ? '普通会员' : returnGuidMemberRwMap[user.id].userLevel eq 2 ? '推广会员' : returnGuidMemberRwMap[user.id].userLevel eq 3 ? '企业会员' : '' }</td>
						<td><fmt:formatDate value="${returnGuidMemberRwMap[user.id].openLevelDate }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${returnGuidMemberRwMap[user.id].endLevelDate }" pattern="yyyy-MM-dd"/></td>
					</c:when>
					<c:otherwise>
						<td></td>
						<td></td>
						<td></td>
					</c:otherwise>
				</c:choose>
				<td>${not empty returnRwUserBeansMap ? returnRwUserBeansMap[user.id].beansNormal : '' }</td>

				<td>${user.delFlag eq 0 ? '正常' : user.delFlag eq 2 ? '审核' : '' }</td>
				<td><fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd"/></td>
				
				<td>${user.roleNames}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a  onclick="updateUser('${user.id}')">修改</a>
					<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('删除用户之后，用户将无法使用客户直通车。', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>