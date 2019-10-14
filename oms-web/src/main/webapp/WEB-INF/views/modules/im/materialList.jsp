<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="lj-center hideTelescopicButton" id="SUCAI">
    <div class="top" onclick="go(1)">
        <img src="${ctxStatic}/admin/images/imImages/people-icon.png" class="i"/>
        <span class="n">朋友圈素材</span>
        <img src="${ctxStatic}/admin/images/imImages/down-icon.png" class="down" />
    </div>
    <ul class="list" id="materialListType" style="display: none">
        <li class="image" onclick="location.href='${ctx}/cm/friendsimagemateria/show'">
            <img src="${ctxStatic}/admin/images/imImages/image-icon.png" class="i"/>
            <span class="n">图片素材</span>
            <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up"/>
        </li>
        <li class="link" onclick="location.href='${ctx}/im/findFriendsLinkMaterialList'">
            <img src="${ctxStatic}/admin/images/imImages/link-icon.png" class="i"/>
            <span class="n">链接素材</span>
            <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up"/>
        </li>
         <li class="video" onclick="location.href='${ctx}/im/findFriendsVideoMaterialList'">
            <img src="${ctxStatic}/admin/images/imImages/video-icon.png" class="i"/>
            <span class="n">视频素材</span>
            <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up"/>
        </li>
    </ul>
    <div class="top publicACC" onclick="go(2)">
        <img src="${ctxStatic}/admin/images/imImages/gzh.png" class="i"/>
        <span class="n">公众号</span>
        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
    </div>
     <div class="top xcxList" onclick="go(3)">
        <img src="${ctxStatic}/admin/images/imImages/xcx.png" class="i"/>
        <span class="n">小程序</span>
        <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up" />
    </div>
</div>
<script>
$('#materialListType').find('li.'+$('#MATERIALLISTTYPEID').val()).addClass('current');
$('#SUCAI').find('.'+$('#MATERIALLISTTYPEID').val()).addClass('current');
if($('#materialListType').find('li.'+$('#MATERIALLISTTYPEID').val()).length==0){
	$("#materialListType").css("display","none");
}else{
	$("#materialListType").css("display","block");
}
function go(num){
	if(num == 1){	//朋友圈素材
		location.href=$("#ctx").val()+'/cm/friendsimagemateria/show';
		var show=$("#materialListType").css("display");
		if(show == 'none'){
			$("#materialListType").css("display","block");
		}else{
			$("#materialListType").css("display","none");
		}
	}else if(num == 2){	//公众号
		location.href=$("#ctx").val()+'/im/publicaccount/publicAccountList';
		$("#materialListType").css("display","none");
	}else if(num == 3){	//小程序
		location.href=$("#ctx").val()+'/im/smallprogram/xcxList';
		$("#materialListType").css("display","none");
	}
}
</script>