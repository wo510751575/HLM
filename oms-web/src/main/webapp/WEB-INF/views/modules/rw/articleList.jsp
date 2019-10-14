<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>热文管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg);
				$("#repMsg").val("");
			}
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
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/rw/article/">热文列表</a></li>		
		<li><a href="${ctx}/rw/article/form">热文添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/rw/article/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>标题：</label>
				<input type="text" name="title" class="input-medium" value="${findArticlePage.param.title}" maxlength="200" placeholder="标题">
			</li>
			<li>
				<label>广告分类：</label>
				<select style="width: 177px;" name="typeCode">
                    <option value="">全部</option>
                    <c:forEach items="${findArticleTypeList }" var="item">
                    	<option value="${item.code }" ${item.code eq findArticlePage.param.typeCode ? 'selected="selected"' : '' }>${item.typeName }</option>
                    </c:forEach>
                </select>
			</li>
			<%-- <li>
				<label>类型：</label>
				<select style="width: 177px;" name="type">
                    <option value="">全部</option>
                    <option value="HOT" ${'HOT' eq findArticlePage.param.type ? 'selected="selected"' : '' }>热文</option>
                    <option value="MY" ${'MY' eq findArticlePage.param.type ? 'selected="selected"' : '' }>用户</option>
                    <option value="OTHER" ${'OTHER' eq findArticlePage.param.type ? 'selected="selected"' : '' }>其它</option>
                </select>
			</li> --%>
			<li>
				<label>状态：</label>
				<select style="width: 177px;" name="rwState">
                    <option value="">全部</option>
                    <option value="normal" ${'normal' eq findArticlePage.param.rwState ? 'selected="selected"' : '' }>正常</option>
                    <option value="forbid" ${'forbid' eq findArticlePage.param.rwState ? 'selected="selected"' : '' }>禁用</option>
                    <option value="review" ${'review' eq findArticlePage.param.rwState ? 'selected="selected"' : '' }>审核</option>
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
				<th>文章类型</th>
				<th>文章标题</th>
				<th>略缩图</th>
				<th>来源</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="rw:article:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<%-- <td>${'HOT' eq item.type ? '热文' : 'MY' eq item.type ? '用户' : '其它' }</td> --%>
				<td>${item.type }</td>
				<td>${item.title }</td>
				<td><img alt="${item.title }" src="${item.mainImage }" width="150"></td>
				<td>${item.source }</td>
				<td>${'normal' eq item.rwState ? '正常' : 'forbid' eq item.rwState ? '禁用' : 'review' eq item.rwState ? '审核' : '' }</td>
				<td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="rw:article:edit">		
				<td>
					<a href="${ctx}/rw/article/form?code=${item.code}">修改</a>
					<a href="${ctx}/rw/article/delete?code=${item.code}" onclick="return confirmx('确认要删除该文章吗？', this.href)">删除</a>
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