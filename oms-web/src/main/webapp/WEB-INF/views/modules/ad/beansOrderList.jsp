<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>豆子充值订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg);
				$("#repMsg").val("");
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
		.container{ padding: 10px 30px; width: 100%; min-height: 800px; background: #fff; -webkit-box-sizing: border-box; box-sizing: border-box; }
	</style>
</head>
<body>
<div class="container">
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ad/beansOrder/">豆子充值订单列表</a></li>		
		<li><a href="${ctx}/ad/beansOrder/form">豆子充值订单添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/ad/beansOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>手机号：</label>
				<input type="text" name="mobile" class="input-medium" value="${findBeansOrderPage.param.mobile}" maxlength="200" placeholder="手机号">
			</li>
			<li>
				<label>会员ID：</label>
				<input type="text" name="mobile" class="input-medium" value="${findBeansOrderPage.param.memberNoGuid}" maxlength="200" placeholder="会员ID">
			</li>
			<li>
				<label>会员名称：</label>
				<input type="text" name="mobile" class="input-medium" value="${findBeansOrderPage.param.memberNameGuid}" maxlength="200" placeholder="会员名称">
			</li>
			
			<li>
				<label>审核状态：</label>
				<select style="width: 177px;" name="status">
                    <option value="">全部</option>
                    <option value="WAIT" ${'WAIT' eq findBeansOrderPage.param.status ? 'selected="selected"' : '' }>待审</option>
                    <option value="PASS" ${'PASS' eq findBeansOrderPage.param.status ? 'selected="selected"' : '' }>通过</option>
                    <option value="UNPASS" ${'UNPASS' eq findBeansOrderPage.param.status ? 'selected="selected"' : '' }>拒绝</option>
                </select>
			</li>
			<li>
				<label>付款方式：</label>
				<select style="width: 177px;" name="payType">
                    <option value="">全部</option>
                    <option value="WX" ${'WX' eq findBeansOrderPage.param.payType ? 'selected="selected"' : '' }>微信</option>
                    <option value="ALI" ${'ALI' eq findBeansOrderPage.param.payType ? 'selected="selected"' : '' }>支付宝</option>
                    <option value="BANK" ${'BANK' eq findBeansOrderPage.param.payType ? 'selected="selected"' : '' }>银行卡</option>
                </select>
			</li>
			<li>
				<label>付款时间：</label>
				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findBeansOrderPage.param.beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findBeansOrderPage.param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员ID</th>
				<th>客户姓名（备注）</th>
				<th>手机号</th>
				<th>会员类型</th>
				<th>充值金额(单位：元)</th>
				<th>付款方式</th>
				<th>充值凭证</th>
				<th>充值时间</th>
				<th>审核状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="ad:beansOrder:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number">
			<c:set var="rwUserMap" value="${findRwUserMap[item.memberNoGuid] }" /> 
			<c:set var="guidMemberPageReturnMap" value="${findGuidMemberPageReturnMap[item.memberNoGuid] }" /> 
			<tr>
				<c:choose>
					<c:when test="${not empty guidMemberPageReturnMap }">
						<td>${guidMemberPageReturnMap.memberNo }</td>
						<td>${guidMemberPageReturnMap.memberName }</td>
						<td>${guidMemberPageReturnMap.mobile }</td>
						<td>商户</td>
					</c:when>
					<c:when test="${not empty rwUserMap }">
						<td>${rwUserMap.memberNo }</td>
						<td>${rwUserMap.nickName }</td>
						<td>${rwUserMap.mobile }</td>
						<td>${rwUserMap.userLevel eq '1' ? '普通用户' : rwUserMap.userLevel eq '2' ? '个体用户' : rwUserMap.userLevel eq '3' ? '商户' : '其他' }</td>
					</c:when>
					<c:otherwise>
						<td>${item.memberNoGuid }</td>
						<td>${item.memberNameGuid }</td>
						<td>${item.mobile }</td>
						<td></td>
					</c:otherwise>
				</c:choose>
				<td>${not empty item.amount ? item.amount / 100 : 0 }</td>
				<td>${item.payType eq 'WX' ? '微信' : item.payType eq 'ALI' ? '支付宝' : item.payType eq 'BANK' ? '银行卡' : '' }</td>
				<td><img alt="${item.payCert }" src="${item.payCert }" width="150" height="150"></td>
				<td>${'WAIT' eq item.status ? '待审' : 'PASS' eq item.status ? '通过' : 'UNPASS' eq item.status ? '拒绝' : '' }</td>
				<td nowrap><fmt:formatDate value="${item.payTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>${item.updateId }</td>
				<shiro:hasPermission name="ad:beansOrder:edit">		
				<td>
					<a href="${ctx}/ad/beansOrder/form?code=${item.code}">修改</a>
					<a href="${ctx}/ad/beansOrder/delete?code=${item.code}" onclick="return confirmx('确认要删除该豆子充值订单吗？', this.href)">删除</a>
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