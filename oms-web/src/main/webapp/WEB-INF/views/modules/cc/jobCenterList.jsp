<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务列表</title>
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
		<li class="active"><a href="${ctx}/cc/jobCenter/">定时任务列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cc/jobCenter/" method="post" class="breadcrumb form-search">
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
				<th>定时任务中文描述</th>
				<th>所属系统</th>
				<th>任务接口</th>
				<th>任务参数</th>
				<th>是否启用</th>
				<th>定时任务配置</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="data" varStatus="number"> 
			<tr>
				<td>
					${data.jobEnglishName}
				</td>
				<td>
					${data.jobName}
				</td>
				<td>
					${data.systemAliasName}
				</td>
					<td>
					${data.jobIntf}
				</td>
					<td>
					${data.jobParam}
				</td>
				 <td>
					${data.isEnable}
				</td>
			    <td>
					${data.jobCalender}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>