<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店员列表</title>
	<meta name="decorator" content="default"/>
		<script type="text/javascript">

        function del2(memberGmNo){
        
        	$.ajax({
                type:"POST",
                dataType:"text",
                url:"${ctx}/ai/merchantPreProblem/deleteAutoReply",
                data:{gmNo:memberGmNo},
                success:function(result){
                   alert("删除成功");
                   window.location.href='${ctx}/ai/merchantPreProblem/selectAutoReplyList';
                }
            });
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
	    <li ><a href="${ctx}/ai/merchantPreProblem/">商家预设问题列表</a></li>		
			<li><a href="${ctx}/ai/merchantPreProblem/form">商家预设问题添加</a></li>	
		<li class="active"><a href="${ctx}/ai/merchantPreProblem/selectAutoReplyList">自动回复列表</a></li>
		<li ><a href="${ctx}/ai/merchantPreProblem/toAddAutoReply">新增</a></li>
	</ul>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>导购</th>
				<th>状态</th>
				<shiro:hasPermission name="member:guid:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${list}" var="member" varStatus="number"> 
			<tr>
			   <td>${member.memberGmName }</td>
				<td>
			                开启
				</td>
				
				<td>
					<a onclick="javascript:del2('${member.memberGmNo}');">删除</a>	
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>

	</div>
</body>
</html>
