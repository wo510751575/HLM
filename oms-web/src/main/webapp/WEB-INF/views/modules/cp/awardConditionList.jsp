<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券奖励管理</title>
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
		<li class="active"><a href="${ctx}/cp/awardCondition/">优惠券奖励管理</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cp/awardCondition/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		     <li><label>终端名称：</label>
				<input type="text" name="shopName" class="input-medium" value="${findAwardConditionPage.shopName}" maxlength="100" placeholder="终端名称">
			</li>
			
			<li>
				<label>活动时间：</label>
				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findAwardConditionPage.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findAwardConditionPage.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>分店名称</th>
			    <th>维度上限</th>
			    <th>维度下限</th>
			    <th>活动开始时间</th>
			    <th>活动结束时间</th>
			    <th>活动结束描述</th>
			  <!--   <th> </th> -->
			    <shiro:hasPermission name="member:guid:edit">
			    <th>操作</th>
			    </shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
			   <td>
				 <c:forEach items="${couponTypes}" var="status">
				  <c:if test="${item.couponType eq status}">${status.name}</c:if>
				</c:forEach>
				</td>
				
				<td>
					${item.couponRemark}
				</td>
				<td>
					${item.useEnable}
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