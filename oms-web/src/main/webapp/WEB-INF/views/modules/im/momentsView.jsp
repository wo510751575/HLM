<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>朋友圈详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//重发朋友圈
			$(".resend").click(function(){
				var friendsCode=$(this).data("code");
				loading("正在重发，请稍等...");
	        	$.ajax({
	                type:"POST",
	                url:"${ctx}/friendsjob/resend",
	                data:{friendsCode:friendsCode},
	                success:function(result){
	                	closeLoading();
                		showTip3(result.msg);
	                }
	            });
			});
		});
		//提示并延迟刷新
		function showTip3(mess) {
			resetTip();
        	setTimeout(function(){
        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:2000});
        	}, 500,setTimeout(function(){
        		window.location.reload();
			},2000));
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
	#contentTable th,#contentTable td{
		text-align: center;
		vertical-align: middle;
	}
	</style>
</head>
<body>
<div class="container">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>终端名称</th>
				<th>终端微信</th>
				<th>发送时间</th>
				<th>消息状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${data}" var="item" varStatus="number"> 
			<tr>
				<td>${number.count}</td>
				<td>${item.shopName}</td>
				<td>${item.noWxShop}</td>
				<td><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:forEach items="${FriendsSendStatus}" var="fss">
						<c:if test="${item.status eq fss.status}">${fss.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:if test="${item.status eq 3 || item.status eq 1}"><!-- 发送失败或者发送中 -->
						<a href="javascript:;" class="resend" data-code="${item.code}">重发</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>	
</body>
</html>