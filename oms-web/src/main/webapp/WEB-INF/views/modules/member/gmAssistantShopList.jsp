<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端助手管理</title>
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
		<li class="active"><a href="${ctx}/member/gmAssistantShop">终端助手列表</a></li>		
			<li><a href="${ctx}/member/gmAssistantShop/form">终端助手添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/member/gmAssistantShop/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<input type="text" name="assistantName" class="input-medium" value="${findGmAssistantShopPage.assistantName}" maxlength="100" placeholder="用户名称">
			</li>
			<li><label>微信号：</label>
				<input type="text" name="noWx" class="input-medium" value="${findGmAssistantShopPage.noWx}" maxlength="100" placeholder="微信号">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>登录名</th>
				<th>终端编码</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>来源</th>
				<th>创建时间</th>	
				<th>操作</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.assistantName}
				</td>
				<td>
				  ${item.loginName}
				</td>
				<td>
				  ${item.terminalCode}
				</td>
				<td>
				  ${item.noWx}
				</td>
				<td>
				  ${item.wxNickname}
				</td>
				<td>
				  ${item.source}
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- <a href="${ctx}/member/gmAssistantShop/form?code=${item.code}">修改</a> --%>
					<a href="${ctx}/member/gmAssistantShop/delect?code=${item.code}" onclick="return confirmx('确认要删除该终端助手吗？', this.href)">删除</a>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>