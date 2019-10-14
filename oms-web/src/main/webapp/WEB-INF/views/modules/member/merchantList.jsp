<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户管理</title>
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
		<li class="active"><a href="${ctx}/member/merchant/">商户列表</a></li>
<%-- 		<shiro:hasPermission name="member:guid:edit"> --%>
			<li><a href="${ctx}/member/guid/form">商户导入</a></li>
<%-- 		</shiro:hasPermission> --%>
	</ul>
	<form id="searchForm" action="${ctx}/member/merchant/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员号：</label>
				<input type="text" name="memberNo" class="input-medium" maxlength="100" placeholder="会员号">
			</li> 
		    <li><label>会员姓名：</label>
		    	<input type="text" name="memberName" class="input-medium" maxlength="100" placeholder="会员姓名">
			</li>
			<li><label>所属行业：</label>
				<input type="text" name=mobile class="input-medium" maxlength="100" placeholder="所属行业">
			</li>
			
			<li><label>状态：</label>
				<select name="status">
					<option value="1">正常</option>
					<option value="1">注销</option>
					<option value="1">冻结</option>
				</select>
<!-- 			</li> -->
<!-- 			<li><label>性别：</label> -->
<%-- 				<form:select path="gender" id="gender"  style="width: 120px;"> --%>
<%-- 					<form:option value="" label="请选择"/> --%>
<%-- 					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 				</form:select> --%>
<!-- 			</li> -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>会员号</th>
				<th>商户姓名</th>
				<th>所属行业</th>
				<th>邮箱</th>
				<th>住址</th>
				<th>状态</th>
				<shiro:hasPermission name="member:guid:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
			    <td>
					<a href="${ctx}/member/guid/form?id=${member.code}">${member.merchantNo}</a>
				</td>
				<td>
					${member.address2}
				</td>
				<td>
					${member.email}
				</td>
				<td>
					${member.address}
				</td>
				<td>
					${member.status}
				</td>
				<shiro:hasPermission name="member:guid:edit"><td>
					<a href="${ctx}/marketing/groupBuy/form?id=${groupBuy.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>