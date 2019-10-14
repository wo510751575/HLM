var pageFlag=true;
var pageFlagList=true;
var scrollHeight=true;
var allCommentPageNo = 1; // 保存全部评论列表的页码
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
		var ch=$(".con_right .comment_all:visible").eq(0).height(); //内容高度
		var sh = $('.sendF #friendScroll').scrollTop();
		if(ch-dh - sh< 0 && pageFlag && scrollHeight && sh != 0){		
			var tt=$("#pageNo").val()*$("#pageSize").val();
			
			var index = $('#topfS .friendItem li.friendItemActive').index();
//			var count = (index == 0 ? $("#AllCount").val() : $("#count").val());
			var count = "";
			if(index==0){
				count = $("#AllCount").val();
			}else if(index==1){
				count = $("#count").val();
			}else if(index ==2){
				count = $("#replyCount").val();
			}
			
			scrollHeight=false;
			if(tt<Number(count)){
				var pageNo=Number($("#pageNo").val())+1;
				console.log(pageNo)
				$("#pageNo").val(pageNo);
				if(index==0 && $('.commentTwo').css("display")=='none'){
					queryInfo(); 
				}else if(index==1){
					showCommentThree();
				}else if(index ==2){
					showGetCommentFour();
				}
			}else{
				pageFlag = false;
			}
			
		}
	});
	$('.shop_navThree .newsListContent-wrarpper').on('scroll',function(){
		var dh = $('.shop_navThree .newsListContent-wrarpper').height(); // document height
		var ch=$(".shop_navThree .newsListContent").height(); //内容高度
		var sh = $('.shop_navThree .newsListContent-wrarpper').scrollTop();
		if(ch-dh - sh < 10 && pageFlagList){
			pageFlagList = false;
			clickNewsList();
		}
	});
	var noWx = $('.active .noWx').val();
	
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
	$('.commentThree').hide();
	$('.commentOne').show();
	$('.friendItem li').removeClass('friendItemActive');
	$('.friendItem li:first').addClass('friendItemActive')
	if(event){
		$(event).addClass("active");
		$(event).siblings().removeClass("active");
		$("#pageNo").val(1);
		$('.sendF #friendScroll').scrollTop(0);
	}
	allCommentPageNo = $("#pageNo").val();
	pageFlag=true;
	
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	var pageNo=$("#pageNo").val();
	//var merchantNo=$(".shop_list .shopDetail.active .merchantNo").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/getfriendData",
        data:{noWxShop:noWx,shopNo:shopNo,pageNo:pageNo},
        dataType:'JSON',	
        success:function(result){
        	commentCount();//待評論數
        	commentCountTwo()//待回复数
//        	getCommentCountTwo();
        	$.ajax({
				type:"POST",
				url:$('#ctx').val()+"/im/loadInteractMessageCount.do", //新消息提示
				data:{noWxShop:noWx},
				dataType:'JSON',	
				success:function(result){
					if(result.messageCount>0){
						html = "";
						html += "<div class='newsListIcon' onclick='clickNewsList(\"Y\")'>";
//						if(result.rows=="" || result.rows==undefined){
//			        		$('#getHeader').val($('#UploadUrl').val() + $('#ctxStatic').val() + '/admin/images/introduce/file.png')
//			        	}else{
//			        		var header = getHeadAddress(result.rows[0].headImg);
//			        		$('#getHeader').val(header);
//			        	}
						html += "<i class='newsListHeader'><img src='"+getHeadAddress(result.headImg)+"'/></i>";
						html += "<b id='massageNum'>"+ result.messageCount +"条新信息</b><span></span>";
						html += "</div>";
						$('.newsListIcon-wrapper').html(html);
						
					}else{
						$('.newsListIcon-wrapper').html("")
					}
					
				}
			});
        	if(result.friendForumPage){
        		var friendsInfo=result.friendForumPage.list;
        		
        		if(result.friendForumPage.count>0){       			
        			$("#AllCount").val(result.friendForumPage.count);
        			$("#pageNo").val(result.friendForumPage.pageNo);
        			$("#pageSize").val(result.friendForumPage.pageSize);
        			var fHtml='';
        			if(result.friendsInfo.length>0){
        				$('.nickNickName').val(result.friendsInfo[0].nickName)
        				fHtml += "<input type='hidden' class='nickNickName' value='"+ result.friendsInfo[0].nickName +"'>"
        				$('.noWxShopName').val(result.friendsInfo[0].noWxShop);
        			}
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
        					fHtml += '</div>';
        					if(friendsInfo[i].noWx == $('.shop_list .active .noWx').val()){
        						fHtml += '<img class="delF" onclick="deleteFC(this,\''+friendsInfo[i].code+'\')"  src="'+$("#ctxStatic").val()+'/admin/images/imImages/del-icon.png" />';	
        					}
        					fHtml +='</div><div class="comment_content" id="xmpl">';
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
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')"  style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
        										fHtml +='<img onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
        									}else{
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +')" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
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
        							if(fLikes[f].username == $('.shop_list .active .noWx').val()){
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
        					fHtml +='<div class="goComment goCommentOne"><textarea onkeydown="BindEnter(event)" class="conmmentInfo" id="textContent" rows="" cols="" placeholder="请输入文字"></textarea>';
        					fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='<div class="comment_detail">'
        							/*if(friendsInfo[i].autoContent!=undefined && friendsInfo[i].autoContent!=""){
    						fHtml +='<p>'+friendsInfo[i].nickName+'：'+ friendsInfo[i].autoContent+'</p>';
    					}*/
        						var wxnickName=$(".shop_list .active .nickName").val();
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
        											toWxNo:comments[c].username, // 被评论微信号
        											toWxName:comments[c].memberName, // 被评论微信名
        											toFriendsId:comments[c].friendsId, // 被评论朋友圈信息
        									};
        										//commentSvrID 是空为回复   非空为评论
        										if( comments[c].memberName && comments[c].tonickname){ //空
        											fHtml +='<div class="replayListInfo"><p>';
        											if(comments[c].memberNoGm){
        												fHtml += '<span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>';
        											}else{
        												fHtml += '<span class="comNickName">'+comments[c].memberName+'</span>';
        											}
        											fHtml += '回复';
        												fHtml += '<span class="comNickName">'+comments[c].tonickname+'</span>';
        											
        											fHtml += '：'+ comments[c].content.expression().expressionFan();
            										if(comments[c].nickname.indexOf(wxnickName)){
            											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
            										}
            										fHtml += '</p>';
            									}else{	//非空

            										if(comments[c].memberNoGm){
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}else{
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}
            										
            										if(comments[c].nickname.indexOf(wxnickName)){
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
        					
        					fHtml +='</div>';
//        				fHtml +='<div class="goComment"><textarea class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
//        				fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='</div></div></div>'
        				}
        				
        			}
        			if(event!="" && event!=undefined){
        				$(".con_right .commentOne").html(fHtml);      				
        			}else{
        				$(".con_right .commentOne").append(fHtml);
        			}
        			scrollHeight=true;
        			$(fHtml).find(".comment_content .comment_text").each(function(){
        				var code=$(this).attr("id");
        				var pcode=$(".con_right .commentOne ").find("#"+code).height();
        				if(pcode>=153){
        					$(".con_right .commentOne ").find("#"+code).next().attr("class","show");
        				}else{
        					$(".con_right .commentOne ").find("#"+code).next().attr("class","hidden");
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
	
	
	
	var noWxList="";
	$(".noWx").each(function(i){
		noWxList +=$(this).val()+",";
	})
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/getZkTerminalStatusList.do",
        data:{noWxShops:noWxList.substring(0,noWxList.length-1)},
        dataType:'JSON',
        success:function(result){
        	var hh='<p class="chat_name pyShejiaoP offline">(离线)</p>';
        	for(var key in result){
        		$(".noWx").each(function(i){
            		if($(this).val()==key){
            			if(result[key]){
            				$(this).parent().find(".imshopName").removeClass("sheJiao");
            				$(this).parent().find(".imshopName p").removeClass("sheJiaoName");
            				$(this).parent().find(".imshopName .offline").remove();
            			}else{
            				$(this).parent().find(".imshopName").addClass("sheJiao");
            				$(this).parent().find(".imshopName p").addClass("sheJiaoName");
            				$(this).parent().find(".imshopName .offline").remove();
            				$(this).parent().find(".imshopName").append(hh);
            			}
            		}
            	})
        	}
        	
//            console.log(result);
        }
    }); 
	
	
}

function headList(memberNo,memberNoGm,merchantNo,event) { //点击头像查看个人信息
	$('.nav-wrapper .perSonInfo').show();
	$('.sendF .shop_navTwo').hide();
	//$('.hideTelescopicButton').show();
	$('.shop_navThree').hide();
	$('.con_right_title').show();
	//$('.newsListIcon').show();
	$('#materialTabId').attr({'memberno':memberNo,'membernogm':memberNoGm});
	$.ajax({
		type:"POST",
        url:$('#ctx').val()+"/im/personInfo",
        data:{memberNo:memberNo,memberNoGm:memberNoGm,merchantNo:merchantNo},
        dataType:'text',	
        success:function(result){
        	$(".perSonInfo").html(result);
        }
	})

}

function inputDateClickNewsList(start,end){
	var startDate = "";
	var endDate = "";
	if(start){
		startDate=start;
	}else{
		startDate=$("#startDate").val();
	}
	$("#startDate").val(startDate);
	if(end){
		endDate=end;
	}else{
		endDate=$("#endDate").val();
	}
	$("#endDate").val(endDate);
	if(startDate != '' || endDate != ''){
		clickNewsList('M');
	}
}

/**
 * 朋友圈点击最新消息
 * @param status
 * @returns
 */
function clickNewsList(status) { 
	$('.commentFour').html("");
	$('.nav-wrapper .hideTelescopicButton').hide();
	$('.sendF .shop_navTwo').hide();
	$('.sendF .perSonInfo').hide();
	$('.sendF .shop_navThree').show();
	$('.newsListIcon').hide();
	$('.lj-main .f_center').hide();
	$('.friendItem').hide();
	$('.commentThree').hide();
	var start = (status == 'Y' || status == 'M' ? 0 : $('#start').val());
	var limit = $('#pageSize').val();
	var noWx = $('.active .noWx').val();
	var beginDate = $('.startDate').val();
	var endDate = $('.endDate').val();
	var memberName = $('.searchThTwo').val();
	$.ajax({
		 type:"POST",
	        url:$('#ctx').val()+"/im/messageList.do",
	        data:{noWxShop:noWx,start:start,limit:limit,noWx:noWx,beginDate:beginDate,endDate:endDate,memberName:memberName},
	        dataType:'JSON',
	        success:function(result){
	        	var start = result.start + result.limit;
	        	$('#start').val(start);
	        	if(result.rows){
	        		var htmlList = "";
	        		$('#total').val(result.total);
	        		$('#limit').val(result.limit);
	        	  for(var i=0;i<result.rows.length;i++){
	        		  htmlList += '<li onclick="clickList(\''+result.rows[i].friendsCode+'\',this)"><div class="newsListContent-left"><img src="'+ getHeadAddress(result.rows[i].headImg)+'" /></div>';
	        		  htmlList += '<input class="fristHeader" type="hidden" value="'+ result.rows[0].headImg +'" />';
	        		  htmlList += '<input type="hidden" value="'+ result.rows[i].friendsCode +'"/>'
	        		  htmlList += '<div class="newsListContent-right">';
	        		  htmlList += '<div class="newsListContent-right-title"><b>'+ result.rows[i].memberName +'</b><span>'+ formatDateTime(result.rows[i].createDate) +'</span></div>'
	        		  if(result.rows[i].type == 1){	 //评论
	        			  htmlList += '<div class="newsListContent-right-text">'+ result.rows[i].content +'</div>'
	        		  }else if(result.rows[i].type == 2){ //点赞 
	        			  htmlList += '<div class="newsListContent-right-text"><img src="/oms-web/static/admin/images/imImages/zanNo.png"></div>'
	        		  }
	        		  htmlList += '</div></li>'	  
	        	  }
	        		if(status == 'Y' || status == 'M'){
	        			$('.shop_navThree .newsListContent-wrarpper').scrollTop(0);
	        			$('.newsListContent').html(htmlList).find('li:first').click();
	        		}else{
	        			$('.newsListContent').append(htmlList);
	        		}
	        	}else{
	        		$('.newsListContent').html("");
	        		$(".con_right .commentTwo").html("");
	        	}
	        	if(start < result.total) {
	        		pageFlagList = true;
	        	}else{
	        		pageFlagList = false;
	        	}
	        }
	      
	})
	
	function formatDateTime(inputTime) {    
	    var date = new Date(inputTime);  
	    var y = date.getFullYear();    
	    var m = date.getMonth() + 1;    
	    m = m < 10 ? ('0' + m) : m;    
	    var d = date.getDate();    
	    d = d < 10 ? ('0' + d) : d;    
	    var h = date.getHours();  
	    h = h < 10 ? ('0' + h) : h;  
	    var minute = date.getMinutes();  
	    var second = date.getSeconds();  
	    minute = minute < 10 ? ('0' + minute) : minute;    
	    second = second < 10 ? ('0' + second) : second;   
	    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
	};  
	
}

function clickList(friendsCode,event) {
$('.commentTwo').show();
$('.newsListContent >li').removeClass('active');
$(event).addClass('active');
	$.ajax({
		 type:"POST",
	        url:$('#ctx').val()+"/im/getFriendsInfo.do",
	        data:{code:friendsCode},
	        dataType:'JSON',
	        success:function(result){
	        	$('.commentOne').hide();
	        	$('commentTwo').show();
	    		var fHtml='';
//	    		fHtml += "<input type='hidden' class='nickNickName' value='"+ result.friendsInfo.nickName +"'>"
    				if(result.status == 2){	//发送成功
    					var headImg =getHeadAddress(result.headImg);    			
    					fHtml +='<div class="comment">';
    					fHtml += "<input class='pyqMemberNo' type='hidden' value="+ result.memberNo +">";
    					fHtml += "<input class='pyqMemberNoGm' type='hidden' value="+ result.memberNoGm +">";
    					if(result.optFlag==1){
    						fHtml += '<div class="comment_title"><img alt="" class="memHead" src="'+headImg+'"/>'
    					}else{
    						fHtml += '<div class="comment_title"><img alt="" onclick="headList(\''+result.memberNo+'\',\''+result.memberNoGm+'\',\''+result.merchantNo+'\',this)" class="memHead" src="'+headImg+'"/>'
    					}
    					fHtml += '<div class="bsPer"><p class="oneLine memWxnick">'+(result.memberName || result.nickName)+'</p>';
    					fHtml += '<p class="oneLine">'+new Date(Number(result.timestamp*1000)).format("yyyy-MM-dd hh:mm")+'</p>';
    					fHtml += '</div></div><div class="comment_content" id="xmpl">';	
    					if(result.type == "1" || result.type == "2"){ //1图文 2纯文字
    					fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+result.code+'" class="comment_text small " >'+result.content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';  
    					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
    					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
    						if(result.imgAddr != ""){
    							var img=result.imgAddr.split(",");
    							var imgs="";
    							if(result.imgStatus !=undefined){
    								imgs=result.imgStatus.split(",");
								}
    							fHtml +='<div class="comment_pic">'
    								for(var n=0;n<img.length;n++){
    									if(imgs!=""){
    										fHtml +='<img onclick="searchLagerImage(this,\''+ result.imgAddr +'\','+ n +',\''+imgs[n]+'\')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
    									}else{
    										fHtml +='<img onclick="searchLagerImage(this,\''+ result.imgAddr +'\','+ n +')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
    									}
    									
    								}
    							fHtml +='</div>';	
    						}
    					}else if(result.type == "3" || result.type == "5"){ // 3、5分享 
    						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+result.code+'" class="comment_text small " >'+result.content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';  
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
    						fHtml +='<div class="comment_pic">';
    						fHtml +='<a href="'+result.shareurl+'" target="view_window">'+result.shareurl+'</a>';	
    						fHtml +='</div>';	
    					}else if(result.type == "4"){ //4歌曲分享
    						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+result.code+'" class="comment_text small " >'+result.content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';  
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
    						fHtml +='<div class="comment_pic"></audio>';
//    					fHtml +='<audio src="'+friendsInfo[i].shareurl+'" controls="controls">';	
    						fHtml +='<a href="'+result.shareurl+'" target="view_window">'+result.shareurl+'</a>';
    						fHtml +='</div>';	
    						
    					}else if(result.type == "15"){ // 15视频
    						fHtml +='<p style="max-height:153px;overflow-y:hidden;" id="'+result.code+'" class="comment_text small " >'+result.content.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</p>';  
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,1)" >全文</a>';
        					fHtml +='<a href="javascript:void(0)" class="hidden" onclick="allArtic(this,0)">关闭</a>';
    						fHtml +='<div class="comment_pic">';
    						fHtml +='<video src="'+result.shareurl+'" controls="controls"  style="width:300px; height:200px;"></video>';	
    						fHtml +='</div>';	
    						
    					}
    					
    					fHtml +='<div class="comment_do"><input type="hidden" value="'+result.noWxShop+'" class="noWxShop">';
    					fHtml +='<input type="hidden" value="'+result.noWx+'" class="noWx"><input type="hidden" value="'+result.friendsId+'" class="friendsId">';
    					fHtml +='<input type="hidden" value="'+result.shopNo+'" class="shopNo">';
    					fHtml +='<input type="hidden" value="'+result.code+'" class="friendsCode">';
    					fHtml +='<div class="zan">';
    					if(result.likes!=undefined){
    						var fl = false; // 未点赞
    						var fLikes=result.likes;
    						for(var f = 0 ,len = fLikes.length; f < len ; f++){
    							if(fLikes[f].username == $('.shop_list .active .noWx').val()){
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
    					fHtml +='<div class="goComment goCommentOne"><textarea onkeydown="BindEnter(event)" class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
    					fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
    					fHtml +='<div class="comment_detail">'
    							/*if(friendsInfo[i].autoContent!=undefined && friendsInfo[i].autoContent!=""){
						fHtml +='<p>'+friendsInfo[i].nickName+'：'+ friendsInfo[i].autoContent+'</p>';
					}*/
    						var wxnickName=$(".shop_list .active .nickName").val();
    							if(result.comments!=undefined){
    								var comments=result.comments;
    								for(var c=0; c<comments.length;c++){
    									var params = {
    											memberNoGm:result.memberNoGm, // 评论导购会员号
    											memberNo:result.memberNo, // 客户编号
    											memberName:result.memberName, // 客户微信名
    											toCommentCode:comments[c].code, // 朋友圈id
    											shopNo:result.shopNo, // 终端号
    											shopWxNo:result.noWxShop, // 终端微信号
    											toWxNo:comments[c].noWxShop, // 被评论微信号
    											toWxName:comments[c].nickname, // 被评论微信名
    											toFriendsId:comments[c].friendsId, // 被评论朋友圈信息
    									};
    										//commentSvrID 是空为回复   非空为评论
    									if( comments[c].memberName && comments[c].tonickname){ //空
											fHtml +='<div class="replayListInfo"><p>';
											if(comments[c].memberNoGm){
												fHtml += '<span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>';
											}else{
												fHtml += '<span class="comNickName">'+comments[c].memberName+'</span>';
											}
											fHtml += '回复';
												fHtml += '<span class="comNickName">'+comments[c].tonickname+'</span>';
											
											fHtml += '：'+ comments[c].content.expression().expressionFan();
    										if(comments[c].nickname.indexOf(wxnickName)){
    											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
    										}
    										fHtml += '</p>';
    									}else{	//非空
    										if(comments[c].memberNoGm){
    											fHtml +='<div class="replayListInfo"><p><span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
    										}else{
    											fHtml +='<div class="replayListInfo"><p><span class="comNickName">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
    										}
    										
    										if(comments[c].nickname.indexOf(wxnickName)){
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
    					
    					fHtml +='</div>';
//    				fHtml +='<div class="goComment"><textarea class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
//    				fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
    					fHtml +='</div></div></div>'
    				}
    				
        			
        		$(".con_right .commentTwo").html(fHtml);
        		var imgWidth = Math.floor($('.commentTwo .comment_pic').width() * 0.32);
        		$('.comment_pic img').css('height',imgWidth+'px');
        		
        		$(fHtml).find(".comment_content .comment_text").each(function(){
    				var code=$(this).attr("id");
    				var pcode=$(".con_right .commentTwo ").find("#"+code).height();
    				if(pcode>=153){
    					$(".con_right .commentTwo ").find("#"+code).next().attr("class","show");
    				}else{
    					$(".con_right .commentTwo ").find("#"+code).next().attr("class","hidden");
    				}
	    			})
	        }
	})
	
}



function newsList() { //点击返回按钮
	$('.nav-wrapper .hideTelescopicButton').hide();
	$('.nav-wrapper .shop_navThree').hide();
	$('.sendF .shop_navTwo').show();
	$('.con_right .con_right_title').show();
	$('.newsListIcon').show();
	$(".sendF .f_center").show();
	$('.commentOne').show();
	$('.commentTwo').hide();
	$('.commentTwo').hide();
	$('.commentOne').show();
	$('.newsListIcon-wrapper').html("");
	$('.friendItem').show();
	$('.commentThree').hide();
	$('.friendItem li').removeClass('friendItemActive');
	$('.friendItem li:first').addClass('friendItemActive')
	var imgWidth = Math.floor($('.comment_pic:first').width() * 0.32);
	$('.comment_pic img').css('height',imgWidth+'px');
	$("#startDate").val("");
	$("#endDate").val("");
	$(".searchThTwo").val("");
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
	html += '<div class="textWarpper"><span></span><textarea class="conmmentInfo" id="textContent" rows="" onkeydown="BindEnter(event)"  cols="" placeholder="请输入文字"></textarea><span></span></div>';
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
		/*var noWx=$(event).parent().parent().find(".noWx").val();*/
		var friendsId=$(event).parent().parent().find(".friendsId").val();
		var shopNo=$(event).parent().parent().find(".shopNo").val();
		var noWx=$(".shop_list .shopDetail.active .noWx").val();
		var memberNo=$(event).parent().parent().parent().parent().find(".pyqMemberNo").val();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/toComment",
	        data:{toFriendsId:friendsId,shopNo:shopNo,shopWxNo:noWxShop,toConent:t,memberNo:memberNo},
	        dataType:'JSON',
	        success:function(result){
	        	if(result.state=="true"){
	        		var nickName= $(".shop_nav .shop_list .shopDetail.active .nickName").val();
	        		var h="<div class='replayListInfo'><p><span class='comNickName'>"+nickName+"</span>："+t+"</p></div>";
	        		$(event).parent().next().append(h);
	        		$(event).prev().val("");
	        		$('.goCommentOne').hide();
	        		
//	        		var hasActive = $('.friendItem .commentIcon ').hasClass('friendItemActive');
	        		var index = $('#topfS .friendItem li.friendItemActive').index();
	        		if(index==1){ //重新获取待评论客户信息
	        			getCommentCount();//重新获取待评论客户数
	        			$('.commentThree').html("");
	        			showCommentThree();
	        		}else if(index==2){
	        			getCommentCountTwo();//重新获取待回复客户数
	        			$('.commentFour').html("");
	        			showGetCommentFour();
	        		}
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

function BindEnter(event) { //回复禁止回车键
    event = event || window.event;
    if (event.keyCode == 13) {
        event.returnValue = false;
//        $('#textContent').val('').focus();
    }
}
function commentCommitAgin(event,data){
	var t = $(event).siblings('.textWarpper').children('.conmmentInfo').val()
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
		params.toConent = $(event).siblings('.textWarpper').children('.conmmentInfo').val(); // 评论内容
		var nickName= params.toWxName;
//		var nickNickName = $('.nickNickName').val();
		var nickNickName = $('.shopDetail.active .nickName').val();
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
	        		var html="<div class='replayListInfo'><p><span class='comNickName'>"+ nickNickName +"</span>回复<span class='comNickName'>"+data.toWxName +"</span>:"+params.toConent+"</p></div>"
	        		$(event).parents('.replayListInfo').parent().append(html);
	        		$(event).parents('.replayInfoSend').remove();
	        		var index = $('#topfS .friendItem li.friendItemActive').index();
	        		if(index==1){ //重新获取待评论客户信息
	        			getCommentCount();//重新获取待评论客户数
	        			$('.commentThree').html("");
	        			showCommentThree();
	        		}else if(index==2){
	        			getCommentCountTwo();//重新获取待回复客户数
	        			$('.commentFour').html("");
	        			showGetCommentFour();
	        		}
	        	}
	        }
	    });
	}
}

function zan(event){
//	var noWxShop=$(event).parent().parent().find(".noWxShop").val();
//	var noWxShop=$('.noWxShopName').val();
	var noWxShop=$(event).parents('.comment').find(".noWxShop").val();
	var noWx=$(event).parents('.comment').find(".noWx").val();
	var friendsId=$(event).parents('.comment').find(".friendsId").val();
	var shopNo=$(event).parents('.comment').find(".shopNo").val();
	var memberNo=$(event).parents('.comment').find(".pyqMemberNo").val();
	if($(event).hasClass("noZ")){
		$(event).removeAttr("onclick")
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/toImLike",
	        data:{
	        	toWxNo:noWx,
	        	toFriendsId:friendsId,
	        	shopNo:shopNo,
	        	shopWxNo:noWxShop,
	        	memberNo:memberNo
        	},
	        dataType:'JSON',
	        success:function(result){
	        	console.log(result);
	        	$(event).attr("onclick","zan(this)");
	        	if(result.state=="true"){
	        		var nickName= $(".shop_nav .shop_list .shopDetail.active .nickName").val();
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
//	        		getCommentCount();//重新获取待评价客户数
//	        		var hasActive = $('.friendItem .commentIcon').hasClass('friendItemActive')
	        		var index = $('#topfS .friendItem li.friendItemActive').index();
	        		if(index==1){ //重新获取待评论客户信息
	        			getCommentCount();//重新获取待评论客户数
	        			$('.commentThree').html("");
	        			showCommentThree();
	        		}else if(index==2){
	        			getCommentCountTwo();//重新获取待回复客户数
	        			$('.commentFour').html("");
	        			showGetCommentFour();
	        		}
	        	}
	        },
	        complete: function(XMLHttpRequest,status){ //请求完成后最终执行参数
	        	$(event).attr("onclick","zan(this)");
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

//改变备注
function changeMemName(event){
	var self = event;
	var params=window.location.search.substring(1).split("&");
	var memberNo='';
	var memberNoGm='';
	var merchantNo='';
	for(var i=0;i<params.length;i++){
		if(params[i].indexOf("memberNo")>-1 && params[i].split("=")[0]=="memberNo"){
			memberNo=params[i].split("=")[1];
		}else if(params[i].indexOf("memberNoGm")>-1){
			memberNoGm=params[i].split("=")[1];
		}else if(params[i].indexOf("merchantNo")>-1){
			merchantNo=params[i].split("=")[1];
		}
	}
	var name=$(event).val();
	
	if(memberNo==''){
		memberNo=$(".memberNo").val();
	}
	if(memberNoGm==''){
		memberNoGm=$(".memberNoGm").val();
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/updatePersonMember",
        data:{memberNoGm:memberNoGm,memberNo:memberNo,memberName:name},
        dataType:'text',
        success:function(result){
        	if(result=="success"){	
        	var nick = $(self).val();
        	$(".person_chat_info .person_chat_head .active").text(nick);
        	}
        }
    });
};

function More(){
	var beginDate=$("#pushDate").val();
	var merchantNo=$("#PumerchantNo").val();
	var memberNoGm=$("#PumemberNoGm").val();
	var pageNo=$("#PushpageNo").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/getMessagePush",
        data:{pageNo:Number(pageNo)+1,memberNoGm:memberNoGm,merchantNo:merchantNo},
        dataType:'JSON',
        success:function(result){
        	var rows=result.rows;
        	$("#PushpageNo").val(result.pageNo);
        	if(rows.length<20){
        		$(".more").hide();
        	}else{
        		for(var i=0;i<rows.length;i++){
        			var now=new Date(rows[i].pushDate).format("yyyy-MM-dd");
        			var html="";
        			if(now != beginDate){ //不同天
        				html +='<div class="recordTime text-center">'+new Date(rows[i].pushDate).format("yyyy/MM/dd")+'</div>';
        			}
        			beginDate=new Date(rows[i].pushDate).format("yyyy-MM-dd");
        			html +='<div class="recordDetail"><div class="bgbox"><div class="bgbox_time">'+new Date(rows[i].pushDate).format("hh:mm")+'</div>';
        			html +='<div class="boundary"><span class="cicle"></span><span class="cicle2"></span></div>	';
        			html += '<div class="recordInfo">';
        			if(rows[i].msgType=='1'){
        				html += '<img alt="" src="'+$("#ctxStatic").val()+'/admin/images/imImages/experience.png">';
        			}else if(rows[i].msgType=='2'){
        				html += '<img alt="" src="'+$("#ctxStatic").val()+'/admin/images/imImages/customerLine.png">';
        			}else if(rows[i].msgType=='3'){
        				html += '<img alt="" src="'+$("#ctxStatic").val()+'/admin/images/imImages/sms.png">';
        			}
        			html += '<div class="rdInDetail"><p class="clb tt">';
        			if(rows[i].msgType=='1'){
        				html += '优惠券推送';
        			}else if(rows[i].msgType=='2'){
        				html += '图文推送';
        			}else if(rows[i].msgType=='3'){
        				html += '短信推送';
        			}	
        			html += '</p><p>'+rows[i].msgContent+'</p></div></div></div></div>'	
					
        			$(".more").before(html);
        			
        			if(i==rows.length-1){
        				$("#pushDate").val(new Date(rows[i].pushDate).format("yyyy-MM-dd"));
        			}
        		}
        	}
        }
    });
}

function clicksBack() {
//	javascript:$('.nav-wrapper .perSonInfo').hide();$('.sendF .shop_navTwo').show();$('.shop_nav .shop_list').css('background','white')
	$('.nav-wrapper .perSonInfo').hide();
	$('.sendF .shop_navTwo').show();
	$(".sendF #materialListType").show();
	$('.shop_nav .shop_list').css('background','white');
	$('.commentOne').show();
	$('.commentTwo').hide();
	$('.friendItem').show();
	$('.commentThree').hide();
	$('.friendItem li').removeClass('friendItemActive');
	$('.friendItem li:first').addClass('friendItemActive')
	var imgWidth = Math.floor($('.comment_pic:first').width() * 0.32);
	$('.comment_pic img').css('height',imgWidth+'px');
}

function commentCount() { //待评论客户数
	var noWx = $('.active .noWx').val();
	$.ajax({
	  type:"POST",
	  url:$('#ctx').val()+"/im/loadPendingCommentCount.do",
	  data:{noWxShop:noWx},
	  dataType:'JSON',
	  success:function(result){
		  console.log(result.pendingCommentCount)
		  $('#commentMemberNoGms').val(result.memberNoGms);
		  $('#memberNos').val(result.memberNos);
		  $('#commentCountNum').val(result.pendingCommentCount);
		  htmlSpan = "";
	  	if(result.pendingCommentCount!=0){
		  	htmlSpan += "待评论<span>"+ result.pendingCommentCount +"</span>"
		  	$('.commentIcon').html(htmlSpan);
	  	}else{
	  		htmlSpan += "待评论";
	  		$('.commentIcon').html(htmlSpan);
	  		$(".con_right .commentThree").html(" ");
	  	}
	  	
	  }
	});
}


function showCommentThree(event) { //显示待评论
	$('.commentThree').show();
	$('.commentTwo').hide();
	$('.commentOne').hide();
	$('.commentFour').hide();
	$('.newsListIcon-wrapper').hide();
	$('.friendItem li').removeClass('friendItemActive');
	$('.commentIcon').addClass('friendItemActive');
	pageFlag=true;
	var memberNoGms = $('#commentMemberNoGms').val();
	var memberNos = $('#memberNos').val();
	var noWx = $('.active .noWx').val();
	if(event){
		pageFlag=true;
		scrollHeight=true;
		$("#pageNo").val(1);
		$('.sendF #friendScroll').scrollTop(0);
	}
	
	var pageNo=$("#pageNo").val();
	var fHtml = "";
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/loadPendingCommentFriends.do",
        data:{noWxShop:noWx,pageNo:pageNo},
        dataType:'JSON',
        success:function(result){
        	var friendsInfo=result.friendForumPage.list;
        	console.log(friendsInfo)
        	if(result.friendForumPage){
        		var friendsInfo=result.friendForumPage.list;
        		if(result.friendForumPage.count>0){       			
        			$("#count").val(result.friendForumPage.count);
        			$("#pageNo").val(result.friendForumPage.pageNo);
        			$("#pageSize").val(result.friendForumPage.pageSize);
        			var fHtml='';
//        			if(result.friendsInfo.length>0){
//        				$('.nickNickName').val(result.friendsInfo[0].nickName)
//        				fHtml += "<input type='hidden' class='nickNickName' value='"+ result.friendsInfo[0].nickName +"'>"
//        				$('.noWxShopName').val(result.friendsInfo[0].noWxShop);
//        			}
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
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')"  style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
        										fHtml +='<img onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
        									}else{
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +')" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
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
        					fHtml +='<div class="goComment goCommentOne"><textarea class="conmmentInfo" id="textContent" rows="" onkeydown="BindEnter(event)" cols="" placeholder="请输入文字"></textarea>';
        					fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='<div class="comment_detail">'
        							/*if(friendsInfo[i].autoContent!=undefined && friendsInfo[i].autoContent!=""){
    						fHtml +='<p>'+friendsInfo[i].nickName+'：'+ friendsInfo[i].autoContent+'</p>';
    					}*/
        						var wxnickName=$(".shop_list .active .nickName").val();
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
        											toWxNo:comments[c].username, // 被评论微信号
        											toWxName:comments[c].memberName, // 被评论微信名
        											toFriendsId:comments[c].friendsId, // 被评论朋友圈信息
        									};
        										//commentSvrID 是空为回复   非空为评论
        										if( comments[c].memberName && comments[c].tonickname){ //空
        											fHtml +='<div class="replayListInfo"><p>';
        											if(comments[c].memberNoGm){
        												fHtml += '<span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>';
        											}else{
        												fHtml += '<span class="comNickName">'+comments[c].memberName+'</span>';
        											}
        											fHtml += '回复';
        												fHtml += '<span class="comNickName">'+comments[c].tonickname+'</span>';
        											
        											fHtml += '：'+ comments[c].content.expression().expressionFan();
            										if(comments[c].nickname.indexOf(wxnickName)){
            											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
            										}
            										fHtml += '</p>';
            									}else{	//非空

            										if(comments[c].memberNoGm){
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}else{
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}
            										
            										if(comments[c].nickname.indexOf(wxnickName)){
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
        					
        					fHtml +='</div>';
//        				fHtml +='<div class="goComment"><textarea class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
//        				fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='</div></div></div>'
        				}
        				
        			}
        			if(event){
        				$(".con_right .commentThree").html(fHtml);    				
        			}else{
        				$(".con_right .commentThree").append(fHtml);
        			}
        			scrollHeight=true;
        			$(fHtml).find(".comment_content .comment_text").each(function(){
        				var code=$(this).attr("id");
        				var pcode=$(".con_right .commentThree ").find("#"+code).height();
        				if(pcode>=153){
        					$(".con_right .commentThree ").find("#"+code).next().attr("class","show");
        				}else{
        					$(".con_right .commentThree ").find("#"+code).next().attr("class","hidden");
        				}
        			})
        		}else{
//        			$(".con_right .comment").remove();
        		}
        		
        		var imgWidth = Math.floor($('.commentThree .comment_pic:first').width() * 0.32);
        		$('.comment_pic img').css('height',imgWidth+'px');
        	}
        }
        
    });
}

function hideCommentThree(){//隐藏待评价 显示全部
	pageFlag=true;
	scrollHeight=true;
	$('.commentThree').hide();
	$('.commentTwo').hide();
	$('.commentOne').show();
	$('.commentFour').hide();
	$('.newsListIcon-wrapper').show();
	$("#pageNo").val(allCommentPageNo);
	$('.friendItem li').removeClass('friendItemActive');
	$('.friendItem li:first').addClass('friendItemActive')
	$('.sendF #friendScroll').scrollTop(0);
	var imgWidth = Math.floor($('.comment_pic:first').width() * 0.32);
	$('.comment_pic img').css('height',imgWidth+'px');
}


function getCommentCount() { //待评论客户数
	var noWx = $('.active .noWx').val();
	$.ajax({
		type:"POST",
		url:$('#ctx').val()+"/im/loadPendingCommentCount.do",
		data:{noWxShop:noWx},
		 dataType:'JSON',
	        success:function(result){
	        	if(result.pendingCommentCount!=0){
	        		$('.commentIcon').html(" ");
	        		htmlSpan = "待评论<span>"+ result.pendingCommentCount +"</span>";
	    	  		$('.commentIcon').html(htmlSpan);
	        	}else{
	        		$('.commentIcon').html(" ");
	        		htmlSpan = "待评论";
	    	  		$('.commentIcon').html(htmlSpan);
	        	}
	        }

	})
}

function getCommentCountTwo() { //待回复客户数
	var noWx = $('.active .noWx').val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	$.ajax({
		type:"POST",
		url:$('#ctx').val()+"/im/loadToReplyFriendsCount.do",
		data:{noWxShop:noWx,shopNo:shopNo},
		 dataType:'JSON',
	        success:function(result){
	        	console.log(result.count)
	        	if(result.count!=0){
	        		$('.reply').html(" ");
	        		htmlSpan = "待回复<span>"+ result.count +"</span>";
	    	  		$('.reply').html(htmlSpan);
	        	}else{
	        		$('.reply').html(" ");
	        		htmlSpan = "待回复";
	    	  		$('.reply').html(htmlSpan);
	        	}
	        }

	})
};
function commentCountTwo() { //待回复客户数
	var noWx = $('.active .noWx').val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	$.ajax({
	  type:"POST",
	  url:$('#ctx').val()+"/im/loadToReplyFriendsCount.do",
	  data:{noWxShop:noWx,shopNo:shopNo},
	  dataType:'JSON',
	  success:function(result){
		  console.log(result.count)
//		  $('#commentMemberNoGms').val(result.memberNoGms);
//		  $('#memberNos').val(result.memberNos);
//		  $('#commentCountNum').val(result.pendingCommentCount);
		  htmlSpan = "";
	  	if(result.count!=0){
		  	htmlSpan += "待回复<span>"+ result.count +"</span>"
		  	$('.reply').html(htmlSpan);
	  	}else{
	  		htmlSpan += "待回复";
	  		$('.reply').html(htmlSpan);
	  		$(".con_right .commentFour").html(" ");
	  	}
	  	
	  }
	});
}

function showGetCommentFour(event) {
	$('.commentFour').show();
	$('.commentThree').hide();
	$('.commentTwo').hide();
	$('.commentOne').hide();
	$('.newsListIcon-wrapper').hide();
	$('.friendItem li').removeClass('friendItemActive');
	$('.reply').addClass('friendItemActive');
	pageFlag=true;
	var memberNoGms = $('#commentMemberNoGms').val();
	var memberNos = $('#memberNos').val();
	var noWx = $('.active .noWx').val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	if(event){
		pageFlag=true;
		scrollHeight=true;
		$("#pageNo").val(1);
		$('.sendF #friendScroll').scrollTop(0);
	}
	
	var pageNo=$("#pageNo").val();
	var fHtml = "";
	$.ajax({
		type:"POST",
		url:$('#ctx').val()+"/im/loadToReplyFriends.do",
		data:{noWxShop:noWx,shopNo:shopNo,pageNo:pageNo,toReplyFlag:1},
		dataType:'JSON',
	    success:function(result){
	    	var friendsInfo=result.friendForumPage.list;
        	console.log(friendsInfo);
        	if(result.friendForumPage){
        		var friendsInfo=result.friendForumPage.list;
        		if(result.friendForumPage.count>0){       			
        			$("#replyCount").val(result.friendForumPage.count);
        			$("#pageNo").val(result.friendForumPage.pageNo);
        			$("#pageSize").val(result.friendForumPage.pageSize);
        			var fHtml='';
//        			if(result.friendsInfo.length>0){
//        				$('.nickNickName').val(result.friendsInfo[0].nickName)
//        				fHtml += "<input type='hidden' class='nickNickName' value='"+ result.friendsInfo[0].nickName +"'>"
//        				$('.noWxShopName').val(result.friendsInfo[0].noWxShop);
//        			}
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
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')"  style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
        										fHtml +='<img onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +',\''+imgs[n]+'\')" alt="" src="" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" />';
        									}else{
//        										fHtml +='<div onclick="searchLagerImage(this,\''+ friendsInfo[i].imgAddr +'\','+ n +')" style="background:url('+ img[n] +') no-repeat center; background-size:cover;" >.</div>';
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
        					fHtml +='<div class="goComment goCommentOne"><textarea  class="conmmentInfo" id="textContent" onkeydown="BindEnter(event)" rows="" cols="" placeholder="请输入文字"></textarea>';
        					fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='<div class="comment_detail">'
        							/*if(friendsInfo[i].autoContent!=undefined && friendsInfo[i].autoContent!=""){
    						fHtml +='<p>'+friendsInfo[i].nickName+'：'+ friendsInfo[i].autoContent+'</p>';
    					}*/
        						var wxnickName=$(".shop_list .active .nickName").val();
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
        											toWxNo:comments[c].username, // 被评论微信号
        											toWxName:comments[c].memberName, // 被评论微信名
        											toFriendsId:comments[c].friendsId, // 被评论朋友圈信息
        									};
        										//commentSvrID 是空为回复   非空为评论
        										if( comments[c].memberName && comments[c].tonickname){ //空
        											fHtml +='<div class="replayListInfo"><p>';
        											if(comments[c].memberNoGm){
        												fHtml += '<span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>';
        											}else{
        												fHtml += '<span class="comNickName">'+comments[c].memberName+'</span>';
        											}
        											fHtml += '回复';
        												fHtml += '<span class="comNickName">'+comments[c].tonickname+'</span>';
        											
        											fHtml += '：'+ comments[c].content.expression().expressionFan();
            										if(comments[c].nickname.indexOf(wxnickName)){
            											fHtml += '<i onclick=\'showCommentTwo(this,'+ JSON.stringify(params) +')\' class="replyButton">回复</i>';
            										}
            										fHtml += '</p>';
            									}else{	//非空

            										if(comments[c].memberNoGm){
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName" onclick="headList(\''+comments[c].memberNo+'\',\''+comments[c].memberNoGm+'\',\''+comments[c].merchantNo+'\')">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}else{
            											fHtml +='<div class="replayListInfo"><p><span class="comNickName">'+comments[c].memberName+'</span>：'+ comments[c].content.expression().expressionFan();
            										}
            										
            										if(comments[c].nickname.indexOf(wxnickName)){
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
        					
        					fHtml +='</div>';
//        				fHtml +='<div class="goComment"><textarea class="conmmentInfo" rows="" cols="" placeholder="请输入文字"></textarea>';
//        				fHtml +='<input value="发送" type="button" onclick="commentCommit(this)" class="sendComment"></div>'
        					fHtml +='</div></div></div>'
        				}
        				
        			}
        			if(event){
        				$(".con_right .commentFour").html(fHtml);    				
        			}else{
        				$(".con_right .commentFour").append(fHtml);
        			}
        			scrollHeight=true;
        			$(fHtml).find(".comment_content .comment_text").each(function(){
        				var code=$(this).attr("id");
        				var pcode=$(".con_right .commentFour ").find("#"+code).height();
        				if(pcode>=153){
        					$(".con_right .commentFour ").find("#"+code).next().attr("class","show");
        				}else{
        					$(".con_right .commentFour ").find("#"+code).next().attr("class","hidden");
        				}
        			})
        		}else{
//        			$(".con_right .comment").remove();
        		}
        		
        		var imgWidth = Math.floor($('.commentFour .comment_pic:first').width() * 0.32);
        		$('.comment_pic img').css('height',imgWidth+'px');
        	}
	    }
	})
	
}
var dCycle="";
//删除发送的朋友圈
function deleteFC(event,fcode){
	var repeat=6;
	var msg ="确认删除该条朋友圈吗!";
		 options = {
			confirm:function(){
				$("#loadFlag").css("display","flex");
				$.ajax({
					  type:"POST",
					  url:$('#ctx').val()+"/im/delectFriends.do",
					  data:{friendsCode:fcode},
					  dataType:'JSON',
					  success:function(result){
						  console.log(result.count)
						  dCycle=setInterval(function() {    
							    if (repeat == 0) {
							    	window.clearInterval(dCycle);
							    	sureDF(event,fcode,repeat);
							    } else {
							        repeat--;
							        sureDF(event,fcode,repeat);
							    }
						  }, 5000);
					  }
				});
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
			isCancel:true, // 是否显示取消按钮
			msg:msg // 提示文字
		};
		 ConfirmBox(options);
	
}


function sureDF(event,fcode,repeat) { //确认是否删除
	$.ajax({
	  type:"POST",
	  url:$('#ctx').val()+"/im/findFriendsDeleteStatus.do",
	  data:{friendsCode:fcode},
	  dataType:'JSON',
	  success:function(result){
		 if(repeat == 0){
			 window.clearInterval(dCycle);
		 }
		 if(result == 1){		//删除成功
			 window.clearInterval(dCycle);
			 $("#loadFlag").css("display","none");
			 $(event).parent().parent().remove();
		 }else if(result == 0){	//删除失败
			 if(repeat == 0){
				 $("#loadFlag").css("display","none");
				 alert("删除朋友圈失败");
			 }
			 
		 }
	  }
   });
}




