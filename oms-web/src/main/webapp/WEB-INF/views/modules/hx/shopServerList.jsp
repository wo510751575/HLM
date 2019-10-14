<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开通服务列表</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.container { padding: 10px 30px; width: 100%; min-height: 800px; background: #fff; -webkit-box-sizing: border-box; box-sizing: border-box; }
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				width: 380,
			    height: 'auto',
			}
			$.jBox.setDefaults(jBoxConfig);
			
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
			});
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/hx/shop/server/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div class="container">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hx/shop/server/">开通服务列表</a></li>
		<li class=""><a href="${ctx}/hx/shop/server/form">开通服务</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/hx/shop/server" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>产品名称：</label>
		    	<input type="text" name="serverName" value="${paramShopServer.serverName}" class="input-medium" maxlength="100" placeholder="产品名称">
			</li>
		    <li><label>订单号：</label>
		    	<input type="text" name="orderNo" value="${paramShopServer.orderNo}" class="input-medium" maxlength="100" placeholder="订单号">
			</li>
		    <li><label>门诊名称：</label>
		    	<input type="text" name="merchantName" value="${paramShopServer.merchantName}" class="input-medium" maxlength="100" placeholder="门诊名称">
			</li>
		    <%-- <li><label>门诊名称：</label>
		    	<input type="text" name="shopName" value="${paramShopServer.shopName}" class="input-medium" maxlength="100" placeholder="门诊名称">
			</li> --%>
				
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>

			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>产品名称</th>
				<th>价格（单位：元）</th>
				<th>客户类型</th>
				<th>数量</th>
				<th>可用数量</th>
				<th>冻结次数</th>
				<!-- <th>描述</th> -->
				<!-- <th>产品说明</th> -->
				<!-- <th>机构</th> -->
				<th>门诊</th>
				<th>下单人</th>
				<th>下单人电话</th>
				<th>下单时间</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="info" varStatus="number">
			<c:set var="_temp_num" value="${fn:length(info.serverDetails) }" />
	 		<c:forEach items="${info.serverDetails }" var="record" varStatus="renumber">
	 		
			<tr>
				<c:if test="${renumber.index == 0 }">
				    <td rowspan="${_temp_num }" nowrap>${info.orderNo}</td>
				    <td rowspan="${_temp_num }" nowrap>${info.serverName}</td>
					<td rowspan="${_temp_num }" nowrap><fmt:formatNumber value="${info.price / 100}" pattern="#.00" /></td>
				</c:if>
					<td nowrap>${shopConfigMap[record.userType].lableName }</td>
					<td nowrap>${record.serverNum}</td>
					<td nowrap>${record.unuseNum}</td>
					<td nowrap>${record.freezeNum}</td>
					<%-- <td>${record.serverDesc}</td> --%>
				<c:if test="${renumber.index == 0 }">
					<td rowspan="${_temp_num }">${info.merchantName}</td>
					<%-- <td rowspan="${_temp_num }">${info.shopName}</td> --%>
					<%-- <td rowspan="${_temp_num }">${info.ctx}</td> --%>
					<td rowspan="${_temp_num }">${not empty info.memberNameGuid ? info.memberNameGuid : info.updateId}</td>
					<td rowspan="${_temp_num }">${info.mobile}</td>
					<td rowspan="${_temp_num }" nowrap><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</c:if>
			</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
	
</body>
</html>