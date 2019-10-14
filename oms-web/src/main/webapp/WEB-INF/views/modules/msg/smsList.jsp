<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>短信群发列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		$("#pageNo").val(1);
		$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/msg/sms">短信群发列表</a></li>
<%-- 		<shiro:hasPermission name="cm:textInfo:edit"> --%>
			<li><a href="${ctx}/msg/sms/form">新建短信群发</a></li>
<%-- 		</shiro:hasPermission> --%>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>素材标题</th>
				<th>素材内容</th>
				<th>发送时间</th>
				<th>地区范围</th>
				<th>客户组别</th>
				<th>发送数量</th>
				<th></th>	
				 <th>操作</th>	
			</tr>
		</thead>
	</table>
     </div>
</body>
</html>