<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="f_center hideTelescopicButton" id="materialListType">
 	<div class="lj-center_info" >
    	<div class="x comment" data-url="/im/friendList">
	        <img src="${ctxStatic}/admin/images/imImages/comment-icon.png" class="i"/>
	        <div class="n">
	        	<p class="t">朋友圈评论</p>
	        	<p class="d">查看且评论朋友圈，增强和好友的互动。</p>
	        </div>
	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
        </div>
    </div>
  	<div class="lj-center_info" >
    	<div class="x friend" data-url="/friendsjob/sendFriends">
	        <img src="${ctxStatic}/admin/images/imImages/people-icon.png" class="i"/>
	        <div class="n">
	        	<p class="t">发朋友圈</p>
	        	<p class="d">可以发送朋友圈图文和朋友圈链接,需先建立朋友圈素材</p>
	        </div>
	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
        </div>
    </div>

    <shiro:hasPermission name="im:couponMultiPush:view">
    <div class="lj-center_info" >
    	<div class="x coupon" data-url="/couponmultipush">
	        <img src="${ctxStatic}/admin/images/imImages/coupon-icon.png" class="i"/>
	        <div class="n">
	        	<p class="t">群发优惠券</p>
	        	<p class="d">可以推送优惠券给多人，需先在后台设置优惠券</p>
	        </div>
	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
        </div>
    </div>
    </shiro:hasPermission>
    <shiro:hasPermission name="im:redPackets:view">
    <div class="lj-center_info" >
    	<div class="x red" data-url="/im/redPackets/show">
	        <img src="${ctxStatic}/admin/images/imImages/red-icon.png" class="i"/>
	        <div class="n">
	        	<p class="t">批量发红包</p>
	        	<p class="d">可以批量推送红包给多人</p>
	        </div>
	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
        </div>
    </div>
    </shiro:hasPermission>

	<div class="lj-center_info" >
    	<div class="x group" data-url="/im/chatroom/list">
	        <img src="${ctxStatic}/admin/images/imImages/comment-icon.png" class="i"/>
	        <div class="n">
	        	<p class="t">创建群聊</p>
	        	<p class="d">可以创建多人群聊进行交流</p>
	        </div>
	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
        </div>
    </div>

    <div class="lj-center_info" >
      	<div class="x massTexting" data-url="/im/groupChatInfo/form">
  	        <img src="${ctxStatic}/admin/images/imImages/comment-icon.png" class="i"/>
  	        <div class="n">
  	        	<p class="t">新建群发</p>
  	        	<p class="d">可以同时给多人发送消息</p>
  	        </div>
  	        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
          </div>
      </div>
</div>
<script>
$(document).ready(function() {
	$(" .f_center .x").click(function(){
		window.location.href=$("#ctx").val()+$(this).attr('data-url');
		$("#materialListType").removeClass("active");
		$(this).addClass("active");
	});
})

$('#materialListType').find('.lj-center_info .'+$('#MATERIALLISTTYPEID').val()).addClass('active');
</script>
