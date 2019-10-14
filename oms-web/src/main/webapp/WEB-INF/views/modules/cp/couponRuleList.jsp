<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券规则管理</title>
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
		<li class="active"><a href="${ctx}/cp/coupon/rule">优惠券规则列表</a></li>		
			<li><a href="${ctx}/cp/coupon/rule/form">优惠券规则添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/cp/coupon/rule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>分店名称：</label>
				<input type="text" name="shopName" class="input-medium" value="${findCouponRulePage.shopName}" maxlength="100" placeholder="分店名称">
			</li> --%>
			<li><label>优惠券名称：</label>
				<input type="text" name="couponName" class="input-medium" value="${findCouponRulePage.couponName}" maxlength="100" placeholder="优惠券名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>总数</th>
				<th>面额</th>
				<th>优惠券开始时间</th>
				<th>优惠券结束时间</th>
				<th>使用金额门槛</th>
				<th>类型</th>
				<th>说明</th>
				<th>发券条件</th>
				<th>是否实名</th>
				<th>状态</th>
				<th>创建人</th>
				<th>创建时间</th>	
				<th>操作</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
					 ${item.couponName}
				</td>
				<td>
					 ${item.couponNum}
				</td>
				<td>
					<fmt:formatNumber value="${item.couponNotes}" pattern="0"/>
				</td>
				<td>
					<fmt:formatDate value="${item.beginDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatNumber value="${item.couponMax}" pattern="0"/>
				</td>
				<td>
					 ${item.couponType}
				</td>
				<td>
					 ${item.couponRemark}
				</td>
				<td>
					 ${item.toCoupon == "NONE" ? "无" : "邀请人数"}
				</td>
				<td>
					 ${item.realName == "YES" ? "实名" : "不实名"}
				</td>
				<td>
					 <span class="label label-${item.ruleStatus eq 'YES'?'success':'danger'}">${item.ruleStatus eq 'YES'?'启用':'禁用'}</span>
				</td>
				<td>
					${item.createId}
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>			
				<td>
					<a href="${ctx}/cp/coupon/rule/updataStatus?code=${item.code}&ruleStatus=${item.ruleStatus eq 'NO'?'YES':'NO'}">${item.ruleStatus eq 'NO'?'启用':'禁用'}</a> 
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>