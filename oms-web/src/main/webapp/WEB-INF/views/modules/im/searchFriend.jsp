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
<input type="hidden" value="searchF" id="SEARCHFRIEND"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl">
<input type="hidden" value="${assistantNo }" id="assistantNo"/>

<!-- <div class="lj-main searchFriend"> -->
<div class="lj-main">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/addFriend.jsp"%>
	
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
	    <div class="top">
	        <span class="n">添加好友</span>
	    </div>
	    <div class="frdContent">
	    	<div class="tip">
	    		<img alt="" src="${ctxStatic}/admin/images/imImages/tip_icon.png">
	    		<span>请先选择微信号再搜索微信账号</span>
	    	</div>
	    	<form action="" id="formFriendSearch" method="post">
	    	<div class="tag">
                <span class="n"><i>*</i>请选择微信：</span>
                 <input type="hidden" id="noWxs" name="noWxs"/>
                 <input type="text" class="ipt" id="alias" name="alias" readonly="readonly"/>
                 <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#lVarWxno').css('display','flex')"/>
            </div>
           <div class="tag">
              <input type="text" class="ipt seachInfo" placeholder="请输入客户的手机号/微信号/QQ号" id="seachInfo" name="seachInfo">
              <input type="button" class="i-btn" value="搜索" style="margin-top: 5px;" onclick="searchF('','#formFriendSearch')">
           </div>
           <div class="tag">
              <input type="text" class="ipt seachInfo" placeholder="请输入验证信息" id="validation" >
           </div>
           <div class="tag">
              <input type="text" class="ipt seachInfo" placeholder="请输入备注" id="nickRemark" >
           </div>
           <div class="tag">
              <input type="text" class="ipt seachInfo" placeholder="请输入手机" id="mobile" >
           </div>
           <!-- <div class="tag">
              <input type="text" class="ipt seachInfo" placeholder="请输入标签多个以英文,分隔" id="labelName" >
           </div> -->
           </form>
           <div class="result">
           		<p>搜索结果<span style="font-size: 12px;">(因网络不稳定会导致首次搜索结果为空，可再次点击搜索)</span></p>
           		<p style="color: red;">主动添加好友，微信端如超过10次，对方可能会收不到</p>
           		<div class="reInfo">
           		
           		</div>
           </div>
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/modules/im/varWxno.jsp"%>
    
</div>
<div class="loadFlag" id="loadFlag">
	<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
 <script src="${ctxStatic}/admin/js/im/addFriends.js?v=11" type="text/javascript"></script>
</body>
</html>