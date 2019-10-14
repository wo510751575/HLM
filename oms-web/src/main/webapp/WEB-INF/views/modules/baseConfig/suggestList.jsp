<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户反馈</title>
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
		
			$("#pageNo").val(1);
			$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/baseConfig/suggest">反馈信息表</a></li>
		<shiro:hasPermission name="baseConfig:suggest:edit">
			<li><a href="${ctx}/baseConfig/suggest/form">服务协议添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/baseConfig/suggest" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>反馈人：</label>
				<input type="text" name=memberName class="input-medium" maxlength="100" placeholder="反馈人" value="${findSuggestPage.memberName}">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
<!-- 			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>反馈人</th>
				<th>反馈意见</th>
				<th>登录类型</th>
				<th>反馈时间</th>
				<shiro:hasPermission name="baseConfig:suggest:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.memberName}
				</td>				
				<td>
					${item.content}
				</td>
				<td>
				 	<c:if test="${item.memberType eq 'SHOP'}">店长</c:if>
				 	<c:if test="${item.memberType eq 'BOSS'}">老板</c:if>
				 	<c:if test="${item.memberType eq 'GUID'}">导购</c:if>	
				 	<c:if test="${item.memberType eq 'PM'}">客户</c:if>						
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
                             
				<shiro:hasPermission name="baseConfig:suggest:edit">
				<td>
					<a href="${ctx}/baseConfig/suggest/form?code=${item.code}">修改</a>
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