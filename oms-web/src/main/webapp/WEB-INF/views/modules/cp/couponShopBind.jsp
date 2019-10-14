<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绑定终端管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3}).show();

			$("#shopChannel").click(function(){
			    if (this.checked) {
			        shopChannelConfirm(true);
			    } else {
			    	shopChannelConfirm(false);
				}
			});
			function shopChannelConfirm(checked) {
				var status = 'USE';
				var promotionCode = $('#promotionCode').val();

				if(checked) {
					$(":checkbox[name='shopChannelCbox']").attr('checked', true);
				} else {
					$(":checkbox[name='shopChannelCbox']").attr('checked', false);
					var status = 'STOP';
				}
			}


			$('#btnSubmit').click(function(){
			    //loading('正在提交，请稍等...');
				//$('#submitForm').submit();
				 submitAjax();
		   });
		 	var s1=window.localStorage.getItem("c_shopNo");
		 	var s3=window.localStorage.getItem("c_useNums");	
			var shopNos=s1.split(",");
			
			var shopNums=s3.split(",");
			
		    if(s1!=""){
		    	var sMap={};
				for (var i = 0; i < shopNos.length; i++) {
					var ele = shopNos[i];
					setMap(sMap,ele, shopNums[i]);
				}
				initListForm(sMap);
		    }
		});
		//设置map里面的值
	    function setMap(map,id,newsObj)
	    {
	        //如果key也是动态的，则如下处理
	        var key="s_"+id;
	        map[key]=newsObj+"";
	    }
		//跳页
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}

		function initListForm(map){
			$('#submitForm tr:gt(0)').each(function(i, item){
				var promotions = $(item).find('.promotion');
				var shopNo=promotions[0].value;
				var key="s_"+shopNo;
				if(map[key]){
					promotions[0].checked=true;
					promotions[1].value=map[key];
				}
			});
		}
		
		function submitAjax(){
			var shopNos='';
			var shopNames='';
			var shopNums='';
			var split='';
			$('#submitForm tr:gt(0)').each(function(i, item){
				var promotions = $(item).find('.promotion');
				if(promotions[0].checked){
					if(shopNos!=''){
						split=',';
					}
					shopNos=(shopNos+split+promotions[0].value);
					shopNames=(shopNames+split+$(promotions[0]).attr("shopName-value"));
					shopNums=(shopNums+split+promotions[1].value);
				} 
			});
			var obj={};
			obj.shopNos=shopNos;
			obj.shopNames=shopNames;
			obj.shopNums=shopNums;
			return obj;
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

.textNum{width:100px}

.promotionHide{display: none;}
</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<!-- <input id="btnSubmit" class="btn btn-primary" type="button" value="保存"/> -->
	</ul>
		<form id="searchForm" action="${ctx}/cp/coupon/rule/toBindShop" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="promotionCode" id="promotionCode" value="${promotionCode}">

		<ul class="ul-form" style="width:600px;">
			<li><label>终端名称：</label>
		    	<input type="text" name="shopName" value="${reqParam.shopName}" class="input-medium" maxlength="100" placeholder="终端名称">
			</li>

			<li class="btns"><input class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<form id="submitForm"  method="post" >
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
			<thead><tr>
				<th>
					<input type="checkbox" id="shopChannel" value="${item.code}" >全部
				</th>
				<th>终端代码</th>
				<th>终端名称</th>
				<th>终端状态</th>
				<th>所属区域</th>
				<th>优惠券数量</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item" varStatus="itemStatus">
				<tr id="${item.code}" name="${itemStatus.index+1}">
					<td>
						<input class="promotion" id="${itemStatus.index+1}" type="checkbox" name="shopChannelCbox" value="${item.shopNo}" shopName-value="${item.shopName}" <c:if test="${item.remark eq 'N'}" >checked</c:if>>
					</td>
					<td>
						${item.shopNoMerchant}			
					</td>
					<td>${item.shopName}</td>
					<td>
						<c:forEach items="${shopStatus}" var="p">
							<c:choose >
								<c:when test="${p eq item.status }" >${p.name}</c:when>
								<c:otherwise></c:otherwise>	
							</c:choose>
						</c:forEach>
					</td>
					<td>${item.areaName}</td>
					<td>
						<input  class="promotion promotionHide" type="number" onkeypress="return( /[\d]/.test(String.fromCharCode(event.keyCode) ) )" name="couponNum" id="couponNum" value="0" />
					</td>
				</tr>
			</c:forEach></tbody>
		</table>
	</form>
		<div class="pagination hide">${page}</div>
	
	 </div>
</body>
</html>