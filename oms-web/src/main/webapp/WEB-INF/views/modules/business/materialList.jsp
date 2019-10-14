<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购素材管理</title>
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
		<li class="active"><a href="${ctx}/business/material/">导购素材列表</a></li>
		<shiro:hasPermission name="business:materialcommen:edit">
			<li><a href="${ctx}/business/material/form">导购素材添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/business/material/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>导购姓名：</label>
		    	<input type="text" name="memberNameGm" class="input-medium" maxlength="100" value="${findMaterialPage.memberNameGm}" placeholder="导购姓名">
			</li>
			<li><label>标题：</label>
				<input type="text" name="title" class="input-medium" maxlength="100" value="${findMaterialPage.title}" placeholder="标题">
			</li>
			
			<li><label>类型：</label>
				<input type="text" name="materialTypeName" class="input-medium" maxlength="100" value="${findMaterialPage.materialTypeName}" placeholder="类型">
			</li>
			
		<!-- 	<li><label>类型：</label>
				<select name="status">
					<option value="1">正常</option>
					<option value="1">注销</option>
					<option value="1">冻结</option>
				</select>
			</li> -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导购姓名</th>
				<th>标题</th>
				<th>阅读量	</th>
<!-- 				<th>缩略图</th> -->
				<th>素材类型名称</th>
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
					${item.memberNameGm}
				</td>
				<td>
					${item.title}
				</td>
				<td>
					${item.respondNum}
				</td>
<!-- 				<td> -->
<%-- 					<c:set var="imgAddr" value="${fn:split(item.imgAddr, ',')}" /> --%>
<%-- 					<img src="${imgAddr[0]}" width="40" height="40">  --%>
<!-- 				</td> -->
				<td>					
				<%-- <a href="${ctx}/business/material/form?code=${item.code}"> --%>${item.materialTypeName}<!-- </a> -->
				</td>
				<td>
					<c:if test="${empty item.linkUrl}">
						<a href="${ctx}/business/material/view?code=${item.code}" target="_black">查看链接</a>
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
							<a href="${ctx}/business/material/form?code=${item.code}" >修改</a>
							<a href="${ctx}/business/material/delete?code=${item.code}" onclick="return confirmx('确认要删除该素材吗？', this.href)">删除</a>
							<a class="copyBtn" href="javascript:;" data-clipboard-text="" data-code="${item.code}">复制链接</a>
							<a href="${ctx}/business/material/view?code=${item.code}" target="_black">预览</a>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<c:if test="${not empty item.merchantNo}">
								<a href="${ctx}/business/material/form?code=${item.code}" >修改</a>
								<a href="${ctx}/business/material/delete?code=${item.code}" onclick="return confirmx('确认要删除该素材吗？', this.href)">删除</a>
								<a class="copyBtn" href="javascript:;" data-clipboard-text="" data-code="${item.code}">复制链接</a>
								<a href="${ctx}/business/material/view?code=${item.code}" target="_black">预览</a>
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
</body>
</html>