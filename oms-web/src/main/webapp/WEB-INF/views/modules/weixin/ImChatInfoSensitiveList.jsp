<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>IM聊天记录敏感词管理</title>
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
		<li class="active"><a href="${ctx}/weixin/ImChatInfoSensitive">含敏感词聊天记录列表</a></li>		
	</ul>
	<form id="searchForm" action="${ctx}/weixin/ImChatInfoSensitive/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户名称：</label>
				<input type="text" name="merchantName" class="input-medium" value="${findImChatInfoSensitivePage.merchantName}" maxlength="50" placeholder="商户名称">
			</li>
<!-- 			<li><label>终端名称：</label> -->
<%-- 				<input type="text" name="shopName" class="input-medium" value="${findImChatInfoSensitivePage.shopName}" maxlength="100" placeholder="终端名称"> --%>
<!-- 			</li> -->
			 <li><label>发送人：</label>
				<input type="text" name="chatAssistant" class="input-medium" value="${findImChatInfoSensitivePage.chatAssistant}" maxlength="50" placeholder="发送人">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>商户名称</th>
<!-- 			    <th>终端名称</th> -->
				<th>客户名称</th>
				<th>客户微信</th>
				<th>所属导购</th>
				<th>终端微信</th>
				<th>聊天内容</th>
				<th>包含敏感词</th>
				<th>聊天时间</th>	
				<th>发送人</th>
				<th>消息来源</th>
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
				  ${item.memberName}
				</td>
				<td>
				  <c:if test="${empty item.alias}">${item.noWx}</c:if>
				  ${item.alias}
				</td>
				<td>
				  ${item.memberNameGm}
				</td>
				<td>
				  ${item.noWxGm}
				</td>
				<td style="width:400px">
					 <c:if test="${not empty item.content}">${item.content}</c:if><br/>
					 <c:if test="${not empty item.shareTitle}">分享标题: ${item.shareTitle}</c:if><br/>
					 <c:if test="${not empty item.shareDes}">分享描述: ${item.shareDes}</c:if><br/>
				</td>
				<td>
				  ${item.sensitiveWords}
				</td>
				<td>
					<fmt:formatDate value="${item.chatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td> 
				   <c:if test="${ not empty item.chatAssistantName}">${item.chatAssistantName}</c:if>
				   <c:if test="${empty item.chatAssistantName}">${item.memberNameGm}</c:if>
				</td>
				<td>
					 <c:if test="${empty item.chatAssistantCode}">导购端</c:if>
					 <c:if test="${not empty item.chatAssistantCode}">导购助手</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>