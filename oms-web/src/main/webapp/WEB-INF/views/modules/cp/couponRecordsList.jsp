<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/cp/couponRecords/");
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
		<li class="active"><a href="${ctx}/cp/couponRecords/">优惠券消费记录</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cp/couponRecords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>优惠券名称：</label>
				<input type="text" name="couponName" value="${findCouponRecordsPage.couponName}" class="input-medium" maxlength="100" placeholder="优惠券名称">
			</li>
			<li>
				<label>使用时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findCouponRecordsPage.startTime}" pattern="yyyy-MM-dd"/>"onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findCouponRecordsPage.endTime}" pattern="yyyy-MM-dd"/>"onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
   <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>优惠券名称</th>
			    <th>优惠券编号</th>
			    <th>类型</th>
			    <th>面额</th>
			    <th>优惠券状态</th>
<!-- 			    <th>发放终端名称</th> -->
			    <th>使用人</th>
				<th>使用时间</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.couponName}
				</td>
			    <td>
					${item.couponNo}
				</td>
				<td> 
				  ${item.couponType}
				</td>
				<td>
				  <fmt:formatNumber value="${item.couponNotes}" pattern="0"/> 
				</td>
				<td> 
				           已使用
				</td>
<!-- 				 <td> -->
<%-- 				    ${item.shopName} --%>
<!-- 				</td> -->
				<td>
				    ${item.memberName}
				    <c:if test="${empty item.memberName}">${item.nickName}</c:if>
				</td>
				 <td>
					<fmt:formatDate value="${item.useDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	<div class="pagination">${page}</div>
	</div>
</body>
</html>