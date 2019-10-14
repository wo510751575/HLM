<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户反馈管理</title>
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
		function CancelQuery(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		
// 			$("#pageNo").val(1);
// 			$("#searchForm").submit();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/pmLabel/">客户反馈列表</a></li>
<%-- 		<shiro:hasPermission name="member:member:edit"> --%>
<%-- 			<li><a href="${ctx}/member/pmLabel/form">新增客户反馈</a></li> --%>
<%-- 		</shiro:hasPermission> --%>
	</ul>
	<form id="searchForm" action="${ctx}/memeber/pmLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 100px;">反馈名称：</label>
				<input type="text" name="labelName" class="input-medium" maxlength="100" placeholder="反馈名称">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>反馈名称</th>
				<th>创建人</th>
				<th>创建时间</th>
<%-- 				<shiro:hasPermission name="member:pmLabel:edit"> --%>
				<th>操作</th>
<%-- 				</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.labelName}
				</td>
				<td>
					${item.createId}
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- 				<shiro:hasPermission name="member:guid:edit"> --%>
				<td>
					<a href="${ctx}/member/pmLabel/form?code=${item.code}">修改</a>
				</td>
				<%-- 				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>