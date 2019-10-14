<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购个人素材管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".copyBtn").click(function(){
				$(this).attr("data-clipboard-text",window.location.href+"/viewH5?code="+$(this).attr("data-code"));
			})
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
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
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/business/materialIntroduce">导购个人素材列表</a></li>
		<shiro:hasPermission name="business:materialIntroduce:edit">
			<li><a href="${ctx}/business/materialIntroduce/setting">导购个人素材模板</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/business/materialIntroduce" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<input type="text" name="name" class="input-medium" maxlength="100" placeholder="姓名" value="${findGuidIntroduceMaterialPage.name}">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>职位</th>
				<th>服务星级</th>
				<th>专业水平</th>
				<th>手机号</th>
				<th>公司地址</th>
				<th>口号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
				<a href="${ctx}/business/materialIntroduce/view?code=${item.code}" target="_black">${item.name}</a>					
				</td>
<!-- 				<td> -->
<%-- 					<c:set var="imgAddr" value="${fn:split(item.imgAddr, ',')}" /> --%>
<%-- 					<c:forEach items="${imgAddr}" var="img"> --%>
<%-- 						<img src="${fns:getUploadUrl()}${img}" width="40" height="40"> 	 --%>
<%-- 					</c:forEach> --%>
<%-- 					<img src="${fns:getUploadUrl()}${imgAddr[0]}" width="40" height="40">  --%>
<!-- 				</td> -->
				<td>
					${item.position}
				</td>
				<td>
					${item.serveLevel}
				</td>
				<td>${item.professionalLevel}</td>
				<td>${item.mobile}</td>
				<td>${item.companyAddress}</td>
				<td>${item.slogan}</td>
				<td>
					<a class="copyBtn" href="javascript:;" data-clipboard-text="" data-code="${item.code}">复制链接</a>
					<a href="${ctx}/business/materialIntroduce/view?code=${item.code}" target="_black">预览</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript" src="${ctxStatic}/common/clipboard.min.js"></script>
	<script>
    var clipboard = new Clipboard('.copyBtn');

    clipboard.on('success', function(e) {
    	showTip("已将链接复制到粘贴板","info");
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
    </script>
</div>	
</body>
</html>