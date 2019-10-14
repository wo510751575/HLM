<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>终端管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		//终端全选流程控制
		
	       //全选
        $("#allcheck").click(function(){
        	if($(this).is(':checked')){
        		$(".shopNochose").prop("checked",true);
        	}else{
        		$(".shopNochose").prop("checked",false);
        	}
        });
        
        //单选终端
        $(".shopNochose").click(function(){
        	if($(this).is(':checked')){
        		$(this).siblings(".shopNochose").prop("checked",true);
        	}else{
        		$(this).siblings(".shopNochose").prop("checked",false);
        	}
        });
		
	});
	
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/sys/user/shopShowList");
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
	</style>
</head>
<body>
	<div id="section" style="margin-left: 0px;">
		<div class="container">
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/sys/user/shopShowList">终端列表</a></li>
			</ul>
			<form id="searchForm" action="${ctx}/sys/user/shopShowList" method="post"class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden"value="${page.pageNo}" /> 
				<input id="pageSize" name="pageSize"type="hidden" value="${page.pageSize}" />
				<input id="companyNo" name="companyNo"type="hidden" value="${companyNo}" />
				<ul class="ul-form">
					<li><label>终端名称：</label> <input type="text" name="shopName"
						class="input-medium" maxlength="100" placeholder="终端名称"
						value="${param.shopName}"></li>
					<li><label>终端状态：</label> 
					<select name="status" class="select valid" style="width: 177px;">
							<option value="">全部</option>
							<c:forEach items="${shopStatus}" var="item">
								<option value="${item}"
									<c:if test="${item eq param.status}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
					</select></li>
				</ul>
				<ul class="ul-form">
				<li><label>所属区域：</label>
				 <select name="areaCode"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
					 <option value="${item.value}"  <c:if test="${item.value eq param.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>					
				 </select>
			    </li>
				<li><label>开店时间：</label> 
						<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
						value="${param.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
						-- 
						<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="${param.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
				</li>
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick="return page();"/></li>
					<li class="clearfix"></li>
				</ul>
			</form>
			<tags:message content="${message}" />
			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
					   <td>
					                   全选<input type="checkbox" id="allcheck" />
				        </td>
						<th>终端代码</th>
						<th>终端名称</th>
						<th>终端类型</th>
						<th>联系电话</th>
						<th>终端状态</th>
						<th>所属区域</th>
						<th>开店时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="item" varStatus="number">
						<tr>
				            <td>
							<input type="checkbox"  class="shopNochose" name="shopNos" value="${item.shopNo}" <%-- <c:if test="${fn:contains(shopMap,item.shopNo)}" >checked</c:if> --%>/>
							</td>
							<td><a href="javascript:;" class="view_btn" data-code="${item.code}" data-shopNoMerchant="${item.shopNoMerchant}">${item.shopNoMerchant}</a></td>
							<td>${item.shopName}</td>
							<td>${item.shopType}</td>
							<td>${item.telephone}</td>
							<td><c:forEach items="${shopStatus}" var="status">
									<c:if test="${item.status eq status}">${status.name}</c:if>
								</c:forEach></td>
							
							<td>${fns:getAreaName(item.provinceCode)}${fns:getAreaName(item.cityCode)}${fns:getAreaName(item.cityAreaCode)}</td>
							<td><fmt:formatDate value="${item.openDate}" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>

</body>
</html>