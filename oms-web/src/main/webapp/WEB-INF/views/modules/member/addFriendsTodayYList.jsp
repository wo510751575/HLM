<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>今日已认领客户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//刷新
			$("#btnReload").click(function() {
				window.location.reload();
			});
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
			});
			
		  	
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
		#myModal{
		position: fixed;
	    top: 10%;
	    left: 50%;
	    z-index: 1050;
	    width: 560px;
	    margin-left: -280px;
	    background-color: #fff;
	    border: 1px solid #999;
	    border: 1px solid rgba(0,0,0,0.3);
	    -webkit-border-radius: 6px;
	    -moz-border-radius: 6px;
	    border-radius: 6px;
	    outline: 0;
	    -webkit-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -moz-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -webkit-background-clip: padding-box;
	    -moz-background-clip: padding-box;
	    background-clip: padding-box;
		}
		
	</style>
</head>
<body>
<div class="container">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/addFriends/todayYList">今日已认领客户</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/addFriends/todayYList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${findAddFriendsAllotPage.mobile}" class="input-medium" maxlength="15" placeholder="手机号">
			</li>
			<li><label>终端微信号：</label>
				<input type="text" name="noWxGm" value="${findAddFriendsAllotPage.noWxGm}" class="input-medium" maxlength="100" placeholder="终端微信号">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>
			<input id="btnReload"  type="hidden" value="刷新"/>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>全选<input type="checkbox" id="allcheck"/></th>
				<th style="min-width: 45px;">头像</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>性别</th>
				<th>所属终端</th>
				<th>添加时间</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="addFriends" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox" value="${addFriends.code}"/>
				</td>
				<td>
						<c:if test="${empty addFriends.headAddress}">
							<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
						</c:if>
						<c:if test="${not empty addFriends.headAddress}">
							<c:if test="${fns:startsWith(addFriends.headAddress,'http')}">
								<img class="img-circle" src="${addFriends.headAddress}">
							</c:if>
							<c:if test="${!fns:startsWith(addFriends.headAddress,'http')}">
								<img class="img-circle" src="${fns:getUploadUrl()}${addFriends.headAddress}">
							</c:if>
						</c:if>
				</td>
				<td>
					${empty addFriends.alias?addFriends.noWx:addFriends.alias}
				</td>
				<td>
					${addFriends.nickName}
				</td>
				<td>
					${fn:replace(addFriends.mobile,",","<br>")}
				</td>
				<td>
					<c:if test="${addFriends.sex=='male'}">男</c:if> 
					<c:if test="${addFriends.sex=='female'}">女</c:if> 
					<c:if test="${addFriends.sex=='unknown'}">未知</c:if> 
				</td>
				<td>
					${addFriends.noWxGm}
				</td>
				<td>
					<fmt:formatDate value="${addFriends.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
	
</body>
</html>