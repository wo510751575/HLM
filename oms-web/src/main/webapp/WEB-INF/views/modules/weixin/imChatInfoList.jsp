<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>IM聊天记录</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").attr("action","${ctx}/weixin/imChatInfo");
	$("#searchForm").submit();
	return false;
}
</script>
<style type="text/css">
.img-circle {
	width: 40px;
	height: 40px;
	border-radius: 20px;
	vertical-align: middle;
}
.modal.fade.in {
    top: 5%;
}
::-webkit-scrollbar {
    width: 4px;
    height: 4px;
}
.top1{
text-align: center;
    background: black;
    padding: 10px;
    border-radius: 5px;
    opacity: 0.2;
    color: white;
}
#chatInfo{
	position: fixed;
    top: 0px;
    width: 500px;
    height:100%;
    background: url("${ctxStatic}/images/iphone2.png") no-repeat;
    background-size:100% 100%;
    left:25%;
     z-index: 100;
    
}
#chatInfo #chatDetail{
	width: 337px;
    height: 60%;
    position: relative;
    z-index: 200;
    margin-left: 82px;
    top: 18%;
   overflow: auto;
    overflow-x: hidden;
    background-color: rgb(236, 234, 234);
}
#chatDetail ul{
width: 97%;
height: auto;
list-style: none;
margin: 2%;
overflow: hidden;
}
#chatDetail ul li{
width: 93%;
margin-bottom: 10px;
overflow:hidden;
}
.mem_head{
width: 40px;
height: 40px;
}
.mem_info{
width: 77%;
margin:0 2%;
}
.left{
float: left;
}
.right{
float: right;
}
.mem_name_right{
text-align: right;
}
.send_info,.send_info_right{
padding: 9px;
background:#a7da58;
border-radius: 5px;
display: inline-block;
position: relative;
color: black;
word-break: break-all;
}
.send_info{
background: #fff;
}
.send_info:after {
    content: '\25E3';
    position: absolute;
    display: inline-block;
    color: #fff;
    top: 7px;
    left: -2px;
    width: 10px;
    height: 10px;
    font-size: 19px;
    line-height: 20px;
    -webkit-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    -o-transform: rotate(45deg);
}
.send_info_right:after {
    content: '\25E3';
    position: absolute;
    display: inline-block;
    color: #a7da58;
    top: 13px;
    right: 0px;
    width: 10px;
    height: 10px;
    font-size: 19px;
    line-height: 20px;
    -webkit-transform: rotate(225deg);
    -moz-transform: rotate(225deg);
    -ms-transform: rotate(225deg);
    -o-transform: rotate(225deg);
}
.audio-box {
    padding-left: 20px;
    margin-left: 10px;
    display: inline-block;
    width: 60%;
    height: 40px;
    line-height: 42px;
    border-radius: 5px;
    border: 1px solid #eeeeee;
    background: #fff;
    position: relative;
}
.audio-box:before {
    content: '';
    position: absolute;
    left: -9px;
    top: 50%;
    margin-top: -7.5px;
    width: 15px;
    height: 15px;
    border: 1px solid #eee;
    transform: rotate(45deg);
    background: #fff;
    border-color: transparent transparent #eee #eee;
}
.audio-box_right {
    padding-left: 20px;
    display: inline-block;
    width: 60%;
    height: 40px;
    line-height: 42px;
    border-radius: 5px;
    border: 1px solid #a7da58;
    background: #a7da58;
    position: relative;
    float: right;
}
.audio-box_right:before {
    content: '';
    position: absolute;
    right: -3px;
    top: 50%;
    margin-top: -7.5px;
    width: 7px;
    height: 7px;
    border: 1px solid #eee;
    transform: rotate(225deg);
    background: #a7da58;
    border-color: transparent transparent #a7da58 #a7da58;
}
.icon-audio {
    margin-left: 20px;
    display: inline-block;
    width: 13px;
    height: 23px;
    background: url(${ctxStatic}/images/audio_icon01.png) no-repeat;
    background-size: 300% 100%;
    vertical-align: middle;
}
.icon-audio_right {
    margin-right: 10px;
    display: inline-block;
    width: 13px;
    height: 23px;
    background: url(${ctxStatic}/images/audio_icon02.png) no-repeat;
    vertical-align: middle;
}
.ml10 {
    margin-left: 7px;
}
.ml10_right{
 margin-right: 7px;
 float: right;
 margin-top: 12px;
}
.video-ifram {
    width: 80%;
    height: 95px;
    background: url(${ctxStatic}/images/player_icon.png) no-repeat center center;
    background-color: #222222;
}
#loading{
width:36px;
height:36px;
margin:10px auto;
border-radius:50%;
overflow:hidden;
background: url(${ctxStatic}/images/loading.gif) no-repeat  center center;
display: none;
}
#chatInfobox{
    position: fixed;
    z-index: 12;
    width: 100%;
    height: 100%;
    top: 0;
    display: none;
}
.title_name{
position: absolute;
    top: 14.5%;
    color: white;
    font-size: 15px;
    text-align: center;
    	width: 100%;
}
.nav_icon{
width:100%;
border-top:1px solid #ccc;
margin: 0px;
font-size: 11px;
}
.local span{
display: inline-block;
margin-top: 0px;
}
.local span:nth-child(1){
width: 25%;
}
.local span:nth-child(1) img{
margin-top: -10px;
}
.local span:nth-child(2){
width: 75%;
}
</style>
</head>
<body>
	<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/imChatInfo/?talker=${parmMap['talker']}&memberNoGm=${parmMap['memberNoGm']}">IM聊天记录</a></li>
<%-- 		<li><a href="${ctx}/weixin/chatInfo/?talker=${parmMap['talker']}&memberNoGm=${parmMap['memberNoGm']}">微信聊天记录</a></li> --%>
	</ul>
	<form id="searchForm" action="${ctx}/weixin/imChatInfo/" method="post"
		class="breadcrumb form-search">
		<input id="lastPage" name="lastPage" type="hidden" value="" />
		<input id="next" name="next" type="hidden" value="" />
		<input id="talkDetail" name="talkDetail" type="hidden" value="" />
		<input id="startTimeDetail" name="startTimeDetail" type="hidden" value="" />
		
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input id="talker" name="talker" type="hidden" value="${parmMap['talker']}" />
		<input id="memberNoGm" name="memberNoGm" type="hidden" value="${parmMap['memberNoGm']}" />
		<ul class="ul-form">
			<li><label>聊天时间 ：</label> <input id="beginDate" name="startTime"
				type="text" readonly="readonly" maxlength="20"
				class="input-mini Wdate"
				value="<fmt:formatDate value="${parmMap.startTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				-- <input id="endDate" name="endTime" type="text"
				readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${parmMap.endTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<%-- <tags:message content="${message}" /> --%>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>聊天时间</th>
				<th>聊天次数</th>
				<th>最近聊天内容</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${item.dateFormat}</td>
					<td>${item.num}</td>
					<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
					<div style="width: 500px;">${item.content }</div>
					</td>
					<td>
						<div class="operate-group">
							<a href="javascript:;" class="view_btn" data-talker="${parmMap['talker']}" data-startTime="${item.dateFormat}" data-memberNoGm="${parmMap['memberNoGm']}">查看</a>
						</div></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
	<div id="chatInfobox">
	<div id="chatInfo">
	<div class="title_name"></div>
		<div id="chatDetail">
			<div id="loading" >
			</div>
			<ul id="chatDetailUl">
			</ul>
		</div>
	</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		
		//聊天详情
		$('.view_btn').click(function() {
			var talker = $(this).attr("data-talker");
			var startTime = $(this).attr("data-startTime");
			var memberNo=$(this).attr("data-memberNoGm");
			$("#talkDetail").val(talker);
			$("#startTimeDetail").val(startTime);
			 $("#chatDetail ul li").remove();
			  $.ajax({
                 type:"POST",
                 url:"${ctx}/weixin/imChatInfo/view",
                 data:{talker:talker,chatTimeBegin:startTime,pageSize:11,memberNoGm:memberNo},
                 dataType:'JSON',
                 success:function(result){
                	 var html="";
                	 $("#next").val(result.next);
            		 $("#lastPage").val(result.lastPage);
                	 result=result.list;
                	 if(result!=undefined&& result.length>0){
                		 $(".title_name").text(result[0].pmNickName);
                		 for(var i=result.length-1;i>=0;i--){
                			 if(isEmptyObject(result[i].gmPhoto)){
                				 result[i].gmPhoto="/oms-web/static/admin/images/introduce/file.png";
             				}
                			 if(result[i].senderFlag==1){  //issend==1表示是自己(导购)发的信息
                				 
                				 var info1='';
                			 //消息类型 1文本 3图片 34语音 43视频 42名片 47图片表情 48定位 49分享 50视频聊天 10000系统信息（添加好友）318767153系统安全提示
                			 //导购 IM新增类型 490素材 491优惠券 492活动 493名片
                				 if(result[i].type=='1'||result[i].type=='50'||result[i].type=='318767153'){
                					info1='<span class="send_info_right right">'+result[i].content+'</span>';
                   				 }else if(result[i].type=='49'|| result[i].type=='490' ||result[i].type=='491' ||result[i].type=='492' ||result[i].type=='493'){
                   					var sType='';
                   					if(result[i].type=='490')
                   						sType='素材';
                   					else if(result[i].type=='491')
                   						sType='优惠券';
                   					else if(result[i].type=='492')
                   						sType='活动';
                   					else if(result[i].type=='493')
                   						sType='名片';
                   					info1='<span class="send_info_right right">'+'<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享'+sType+']</a>'+'</span>';
                   				 }else if(result[i].type=='3' ||result[i].type=='47'){
                   					 var resourcesPath='';
                   					 if(result[i].resourcesPath){
                   						resourcesPath=result[i].resourcesPath
                   					 }else{
                   						resourcesPath='${ctxStatic}/images/defaultImg.png';
                   					 }
                   					info1='<span class="send_info_right right"> <img src="'+resourcesPath+'" width="100px"/></span>';
                   				 }else if(result[i].type=='34'){
                   					info1='<div class="audio-centent"><div class="audio-box_right"><span class="icon-audio_right"></span>'+
                   					 '<span class="">语音标题</span></div><span class="ml10_right"></span></div>'
                   				 }else if(result[i].type=='43'){
                   					info1='<div class="video-ifram"></div></div></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
                   				 }else if(result[i].type=='42'){
                   					info1='<span class="send_info_right right"> <div>'+result[i].content+'<p class="nav_icon">个人名片</p></div></span>';
                   				 }else if(result[i].type=='48'){
                   					info1='<span class="send_info_right right"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'+result[i].content.substring(result[i].content.indexOf('label="')+7,result[i].content.indexOf('" poiname'))+'</span></div></span>';
                   				 }else if(result[i].type=='10000'){
                   					 html=html+'<li>'+result[i].content+'</li>'
                   					continue;
                   				 }
                				 html=html+'<li class="right"><div class="mem_head right"><img src="'+uploadUrl+result[i].gmPhoto+ 
                				 '" /></div><div class="mem_info right">'+info1+'</div></li>'
                			 }else if(result[i].senderFlag==2){ //issend==2表示是对方(客户)发的信息
                				 var info=""
                					 if(result[i].type=='1'||result[i].type=='50'||result[i].type=='318767153'){
	                					 info='<span class="send_info">'+result[i].content+'</span>';
	                				 }else if(result[i].type=='49'){
                    					 info='<span class="send_info">'+'<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享]</a>'+'</span>';
	                   				 }else if(result[i].type=='3' ||result[i].type=='47'){
	                					 var resourcesPath='';
	                   					 if(result[i].resourcesPath){
	                   						resourcesPath=result[i].resourcesPath
	                   					 }else{
	                   						resourcesPath='${ctxStatic}/images/defaultImg.png';
	                   					 }
	                					 info='<span class="send_info"> <img src="'+resourcesPath+'" width="100px"/></span>';
	                				 }else if(result[i].type=='34'){
	                					 info='<div class="audio-centent"><div class="audio-box"><span class="">语音标题</span>'+
	                					 '<span class="icon-audio"></span></div><span class="ml10"></span></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
	                				 }else if(result[i].type=='43'){
	                					 info='<div class="video-ifram"></div>'
	                				 }else if(result[i].type=='42'){
	                					 info='<span class="send_info"> <div>'+result[i].content+'<p class="nav_icon">个人名片</p></div></span>';
	                   				 }else if(result[i].type=='48'){
	                    					info1='<span class="send_info"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'+result[i].content.substring(result[i].content.indexOf('label="')+7,result[i].content.indexOf('" poiname'))+'</span></div></span>';
	                   				 }else if(result[i].type=='10000'){
	                   					 html=html+'<li class="top1">'+result[i].content+'</li>'
	                   					 continue;
	                   				 }
                				 html=html+'<li class="left"><div class="mem_head left"> <img src="'+result[i].pmPhoto+ 
                				 '" /></div><div class="mem_info left">'+info+'</div></li>'
                			 }
                		 }
                		$("#chatDetail ul").append(html); 
                		var h=document.getElementById('chatDetailUl').scrollHeight;
                		$('#chatDetail').scrollTop(750);
                	 }else{
                		 showTip("网络异常!","info");
                	 }
                 }
             });
			  $("#chatInfobox").css("display","block");
		 });
		
			  //滑动向后台请求分页
			  $('#chatDetail').on('scroll',function(){
				  var sct=$('#chatDetail').scrollTop();
				  var next=$("#next").val();
				  var lastPage=$("#lastPage").val();
				  if(sct==0 && lastPage=="false"){
					  $("#loading").css("display","block");
					  $.ajax({
			                 type:"POST",
			                 url:"${ctx}/weixin/imChatInfo/view",
			                 data:{talker:$("#talkDetail").val(),chatTimeBegin:$("#startTimeDetail").val(),pageNo:next,pageSize:11},
			                 dataType:'JSON',
			                 success:function(result){
			                	 $("#loading").css("display","none");
			                	 var html="";
			                	 $("#next").val(result.next);
			            		 $("#lastPage").val(result.lastPage);
			                	 result=result.list;
			                	 if(result!=undefined && result.length>0){
			                		 for(var i=result.length-1;i>=0;i--){
			                			 if(isEmptyObject(result[i].gmPhoto)){
			                				 result[i].gmPhoto="/oms-web/static/admin/images/introduce/file.png";
			                				}
			                			 if(result[i].senderFlag==1){  //issend==1表示是自己发的信息
			                				 var info1='';
			                				if(result[i].type=='1'||result[i].type=='50'||result[i].type=='10000'||result[i].type=='318767153'){
			                					info1='<span class="send_info_right right">'+result[i].content+'</span>';
			                   				 }else if(result[i].type=='49'|| result[i].type=='490' ||result[i].type=='491' ||result[i].type=='492' ||result[i].type=='493'){
			                   					var sType='';
			                   					if(result[i].type=='490')
			                   						sType='素材';
			                   					else if(result[i].type=='491')
			                   						sType='优惠券';
			                   					else if(result[i].type=='492')
			                   						sType='活动';
			                   					else if(result[i].type=='493')
			                   						sType='名片';
			                   					info1='<span class="send_info_right right">'+'<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享'+sType+']</a>'+'</span>';
			                   				 }else if(result[i].type=='3' ||result[i].type=='47'){
			                   					 var resourcesPath='';
			                   					 if(result[i].resourcesPath){
			                   						resourcesPath=result[i].resourcesPath
			                   					 }else{
			                   						resourcesPath='${ctxStatic}/images/defaultImg.png';
			                   					 }
			                   					info1='<span class="send_info_right right"> <img src="'+resourcesPath+'" width="100px"/></span>';
			                   				 }else if(result[i].type=='34'){
			                   					info1='<div class="audio-centent"><div class="audio-box_right"><span class="icon-audio"></span>'+
			                   					 '<span class="">语音标题</span></div><span class="ml10_right"></span></div>'
			                   				 }else if(result[i].type=='43'){
			                   					info1='<div class="video-ifram"></div></div></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
			                   				 }else if(result[i].type=='42'){
			                   					info1='<span class="send_info_right right"> <div>'+result[i].content+'<p class="nav_icon">个人名片</p></div></span>';
			                   				 }else if(result[i].type=='48'){
			                    					info1='<span class="send_info_right right"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'+result[i].content.substring(result[i].content.indexOf('label="')+7,result[i].content.indexOf('" poiname'))+'</span></div></span>';
			                   				 }else if(result[i].type=='10000'){
			                   					 html=html+'<li class="top1">'+result[i].content+'</li>'
			                   					 continue;
			                   				 }
			                				 html=html+'<li class="right"><div class="mem_head right"><img src="'+uploadUrl+result[i].gmPhoto+ 
			                				 '" /></div><div class="mem_info right">'+info1+'</div></li>'
			                			 }else if(result[i].senderFlag==2){ //issend==2表示是对方发的信息
			                				 var info=""
			                				 if(result[i].type=='1'||result[i].type=='50'||result[i].type=='10000'||result[i].type=='318767153'){
			                					 info='<span class="send_info">'+result[i].content+'</span>';
			                				 }else if(result[i].type=='49'){
			                					 info='<span class="send_info">'+'<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享]</a>'+'</span>';
			                   				 }else if(result[i].type=='3' ||result[i].type=='47'){
			                					 var resourcesPath='';
			                   					 if(result[i].resourcesPath){
			                   						resourcesPath=result[i].resourcesPath
			                   					 }else{
			                   						resourcesPath='${ctxStatic}/images/defaultImg.png';
			                   					 }
			                					 info='<span class="send_info"> <img src="'+resourcesPath+'" width="100px"/></span>';
			                				 }else if(result[i].type=='34'){
			                					 info='<div class="audio-centent"><div class="audio-box"><span class="">语音标题</span>'+
			                					 '<span class="icon-audio"></span></div><span class="ml10"></span></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
			                				 }else if(result[i].type=='43'){
			                					 info='<div class="video-ifram"></div>'
			                				 }else if(result[i].type=='42'){
			                					 info='<span class="send_info"> <div>'+result[i].content+'<p class="nav_icon">个人名片</p></div></span>';
			                   				 }else if(result[i].type=='48'){
			                    					info1='<span class="send_info"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'+result[i].content.substring(result[i].content.indexOf('label="')+7,result[i].content.indexOf('" poiname'))+'</span></div></span>';
			                   				 }else if(result[i].type=='10000'){
			                   					 html=html+'<li class="top1">'+result[i].content+'</li>'
			                   					 continue;
			                   				 }
			                				 html=html+'<li class="left"><div class="mem_head left"> <img src="'+result[i].pmPhoto+ 
			                				 '" /></div><div class="mem_info left">'+info+'</div></li>'
			                			 }
			                		 }
			                		 
			                		$("#chatDetail ul").prepend(html); 
			                	 }
			                 }
			             });
				  }
			  });
			  
	   $("#chatInfobox").click(function(e){
		   var target=$(e.target);
		   if(target.closest('#chatInfo').length==0){
			   $("#chatInfobox").hide();
		   }
		   
	   });
	});
	  function isEmptyObject(e) {  
	        var t;  
	        for (t in e)  
	            return !1;  
	        return !0  
	    }
</script>
</body>
</html>