<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作事项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//工作事项选择
			$('.view_btn').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/workTaskChoose/","选择工作事项", 920, 730,{//宽高
					id:9527,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} /* 信息关闭后执行的函数 */
				});
		    });
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
		
			/* $("#pageNo").val(1);
			$("#searchForm").submit(); */
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
	.label-success{
	background-color: #2ecc71;
	}
	.label-danger{
	background-color: #e74c3c;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/baseConfig/workTaskChooseShow/">工作事项列表</a></li>
 	 <%--    <shiro:hasPermission name="baseConfig:workTaskList:edit">
			<li><a href="${ctx}/baseConfig/workTaskList/form">新增工作事项</a></li>
		</shiro:hasPermission>   --%>
		<li><a href="javascript:;" class="view_btn" >选择</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/baseConfig/workTaskChooseShow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label>项目名称：</label>       
			<input type="text" name="nameList" class="input-medium" maxlength="100" value="${findWorkTaskChoosePageDto.nameList}" placeholder="工作事项">
			</li> 	
					
		   <li> <label>任务类型：</label>
		   <select name="taskType" id="materialTypeCode"  class="required">
		   <option value="">全部</option>
		   <c:forEach items="${taskTypes}" var="item">			 		
		   <option value="${item}" <c:if test="${item eq findWorkTaskChoosePageDto.taskType}">selected="selected"</c:if>>${item.name}</option>
		   </c:forEach>
		  <span class="help-inline"><font color="red">*</font></span>
		  </select>
		</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>  
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>任务类型</th>
				<th>排序值</th>
			<%-- 	<th>状态</th>
				<shiro:hasPermission name="baseConfig:workTaskList:edit">
			    <th>操作</th>
				</shiro:hasPermission>  --%> 
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.nameList}
				</td>
			   <td>
				<c:forEach items="${taskTypes}" var="status">
				<c:if test="${item.taskType eq status}">${status.name}</c:if>
				</c:forEach>
			   </td>
				<td>
					${item.seq}
				</td>
				<%-- <td>   
					<span class="label label-${item.status eq 'Y'?'success':'danger'}">${item.status eq 'Y'?'启用':'禁用'}</span>
				</td>
					<shiro:hasPermission name="baseConfig:workTaskList:edit">
						<td>
							<a href="${ctx}/baseConfig/workTaskList/form?code=${item.code}">修改</a>
							<a href="${ctx}/baseConfig/workTaskList/edit?code=${item.code}&status=${item.status eq 'N'?'Y':'N'}&taskName=${item.taskName}"onclick="return confirmx('确认要${item.status eq 'N'?'启用':'禁用'}该工作事项吗？', this.href)">${item.status eq 'N'?'启用':'禁用'}</a>
						</td>
					</shiro:hasPermission>		 --%>		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>