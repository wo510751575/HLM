 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域经理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	  $(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			  });
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出区域经理数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/member/manager/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
	    });
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/member/manager/");
			$("#searchForm").submit();
        	return false;
        }
		function CancelQuery(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		
// 			$("#pageNo").val(1);
// 			$("#searchForm").submit();
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

<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/member/manager/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/member/manager/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/manager/">区域经理列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/manager/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<input type="text" name="memberName" class="input-medium" maxlength="100" value="${paramManager.memberName}" placeholder="姓名">
			</li> 
			<li><label>手机号：</label>
				<input type="text" name="mobile" class="input-medium" maxlength="11" value="${paramManager.mobile}" placeholder="手机号">
			</li>
			<li><label>所属区域：</label>
				<select name="areaCode"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
						<option value="${item.value}"  <c:if test="${item.value eq paramManager.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>					
				</select>
				</li>
		</ul>
		<ul class="ul-form">
			<li><label>状态：</label>
				<select style="width: 177px;" id="statusSec" name="status">
                        <option value="">全部</option>
                        <c:forEach items="${memberStatus}" var="item">
							<option value="${item}" <c:if test="${item eq paramManager.status}">selected="selected"</c:if> >${item.name}</option>
						</c:forEach>
                 </select>
			</li>
			<li>
				<label>入职时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramManager.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramManager.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<shiro:hasPermission name="member:manager:edit">
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>员工姓名</th>
				<th>手机号</th>
			    <th>微信号</th>
				<th>性别</th>
			<!-- 	<th>出生年月日</th> -->
				<th>入职时间</th>
				<th>所属区域</th>
				<!-- <th>职位</th> -->				
				<th>状态</th>
				<shiro:hasPermission name="member:manager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>

			    <td>
					<a href="${ctx}/member/manager/form?code=${member.code}">${member.memberName}</a>
				</td>
				<td>
					${member.mobile}
				</td>
				<td>
					${member.noWx}
				</td>
				<td>
					<c:forEach items="${genders}" var="item">
				 		<c:if test="${item eq member.sex}">${item.name}</c:if>
					</c:forEach>
				</td>
			<!-- 	<td>
					1989-01-20
				</td> -->
				<td>
					<fmt:formatDate value="${member.workDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>				
					${fns:getDictLabel(member.areaCode, "erp_dict_1", "")}		
				</td>
				
			<!-- 	<td>
				 	区域经理
				</td> -->

				<td>
					<c:forEach items="${memberStatus}" var="item">
					<c:if test="${item eq member.status}">${item.name}</c:if>
					</c:forEach> 
				</td>
				<shiro:hasPermission name="member:manager:edit">
				<td>
					<a href="${ctx}/member/manager/form?code=${member.code}">修改</a>
					 <a href="${ctx}/member/manager/edit?code=${member.code}&status=${member.status eq 'FREEZE'?'NORMAL':'FREEZE'}"onclick="return confirmx('确认要${member.status eq 'FREEZE'?'解冻':'冻结'}该员工账号吗？', this.href)">${member.status eq 'FREEZE'?'解冻':'冻结'}</a>
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