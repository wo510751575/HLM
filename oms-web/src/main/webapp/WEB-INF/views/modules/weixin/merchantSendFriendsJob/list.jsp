<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>朋友圈列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			initView();
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
		
			$("#pageNo").val(1);
			$("#searchForm").submit();
        }
		
		function initView(){
	    	$('.form-group-select-css').select2();
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
	.select-medium{
		width: 220px;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/merchantSendFriendsJob/list">发朋友圈列表</a></li>
		<li><a href="${ctx}/weixin/merchantSendFriendsJob/form">发朋友圈</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/weixin/merchantSendFriendsJob/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户：</label>
		    	<input type="text" name="param.merchantName"  value="${findPage.param.merchantName}" class="input-medium" maxlength="50" placeholder="商户">
			</li>
			<li><label>发布微信：</label>
		    	<input type="text" name="param.noWxs"  value="${findPage.param.noWxs}" class="input-medium" maxlength="50" placeholder="发布微信">
			</li>
			<li><label>类型：</label>
				<select id="type" style="width: 150px;" name="param.type" >
					<option value="">全部</option>
		             <c:forEach items="${materialType}" var="item">
	             		<option value="${item.type}" <c:if test="${item.type eq findPage.param.type}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
	             </select>
			</li>
			<li><label>执行时间 ：</label> 
			<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" 
				value="<fmt:formatDate value="${findPage.startTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findPage.endTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />&nbsp;&nbsp;
			</li>
			<li><label>分享内容：</label>
		    	<input type="text" name="param.content"  value="${findPage.param.content}" class="input-medium" maxlength="50" placeholder="分享内容">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户</th>
				<th>发布微信号</th>
				<th>类型</th>
				<th>分享内容</th>
				<th>图片/链接/视频</th>
				<th>创建时间</th>
				<th>预计执行时间</th>
				<th>实际执行时间</th>
				<th>发布状态</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>${item.merchantName}</td>
				<td>${item.noWxs}</td>
				<td>
					<c:forEach items="${materialType}" var="i">
						<c:if test="${i.type eq item.type}">${i.name}</c:if>
					</c:forEach>
				</td>
				<td>${item.content}</td>
				<td>
					<!--图文 -->
					<c:if test="${item.type eq '1'}">
						<c:forEach items="${fn:split(item.imgAddr, ',')}" var="i">
							<img width="60px" height="60px" src="${fns:getUploadUrl()}${i}" />
						</c:forEach>
					</c:if>
					<!--视频 -->
					<c:if test="${item.type eq '2'}">
						<video src="${fns:getUploadUrl()}${item.linkUrl}" width="200px" height="200px" controls="controls" preload="auto" poster="${fns:getUploadUrl()}${item.imgAddr}"></video>
					</c:if>
					<!--文章 -->
					<c:if test="${item.type eq '3'}">
							<a href="${item.linkUrl}" target="_black">查看链接</a>
					</c:if>
					<!--纯文字 -->
					<c:if test="${item.type eq '4'}"></c:if>
				</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.executeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.realExecuteTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${item.jobState eq '1'}">未执行</c:if>
					<c:if test="${item.jobState eq '2'}">执行中</c:if>
					<c:if test="${item.jobState eq '3'}">执行完成</c:if>
				</td>
				<td>
					${fns:escapeHtml(item.remark)}
				</td>
				<td>
<%-- 					<a href="${ctx}/member/wxPushConfig/delete?code=${item.code}" onclick="return delConfirm()">重发</a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>