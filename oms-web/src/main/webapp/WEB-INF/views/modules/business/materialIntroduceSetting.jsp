<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>素材模版设置列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".copyBtn").click(function(){
				$(this).attr("data-clipboard-text",window.location.href+"/viewH5?code="+$(this).attr("data-code"));
			})
			
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
	.materialInfo{
		width: 70%;
	}
	.materialInfo img{
	width:223px;
	height:334px;
	margin-left: 10%;
	cursor: pointer;
	margin-top: 20px;
	}
	
	
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/business/materialIntroduce/setting/">素材模版列表</a></li>
		<li ><a href="${ctx}/business/materialIntroduce/settingForm?id=${data.id}">素材模版添加</a></li>
	</ul>
	<tags:message content="${message}"/>
	<div class="materialInfo">
		<c:forEach items="${list}" var="item" varStatus="number"> 
			<!-- 		循环所有模版 		A标签点击模版跳转至添加素材 ：${ctx}/business/materialcommen/form?tempId=${item.id} -->
		<img src="${fns:getUploadUrl()}${item.value }">
		</c:forEach>
	
	</div>
	
	
</div>	
</body>
</html>