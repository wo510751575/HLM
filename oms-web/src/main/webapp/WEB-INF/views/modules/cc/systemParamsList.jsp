<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统参数列表</title>
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
		<li class="active"><a href="${ctx}/cc/systemParams/">系统参数列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cc/systemParams/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属系统：</label>
				<input type="text" name="systemAliasName" class="input-medium" value="${param.systemAliasName}" maxlength="30" placeholder="所属系统">
			</li>
			<li><label>分组信息：</label>
				<input type="text" name="groupName" class="input-medium" value="${param.groupName}" maxlength="30" placeholder="分组信息">
			</li>
			<li><label>系统参数名：</label>
				<input type="text" name="sysParamName" class="input-medium" value="${param.sysParamName}" maxlength="30" placeholder="系统参数名">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>系统参数名</th>
				<th>分组信息</th>
				<th>系统参数值</th>
				<th>参数信息备注</th>
				<th>所属系统</th>
				<th>是否运维修改</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="data" varStatus="number"> 
			<tr>
				<td>
					${data.sysParamName}
				</td>
				<td>
					${data.groupName}
				</td>
				<td>
					${data.sysParamValue}
				</td>
	            <td>
					${data.remark}
				</td>
				 <td>
					${data.systemAliasName}
				</td>
				 <td>
					${data.onlyAdminModify}
				</td>
	
				<td>
					<a href="${ctx}/cc/systemParams/from?groupName=${data.groupName}&sysParamName=${data.sysParamName}">修改</a>					
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>