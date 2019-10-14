<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#myModal").on("hidden", function() {  
		        $(this).removeData("modal");  
		    }); 
			
			//查看日志
			$('.showLogs').click(function() {
				var imei = $(this).data("imei");
				var terminalCode=$(this).data("terminalcode");
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/terminalLogFiles?imei="+imei,"终端日志    :  "+terminalCode, 920, 730,{//宽,高
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
			
			//编辑微信支付密码
			$('.showWxPwd').click(function() {
				$("#mCode").val("");
				$("#wxPwd").val("");
				$("#wxPwd2").val("");
				var code = $(this).data("code")||"";
				$("#mCode").val(code);
		    });
			
			//编辑微信支付密码modal提交
			$(".myModalSubmit").click(function(){
				var pwd=$("#wxPwd").val()||"";
				var pwd2=$("#wxPwd2").val()||"";
				if(!submitCk(pwd)){
					return false;
				}
				if(!submitCk(pwd2)){
					return false;
				}
				if(pwd && pwd2 && pwd!=pwd2){
					alertx("请输入一致的密码");
					return false;
				}
				loading("正在提交，请稍等...");
	        	var code = $("#mCode").val()||"";
	        	$.ajax({
	                type:"POST",
	                url:"${ctx}/member/shopTerminal/updateWxPwd",
	                data:{code:code,pwd:pwd},
	                success:function(result){
	                	closeLoading();
	                	$(".myModalClose").click();
	                	showTip3(result.msg);
	                },
	                error : function(error) {  
	                	closeLoading();
	                	$(".myModalClose").click();
	                	showTip3(error.responseText);
	                }
	            });	
			});
			
		});
		//提示并延迟刷新(使用setTimeout第三个参数)
		function showTip3(mess) {
			resetTip();
        	setTimeout(function(){
        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:2000});
        	}, 500,setTimeout(function() {
                window.location.reload();
            }, 2500));
		}
		//js正则验证密码
		function submitCk(pwd) {
			var reg = /^\d{6}$/;
			if (!reg.test(pwd)) {
				alertx("请输入6位的数字");
				return false;
			}
			return true;
		}
		function upgrade(stCode){
			var stCode;
			confirmx("确认终端检查升级？", function(){
				$.ajax({
					   type:"POST",
				        url:"${ctx}/member/terminalImStatus/checkAndUpdateVersion",
				        data:{stCode:stCode},
				        success:function(result){
				        	if(result){
				        		showTip(result.returnMessage,result.type);
				        	}
				        }
				   });
			}, closed);
		}
		
		<!--同步通讯录-->
		function communication(stCode){
			confirmx("确认同步通讯录？", function(){
				$.ajax({
					   type:"POST",
				        url:"${ctx}/member/terminalImStatus/syncFriendsRequest",
				        data:{stCode:stCode},
				        success:function(result){
				        	if(result){
				        		showTip(result.returnMessage,result.type);
				        	}
				        }
				   });
			}, closed);
		}
		
		<!--同步历史记录-->
		function communication2(noWxGm){
			confirmx("确认历史记录？", function(){
				$.ajax({
					   type:"POST",
				        url:"${ctx}/member/terminalImStatus/sysLastChatData",
				        data:{noWxGm:noWxGm},
				        dataType:"json",
				        success:function(data){
				     
				        	if(data.result == 'true' || data.result == true){
				        		showTip("检查成功，开始同步");
				        	}
				        	if(data.result == 'false' || data.result == false){
				        		showTip(data.message);
				        	}
				        }
				   });
			}, closed);
		}
		
		//同步群消息
		function sendSyncChatRoom(noWxZk){
			confirmx("确认同步群消息？", function(){
				$.ajax({
					   type:"POST",
				        url:"${ctx}/member/terminalImStatus/sendSyncChatRoom",
				        data:{noWxZk : noWxZk},
				        success:function(result){
				        	if(result){
				        		showTip(result.returnMessage,result.type);
				        	}
				        }
				   });
			}, closed);
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
i{
	font-size: 20px;
}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/shopTerminal">终端列表</a></li>		
			<li><a href="${ctx}/member/shopTerminal/form">终端添加</a></li>	
	</ul>
	<form id="searchForm" action="${ctx}/member/shopTerminal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>微信号：</label>
				<input type="text" name="noWx" class="input-medium" value="${findShopTerminalPage.noWx}" maxlength="100" placeholder="微信号">
			</li>
			<li><label>微信昵称：</label>
				<input type="text" name="wxNickname" class="input-medium" value="${findShopTerminalPage.wxNickname}" maxlength="50" placeholder="微信昵称">
			</li>
			<%-- <li><label>手机串号：</label>
				<input type="text" name="imei" class="input-medium" value="${findShopTerminalPage.imei}" maxlength="100" placeholder="手机串号">
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信头像</th>
				<th>终端编码</th>
				<th>终端名称</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>状态</th>
				<th>终端版本号</th>	
				<th>微信版本号</th>	
				<th>上传朋友圈</th>	
				<th>创建时间</th>	
				<th>操作</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>	
				<td>
			    	<div class="lafen-group">
					<img class="img-small" src="${item.headurl}" alt="">
			    	<div class="img-big">
			    		<img src="${item.headurl}" alt="">
			    	</div>
			    	</div>
				</td>	
				<td>
				  ${item.terminalCode}
				</td>
				<td>
				  ${item.shopName}
				</td>
				<td>
					 ${not empty item.alias?item.alias:item.noWx}
				</td>
				<td>
					 ${item.wxNickname}
				</td>
				<td>
					<c:choose>
						<c:when test="${item.status eq 1}">正常</c:when>
						<c:when test="${item.status eq 2}">注销</c:when>
						<c:otherwise>未激活</c:otherwise>
					</c:choose>
				</td>
				<td> 
				  ${item.versionName}
				</td>
				<td> 
				  ${item.wxVersionName}
				</td>
				<td> 
<%-- 				  <c:choose> --%>
<%-- 					<c:when test="${item.uploadFriendsFlag == 1}"> --%>
<!-- 						打开 -->
<%-- 						<a href="${ctx}/member/shopTerminal/updateUploadFriendsFlag?code=${item.code}&uploadFriendsFlag=${item.uploadFriendsFlag == 1?0:1}" onclick="return confirmx('确认要${item.uploadFriendsFlag == 1?'关闭':'打开'}朋友圈上传功能吗？', this.href)">${item.uploadFriendsFlag == 1?'关闭':'打开'}</a> --%>
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
<!-- 						关闭 -->
						<a href="${ctx}/member/shopTerminal/updateUploadFriendsFlag?code=${item.code}&uploadFriendsFlag=${item.uploadFriendsFlag == 1?0:1}" onclick="return confirmx('确认要${item.uploadFriendsFlag == 1?'关闭':'打开'}朋友圈上传功能吗？', this.href)">${item.uploadFriendsFlag == 1?'关闭':'打开'}</a>
<%-- 					</c:otherwise> --%>
<%-- 				  </c:choose> --%>
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="member:shopTerminal:edit">
<%-- 					   <a href="${ctx}/member/shopTerminal/form?code=${item.code}">修改</a> --%>
					   <a href="${ctx}/member/shopTerminal/form?code=${item.code}" title="修改">
					  	 <span><i class="icon-edit"></i></span>
					   </a>
					   <a onclick="upgrade('${item.code}')" href="javascript:;" data-imei="${item.code}" title="检查升级">
					   		<span><i class="icon-upload"></i></span>
					   </a>
					   <a class="showWxPwd" href="javascript:;"  data-toggle="modal" data-target="#myModal" data-code="${item.code}" title="${empty item.wxPwd?'填写':'修改'}支付密码">
					   		<span><i class="icon-credit-card"></i></span>
					   </a>
					   <a onclick="communication('${item.code}')" href="javascript:;" data-imei="${item.code}" title="同步通讯录">
					   		<span><i class="icon-user"></i></span>
					   </a>
					   <a onclick="communication2('${item.noWx}')" href="javascript:;" data-imei="${item.code}" title="同步聊天记录">
					   		<span><i class="icon-book"></i></span>
					   </a>
					   <a onclick="sendSyncChatRoom('${item.noWx}')" href="javascript:;" data-imei="${item.code}" title="同步群通讯录">
					   		<span><i class="icon-group"></i></span>
					   </a>
					   <c:choose>
					   		<c:when test="${fns:getRedisValue('ZK_FRIENDS_AUTO:'.concat(item.noWx)) ==1}">
					   			<a href="${ctx}/member/shopTerminal/setAutoDownFriends?noWx=${item.noWx}&val=0" onclick="return confirmx('确认要关闭朋友圈图片自动下载功能吗？', this.href)" title="关闭朋友圈图片自动下载">
					   				<span><i class="icon-remove"></i></span>
					   			</a>
					   		</c:when>
					   		<c:otherwise>
					   			<a href="${ctx}/member/shopTerminal/setAutoDownFriends?noWx=${item.noWx}&val=1" onclick="return confirmx('确认要开启朋友圈图片自动下载功能吗？', this.href)" title="开启朋友圈图片自动下载">
					   				<span><i class="icon-ok"></i></span>
					   			</a>
					   		</c:otherwise>
					   </c:choose>
					   <a href="${ctx}/member/shopTerminal/delete?code=${item.code}&noWx=${not empty item.alias?item.alias:item.noWx}" onclick="return confirmx('删除终端之后，所有客户、聊天记录、朋友圈以及相关的标签分组等信息将全部删除，请谨慎操作。确认要删除终端吗？', this.href)" title="删除">
					   		<span><i class="icon-trash"></i></span>
					   </a>
					</shiro:hasPermission>
					<shiro:hasPermission name="member:terminalLogFiles:view">
					   <a href="javascript:;" class="showLogs" data-imei="${item.imei}" data-terminalcode="${item.terminalCode}" title="查看日志">
					   		<span><i class="icon-list-alt"></i></span>
					   </a>
					</shiro:hasPermission>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
<!-- Modal Start -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 350px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					微信支付密码
				</h4>
			</div>
			<form id="inputForm" action="${ctx}/member/shopTerminal/updateWxPwd" method="post" enctype="multipart/form-data" class="">
				<div class="modal-body">
					<input id="mCode"  type="hidden"/>
					<table id="contentTable1" class="table table-striped table-bordered table-condensed table-hover">
						<tbody>
							<div class="control-group">
						        <label class="control-label">微信支付密码:</label>
						        <div class="controls">
						            <input type="password" id="wxPwd" name="wxPwd"  maxlength="6" minlength="6"  class="required valid input-xlarge" />
						            <span class="help-inline"><font color="red">*</font></span>
						        </div>
						        <label class="control-label">确认支付密码:</label>
						        <div class="controls">
						            <input type="password" id="wxPwd2" name="wxPwd2"  maxlength="6" minlength="6"  class="required valid input-xlarge" />
						            <span class="help-inline"><font color="red">*</font></span>
						        </div>
						    </div>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default myModalClose" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary myModalSubmit">提交</button>
				</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- Modal End -->
	
</body>
</html>