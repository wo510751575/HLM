/**
 * 预报名JS
 * @returns
 */
$(document).ready(function() {
	var firstwxNickname1=$("#wxNickname1").val();
	var merchantNoIndex=$("#merchantNoIndex").val();
	var sessionMerchantNo = sessionStorage.getItem('premerchantNo')||''; //获取存在本地的merchantNo
	if(merchantNoIndex!=sessionMerchantNo){
		sessionStorage.clear();
	}
	var noWx = sessionStorage.getItem('prenoWx')||''; //获取存在本地的微信号码
	var index = sessionStorage.getItem('preindex')||0;
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

	window.localStorage.setItem("headImgLeft", $(".left_nav .member img").attr("src"));

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
	var nW=$(".shop_nav .shop_list .shopDetail.active .noWx").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/unreadCountByMember",
        data:{memberNoList:memberNoList.substring(0,memberNoList.length-1),noWxShop:nW},
        dataType:'JSON',
        success:function(result){
            if(result!=null){
            	for(var i=0;i<result.length;i++){
                	$(".con_nav .clientMemNo").each(function(){
                		if($(this).val()==result[i].memberNo){
                			$(this).parent().find(".pao").text(result[i].unreadCount);
                			$($(this).parent().find(".pao")).css("visibility","visible");
                			var lk=$(this).parent().attr("onclick");
                			if(lk.indexOf("chatTimeBegin")<0){
                				var link= lk.substring(0,lk.length-1)+"&chatTimeBegin="+result[i].chatTimeBegin+"'";
                				$(this).parent().attr("onclick",link);
                			}
                		}
                	})
                }
            }
        }
    });

}

function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();

	//清除所有的session
	sessionStorage.clear();
	window.location.href=$('#ctx').val()+"/member/forecastName/list?searchWords="+searchWords+"&pmTypeCode="+pmTypeCode;
}


/*重置查询*/
function resetQuery(){
	$(".shop_list .active").click();
}

/*查找终端好友*/
function shopMenmber(noWx,wxNickname,paramNum,headAddress,event){
	console.log(noWx,paramNum);
	sessionStorage.setItem('prenoWx',noWx); //储存点击的微信号于本地
	sessionStorage.setItem('preindex',paramNum); //储存点击的序号于本地
	sessionStorage.setItem('preshopNo',shopNo); //储存点击的微信号于本地
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
	$.ajax({
        type:"POST",
        url: $('#ctx').val()+"/member/forecastName/personMemberList",
        data:{noWx:noWx,pageSize:Number(n)},
        dataType:'JSON',
        success:function(res){
        	$(".con_search .wxName").text($(".shop_list .shopDetail.active .wxNickname").val());
        	$("#todayCount").text(res.todayCount);
        	var guideM = res.guidMembers;
        	var friendPoint=res.friendPoint;
        	if(guideM!=undefined){
        		var opHtml='<option value="">全部</option>';
        		for(var n=0;n<guideM.length;n++){
        			if(n==0){
        				sessionStorage.setItem('premerchantNo',guideM[n].merchantNo)
        			}
        			 opHtml+='<option value="'+guideM[n].memberNo+'">'+guideM[n].memberName+'</option>';
        		}
        		$(".guideG option").remove();
        		$(".guideG").append(opHtml);
        	}

        	var result = res.page;
            $(".condition_all .kehuNum").text(result.count);
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

             		if(i==0){
                 		html +='<div class="shopDetail active" id="'+ info[i].code +'" onclick="window.location.href=\''+$('#ctx').val()+'/member/forecastName/chat?pmTypeCode='+info[i].pmTypeCode+'&memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code='+info[i].code+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'\'">';
                 	}else{
	                 		html +='<div class="shopDetail" id="'+ info[i].code +'" onclick="window.location.href=\''+$('#ctx').val()+'/member/forecastName/chat?pmTypeCode='+info[i].pmTypeCode+'&memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code='+info[i].code+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'\'">';
                 	}

                 	html +='<input type="hidden" value="'+info[i].memberNo+'" class="clientMemNo"><input type="hidden" value="'+info[i].memberNoGm+'" class="memberNoGm"><input type="hidden" value="" class="chatTimeBegin">'
                 	html +='<div class="shopInfo"><img alt="" src="'+getHeadAddress(info[i].headAddress)+'">';
//                 	html +='<div class="shopInfo"><img alt="" src="'+info[i].headAddress+'">';
                     html +='<div class="pao">'+5+'</div></div><div class="shopInfo imshopName">';
         			html +='<p class="oneLine">'+info[i].nickNameWx+'(<span class="editMemberName">'+info[i].memberName +'</span><span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p><p class="chat_name">';
         			html +='(客户分组：'+info[i].pmTypeName+'；添加方式：'+addtype+'；所属导购：'+info[i].memberNameGm+'<span>;'+getPhone(info[i].mobile)+'</span>)</p></div></div>';
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
            }

            $(".person").html(html);
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
	var shopNo=$(".shop_list .shopDetail.active").find(".shopNo").val();

	//客户分组条件
	var clientG= $(".con_nav .clientG").find("option:selected").val();
	//导购筛选
	var guideG= $(".con_nav .guideG").find("option:selected").val();
	//自主筛选
	var keyword=$(".con_nav .searchTh").val();

	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/member/forecastName/personMemberList",
        data:{noWx:noWx,pageNo:pageNo,pageSize:pageSize,typeCode:clientG,memberNo:guideG,keyWord:keyword,shopNo:shopNo},
        dataType:'JSON',
        success:function(res){
        	var result = res.page;
        	var friendPoint=res.friendPoint;
            $(".pagination .pageShow").val(result.pageNo);

            $(".condition_all .kehuNum").text(result.count);
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
                 		html +='<div class="shopDetail active" onclick="window.location.href=\''+$('#ctx').val()+'/member/forecastName/chat?pmTypeCode='+info[i].pmTypeCode+'&memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code='+info[i].code+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'\'">';
                 	}else{
                 		html +='<div class="shopDetail" onclick="window.location.href=\''+$('#ctx').val()+'/member/forecastName/chat?pmTypeCode='+info[i].pmTypeCode+'&memberNo='+info[i].memberNo+'&'+'memberNoGm='+info[i].memberNoGm+'&'+'merchantNo='+info[i].merchantNo+'&'+'code='+info[i].code+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode+'\'">';
                 	}

             		html +='<input type="hidden" value="'+info[i].memberNo+'" class="clientMemNo"><input type="hidden" value="'+info[i].memberNoGm+'" class="memberNoGm"><input type="hidden" value="" class="chatTimeBegin">'
                 	html +='<div class="shopInfo"><img alt="" src="'+getHeadAddress(info[i].headAddress)+'">';
                    html +='<div class="pao">'+5+'</div></div><div class="shopInfo imshopName">';
                    html +='<p class="oneLine">'+info[i].nickNameWx+'(<span class="editMemberName">'+info[i].memberName +'</span><span> '+ newDate.getFullYear()+'-'+month+'-'+newDate.getDate()+')</span></p><p class="chat_name">';
//         			html +='(客户分组：'+info[i].pmTypeName+'；添加方式：'+addtype+'；所属导购：'+info[i].memberNameGm+')</p></div><div class="shopInfo shopNum">';
                    html +='(客户分组：'+info[i].pmTypeName+'；添加方式：'+addtype+'；所属导购：'+info[i].memberNameGm+'<span>;'+getPhone(info[i].mobile)+'</span>)</p></div></div>';
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
            }

            $(".person").html(html);
          //查找未读信息；
            clock();
        }
    });
}
