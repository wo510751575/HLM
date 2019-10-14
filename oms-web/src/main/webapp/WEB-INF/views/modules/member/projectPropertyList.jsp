<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务项目属性管理</title>
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
		<li class="active"><a href="${ctx}/member/projectProperty/?projectCode=${findProjectPropertyPage.projectCode}">服务属性列表</a></li>
			<li><a href="${ctx}/member/projectProperty/form?projectCode=${findProjectPropertyPage.projectCode}">服务属性添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/projectProperty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="projectCode" name="projectCode" type="hidden" value="${findProjectPropertyPage.projectCode}"/>
		<ul class="ul-form">
		    <li><label>属性名称：</label>
		    	<input type="text" name="propertyName"  value="${findProjectPropertyPage.propertyName}" class="input-medium" maxlength="100" placeholder="属性名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>属性名称</th>
				<th>取值范围</th>
				<th>显示序号</th>
				<th>项目名称</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.propertyName}
				</td>
				<td>
					${item.valueRange}
				</td>
				 <td>
					${item.showIndex}
				</td>
			     <td>
					${item.projectName}
				</td>
				<td>
				    ${item.createId}
				</td>
				<td>
				   <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  <a href="${ctx}/member/projectProperty/form?code=${item.code}&projectCode=${findProjectPropertyPage.projectCode}">修改</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>