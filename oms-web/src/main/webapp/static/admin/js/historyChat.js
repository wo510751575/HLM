var HISTORYCHATLIST = [];
$(document).ready(function() {
	clock();
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
	var limit=$("#limit").val();
	var start=$("#start").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/gmChat",
        data:{memberNoGm:memberNoGm,memberNo:memberNo,limit:limit,start:start,chatDate:data},
        dataType:'JSON',
        success:function(result){
        	/*$("#startTime").val("");*/
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
    			var gudHeadAddress=$("#gudHeadAddress").val();
    			var memberHead=$("#historyChat .person_chat_head img").attr("src");
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
    					if(list[i-1].type==1){			//文本	
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
    						html += '</div>';
    					}else if(list[i-1].type==3){		//图片
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}else{
    							html += '<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}
    						html += '</div>';
    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
    						html += '</div>';
    					}else if(list[i-1].type==34){		//语音
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    							 dataType:'text',
     						    async: false,
    						    success:function(re){
    						    	console.log(re);
    						    	html += '<div class="information"><div class="audio" onclick="play(this)"><audio src="'+re+'"  class="yuying"></audio></div></div><div class="seCo">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div>';
    						    }
    						});
    						html += '</div>';
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){	//分享
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '<div class="sendDecText">';
    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
    						}else{
    							html += '<p>'+list[i-1].shareDes+'</p>';
    						}
    						html +=	'</div></div></div>'	
    						html += '</div>';	
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div>';
    						html += '</div>';
    					}else if(list[i-1].type==48){ //位置
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ label +'</h2><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div></div>';
    						html +=	'</div>'	
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="other sendAll">';
							html += '<img alt="" class="headImg" src="'+gudHeadAddress+'">';
    						html += '<div class="information">';
    						html += '<div class="personCard sendAll redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div class="redEnvelopes-right"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '</div>'
    						
    					}else if(list[i-1].type==43){		//视频
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information"><input type="hidden" class="videoLink" value="'+list[i-1].resourcesPath+'" /><div class="videoTop" onclick="findChatVideo(this,\''+list[i-1].code+'\')"></div><video  src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div></div>';
    					}else if(list[i-1].type==42){	//联系人名片或公众号名片
    						var content=JSON.parse(list[i-1].content);
    						html += '<div class="other personCard sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						if(content.certflag!=""){
    							if(content.certflag==0){
    								html += '<div class="shareInfo" onclick="findFriendsByWxNo(\''+content.username+'\',\''+list[i-1].memberNoGm+'\',\''+content.alias+'\',\''+content.usernamev2+'\',\''+list[i-1].resourcesPath+'\',\''+list[i-1].shareTitle+'\',\''+list[i-1].noWxGm+'\')">';
    							}else{
    								html += '<div class="shareInfo">';
    							}
    						}else{
    							html += '<div class="shareInfo">';
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
    						html +='</p></div></div>';
    					}else if(list[i-1].type==494){		//小程序
    						var content=JSON.parse(list[i-1].content);
    						html += '<div class="other personCard sendAll  xcxT"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="shareInfo"><div class="sendDec">';
    						html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p><p>'+list[i-1].shareDes +'</p>';
    						if(list[i-1].shareUrl){
    							html += '<img alt=""  src="'+list[i-1].shareUrl+'">';
    						}
    						html += '</div></div><p class="sendTitle">';
    						html += '小程序';
    						html +='</p></div></div>';
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
