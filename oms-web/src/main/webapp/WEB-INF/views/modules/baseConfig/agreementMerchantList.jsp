<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务协议管理</title>
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
		<li class="active"><a href="${ctx}/baseConfig/agreementMerchant">服务协议列表</a></li>
		<shiro:hasPermission name="baseConfig:agreementMerchant:edit">
			<li><a href="${ctx}/baseConfig/agreementMerchant/form">服务协议添加</a></li>
		</shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>服务协议类型</th>
				<th>服务协议</th>
			<%-- 	<shiro:hasPermission name="baseConfig:agreementMerchant:edit"> --%>
				<th>操作</th>
			<%-- 	</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
				<c:forEach items="${AgreementTypes}" var="status">
				<c:if test="${item.agreementType eq status}">${status.name}</c:if>
				</c:forEach>
			    </td>
				<td>
					<div title="${item.agreement }">${fns:abbr(item.agreement,45)}</div>  
				</td>
				<shiro:hasPermission name="baseConfig:agreementMerchant:edit">
				<td>
					<a href="${ctx}/baseConfig/agreementMerchant/form?code=${item.code}">修改</a>
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