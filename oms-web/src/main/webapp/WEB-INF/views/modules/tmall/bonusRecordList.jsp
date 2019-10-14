<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单红包发送管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/message/jquery.my-message.1.1.js"></script>
	<link rel="stylesheet"href="${ctxStatic}/message/jquery.my-message.1.1.css" type="text/css"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
 
	</script>
	<style type="text/css">
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
		<li class="active"><a href="${ctx}/tmall/bonusRecord/list">红包记录</a></li>		
	</ul>
	<form id="searchForm" action="${ctx}/tmall/bonusRecord/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户姓名：</label>
				<input type="text" name="memberName" class="input-medium" value="${tmallBonusRecordDto.memberName}" maxlength="100" placeholder="客户姓名">
			</li>
			<li><label>微信号：</label>
				<input type="text" name="noWx" class="input-medium" value="${tmallBonusRecordDto.noWx}" maxlength="50" placeholder="微信号">
			</li>
			<li><label>订单号：</label>
				<input type="text" name="orderNo" class="input-medium" value="${tmallBonusRecordDto.orderNo}" maxlength="100" placeholder="订单号">
			</li>
			<li><label>状态：</label>
			<select style="width: 177px;" name="status">
                   <option value="">全部</option>
                   <c:forEach items="${bonusStatus}" var="item">
					<option value="${item}"
						<c:if test="${item eq tmallBonusRecordDto.status}">selected="selected"</c:if>>${item.name}</option>
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
				<th>客户姓名</th>
				<th>微信号</th>
				<th>订单号</th>
				<th>订单金额</th>
				<th>红包金额（元）</th>
				<th>发送红包时间</th>	
				<th>状态</th>	
				<th>备注</th>	
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>	
				<td>
			    	 ${item.memberName}
				</td>	
				<td>
				  ${item.noWx}
				</td>
				<td>
				  ${item.orderNo}
				</td>
				<td>
					  <fmt:formatNumber value="${item.orderAmt/100}" pattern="#,##0.00"/>
				</td>
				<td> 
					 <fmt:formatNumber value="${item.bonuxAmt/100}" pattern="#,##0.00"/>
				</td>
				<td>
					<fmt:formatDate value="${item.pushTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td> 
				    <c:forEach items="${bonusStatus}" var="item1">
						<c:if test="${item1 eq item.status}">${item1.name}</c:if>
					</c:forEach>
				</td>
				<td> 
				    ${item.remark}
				</td>
				<td> 
				    <c:if test="${item.status eq 'N' }">
				    	&nbsp;&nbsp;<a href="${ctx}/tmall/bonusRecord/resend?code=${item.code}" onclick="return confirmx('确认重发红包吗？', this.href)">重发</a>&nbsp;&nbsp;
				    </c:if>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>