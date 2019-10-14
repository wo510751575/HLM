<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="zhegai" id="couponRuleCodeId">
	<!-- 优惠券 -->
	<div class="huodong  huodong3" style="display:block;">
		<div class="hd_title text-center">优惠券</div>
		<div class="hd_con">
			<div class="hd_list"></div>
			<div class="hd_detail">
				<div class="hd_detail_th text-center">优惠券详情</div>
				<iframe class="hd_detail_tbd"  id='couponIframeId' src=""></iframe>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="confirmCoupon('#couponRuleCodeId')">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('#couponRuleCodeId').hide()">
		        </div>
			</div>
		</div>
	</div>
</div>