<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券管理</title>
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
		<li class="active"><a href="${ctx}/cp/couponType/">优惠券类型管理</a></li>
		<li><a href="${ctx}/cp/couponType/form">优惠券类型添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cp/couponType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
	    <li><label>优惠券类型：</label>
		    	<input type="text" name="couponType" value="${findCouponTypePage.couponType}" class="input-medium" maxlength="100" placeholder="优惠券类型">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>优惠券类型</th>
			    <th>优惠券类型说明</th>
			    <th>是否启用</th>
			    <th>创建时间</th>
			    <shiro:hasPermission name="member:guid:edit">
			    <th>操作</th>
			    </shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
			   <td>
			   ${item.couponType}
				</td>
				
				<td>
					${item.couponRemark}
				</td>
				<td>
					${item.useEnable == "YES" ? "启用" : "禁用"}
				</td>
				 <td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
						<shiro:hasPermission name="member:guid:edit">
				<td>
					<a href="${ctx}/cp/couponType/form?code=${item.code}">修改</a>					
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