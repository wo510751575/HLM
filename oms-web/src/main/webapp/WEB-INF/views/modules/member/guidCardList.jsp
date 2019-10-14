<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购名片管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").attr("action","${ctx}/member/guidCard/");
				$("#searchForm").submit();
	        	return false;
	        }
        }
	</script>
	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
#wechart_info .personal_header .hrf{
    width:35px;
    height:18px;
    margin:0px 15px;
    background:url("${ctxStatic}/images/hrf.png") no-repeat 50%;
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
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/guidCard/list/">导购名片列表</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/guidCard/list/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>导购姓名：</label>
		    	<input type="text" name="memberNameGm" value="${param.memberNameGm}" class="input-medium" maxlength="100" placeholder="导购姓名">
			</li>
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${param.mobile}" class="input-medium" maxlength="11" placeholder="手机号">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
		</ul>
	</form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导购姓名</th>
				<th>终端</th>
				<th>人气数</th>
				<th>被赞数</th>
				<th>保存数</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.memberNameGm}
				</td>
				<td>
					${item.shopName}
				</td>
				<td>
					${item.pageView}
				</td>
				<td>
					${item.numPraise}
				</td>
				<td>
					${item.numCollection}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
	
</body>
</html>