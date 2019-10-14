<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>今日客户汇总信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
    min-height: 80px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/st/todayMemberSummary/list">今日客户汇总列表</a></li>
	</ul>
 	<form id="searchForm" action="${ctx}/st/todayMemberSummary" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li>
				<label>汇总日期：</label>
				<input id="summaryDate" name="summaryDate" type="text" readonly="readonly" maxlength="20" class="input Wdate"
				value="<fmt:formatDate value="${findTodayMemberSummaryPage.summaryDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
		    <li><label>终端名称：</label>
	    		<select class="selectEnum"  name="shopNo" >
                   <option value="">全部</option>
                   <c:forEach items="${shops}" var="p">
					<option value="${p.shopNo}" <c:if test="${p.shopNo eq findTodayMemberSummaryPage.shopNo}">selected='selected'</c:if>>${p.shopName}</option>
				</c:forEach>
               </select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form> 
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>汇总日期</th>
				<th>终端名称</th>
 				<th>导购姓名</th>
			    <th>到店客户数</th>
			    <th>意向客户数</th>
			    <th>扫码客户数</th>
			    <th>未扫码原因</th>
			    <th>填写资料客户数</th>
			    <th>未填写资料原因</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					<fmt:formatDate value="${item.summaryDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${item.shopName}
				</td>
				<td>
					${item.memberNameGm}
				</td>
				<td>
					${item.shopCount}
				</td>
				<td>
					${item.intentionCount}
				</td>
				<td>
					${item.scanCount}
				</td>
				<td>
					${fns:abbr(item.unscanReason,50)}
				</td>
				<td>
					${item.infoCount}
				</td>
				<td>
					${fns:abbr(item.uninfoReason,50)}
				</td>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>