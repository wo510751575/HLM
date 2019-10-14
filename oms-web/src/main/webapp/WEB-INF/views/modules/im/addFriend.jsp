<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="lj-center hideTelescopicButton">
    <div class="top">
        <img src="${ctxStatic}/admin/images/imImages/addf_icon.png" class="i"/>
        <span class="n">添加好友</span>
        <img src="${ctxStatic}/admin/images/imImages/down-icon.png" class="down" />
    </div>
    <ul class="list" id="addFriendsType">
        <li class="searchF" onclick="location.href='${ctx}/im/searchFriend'">
            <img src="${ctxStatic}/admin/images/imImages/searchf_icon.png" class="i"/>
            <span class="n">搜号码加好友</span>
            <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up"/>
        </li>
        <li class="addFList" onclick="location.href='${ctx}/im/applayFriend'">
            <img src="${ctxStatic}/admin/images/imImages/applay_icon.png" class="i"/>
            <span class="n">申请添加好友记录</span>
            <img src="${ctxStatic}/admin/images/imImages/right-icon.png" class="up"/>
        </li>
    </ul>
</div>
<script>	
$('#addFriendsType').find('li.'+$('#SEARCHFRIEND').val()).addClass('current');
</script>