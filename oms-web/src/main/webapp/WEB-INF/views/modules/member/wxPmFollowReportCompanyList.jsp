<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商微信客户跟踪日报</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				if(!$("#infolist tr").length){
					alertx("没有数据无法导出!");
					return false;
				}
				top.$.jBox.confirm("确认要导出报表数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/member/wxPmFollow/company/export");
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
		function CancelQuery(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		
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
		<li class="active"><a href="${ctx}/member/wxPmFollow/company/list">经销商微信客户跟踪日报</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/wxPmFollow/company/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<c:set var="beginIndex" value="${(page.pageNo - 1) * page.pageSize}"></c:set>
		<ul class="ul-form">
		    <li><label>日期：</label>
						<input id="reportDate" name="reportDate" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
		 				value="<fmt:formatDate value="${findWxPmFollowReportCompanyPage.reportDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 
					</li>
					<li><label>经销商代码：</label><input type="text" name="dealerCode" class="input-medium" maxlength="30" placeholder="经销商代码" value="${findWxPmFollowReportCompanyPage.dealerCode}"></li>
					<li><label>经销商名称：</label><input type="text" name="companyName" class="input-medium" maxlength="80" placeholder="经销商名称" value="${findWxPmFollowReportCompanyPage.companyName}"></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>日期</th>
				
				<th>经销商代码</th>
				<th>经销商名称</th>
				<!-- <th>商户名称</th> -->
				
				<th>新增客户数</th>
				<th>未跟踪新增客户数</th>
				<th>已跟踪新增客户数</th>
				<th>老客户数</th>
				<th>未跟踪老客户数</th>
				<th>已跟踪老客户数</th>
				<th>客户总数</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${beginIndex + number.count}
				</td>
				<td>
					<fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					${item.dealerCode}
				</td>
				<td>
					${item.companyName}
				</td>
				<%-- <td>
					${item.merchantName}
				</td> --%>
				<!-- 统计信息 -->
				<td>
					${item.numPmNew}
				</td>
				<td <c:if test="${item.numPmNewNotFollow > 0}">style="color:red"</c:if>>
					${item.numPmNewNotFollow}
				</td>
				<td>
					${item.numPmNewFollow}
				</td>
				<td>
					${item.numPmOld}
				</td>
				<td <c:if test="${item.numPmOldNotFollow > 0}">style="color:red"</c:if>>
					${item.numPmOldNotFollow}
				</td>
				<td>
					${item.numPmOldFollow}
				</td>
				<td>
					${item.numPmTotal}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</body>
</html>