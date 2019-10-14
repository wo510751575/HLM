<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务产品管理</title>
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
		<li class="active"><a href="${ctx}/member/serviceProduct/">服务产品列表</a></li>
			<li><a href="${ctx}/member/serviceProduct/form">服务产品添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/serviceProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>产品名称：</label>
		    	<input type="text" name="productName"  value="${findServiceProductPage.productName}" class="input-medium" maxlength="100" placeholder="产品名称">
			</li>
			<li><label>终端名称：</label>
				<input type="text" name="shopName" value="${findServiceProductPage.shopName}" class="input-medium" maxlength="100" placeholder="终端名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>分店名称</th>
				<th>关键字</th>
				<th>描述</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="member:pmType:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.productName}
				</td>
				<td>
					${item.shopName}
				</td>
				 <td>
					${item.keywords}
				</td>
				 <td>
					${item.description}
				</td>
				<td>
				    ${item.createId}
				</td>
				<td>
				    <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  <a href="${ctx}/member/serviceProduct/form?code=${item.code}">修改</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>