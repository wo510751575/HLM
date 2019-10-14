<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单红包配置管理</title>
	<meta name="decorator" content="default"/>
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
	 
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tmall/bonuscfg/list">订单红包配置列表</a></li>
	    <li ><a href="${ctx}/tmall/bonuscfg/form?code=${data.code}">订单红包配置${not empty tmallBonusConfigDto.code?'修改':'添加'}</a></li>		
	</ul>
	<form id="searchForm" action="${ctx}/tmall/bonuscfg" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单金额：</label>
				<input type="text" name="ordAmtMinDec" class="input-medium " value="${tmallBonusConfigDto.ordAmtMinDec}" maxlength="100" >
				-
				<input type="text" name="ordAmtMaxDec" class="input-medium" value="${tmallBonusConfigDto.ordAmtMaxDec}" maxlength="100" >
			</li>
			 
			<li><label>红包金额：</label>
				<input type="text" name="bonuxMinDec" class="input-medium" value="${tmallBonusConfigDto.bonuxMinDec}" maxlength="100" >
				-
				<input type="text" name="bonuxMaxDec" class="input-medium" value="${tmallBonusConfigDto.bonuxMaxDec}" maxlength="100" >
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单金额</th>
				<th>红包金额</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>	
				<td>
				 	<fmt:formatNumber value="${item.ordAmtMin/100}" pattern="#,##0.00"/>
				 		-
				 	 <fmt:formatNumber value="${item.ordAmtMax/100}" pattern="#,##0.00"/>
			    	 
				</td>	
				<td>
				  	<fmt:formatNumber value="${item.bonuxMin/100}" pattern="#,##0.00"/>
				 		-
				 	 <fmt:formatNumber value="${item.bonuxMax/100}" pattern="#,##0.00"/>
			    	 
				</td>
				<td>
				  <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td> 
				
				    <a href="${ctx}/tmall/bonuscfg/form?code=${item.code}">修改</a>
				    &nbsp;&nbsp;<a href="${ctx}/tmall/bonuscfg/remove?code=${item.code}" onclick="return confirmx('确认要删除该配置吗？', this.href)">删除</a>&nbsp;&nbsp; 
				    
				</td>
			 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>