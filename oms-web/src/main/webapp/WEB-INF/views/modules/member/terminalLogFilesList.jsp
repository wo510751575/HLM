<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端日志</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//向终端下发上传日志文件指令
	        $("#btnUpload").click(function(){
	        	var imei = $("#imei").val();
	        	$.ajax({
	                type:"POST",
	                url:"${ctx}/member/terminalLogFiles/getLogFiles",
	                data:{imei:imei},
	                success:function(result){
	                	if(result.state){
	                		showTip(result.msg,"info");
	                	}else{
	                		showTip("向终端下发上传日志文件指令失败！","warning");
	                	}
	                }
	            });		    		
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
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
		#myModal{
		position: fixed;
	    top: 10%;
	    left: 50%;
	    z-index: 1050;
	    width: 560px;
	    margin-left: -280px;
	    background-color: #fff;
	    border: 1px solid #999;
	    border: 1px solid rgba(0,0,0,0.3);
	    -webkit-border-radius: 6px;
	    -moz-border-radius: 6px;
	    border-radius: 6px;
	    outline: 0;
	    -webkit-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -moz-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -webkit-background-clip: padding-box;
	    -moz-background-clip: padding-box;
	    background-clip: padding-box;
		}
		
	</style>
</head>
<body>
<div class="container">
	
	<form id="searchForm" action="${ctx}/member/terminalLogFiles" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="imei" name="imei" type="hidden" value="${findTerminalLogFilesPage.imei}"/>
		<ul class="ul-form">
		    <%-- <li><label>文件名称：</label>
		    	<input type="text" name="logFileName"  value="${findTerminalLogFilesPage.logFileName}" class="input-medium" maxlength="50" placeholder="文件名称">
			</li> --%>
			<li>
				<label>日志创建时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findTerminalLogFilesPage.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findTerminalLogFilesPage.endTime}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="member:terminalLogFiles:edit">
			<li class="btns"><input id="btnUpload" class="btn btn-primary" type="button" value="获取日志"/></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件名称</th>
				<th>日志创建时间</th>
				<th>上传时间</th>
				<shiro:hasPermission name="member:terminalLogFiles:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="logFile" varStatus="number"> 
			<tr>
			    <td>
					${logFile.logFileName}
				</td>
				<td>
					<fmt:formatDate value="${logFile.logBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${logFile.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:terminalLogFiles:edit">
				<td>
					<a href="${fns:getImUploadUrl()}${logFile.logAddr}" data-code="${logFile.code}" class="downloadLogFiles" download>下载</a> 
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