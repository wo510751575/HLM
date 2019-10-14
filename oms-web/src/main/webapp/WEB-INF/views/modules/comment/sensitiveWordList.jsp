<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>敏感词管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".copyBtn").click(function(){
				$(this).attr("data-clipboard-text",window.location.href+"/viewH5?code="+$(this).attr("data-code"));
			})

			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"敏感词导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过10M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnExport").click(function(){
				if(!$("#contentTable tbody tr").length){
					alertx("没有数据无法导出!");
					return false;
				}
				top.$.jBox.confirm("确认要导出敏感词数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/comment/sensitiveWord/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
			
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/comment/sensitiveWord/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/comment/sensitiveWord/import/template">下载模板</a>
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/comment/sensitiveWord">敏感词列表</a></li>
		<shiro:hasPermission name="comment:sensitiveWord:edit">
			<li><a href="${ctx}/comment/sensitiveWord/form">敏感词添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/comment/sensitiveWord" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>敏感词：</label>
				<input type="text" name="param.name" class="input-medium" maxlength="100" placeholder="敏感词" value="${findSensitiveWordPage.param.name}">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li> --%>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
		</ul> 
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>敏感词</th>
				<th>创建时间</th>
				<shiro:hasPermission name="comment:sensitiveWord:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					<a href="${ctx}/comment/sensitiveWord/form?code=${item.code}">${item.name}</a>					
				</td>
				<td>
					<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="comment:sensitiveWord:edit">
				<td>
					<a href="${ctx}/comment/sensitiveWord/form?code=${item.code}&pageNo=${page.pageNo}&pageSize=${page.pageSize}">修改</a>
					<a href="${ctx}/comment/sensitiveWord/del?code=${item.code}">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript" src="${ctxStatic}/common/clipboard.min.js"></script>
	<script>
    var clipboard = new Clipboard('.copyBtn');

    clipboard.on('success', function(e) {
    	showTip("已将链接复制到粘贴板","info");
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
    </script>
</div>	
</body>
</html>