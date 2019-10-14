<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出优惠券吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/cp/coupon/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});   
			
		});
		function page(n,s){
			debugger;
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/cp/coupon/");
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
		<li class="active"><a href="${ctx}/cp/coupon/">优惠券管理</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cp/coupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>优惠券编号：</label>
				<input type="text" name="couponNo" class="input-medium" value="${findCouponPage.couponNo}" maxlength="100" placeholder="优惠券编号">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"	onclick="return page();"/></li>
			  <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>优惠券编号</th>
			    <th>优惠券名称</th>
			    <th>优惠券面额</th>
			    <th>优惠券类型</th>
				<th>优惠券状态</th>
				<th>使用时间</th>
				<th>开始时间</th>
				<th>结束时间</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="item" > 
			<tr>
				<td>
					${item.couponNo}
				</td>
				<td>
					${item.couponName}
				</td>
				<td>
					<fmt:formatNumber value="${item.couponNotes}" pattern="0"/>
				</td>
				 <td>
				    ${item.couponType }
				</td>
				<td>
				<c:forEach items="${couponStatuss}" var="status">
				   <c:if test="${item.couponStatus eq status}">${status.name}</c:if>
				</c:forEach>
				</td>
				 <td>
					<fmt:formatDate value="${item.useDate}" pattern="yyyy-MM-dd"/>
				</td>
				 <td>
					<fmt:formatDate value="${item.beginDate}" pattern="yyyy-MM-dd"/>
				</td>
				 <td>
					<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>