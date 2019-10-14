<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端推送配置列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			initView();
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
		
		function initView(){
	    	$('.form-group-select-css').select2();
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
	.select-medium{
		width: 220px;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/wxPushConfig/list">终端推送配置列表</a></li>
		<shiro:hasPermission name="member:wxPushConfig:edit">
			<li><a href="${ctx}/member/wxPushConfig/addView">终端推送配置添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/member/wxPushConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>推送终端：</label>
				<select class="form-group-select-css select-medium"  name="noWx" >
                   <option value="">全部</option>
	                <c:forEach items="${findShopTerminalReturns}" var="item">
						<option value="${item.noWx}" <c:if test="${item.noWx eq findWxPushConfigPage.noWx}">selected='selected'</c:if>>
							${item.alias}-${item.wxNickname}
						</option>
					</c:forEach>
               </select>
			</li>
			<%-- <li><label>推送微信：</label>
				<input type="text" name="noWx" class="input-medium" placeholder="推送微信" value="${findWxPushConfigPage.noWx}">
			</li> --%>
<!-- 			<li><label>推送时间：</label> -->
<!-- 				<select class="selectEnum"  name="pushTime" > -->
<!--                    <option value="">全部</option> -->
<%-- 	                <c:forEach items="${pushTimes}" var="item"> --%>
<%-- 						<option value="${item}" <c:if test="${item eq findWxPushConfigPage.pushTime}">selected='selected'</c:if>>${item.name}</option> --%>
<%-- 					</c:forEach> --%>
<!--                </select> -->
<!-- 			</li> -->
			<li><label>类型：</label>
				<select class="selectEnum"  name="type" >
                   <option value="">全部</option>
	                <c:forEach items="${pushConfigTypes}" var="item">
						<option value="${item}" <c:if test="${item eq findWxPushConfigPage.type}">selected='selected'</c:if>>${item.name}</option>
					</c:forEach>
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
<!-- 				<th>终端名称</th> -->
				<th>推送终端</th>
				<th>推送时间</th>
				<th>类型</th>
				<th>内容</th>
				<th>排序</th>
				<th>状态</th>
				<th>添加时间</th>
				<shiro:hasPermission name="member:wxPushConfig:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
<!-- 				<td> -->
<%-- 					${item.shopName} --%>
<!-- 				</td> -->
				<td>
					${item.remark}
				</td>
				<td>
					<c:forEach items="${pushTimes}" var="i">
						<c:if test="${i eq item.pushTime}">${i.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${pushConfigTypes}" var="i">
						<c:if test="${i eq item.type}">${i.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:choose>
						<c:when test="${item.type eq 'GREET'}">${item.content}</c:when>
						<c:when test="${item.type eq 'IMAGE'}">
							<img width="60px" height="60px" src="${fns:getUploadUrl()}${item.shareIcon}" />
						</c:when>
						<c:when test="${item.type eq 'SHARE'}">
							<img width="60px" height="60px" src="${item.shareIcon}" />
							${item.shareTitle}
						</c:when>
						<c:when test="${item.type eq 'PA'}">
							<c:choose>
								<c:when test="${not empty item.shareUrl}">
									<img width="60px" height="60px" src="${item.shareUrl}" />
								</c:when>
								<c:otherwise>
									<img width="60px" height="60px" src="${item.shareIcon}" />
								</c:otherwise>
							</c:choose>
							${item.shareTitle}
						</c:when>
						<c:when test="${item.type eq 'SP'}">
							<c:choose>
								<c:when test="${not empty item.shareUrl}">
									<img width="60px" height="60px" src="${item.shareUrl}" />
								</c:when>
								<c:otherwise>
									<img width="60px" height="60px" src="${item.shareIcon}" />
								</c:otherwise>
							</c:choose>
							${item.shareTitle}
						</c:when>
					</c:choose>
				</td>
				<td>
					${item.sort}
				</td>
				<td>
					<c:if test="${item.status eq 'USE'}">有效</c:if>
					<c:if test="${item.status eq 'STOP'}">失效</c:if>
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:wxPushConfig:edit">
				<td>
					<a href="${ctx}/member/wxPushConfig/editView?code=${item.code}">编辑</a>
					<a href="${ctx}/member/wxPushConfig/delete?code=${item.code}" onclick="return delConfirm()">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/common/clipboard.min.js"></script>
	<script type="text/javascript">
		function delConfirm() {
			var result = window.confirm("确认删除该记录？");
			return result;
		}
	</script>
</body>
</html>