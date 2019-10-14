<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>
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
		<li class="active"><a href="${ctx}/rw/rwUser/list">会员列表</a></li>		
		<li><a href="${ctx}/rw/rwUser/form">会员添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/rw/rwUser/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>会员ID：</label>
				<input type="text" name="memberNoGuid" class="input-medium" value="${findRwUserPage.param.memberNoGuid}" maxlength="200" placeholder="会员ID">
			</li>
			<li>
				<label>客户姓名：</label>
				<input type="text" name="nickName" class="input-medium" value="${findRwUserPage.param.nickName}" maxlength="200" placeholder="客户姓名">
			</li>
			<li>
				<label>会员类型：</label>
				<select style="width: 177px;" name="userLevel">
                    <option value="">全部</option>
                    <option value="1" ${"1" eq findRwUserPage.param.userLevel ? 'selected="selected"' : '' }>普通用户</option>
                    <option value="2" ${"2" eq findRwUserPage.param.userLevel ? 'selected="selected"' : '' }>个体用户</option>
                    <option value="3" ${"3" eq findRwUserPage.param.userLevel ? 'selected="selected"' : '' }>商户</option>
                </select>
			</li>
			<li>
				<label>状态：</label>
				<select style="width: 177px;" name="status">
                    <option value="">全部</option>
                    <option value="NORMAL" ${'NORMAL' eq findRwUserPage.param.status ? 'selected="selected"' : '' }>正常</option>
                    <option value="CANCEL" ${'CANCEL' eq findRwUserPage.param.status ? 'selected="selected"' : '' }>注销</option>
                    <option value="FREEZE" ${'FREEZE' eq findRwUserPage.param.status ? 'selected="selected"' : '' }>冻结</option>
                </select>
			</li>
			<li>
				<label>注册时间：</label>
				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findRwUserPage.param.beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${findRwUserPage.param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员ID</th>
				<th>头像</th>
				<th>会员姓名（备注）</th>
				<th>微信号</th>
				<th>手机号</th>
				<th>会员类型</th>
				<th>性别</th>
				<th>所在地区</th>
				<th>所在公司</th>
				<th>公司网址</th>
				<th>剩余广告豆</th>
				<th>账号状态</th>
				<th>注册时间</th>
				<shiro:hasPermission name="rw:rwUser:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>${item.memberNoGuid }</td>
				<td><img alt="${item.headAddress }" src="${item.headAddress }" width="150"></td>
				<td>${item.nickName }</td>
				<td>${item.noWx }</td>
				<td>${item.mobile }</td>
				<td>${item.userLevel eq '1' ? '普通用户' : item.userLevel eq '2' ? '个体用户' : item.userLevel eq '3' ? '商户' : '其他' }</td>
				<td>${item.gender eq 'male' ? '男' : item.gender eq 'female' ? '女' : '' }</td>
				<td>${item.areaName }</td>
				<td>${item.companyName }</td>
				<td>${item.websiteUrl }</td>
				<td></td>
				<td>${'NORMAL' eq item.status ? '正常' : 'CANCEL' eq item.status ? '注销' : 'FREEZE' eq item.status ? '冻结' : '' }</td>
				<td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/></td>
				<shiro:hasPermission name="rw:rwUser:edit">		
				<td>
					<a href="${ctx}/rw/rwUser/form?code=${item.code}">修改</a>
					<%-- <a href="${ctx}/rw/rwUser/form?hasView=1&code=${item.code}">查看</a> --%>
					<%-- <a href="${ctx}/rw/rwUser/delete?code=${item.code}" onclick="return confirmx('确认要删除该文章吗？', this.href)">删除</a> --%>
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