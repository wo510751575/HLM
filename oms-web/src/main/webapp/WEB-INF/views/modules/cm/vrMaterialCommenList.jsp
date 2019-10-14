<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>VR素材管理</title>
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
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cm/vrMaterialCommen/">VR素材列表</a></li>		
			<li><a href="${ctx}/cm/vrMaterialCommen/from">VR素材添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/cm/vrMaterialCommen/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<input type="text" name="title" class="input-medium" value="${findVrMaterialCommenPage.title}" maxlength="30" placeholder="标题">
			</li>
			<li><label>显示渠道：</label>
	    		<select class="selectEnum"  name="showChannel" >
                   <option value="">全部</option>
                   <c:forEach items="${ShowChannel}" var="p">
					<option value="${p}" <c:if test="${p eq findVrMaterialCommenPage.showChannel}">selected='selected'</c:if>>${p.name}</option>
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
				<th>标题</th>
				<th>简介</th>	
				<th>素材归属类型</th>
<!-- 				<th>终端名称</th> -->
				<th>终端类型</th>
				<th>显示渠道</th>
				<th>网址链接</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
					 ${item.title}
				</td>
				<td>
					 <div title="${item.brief }">${fns:abbr(item.brief,45)}</div>  
				</td>
				 <td >
                     ${item.remark}
				</td>
<!-- 				<td> -->
<%-- 					 ${item.shopName} --%>
<!-- 				</td> -->
				 <td>
				 	<c:set value="${ fn:split(item.shopType, ',') }" var="arr" />
					<c:forEach items="${arr}" var="s">
						<c:forEach items="${mecShopChannel}" var="i">
		               	 	<c:if test="${i.value eq s}">
		               	 		${i.label}
		               	 	</c:if>
		                </c:forEach>
		            </c:forEach>
				</td>
				 <td>
	            	<c:forEach items="${ShowChannel}" var="i">
						<c:if test="${i eq item.showChannel}">${i.name}</c:if>
					</c:forEach>
	            </td>
				<td>
					<c:if test="${not empty item.linkUrl}">
					  <a href="${item.linkUrl}" target="_black">查看链接</a>
					</c:if>
				</td>
						
				<td>
					<a href="${ctx}/cm/vrMaterialCommen/from?code=${item.code}">修改</a>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>