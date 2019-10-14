<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绑定终端</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
		
			$("#treeTable").treeTable({expandLevel : 3}).show();

			initView();
	    	bindEvent();
	    	
	   	 //实现单选
		    $(":checkbox").click(function(){
				    $("input[type='checkbox']").each(function(){    
				    		if($(this).is(":checked"))    
				    		{    
				                 $(this).attr("checked",false);
				    		}
				   });
			//设置当前选中checkbox的状态为checked
			    $(this).attr("checked",true);
			    
			});
	   	 
	
		});

	
		function initView(){
	    	$('.form-group-select-css').select2();
	    	var info = window.localStorage.getItem('info');
	    	console.log(info);
	    	var arr = info.split(',');
			for(var i=0; i<arr.length; i++) {
				var childArr = arr[i].split('|');
				$('#cbox-'+childArr[2]).attr('checked', true);
			}
	    }

	    function bindEvent(){
	    	$('#btnSubmit').click(function(){
			    //loading('正在提交，请稍等...');
				//$('#submitForm').submit();
				 submitAjax();
		   }); 

	    	$("#shopChannel").click(function(){
				var cbox = this;
				var flag = cbox.checked;
				var selCss = ":checkbox[name='shopChannelCbox']";
				if(flag) {
					$(selCss).attr('checked', true);
				} else {
					$(selCss).attr('checked', false);
				}
			});

			$('#btnQuery').click(function(){

				var noWx = $('[name="noWx"]').val();
				var lists = $('#treeTable tr:gt(0)');
				lists.removeClass('hide');
				
				if(noWx!='' ) {
					var selLists = null;
					var selLists2 = null;
					if(noWx!=''){
						selLists = $('#treeTable tr[alt-noWx*="'+noWx+'"]');
					}
				
					
					for(var i=0; i<lists.length; i++) {
					
						if(selLists!=null) {
							var flag = false;
							for(var j=0; j<selLists.length; j++) {
								if(lists[i].id == selLists[j].id) {
									flag = true;
									break;
								}
							}
							if(!flag) {
								$(lists[i]).addClass('hide');
							}
						}
					}
				}

				
			});
		}
		
		//跳页
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}

		function submitAjax(){
			//console.log(window.localStorage.getItem("info"));
			
			var list = $(':checked[name="shopChannelCbox"]');
			window.localStorage.setItem("info", "");
			if(list.length==0) {
				showTip("请选择");
                return false;
			}
			var arr = new Array();
			for(var i=0; i<list.length; i++) {
				arr.push(list[i].value);
			}
			window.localStorage.setItem("info", arr)
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
		<form id="searchForm" action="${ctx}/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	
			<ul class="ul-form" style="width:100%;">

				<li><label>微信号：</label>
					<input type="text" name="noWx" value="${findShopTerminalPage.noWx}" class="input-medium" maxlength="100" >
				</li>
	
				<li class="btns"><input class="btn btn-primary" type="button" value="查询" id="btnQuery"/></li>
				<li class="clearfix"></li>
			</ul>
		</form>
	<tags:message content="${message}"/>
	<form id="submitForm" action="${ctx}/mec/promotionInfo/bindPromotionShop2" method="post" >
		<input type="hidden" name="promotionCode" id="promotionCode" value="${promotionCode}">
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
			<thead><tr>
				<th>
				</th>
				<th>微信号</th>
				<th>微信昵称</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty page.list}"><tr><td colspan="3" style="text-align: center;">暂无数据</td></c:if>
			<c:forEach items="${page.list}" var="item" varStatus="itemStatus">
				<tr id="${item.code}" alt-noWx="${item.noWx}" >
					<td>
						<input class="promotion" id="cbox-${item.code}" type="checkbox" name="shopChannelCbox" value="${item.code}|${item.noWx}|${item.shopName}" >
					</td>
					<td class="">
						${item.noWx}			
					</td>
					<td>
						${item.wxNickname}
					</td>
				</tr>
			</c:forEach></tbody>
		</table>
	</form>
		<div class="pagination hide">${page}</div>
	
	 </div>
</body>
</html>