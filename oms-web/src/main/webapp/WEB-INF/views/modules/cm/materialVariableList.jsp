<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>素材变量列表</title>
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
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.variable{
		border: 1px solid #9D9D9D;
		background-color: yellow;
		border-radius:5px;
		margin:5px;
		text-align: center;
		vertical-align: middle;
	}
		.variables{
		border: 1px solid #9D9D9D;
		background-color: #F0FFFF;
		border-radius:5px;
		margin:5px;
		text-align: center;
		vertical-align: middle;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cm/materialVariable/">素材变量列表</a></li>		
			<li><a href="${ctx}/cm/materialVariable/form">素材变量添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cm/materialVariable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			 <li>
				<label>开始时间：</label>
				<input id="startDate" name="startDate" style="width:200px" type="text" readonly="readonly" maxlength="40" class="input-mini Wdate"
				<%-- value="${materialVariable.startDate}" --%>
				value="<fmt:formatDate value="${materialVariable.startDate}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<label>结束时间：</label>
				<input id="endDate" name=endDate type="text" style="width:200px" readonly="readonly" maxlength="40" class="input-mini Wdate"
				value="<fmt:formatDate value="${materialVariable.endDate}" pattern="yyyy-MM-dd"/>" 
			  <%--   value="${materialVariable.startDate}" --%>
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
				/>&nbsp;&nbsp;
			</li>
			<li>
			 <label>名称/变量值：</label>
			   <input type="conditionStr" name="conditionStr" class="input-medium" value="${materialVariable.conditionStr}" maxlength="30" placeholder="名称或变量值">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>变量名称</th>
				<th>变量内容</th>
				<th>值个数</th>	
				<th>最后更新时间</th>	
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.varName}
				</td>
				<td style="width:800px">
					<c:forEach items="${item.countList}" var="count"> 
					<c:if test = "${item.sysFlag eq 1}">
					 <span class="variable">
					   &nbsp;${count}&nbsp;
					 </span>
					</c:if>
					 <c:if test = "${item.sysFlag eq 0}">
					 <span class="variables">
					   &nbsp;${count}&nbsp;
					 </span>
					</c:if>
					 </c:forEach>
				</td>
				<td>
					 ${item.varCount}
				</td>
				<td>
					<fmt:formatDate value="${item.upateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>			
				<td>
					<a href="${ctx}/cm/materialVariable/form?code=${item.code}">修改</a>
					<c:if test="${item.sysFlag eq 0}">
					<a href="${ctx}/cm/materialVariable/deleteH5?code=${item.code}">删除</a>
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