<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>历史记录</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" value="${ctx}" id="ctx"> 
	<input type="hidden" value="10" id="limit">
	<input type="hidden" value="0" id="start">
	<input type="hidden" value="${ctxStatic}" id="ctxStatic">
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<input type="hidden" value="${chatroom.noWxZk}" id="guideWxId">
	
	<input type="hidden" value="1" id="pageNo">
	<input type="hidden" value="0" id="count">
	
	<div class="imcontent" id="historyChat">
		<input type="hidden" value="rp" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
		<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<span class="back" onclick="ImIndex()">
					返回
				</span>
			</div>
			<div class="shop_list" id="shop_list">
				<div class="goupTitle">群成员</div>
				<div class="groupHeads">
					<ul>
					</ul>
				</div>
				<div>
					<input class="groupBt" onclick="moreGroupM()" type="button" value="查看更多群成员》">
				</div>
			</div>
		</div>
		<div class="con_nav">
			<div class="con_search">
				<input id="startTime" name="startTime" type="text"  readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="" onclick="WdatePicker({onpicked:cDayFunc(this),dateFmt:'yyyy-MM-dd',isShowClear:true,startDate:'%y',maxDate:'%y-%M-%d'});" />
				<img alt="" src="${ctxStatic}/admin/images/imImages/ce.png" class="dateCe">
				<div class="choseDate">选择日期</div>
				<div class=" historyTitle text-center">
					聊天记录
				</div>
			</div>
			<div class="person">
				
				<label class="sign"></label>
			</div>
			<!-- 分页 -->
			<div class="pagination">
				<span>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1" class="pageShow" type="tel"> 页，每页
					<select>
						<option>10</option>
						<option>20</option>
						<option>30</option>
						<option>50</option>
					</select>
				条</span>
				<img alt="" onclick="changePage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
				<img alt="" onclick="changePage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
				<img alt="" onclick="changePage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
				<img alt="" onclick="changePage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">		
			</div>
		</div>
		<div class="yulan">
			<div class="top_yulan text-center" >
				<!-- <div id="yulan"></div> -->
				<iframe id="yulan" src=""></iframe>
			</div>
		</div>
		
	</div>
	<div class="loadFlag" id="loadFlag" onclick="$('#loadFlag').hide();window.clearInterval(intCycle);">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div>
	<div class="bigImg" id="bigImg" onclick="$('#bigImg').hide()">
		<img alt="" src="" style="height:67% !important">
		<img alt="" src="" class="hideImg"  style="height:67% !important;display:none;">
	</div>
	<script src="${ctxStatic}/admin/js/im/chatroomHistory.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=6.exp"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script src="${ctxStatic}/admin/js/im/exif.js" type="text/javascript"></script>
</body>
</html>