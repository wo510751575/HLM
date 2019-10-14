<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购端监控</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var refreshSwitch=$("#refreshSwitch").val();
			var refreshTime=$("#refreshTime").val();
			if(refreshSwitch==1){//仅当1时为开
				var time=setInterval("time()",refreshTime);
			}
		});
		function time(){
			$("#btnSubmit").click();
		}
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
		<li><a href="${ctx}/member/terminalImStatus/batteryLevelAll">中控端监控</a></li>
		<li class="active"><a href="${ctx}/member/terminalImStatus/gmBatteryLevelAll">导购端监控</a></li>		
	</ul>
	<form id="searchForm" action="${ctx}/member/terminalImStatus/gmBatteryLevelAll" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" value="${fns:getDictValue('switch','interval_refresh','1')}" id="refreshSwitch">
		<input type="hidden" value="${fns:getDictValue('time','interval_refresh','10000')}" id="refreshTime">
		<ul class="ul-form">
			<li><label>商户名称：</label>
				<input type="text" name="merchantName" class="input-medium" value="${findTerminalBatteryLevelPage.merchantName}" maxlength="100" placeholder="商户名称">
			</li>
			<li><label>终端微信：</label>
				<input type="text" name="noWx" class="input-medium" value="${findTerminalBatteryLevelPage.noWx}" maxlength="100" placeholder="终端名称">
			</li>
			<%-- <li><label>手机号：</label>
				<input type="text" name="guidMobile" class="input-medium" value="${findTerminalBatteryLevelPage.guidMobile}" maxlength="15" placeholder="手机号">
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<!-- <li class="clearfix"></li> -->
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
<!-- 				<th>终端名称</th> -->
				<th>手机号</th>
				<th>手机状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.merchantName}
				</td>
<!-- 				<td> -->
<%-- 				  ${item.shopName} --%>
<!-- 				</td> -->
				<td>
				  ${item.guidMemberMobile}
				</td>
				<td>
					<c:if test="${item.onlineFlag eq 0}"><font color="#d9534f">离线</font></c:if>
					<c:if test="${item.onlineFlag eq 1}"><font color="#5cb85c">在线</font></c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>