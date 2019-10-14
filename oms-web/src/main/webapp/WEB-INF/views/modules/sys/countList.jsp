<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/count");
			$("#searchForm").submit();
	    	return false;
	    }
		
		$(document).ready(function() {
			$("#btnExport").click(function(){
				if(!$("#infolist tr").length){
					alertx("没有数据无法导出!");
					return false;
				}
				top.$.jBox.confirm("确认要导出客户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/sys/user/count");
					}
				},{buttonsFocus:1});
			});
			
		});
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/count">员工统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/count" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<tags:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>员工姓名：</label><input type="text" name="name" class="input-medium" value="${findUserCountPage.name}" maxlength="200" placeholder="员工姓名"></li>
			<%-- <li><label>员工ID：</label><input type="text" name="userId" class="input-medium" value="${user.userId}" maxlength="200" placeholder="员工ID"></li> --%>
			<li><label>归属公司：</label><input type="text" name="company" class="input-medium" value="${findUserCountPage.company}" maxlength="200" placeholder="归属公司"></li>
			<li><label>归属部门：</label><input type="text" name="office" class="input-medium" value="${findUserCountPage.office}" maxlength="200" placeholder="归属部门"></li>
			<li class="clearfix"></li>
			<li><label>所属终端：</label><input type="text" name="shopWx" class="input-medium" value="${findUserCountPage.shopWx}" maxlength="200" placeholder="所属终端"></li>
			<li>
				<label>统计时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findUserCountPage.startTime}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findUserCountPage.endTime}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<!-- <th>员工ID</th> -->
			<th>员工姓名</th>
			<th>归属公司</th>
			<th>归属部门</th>
			<th>所属终端</th>
			<th>客户总量</th>
			<th>新增客户量</th>
			<th>手机号码客户量</th>
			<th>新增手机号码客户量</th>
			<th>聊天客户量</th>
			<th>未聊天客户量</th>
			<th>聊天条数</th>
		</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="countPage">
			<tr>
				<%-- <td>${countPage.userId }</td> --%>
				<td>${countPage.name }</td>
				<td>${countPage.company }</td>
				<td>${countPage.office }</td>
				<td>${countPage.shopWx }</td>
				<td>${countPage.totalMember }</td>
				<td>${countPage.newMember }</td>
				<td>${countPage.totalMemberPhone }</td>
				<td>${countPage.newMemberPhone }</td>
				<td>${countPage.chatCount }</td>
				<td>${countPage.unChatCount }</td>
				<td>${countPage.chatTotal }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>