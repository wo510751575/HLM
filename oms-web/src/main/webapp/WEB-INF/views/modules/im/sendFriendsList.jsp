<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>发朋友圈</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="friend" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<input type="hidden" value="${delayTimes }" id="minDelayTimes" />

<div class="lj-main">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/friendsList.jsp"%>
		
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
		<form action="" id="formFriend" method="post">
			<input type="hidden" value="${friendsId }" id="friendsId" name="friendsId"/>
			<input type="hidden" value="${assistantNo }" id="assistantNo"/>
			<input type="hidden" value="" id="noWxs" name="noWxs"/>
	        <div class="edit-material">
	            <div class="tag">
	                <span class="n">任务名称：</span><span style="line-height:40px;">发朋友圈</span>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>网点微信号：</span>
                    <input type="text" class="ipt" id="alias" name="alias" value="" readonly="readonly"/>
                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#lVarWxno').css('display','flex')"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>延迟时间：</span>
                    <input type="text" class="ipt" id="delayTimes" name="delayTimes" value="" style="width:120px;"/>
                    <p class="tip" style="line-height:40px; margin-left:15px;">单位:分钟。执行任务随机按照这个时间范围内延迟处理；最小延迟时间不低于60分钟。</p>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>发送素材：</span>
                    <input type="text" class="ipt" id="materialName" value="" name="materialName" readonly="readonly"/>
                    <!--  IMAGE("1", "图片素材"), VIDEO("2", "视频素材"),LINK("3","链接素材"); -->
                    <input type="hidden" id="materialCategory" name="materialCategory" value="1"/>
                    <input type="hidden" id="materialCode" name="materialCode" value=""/>
                    <input type="button" class="i-btn" value="选择素材" style="margin-top: 5px;" onclick="$('#friendMateriaList').css('display','flex')"/>
	            </div>
	            <div class="tag">
	                <span class="n">执行时间：</span>
                    <input id="executeTime" name="executeTime" type="text" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,minDate:'%y-%M-%d'});" style="width:200px;"/>
	            </div>
	            <div class="button-list absolute">
	                <input type="button" value="保存" class="save g-btn" onclick="sendFriends(this,'#formFriend','1')"/>
	                <input type="button" value="立即执行" class="doNow g-btn" onclick="sendFriends(this,'#formFriend','2')"/>
	                <input type="button" value="取消" class="cancel g-btn" onclick=""/>
	            </div>
	        </div>
	        
		</form>
    </div>
    <%@ include file="/WEB-INF/views/modules/im/varWxno.jsp"%>
    
    <div class="mask-layer" id="friendMateriaList">
	    <div class="sys-variable">
	        <div class="sys-variable-side">
	            <div class="top">
	                <!-- <span class="n">未选(<span id="ySelect">0</span>)</span> -->
	                <span class="n">选择素材</span>
	                <ul class="list" id="friendMateriaButtonList">
	                	<li class="current" data-url="${ctx}/cm/friendsimagemateria/list" data-type="image" data-value="1">图文素材</li>
	                	<li data-url="${ctx}/im/findFriendsLinkMaterialPage" data-type="link" data-value="3">链接素材</li>
	                	<li data-url="${ctx}/im/findFriendsVideoMaterialPage" data-type="video" data-value="2">视频素材</li>
	                </ul>
	                <div class="search">
	                    <input type="text" class="ipt" placeholder="主题/内容" id="conditionStr" value=""/>
	                    <img onclick="friendMateriaList()" src="${ctxStatic}/admin/images/imImages/search.png" class="i" />
	                </div>
	            </div>
	            <div class="data-table" id="FriendMateriaListTable" style="height:405px;"></div>
	            <div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="saveFriendMateria('#friendMateriaList')"/>
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('#friendMateriaList').hide()"/>
		        </div>
	        </div>
	    </div>
	</div>
</div>
<script src="${ctxStatic}/admin/js/ajaxfileupload.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/sendFriendsList.js?v=11" type="text/javascript"></script>
</body>
</html>