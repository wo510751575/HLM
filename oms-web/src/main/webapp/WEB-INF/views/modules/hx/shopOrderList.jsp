<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门诊服务订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		$(document).ready(function() { 
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				width: 380,
			    height: 'auto',
			}
			$.jBox.setDefaults(jBoxConfig);
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg, "error", 5000);
				$("#repMsg").val("");
			}
			
			var importMsg=$("#importMsg").val();
			if(importMsg){
				$.jBox.success(importMsg, '提示');
				$("#importMsg").val("");
			}
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			
		});
	</script>
	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
.img-small {
	width: 30px;
	height: 30px;
}

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
 
</style>
</head>
<body>
<div class="container">
	 
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hx/shop/order/list">服务审核</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/hx/shop/order/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>服务名称：</label>
				<input type="text" name="serveName" class="input-medium " value="${shopOrderDto.serveName}" maxlength="100" >
			</li>
			<li><label>诊所名称：</label>
				<input type="text" name="merchantName" class="input-medium" value="${shopOrderDto.merchantName}" maxlength="100" >
			</li>
			<li><label>下单人：</label>
				<input type="text" name="memberNameGuid" class="input-medium" value="${shopOrderDto.memberNameGuid}" maxlength="100" >
			</li>
			<li><label>订单编号：</label>
				<input type="text" name="orderNo" style="width: 240px;" class="input-medium" value="${shopOrderDto.orderNo}" maxlength="100" >
			</li>
			<li><label>审核状态：</label>
			 <select name="status" class="select valid" aria-required="true" aria-invalid="false">
				<option value="">全部</option>
				<c:forEach items="${orderStatus}" var="item"> 
				<option value="${item}" <c:if test="${item eq shopOrderDto.status}">selected="selected"</c:if>>${item.name}</option> 
			    </c:forEach> 
			  </select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>服务名称</th>
				<th>诊所名称</th>
				<th>下单人</th>
				<th>下单人电话</th>
				<th>流水编号</th>
				<th>付款方式</th>
				<th>付款金额</th>
				<th>付款时间</th>
				<th>支付凭证</th>
				<th>审核状态</th>
				<th style="width: 110px;">审核备注</th>
				<th>下单时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>	
				<td>
				 	${item.orderNo }
				</td>	
				
				<td>${item.serveName }</td>
				<td>${item.merchantName }</td>
				<td>${item.memberNameGuid }</td>
				<td>${item.mobile }</td>
				<td>${item.serialNum }</td>
				<td>
					<c:forEach items="${payTypes}" var="ele">
						<c:if test="${ele eq item.payType}">
			 		 		${ele.name}
			 			</c:if>
					</c:forEach>
				</td>
				<td>
				<fmt:formatNumber value="${item.amount/100}" pattern="#,##0.00"/>
				</td>
				<td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
 					<c:set var="imgAddr" value="${fn:split(item.payCert, ',')}" /> 
 					<c:forEach items="${imgAddr}" var="img"> 
 						<a href="${fns:getUploadUrl()}${img}" target="_blank">
 							<img class="img-circle"  src="${fns:getUploadUrl()}${img}" width="40" height="40"> 	 
 						</a>
 					</c:forEach> 
				</td>
				<td><c:forEach items="${orderStatus}" var="ele">
						<c:if test="${ele eq item.status}">
			 		 ${ele.name}
			 		</c:if>
					</c:forEach>
				</td>
  
				<td style="width: 110px;">${item.remark }</td>
				<td >
				  <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td align="center" style="text-align: center;"> 
					<c:choose>
						<c:when test="${item.status eq 'WAIT'}">
						    <a href="${ctx}/hx/shop/order/form?code=${item.code}">补充资料</a>
						    &nbsp;&nbsp;<a href="${ctx}/hx/shop/order/edit?code=${item.code}&status=PASS" onclick="return confirmx('确认要审核通过吗？', this.href)">通过</a>&nbsp;&nbsp;
							<a href="${ctx}/hx/shop/order/edit?code=${item.code}&status=UNPASS" onclick="return confirmx('确认要审核拒绝吗？', this.href)">拒绝</a>&nbsp;&nbsp;
						</c:when>
						<c:when test="${item.status eq 'UNPASS' }">
							 <a href="${ctx}/hx/shop/order/form?code=${item.code}">补充资料</a>
						    &nbsp;&nbsp;<a href="${ctx}/hx/shop/order/edit?code=${item.code}&status=PASS" onclick="return confirmx('确认要审核通过吗？', this.href)">通过</a>&nbsp;&nbsp;
						</c:when>
					<c:otherwise>
						—&nbsp;—
					</c:otherwise>
					</c:choose>
			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>