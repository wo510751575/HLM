var HISTORYCHATLIST = [];
/**
 * 导购聊JS
 * @returns
 */
$(document).ready(function() {
    var memberNo=$(".shop_list .active .clientMemNo").val();
    var memberNoGm=$("#guide .guidMemNo").val();
    if(memberNo!=""){
        readed(memberNo,memberNoGm);
         clock(memberNo,memberNoGm,1);
    }
    $(".shopDetail").click(function(){
        HISTORYCHATLIST=[];
        $(".person").html('<label class="sign"></label>');
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        var choseSrc=$(this).find(".shopInfo img").attr("src");
        var choseName=$(this).find(".imshopName p").text();
        $(".con_nav .con_search .person_chat_head img").attr("src",choseSrc);
        $(".con_nav .con_search .person_chat_head span").text(choseName);
        memberNo=$(this).find(".clientMemNo").val();
        memberNoGm=$(this).find(".guidMemNo").val();
        
        if(memberNo!=""){
             readed(memberNo,memberNoGm);
             clock(memberNo,memberNoGm,1);
        }
        
    })
    
    var int=self.setInterval(function(){ if(memberNo!=""){
        clock(memberNo,memberNoGm);
        readed(memberNo,memberNoGm);
    } },10000);
    
    $("#txtContent").focus();
    
    //最新消息滚动
    $("#guideChat .person").scrollTop($("#guideChat .person")[0].scrollHeight);
    $("#guideChat .left_nav .member img").attr("src", window.localStorage.getItem("headImgLeft"));
    jfMall();
});

function clock(memberNo,memberNoGm,num)
{   
    var memberNo=$(".shop_list .active .clientMemNo").val();
    var memberNoGm=$("#guide .guidMemNo").val();
    
    var limit=50;
    var chatTimeEnd=null;
    if(num== 2){
        var cd=Number(window.localStorage.getItem("GchatTimeEnd"));
        if(cd>0){
            chatTimeEnd = new Date(cd).format('yyyy-MM-dd hh:mm:ss');
        }
    }
    $.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/gmChat",
        data:{memberNoGm:memberNoGm,memberNo:memberNo,chatTimeEnd:chatTimeEnd,limit:limit},
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
                html='';
                var gudHeadAddress=$("#gudHeadAddress").val();
                var memberHead=$(".shop_list .shopDetail.active  .shopInfo img").attr("src");
                var mapList=new Array();
                if(num == 2 || num == 1){
                    if(result.rows.length==limit){
                        html +='<div class="sendTime text-center"><span onclick="clock(\''+memberNo+'\',\''+memberNoGm+'\',2)">加载更多</span></div>';
                    }
                    window.localStorage.setItem("GchatTimeEnd", result.rows[result.rows.length-1].chatTime);
                }
                for(var i=list.length; i>0;i-- ){
                    if(num==1 && i-1==list.length-1 && $(".person .sendAll").length==0 ){
                        html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
                    }else if( num==1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
                        html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
                    }else if(i-1==list.length-1 && list[i-1].chatTime -parseInt(window.localStorage.getItem("guidechatstart"))>(1000 * 60 * 5)){
                        html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
                    }else if(i<=list.length-1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
                        html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
                    }
                    
                    if(i-1==list.length-1){
                        window.localStorage.setItem("guidechatstart", list[0].chatTime);
                    }
                    
                    if(list[i-1].senderFlag==2){    //客户发送
                        
                        
                        if(list[i-1].type==1){          //文本    
                            html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            html += '<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div></div>';
                        }else if(list[i-1].type==3){        //图片
                            html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
                                html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
                            }else{
                                html += '<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
                            }
                            html += '</div>';
                        }else if(list[i-1].type==47){       //、图片表情
                            html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            html += '<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
                            html += '</div>';
                        }else if(list[i-1].type==34){       //语音
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
                            //html += '<div class="information"><audio src="'+list[i-1].resourcesPath +'" controls="controls" class="yuying"></audio></div>';
                            html += '</div>';
                        }else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){  //分享
                            html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            html +='<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
                            html += '<div class="sendDec"><img alt="" src="'+list[i-1].resourcesPath+'" style="width:68px"/>';
                            html += '<div class="sendDecText">';
                                if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
                                    html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
                                }else{
                                    html += '<p>'+list[i-1].shareDes+'</p>';
                                }
                            html += '</div></div></div>'
                            html += '</div>';
                        }else if(list[i-1].type==493){  //导购名片
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
                            html += '<div class="other personCard sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            var label = JSON.parse(list[i-1].content).label;
                            var poiname = JSON.parse(list[i-1].content).poiname;
                            var lat = JSON.parse(list[i-1].content).lat;
                            var lng = JSON.parse(list[i-1].content).lng;
                            html +='<div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div></div>';
                            mapList.push(list[i-1]);
                            html += '</div>';
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
                            
                        }else if(list[i-1].type==43){       //视频
                            html += '<div class="other sendAll"><img alt="" class="headImg" src="'+memberHead+'">';
                            html += '<div class="information"><input type="hidden" class="videoLink" value="'+list[i-1].resourcesPath+'" /><div class="videoTop" onclick="findChatVideo(this,\''+list[i-1].code+'\')"></div><video  src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div>';
                            html += '</div>';
                        }else if(list[i-1].type==42){   //联系人名片或公众号名片
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
                        }else if(list[i-1].type==494){      //小程序
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
                        
                        
                    }else{  //导购发送
                        
                        if(list[i-1].type==1){          //文本    
                            html += '<div class="mine general sendAll">';
                             
                            html +='<div class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
                            html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
                            if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
                                html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';   
                            }
                            
                        }else if(list[i-1].type==34){       //语音
                            
                            html += '<div class="mine general sendAll">';
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
                        
                        }else if(list[i-1].type==3){        //图片
                            
                            html += '<div class="mine general sendAll">';
                            
                            if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
                                html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
                            }else{
                                html +='<div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
                            }
                            
                            html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
                            if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
                                html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';   
                            }
                            
                        }else if(list[i-1].type==47){       //、图片表情
                            html += '<div class="mine general sendAll">';
                            html +='<div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
                            html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
                            if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
                                html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';   
                            }
                        }else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){  //分享
                            html += '<div class="mine general sendAll">';
                            html += '<div class="shareInfo"   onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
                            html += '<div class="sendDec"><div class="sendDecText">';
                            if(list[i-1].shareDes!=undefined && list[i-1].shareDes.split(" ").length>1){
                                html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
                            }else{
                                html += '<p>'+list[i-1].shareDes+'</p>';
                            }
                            html += '</div><img alt="" src="'+list[i-1].resourcesPath+'" style="width:68px"/>';
                            html += '</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div></div>';
                            if(list[i-1].status!=2 &&list[i-1].errorMessage !=undefined){
                                html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';   
                            }
                        }else if(list[i-1].type==493){  //导购名片
                            html += '<div class="mine personCard sendAll">';
                            html += '<div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt="" src="'+gudHeadAddress+'">';
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
                            var label = JSON.parse(list[i-1].content).label;
                            var poiname = JSON.parse(list[i-1].content).poiname;
                            var lat = JSON.parse(list[i-1].content).lat;
                            var lng = JSON.parse(list[i-1].content).lng;
                            html += '<div class="mine personCard sendAll">';
                            html +='<div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div><img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
                            html += '</div>';   
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
                            
                        }else if(list[i-1].type==43){   //视频
                            html += '<div class="mine general sendAll">';
                            html +='<div class="information"><video src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div>';
                            html +='<img alt="" class="headImg" src="'+gudHeadAddress+'"></div>';
                            if(list[i-1].status!=2&&list[i-1].errorMessage !=undefined){
                                html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';   
                            }
                        }else if(list[i-1].type==42){   //联系人名片或公众号名片
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
                        }else if(list[i-1].type==494){      //小程序
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
                    var bftalH=$("#guideChat .person")[0].scrollHeight;
                    $($("#guideChat .person .sendTime")[0]).remove();
                    $($("#guideChat .person ").children()[0]).before(html); 
                    var talH=$("#guideChat .person")[0].scrollHeight;
                    $("#guideChat .person").scrollTop(talH-bftalH);
                }else{
                    $("#guideChat .person .sign").before(html);
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
                    $("#guideChat .person").scrollTop($("#guideChat .person")[0].scrollHeight);
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
    
function check(memberNo,memberNoGm){
    //客户未读消息数
    var memberNoList="";
    $(".shop_nav .shop_list .shopDetail ").each(function(){
        memberNoList +=$(this).find(".clientMemNo").val() +",";
    });
    var shopNo=$(".social .shopNo").val();
    var noWxShop=$(".social .noWxShop").val();
    
    $.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/unreadCountByMember",
        data:{memberNoList:memberNoList.substring(0,memberNoList.length-1),shopNo:shopNo,noWxShop:noWxShop},
        dataType:'JSON',
        success:function(result){
        	var members ="";
        	var map = new Map();
        	for(var i=0;i<result.length;i++){
        		members+=result[i].memberNo+",";
        		map.set(result[i].memberNo,result[i]);
        	}
        	
        	$(".shop_nav .shop_list .shopDetail .clientMemNo").each(function(){
        		if(members.search($(this).val())!=-1){
        			$(this).parent().find(".pao").text(map.get($(this).val()).unreadCount);
                    $($(this).parent().find(".pao")).css("visibility","visible");
                    $(this).parent().find(".chatTime").val(map.get($(this).val()).chatTimeBegin);  
        			
//            			var $top = $(this).parent(".shopDetail")	//新消息置顶
//            			$(".person").prepend($top)
        		}else{
        			//没有数量就是0
                	$(this).parent().find(".pao").text("");
                    $($(this).parent().find(".pao")).css("visibility","hidden");
        		}
        	});
//        	if(result && result.length>0){
//	            $(".shop_nav .shop_list .shopDetail .clientMemNo").each(function(){
//	            	for(var i=0;i<result.length;i++){
//		                if($(this).val()==result[i].memberNo){
//		                    $(this).parent().find(".pao").text(result[i].unreadCount);
//		                    $($(this).parent().find(".pao")).css("visibility","visible");
//		                    $(this).parent().find(".chatTime").val(result[i].chatTimeBegin);   
//		
//		                   // var $top = $(this).parent(".shopDetail")    //新消息置顶                                                  
//		                  //  $(".person").prepend($top)
//		                }else{
//		                	//没有数量就是0
//		                	$(this).parent().find(".pao").text("");
//		                    $($(this).parent().find(".pao")).css("visibility","hidden");
//		                }
//	            	 }
//	            });
//        	}
            clock(memberNo,memberNoGm);
        }
    }); 
}

function readed(memberNo,memberNoGm){

    //已读
    $.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/updateThirdHaveRead",
        data:{memberNo:memberNo,memberNoGm:memberNoGm},
        dataType:'JSON',
        success:function(result){
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

//是否显示积分商城
function jfMall(){
    var memberNo=$(".shop_list .active .clientMemNo").val();
    var memberNoGm=$("#guide .guidMemNo").val();
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
                $(".sendInfoDetail .emoticon .clientEmoji ul").append(html);
                
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
