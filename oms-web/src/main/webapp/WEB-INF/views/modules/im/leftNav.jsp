<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${merchantNo }" id="merchantNo" />
<input type="hidden" value="${merchantName }" id="merchantName" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<div class="lj-left-nav">
    <div class="h">
        <c:if test="${!empty user_photo}">
      	 	 <%-- <img alt="" src="${fns:getUploadUrl()}${user_photo}" class="i"/> --%>
      	 	 <div class="i" style="background:url(${fns:getUploadUrl()}${user_photo}) no-repeat center; background-size:100% 100%"></div>
       	</c:if>
       	<c:if test="${empty user_photo}">   
             <%-- <img alt="" src="${ctxStatic}/admin/images/user-logo.jpg" class="i"> --%>
             <div class="i" style="background:url(${ctxStatic}/admin/images/user-logo.jpg) no-repeat center; background-size:100% 100%"></div>
       	</c:if>
        <span class="n"><shiro:principal property="name"/></span>
    </div>
    <ul class="nav" id="leftNavType">
        <li class="rp" title="网点客户列表" onclick="indexListHref()"></li>
        <li class="bk" title="运营" onclick="location.href='${ctx}/im/friendList'"></li>
        <li class="mt" title="素材" onclick="location.href='${ctx}/cm/friendsimagemateria/show'"></li>
        <li class="st" title="添加好友" onclick="location.href='${ctx}/im/searchFriend'"></li>
    </ul>
</div>
<script>
$('#leftNavType').find('li.'+$('#LEFTNAVTYPEID').val()).addClass('current');
$('body').on('click','.telescopicImage',function(){
	$('.hideTelescopicButton').toggle();
});

/* $.ajax({
    type:"POST",
    url:$('#ctx').val()+"/member/forecastName/showForecastNameMenu",
    dataType:'JSON',
    success:function(result){
    	if(result.data){
    		var html='<li class="ybm" title="预报名" onclick="location.href=\'${ctx}/member/forecastName/list\'"></li>';
    		$("#leftNavType").append(html);
    		$('#leftNavType').find('li.'+$('#LEFTNAVTYPEID').val()).addClass('current');
    	}
    }
}); */
function indexListHref(){
	window.location.href = localStorage.getItem("indexListHref") || $("#ctx").val()+ "/im/list";
}
</script>