<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成单记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//客户详情
			$('.view_btn').click(function() {
				var id = $(this).attr("data-id");
				var type=$(this).attr("data-type");
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/view?code="+id+"&pmTypeType="+type,"客户详情", 917, 800, {//宽高
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
			$("#searchForm").submit();
        	return false;
        }
		function CancelQuery(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
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
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/successList/">成单客户</a></li>
		<li class="active"><a href="${ctx}/member/successView/">成单记录</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/successView/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户姓名：</label>
		    	<input type="text" name="memberName" value="${findSuccessView.memberName}" class="input-medium" maxlength="20" placeholder="客户姓名">
			</li>
			<li><label>所属导购：</label>
		    	<input type="text" name="memberNameGm" value="${findSuccessView.memberNameGm}" class="input-medium" maxlength="20" placeholder="所属导购">
			</li>
			<li>
				<label>时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findSuccessView.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findSuccessView.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
				
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="min-width: 45px;">头像</th>
				<th>客户姓名</th>
				<th>微信昵称</th>
				<th>所属导购</th>
				<th>所属终端</th>
				<th>手机号</th>
				<th style="width: 150px;">时间</th>
				<th style="width: 150px;">成单金额</th>
				<th>跟进次数</th>
				<th style="width: 150px;">送货时间</th>
				<th style="width: 200px;">购买商品</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="Clientlist" varStatus="number"> 
				<tr>
					<td>
						
						<a  href="javascript:;" class="view_btn" data-id="${Clientlist.code}" data-type="${Clientlist.pmTypeType}">
							<c:if test="${empty Clientlist.headAddress}">
								<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
							</c:if>
							<c:if test="${not empty Clientlist.headAddress}">
								<c:if test="${fns:startsWith(Clientlist.headAddress,'http')}">
									<img class="img-circle" src="${Clientlist.headAddress}">
								</c:if>
								<c:if test="${!fns:startsWith(Clientlist.headAddress,'http')}">
									<img class="img-circle" src="${fns:getUploadUrl()}${Clientlist.headAddress}">
								</c:if>
							</c:if> 
						</a>
						
					</td>
					
				    <td>
						<a  href="javascript:;" class="view_btn" data-id="${Clientlist.code}" data-type="${Clientlist.pmTypeType}">${Clientlist.memberName}</a>
					</td>
					<td>
						${Clientlist.nickNameWx}
					</td>
					<td>
						${Clientlist.memberNameGm}
					</td>
					<td>
						${Clientlist.shopName}
					</td>
					<td>
						${Clientlist.mobile}
					</td>
					<td>
						<fmt:formatDate value="${Clientlist.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatNumber value="${Clientlist.orderAmount/100}" pattern="#,##0.00"/>元
					</td>
					<td>
						${Clientlist.followNum}
					</td>
					<td>
						<fmt:formatDate value="${Clientlist.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
					${Clientlist.remark2}
					</td>
					<td>
						${Clientlist.remark}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</body>
</html>