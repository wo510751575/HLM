$(document).ready(function() {
	var firstwxNickname1=$("#wxNickname1").val();
	var merchantNoIndex=$("#merchantNoIndex").val();
	var sessionMerchantNo = sessionStorage.getItem('merchantNo')||''; //获取存在本地的merchantNo
	if(merchantNoIndex!=sessionMerchantNo){
		sessionStorage.clear();
	}
	var noWx = sessionStorage.getItem('noWx')||''; //获取存在本地的微信号码
	var index = sessionStorage.getItem('index')||0;
	var headAddress = sessionStorage.getItem('headAddress')||''; //获取存在本地的微信号码
	if(noWx == ''|| noWx ==null ){
		 noWx=$("#noWx1").val();
	}
	shopMenmber(noWx,firstwxNickname1,index,headAddress);
	
	if(index != ''&&index!=null){
		$('.shop_list').find('.shopDetail').eq(index).addClass('active');
	}else{
		$('.shop_list').find('.shopDetail').eq(0).addClass('active');
	}
	var int=self.setInterval("clock()",10000);
	$(".pagination select").change(function(){
		var n=$(this).find("option:selected").text();
		$("#pageSize").val(Number(n));
		var t=Number($(".pagination .total").text());
		var totalPage=Math.ceil(t/Number(n));
        $(".pagination .totalPage").text(totalPage);
        changePage("first");
	});

	$(".con_nav .clientG").change(function(){
		var ff=$(".con_nav .clientG").find("option:selected").val();
		if(ff=="TODAY" || ff=="WEEK" ||ff=="MONTH"){
			$("#startDate").val("");
			$("#endDate").val("");
		}
		changePage();
	});
	$(".con_nav .guideG").change(function(){
		changePage();
	});

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

	localStorage.setItem("headImgLeft", $(".left_nav .member img").attr("src"));
	localStorage.setItem("indexListHref",window.location.href);

	$(".pageShow").change(function(){
		var total=$(".pagination .totalPage").text();
		var page=$(".pageShow").val();
		var reg= /^[0-9]*[1-9][0-9]*$/;
		if(reg.test(page)){
			if(Number(page)<=Number(total) && Number(page)>0){
				$("#pageNo").val( Number(page));

			}else{
				$(".pageShow").val(1);
				$("#pageNo").val( 1);
			}
			changePage();
		}else{
			if(Number(page)>0 && Number(page)<=Number(total)){
				$(".pageShow").val(Math.floor(Number(page)));
				$("#pageNo").val( Math.floor(Number(page)));
			}else{
				$(".pageShow").val(1);
				$("#pageNo").val( 1);
			}
			changePage();
		}
	});

	


});

/**
 * 置顶用户
 * @param memberNo
 * @param roomType
 * @param setType
 * @returns
 */
function setUpUser(memberNo,roomType,setType){

	var noWx = sessionStorage.getItem('noWx')||''; //获取存在本地的微信号码
	var index = sessionStorage.getItem('index')||0;
	var headAddress = sessionStorage.getItem('headAddress')||''; //获取存在本地的微信号码
	var firstwxNickname1=$("#wxNickname1").val();
	if(noWx == ''|| noWx ==null ){
		 noWx=$("#noWx1").val();
	}
	var roomCode = '';
	if(roomType == 2 || roomType == '2'){
		roomCode = memberNo;
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/member/setUpUser.do",
        data:{memberNo:memberNo,roomCode:roomCode,roomType:roomType,setType:setType,noWxGm:noWx},
        dataType:'JSON',
        success:function(res){
        	
        	shopMenmber(noWx,firstwxNickname1,index,headAddress);
        }
	});
}

function clock()
{
	var noWxList="";
	$(".shop_nav .shop_list .shopDetail ").each(function(){
		noWxList +=$(this).find(".noWx").val()+",";
	});
	//客户未读数
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/unreadPersonCount",
        data:{noWxList:noWxList.substring(0,noWxList.length-1)},
        dataType:'JSON',
        success:function(result){
        	if(result!=null){
        		for(var i=0;i<result.length;i++){
                	$(".shop_nav .noWx").each(function(){
                		if($(this).val()==result[i].NO_WX){
                			$(this).parent().find(".pao").text(result[i].UNREAD_COUNT);
                			$($(this).parent().find(".pao")).css("visibility","visible");
                		}
                	})
                }
        	}

        }
    });

	//客户未读消息数
	var memberNoList="";
	$(".con_nav .person .shopDetail ").each(function(){
		memberNoList +=$(this).find(".clientMemNo").val() +",";
	});
	
//	var nW=$(".shop_nav .shop_list .shopDetail.active .noWx").val();
	var nW=sessionStorage.getItem('noWx');
	if(nW == null || nW == ''){
		return;
	};
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/unreadCountByMember",
        data:{memberNoList:memberNoList.substring(0,memberNoList.length-1),noWxShop:nW},
        dataType:'JSON',
        success:function(result){
        	var members ="";
        	var map = new Map();
        	for(var i=0;i<result.length;i++){
        		members+=result[i].memberNo+",";
        		map.set(result[i].memberNo,result[i].unreadCount);
        	}
        	
        	$(".con_nav .clientMemNo").each(function(){
        		if(members.search($(this).val())!=-1){
        			$(this).parent().find(".pao").text(map.get($(this).val()));
        			$($(this).parent().find(".pao")).css("visibility","visible");
        			
//            			var $top = $(this).parent(".shopDetail")	//新消息置顶
//            			$(".person").prepend($top)
        		}else{
        			//没有数量就是0
                	$(this).parent().find(".pao").text("");
                    $($(this).parent().find(".pao")).css("visibility","hidden");
        		}
        	});
        }
    });

}

function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();
	var pmTypeCode=$(".shop_nav .storeClient ").find("option:selected").val();

	//清除所有的session
	sessionStorage.clear();
	window.location.href=$('#ctx').val()+"/im/list?searchWords="+searchWords+"&pmTypeCode="+pmTypeCode;
}


/*重置查询*/
function resetQuery(){
	$(".shop_list .active").click();
}

/*查找终端好友*/
function shopMenmber(noWx,wxNickname,paramNum,headAddress,event){
	$("#startDate").val("");
	$("#endDate").val("");
	console.log(noWx,paramNum);
	sessionStorage.setItem('noWx',noWx); //储存点击的微信号于本地
	sessionStorage.setItem('index',paramNum); //储存点击的序号于本地
	sessionStorage.setItem('shopHeadImg',headAddress); //储存点击的序号于本地
	
	var pageNo=sessionStorage.getItem(noWx) ||1;	//获取页数
	if(pageNo==1){
		sessionStorage.setItem(noWx,1);
	}

	if(noWx==undefined){
		noWx="";
		return false;
	}
	if(event!="" && event!=undefined){
		$(event).addClass("active");
		$(event).siblings().removeClass("active");
	}
	$(".social_info .shoptid"+paramNum).show();
	$(".social_info .shoptid"+paramNum).siblings().css("display","none");

	var n=$(".pagination select").find("option:selected").text();
	$("#pageSize").val(Number(n));
	$(".pagination .pageShow").val(1);

	$(".con_nav .clientG option").each(function(i){
		$(this).prop("selected",false);
		if(i==0){
			$(this).prop("selected",true);
		}
	});

	$(".con_nav .guideG option").each(function(i){
		$(this).prop("selected",false);
		if(i==0){
			$(this).prop("selected",true);
		}
	});
	$(".con_nav .searchTh").val("");
	//客户分组条件
	var clientG= sessionStorage.getItem(noWx+"PclientG");
	$(".con_nav .clientG").find("option").each(function(){
		if($(this).val()== clientG){
			$(this).attr("selected","selected");
		}
	});
	//导购筛选
	var guideG= sessionStorage.getItem(noWx+"PguideG");
	$(".con_nav .guideG").find("option").each(function(){
		if($(this).val()== guideG){
			$(this).attr("selected","selected");
		}
	});
	//自主筛选
	var keyword= sessionStorage.getItem(noWx+"PkeyWord");
	if(keyword!="null"){
		$(".con_nav .searchTh").val(keyword);
	}

	var startDate=sessionStorage.getItem(noWx+"PstartDate");
	var endDate=sessionStorage.getItem(noWx+"PendDate");
	if(startDate!="null"){
		$("#startDate").val(startDate);
	}
	if(endDate!="null"){
		$("#endDate").val(endDate);
	}
	//客户分组条件
	var clientG= $(".con_nav .clientG").find("option:selected").val();
	//导购筛选
	var guideG= $(".con_nav .guideG").find("option:selected").val();
	//自主筛选
	var keyword=$(".con_nav .searchTh").val();
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();

	$.ajax({
        type:"POST",
        url: $('#ctx').val()+"/im/personMemberList",

        data:{noWx:noWx,pageSize:Number(n),pageNo:pageNo, typeCode:clientG,memberNo:guideG,keyWord:keyword,startTime:startDate,endTime:endDate},
        dataType:'JSON',
        success:function(res){
        	$(".con_search .wxName").text($(".shop_list .shopDetail.active .wxNickname").val());
        	$("#todayCount").text(res.todayCount);
        	var guideM = res.guidMembers;
        	var friendPoint=res.friendPoint;
        	if(guideM!=undefined){
        		var opHtml='<option value="">全部</option>';
        		for(var n=0;n<guideM.length;n++){
        			if(guideG == guideM[n].memberNo){
        				opHtml+='<option value="'+guideM[n].memberNo+'" selected="selected">'+guideM[n].memberName+'</option>';
        			}else{
        				opHtml+='<option value="'+guideM[n].memberNo+'">'+guideM[n].memberName+'</option>';
        			}

        		}
        		$(".guideG option").remove();
        		$(".guideG").append(opHtml);
        	}

        	var result = res.page;
            $(".condition_all .kehuNum").text(res.kehuNum);		//客户数
            $(".condition_all .chatRoomNum").text(res.chatRoomNum);	//群聊数
            
            $(".pagination .total").text(result.count);
            var totalPage=Math.ceil(result.count/result.pageSize);
            $(".pagination .totalPage").text(totalPage);
            $(".pagination .pageShow").val(result.pageNo);
        	var currPage=$(".pagination .pageShow").val();
        	 if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
             	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
         	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
         		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
         	}else  if(Number(currPage)==1  && totalPage>1){
             	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
         	}else{
         		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png")
         	}

            $("#pageNo").val(result.pageNo);
            $("#pageSize").val(result.pageSize);
            var temiNo=$($(".shop_list .shopDetail.active  .shopNum p")[0]).text();
            var html='';
            var info=result.list;
            var haveFlag=false;		//当页是否有选中的人
            if(info!=undefined){
            	 for(var i=0;i<info.length;i++){
                 	var newDate =new Date();
                 	newDate.setTime(info[i].createDate);
                 	var month=newDate.getMonth()+1;
                 	/*添加方式*/
                 	var addtype="";
             		if(1==info[i].addType){
             			addtype="导购扫码添加";
             		}else if(2==info[i].addType){
             			addtype="客户扫码添加";
             		}else if(3==info[i].addType){
             			addtype="导购手动新增";
             		}else if(4==info[i].addType){
             			addtype="微信自动导入";
             		}else if(5==info[i].addType){
             			addtype="手机号添加";
             		}else if(6==info[i].addType){
             			addtype="微信号添加";
             		}else if(7==info[i].addType){
             			addtype="QQ号添加";
             		}

             		/*if(i==0){
                 		getPersonInfo(info[i].code,info[i].pmTypeCode);
                 		html +='<div class="shopDetail active" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                 	}else{
	                 		html +='<div class="shopDetail" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                 	}*/

             			var codeIndex = sessionStorage.getItem('code'+noWx)||''; //获取储存在本地的code
                 		if(codeIndex=="" || codeIndex ==null){
                         	if(i==0){
                         		getPersonInfo(info[i].code,info[i].pmTypeCode);
                         		
                         		 if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                          			html +='<div class="shopDetail active" style="background: #ccc" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }else{
                                	  html +='<div class="shopDetail active" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }
                         		
                         	}else{
                         		
                         		if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                             		html +='<div class="shopDetail" style="background: #ccc" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }else{
                               		html +='<div class="shopDetail" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }
                         	}
                 		}else{
                 			if(codeIndex == info[i].code){
                 				haveFlag=true;
                 				var code = sessionStorage.getItem('code'+noWx);
                     			var pmTypeCode = sessionStorage.getItem('pmTypeCode'+noWx);
                     			getPersonInfo(code,pmTypeCode);
                     			
                     			if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                         			html +='<div class="shopDetail active" style="background: #ccc"  id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';

                                  }else{
                           			html +='<div class="shopDetail active"   id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }
                     		}else{
                     			var code = sessionStorage.getItem('code'+noWx);
                     			var pmTypeCode = sessionStorage.getItem('pmTypeCode'+noWx);
                         		
                         		if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                         			html +='<div class="shopDetail" style="background: #ccc" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                                  }else{
                                	  html +='<div class="shopDetail" id="'+ info[i].code +'" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                               	 }
                         	}
                 			if(i == info.length-1 && !haveFlag){
                 				getPersonInfo(info[0].code,info[0].pmTypeCode);
                 			}
                 		}

            
                 	html +='<input type="hidden" value="'+info[i].memberNo+'" data-memberNo="'+info[i].memberNo+'" class="clientMemNo"><input type="hidden" value="'+info[i].memberNoGm+'" class="memberNoGm"><input type="hidden" value="" class="chatTimeBegin">'
                 	html +='<div class="shopInfo"><img alt="" src="'+getHeadAddress(info[i].headAddress)+'">';
//                 	html +='<div class="shopInfo"><img alt="" src="'+info[i].headAddress+'">';
                    html +='<div class="pao"></div></div>';
                    if(info[i].chatRoomFlag){		//群聊
                        html +='<div class="shopInfo imshopName qun">';
                		html +='<p class="oneLine">'+info[i].nickNameWx+'(<span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p>';
//						html +='<p class="chat_name">(所属导购：'+info[i].memberNameGm+')</p>';
						html +='</div><div class="shopInfo shopNum">';
              			html +='<ul class="qun"><li><a href="'+$('#ctx').val()+'/im/chatroom/chatroom?code='+info[i].code+'">';
              			html +='接入客户</a></li><li><a href="'+$('#ctx').val()+'/im/chatroom/chatroomHistory?code='+info[i].code+"&provinceCode="+$('.province  option:selected').val()+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'">历史消息</a></li>';
              			
              			
              			
              			
              			 if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
              				html +='<li><a href="javascript:setUpUser(\''+info[i].code+'\',2,0);" style="color:#000;">取消置顶</a></li>';
                         }else{
                        	 html +='<li><a href="javascript:setUpUser(\''+info[i].code+'\',2,1);" style="color:#000;">置顶</a></li>';
                         }
              			 
              			html +='</ul></div></div>'
                     }else{		//普通用户
                    	html +='<div class="shopInfo imshopName">'
                    	html +='<p class="oneLine">'+info[i].nickNameWx+'(<span class="editMemberName">'+info[i].memberName +'</span><span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p><p class="chat_name">';
              			html +='(客户分组：'+info[i].pmTypeName+'；添加方式：'+addtype+'；<span>;'+getPhone(info[i].mobile)+'</span>)</p></div><div class="shopInfo shopNum">';
              			html +='<ul><li><a href="'+$('#ctx').val()+'/im/chat?memberNo='+info[i].memberNo+'&noWxGm='+info[i].noWxGm+'&'+'code='+info[i].code+'">';
              			html +='接入客户</a></li><li><a href="'+$('#ctx').val()+'/im/historyChat?memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code'+info[i].code+"&provinceCode="+$('.province  option:selected').val()+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'">历史消息</a></li>';
              			//电话
              			/*html +='<li onclick="guideMobile(\''+info[i].memberNoGm+'\',\''+info[i].memberNameGm+'\')">导购电话</li>';*/
              			html +='<li><a title="去评论或点赞朋友圈" href="'+$('#ctx').val()+'/im/toSignalFriendData?memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&temiNo='+temiNo+'&noWxShop='+noWx+'">朋友圈</a>';
              			/*for(var f in friendPoint){
              				if(f==info[i].memberNo && friendPoint[f]){
              					html +="<span></span>";
              				}
              			}*/
              			html +="</li>";
                        if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                        	html +='<li ><a href="javascript:setUpUser(\''+info[i].memberNo+'\',1,0);" style="color:#000;">取消置顶</a></li>';
                        }else{
              			    html +='<li><a href="javascript:setUpUser(\''+info[i].memberNo+'\',1,1);" style="color:#000;">置顶</a></li>';
                        }
              			html +='</ul></div></div>';
                     }

                 }
            }else{
            	$(".basicInfo .memWxnick").text("");
            	$(".basicInfo .memWxNo").text("");
            	$(".basicInfo .memWxNo").attr("title","");
            	$(".basicInfo .memAppName").val("");
            	$(".basicInfo .memAddr").text("");
            	$(".basicInfo .pmtype").text("");
            	$(".basicInfo .guideName").text("");
            	$(".basicInfo .guideMobile").text("");
            	$(".basicInfo .needPro").text("");
            	$(".basicInfo .memSrc").text("");
            	$(".basicInfo .addtype").text("");
            	$(".basicInfo .followNum").text("");
            	$(".basicInfo .keepNum").text("");
            	$(".basicInfo .sucNum").text("");
            	$(".basicInfo .recodTime").text("");
            	$(".basicInfo .memHead").attr("src","");
            	$(".basicInfo .peculiarity").text("");
            	$(".basicInfo .houses").text("");
            	$(".basicInfo .remark").text("");
            }

            $(".person").html(html);

            if(!haveFlag){
            	$($(".person .shopDetail")[0]).addClass("active");
 			}
            //查找未读信息；
            clock();
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
        	var hh='<p class="chat_name sheJiaoName offline">(离线)</p>';
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

            console.log(result);
        }
    });
}
	//添加手机号字段
function getPhone(phone) {
	var phoneNumber = "";
	if(phone == undefined || phone == ""){
		phoneNumber = "";
	}else{
		phoneNumber = "手机号:"+phone
	}
	return phoneNumber;

}

function getPersonInfo(code,pmTypeCode,event){

	var noWxShop=$(".shop_list .shopDetail.active .noWx").val();
	sessionStorage.setItem('code'+noWxShop,code); //储存点击的code于本地
	sessionStorage.setItem('pmTypeCode'+noWxShop,pmTypeCode); //储存点击的pmTypeCode于本地
	$("#code").val(code);
	var memberfrom = {
			SHOP_SACN:"到店",
			NO_SHOP_SACN:"未到店",
			OLD:"老客户转介绍",
			NET:"网络推广",
			OTHER:"其他",
			WITHOUT:"无",
		};
	if(event!=undefined){
		$(event).addClass("active");
		$(event).siblings().removeClass("active");
	}
	if(pmTypeCode == 'CHATROOM'){		//群聊
		$("#gropInfo").show();
		$("#pInfo").hide();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/chatroom/form",
	        data:{code:code},
	        dataType:'JSON',
	        success:function(result){
	        	if(result.data){
	        		$(".basicInfo .memWxnick").text(result.data.roomNickName);
                	$(".basicInfo .memWxnick").attr("title",result.data.roomNickName);
                	$(".basicInfo .recodTime").text((new Date(result.data.createDate).format('yyyy-MM-dd hh:mm:ss')));
        			$(".basicInfo .memHead").attr("src",getHeadAddress(result.data.headUrl));
	        	}
	        }
		});
	}else{
		$("#gropInfo").hide();
		$("#pInfo").show();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val()+"/im/findPersonMember",
	        data:{code:code,pmTypeCode:pmTypeCode},
	        dataType:'JSON',
	        success:function(result){
	        	if(result!=null){
	        		/*添加方式*/
	            	var addtype="";
	            	if(1==result.addType){
	         			addtype="导购扫码添加";
	         		}else if(2==result.addType){
	         			addtype="客户扫码添加";
	         		}else if(3==result.addType){
	         			addtype="导购手动新增";
	         		}else if(4==result.addType){
	         			addtype="微信自动导入";
	         		}else if(5==result.addType){
	         			addtype="手机号添加";
	         		}else if(6==result.addType){
	         			addtype="微信号添加";
	         		}else if(7==result.addType){
	         			addtype="QQ号添加";
	         		}
	        		/*客户来源*/
	        		var memfrom="";
	            	for(var item in memberfrom){
	            		if(item==result.memberSrc){
	            			memfrom=memberfrom[item];
	            		}
	            	}

	            	$(".basicInfo .memWxnick").text(result.nickNameWx);
	            	if(result.noWxAlias!=undefined & result.noWxAlias!="" & result.noWxAlias!=null){
	            		$(".basicInfo .memWxNo").text(result.noWxAlias);
	                	$(".basicInfo .memWxNo").attr("title",result.noWxAlias);
	            	}else{
	            		$(".basicInfo .memWxNo").text(result.noWx);
	                	$(".basicInfo .memWxNo").attr("title",result.noWx);
	            	}
	            	$(".basicInfo .memAppName").val(result.memberName);

	            	$(".basicInfo .memAddr").text(result.address);

	            	$(".basicInfo .pmtype").text(result.pmTypeName);

	            	$(".basicInfo .guideName").text(result.memberNameGm);
	            	$(".basicInfo .guideMobile").text(result.mobileGm);

	            	$(".basicInfo .needPro").text("");
	            	if(result.bomName!=undefined & result.bomName!="" & result.bomName!=null){
		            	$(".basicInfo .needPro").text(result.bomName);
		            	$(".basicInfo .needPro").attr("title",result.bomName);
	            	}

	            	$(".basicInfo .peculiarity").text("");
	            	if(result.clientSpecial!=undefined & result.clientSpecial!="" & result.clientSpecial!=null){
		            	$(".basicInfo .peculiarity").text(result.clientSpecial);
		            	$(".basicInfo .peculiarity").attr("title",result.clientSpecial);
	            	}

	            	$(".basicInfo .houses").text("");
	            	if(result.houses!=undefined & result.houses!="" & result.houses!=null){
		            	$(".basicInfo .houses").text(result.houses);
		            	$(".basicInfo .houses").attr("title",result.houses);
	            	}

	            	$(".basicInfo .remark").text("");
	            	if(result.remark!=undefined & result.remark!="" & result.remark!=null){
		            	$(".basicInfo .remark").text(result.remark);
		            	$(".basicInfo .remark").attr("title",result.remark);
	            	}

	            	$(".basicInfo .memSrc").text(memfrom);
	            	$(".basicInfo .addtype").text(addtype);
	            	$(".basicInfo .followNum").text(result.followNum);
	            	$(".basicInfo .keepNum").text(result.keepNum);
	            	$(".basicInfo .sucNum").text(result.successNum);
	            	$(".basicInfo .recodTime").text((new Date(result.createDate).format('yyyy-MM-dd hh:mm:ss')));
	            	$(".basicInfo .cycle").text(result.remark4);

//	            	console.log(result.headAddress)
	            	if(result.headAddress.indexOf('http')==-1) {
	            		var head=getHeadAddress(result.headAddress);
	            		$(".basicInfo .memHead").attr("src",head);
//	            		$(".basicInfo .memHead").attr("src","../images/introduce/file.png");
	            	}else{
	            		$(".basicInfo .memHead").attr("src",getHeadAddress(result.headAddress));
	            	}
	        	}

	        }
		});
	}

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
		changePage("first");
	}
}

function changePage(info){

	var pageNo=$("#pageNo").val();
	var pageSize=$("#pageSize").val();

	if(info=="first"){
		pageNo=1;
	}else if(info=="prev"){
		if(pageNo>1){
			pageNo=pageNo-1;
		}
	}else if(info=="next"){
		var last=$(".pagination .totalPage").text();
		if(pageNo<Number(last)){
			pageNo=Number(pageNo)+1;
		}
	}else if(info=="last"){
		pageNo=$(".pagination .totalPage").text();
	}
	var noWx=$(".shop_list .shopDetail.active").find(".noWx").val();
	sessionStorage.setItem(noWx,pageNo);
	//客户分组条件
	var clientG= $(".con_nav .clientG").find("option:selected").val();
	//导购筛选
	var guideG= $(".con_nav .guideG").find("option:selected").val();
	//自主筛选
	var keyword=$(".con_nav .searchTh").val();
	if(clientG=="TODAY" || clientG=="WEEK" ||clientG=="MONTH"){
		$("#startDate").val("");
		$("#endDate").val("");
		$("#startDate").attr("disabled","disabled");
		$("#endDate").attr("disabled","disabled");
	}else{
		$("#startDate").removeAttr("disabled");
		$("#endDate").removeAttr("disabled");
	}
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();

	//	保存查询条件
	sessionStorage.setItem(noWx+"PkeyWord",keyword);
	sessionStorage.setItem(noWx+"PstartDate",startDate);
	sessionStorage.setItem(noWx+"PendDate",endDate);
	sessionStorage.setItem(noWx+"PclientG",clientG);
	sessionStorage.setItem(noWx+"PguideG",guideG);

	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/personMemberList",
        data:{noWx:noWx,pageNo:pageNo,pageSize:pageSize,typeCode:clientG,memberNo:guideG,keyWord:keyword,startTime:startDate,endTime:endDate},
        dataType:'JSON',
        success:function(res){
        	var result = res.page;
        	var friendPoint=res.friendPoint;
            $(".pagination .pageShow").val(result.pageNo);

            $(".condition_all .kehuNum").text(res.kehuNum);		//客户数
            $(".condition_all .chatRoomNum").text(res.chatRoomNum);	//群聊数
            
            $(".pagination .total").text(result.count);
            var totalPage=Math.ceil(result.count/result.pageSize);
            $(".pagination .totalPage").text(totalPage);

            var currPage=$(".pagination .pageShow").val();
            if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
            	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
        	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
        		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
        	}else  if(Number(currPage)==1  && totalPage>1){
            	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
        	}else{
        		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png")
        	}
            $("#pageNo").val(result.pageNo);
            $("#pageSize").val(result.pageSize);
            var temiNo=$($(".shop_list .shopDetail.active  .shopNum p")[0]).text();
            var html='';
            var info=result.list;
            if(info!=undefined){
            	 for(var i=0;i<info.length;i++){
                 	var newDate =new Date();
                 	newDate.setTime(info[i].createDate);

                 	var month=newDate.getMonth()+1;
                 	/*添加方式*/
                 	var addtype="";
                 	if(1==info[i].addType){
             			addtype="导购扫码添加";
             		}else if(2==info[i].addType){
             			addtype="客户扫码添加";
             		}else if(3==info[i].addType){
             			addtype="导购手动新增";
             		}else if(4==info[i].addType){
             			addtype="微信自动导入";
             		}else if(5==info[i].addType){
             			addtype="手机号添加";
             		}else if(6==info[i].addType){
             			addtype="微信号添加";
             		}else if(7==info[i].addType){
             			addtype="QQ号添加";
             		}
             		var code=$("#code").val();
             		if(i==0){
                 		getPersonInfo(info[i].code,info[i].pmTypeCode);
                 		
                 		
                 		 if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                 			html +='<div class="shopDetail active" style="background: #ccc" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                         }else{
                        	 html +='<div class="shopDetail active" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                         }
                 	}else{
                 		if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
                 			html +='<div class="shopDetail" style="background: #ccc" onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                         }else{
                        	 html +='<div class="shopDetail"  onclick="getPersonInfo(\''+info[i].code+'\',\''+info[i].pmTypeCode+'\',this)">';
                         }
                 		
                 	}

             		html +='<input type="hidden" value="'+info[i].memberNo+'" data-memberNo="'+info[i].memberNo+'" class="clientMemNo"><input type="hidden" value="'+info[i].memberNoGm+'" class="memberNoGm"><input type="hidden" value="" class="chatTimeBegin">'
                 	html +='<div class="shopInfo"><img alt="" src="'+getHeadAddress(info[i].headAddress)+'">';
                    html +='<div class="pao"></div></div>';

                    if(info[i].chatRoomFlag){		//群聊
						html +='<div class="shopInfo imshopName qun">';
                		html +='<p class="oneLine">'+info[i].nickNameWx+'(<span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p>';
//						html +='<p class="chat_name">(所属导购：'+info[i].memberNameGm+')</p>';
              			html +='</div><div class="shopInfo shopNum">';
              			html +='<ul class="qun"><li><a href="'+$('#ctx').val()+'/im/chatroom/chatroom?code='+info[i].code+'">';
              			html +='接入客户</a></li><li><a href="'+$('#ctx').val()+'/im/chatroom/chatroomHistory?code='+info[i].code+'">历史消息</a></li>';
              		
              			 if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
               				html +='<li><a href="javascript:setUpUser(\''+info[i].code+'\',2,0);" >取消置顶</a></li>';
                          }else{
                         	 html +='<li ><a href="javascript:setUpUser(\''+info[i].code+'\',2,1);" >置顶</a></li>';
                          }
              			html +='</ul></div></div>'
                     }else{		//普通用户
                    	html +='<div class="shopInfo imshopName">'
                    	html +='<p class="oneLine">'+info[i].nickNameWx+'(<span class="editMemberName">'+info[i].memberName +'</span><span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p><p class="chat_name">';
              			html +='(客户分组：'+info[i].pmTypeName+'；添加方式：'+addtype+'<span>;'+getPhone(info[i].mobile)+'</span>)</p></div><div class="shopInfo shopNum">';
              			html +='<ul><li><a href="'+$('#ctx').val()+'/im/chat?memberNo='+info[i].memberNo+'&noWxGm='+info[i].noWxGm+'&'+'code='+info[i].code+'">';
              			html +='接入客户</a></li><li><a href="'+$('#ctx').val()+'/im/historyChat?memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code'+info[i].code+"&provinceCode="+$('.province  option:selected').val()+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'">历史消息</a></li>';
              			//电话
              			/*html +='<li onclick="guideMobile(\''+info[i].memberNoGm+'\',\''+info[i].memberNameGm+'\')">导购电话</li>';*/
              			html +='<li><a title="去评论或点赞朋友圈" href="'+$('#ctx').val()+'/im/toSignalFriendData?memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&temiNo='+temiNo+'&noWxShop='+noWx+'">朋友圈</a>';
              			/*for(var f in friendPoint){
              				if(f==info[i].memberNo && friendPoint[f]){
              					html +="<span></span>";
              				}
              			}*/
              			html +="</li>";
                        
              	//		html +='<li onclick="getGmNoWxPersonal(\''+info[i].code+'\',\''+info[i].memberNo+'\',\''+info[i].memberNoGm+'\',\''+info[i].merchantNo+'\')">导购微信</li>";
              			
              			
              			
              			 if(info[i].setUpUser == 1 || info[i].setUpUser == '1'){
              				   html +='<li><a href="javascript:setUpUser(\''+info[i].memberNo+'\',1,0);" >取消置顶</a></li>';
                           }else{
                        	   html +='<li><a href="javascript:setUpUser(\''+info[i].memberNo+'\',1,1);" >置顶</a></li>';
                           }
              			html +='</ul></div></div>';
                     }
                 }
            }else{
            	$(".basicInfo .memWxnick").text("");
            	$(".basicInfo .memWxNo").text("");
            	$(".basicInfo .memWxNo").attr("title","");
            	$(".basicInfo .memAppName").val("");
            	$(".basicInfo .memAddr").text("");
            	$(".basicInfo .pmtype").text("");
            	$(".basicInfo .guideName").text("");
            	$(".basicInfo .guideMobile").text("");
            	$(".basicInfo .needPro").text("");
            	$(".basicInfo .memSrc").text("");
            	$(".basicInfo .addtype").text("");
            	$(".basicInfo .followNum").text("");
            	$(".basicInfo .keepNum").text("");
            	$(".basicInfo .sucNum").text("");
            	$(".basicInfo .recodTime").text("");
            	$(".basicInfo .memHead").attr("src","");
            	$(".basicInfo .peculiarity").text("");
            	$(".basicInfo .houses").text("");
            	$(".basicInfo .remark").text("");

            }

            $(".person").html(html);
          //查找未读信息；
            clock();
        }
    });
}



