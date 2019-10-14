<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//属性配置
			$('.propertyConfig').click(function() {
				var id = $(this).data("id");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/projectProperty/view?projectCode="+id,"服务属性管理", 920, 730,{//宽高
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
		<li class="active"><a href="${ctx}/member/serviceProject/">服务项目列表</a></li>
			<li><a href="${ctx}/member/serviceProject/form">服务项目添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/serviceProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>项目名称：</label>
		    	<input type="text" name="projectName"  value="${findServiceProjectPage.projectName}" class="input-medium" maxlength="100" placeholder="项目名称">
			</li>
			<li><label>终端名称：</label>
				<input type="text" name="shopName" value="${findServiceProjectPage.shopName}" class="input-medium" maxlength="100" placeholder="终端名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>显示序号</th>
				<th>项目名称</th>
				<th>分店名称</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="member:serviceProject:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.showIndex}
				</td>
				<td>
					${item.projectName}
				</td>
				 <td>
					${item.shopName}
				</td>
				<td>
				    ${item.createId}
				</td>
				<td>
				    <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  <a href="${ctx}/member/serviceProject/form?code=${item.code}">修改</a>
				  <a href="javascript:;" class="propertyConfig" data-id="${item.code}" ">属性配置</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>