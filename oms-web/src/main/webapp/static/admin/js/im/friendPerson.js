var pageFlag=true;
var scrollHeight=true;
$(document).ready(function() {
	$(".shop_nav .storeClient").change(function(){
		storeSearch();
	});
	
	$(".shop_nav #codeListSec").change(function(){
		storeSearch();
	});
	$(".shop_nav select.province").change(function(){
		storeSearch();
	});
	$(".shop_nav select.qu").change(function(){
		storeSearch();
	});
	$(".shop_nav select.shi").change(function(){
		storeSearch();
	});
	
	var hasList=$(".shop_nav .shop_list").html();
	if($.trim(hasList)!=""){
		queryInfo();
	}

	$('.sendF #friendScroll').on('scroll',function(){
		var dh = $('.sendF #friendScroll').height(); // document height
		var ch=$(".con_right .comment_all").height(); //内容高度
		var sh = $('.sendF #friendScroll').scrollTop();
		//console.log(ch,dh,sh)
		if(ch-dh - sh< 10 && pageFlag && scrollHeight){
			
			var tt=$("#pageNo").val()*$("#pageSize").val();
			var count=$("#count").val();
			scrollHeight=false;
			if(tt<Number(count)){
				var pageNo=Number($("#pageNo").val())+1;
				$("#pageNo").val(pageNo);
				queryInfo();
			}else{
				pageFlag = false;
			}
			
		}
	});
	
	$(".period").blur(function(){
		var self = this;
		
		var memberNo=getQueryString('memberNo')||'';
		var cycle=$(this).val();
		if(/^[1-9]\d*$/.test(cycle)){
			$.ajax({
		        type:"POST",
		        url:$('#ctx').val()+"/im/updateFriendPointCycle",
		        data:{cycle:cycle,memberNo:memberNo},
		        dataType:'text',
		        success:function(result){
		        	if(result=="success"){	
		        	
		        	}
		        }
		    });
			$(this).next().css("display","none");
		}else{
			$(this).next().css("display","inline-block");
		}
		
		
	});
//	syncMemberWxInfo();		实时同步客户信息耗服务器资源 DZP 2018-12-22
});

function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();
	var shopNo=$(".shop_nav #codeListSec").find("option:selected").val();
	var pmTypeCode=$(".shop_nav .storeClient ").find("option:selected").val();
	var provinceCode=$(".shop_nav .province ").find("option:selected").val();
	var cityCode=$(".shop_nav .shi ").find("option:selected").val();
	var cityAreaCode=$(".shop_nav .qu ").find("option:selected").val();
	window.location.href=$('#ctx').val()+"/im/friendList?searchWords="+searchWords+"&shopNo="+shopNo+"&pmTypeCode="+pmTypeCode+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode;
}

/*查找朋友圈信息*/

function queryInfo(event){
	if(event!="" && event!=undefined){
		$(event).addClass("active");
		$(event).siblings().removeClass("active");
		$("#pageNo").val(1);
		$('.sendF #friendScroll').scrollTop(0);
	}
	pageFlag=true;
	
	var memberNo=getQueryString('memberNo')||'';
	var pageNo=$("#pageNo").val();
	var noWxShop=getQueryString('noWxShop')||'';
	//var merchantNo=$(".shop_list .shopDetail.active .merchantNo").val();
	
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/getfriendData",
        data:{pageNo:pageNo,memberNo:memberNo,noWxShop:noWxShop},
        dataType:'JSON',	
        success:function(result){
        	if(result.friendForumPage){
        		var friendsInfo=result.friendForumPage.list;
        		
        		if(result.friendForumPage.count>0){       			
        			$("#count").val(result.friendForumPage.count);
        			$("#pageNo").val(result.friendForumPage.pageNo);
        			$("#pageSize").val(result.friendForumPage.pageSize);
        			var fHtml='';
        			fHtml += "<input type='hidden' class='nickNickName' value='"+ result.friendsInfo[0].nickName +"'>"
        			$('.noWxShopName').val(result.friendsInfo[0].noWxShop)
        			console.log($('.noWxShopName').val(result.friendsInfo[0].noWxShop))
        			for(var i=0;i<result.friendForumPage.list.length;i++){
        				if(friendsInfo[i].status == 2){	//发送成功
        					var headImg =getHeadAddress(friendsInfo[i].headImg);    			
        					fHtml +='<div class="comment">';
        					fHtml += "<input class='pyqMemberNo' type='hidden' value="+ friendsInfo[i].memberNo +">";
        					fHtml += "<input class='pyqMemberNoGm' type='hidden' value="+ friendsInfo[i].memberNoGm +">";
        					if(friendsInfo[i].optFlag==1){
        						fHtml += '<div class="comment_title"><img alt="" class="memHead" src="'+headImg+'"/>'
        					}else{
        						fHtml += '<div class="comment_title"><img alt="" onclick="headList(\''+friendsInfo[i].memberNo+'\',\''+friendsInfo[i].memberNoGm+'\',\''+friendsInfo[i].merchantNo+'\',this)" class="memHead" src="'+headImg+'"/>'
        					}
        					fHtml += '<div class="bsPer"><p class="oneLine memWxnick">'+(friendsInfo[i].memberName || friendsInfo[i].nickName)+'</p>';
        					fHtml += '<p class="oneLine">'+new Date(Number(friendsInfo[i].timestamp*1000)).format("yyyy-MM-dd hh:mm")+'</p>';
        					fHtml += '</div></div><div class="comment_content" id="xmpl">';	
        					if(friendsInfo[i].type == "1" || friendsInfo[i].type == "2"){ //1图文 2纯文字
        					fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+friendsInfo[i].code+'" class="comment_text small" >'+friendsInfo[i].content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
        					if(friendsInfo[i].imgAddr != ""){
        							var img=friendsInfo[i].imgAddr.split(",");
        							var imgs="";
        							if(friendsInfo[i].imgStatus !=undefined){
        								imgs=friendsInfo[i].imgStatus.split(",");
									}
        							fHtml +='<div class="comment_pic">'
        								for(var n=0;n<img.length;n++){
        									if(imgs!=""){
        										fHtml +='<img onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
        									}else{
        										fHtml +='<img onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
        									}
        									
        								}
        							fHtml +='</div>';	
        						}
        					}else if(friendsInfo[i].type == "3" || friendsInfo[i].type == "5"){ // 3、5分享 
        						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+friendsInfo[i].code+'" class="comment_text small" >'+friendsInfo[i].content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
        						fHtml +='<div class="comment_pic">';
        						fHtml +='<a href="'+friendsInfo[i].shareurl+'" target="view_window">'+friendsInfo[i].shareurl+'</a>';	
        						fHtml +='</div>';	
        					}else if(friendsInfo[i].type == "4"){ //4歌曲分享
        						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+friendsInfo[i].code+'" class="comment_text small" >'+friendsInfo[i].content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
        						fHtml +='<div class="comment_pic"></audio>';
//        					fHtml +='<audio src="'+friendsInfo[i].shareurl+'" controls="controls">';	
        						fHtml +='<a href="'+friendsInfo[i].shareurl+'" target="view_window">'+friendsInfo[i].shareurl+'</a>';
        						fHtml +='</div>';	
        						
        					}else if(friendsInfo[i].type == "15"){ // 15视频
        						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+friendsInfo[i].code+'" class="comment_text small" >'+friendsInfo[i].content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
            					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
        						fHtml +='<div class="comment_pic">';
        						fHtml +='<video src="'+friendsInfo[i].shareurl+'" controls="controls"  style="width:300px; height:200px;"></video>';	
        						fHtml +='</div>';	
        						
        					}
        					
        					fHtml +='<div class="comment_do"><input type="hidden" value="'+friendsInfo[i].noWxShop+'" class="noWxShop">';
        					fHtml +='<input type="hidden" value="'+friendsInfo[i].noWx+'" class="noWx"><input type="hidden" value="'+friendsInfo[i].friendsId+'" class="friendsId">';
        					fHtml +='<input type="hidden" value="'+friendsInfo[i].shopNo+'" class="shopNo">';
        					fHtml +='<input type="hidden" value="'+friendsInfo[i].code+'" class="friendsCode">';
        					fHtml +='<div class="zan">';
        					if(friendsInfo[i].likes!=undefined){
        						var fl = false; // 未点赞
        						var fLikes=friendsInfo[i].likes;
        						for(var f = 0 ,len = fLikes.length; f < len ; f++){
        							if(fLikes[f].username == $('.noWxShopName').val()){
        								fl = true;
        								break;
        							}
        						}
        						fHtml +='<img onclick="zan(this)"  src="'+$("#ctxStatic").val()+'/admin/images/imImages/'+ (fl?'zan-icon':'zanNo') +'.png" class="'+ (fl?'':'noZ') +'">';
        						var nicknameArr = [];
        						for(var f=0; f<fLikes.length;f++){
        							nicknameArr.push(fLikes[f].memberName);
        						}
        						fHtml +='<p>'+ nicknameArr.join(',') +'</p>';
        					}else{
        						fHtml +='<img onclick="zan(this)"  src="'+$("#ctxStatic").val()+'/admin/images/imImages/zanNo.png" class="noZ"><p></p>';
        					}
        					fHtml +='<img alt="" onclick="showComment(this)" src="'+$("#ctxStatic").val()+'/admin/images/imImages/leave.png"></div>';
        					fHtml +='<div class="goComment goCommentOne"><textarea class="conmmentInfo" id="textContent" onkeydown="BindEnter(event)" rows="" cols="" placeholder="请输入文字"></textarea>';
        					fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='<div class="comment_detail">'
        							/*if(friendsInfo[i].autoContent!=undefined && friendsInfo[i].autoContent!=""){
    						fHtml +='<p>'+friendsInfo[i].nickName+'：'+ friendsInfo[i].autoContent+'</p>';
    					}*/
        						var wxnickName= $('#wxNickname').val();
        							if(friendsInfo[i].comments!=undefined){
        								var comments=friendsInfo[i].comments;
        								for(var c=0; c<comments.length;c++){
        									var params = {
        											memberNoGm:friendsInfo[i].memberNoGm, // 评论导购会员号
        											memberNo:friendsInfo[i].memberNo, // 客户编号
        											memberName:friendsInfo[i].memberName, // 客户微信名
        											toCommentCode:comments[c].code, // 朋友圈id
        											shopNo:friendsInfo[i].shopNo, // 终端号
        											shopWxNo:friendsInfo[i].noWxShop, // 终端微信号
        											toWxNo:comments[c].noWxShop, // 被评论微信号
        											toWxName:comments[c].nickname, // 被评论微信名
        											toFriendsId:comments[c].friendsId, // 被评论朋友圈信息
        									};
        									
        											if( comments[c].nickname && comments[c].tonickname){ //空
    											fHtml +='<div class="replayListInfo"><p>';
    											if(comments[c].memberNoGm){
    												fHtml += '<span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>';
    											}else{
    												fHtml += '<span class="comNickName">'+comments[c].memberName+'</span>';
    											}
    											fHtml += '回复';
    												fHtml += '<span class="comNickName">'+comments[c].tonickname+'</span>';
    											
    											fHtml += '：'+ comments[c].content.expression().expressionFan();
        										if(comments[c].memberNoGm){
        											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
        										}
        										fHtml += '</p>';
        									}else{	//非空
        										if(comments[c].memberNoGm){
        											fHtml +='<div class="replayListInfo"><p><span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
        										}else{
        											fHtml +='<div class="replayListInfo"><p><span class="comNickName">'+comments[c].nickname+'</span>：'+ comments[c].content.expression().expressionFan();
        										}
        										
        										if(comments[c].memberNoGm){
        											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
        										}
        										fHtml +='</p>';            										
        									}


        									setTimeout(function(){
        										$('.comment_do .comment_detail p').hover(function() {
        											$(this).children("i").css('display','block');
        										},function() {
        											$(this).children("i").css('display','none');
        										},1000)
        									})
        									
        									fHtml += '</div>';	
        								}
        							}
        					
        					fHtml +='</div>'
//        				fHtml +='<div class="goComment"><textarea class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
//        				fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='</div></div></div>'
        				}
        				
        			}
        			if(event!="" && event!=undefined){
   					$(".con_right .comment_all").html(fHtml);
   				}else{
   					$(".con_right .comment_all").append(fHtml);
  				}
        			scrollHeight=true;
        			
        			$(fHtml).find(".comment_content .comment_text").each(function(){
        				var code=$(this).attr("id");
        				var pcode=$(".con_right .comment_all ").find("#"+code).height();
        				if(pcode>=153){
        					$(".con_right .comment_all ").find("#"+code).next().attr("class","show");
        				}else{
        					$(".con_right .comment_all ").find("#"+code).next().attr("class","hidden");
        				}
        			})
            	}else{
            		$(".con_right .comment").remove();
           	}
        		var imgWidth = Math.floor($('.comment_pic:first').width() * 0.32);
        		$('.comment_pic img').css('height',imgWidth+'px');
        	}
        }
        
    }); 
}
function showComment(event){
	var d=$(event).parent().parent().find(".goComment").css("display");
	if(d=="none"){
		$(event).parent().parent().find(".goComment").css("display","flex");
	}else{
		$(event).parent().parent().find(".goComment").css("display","none");
	}
	
}

function commentCommit(event){
	var t=$(event).prev().val();
	if(t.length>500){
		var msg ="评论只能少于500字哦！";
  		 options = {
				confirm:function(){
				}, // 确定调用方法
				cancel:function(){}, // 取消调用方法
				confirmBtn:{ // 确定按钮文本和背景
					text:'确定',
					background:'#00b204',
					color:'#fff'
				},
				cancelBtn:{ // 取消按钮文本和背景
					text:'取消',
					background:'#fff',
				},
				isCancel:false, // 是否显示取消按钮
				msg:msg // 提示文字
			};
  		 ConfirmBox(options);
	}else{
		var noWxShop=$(event).parent().parent().find(".noWxShop").val();
		var friendsId=$(event).parent().parent().find(".friendsId").val();
		var memberNo=$(event).parent().parent().parent().parent().find(".pyqMemberNo").val();
		debugger;
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/toComment",
	        data:{toFriendsId:friendsId,shopWxNo:noWxShop,toConent:t,memberNo:memberNo},
	        dataType:'JSON',
	        success:function(result){
	        	if(result.state=="true"){
//	        		var nickName= $(".shop_nav .shop_list .shopDetail.active .nickName").val();
	        		var nickName = $('#wxNickname').val()
	        		var h="<div class='replayListInfo'><p><span class='comNickName'>"+nickName+"</span>："+t+"</p></div>";
	        		$(event).parent().next().append(h);
	        		$(event).prev().val("");
	        		$('.goCommentOne').hide();
	        	}else{
	        		var msg ="评论失败！";
	        		 options = {
	       				confirm:function(){
	       				}, // 确定调用方法
	       				cancel:function(){}, // 取消调用方法
	       				confirmBtn:{ // 确定按钮文本和背景
	       					text:'确定',
	       					background:'#00b204',
	       					color:'#fff'
	       				},
	       				cancelBtn:{ // 取消按钮文本和背景
	       					text:'取消',
	       					background:'#fff',
	       				},
	       				isCancel:false, // 是否显示取消按钮
	       				msg:msg // 提示文字
	       			};
	        		 ConfirmBox(options);
	        	}
	        }
	    });
	}
}
function commentCommitAgin(event,data){
	var t=$(event).prev().val();
	if(t.length>500){
		var msg ="评论只能少于500字哦！";
  		 options = {
				isCancel:false, // 是否显示取消按钮
				msg:msg // 提示文字
			};
  		 ConfirmBox(options);
	}else{
		var noWxShop=$(event).attr('nowxshop');
		var friendsId=$(event).parent().parent().parent().find(".friendsId").val();
		var shopNo=$(event).parent().parent().parent().find(".shopNo").val();
		var noWx= $('.active .noWx').val()
		var toWxName = $(event).parent().prev().children('.replyName').html();
		
		var params = data;
		
		params.toConent = $(event).siblings('.conmmentInfo').val(); // 评论内容
		
		var nickName= params.toWxName;
		var pp = $('#wxNickname').val()
		debugger;
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/toComment",
	        data:params,
	        dataType:'JSON',
	        success:function(result){
	        	if(result.state!="true"){
	        		var msg ="评论失败！";
	        		options = {
        				isCancel:false, // 是否显示取消按钮
        				msg:msg // 提示文字
	        		};
	        		ConfirmBox(options);
	        		$(event).parents('.replayInfoSend').remove();
	        	}else{
	        		var html="<div class='replayListInfo'><p><span class='comNickName'>"+ pp +"</span>回复<span class='comNickName'>"+data.toWxName +"</span>:"+params.toConent+"</p></div>"
	        		$(event).parents('.replayListInfo').parent().append(html);
	        		$(event).parents('.replayInfoSend').remove();
	        	}
	        }
	    });
	}
}
function showComment(event){	
	$(event).parent().parent().find('.replyDiv').html("")
	var d=$(event).parent().parent().find(".goCommentOne").css("display");
	if(d=="none"){
		$(event).parent().parent().parent().find(".goCommentOne").css("display","flex");
	}else{
		$(event).parent().parent().parent().find(".goCommentOne").css("display","none");
	}	
}
function showCommentTwo(elem,data){
	var html = '';
	html += '<div class="replayInfoSend">';
	html += '<div class="replyDiv" style="dispay:flex;">回复：<span class=" replyName">'+data.toWxName+'</span></div>';
	html +='<div class="goComment goCommentTwo" style="display:flex;">';
	html += '<textarea class="conmmentInfo" id="textContent" onkeydown="BindEnter(event)" rows="" cols="" placeholder="请输入文字"></textarea>';
	html +='<input value="发送" type="button" onclick=\'commentCommitAgin(this,'+ JSON.stringify(data) +')\' class="sendComment">';
	html += '</div></div>';
	
	var replayListInfo = $(elem).parents('.replayListInfo');
	var replayInfoSend = $(replayListInfo).find('.replayInfoSend');
	
	if(replayInfoSend.length > 0){
		$(replayInfoSend).remove();
	}else{
		$(replayListInfo).append(html);
	}
}

function zan(event){
	var noWxShop=$(event).parents('.comment').find(".noWxShop").val();
	var noWx=$(event).parents('.comment').find(".noWx").val();
	var friendsId=$(event).parents('.comment').find(".friendsId").val();
	var memberNo=$(event).parents('.comment').find(".pyqMemberNo").val();
	if($(event).hasClass("noZ")){
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/toImLike",
	        data:{
	        	toWxNo:noWx,
	        	toFriendsId:friendsId,
	        	shopWxNo:noWxShop,
	        	memberNo:memberNo
        	},
	        dataType:'JSON',
	        success:function(result){
	        	console.log(result);
	        	if(result.state=="true"){
	        		var nickName= $("#wxNickname").val();
        			var p = $(event).next();
        			$(p).text(function(i,txt){
        				if(txt == ''){
        					return nickName;
        				}else{
        					return nickName +', '+ txt;
        				}
        			});
	        		$(event).attr("src",$("#ctxStatic").val()+'/admin/images/imImages/zan-icon.png');
	        		$(event).removeClass("noZ");
	        	}else{
	        		var msg ="点赞失败！";
	        		options = {
        				isCancel:false, // 是否显示取消按钮
        				msg:msg // 提示文字
	        		};
	        		ConfirmBox(options);
	        	}
	        }
	    });
	}else{
		var msg ="不能重复点赞！";
 		 options = {
				confirm:function(){
				}, // 确定调用方法
				cancel:function(){}, // 取消调用方法
				confirmBtn:{ // 确定按钮文本和背景
					text:'确定',
					background:'#00b204',
					color:'#fff'
				},
				cancelBtn:{ // 取消按钮文本和背景
					text:'取消',
					background:'#fff',
				},
				isCancel:false, // 是否显示取消按钮
				msg:msg // 提示文字
			};
 		 ConfirmBox(options);
	}
	
}


function headList(memberNo,memberNoGm,merchantNo,event) { //点击头像查看个人信息
	$('.nav-wrapper .perSonInfo').show();
	$('.sendF .shop_navTwo').hide();
	//$('.hideTelescopicButton').show();
	$('.shop_navThree').hide();
	$('.con_right_title').show();
	$('.newsListIcon').show();
	$('#materialTabId').attr({'memberno':memberNo,'membernogm':memberNoGm});
	$.ajax({
		type:"POST",
        url:$('#ctx').val()+"/im/personInfo",
        data:{memberNo:memberNo,memberNoGm:memberNoGm,merchantNo:merchantNo},
        dataType:'text',	
        success:function(result){
        	$(".shop_nav").html(result);
        }
	})

}

function clicksBack() {
	window.history.go(-1);
}
//同步客户微信基本信息
function syncMemberWxInfo(){
	var noWx=$(".oth_info .noWx").val();
	var memberNo=getQueryString('memberNo');
	var memberNoGm=getQueryString('memberNoGm');
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/syncMemberWxInfo.do",
        data:{noWx:noWx,memberNoGm:memberNoGm,memberNo:memberNo},
        dataType:'JSON',
        success:function(result){
        	if(result!=null){
        		if(result.alias!=""){
            		$(".wxNum").text(result.alias);
            	}else if(result.noWx!=""){
            		$(".wxNum").text(result.noWx);
            	}
            	if(result.headAddress!=""){
            		$(".person_chat_head img").attr("src",result.headAddress);
            		$(".comment_title .memHead").attr("src",result.headAddress);
            	}
    			if(result.nickNameWx!=""){
    				$(".nickNameWx").text(result.nickNameWx);   		
    			}
        	}
        }
    });
}
function BindEnter(event) { //回复禁止回车键
    event = event || window.event;
    if (event.keyCode == 13) {
        event.returnValue = false;
//        $('#message').val('').focus();
    }
}



