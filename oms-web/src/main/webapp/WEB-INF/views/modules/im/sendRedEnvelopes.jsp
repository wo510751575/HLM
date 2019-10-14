<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>发红包</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="red" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<input type="hidden" value="${delayTimes }" id="minDelayTimes" />
<div class="lj-main redpackage">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/friendsList.jsp"%>
		
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
		<div id="redRecord" onclick="RecordR()">红包记录</div>
		<form action="" id="formFriend" method="post">
			<input type="hidden" value="${friendsId }" id="friendsId" name="friendsId"/>
			<input type="hidden" value="${assistantNo }" id="assistantNo"/>
			<input type="hidden" value="" id="noWxs" name="noWxs"/>
			<input type="hidden" value="" id="isAppendStatus"/>
	        <div class="edit-material">
	            <div class="tag">
	                <span class="n"><i>*</i>网点微信号：</span>
                    <input type="text" class="ipt" id="alias" name="alias" value="" readonly="readonly"/>
                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#wxNosListId').css('display','flex')"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>推送对象：</span>
                    <!-- <input type="text" class="ipt" id="memberNos" name="memberNos" value="" readonly="readonly"/> -->
                    <div>
                    	<input type="hidden" id="noWxMapping" value="" />
	                    <textarea class="ipt" id="memberNos" readonly="readonly" name="memberNos" style="height:80px;"></textarea>
                    </div>
                    <div>
	                    <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="memberNosList();"/><br>	
	                    <input type="button" class="i-btn" value="追加选择" style="margin-top: 5px;" onclick="memberNosList('Y');"/>
                    </div>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>单个红包金额：</span>
                    <input type="number" class="ipt" onblur="changeMoney(this)" id="redpackAmount" name="redpackAmount" value="" style="width:120px;"/>
	            </div>
	             <div class="sltNum">已选客户（<span id="sltNum">0</span>人）,红包总金额为（<span id="meneyAll">0</span>）元</div>
	            <div class="tag">
	                <span class="n">红包备注：</span>
                    <textarea class="ipt" id="remarkRed" name="remarkRed" style="height:80px;">恭喜发财，大吉大利</textarea>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>延迟时间：</span>
                    <input type="text" class="ipt" id="delayTimes" name="delayTimes" value="" style="width:120px;"/>
                    <p class="tip" style="line-height:40px; margin-left:15px;">单位:分钟。执行任务随机按照这个时间范围内延迟处理；最小延迟时间不低于${delayTimes }分钟。</p>
	            </div>
	            <div class="tag">
	                <span class="n">执行时间：</span>
                    <input id="executeTime" name="executeTime" type="text" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,minDate:'%y-%M-%d'});" style="width:200px;"/>
	            </div>
	            <div class="button-list absolute">
	                <input type="button" value="保存" class="save g-btn" onclick="sendFriends('#formFriend','1')"/>
	                <input type="button" value="立即执行" class="doNow g-btn" onclick="sendFriends('#formFriend','2')"/>
	                <input type="button" value="取消" class="cancel g-btn" onclick=""/>
	            </div>
	        </div>
	        
		</form>
    </div>
    <%@ include file="/WEB-INF/views/modules/im/varWxno.jsp"%>
    <%@ include file="/WEB-INF/views/modules/im/selectCoupon.jsp"%>
    <%@ include file="/WEB-INF/views/modules/im/memberListByNoWxGm.jsp"%>
	
	<div class="mask-layer" id="wxNosListId">
	    <div class="sys-variable">
	        <div class="sys-variable-side">
	            <div class="top">
	            	<div class="wxTitle">选择网点微信号</div>
	                <div class="search" id="wxSearch">	               
	                   	<input type="text" class="ipt" placeholder="终端号/网点微信号" id="wxkeyWord" value="" />
	                    <input type="text" class="searchTh" name="" placeholder="微信昵称/客户姓名/手机号" style="display:none">
	                    <!-- <div class="ipt" contenteditable="true"></div> -->
	                    <img onclick="searchWxNoDataList('#wxSearch','#wxNosListTable')" src="${ctxStatic}/admin/images/imImages/search.png" class="i" />
	                </div>
	            </div>
	            <div class="selectAllCoupon">
					<ul>
						<li>
							<input type="checkbox" id="selectAllwx" class="chk" onchange="selectwxAll(this)"/>
							<label for="selectAllwx">全选</label>
						</li>
						<li>网点微信号</li>
						<li>网点微信昵称</li>
						<li>设备号</li>
						<li>资产编号</li>
						<li>余额<input type="button" value="刷新" class="refresh" onclick="refreshAmount()"></li>
					</ul>
				</div>
	            <div id="wxNosListTable"  style="height:369px;overflow: scroll;"></div>
	            <div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="savewxNos('#wxNosListId')"/>
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('#wxNosListId').hide()"/>
		        </div>
	        </div>
	    </div>
	</div>
	
	<div class="confirm" id="confirm">
		<div class="confirmMoney">
			<div class="confirmTitle">
				红包支付
			</div>
			<div class="confirmContent">
				<p>支付金额：<span id="confirmM"></span> 元 </p>
				<P class="gray">本次发红包涉及网点微信号为<span id="wxNum"></span>个，请输入登录密码：</P>
				<P><input type="password" id="confirmPwd" /></P>
			</div>
			<div class="button-list">
	            <input type="button" value="确定" data-num="" id="cnfirmPay" class=" g-btn" onclick="payRed(this)"/>
	            <input type="button" value="取消" class=" b-btn" onclick="$('#confirm').hide();$('#confirmPwd').val('');"/>
	        </div>
		</div>
	</div>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/sendRedEnvelopes.js?v=11" type="text/javascript"></script>

</body>
</html>