<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公用素材管理</title>
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
		<li class="active"><a href="${ctx}/business/materialcommen">公用素材列表</a></li>
		<shiro:hasPermission name="business:materialcommen:edit">
			<li><a href="${ctx}/business/materialcommen/form">公用素材添加</a></li>
			<%-- <li><a href="${ctx}/business/materialcommen/setting">公用素材模板</a></li> --%>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/business/materialcommen" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<input type="text" name="title" class="input-medium" maxlength="100" placeholder="标题" value="${findMaterialCommenPage.title }">
			</li>
	<%-- 		<li><label style="width:110px">素材类型名称：</label>
				<select name="materialTypeName" id="materialTypeCode" >
			 			<option value="">全部</option>
			 			<c:forEach items="${materialType }" var="item">
			 			<option value="${item.typeName}" <c:if test="${item.typeName eq findMaterialCommenPage.materialTypeName}">selected="selected"</c:if> >${item.typeName}</option>
			 			</c:forEach>
					 </select>
			</li> --%>
			<li><label>素材类型名称：</label>
				<input type="text" name="materialTypeName" class="input-medium" maxlength="100" placeholder="素材类型名称" value="${findMaterialCommenPage.materialTypeName}">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>阅读量</th>
<!-- 				<th>缩略图</th> -->
				<th>素材类型名称</th>
<!-- 				<th>终端名称</th> -->
				<th>终端类型</th>
				<th>网址链接</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>创建组织</th>
				<th>创建人</th>
				<shiro:hasPermission name="business:material:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
				<a href="${ctx}/business/materialcommen/view?code=${item.code}" target="_black">${item.title}</a>					
				</td>
<!-- 				<td> -->
<%-- 					<c:set var="imgAddr" value="${fn:split(item.imgAddr, ',')}" /> --%>
<%-- 					<c:forEach items="${imgAddr}" var="img"> --%>
<%-- 						<img src="${fns:getUploadUrl()}${img}" width="40" height="40"> 	 --%>
<%-- 					</c:forEach> --%>
<%-- 					<img src="${fns:getUploadUrl()}${imgAddr[0]}" width="40" height="40">  --%>
<!-- 				</td> -->
				<td>
					${item.respondNum}
				</td>
				
				<td>
					${item.materialTypeName}
				</td>
				
<!-- 				<td> -->
<%-- 				  ${item.shopName eq ''?'全部':item.shopName} --%>
<!-- 				</td> -->
				 <td>
				  ${item.shopType eq ''?'全部':item.shopType}
				</td>
				
			    <td>
			    	<c:if test="${empty item.linkUrl}">
						<a href="${ctx}/business/materialcommen/view?code=${item.code}" target="_black">查看链接</a>
					</c:if>
					<c:if test="${not empty item.linkUrl}">
						<a href="${item.linkUrl}" target="_black">查看链接</a>
					</c:if>
				</td>
				<td><fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd"/></td>
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
							<a href="${ctx}/business/materialcommen/form?code=${item.code}&&tempId=${item.tempId}">修改</a>
							<a href="${ctx}/business/materialcommen/delete?code=${item.code}" onclick="return confirmx('确认要删除该素材吗？', this.href)">删除</a>
							<a class="copyBtn" href="javascript:;" data-clipboard-text="" data-code="${item.code}">复制链接</a>
							<a href="${ctx}/business/materialcommen/view?code=${item.code}" target="_black">预览</a>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<c:if test="${not empty item.merchantNo}">
								<a href="${ctx}/business/materialcommen/form?code=${item.code}&&tempId=${item.tempId}">修改</a>
								<a href="${ctx}/business/materialcommen/delete?code=${item.code}" onclick="return confirmx('确认要删除该素材吗？', this.href)">删除</a>
								<a class="copyBtn" href="javascript:;" data-clipboard-text="" data-code="${item.code}">复制链接</a>
								<a href="${ctx}/business/materialcommen/view?code=${item.code}" target="_black">预览</a>
							</c:if>
						</td>
					</c:otherwise>
				</c:choose>
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