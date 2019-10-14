<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/message/jquery.my-message.1.1.js"></script>
	<link rel="stylesheet"href="${ctxStatic}/message/jquery.my-message.1.1.css" type="text/css"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		$(document).ready(function() { 
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				width: 380,
			    height: 'auto',
			}
			$.jBox.setDefaults(jBoxConfig);
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg, "error", 5000);
				$("#repMsg").val("");
			}
			
			var importMsg=$("#importMsg").val();
			if(importMsg){
				$.jBox.success(importMsg, '提示');
				$("#importMsg").val("");
			}
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			
		});
	</script>
	<style type="text/css">
.img-small {
	width: 30px;
	height: 30px;
}

.container {
	padding: 20px 30px;
	width: 100%;
	min-height: 800px;
	background: #fff;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.page_header {
	font-size: 32px;
	font-weight: normal;
	line-height: 1;
	padding-bottom: 40px;
	color: #666;
}
.nav-tabs > li > a {
    padding-top: 0px;
}
.lafen-group {
    position: relative;
}
.lafen-group .img-big {
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -75px;
    margin-left: 35px;
    opacity: 0;
    transform: scale(.2, .2);
    transition: all .2s ease-in-out;
    width: 130px;
    height: 130px;
}
.lafen-group .img-small:hover + .img-big {
    transform: scale(1, 1);
    opacity: 1;
}
.lafen-group .img-big img {
    width: 130px;
    height: 130px;
}
	</style>
</head>
<body>
<div class="container">
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<input id="importMsg" name="importMsg" style="display:none" value="${importMsg}"/>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/tmall/order/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/tmall/order/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tmall/order/list">订单列表</a></li>		
	</ul>
	<form id="searchForm" action="${ctx}/tmall/order" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>下单人姓名：</label>
				<input type="text" name="memberName" class="input-medium" value="${tmallOrderDto.memberName}" maxlength="100" placeholder="下单人姓名">
			</li>
			<li><label>下单人电话：</label>
				<input type="text" name="mobile" class="input-medium" value="${tmallOrderDto.mobile}" maxlength="50" placeholder="下单人电话">
			</li>
			<li><label>下单人旺旺：</label>
				<input type="text" name="noWw" class="input-medium" value="${tmallOrderDto.noWw}" maxlength="100" placeholder="下单人旺旺">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>下单人姓名</th>
				<th>下单人电话</th>
				<th>下单人旺旺</th>
				<th>订单号</th>
				<th>商品名称</th>
				<th>商品链接</th>	
				<th>金额（元）</th>	
				<th>下单时间</th>	
				<th>评论星级</th>
				<th>评论语</th>	
				<th>导入时间</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>	
				<td>
			    	 ${item.memberName}
				</td>	
				<td>
				  ${item.mobile}
				</td>
				<td>
				  ${item.noWw}
				</td>
				<td>
				${item.orderNo }
				</td>
				<td> 
				  ${item.productName}
				</td>
				<td> 
				  ${item.productUrl}
				</td>
				<td> 
				    <fmt:formatNumber value="${item.amount/100}" pattern="#,##0.00"/>
				</td>
				<td> 
				    ${item.orderDate}
				</td>
				<td> 
				    ${item.commentLevel}
				</td>
				<td> 
				    ${item.commentCtx}
				</td>
				
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>