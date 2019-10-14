
//
$(document).ready(function() {
	var params=window.location.search.substring(1).split("&");
	var memberNo='';
	var memberNoGm='';
	for(var i=0;i<params.length;i++){
		if(params[i].indexOf("memberNo")>-1 && params[i].split("=")[0]=="memberNo"){
			memberNo=params[i].split("=")[1];
		}else if(params[i].indexOf("memberNoGm")>-1){
			memberNoGm=params[i].split("=")[1];
		}
	}
	
	var int=self.setInterval("clock("+memberNo+","+memberNoGm+")",10000);
	clock(memberNo,memberNoGm,1);
	$("#chat .left_nav .member img").attr("src", window.localStorage.getItem("headImgLeft"));
	$("#txtContent").focus();
//	syncMemberWxInfo();实时同步客户信息耗服务器资源 DZP 2018-12-22
	
	jfMall();
	
	$("#edit").click(function(){
		var ed=$(this).attr("class");
		if(ed.indexOf("edtbtn")>0){
			$(this).parent().find("input").removeAttr("disabled");
			$(this).parent().find("select").removeAttr("disabled");
			$(this).parent().find("textarea").removeAttr("disabled");
			$(".Name").focus();
			$(this).find("b").text("保存");
			$(this).removeClass("edtbtn");
		}else{
			var memAppName=$(".Name").val();//姓名
			var memMobile=$(".memMobile").val();	//电话
			var wantTravel=$(".wantTravel").val();	//意向旅游地
			var travelNum=$(".travelNum").val();	//出游人数
			var travelSite=$(".travelSite").val();	//出发地
			var travelMoney=$(".travelMoney").val();//费用预算
			var travelDul=$(".travelDul").val();//出游时长
			var travelDist=$(".travelDist").val();//行程标准
			var travelBefore=$(".travelBefore").val();//曾出游地
			var remark=$(".remark").val();//备注
			
			if(memAppName.length>10 || memAppName==""){
				alert("姓名字数不能超过10个并且不能为空！");
				$(".Name").focus();
				return;
			}
			var myreg=/^0?(13[0-9]|[0-9]14|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/; 
			if(memMobile!="" && !myreg.test(memMobile)){
				alert("手机号码格式不正确,请重新输入");
				$(".memMobile").focus();
				return;
			}else if(memMobile==""){
				alert("手机号码不能为空");
				$(".memMobile").focus();
				return;
			}
			if(wantTravel.length>20 || wantTravel==""){
				alert("意向旅游地字数不能超过20个并且不能为空！");
				$(".wantTravel").focus();
				return;
			}
			var reg = /^[1-9]\d*$/;
			if(!reg.test(travelNum) || travelNum==""){
				alert("只能输入正整数");
				$(".travelNum").focus();
				return;
			}else if(travelNum.length>3 ){
				alert("出游人数不能大于3位数！");
				$(".travelNum").focus();
				return;
			}
			if(travelSite.length>20){
				alert("出发地字数不能大于20个！");
				$(".travelSite").focus();
				return;
			}
			var regMoney = 	/^([1-9]\d*|0)(\.\d{1,2})?$/;
			if(!regMoney.test(travelMoney) && travelMoney!=""){
				alert("费用预算只能输入正数且最多为小数点后两位！");
				$(".travelMoney").focus();
				return;
			}else if(travelMoney.length>20){
				alert("费用预算字数不能大于20个！");
				$(".travelMoney").focus();
				return;
			}
			if(travelDul.length>20){
				alert("出游时长字数不能大于20个！");
				$(".travelDul").focus();
				return;
			}
			if(travelDist.length>50){
				alert("行程标准字数不能大于50个！");
				$(".travelDist").focus();
				return;
			}
			if(travelBefore.length>20){
				alert("曾出游地字数不能大于20个！");
				$(".travelBefore").focus();
				return;
			}
			if(remark.length>300){
				alert("备注字数不能大于300个！");
				$(".remark").focus();
				return;
			}
			var travelCode=$("#travelCode").val();
			var param={
					code:travelCode,
					name:memAppName,
					mobile :memMobile,
					sex :$(".sexChose option:selected").val(),
					intentAddress:wantTravel,
					personCount :travelNum,
					startAddress :travelSite,
					budget :travelMoney*100,
					tourtime :travelDul,
					travelStandard :travelDist,
					onceAddress  :travelBefore,
					remark:remark
			}
			$.ajax({
		        type:"POST",
		        url:$('#ctx').val()+"/member/forecastName/edit",
		        data:param,
		        dataType:'JSON',
		        success:function(result){
		        	console.log(result);
		        	if(result){
		        		$("#edit").parent().find("input").attr("disabled","disabled");
		        		$("#edit").parent().find("select").attr("disabled","disabled");
		        		$("#edit").parent().find("textarea").attr("disabled","disabled");
		        		$("#edit").find("b").text("编辑");
		        		$("#edit").addClass("edtbtn");
		        	}else{
		        		alert("保存失败！");
		        	}
		        	
		        }
		    });
			
			
		}
	})
	
});

function clock(memberNo,memberNoGm,num)
{
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/updateThirdHaveRead",
        data:{memberNo:memberNo,memberNoGm:memberNoGm},
        dataType:'JSON',
        success:function(result){
        	
        }
    });
	
	var limit=50;
	var chatTimeEnd=null;
	if(num== 2){
		var cd=Number(window.localStorage.getItem("preChatTimeEnd"));
		if(cd>0){
			chatTimeEnd = new Date(cd).format('yyyy-MM-dd hh:mm:ss');
		}
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/gmChat",
        data:{memberNoGm:memberNoGm,memberNo:memberNo,limit:limit,chatTimeEnd:chatTimeEnd},
        dataType:'JSON',
        success:function(result){
    		if(result.total>0){
    			var dataRows = []; // 保存去重后的数据
            	var rows = result.rows;
            	for(var lt = 0, len = rows.length; lt < len; lt++){
            		if(HISTORYCHATLIST.indexOf(rows[lt].code) == -1){ // 不存在
            			HISTORYCHATLIST.push(rows[lt].code);
            			dataRows.push(rows[lt]);
            		}
            	}
    			
    			var list = dataRows;
    			//list = list.reverce();
    			html='';
    			var gudHeadAddress=$("#gudHeadAddress").val();
    			var memberHead=$("#chat .person_chat_head img").attr("src");
    			console.log(gudHeadAddress)
    			var mapList=new Array();
    			if(num == 2 || num == 1){
    				if(result.rows.length==limit){
    					html +='<div class="sendTime text-center"><span onclick="clock(\''+memberNo+'\',\''+memberNoGm+'\',\''+merchantNo+'\',2)">加载更多</span></div>';
    				}
    				window.localStorage.setItem("preChatTimeEnd", list[list.length-1].chatTime);
    			}
    			for(var i=list.length; i>0;i-- ){
    				
    				if(num==1 && i-1==list.length-1 && $(".person .sendAll").length==0 ){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if( num==1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if(i-1==list.length-1 && list[i-1].chatTime -parseInt(window.localStorage.getItem("prechatstart"))>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if(i<=list.length-1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}
    				
    				if(i-1==list.length-1){
    					window.localStorage.setItem("prechatstart", list[0].chatTime);
    				}
    				if(list[i-1].senderFlag==2){	//客户发送
    					
    					if(list[i-1].type==1){			//文本	
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div></div>';
    					}else if(list[i-1].type==3){		//图片
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px"  onclick="findChatImage(\''+list[i-1].code+'\')" /></div></div>';
    						}else{
    							html += '<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div></div>';
    						}
    						
    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px" /></div></div>';
    					}else if(list[i-1].type==34){		//语音
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    						    dataType:'text',
    						    async: false,
    						    success:function(re){
    						    	html += '<div class="information"><div class="audio" onclick="play(this)"><audio src="'+$("#UploadUrl").val()+re+'"  class="yuying"></audio></div></div><div class="seCo">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div></div>';
    						    }
    						});
    						
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492 || list[i-1].type==49){	//分享1
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '<div class="sendDecText">';
    							if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
        							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
        						}else{
        							html += '<p>'+list[i-1].shareDes+'</p>';
        						}
    						html +=	'</div></div></div></div>'		
//    						html += '<div class="mine general sendAll" >';
//    						html += '<div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
//    						html += '<div class="sendDec"><div class="sendDecText">';
//    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
//    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
//    						}else{
////    							html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p><p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
//    							if(list[i-1].type==490){
//    								html += '<p>'+ list[i-1].shareDes +'</p>';
//    							}else{
//    								html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p><p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
//    							}
//    						}
//    						html += '</div><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
//    						html += '</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div></div>';
//    						if(list[i-1].status>2 &&list[i-1].errorMessage !=undefined){
//        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
//        					}
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div></div>';	
    					}else if(list[i-1].type==48){ //位置
    						var poiname = JSON.parse(list[i-1].content).poiname;
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div></div>';
    						html +=	'</div>'	
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){	//红包
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="other sendAll">';
							html += '<img alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div class="information">';
    						html += '<div class="personCard sendAll redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div class="redEnvelopes-right" title="'+content.des+'"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
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
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'	
    						}
    						 
    						html +='<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
							
    					}else if(list[i-1].type==34){		//语音
    						
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'	
    						}
    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    						    dataType:'text',
    						    async: false,
    						    success:function(re){
    						    	html +='<div class="seCo">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div><div class="information"><div class="audio" onclick="play(this)"><audio src="'+$("#UploadUrl").val()+re+'"  class="yuying"></audio></div></div>';
    						    }
    						});
    						
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
    					
    					}else if(list[i-1].type==3){		//图片
    						
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'	
    						}
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}else{
    							html +='<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
    						
    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'	
    						}
    						html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){	//分享
    						html += '<div class="mine general sendAll" >';
    						html += '<div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><div class="sendDecText">';
    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
    						}else{
    							if(list[i-1].type==490 || list[i-1].type==49){
    								html += '<p>'+ list[i-1].shareDes +'</p>';
    							}else{
    								html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p><p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    							}
    							
    						}
    						html += '</div><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div></div>';
    						if(list[i-1].status>2 &&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="mine personCard sendAll">';
    						html += '<div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';	
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';	
        					}
    					}else if(list[i-1].type==48){//位置	
    						var poiname = JSON.parse(list[i-1].content).poiname;
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="mine personCard sendAll">';
    						html +='<div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div></div>';
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="mine sendAll">';
    						html += '<div class="redPackage">';
    						html += '<div class="redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div title="'+content.des+'" class="redEnvelopes-right"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '<img alt="" class="headImg" src="'+gudHeadAddress+'">';
    						html += '</div>'
    						
    					}else if(list[i-1].type==43){	//视频
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'	
    						}
    						html +='<div class="information"><video src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div>';
    						html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
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
    			if(num == 2){
    				var bftalH=$("#chat .person")[0].scrollHeight;
    				$($("#chat .person .sendTime")[0]).remove();
    				$($("#chat .person ").children()[0]).before(html); 
    				var talH=$("#chat .person")[0].scrollHeight;
    				$("#chat .person").scrollTop(talH-bftalH);
    			}else{
    				$("#chat .person .sign").before(html);
    			}
    		
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
    			if(num==1){
    				$("#chat .person").scrollTop($("#chat .person")[0].scrollHeight);
    			}else{
        			var ph=$(".person").height();
        			var ch=$(".person")[0].scrollHeight;
        			var scrCh=$(".person").scrollTop();
        			var ff=$(".sendAll").length-1;
        			var newH=$($(".sendAll")[ff]).height();
        			if(ch-ph-scrCh-newH<50){
        				$(".person").scrollTop($(".person")[0].scrollHeight);
        			}
    			}
    			
    		}
        }
    });
}	

	
function More(){
	var beginDate=$("#pushDate").val();
	var merchantNo=$("#PumerchantNo").val();
	var memberNoGm=$("#PumemberNoGm").val();
	var pageNo=$("#pageNo").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/getMessagePush",
        data:{pageNo:Number(pageNo)+1,memberNoGm:memberNoGm,merchantNo:merchantNo},
        dataType:'JSON',
        success:function(result){
        	var rows=result.rows;
        	$("#pageNo").val(result.pageNo);
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
$(document).click(function(e) { // 在页面任意位置点击而触发此事件
	 var words= $(e.target).attr("class");       // e.target表示被点击的目标
	 if(words!=undefined && words.indexOf("words")==-1){
		 $(".emoticon .words").hide();
	 }
})


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

//是否显示积分商城
function jfMall(){
	var memberNo=getQueryString('memberNo');
	var memberNoGm=getQueryString('memberNoGm');
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/queryIemEntry.do",
        data:{memberNoGm:memberNoGm,memberNo:memberNo},
        dataType:'JSON',
        success:function(result){
        	console.log(result);
        	if(result.showIemEntry){
        		var ctxStatic=$("#ctxStatic").val();
        		var html='<li><img title="积分商城" alt="" class="jfshopIcon" src="'+ctxStatic+'/admin/images/imImages/jfshopIcon.png" onmouseover="changeimg(this)" onmouseout="changeimg(this)" onclick=\'sendJf('+JSON.stringify(result)+')\'></li>'
        		$(".sendInfoDetail .emoticon ul").append(html);
        	}
        }
    });
}


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
