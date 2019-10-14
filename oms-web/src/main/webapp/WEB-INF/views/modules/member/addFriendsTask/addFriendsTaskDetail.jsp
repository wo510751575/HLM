<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/message/jquery.my-message.1.1.js"></script>
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
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/addFriendsTask/selectAddFriendsTaskDetailList?param.taskCode=${findAddFriendsTaskDetailPage.param.taskCode}">任务详情</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/addFriendsTask/selectAddFriendsTaskDetailList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="taskCode" name="param.taskCode" type="hidden" value="${findAddFriendsTaskDetailPage.param.taskCode}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="returnMessage" name="returnMessage" type="hidden" value="${returnMessage}"/>
		<ul class="ul-form">
		    <li><label>手机号码：</label>
		    	<input type="text" name="param.phone" class="input-medium" maxlength="11" placeholder="手机号" value="${findAddFriendsTaskDetailPage.param.phone}">
			</li>
			<li><label>客户姓名：</label>
				<input type="text" name="param.username" class="input-medium" placeholder="客戶姓名" value="${findAddFriendsTaskDetailPage.param.username}">
			</li>
			<li><label>任务微信：</label>
				<input type="text" name="param.noWxGm" class="input-medium" placeholder="终端微信" value="${findAddFriendsTaskDetailPage.param.noWxGm}">
			</li>
			<li><label>任务结果：</label>
				<select  style="width: 120px;" id="statusSec" name="param.status">
					<option value="">全部</option>
					<c:forEach items="${statuss }" var="item">
						<option value="${item.code}" <c:if test="${findAddFriendsTaskDetailPage.param.status eq item.code}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
				</select>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
		  	<li class="btns"><input class="btn btn-primary" type="button" onclick="return confirmx('确认要全部暂停任务吗？', '${ctx}/member/addFriendsTask/allStatus?taskCode=${findAddFriendsTaskDetailPage.param.taskCode}&status=2')" value="全部暂停"/></li> 
		  	<li class="btns"><input class="btn btn-primary" type="button" onclick="return confirmx('确认要全部启动任务吗？', '${ctx}/member/addFriendsTask/allStatus?taskCode=${findAddFriendsTaskDetailPage.param.taskCode}&status=1')" value="全部启动"/></li>
<%-- 		  	<li class="btns"><input class="btn btn-primary" type="button" onclick="javascript:stopAll('${param.taskCode}',4);" value="全部重发"/></li> --%>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>手机号</th>
				<th>客户姓名</th>
				<th>导入时间</th>
				<th>执行任务时间</th>
				<th>发起好友请求时间</th>
				<th>好友回应时间</th>
				<th>任务微信</th>
				<th>客户微信</th>
				<th>任务状态</th>
				<th>任务结果</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="task" varStatus="number">
			<tr>
			  <td>${task.phone}</td>
				<td>${task.username}</td>
				<td><fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${task.executeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${task.addfriendsDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${task.callbackDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${task.noWxGm}</td>
	 			<td>${task.noWx}</td>
				<td>
					<c:forEach items="${statuss }" var="item">
						<c:if test="${task.status eq item.code}">${item.name}</c:if>
					</c:forEach>
				</td>
                <td>${task.detail}</td>
				<td>
					<c:if test="${task.status == 1 || task.status == 2 }">
					 	<a href="${ctx}/member/addFriendsTask/del?code=${task.code}"onclick="return confirmx('确认要删除该任务吗？', this.href)">删除</a>
					</c:if>
	                <c:if test="${task.status == 2}">
					    <a href="${ctx}/member/addFriendsTask/updateStatus?code=${task.code}&status=1" onclick="return confirmx('确认要启动该任务吗？', this.href)">启动</a>
					</c:if>
	                  
	                 <c:if test="${task.status == 1}">
					     <a href="${ctx}/member/addFriendsTask/updateStatus?code=${task.code}&status=2" onclick="return confirmx('确认要暂停该任务吗？', this.href)">暂停</a>
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
