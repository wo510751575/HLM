	<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>添加好友</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="st" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="addFList" id="SEARCHFRIEND"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<input type="hidden" id="pageNo" value="" />
<input type="hidden" id="pageSize" value="" />
<input type="hidden" id="count" value="" />

<div class="lj-main searchFriend">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/addFriend.jsp"%>
	
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
	    <div class="top">
	        <span class="n">申请添加好友记录</span>
	    </div>
	    <div class="frdContent">
	    	<table>
	    	</table>
	    </div>
	    <div class="pagination">
			<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1" class="pageShow" type="tel"> 页，每页
				<select>
					<option selected="selected">10</option>
					<option>20</option>
					<option>30</option>
					<option>50</option>
				</select>
			条</p>
			<img alt="" onclick="applayFriendList('first')" class="first" src="/oms-web/static/admin/images/imImages/arrow_back1.png">
			<img alt="" onclick="applayFriendList('prev')" class="prev" src="/oms-web/static/admin/images/imImages/arrow_double_b1.png">
			<img alt="" onclick="applayFriendList('next')" class="next" src="/oms-web/static/admin/images/imImages/arrow_doublef.png">
			<img alt="" onclick="applayFriendList('last')" class="last" src="/oms-web/static/admin/images/imImages/arrow_font.png">	
		</div>
	</div>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/applayFriends.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
</body>
</html>