<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优秀员工管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$(".updateStatusBtn").click(function(){
					var code = $(this).attr("data-code");
					var status = $(this).attr("data-status");
						$.ajax({
		                    type:"POST",
		                    url:"${ctx}/baseConfig/exGuider/updateStatus",
		                    data:{code:code,status:status},
		                    dataType:'JSON',
		                    async:false,
		                    success:function(result){
		                        if(result==true){
		                            alertx("保存成功！");
		                            window.location.href="${ctx}/baseConfig/exGuider?timestamp="+new Date().getTime();
		                        }else{
		                            alertx("保存失败!");
		                        }
		                    }
					})
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/baseConfig/exGuider/">优秀员工列表</a></li>
		<shiro:hasPermission name="baseConfig:exGuider:edit">
			<li><a href="${ctx}/baseConfig/exGuider/form">优秀员工添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/baseConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>奖项名称：</label>
		    	<input type="text" name="name" class="input-medium" maxlength="100" placeholder="奖项名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>奖项名称</th>
				<th>备注</th>
				<th>状态</th>
				<shiro:hasPermission name="business:line:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
			    <td>
					<a href="${ctx}/baseConfig/exGuider/form?code=${item.code}">${item.name}</a>
				</td>
				<td>
					${item.remarks}
				</td>
				<td>
					<span class="label label-${item.status eq '1'?'success':'danger'}">${fns:getDictLabel(item.status,'enable_flag','')}</span>
				</td>
				
				<shiro:hasPermission name="baseConfig:exGuider:edit">
				<td>
					<a href="${ctx}/baseConfig/exGuider/form?code=${item.code}">修改</a>
					<c:if test="${item.status eq 0}">
						<a  	class="updateStatusBtn" data-code="${item.code }" data-status="1">启用</a>
					</c:if>
					<c:if test="${item.status eq 1}">
						<a  class="updateStatusBtn" data-code="${item.code }" data-status="0">禁用</a>
					</c:if>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>