<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>银行账户</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	 	var repMsg=$("#repMsg").val();
    	if(repMsg){
    		showTip(repMsg);
    		$("#repMsg").val("");
    	}

	});
	
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/member/companyBankAccount?companyNo=${findCompanyBankAccountPage.companyNo}");
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
</script>
<style type="text/css">
.container {
	padding: 20px 30px;
	width: 100%;
	min-height: 800px;
	background: #fff;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.page_header {
	font-size: 32px;
	font-weight: normal;
	line-height: 1;
	padding-bottom: 40px;
	color: #666;
}
.nav-tabs > li > a {
    padding-top: 0px;
}
.lafen-group {
    position: relative;
}
.lafen-group .img-big {
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -75px;
    margin-left: 35px;
    opacity: 0;
    transform: scale(.2, .2);
    transition: all .2s ease-in-out;
    width: 130px;
    height: 130px;
}
.lafen-group .img-small:hover + .img-big {
    transform: scale(1, 1);
    opacity: 1;
}
.lafen-group .img-big img {
    width: 130px;
    height: 130px;
}
.img-small {
	width: 30px;
	height: 30px;
}
	</style>
</head>
<body>
	<div id="section" style="margin-left: 0px;">
		<div class="container">
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/member/companyBankAccount?companyNo=${findCompanyBankAccountPage.companyNo}">银行账号列表</a></li>
					<li><a href="${ctx}/member/companyBankAccount/form?companyNo=${findCompanyBankAccountPage.companyNo}">添加银行账户</a></li>
			</ul>
			<form id="searchForm" action=${ctx}/member/companyBankAccount/ method="post"
				class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden"value="${page.pageNo}" /> 
				<input id="pageSize" name="pageSize"type="hidden" value="${page.pageSize}" />
				<input id="repMsg" name="repMsg"type="hidden" value="${repMsg}" />
			</form>
			<tags:message content="${message}" />
			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
					<th>经销商</th>
					<th>银行名称</th>
					<th>银行卡号</th>
					<th>持卡人</th>
					<th>是否默认账户</th>
					<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="item">
						<tr>
						<td>
							<a href="${ctx}/member/branchCompany/view?code=${findCompanyBankAccountPage.branchCompanyCode}">${item.companyName}</a>
						</td>
						<td>${item.bankName}</td>
						<td>${item.bankcardNo}</td>
						<td>${item.custName}</td>
						<td>
						<c:if test="${0 eq item.isDefault}">非默认</c:if>
						<c:if test="${1 eq item.isDefault}">默认</c:if>
						</td>
						<td>
						<a href="${ctx}/member/companyBankAccount/form?code=${item.code}&companyNo=${findCompanyBankAccountPage.companyNo}">修改</a>
						<a href="${ctx}/member/companyBankAccount/delect?code=${item.code}&companyNo=${findCompanyBankAccountPage.companyNo}">删除</a>
						<c:if test="${item.isDefault == '0'}">
						<a href="${ctx}/member/companyBankAccount/updateDefaultAccount?code=${item.code}&companyNo=${findCompanyBankAccountPage.companyNo}" onclick="return confirmx('确认设为默认账号吗？', this.href)">设置为默认账号</a>
						</c:if>
						</td>
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>

		</div>
	</div>

</body>
</html>