<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>朋友圈</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function() {
		var jBoxConfig = {};
		jBoxConfig.defaults = {
			width: 380,
		    height: 'auto',
		}
		$.jBox.setDefaults(jBoxConfig);
		
		var importMsg=$("#importMsg").val();
		if(importMsg){
			$.jBox.success(importMsg, '提示');
			$("#importMsg").val("");
		}
		initView();
	});
	
	function initView(){
    	$('.form-group-select-css').select2();
    }
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/weixin/friendsInfo/countList");
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
	
	 function exportExcel(){
			 
		 
		var startTime =$("#beginDate").val();	
		var endTime =$("#endDate").val();	
		var noWxShop =$("#noWxShop").val();	
		window.location.href="${ctx}/weixin/friendsInfo/exportExcel?noWxShop="+noWxShop+"&startTime="+startTime+"&endTime="+endTime;
			 
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
.img-small {
	width: 30px;
	height: 30px;
}
.tree-set{
	width: 117px;
}
	</style>
</head>
<body>
<div id="section" style="margin-left: 0px;">
		<div class="container">
			<form id="searchForm" action="${ctx}/weixin/friendsInfo/countList" method="post"
				class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden"value="${page.pageNo}" /> 
				<input id="pageSize" name="pageSize"type="hidden" value="${page.pageSize}" />
			
					<ul class="ul-form">

			       <li><label>微信号：</label>
				<input type="text" name="noWxShop" id="noWxShop"
						class="input-medium" maxlength="100" 
						value="${param.noWxShop}">
			       </li>
			       <li><label>描述：</label>
				<input type="text" name="content" id="content" 
						class="input-medium" maxlength="100" 
						value="${param.content}">
			       </li>
					<li><label>时间：</label> 
						<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
						value="${param.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
						-- 
						<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="${param.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
					</li>
										<li><label></label><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick="return page();"/></li>
															<li><label></label><input  class="btn btn-primary" type="button" onclick="exportExcel()" value="下载列表"  /></li>
					
					</ul>
					
			
			</form>

			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>微信号</th>
						<th>描述</th>
						<th>发送日期</th>
						<th>评论数</th>
						<th>点赞数</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${page.list}" var="item" varStatus="number">
						<tr>
						<td>${item.noWxShop}</td>
				<!--  类型1图文 2纯文字 3分享 4歌曲分享 15视频-->
						<td>
						<c:if test="${fn:length(item.content)>'105'}">
								${fn:substring(item.content,0,105)}...
						</c:if>
						<c:if test="${fn:length(item.content)<='105'}">
							${item.content}
						</c:if>


					
						</td>
					<!--	<td>
						<c:if test="${item.type ==1}"><img alt="" src="${item.imgAddr}"/></c:if>
						<c:if test="${item.type ==3}"><img alt="" src="${item.imgAddr}"/></c:if>
						<c:if test="${item.type ==4}"><img alt="" src="${item.imgAddr}"/></c:if>
						<c:if test="${item.type ==15}"><video controls="controls" style="width:200px;margin-left:10px;height:100px" 
						src="${item.imgAddr}" 
						class="i"></video>
						</c:if>
						</td>   -->
						<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
						<td>${item.commentCount}</td>
						<td>${item.likeCount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>


		</div>
	</div>

</body>
</html>