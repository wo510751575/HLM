<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>中控端监控</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			 var Msg=$("#msg").val();
		     if(Msg){
		    	 showTip(Msg); 
		      }
			
			var refreshSwitch=$("#refreshSwitch").val();
			var refreshTime=$("#refreshTime").val();
			if(refreshSwitch==1){//仅当1时为开
				var time=setInterval("time()",refreshTime);
			}
			
			//查看日志
			$('.showLogs').click(function() {
				var imei = $(this).data("imei");
				var terminalCode=$(this).data("terminalcode");
				var merchantNo = $(this).data("merchantNo");
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/terminalLogFiles?imei="+imei + "&merchantNo="+merchantNo,"终端日志    :  "+terminalCode, 920, 730,{//宽,高
					id:9527,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} /* 信息关闭后执行的函数 */
				});
		    });
		});
		
		function demo(stCode){
			var stCode;
			$.ajax({
				   type:"POST",
			        url:"${ctx}/member/terminalImStatus/checkAndUpdateVersionAll",
			        data:{stCode:stCode},
			        success:function(result){
			        	if(result){
			        		showTip(result.returnMessage,result.type);
			        	}
			        }
			   });
		}
		function communication(stCode){
			var stCode;
			$.ajax({
				   type:"POST",
			        url:"${ctx}/member/terminalImStatus/syncFriendsRequestAll",
			        data:{stCode:stCode},
			        success:function(result){
			        	if(result){
			        		showTip(result.returnMessage,result.type);
			        	}
			        }
			   });
		}
		
    	
		function time(){
			$("#btnSubmit").click();
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
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
		<li class="active"><a href="${ctx}/member/terminalImStatus/batteryLevelAll">中控端监控</a></li>
		<li><a href="${ctx}/member/terminalImStatus/gmBatteryLevelAll">导购端监控</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/member/terminalImStatus/batteryLevelAll" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" value="${fns:getDictValue('switch','interval_refresh','1')}" id="refreshSwitch">
		<input type="hidden" value="${fns:getDictValue('time','interval_refresh','10000')}" id="refreshTime">
		<input id="msg" name="msg" type="hidden" value="${msg}"/>
		<ul class="ul-form">
			<li><label>商户名称：</label>
				<input type="text" name="merchantName" class="input-medium" value="${findTerminalBatteryLevelPage.merchantName}" maxlength="100" placeholder="商户名称">
			</li>
<!-- 			<li><label>终端名称：</label> -->
<%-- 				<input type="text" name="shopName" class="input-medium" value="${findTerminalBatteryLevelPage.shopName}" maxlength="100" placeholder="终端名称"> --%>
<!-- 			</li> -->
			<li><label>终端编码：</label>
				<input type="text" name="terminalCode" class="input-medium" value="${findTerminalBatteryLevelPage.terminalCode}" maxlength="20" placeholder="终端编码">
			</li>
			<li><label>手机串号：</label>
				<input type="text" name="imei" class="input-medium" value="${findTerminalBatteryLevelPage.imei}" maxlength="150" placeholder="手机串号">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
<!-- 				<th>终端名称</th> -->
				<th>终端编码</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>手机串号</th>
				<th>手机电量</th>
				<th>手机状态</th>
			    <th>客户端版本号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  ${item.merchantName}
				</td>
<!-- 				<td> -->
<%-- 				  ${item.shopName} --%>
<!-- 				</td> -->
				<td>
					 ${item.terminalCode}
				</td>
				<td>
				 ${item.noWx}
				</td>
				<td>
				 ${item.wxNickname}
				</td>
				<td>
					 ${item.imei}
				</td>
				<td>
					<c:if test="${item.onlineFlag eq 0}">
						<mark><font color="#d9534f"><b>未知</b></font></mark>
					</c:if>
					<c:if test="${item.onlineFlag eq 1}">
						<c:choose>
							<c:when test="${item.batteryLevel gt 20}"><font color="#5cb85c"><fmt:formatNumber type="percent" value="${item.batteryLevel}" pattern="#.##" />%</font></c:when>
							<c:when test="${item.batteryLevel gt 10 && item.batteryLevel le 20}"><font color="#f0ad4e"><fmt:formatNumber type="percent" value="${item.batteryLevel}" pattern="#.##" />%</font></c:when>
							<c:otherwise><font color="#d9534f"><fmt:formatNumber type="percent" value="${empty item.batteryLevel?0:item.batteryLevel}" pattern="#.##" />%</font></c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td>
					<c:if test="${item.onlineFlag eq 0}"><font color="#d9534f">离线</font></c:if>
					<c:if test="${item.onlineFlag eq 1}"><font color="#5cb85c">在线</font></c:if>
				</td>
				<td> 
				  ${item.versionName}
				</td>
				<td>
				  <a href="javascript:;" class="showLogs" data-imei="${item.imei}" data-terminalcode="${item.terminalCode}"data-merchantNo="${item.merchantNo}" >查看日志</a>
				  <a onclick="demo('${item.stCode}')" href="javascript:;" data-imei="${item.stCode}" >检查升级</a>
				   <a onclick="communication('${item.stCode}')" href="javascript:;" data-imei="${item.stCode}" >同步通讯录</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>