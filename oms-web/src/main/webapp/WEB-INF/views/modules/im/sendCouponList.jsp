<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>发优惠券</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="coupon" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
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
			<input type="hidden" value="" id="isAppendStatus"/>
			<input type="hidden" value="" id="noWxs" name="noWxs"/>
	        <div class="edit-material">
	            <div class="tag">
	                <span class="n"><i>*</i>网点微信号：</span>
                    <input type="text" class="ipt" id="alias" name="alias" value="" readonly="readonly"/>
                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#lVarWxno').css('display','flex')"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>优惠券：</span>
	                <input type="text" class="ipt" id="couponRuleCode" name="couponRuleCode" value="" readonly="readonly"/>
                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="couponList()"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>选择推送对象：</span>
                    <!-- <input type="text" class="ipt" id="memberNos" name="memberNos" value="" readonly="readonly"/> -->
                    <div>
                    	<input type="hidden" id="noWxMapping" value="" />
	                    <textarea class="ipt" readonly="readonly" id="memberNos" name="memberNos" style="height:80px;"></textarea>
	                    <div class="sltNum">已选客户（<span id="sltNum">0</span>人）</div>
                    </div>
                    <div>
	                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="memberNosList();"/><br>	
	                    <input type="button" class="i-btn" value="追加选择" style="margin-top: 5px;" onclick="memberNosList('Y');"/>
                    </div>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>延迟时间：</span>
                    <input type="text" class="ipt" id="delayTimes" name="delayTimes" value="" style="width:120px;"/>
                    <p class="tip" style="line-height:40px; margin-left:15px;">单位:分钟。执行任务随机按照这个时间范围内延迟处理；最小延迟时间不低于60分钟。</p>
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
    <%@ include file="/WEB-INF/views/modules/im/selectCoupon.jsp"%>
    <%@ include file="/WEB-INF/views/modules/im/memberListByNoWxGm.jsp"%>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/sendCouponList.js?v=11" type="text/javascript"></script>
</body>
</html>