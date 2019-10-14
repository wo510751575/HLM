<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户运营日报</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//导出
			$("#btnExport").click(function(){
				if(!$("#infolist tr").length){
					alertx("没有数据无法导出!");
					return false;
				}
				top.$.jBox.confirm("确认要导出商户运营日报数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/st/merchantOperationDayReport/export?export=1");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/st/merchantOperationDayReport");
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
	#contentTable th{
		text-align: center;
		vertical-align: middle;
		border:1px solid grey;!important;
	}
	#contentTable td{
		text-align: right;
		vertical-align: middle;
		border:1px solid grey;!important;
	}
	</style>
</head>
<body>
<div class="container">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/st/merchantOperationDayReport">商户运营日报</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/st/merchantOperationDayReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				<input id="reportDateBegin" name="reportDateBegin" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
 				value="<fmt:formatDate value="${findMerchantOperationDayReportPage.reportDateBegin}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;
				-&nbsp; <input id="reportDateEnd" name="reportDateEnd" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate" 
 				value="<fmt:formatDate value="${findMerchantOperationDayReportPage.reportDateEnd}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<shiro:hasPermission name="st:merchantOperationDayReport:view">
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul> 
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2">序号</th>
				<th rowspan="2">日期</th>
				<th colspan="2">客户情况</th>
				<th colspan="6">下单情况</th>
				<th colspan="6">成交情况</th>
				<th colspan="4">客户访问情况</th>
				<th>其他</th>
			</tr>
			<tr>
				<th>总客户数</th>
				<th>今日新增</th>
				<th>商品件数</th>
				<th>订单数</th>
				<th>订单总额</th>
				<th>客单价</th>
				<th>优惠总额</th>
				<th>实际支付总额</th>
				<th>商品件数</th>
				<th>订单数</th>
				<th>订单总额</th>
				<th>客单价</th>
				<th>优惠总额</th>
				<th>实际支付总额</th>
				<th>商城主页<br>访问次数</th>
				<th>商城主页<br>访问人数</th>
				<th>商品详情页<br>访问次数</th>
				<th>商品详情页<br>访问人数</th>
				<th>短信数量</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td style="text-align: center;">${number.count}</td>
				<td style="text-align: center;"><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>
				<td>${item.mbrTotalCount}</td>
				<td>${item.mbrTodayCount}</td>
				<td>${item.ordGoodCount}</td>
				<td>${item.ordOrderCount}</td>
				<td><fmt:formatNumber value="${item.ordOrderAmount/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.ordCustomerPrice/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.ordActivitieAmount/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.ordPayAmount/100}" type="currency" pattern="#0.00"/></td>
				<td>${item.succGoodCount}</td>
				<td>${item.succOrderCount}</td>
				<td><fmt:formatNumber value="${item.succOrderAmount/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.succCustomerPrice/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.succActivitieAmount/100}" type="currency" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.succPayAmount/100}" type="currency" pattern="#0.00"/></td>
				<td>${item.pvIndexTotalCount}</td>
				<td>${item.pvIndexMemberCount}</td>
				<td>${item.pvDetailTotalCount}</td>
				<td>${item.pvDetailMemberCount}</td>
				<td>${item.smsCount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<br>
	<br>
	<br>
	
</div>	
</body>
</html>