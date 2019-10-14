<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 1,
			beforeExpand : function($treeTable, id) {
	            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
	            if ($('.' + id, $treeTable).length) { 
	            	return; 
	            }
	            $.ajax({
	    			url: "${ctx}/sys/area/findChilds",
	    			type: "POST",
	    			data: {
	    				parentId :id
	    			},
	    			dataType: "json",
	    			cache: false,
	    			success: function(data) {
	    				 var html = '';
	    				//这里的html可以是ajax请求
			            for(var i=0;i<data.length;i++) {  
	                        var area = data[i]; //获取子级  
	                        var initial= area.initial||"";
	                        var remarks= area.remarks||"";
	                        var zone_label= area.zone_label||"";
	                        html+='<tr id="'+area.id+'" pId='+id+' hasChild="'+area.hasChild+'" controller="'+area.hasChild+'" >'
	                        	+'<td><a href="${ctx}/sys/area/form?id='+area.id+'">'+area.name+'</a></td>'
	                        	+'<td>'+area.code+'</td>'
	                        	+'<td>'+area.type_label+'</td>'
	                        	+'<td>'+zone_label+'</td>'
	                        	+'<td>'+initial+'</td>'
	                        	+'<td>'+remarks+'</td>'
	                        	+'<shiro:hasPermission name="sys:area:edit"><td>'
	                        	+'	<a href="${ctx}/sys/area/form?id='+area.id+'">修改</a>'
	                        	+'	<a href="${ctx}/sys/area/delete?id='+area.id+'" onclick="return confirmx('+'要删除该区域及所有子区域吗？'+', this.href)">删除</a>'
	                        	+'	<a href="${ctx}/sys/area/form?parent.id='+area.id+'">添加下级区域</a> '
	                        	+'</td></shiro:hasPermission></tr>';
	    				}
			            $treeTable.addChilds(html);
	    			}
	    		});
	        }
		});
	});
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
	.table td i{margin:0 2px;}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/area/">区域列表</a></li>
		<shiro:hasPermission name="sys:area:edit"><li><a href="${ctx}/sys/area/form">区域添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>区域名称</th><th>区域编码</th><th>区域类型</th><th>区域地带</th><th>首字母</th><th>备注</th><shiro:hasPermission name="sys:area:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList">
			<c:forEach items="${list}" var="area">
				<tr id="${area.id}" pId="0" hasChild="true">
					<td><a href="${ctx}/sys/area/form?id=${area.id}">${area.name}</a></td>
					<td>${area.code}</td>
					<td>${fns:getDictLabel(area.type, 'sys_area_type', '无')}</td>
					<td>${fns:getDictLabel(area.zone, 'erp_dict_1', '无')}</td>
					<td>${area.initial}</td>
					<td>${area.remarks}</td>
					<shiro:hasPermission name="sys:area:edit"><td>
						<a href="${ctx}/sys/area/form?id=${area.id}">修改</a>
						<a href="${ctx}/sys/area/delete?id=${area.id}" onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)">删除</a>
						<a href="${ctx}/sys/area/form?parent.id=${area.id}">添加下级区域</a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<!-- 	<script type="text/template" id="treeTableTpl"> 
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/area/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.code}}</td>
			<td>{{dict.type}}</td>
			<td>{{dict.zone}}</td>
			<td>{{row.initial}}</td>
			<td>{{row.remarks}}</td>
			<shiro:hasPermission name="sys:area:edit"><td>
				<a href="${ctx}/sys/area/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/area/delete?id={{row.id}}" onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/area/form?parent.id={{row.id}}">添加下级区域</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>-->
	</div>
</body>
</html>