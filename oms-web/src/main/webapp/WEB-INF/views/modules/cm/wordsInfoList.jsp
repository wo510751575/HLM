<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>话术管理</title>
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
		<li class="active"><a href="${ctx}/cm/wordsInfo/">个人话术列表</a></li>		
			<li><a href="${ctx}/cm/wordsInfo/form">新增个人话术</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/cm/wordsInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>话术类型：</label>
		    	<select  name="typeCode"  class="input-medium" >
					<option value='' >全部</option>
					<c:forEach items="${typeList}" var="item">
		 				<option value="${item.code}" <c:if test="${item.code eq findWordsInfoPage.typeCode}">selected="selected"</c:if> >${item.typeName}</option>
		 			</c:forEach>
				 </select>
			</li>
			<li><label>话术内容：</label>
				<input type="text" name="content" class="input-medium" value="${findWordsInfoPage.content}" maxlength="200" placeholder="话术内容">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>话术类型</th>
				<th>话术内容</th>
				<th>创建组织</th>
				<th>创建人</th>
				<shiro:hasPermission name="cm:wordsInfo:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  	 ${item.typeName}
				</td>
				<td>
					 ${item.content}
				</td>
				<td>
				<c:choose>
					<c:when test="${empty item.officeName}">
						总管账号
					</c:when>
					<c:otherwise>
						${item.officeName}
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
					${item.createId}
				</td>
				
				<!-- 总管账号创建内容不允许修改删除 -->
				<c:choose>
					<c:when test="${empty merchantNo}">
						<td>
							<c:if test="${item.defaultFlag eq 1}">
								<a href="${ctx}/cm/wordsInfo/defaultFlag?defaultFlag=0&code=${item.code}"><font style="color: red;">取消默认</font></a>&nbsp;&nbsp;
							</c:if>
							<c:if test="${item.defaultFlag eq 0}">
								<a href="${ctx}/cm/wordsInfo/defaultFlag?defaultFlag=1&code=${item.code}">设为默认</a>&nbsp;&nbsp;
							</c:if>
							&nbsp;&nbsp;<a href="${ctx}/cm/wordsInfo/form?code=${item.code}">修改</a>&nbsp;&nbsp;
							&nbsp;&nbsp;<a href="${ctx}/cm/wordsInfo/delete?code=${item.code}&content=${item.content}" onclick="return confirmx('确认要删除该话术吗？', this.href)">删除</a>&nbsp;&nbsp;
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<c:if test="${not empty item.merchantNo}">
								<c:if test="${item.defaultFlag eq 1}">
								<a href="${ctx}/cm/wordsInfo/defaultFlag?defaultFlag=0&code=${item.code}"><font style="color: red;">取消默认</font></a>&nbsp;&nbsp;
							</c:if>
							<c:if test="${item.defaultFlag eq 0}">
								<a href="${ctx}/cm/wordsInfo/defaultFlag?defaultFlag=1&code=${item.code}">设为默认</a>&nbsp;&nbsp;
							</c:if>
							&nbsp;&nbsp;<a href="${ctx}/cm/wordsInfo/form?code=${item.code}">修改</a>&nbsp;&nbsp;
							&nbsp;&nbsp;<a href="${ctx}/cm/wordsInfo/delete?code=${item.code}&content=${item.content}" onclick="return confirmx('确认要删除该话术吗？', this.href)">删除</a>&nbsp;&nbsp;
							</c:if>
						</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>