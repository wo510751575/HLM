<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公用素材管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".copyBtn").click(function(){
				//生成网址
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
		.scanCodeList img{width: 36px;height: 36px;}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/member/shopTerminal/selectFlowWx">活码列表</a></li>
			<li><a href="${ctx}/member/shopTerminal/toAddShopTerminalFlowPage">新增粉丝活码</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活码名称</th>
				<th>微信数量</th>
				<th>微信账号</th>
				<th>活码内容</th>
				<th>创建时间</th>
				<th>操作</th>
		
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="item" varStatus="number">
			<tr>
				<td>
					${item.pmName}
				</td>
				<td>
			        ${item.num }
				</td>
				<td class="scanCodeList">
 			          ${item.flowWxNo}
 				</td>
				<td>
					${item.qcodeContent}
				</td>
				 <td>
				 <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/>
				
				</td>
			
				<td>
				<a href='${item.flowQcode}'>下载二维码</a>
				<a href='${ctx}/member/shopTerminal/selectFlowWxByCode?code=${item.code}'>编辑</a>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>


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
