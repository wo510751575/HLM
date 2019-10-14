<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务类型管理</title>
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
		<li class="active"><a href="${ctx}/member/serviceTypeChoose/">服务类型列表</a></li>
			<li><a href="${ctx}/member/serviceTypeChoose/form">服务类型添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/serviceTypeChoose/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>服务名称：</label>
		    	<input type="text" name="serviceName"  value="${findServiceTypeChoosePage.serviceName}" class="input-medium" maxlength="40" placeholder="服务名称">
			</li>
			<li><label>终端名称：</label>
				<input type="text" name="shopName" value="${findServiceTypeChoosePage.shopName}" class="input-medium" maxlength="100" placeholder="终端名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>显示序号</th>
				<th>服务名称</th>
				<th>服务类型</th>
				<th>终端名称</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.showIndex}
				</td>
				<td>
					${item.serviceName}
				</td>
				 <td>
				 <c:forEach items="${serviceTypes}" var="type">
				 <c:if test="${item.serviceType eq type}">${type.name}</c:if>
				 </c:forEach>
			    </td>
				
			    <td>
				    ${item.shopName}
				</td>
				<td>
				    ${item.createId}
				</td>
				<td>
				    <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  <a href="${ctx}/member/serviceTypeChoose/form?code=${item.code}">修改</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>