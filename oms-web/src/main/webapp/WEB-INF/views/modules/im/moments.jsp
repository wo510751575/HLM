<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>朋友圈</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//详情
			$('.view_btn').click(function() {
				var code = $(this).data("code");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/friendsjob/momentsView?jobCode="+code,"朋友圈详情", 600, 430,{//宽高
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/friendsjob/moments");
			$("#searchForm").submit();
        	return false;
        }
	
	</script>
		<style type="text/css">
	.img-small {
		position: relative;
		z-index:10;
		width: 30px;
		height: 30px;
		margin-left:5px;
	}
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
	
	tbody tr td div{
		font-size:12px;
		color:#b7b7b7
		
	}
	.lafen-group {
    	position: relative;
    	margin-left:5px;
	}
	.lafen-group:nth-child(1){
		margin-left:0;
	}
	.flesX{
		display:flex;
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
	    z-index:100;
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
		<li class="active"><a href="${ctx}/friendsjob/moments">朋友圈</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/friendsjob/moments" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>素材分类：</label>
				 <select name="materialCategory"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${MaterialType}" var="item">
					 <option value="${item.type}"  <c:if test="${findSendFriendsJobPage.materialCategory eq item.type}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>		
				 </select>
	        </li>
			<li><label>朋友圈素材类型：</label>
				 <select name="friendsMaterialType"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${FriendsMaterialType}" var="item">
					 <option value="${item.type}"  <c:if test="${findSendFriendsJobPage.friendsMaterialType eq item.type}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>		
				 </select>
	        </li>
			<li><label>任务状态：</label>
				 <select name="jobState"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${SendFriendsJobStatus}" var="item">
					 <option value="${item.code}"  <c:if test="${findSendFriendsJobPage.jobState eq item.code}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>		
				 </select>
	        </li>
			<li><label>创建时间：</label>
				<input id="createDateBegin" name="createDateBegin" type="text" readonly="readonly" maxlength="20"  class="input-medium Wdate" 
 				value="<fmt:formatDate value="${findSendFriendsJobPage.createDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>&nbsp;
				-&nbsp; <input id="createDateEnd" name="createDateEnd" type="text" readonly="readonly" maxlength="20"  class="input-medium Wdate" 
 				value="<fmt:formatDate value="${findSendFriendsJobPage.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul> 
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>素材分类</th>
				<th>朋友圈素材类型</th>
				<th>内容</th>
				<th>图片</th>
				<th>链接</th>
				<th>创建时间</th>
				<th>执行时间</th>
				<th>任务状态</th>
				<th>发送微信</th>
				<th>已发送微信</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					<c:forEach items="${MaterialType}" var="mt">
						<c:if test="${item.materialCategory eq mt.type}">${mt.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${FriendsMaterialType}" var="fmt">
						<c:if test="${item.friendsMaterialType eq fmt.type}">${fmt.name}</c:if>
					</c:forEach>
				</td>
				<td><span title="${item.content}" style="cursor: pointer;">${fns:abbr(item.content,50)}</span></td>
				<td class="flesX">
					<c:if test="${not empty item.imgAddr}">
						<c:set var="imgAddr" value="${fn:split(item.imgAddr, ',')}" />
						<c:forEach items="${imgAddr}" var="img">
							<div class="lafen-group">
						    	<img class="img-small" src="${fns:getImUploadUrl()}${img}" alt="">
						    	<div class="img-big">
						    		<img  src="${fns:getImUploadUrl()}${img}" alt="">
						    	</div>
						    </div>
						</c:forEach>
					</c:if>
				</td>
				<td>
					<c:if test="${not empty item.linkUrl}">
						<a href="${item.linkUrl}" target="_black">查看链接</a>
					</c:if>
				</td>
				<td style="text-align: center;"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align: center;"><fmt:formatDate value="${item.executeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:forEach items="${SendFriendsJobStatus}" var="sfjs">
						<c:if test="${item.jobState eq sfjs.code}">${sfjs.name}</c:if>
					</c:forEach>
				</td>
				<td>${item.noWxs}</td>
				<td>${item.sentNoWxs}</td>
				<td>
					<shiro:hasPermission name="im:sendFriendsjob:view">
						<a href="javascript:;" class="view_btn" data-code="${item.code}">详情</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
</body>
</html>