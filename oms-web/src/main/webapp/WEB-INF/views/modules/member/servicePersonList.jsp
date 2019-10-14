<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务人员管理</title>
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
		<li class="active"><a href="${ctx}/member/servicePerson">服务人员列表</a></li>		
			<li><a href="${ctx}/member/servicePerson/form">服务人员添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/member/servicePerson/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>人员姓名：</label>
				<input type="text" name="personName" class="input-medium" value="${findServicePersonPage.personName}" maxlength="100" placeholder="人员姓名">
			</li>
			<li><label>分店名称：</label>
				<input type="text" name="shopName" class="input-medium" value="${findServicePersonPage.shopName}" maxlength="100" placeholder="分店名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>人员姓名</th>
				<th>分店名称</th>
				<th>头像</th>
				<th>职称</th>
				<th>服务价格</th>
				<th>标签</th>
				<th>简介</th>
				<th>创建人</th>
				<th>创建时间</th>	
				<th>操作</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.personName}
				</td>
				<td>
					 ${item.shopName}
				</td>
				<td>
			    	<div class="lafen-group">
			    	<img class="img-small" src="${fns:getUploadUrl()}${item.headAddress}" alt="">
			    	<div class="img-big">
			    		<img src="${fns:getUploadUrl()}${item.headAddress}" alt="">
			    	</div>
			    	</div>
				</td>
				<td>
					 ${item.title}
				</td>
				<td>
					 ${item.servicePrice}
				</td>
				<td>
					 ${item.hcLabel}
				</td>
				<td>
					 ${item.summary}
				</td>
				<td>
					${item.createId}
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/member/servicePerson/form?code=${item.code}">修改</a>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>