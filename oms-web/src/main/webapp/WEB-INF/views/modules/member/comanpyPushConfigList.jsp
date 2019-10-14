<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商推送配置列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".copyBtn").click(function(){
				$(this).attr("data-clipboard-text",window.location.href+"/viewH5?code="+$(this).attr("data-code"));
			})
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
		
			$("#pageNo").val(1);
			$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/member/comanpyPushConfig/list">经销商推送配置列表</a></li>
		<shiro:hasPermission name="member:comanpyPushConfig:edit">
			<li><a href="${ctx}/member/comanpyPushConfig/addView">经销商推送配置添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/business/material/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form"> -->
		    <%-- <li><label>导购姓名：</label>
		    	<input type="text" name="memberNameGm" class="input-medium" maxlength="100" value="${findMaterialPage.memberNameGm}" placeholder="导购姓名">
			</li>
			<li><label>标题：</label>
				<input type="text" name="title" class="input-medium" maxlength="100" value="${findMaterialPage.title}" placeholder="标题">
			</li>
			
			<li><label>类型：</label>
				<input type="text" name="materialTypeName" class="input-medium" maxlength="100" value="${findMaterialPage.materialTypeName}" placeholder="类型">
			</li> --%>
			
		<!-- 	<li><label>类型：</label>
				<select name="status">
					<option value="1">正常</option>
					<option value="1">注销</option>
					<option value="1">冻结</option>
				</select>
			</li> -->
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li> -->
			<li class="clearfix"></li>
		<!-- </ul> -->
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>经销商</th>
				<th>类型</th>
				<th>内容</th>
				<th>排序</th>
				<th>状态</th>
				<th>添加时间</th>
				<shiro:hasPermission name="member:comanpyPushConfig:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.companyName}
				</td>
				<td>
					<c:if test="${item.type eq 'GREET'}">话术、问候语</c:if>
					<c:if test="${item.type eq 'IMAGE'}">图片</c:if>
					<c:if test="${item.type eq 'MGR_CARD'}">店长名片</c:if>
				</td>
				<td>
					<c:if test="${item.type eq 'GREET'}">${item.content}</c:if>
					<c:if test="${item.type eq 'IMAGE'}">
						<img width="120px" height="120px" src="${fns:getUploadUrl()}${item.content}" />
					</c:if>
				</td>
				<td>
					${item.sort}
				</td>
				<td>
					<c:if test="${item.status eq 'USE'}">有效</c:if>
					<c:if test="${item.status eq 'STOP'}">失效</c:if>
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:comanpyPushConfig:edit">
				<td>
					<a href="${ctx}/member/comanpyPushConfig/editView?code=${item.code}">编辑</a>
					<a href="${ctx}/member/comanpyPushConfig/delete?code=${item.code}" onclick="return delConfirm()">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/common/clipboard.min.js"></script>
	<script type="text/javascript">
		function delConfirm() {
			var result = window.confirm("确认删除该记录？");
			return result;
		}
	</script>
</body>
</html>