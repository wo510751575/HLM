<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>今日待认领客户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//分配列表
			$('.view_btn').click(function() {
				var noWxGm = $(this).data("nowxgm");
				var code   = $(this).data("code");
				var sourceFlag='todayNList';
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/addFriends/view?noWxGm="+noWxGm+"&code="+code+"&sourceFlag="+sourceFlag ,"导购列表", 520, 400,{//宽高
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
			//刷新
			$("#btnReload").click(function() {
				window.location.reload();
			});
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
			});
			
			//批量分配显示&隐藏
			var noWxGm=$("input[name='noWxGm']").val()||"";
			if(noWxGm){
				$("#btnBatchAllot").css("background-color","#23b7e5");//置蓝
			}else{
				$("#btnBatchAllot").css("background-color","gray");//置灰
			}
			
			//批量分配
			if($("#btnBatchAllot").css("background-color")!='rgb(128, 128, 128)'){//如果不是灰色,开启点击事件
				$("#btnBatchAllot").click(function(){
					//获取勾选的值
					var valArr = new Array;
				    $("#infolist :checkbox[checked]").each(function(i){
						valArr[i] = $(this).val();
				    });
					var vals = valArr.join(',');//转换为逗号隔开的字符串
					if(!vals){
						alertx("请选择客户!");
						return false;
					}
					
					// 正常打开	
					top.$.jBox.open("iframe:${ctx}/member/addFriends/view?noWxGm="+noWxGm+"&vals="+vals ,"导购列表", 520, 400,{//宽高
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
						} 
					}); 
				});
			}
			
			//全选、全不选
		    $("#allcheck").click(function(){
		    	  if(this.checked){   
		    	        $("#infolist :checkbox").prop("checked", true);  
		    	    }else{   
		    			$("#infolist :checkbox").prop("checked", false);
		    	    }  
		    });
		    
		  	//设置全选复选框
		    $("#infolist :checkbox").click(function(){
		    	allchk();
		    });
		  	
		});
		function allchk(){
			var chknum = $("#infolist :checkbox").size();//选项总个数
			var chk = 0;
			$("#infolist :checkbox").each(function () {  
		        if($(this).prop("checked")==true){
					chk++;
				}
		    });
			if(chknum==chk){//全选
				$("#allcheck").prop("checked",true);
			}else{//不全选
				$("#allcheck").prop("checked",false);
			}
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
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
		<li class="active"><a href="${ctx}/member/addFriends/todayNList">今日待认领客户</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/addFriends/todayNList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${findAddFriendsAllotPage.mobile}" class="input-medium" maxlength="15" placeholder="手机号">
			</li>
			<li><label>终端微信号：</label>
				<input type="text" name="noWxGm" value="${findAddFriendsAllotPage.noWxGm}" class="input-medium" maxlength="100" placeholder="终端微信号">
				<span class="help-inline"><font color="red">（筛选终端微信号，可实现批量分配导购）</font></span>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>
			<li class="btns">
				<input id="btnBatchAllot" class="btn btn-primary" type="button" value="批量分配" style="background-color: gray;"/>
			</li>
			<input id="btnReload"  type="hidden" value="刷新"/>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>全选<input type="checkbox" id="allcheck"/></th>
				<th style="min-width: 45px;">头像</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>性别</th>
				<th>所属终端</th>
				<th>添加时间</th>
				<shiro:hasPermission name="member:addFriends:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="addFriends" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox" value="${addFriends.code}"/>
				</td>
				<td>
						<c:if test="${empty addFriends.headAddress}">
							<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
						</c:if>
						<c:if test="${not empty addFriends.headAddress}">
							<c:if test="${fns:startsWith(addFriends.headAddress,'http')}">
								<img class="img-circle" src="${addFriends.headAddress}">
							</c:if>
							<c:if test="${!fns:startsWith(addFriends.headAddress,'http')}">
								<img class="img-circle" src="${fns:getUploadUrl()}${addFriends.headAddress}">
							</c:if>
						</c:if>
				</td>
				<td>
					${empty addFriends.alias?addFriends.noWx:addFriends.alias}
				</td>
				<td>
					${addFriends.nickName}
				</td>
				<td>
					${fn:replace(addFriends.mobile,",","<br>")}
				</td>
				<td>
					<c:if test="${addFriends.sex=='male'}">男</c:if> 
					<c:if test="${addFriends.sex=='female'}">女</c:if> 
					<c:if test="${addFriends.sex=='unknown'}">未知</c:if> 
				</td>
				<td>
					${addFriends.noWxGm}
				</td>
				<td>
					<fmt:formatDate value="${addFriends.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="javascript:;" class="view_btn" data-code="${addFriends.code}" data-nowxgm="${addFriends.noWxGm}" >分配导购</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
	
</body>
</html>