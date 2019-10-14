var HISTORYCHATLIST = [];
var GROUPMEMBER = [];
$(document).ready(function() {
	queryGroupMember(1);	//查询群成员
	
	$(".pagination select").change(function(){
		var n=$(this).find("option:selected").text();
		$("#limit").val(Number(n));
		var t=Number($(".pagination .total").text());
		var totalPage=Math.ceil(t/Number(n));
        $(".pagination .totalPage").text(totalPage);
        changePage("first");
	});
	$("#historyChat .left_nav .member img").attr("src", window.localStorage.getItem("headImgLeft"));


	$(".pageShow").change(function(){
		var limit=$("#limit").val();
		var total=$(".pagination .totalPage").text();
		var page=$(".pageShow").val();
		var reg= /^[0-9]*[1-9][0-9]*$/;
		if(reg.test(page)){
			if(Number(page)<=Number(total) && Number(page)>1){
				$("#start").val(Number(page-1)*limit);
			}else{
				$(".pageShow").val(1);
				$("#start").val(0);
			}
			changePage();
		}else{
			if(Number(page)>0 && Number(page)<=Number(total)){
				$(".pageShow").val(Math.floor(Number(page)));
				$("#pageNo").val( Math.floor(Number(page)));
			}else{
				$(".pageShow").val(1);
				$("#start").val(0);
			}
			changePage();
		}
	});

});

var SELECTDATE = $("#startTime").val();

function cDayFunc(dp){
	var data=$("#startTime").val();
	if(SELECTDATE == data) return;
	SELECTDATE = data;
	$(".choseDate").text(data);
	if(data==""){
		$(".choseDate").text("选择日期");
	}
	$("#start").val(0);
	$(".pagination .pageShow").val(1);
	clock(data);
	window.localStorage.setItem("hisData", data);
}
function clock(data)
{
	var memberNo=getQueryString("code");
	var noWxGm = $('#guideWxId').val();//终端微信号

	var limit=$("#limit").val();
	var start=$("#start").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/gmChat",
        data:{memberNo:memberNo,limit:limit,start:start,chatDate:data},
        dataType:'JSON',
        success:function(result){
        	/*$("#startTime").val("");*/
        	var flag=false;
        	$("#limit").val(result.limit);
        	$("#start").val(result.start);
        	$("#historyChat .total").text(result.total);
        	 var totalPage=Math.ceil(result.total/result.limit);
             $("#historyChat .totalPage").text(totalPage);

             var currPage=$(".pagination .pageShow").val();
             if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
             	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
         	}else if(Number(currPage)==Number(totalPage) && totalPage!=1){
         		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
         		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
         		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
         		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
         	}else  if(Number(currPage)==1  && totalPage!=1){
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

    		if(result.total>0){
    			var list=result.rows;
    			var html='';
    			var gudHeadAddress=sessionStorage.getItem("shopHeadImg");
    			var mapList=new Array();
    			for(var i=list.length; i>0;i-- ){
    				var hour=new Date(list[i-1].chatTime).getHours();
    				var minute=new Date(list[i-1].chatTime).getMinutes();
    				if(i-1==list.length-1){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if(i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}

    				if(list[i-1].senderFlag==2){	//客户发送
    					var headImgs=sessionStorage.getItem("headUrl_"+list[i-1].chatroomNoWx);		//从缓存中加载
    					var memberNmg=sessionStorage.getItem("nickName_"+list[i-1].chatroomNoWx);		//从缓存中加载
    					var memberHead = getHeadAddress(headImgs);
    					
    					if(list[i-1].type==1){			//文本
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;" class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
    						html += '</div></div>';
    					}else if(list[i-1].type==3){		//图片
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}else{
    							html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}
    						html += '</div></div>';
    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
    						html += '</div></div>';
    					}else if(list[i-1].type==34){		//语音
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    							 dataType:'text',
     						    async: false,
    						    success:function(re){
    						    	console.log(re);
    						    	html += '<div><span>'+memberNmg+'</span><div class="information"><div class="audio" onclick="play(this)"><audio src="'+re+'"  class="yuying"></audio></div></div></div><div class="seCo"  style="margin-top:48px;">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div>';
    						    }
    						});
    						html += '</div>';
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){	//分享
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div><span>'+memberNmg+'</span><div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '<div class="sendDecText">';
    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
    						}else{
    							html += '<p>'+list[i-1].shareDes+'</p>';
    						}
    						html +=	'</div></div></div>'
    						html += '</div></div>';
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div>';
    						html += '</div></div>';
    					}else if(list[i-1].type==48){ //位置
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div><span>'+memberNmg+'</span><div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ label +'</h2><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div></div>';
    						html +=	'</div></div>'
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="other sendAll">';
							html += '<img alt="" data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  class="headImg" src="'+gudHeadAddress+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="information">';
    						html += '<div class="personCard sendAll redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div class="redEnvelopes-right"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '</div></div>'

    					}else if(list[i-1].type==43){		//视频
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;" class="information"><input type="hidden" class="videoLink" value="'+list[i-1].resourcesPath+'" /><div class="videoTop" onclick="findChatVideo(this,\''+list[i-1].code+'\')"></div><video  src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div></div></div>';
    					}else if(list[i-1].type==42){	//联系人名片或公众号名片
    						var content=JSON.parse(list[i-1].content);
    						html += '<div class="other personCard sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						if(content.certflag!=""){
    							if(content.certflag==0){
    								html += '<div><span>'+memberNmg+'</span><div style="max-width:none;" class="shareInfo" onclick="findFriendsByWxNo(\''+content.username+'\',\''+content.alias+'\',\''+content.usernamev2+'\',\''+list[i-1].resourcesPath+'\',\''+list[i-1].shareTitle+'\',\''+list[i-1].noWxGm+'\')">';
    							}else{
    								html += '<div><span>'+memberNmg+'</span><div style="max-width:none;" class="shareInfo">';
    							}
    						}else{
    							html += '<div><span>'+memberNmg+'</span><div class="shareInfo" style="max-width:none;">';
    						}
    						html += '<div class="sendDec"><img alt=""  src="'+list[i-1].resourcesPath+'">';
    						html += '<div class="sendDecText" style="display: flex;align-items: center;"><p>'+list[i-1].shareTitle +'</p>';
    						html += '</div></div><p class="sendTitle">';
    						if(content.certflag!=""){
    							if(content.certflag==0){
    								html += '个人名片';
    							}else if(content.certflag==8){
    								html += '公众号名片';
    							}
    						}
    						html +='</p></div></div></div>';
    					}else if(list[i-1].type==494){		//小程序
    						var content=JSON.parse(list[i-1].content);
    						html += '<div class="other personCard sendAll  xcxT"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'"  alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;" class="shareInfo"><div class="sendDec">';
    						html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p><p>'+list[i-1].shareDes +'</p>';
    						if(list[i-1].shareUrl){
    							html += '<img alt=""  src="'+list[i-1].shareUrl+'">';
    						}
    						html += '</div></div><p class="sendTitle">';
    						html += '小程序';
    						html +='</p></div></div></div>';
    					}else{
    						html += '<div class="sendTime text-center"><span>'+list[i-1].content+'</span></div>'
    					}


    				}else{	//导购发送

    					if(list[i-1].type==1){			//文本
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status!=2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}

    						html +='<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}

    					}else if(list[i-1].type==34){		//语音

    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status!=2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}

    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    							 dataType:'text',
     						    async: false,
    						    success:function(re){
    						    	console.log(re);
    						    	html +='<div class="seCo">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div><div class="information"><div onclick="play(this)" class="audio"><audio src="'+re+'"  class="yuying"></audio></div></div>';
    						    }
    						});
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}

    					}else if(list[i-1].type==3){		//图片

    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status!=2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}

    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}else{
    							html +='<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}

    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}

    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status!=2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}
    						html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){	//分享
    						html += '<div class="mine general sendAll" >';
    						html += '<div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><div class="sendDecText">';
    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
    						}else{
    							html += '<p>'+list[i-1].shareDes+'</p>';
    						}
    						html += '</div><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div></div>';
    						if(list[i-1].status!=2 &&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="mine personCard sendAll">';
    						html += '<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==48){//位置
    						var content = JSON.parse(list[i-1].content || '{}');
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html +='<div class="mine personCard sendAll">';
    						html += '<div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')">';
    						html += '<h2 class="mapToy-title">'+ label +'</h2>';
//    						html += '<span class="mapToyMs"></span>';
    						html += '<div id="'+ list[i-1].code +'" class="containerMap"></div>';
    						html += '</div>';
							html += '<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="mine sendAll">';
    						html += '<div class="redPackage">';
    						html += '<div class="redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div class="redEnvelopes-right"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '<img alt="" class="headImg" src="'+gudHeadAddress+'">';
    						html += '</div>'

    					}else if(list[i-1].type==43){	//视频
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status!=2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}
    						html +='<div class="information"><video src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==42){	//联系人名片或公众号名片
    						var conJson="";
    						if(list[i-1].content){
    							conJson=JSON.parse(list[i-1].content);
    						}
    						html += '<div class="mine personCard sendAll">';
    						html += '<div class="shareInfo"><div class="sendDec"><img alt=""  src="'+list[i-1].resourcesPath+'">';
    						html += '<div class="sendDecText" style="display: flex;align-items: center;"><p>'+list[i-1].shareTitle +'</p>';
    						html += '</div></div><p class="sendTitle">';
    						if(conJson!=""){
    							if(conJson.certflag==0){
    								html += '个人名片';
    							}else if(conJson.certflag==8){
    								html += '公众号名片';
    							}
    						}
    						html +='</p></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==494){		//小程序
    						var conJson="";
    						if(list[i-1].content){
    							conJson=JSON.parse(list[i-1].content);
    						}
    						html += '<div class="mine personCard sendAll  xcxT">';
    						html += '<div class="shareInfo"><div class="sendDec">';
    						html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p><p>'+list[i-1].shareDes +'</p>';
    						if(list[i-1].shareUrl){
    							html += '<img alt=""  src="'+list[i-1].shareUrl+'">';
    						}
    						html += '</div></div><p class="sendTitle">';
    						html += '小程序';
    						html +='</p></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else{
    						html += '<div class="sendTime text-center"><span>'+list[i-1].content+'</span></div>'
    					}
    				}

    			}

    			$("#historyChat .person").html(html);
    			for(var m=0;m<mapList.length;m++){
    				var label = JSON.parse(mapList[m].content).label;
					var lng = JSON.parse(mapList[m].content).lng;
					var lat = JSON.parse(mapList[m].content).lat;
					var center = new qq.maps.LatLng(lat,lng);
					var map = new qq.maps.Map(document.getElementById(mapList[m].code), {
						 	center: center,
				            zoom: 13,
				            draggable: false,               //设置是否可以拖拽
					        scrollwheel: false,             //设置是否可以滚动
					        disableDefaultUI: true    //禁止所有控件
				        });
				        var marker = new qq.maps.Marker({
				            position: center,
				            map: map,
				        });
				        $('#container').append(marker);
				}
    			$("#historyChat .person").scrollTop($("#historyChat .person")[0].scrollHeight);
    		}else{
    			$("#historyChat .person").html("");
    		}

        }
    });
}


function changePage(info){

	var limit=$("#limit").val();
	var start=$("#start").val();

	if(info=="first"){
		start=0;
		$(".pagination .pageShow").val(1);
	}else if(info=="prev"){
		if(Number(start)>=Number(limit)){
			start=start-limit;
			$(".pagination .pageShow").val($(".pagination .pageShow").val()-1);
		}

	}else if(info=="next"){
		var last=$(".pagination .totalPage").text();
		var nn=$(".pagination .pageShow").val();
		if(Number(nn)<Number(last)){
			$(".pagination .pageShow").val(Number(nn)+1);
		}
		if(start<limit*Number(last) && Number(nn)*limit<$(".pagination .total").text()){
			start=Number(nn)*limit;
		}
	}else if(info=="last"){
		start=Number($(".pagination .totalPage").text()-1)*limit;
		$(".pagination .pageShow").val($(".pagination .totalPage").text());
	}

	$("#limit").val(limit);
	$("#start").val(start);
	var hisData=window.localStorage.getItem("hisData");
	clock(hisData);
}


window.addEventListener('message', function(event) {
    // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
    var loc = event.data;
    if (loc && loc.module == 'locationPicker') {//防止其他应用也会向该页面post信息，需判断module是否为'locationPicker'
        var lat = loc.latlng.lat;
        var lng = loc.latlng.lng;
        var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('memberNo') ||'';
    	var memberNoGm=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.guidMemNo').val() :getQueryString('memberNoGm')||'';
    	sessionStorage.setItem('lat',loc.latlng.lat);
    	sessionStorage.setItem('lng',loc.latlng.lng);
    	sessionStorage.setItem('poiaddress',loc.poiaddress);
    	sessionStorage.setItem('poiname',loc.poiname);

        console.log(loc)
       var options = {
				confirm:function(){
					sendBox();
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
				msg:"确定发送该位置" // 提示文字
			};
  		 ConfirmBox(options);
    }
}, false);


//下载视频
var videoCycle;
function findChatVideo(evnet,code,imgurl){
	var repeat=10;
	var vl=$(evnet).prev().val();
	if(vl!=""){
		if($(evnet).next()[0].paused){
			$(evnet).next()[0].play();
		}else{
			$(evnet).next()[0].pause();
		}
		$(evnet).remove();
	}else{
		$("#loadFlag").css("display","flex");
		$.ajax({
			type:"post",
	    	url:$('#ctx').val() + "/im/findChatVideo.do",
	    	data:{code:code},
	    	dataType:'JSON',
	    	async: false,
	    	success:function(res){
	    		console.log(res)
	    		if(res.resourcesPath){
	    			$(evnet).prev().val(res.resourcesPath)
	    			$(evnet).next().attr("src",res.resourcesPath);
	    			$(evnet).next()[0].play();
	    			$("#loadFlag").css("display","none");
	    		}else{
	    			//每隔3秒查询一次;共查10次
	    			videoCycle=setInterval(function() {
    				    if (repeat == 0) {
    				    	window.clearInterval(videoCycle);
    				    	findChatVideoinfo($(evnet).next(),code,imgurl,repeat);
    				    } else {
    				    	repeat =  repeat-1;
    				    	findChatVideoinfo($(evnet).next(),code,imgurl,repeat);
    				    }
    				}, 3000);

	    		}

	    	}
		})
	}

}

function findChatVideoinfo(evnet,code,imgurl,repeat){
	$("#loadFlag").css("display","flex");
	$.ajax({
		type:"post",
    	url:$('#ctx').val() + "/im/findChatInfo.do",
    	data:{code:code},
    	dataType:'JSON',
    	async: false,
    	success:function(res){
    		console.log(res)
    		if(res.resourcesPath){
    			$(evnet).prev().prev().val(res.resourcesPath)
    			$(evnet).attr("src",res.resourcesPath);
    			$(evnet)[0].play();
    			window.clearInterval(videoCycle);
    		}
    		if(res !=null && repeat!=0){
    			$("#loadFlag").css("display","none");
    			window.clearInterval(videoCycle);
    		}else if(repeat==0){
    			$("#loadFlag").css("display","none");
    			window.clearInterval(videoCycle);
    		}
    	}
	})

}


/*查询群成员*/
function queryGroupMember(pageNo){
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/chatroom/memberList",
        data:{roomCode:getQueryString("code"),status:1,pageNo:pageNo,pageSize:20},
        dataType:'JSON',
        success:function(result){
        	if(result.result){
        		var list = result.data.list;
        		$("#count").val(result.data.count)
        		if(list!=undefined){
            		var html = "";
            		var arr=[];
            		for(var i=0;i<list.length;i++){
            			arr.push();
            			var info={
        					'headUrl':list[i].headUrl,
        					'nickName':list[i].nickName,
       			            'userName':list[i].userName
            			}
            			/**
            			 * 缓存群成员头像
            			 * key = headUrl_前缀+微信号
            			 * value = 微信头像全路径
            			 * @returns
            			 */
            			sessionStorage.setItem("headUrl_"+list[i].userName,list[i].headUrl);
            			/**
            			 * 缓存群成员昵称
            			 * key = nickName_前缀+微信号
            			 * value = 群成员昵称
            			 * @returns
            			 */
            			sessionStorage.setItem("nickName_"+list[i].userName,list[i].nickName);
            			
            			arr.push(info);
            			html +='<li><span><img alt="" src="'+getHeadAddress(list[i].headUrl)+'">';
            			html +='</span><span title="'+list[i].nickName+'">'+list[i].nickName+'</span></li>';
            		}
            		console.log(arr);
            		GROUPMEMBER.push( JSON.stringify(arr));
            		$(".groupHeads ul").append(html);
            		sessionStorage.setItem(list[0].roomCode,GROUPMEMBER);
            		if(result.data.count>result.data.pageSize*result.data.pageNo){
            			$("#pageNo").val(Number(pageNo) + 1);
            			$(".groupBt").css("display","block")
            		}else{
            			$(".groupBt").css("display","none")
            		}
            	}else{
            		$(".groupBt").css("display","none")
            	}
        		
        		//初始化聊天记录
        		clock();
        	}

        }
	});
}

function moreGroupM(){
	var num = $("#pageNo").val();
	queryGroupMember(num);
}

var intCycle="";
//查看大图
function findChatImage(code,imgurl){
	var repeat=10;
	if(imgurl!=undefined){
		$(".bigImg").css("display","flex");
		$(".bigImg img").attr("src",imgurl);
	}else{
		$("#loadFlag").css("display","flex");
		$.ajax({
			type:"post",
	    	url:$('#ctx').val() + "/im/findChatImage.do",
	    	data:{code:code},
	    	dataType:'JSON',
	    	async: false,
	    	success:function(res){
	    		console.log(res)
	    		if(res !=null){
	    			$("#loadFlag").css("display","none");
	    			if(!res.content){ //空
	    				$(".bigImg .hideImg").attr("src",getHeadAddress(res.resourcesPath));
	    				changePic(getHeadAddress(res.resourcesPath))
	    			}else{
	    				var imgaddr=JSON.parse(res.content).midImg;
	    				$(".bigImg .hideImg").attr("src",imgaddr);
	    				changePic(imgaddr)
	    			}


	    		}else{
	    			//每隔3秒查询一次;共查10次
	    				intCycle=setInterval(function() {
	    				    if (repeat == 0) {
	    				    	window.clearInterval(intCycle);
	    				    	findChatInfo(code,repeat);
	    				    } else {
	    				        repeat--;
	    				        findChatInfo(code,repeat);
	    				    }
	    				}, 3000);

	    		}

	    	}
		})
	}

}
//循环查询大图信息
function findChatInfo(code,imgsee){
		$.ajax({
			type:"post",
	    	url:$('#ctx').val() + "/im/findChatInfo.do",
	    	data:{code:code},
	    	dataType:'JSON',
	    	async: false,
	    	success:function(res){
	    		console.log(res)
	    		if(res !=null && imgsee!=0){
	    			if(res.content){ //非空
	    				var imgaddr=JSON.parse(res.content).midImg;
	    				$(".bigImg .hideImg").attr("src",imgaddr);
	    				changePic(imgaddr);
	    			}else{
	    				$(".bigImg .hideImg").attr("src",getHeadAddress(res.resourcesPath));
	    				changePic(getHeadAddress(res.resourcesPath));
	    			}
	    			$("#loadFlag").css("display","none");
	    			window.clearInterval(intCycle);
	    		}else if(imgsee==0){
	    			$("#loadFlag").css("display","none");
	    			changePic(getHeadAddress(res.resourcesPath));
	    			window.clearInterval(intCycle);
	    		}
	    	}
		})

}


//查看html片斷
function preview() {
	var htmls = document.getElementById('txtContent').innerHTML;
	if(htmls) {
		htmls = '<div style="margin:0;padding:0;background:#bbb;">'+ htmls +'<\/div>';
		var view = window.open('about:blank', 'view');
		view.document.open();
		view.document.write(htmls);
		view.document.close();
	}
}

//判斷流覽器
(function() {
	window.browser = {};
	if(navigator.userAgent.indexOf("MSIE") > 0) {
		browser.name = 'MSIE';
		browser.ie = true;
	} else if(navigator.userAgent.indexOf("Firefox") > 0){
		browser.name = 'Firefox';
		browser.firefox = true;
	} else if(navigator.userAgent.indexOf("Chrome") > 0) {
		browser.name = 'Chrome';
		browser.chrome = true;
	} else if(navigator.userAgent.indexOf("Safari") > 0) {
		browser.name = 'Safari';
		browser.safari = true;
	} else if(navigator.userAgent.indexOf("Opera") >= 0) {
		browser.name = 'Opera';
		browser.opera = true;
	} else {
		browser.name = 'unknow';
	}
} )();

function changePic(url){
	var imageObj=new Image();

	imageObj.src=url;

	imageObj.onload=function(){

		var Orientation=1;//默认为1

		var cvs=document.createElement('canvas');

		var ctx=cvs.getContext('2d');

		var scale=1;//预留压缩比

		var size=this.size;

		cvs.width=this.width*scale;

		cvs.height=this.height*scale;//计算等比缩小后图片宽高

		EXIF.getData(imageObj,function() {

		Orientation=EXIF.getTag(this,"Orientation");//获取图片原始方向

		ctx.drawImage(this,0,0,cvs.width,cvs.height);

		var newImageData=cvs.toDataURL(this,1);

		//重新定向加载url地址
		$('.bigImg img').attr('src',newImageData);
		$(".bigImg").css("display","flex");

		/* 顺时针改变图片方向 */
		console.log('Orientation : '+Orientation);
		$(".bigImg img").rotate(0);
		if(Orientation&&Orientation!=1){
			switch(Orientation){
				case 6:// 旋转90度
					$(".bigImg img").rotate(90);
					break;
				case 8:// 旋转270度
					$(".bigImg img").rotate(90*3);
					break;
				case 3:// 旋转180度
					$(".bigImg img").rotate(90*2);
					break;
			}
		}

		});

		};
}



function bigImg(url){
	$("#bigImg").css("display","flex");
	$("#bigImg img").attr("src",url);

}


/*添加好友*/
function findFriendsByWxNo(username,alias,usernamev2,headAddress,nickNameWx,merchantWxNo){
	var msg ="是否添加好友";
	 options = {
		confirm:function(){
			doApplayFriend(username,alias,usernamev2,headAddress,nickNameWx,merchantWxNo);
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

function doApplayFriend(username,alias,usernamev2,headAddress,nickNameWx,merchantWxNo){
	var param={
		scanId:"-100",
		wxQrCode:username,
		alias:alias,
		usernamev2:usernamev2,
		headAddress:headAddress,
		nickNameWx:nickNameWx,
		merchantWxNo:merchantWxNo
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/doApplayFriend",
        data:param,
        dataType:'JSON',
        success:function(result){
        	if(result.success){
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
       				msg:"已发送好友请求" // 提示文字
       			};
            	ConfirmBox(options);
        	}else{
        		var msg=result.msg;
        		if(msg.indexOf("-->")>0){
        			msg=msg.split("-->")[1];
        		}
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


;(function($){ // 弹出框
	var defaultstt = {
		confirm:function(){}, // 确定调用方法
		cancel:function(){}, // 取消调用方法
		confirmBtn:{ // 确定按钮文本和背景
			text:'确定',
			background:'#e53c5f',
			color:'#2a2a2a'
		},
		cancelBtn:{ // 取消按钮文本和背景
			text:'取消',
			background:'#b3b3b3',
			color:'#fff'
		},
		isCancel:true, // 是否显示取消按钮
		msg:'' // 提示文字
	};
	var ConfirmRe = function(options){
		this.init(options);
	};
	ConfirmRe.prototype = {
		init:function(options){
			var self = this;
			$('body').append(this.createTags(options));

			$('.promitConfirmButton').on('click',function(ev){
				$(this).parents('.promitMsgLayerList').remove();
				options.confirm();
			});
			if(options.isCancel) { // 是否有取消按钮
				$('.promitCancleButton').on('click',function(ev){
					$(this).parents('.promitMsgLayerList').remove();
					options.cancel();
				});
			}
		},
		createTags:function(options){
			var tags='<div class="text-center promitMsgLayerList" style="position: absolute;z-index: 9999;background: rgba(0, 0, 0, 0.5);width: 100%;height: 100%;top:0;left: 0;">';
			tags +=	'<div style="width: 426px;height: 200px;background: #fff;border-radius: 5px;">';
			tags +=	'<div style="width: 100%;height: 130px;font-size: 16px;line-height: 25px;" class="text-center">'+options.msg+'</div>';
			tags +=	'<div style="width: 80%;height: 70px;display: flex;padding: 0 10%;justify-content:space-around;align-items:center;">';
			tags +=	'<span class="text-center promitConfirmButton" style="width: 130px;height: 40px;border: 1px solid #d1d1d1;border-radius: 5px;color:'+ options.confirmBtn.color +';background: '+ options.confirmBtn.background +'">'+options.confirmBtn.text +'</span>';
			if(options.isCancel){ // 是否有取消按钮
				tags +='<span class="text-center promitCancleButton" style="width: 130px;height: 40px;border: 1px solid #d1d1d1;border-radius: 5px;color: '+ options.cancelBtn.color +';background: '+ options.cancelBtn.background +'">'+ options.cancelBtn.text +'</span>';
			}
			tags +=	'</div></div></div>';
			return tags;
		}
	};

	ConfirmBox = function(options){
		var options = $.extend({},defaultstt,options);
		new ConfirmRe(options);
	};
})(jQuery);
	
