<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>创建群聊</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="group" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<input type="hidden" value="${delayTimes }" id="minDelayTimes" />

<div class="lj-main">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/friendsList.jsp"%>
		
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
		<form action="" id="formFriend" method="post">
			<input type="hidden" value="" id="isAppendStatus"/>
			<input type="hidden" value="" id="noWxs" name="noWxs"/>
	        <div class="edit-material">
	            <div class="tag">
	                <span class="n"><i>*</i>微信群昵称：</span>
                    <input type="text" class="ipt" id="roomNickName" name="roomNickName" value=""/>
	            </div>
	           <div class="tag">
	                <span class="n"><i>*</i>网点微信号：</span>
                    <input type="text" class="ipt" id="alias" name="alias" value="" readonly="readonly"/>
                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#lVarWxno').css('display','flex')"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>选择创建对象：</span>
                    <div>
                    	<input type="hidden" id="noWxMapping" value="" />
	                    <textarea class="ipt" readonly="readonly" id="memberNos" name="memberNos" style="height:80px;"></textarea>
	                    <div class="sltNum">已选客户（<span id="sltNum">0</span>人）最多选择39名客户</div>
                    </div>
                    <div>
	                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="memberNosList();"/><br>	
                    </div>
	            </div>
	            <div class="button-list absolute">
	                <!-- <input type="button" value="保存" class="save g-btn" onclick="sendFriends(this,'#formFriend','1')"/> -->
	                <input type="button" value="立即创建" class="doNow g-btn" onclick="sendFriends(this,'#formFriend','2')"/>
	                <input type="button" value="取消" class="cancel g-btn" onclick=""/>
	            </div>
	        </div>
	        
		</form>
    </div>
    <%@ include file="/WEB-INF/views/modules/im/varWxno.jsp"%>
    <%@ include file="/WEB-INF/views/modules/im/memberListByNoWxGm.jsp"%>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/creatGroup.js?v=11" type="text/javascript"></script>
</body>
</html>