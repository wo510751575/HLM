<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>job执行日志列表</title>
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
		<li class="active"><a href="${ctx}/cc/jobExecuteLog/">任务执行日志查询</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cc/jobExecuteLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<%-- 		<ul class="ul-form">
			<li><label>手机号：</label>
				<input type="text" name="mobile" class="input-medium" maxlength="11" placeholder="手机号" value="${paramGuid.mobile}">
			</li>
		</ul> --%>
<!-- 		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>定时任务标识</th>
				<th>执行信息</th>
				<th>执行状态</th>
				<th>执行时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="data" varStatus="number"> 
			<tr>
				<td>
					${data.jobEnglishName}
				</td>
				<td>
					${data.jobExecuteInfo}
				</td>
				<td>
					${data.jobExecuteStatus}
				</td>
	            <td>
					<fmt:formatDate value="${data.jobExecuteTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>