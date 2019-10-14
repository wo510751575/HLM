var HISTORYCHATLIST = [];
/**
 * 群聊JS
 * @returns
 */
$(document).ready(function() {
	queryGroupMember(1);	//查询群成员
	var memberNo=getQueryString("code");
	var noWxGm = $('#guideWxId').val();//终端微信号
	
	var int=self.setInterval('clock("'+memberNo+'","'+noWxGm+'")',10000);
	
	$("#txtContent").focus();

	$(".penson .yuying").click(function(){
		//这里的索引是1开始的所以要减一
	    var thisIndex = $(this).index()-1;
	    for( var i = 0 , len = $('audio').length ; i < len ; i ++ ){
	        //如果是当前这个音频播放，那么就跳过，其它音频全部暂停
	        if ( thisIndex != i ) {
	            $('audio')[i].pause();
	        }
	    }
	});


	/*隐藏遮盖层*/
	$(".zhegai").click(function(e){
		if($(e.target).attr("class")=='zhegai'){
			$(this).hide();
			$("#hd1").html("");
			$("#target").html("");
			$("#hd3").html("");
		}
		$(document).unbind("touchmove");
	});

	$(".yulan").click(function(e){
		if($(e.target).attr("class")=='yulan'){
			$(this).hide();
			$("#yulan").html("");
		}
	})

	$(".emojitab li").click(function(){
		$(this).addClass("active");
		$(this).siblings().removeClass("active");
	});

	/*enter键发送信息*/
	$("#txtContent").keyup(function(event){
		 var e = e || event, ec = event.keyCode || event.which;
		    if (!e.ctrlKey && 13 == ec) {
		        //console.log('发送');
		    	 sendSub(1);
		        return false;
		    }
		    if(e.ctrlKey && 13 == ec){
		    	var ev = e || window.event;
				var key = ev.keyCode || ev.charCode;
				var sel, rang, br, fixbr, node, inner, tempRange, offset;
				if(key == 13) {
					if(ev.preventDefault) {
						ev.preventDefault();
					} else {
						ev.returnValue = false;
					}
					if(window.getSelection) {
						br = document.createElement('br');
						sel = window.getSelection();
						rang = sel.rangeCount > 0 ? sel.getRangeAt(0) : null;
						if (rang === null) {
							return false;
						}
						rang.deleteContents();
						node = sel.focusNode;
						inner = false;
						while(node.parentNode != document.documentElement) {//確定focusNode是否在編輯框內
							if(node === $("#txtContent")[0]) {
								inner = true;
								break;
							} else {
								node = node.parentNode;
							}
						}
						if (inner) {
							if(browser.chrome || browser.safari || browser.firefox) {//chrome、safari內，尾部換行時多添加一個<br type='_moz'>
								tempRange = rang.cloneRange();
								tempRange.selectNodeContents($("#txtContent")[0]);
								tempRange.setEnd(rang.endContainer, rang.endOffset);
								offset = tempRange.toString().length;
								if(offset == $("#txtContent")[0].textContent.length && $("#txtContent")[0].querySelectorAll("#txtContent br[type='_moz']").length == 0) {//在行尾且不存在<br type='_moz'>時
									fixbr = br.cloneNode();
									fixbr.setAttribute('type', '_moz');
									rang.insertNode(fixbr);
								}
							}
							rang.insertNode(br);
						}
						if (document.implementation && document.implementation.hasFeature && document.implementation.hasFeature("Range", "2.0")) {
							tempRange = document.createRange();
							tempRange.selectNodeContents($("#txtContent")[0]);
							tempRange.setStart(rang.endContainer, rang.endOffset);
							tempRange.setEnd(rang.endContainer, rang.endOffset);
							sel.removeAllRanges();
							sel.addRange(tempRange);
						}
					} else {
						rang = document.selection.createRange();
						if (rang === null) {
							return false;
						}
						rang.collapse(false)
						rang.pasteHTML('<br>');
						rang.select();
					}
				}
		    }

	});


	$.ajaxSetup({
		 contentType: "application/x-www-form-urlencoded; charset=utf-8"
		});

	$(".sendInfoDetail .emoticon li img").mouseover(function(){
		var imgName=$(this).attr("class");
		if(imgName.indexOf("_arr")<0){

			var url=$(this).attr("src");
			url=url.replace(imgName,imgName+"_arr");
			$(this).attr("src",url);
			$(this).attr("class",imgName+"_arr");
		}
	}).mouseout(function(){
		var imgName=$(this).attr("class");
		if(imgName.indexOf("_arr")>-1){
			var url=$(this).attr("src");
			url=url.replace(imgName,imgName.replace("_arr",""));
			$(this).attr("src",url);
			$(this).attr("class",imgName.replace("_arr",""));
		}
	});

	$(".sendInfoDetail .emoticon li .upload").mouseover(function(){
		var imgName=$(this).prev().attr("class");
		if(imgName.indexOf("_arr")<0){
			var url=$(this).prev().attr("src");
			url=url.replace(imgName,imgName+"_arr");
			$(this).prev().attr("src",url);
			$(this).prev().attr("class",imgName+"_arr");
		}
	}).mouseout(function(){
		var imgName=$(this).prev().attr("class");
		if(imgName){
			if(imgName.indexOf("_arr")>-1){
				var url=$(this).prev().attr("src");
				url=url.replace(imgName,imgName.replace("_arr",""));
				$(this).prev().attr("src",url);
				$(this).prev().attr("class",imgName.replace("_arr",""));
			}
		}
	});


	$(".vr_style .vr_style_type select").change(function(){
		var code="";
		$(".vr_style .vr_style_type select option:selected").each(function(){
			code +=$(this).val()+",";
		})
		var chosetext=$(this).find("option:selected").text();
		$(this).prev().text(chosetext);
		/*queryVr(code);*/
	});

	//联系人名片分页
	$(".personCardContent .pageShow").change(function(){
		var total=$(".personCardContent .pagination .totalPage").text();
		var page=$(".personCardContent .pageShow").val();
		var reg= /^[0-9]*[1-9][0-9]*$/;
		if(reg.test(page)){
			if(Number(page)<=Number(total) && Number(page)>0){
				$(".personCardContent .pageShow").val(Number(page));
			}else{
				$(".personCardContent .pageShow").val(1);
			}
			 personCardOther($(".certFlag").val());
		}else{
			if(Number(page)>0 && Number(page)<=Number(total)){
				$(".personCardContent .pageShow").val(Math.floor(Number(page)));
			}else{
				$(".personCardContent .pageShow").val(1);
			}
			personCardOther($(".certFlag").val());
		}
	});

	$(".personCardContent .pagination select").change(function(){
		var n=$(this).find("option:selected").text();
		var t=Number($(".personCardContent .pagination .total").text());
		var totalPage=Math.ceil(t/Number(n));
        $(".personCardContent .pagination .totalPage").text(totalPage);
        $(".personCardContent .pageShow").val(1);
        personCardOther($(".certFlag").val());
	});
	//联系人分页结束

	//小程序分页
	$(".wxSmallProgramContent .pageShow").change(function(){
		var total=$(".wxSmallProgramContent .pagination .totalPage").text();
		var page=$(".wxSmallProgramContent .pageShow").val();
		var reg= /^[0-9]*[1-9][0-9]*$/;
		if(reg.test(page)){
			if(Number(page)<=Number(total) && Number(page)>0){
				$(".wxSmallProgramContent .pageShow").val(Number(page));
			}else{
				$(".wxSmallProgramContent .pageShow").val(1);
			}
			showXCX();
		}else{
			if(Number(page)>0 && Number(page)<=Number(total)){
				$(".wxSmallProgramContent .pageShow").val(Math.floor(Number(page)));
			}else{
				$(".wxSmallProgramContent .pageShow").val(1);
			}
			showXCX();
		}
	});

	$(".wxSmallProgramContent .pagination select").change(function(){
		var n=$(this).find("option:selected").text();
		var t=Number($(".wxSmallProgramContent .pagination .total").text());
		var totalPage=Math.ceil(t/Number(n));
        $(".wxSmallProgramContent .pagination .totalPage").text(totalPage);
        $(".wxSmallProgramContent .pageShow").val(1);
        showXCX();
	});
	//小程序分页结束


	//撤回聊天消息开始
	setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
		//阻止浏览器默认右键点击事件
		$(".removeInfo").bind("contextmenu", function(){
		    return false;
		});
		$(".removeInfo").mousedown(function(e) {
		        //console.log(e.which);
		        //右键为3
		        if (3 == e.which) {
		        	var flag = $(".chexiaoInfo")
		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
		        	if(!flag.is(":visible")){
		        		var chatTime=$(this).attr("data-time");		//聊天时间
    		        	var timestamp = Date.parse(new Date());			//当前时间
    		        	var datainfo= timestamp - chatTime;
    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
    		        		var chatCode=$(this).attr("data-code");	//聊天code
    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
    			            $(this).parent().append(html);
    		        	}
		        	}else{
		        		$(".chexiaoInfo").remove()
		        	}
		        } else if (1 == e.which) {
		            //左键为1
		        }
		});
	},2000);
	//撤回聊天消息结束

});
$('body').on('change','.wordsContent .top select',function(){
	var typeCode=$(this).val();
	moreWord(typeCode);
});

//插入图片
function insertImg(str,id){
	try{
		var selection= window.getSelection ? window.getSelection() : document.selection;
		var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0);

		if (!window.getSelection){
			var selection= window.getSelection ? window.getSelection() : document.selection;
			var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0);
			range.pasteHTML(str);
			range.collapse(false);
			range.select();
		}else{
			range.collapse(false);
			var hasR = range.createContextualFragment(str);
			var hasR_lastChild = hasR.lastChild;
			while (hasR_lastChild && hasR_lastChild.nodeName.toLowerCase() == "br" && hasR_lastChild.previousSibling && hasR_lastChild.previousSibling.nodeName.toLowerCase() == "br") {
				var e = hasR_lastChild;
				hasR_lastChild = hasR_lastChild.previousSibling;
				hasR.removeChild(e)
			}
			if(range.commonAncestorContainer.parentNode.id == id){
				range.insertNode(hasR);
				if (hasR_lastChild) {
					range.setEndAfter(hasR_lastChild);
					range.setStartAfter(hasR_lastChild)
				}
				selection.removeAllRanges();
				selection.addRange(range)
			}else{
				var tt=$("#"+id).html();
				$("#"+id).html(tt+str);
			}
		}
	}catch(e){}

}

/*弹出表情框*/
function showEmoji(){
	$(".zhegai").show();
	$(".emoji").show();
	$(".huodong2").hide();
	$(".huodong1").hide();
	$(".huodong3").hide();
	$(".huodong4").hide();
	var params=window.location.search.substring(1).split("&");
	var memberNo='';
	var merchantNo='';
	for(var i=0;i<params.length;i++){
		if(params[i].indexOf("memberNum")>-1){
			memberNo=params[i].split("=")[1];
		}else if(params[i].indexOf("merchantNo")>-1){
			merchantNo=params[i].split("=")[1];
		}
	}
	//请求表情
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/expressionPackage",
        data:{type:'IM_EMO_JI',merchantNo:merchantNo,memberNo:memberNo},
        dataType:'JSON',
        success:function(result){
            var tag='';
            var html='<li onclick="emojiDetail(0,this)" class="active text-center"><img src="/oms-web/static/admin/images/emoji/ic_wx_emoji.png"></li>';
            for(var i=0;i<result.length;i++){
            	if(result[i].status==1){
            		var m=i+1;
                	html +='<li onclick="emojiDetail('+m+',this)" class="text-center"><img src="'+$("#UploadUrl").val()+result[i].packageLogo+'"></li>';
                	  tag +='<ul class="emj'+m+'" style="display:none">';
                	  if(result[i].emojiList!=undefined){
                		  for(var n=0;n< result[i].emojiList.length;n++){
                      		tag +='<li><input type="hidden" value="'+result[i].emojiList[n].code+'" class="imgCode">';
                      		tag +='<input type="hidden" value="'+result[i].emojiList[n].emojiName+'" class="emojiName"><input type="hidden" value="'+$("#UploadUrl").val()+result[i].emojiList[n].emojiUrl+'" class="emojiUrl">';
                      		tag +='<img title="'+result[i].emojiList[n].emojiName+'" onclick="chosePic(this,\'piction\')" src="'+$("#UploadUrl").val()+result[i].emojiList[n].emojiUrl+'"></li>';
                      	}
                	  }

                	tag +='</ul>';
            	}
            }

            tag +='<ul class="emj0">'
        	for(var i=1;i<100;i++){
        		if(i>78 && i<87){
        			continue;
        		}
        		if(i>92 && i<98){
        			continue;
        		}
        		if(i<10){
        			tag +='<li><img onclick="chosePic(this)" src="/oms-web/static/admin/images/emoji/emoji_00'+i+'.png"></li>';
        		}else{
        			tag +='<li><img onclick="chosePic(this)" src="/oms-web/static/admin/images/emoji/emoji_0'+i+'.png"></li>';
        		}

        	}
            tag +='</ul>';
        	$(".zhegai .emoji .showEmoji").html(tag);

        	if(result.length>6){
        		$(".emojiMore .next").attr("src","/oms-web/static/admin/images/imImages/arrowqs.png");
        	}
        	$(".emojiList .emojitab ul").html(html);
        }
    });

	$(document).bind( "touchmove", function (e) {
		   e.preventDefault();
		});
}

function emojiDetail(num,evnet){
	$(evnet).addClass("active");
	$(evnet).siblings().removeClass("active");
	$(".zhegai .emoji .showEmoji .emj"+num).show();
	$(".zhegai .emoji .showEmoji .emj"+num).siblings().css("display","none");
}
/*查询VR素材*/
function queryVr(codes){
	var memberNo=getQueryString("code");
	var shopType=$("#shopType").val();
	var shopNo=$("#shopNo").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/expressionPackage",
        data:{type:"VR_MATERIAL",memberNo:memberNo,shopNo:shopNo,shopType:shopType,codes:codes,limit:1000},
        dataType:'JSON',
        success:function(result){
        	//console.log(result);
    		var html='';
    		for(var i=0;i<result.length;i++){
        		if(i==0){
            		html +='<p class="active oneLine " onclick=\'javascript:showContent("'+encodeURI(result[i].linkUrl)+'","hd1",'+i+')\'>'+result[i].title;
            		html +='<input type="hidden" value="'+result[i].linkUrl+'" class="shareUrl">';
            		html +='<input type="hidden" value="'+result[i].brief+'" class="shareDes">';
            		html +='<input type="hidden" value="'+$('#UploadUrl').val()+result[i].imgAddr+'" class="img"></p>';
            	}else{
            		html +='<p class="oneLine " onclick=\'javascript:showContent("'+encodeURI(result[i].linkUrl)+'","hd1",'+i+')\'>'+result[i].title;
            		html +='<input type="hidden" value="'+result[i].linkUrl+'" class="shareUrl">';
            		html +='<input type="hidden" value="'+result[i].brief+'" class="shareDes">';
            		html +='<input type="hidden" value="'+$('#UploadUrl').val()+result[i].imgAddr+'" class="img"></p>';
            	}
        	}
        	$(".huodong1 .vr_info").html(html);
        	if(result.length>0){
        	$(".huodong1 .vr_info p")[0].click();
        	}

        }
	});
}


/*活动弹框*/
function huodong(param){
	$(".zhegai").show();
	var shopNo=$("#shopNo").val();
	var params=window.location.search.substring(1).split("&");
	var memberNo='';
	var merchantNo='';
	for(var i=0;i<params.length;i++){
		if(params[i].indexOf("memberNo")>-1 && params[i].split("=")[0]=="memberNo"){
			memberNo=params[i].split("=")[1];
		}else  if(params[i].indexOf("merchantNo")>-1){
			merchantNo=params[i].split("=")[1];
		}
	}
	if(param=='huodong1'){		//素材
		$(".huodong1").show();
		$(".huodong2").hide();
		$(".huodong3").hide();
		$(".huodong4").hide();
		$(".emoji").hide();
		$(".huodong1 .hdtitle ul li").removeClass("active");
		$($(".huodong1 .hdtitle ul li")[0]).addClass("active");
		/*queryVr();*/
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/expressionPackage",
	        data:{type:'MATERIAL',merchantNo:merchantNo,memberNo:memberNo,shopNo:shopNo},
	        dataType:'JSON',
	        success:function(result){
	        	//console.log(result);
	        	var html='';

	        	//公共素材
	        	var com=result.materialCommenGroup;
	        	for(var i=0;i<com.length;i++){
	        		if(i==0){
	        			html += '<div class="mateInfo"><div  class=" oneLine stair active" onclick="showList(this)">'+com[i].materialTypeName+'</div>';
	        			html +='<ul class="scdMate" style="display:block;">';
	        		}else{
	        			html += '<div class="mateInfo"><div class=" oneLine stair " onclick="showList(this)">'+com[i].materialTypeName+'</div>';
	        			html +='<ul class="scdMate">';
	        		}

        			for(var n=0;n<com[i].datas.length;n++){
        				var urlH5=com[i].datas[n].urlH5;
        				if(urlH5.indexOf("http")<0){	//相对路径
        					urlH5=$('#UploadUrl').val()+com[i].datas[n].urlH5;
        				}
        				if(n==0){
        					html += '<li class="oneLine active"><input type="hidden" value="'+urlH5+'" class="hd1URL">';
        					if(com[i].datas[n].brief){
        						html +='<input type="hidden" value="'+com[i].datas[n].brief+'" class="brief">';
        					}else{
        						html +='<input type="hidden" value="'+com[i].datas[n].content.substring(0,50)+'" class="brief">';
        					}
    						html += '<input type="hidden" value="'+$('#UploadUrl').val()+com[i].datas[n].imgAddr+'" class="hd1imgAddr"><a href=\'javascript:showContent("'+urlH5+'","hd1",'+n+')\'>'+com[i].datas[n].title+'</a></li>';
        				}else{
        					html += '<li class="oneLine"><input type="hidden" value="'+urlH5+'" class="hd1URL">';
        					if(com[i].datas[n].brief){
        						html +='<input type="hidden" value="'+com[i].datas[n].brief+'" class="brief">';
        					}else{
        						html +='<input type="hidden" value="'+com[i].datas[n].content.substring(0,50)+'" class="brief">';
        					}
    						html += '<input type="hidden" value="'+$('#UploadUrl').val()+com[i].datas[n].imgAddr+'" class="hd1imgAddr"><a href=\'javascript:showContent("'+urlH5+'","hd1",'+n+')\'>'+com[i].datas[n].title+'</a></li>';
        				}

        			}

        			html +='</ul></div>';
	        	}
	        	$(".huodong1 .common_mate").html(html);

	        	//个人素材
	        	var person=result.materialGroup;
	        	var phtml='';
	        	if(person!=undefined){
	        		for(var j=0;j<person.length;j++){
		        		if(j==0){
		        			phtml += '<div class="mateInfo"><div  class=" oneLine stair active" onclick="showList(this)">'+person[j].materialTypeName+'</div>';
		        			phtml +='<ul class="scdMate" style="display:block;">';
		        		}else{
		        			phtml += '<div class="mateInfo"><div class=" oneLine stair " onclick="showList(this)">'+person[j].materialTypeName+'</div>';
		        			phtml +='<ul class="scdMate">';
		        		}
	        			for(var m=0;m<person[j].datas.length;m++){
	        				var urlH5p=person[j].datas[m].urlH5;
	        				if(urlH5p.indexOf("http")<0){	//相对路径
	        					urlH5p=$('#UploadUrl').val()+person[j].datas[m].urlH5;
	        				}
	        				if(m==0){
	        					phtml += '<li class="oneLine active"><input type="hidden" value="'+urlH5p+'" class="hd1URL">';
	        					if(person[j].datas[m].brief){
	        						phtml +='<input type="hidden" value="'+person[j].datas[m].brief+'" class="brief">';
	        					}else{
	        						phtml +='<input type="hidden" value="'+person[j].datas[m].content.substring(0,50)+'" class="brief">';
	        					}

	    						phtml +='<input type="hidden" value="'+$('#UploadUrl').val()+person[j].datas[m].imgAddr+'" class="hd1imgAddr"><a href=\'javascript:showContent("'+urlH5p+'","hd1",'+m+')\'>'+person[j].datas[m].title+'</a></li>';
	        				}else{
	        					phtml += '<li class="oneLine"><input type="hidden" value="'+urlH5p+'" class="hd1URL">';
	        					if(person[j].datas[m].brief){
	        						phtml +='<input type="hidden" value="'+person[j].datas[m].brief+'" class="brief">';
	        					}else{
	        						phtml +='<input type="hidden" value="'+person[j].datas[m].content.substring(0,50)+'" class="brief">';
	        					}
	    						phtml += '<input type="hidden" value="'+$('#UploadUrl').val()+person[j].datas[m].imgAddr+'" class="hd1imgAddr"><a href=\'javascript:showContent("'+urlH5p+'","hd1",'+m+')\'>'+person[j].datas[m].title+'</a></li>';
	        				}

	        			}
	        			phtml +='</ul></div>';
		        	}
		        	$(".huodong1 .personnal").html(phtml);
	        	}
	        	var chos=$(".huodong1 .hd_list .hdtitle li.active").attr("id");
	        	if(chos=="common_mate"){
	        		$(".huodong1 .hd_con .hd_list .VR_mate").hide();
	        		$(".huodong1 .hd_con .hd_list .personnal").hide();
	        		$(".huodong1 .hd_con .hd_list .common_mate").show();
	        		if($(".huodong1 .common_mate li.active a")[0]){
	        			$(".huodong1 .common_mate li.active a")[0].click();
	        		}else{
	        			$("#hd1").attr("src","");

	        		}

	        	}else if(chos=="personnal"){
	        		$(".huodong1 .hd_con .hd_list .VR_mate").hide();
	        		$(".huodong1 .hd_con .hd_list .personnal").show();
	        		$(".huodong1 .hd_con .hd_list .common_mate").hide();
	        		$(".huodong1 .common_mate li.active a")[0].click();
	        	}else if(chos=="VR_mate"){
	        		$(".huodong1 .hd_con .hd_list .VR_mate").show();
	        		$(".huodong1 .hd_con .hd_list .personnal").hide();
	        		$(".huodong1 .hd_con .hd_list .common_mate").hide();
	        	}
	        }
		});
	}else if(param=='huodong2'){	//活动
		$(".huodong2").show();
		$(".huodong3").hide();
		$(".huodong1").hide();
		$(".huodong4").hide();
		$(".emoji").hide();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/expressionPackage",
	        data:{type:'ACTIVITY',merchantNo:merchantNo,memberNo:memberNo},
	        dataType:'JSON',
	        success:function(result){
	        	var html='';
	        	var upUrl=($('#UploadUrl').val()+$("#ctx").val()).replace(/(\/\/oms)/g,"\/oms");
	        	for(var i=0;i<result.length;i++){
	        		var startDate =new Date();
	        		startDate.setTime(result[i].startDate);
	            	var tt=startDate.getMonth()+1;

	            	if(i==0){
	            		if(result[i].linkUrl==""){
	            			html +='<div class="hd_list_det active"><a href=\'javascript:showContent("'+upUrl+'/cm/activity/viewH5?code='+
		            		result[i].code+'","target",'+i+')\'>';
	            		}else{
	            			html +='<div class="hd_list_det active"><a href=\'javascript:showContent("'+result[i].linkUrl+'","target",'+i+')\'>';
	            		}

	            	}else{
	            		if(result[i].linkUrl==""){
	            			html +='<div class="hd_list_det"><a href=\'javascript:showContent("'+upUrl+'/cm/activity/viewH5?code='+
		            		result[i].code+'","target",'+i+')\'>';
	            		}else{
	            			html +='<div class="hd_list_det"><a href=\'javascript:showContent("'+result[i].linkUrl+'","target",'+i+')\'>';
	            		}

	            	}
	            	if(result[i].linkUrl==""){
	            		html +='<input type="hidden" value="'+upUrl+'/cm/activity/viewH5?code='+
	            		result[i].code+'" class="hdShareUrl">'
            		}else{
            			html +='<input type="hidden" value="'+result[i].linkUrl+'" class="hdShareUrl">'
            		}

	        		html +='<img alt=""  src="'+$('#UploadUrl').val()+result[i].showImgAddr+'">';
	        		html +='<p><span>'+result[i].title+'</span><span>';
	        		if(result[i].startDate!=undefined){
	        			html +=startDate.getFullYear()+"-"+tt+"-"+startDate.getDate()+" "+startDate.getHours()+":"+startDate.getMinutes()+":"+startDate.getSeconds();
	        		}
	        		html +='</span></p></a></div>';

	        	}

	        	$(".huodong2 .hd_list").html(html);
	        	$(".huodong2 .hd_list .hd_list_det a")[0].click();

	        }
		});
	}else if(param=='huodong3'){	//优惠券
		$(".huodong3").show();
		$(".emoji").hide();
		$(".huodong1").hide();
		$(".huodong2").hide();
		$(".huodong4").hide();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/expressionPackage",
	        data:{type:'COUPON',merchantNo:merchantNo,memberNo:memberNo},
	        dataType:'JSON',
	        success:function(result){
	        	console.log(result);
	        	var html='';

	        	for(var i=0;i<result.length;i++){
	        		var startDate =new Date();
	        		startDate.setTime(result[i].beginDate);
	            	var tt=startDate.getMonth()+1;

	            	var endDate =new Date();
	            	endDate.setTime(result[i].endDate);
	            	var endtt=endDate.getMonth()+1;

	            	if(i==0){
	            		html +='<div class="hd_list_det active"><a href=\'javascript:showContent("'+result[i].clientUrl+'","hd3",'+i+')\'>';
	            	}else{
	            		html +='<div class="hd_list_det"><a href=\'javascript:showContent("'+result[i].clientUrl+'","hd3",'+i+')\'>';
	            	}
	            	html +='<input type="hidden" value="'+result[i].shareUrl+'" class="yhjShareUrl"><input type="hidden" value="'+result[i].code+'" class="couponRuleCode"><input type="hidden" value="'+result[i].merchantLogoUrl+'" class="merchantLogoUrl">'
	        		html +='<div class="rollList"><div class="roll_left text-center"><p>￥<span>'+result[i].couponNotes+'</span></p>';
	        		html +='<p class="oneLine">'+result[i].couponRemark+'<span></div>';
	        		html +='<div class="roll_right"><p>'+result[i].couponName+'</p><p>';
	        		if(result[i].startDate!=undefined){
	        			html +=startDate.getFullYear()+"."+tt+"."+startDate.getDate()+"~"+endDate.getFullYear()+"."+endtt+"."+endDate.getDate();
	        		}
	        		html +='</p><p class="oneLine">使用范围：'+result[i].shopName+'</p>';
	        		html += '<p>说明：'+result[i].couponRemark+'</p></div></div></a></div>';

	        	}
	        	$(".huodong3 .hd_list").html(html);
	        	$(".huodong3 .hd_list .hd_list_det a")[0].click();

	        }
		});
	}else if(param=="huodong4"){
		$(".huodong4").show();
		$("#mapPage").attr("src","http://apis.map.qq.com/tools/locpicker?search=1&type=1&key=YF4BZ-OFRAW-KJIRS-O52PO-ZLUIK-6SFDS&referer=myapp")
		$(".emoji").hide();
		$(".huodong1").hide();
		$(".huodong2").hide();
		$(".huodong3").hide();
	}
}


function sendBox() {
	var lat = sessionStorage.getItem("lat");
	var lng = sessionStorage.getItem("lng");
	//console.log(lat,lng);
	var poiaddress = sessionStorage.getItem("poiaddress");
	var poiname = sessionStorage.getItem("poiname");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
// 	var noWx = $('.oneLineTwo .wxNum').html().trim() //客户微信号
 	var guideWxId = $('#guideWxId').val();//导购微信号
 	var msgId = uuid();
 	$.ajax({
    	type:"post",
    	url:$('#ctx').val() + "/im/sendChatMessage",
    	data:{
    		msgId:msgId,
    		memberNo:memberNo,
    		type:48,
    		content:JSON.stringify({label:poiaddress,lng:lng,lat:lat,scale:13,poiname:poiname}),
    		resources:"/n",
    		chatroomType:2,
    		chatroomNoWx:guideWxId
    		},
    	dataType:'JSON',
    	success:function(res) {
    		$(".emoticon .allCard").hide();
    		console.log(poiname)
    		if(res['true'] =="发送成功"){
    			console.log(1111)
			 	$('.huodong4').hide();
			 	$('.zhegai').hide();
			 	$('#container').css('display','block');
			 	var html = "";
			 	html += '<div class="mine personCard sendAll removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'>';
			 	html +='<div class="mapToy" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+poiaddress+'\')"><div class="mapCon"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ poiaddress +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ msgId +'" class="containerMap">' +'</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
			 	html +=	'</div>';
			 	$(".person .sign").before(html);
			 	 var center = new qq.maps.LatLng(lat,lng);
			      var map = new qq.maps.Map(document.getElementById(msgId), {
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
			 	$(".person").scrollTop($(".person")[0].scrollHeight);
			 	HISTORYCHATLIST.push(msgId);

				$(".removeInfo").bind("contextmenu", function(){
				    return false;
				});
				$(".removeInfo").mousedown(function(e) {
	    		        //console.log(e.which);
	    		        //右键为3
	    		        if (3 == e.which) {
	    		        	var flag = $(".chexiaoInfo")
	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    		        	if(!flag.is(":visible")){
	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
    	    		        	var timestamp = Date.parse(new Date());			//当前时间
    	    		        	var datainfo= timestamp - chatTime;
    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
    	    			            $(this).parent().append(html);
    	    		        	}
	    		        	}else{
	    		        		$(".chexiaoInfo").remove()
	    		        	}

	    		        } else if (1 == e.which) {
	    		            //左键为1
	    		        }
	    		});
    		}else if(res.zkclient_offline == "中控客户端已离线，发送失败！") {
			 	$('.huodong4').hide();
			 	$('.zhegai').hide();
			 	html='<div class="sendTime text-center"><span>'+res.zkclient_offline+'</span></div>';
			 	$(".person .sign").before(html);
    		}
    	}
    })

}

function clickMap(lat,lng,poiaddress) {
	var url = "http://map.qq.com/?type=marker&isopeninfowin=1&markertype=1&name="+returnCitySN["cname"]+"&addr="+poiaddress+"&pointy="+lat+"&pointx="+lng;
	window.open(url);
}
var times;
function clickTrusteeship() {//启动托管
	$('.trusteeship').show();
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var memberNoGm=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.guidMemNo').val() :getQueryString('memberNoGm')||'';
	times = setInterval(function() {
		$.ajax({
			type:"get",
	        url: $('#ctx').val()+"/im/autoAnswer",
	        data:{
	        	memberNo:memberNo,
	        	memberNoGm:memberNoGm,
	        	type:1
	        },
	        dataType:'JSON',
	        success:function(res){
	        	if(res.matchCode==10000){
	        		var result = res.result;
	        		console.log(result[0].problemWord)
	        		var html='';
		    		for(var i=0;i<result.length;i++) {
		    			var t=Number(i)+1;
		    			if(result[i].problemContent.indexOf('?')!=-1){
		    				html +=t +":" + result[i].problemContent +"\n";
		    			}else{
		    				html +=t +":" + result[i].problemContent +"?\n";
		    			}
		    			html +="答："+result[i].answers.answerContent+"\n";
		    		}
		    		html =html.substring(0,html.length-1);
		    		console.log(html);
		    		$(".sendInfo").html(html);
		    		sendSub(1,null,"true");
	        	}else if(res.matchCode==60000){
//	        		html='<div class="sendTime text-center"><span>'+"中控客户端已离线，发送失败！"+'</span></div>';
//				 	$(".person .sign").before(html);
	        	}

	        }
		});

	},Math.random()*5000 + 5000)
}

function exitTrusteeship() { //退出托管
	$('.trusteeship').hide();
	clearInterval(times)
}


//下拉展示素材
function showList(event){
	var flag=$(event).hasClass("active");
	if(flag){
		$(event).removeClass("active");
		$($(event).parent().find(".scdMate")).css("display","none");
	}else{
		$(event).addClass("active");
		$($(event).parent().find(".scdMate")).css("display","block");
		$($(event).parent().siblings().find(".stair")).removeClass("active");
		$($(event).parent().siblings().find(".scdMate")).css("display","none");
	}
}

/*上一个图片*/
function prevTab(){
	var tabNum=$(".emojiList li").length;
	var total=tabNum*70;
	var left=$(".emojiList .emojitab ul").css("margin-left");
	if(Number(left.split("px")[0])<0){
		left =Number(left.split("px")[0]) + 70;
		$(".emojiList .emojitab ul").css("margin-left",left+"px");
		$(".emojiMore .next").attr("src","/oms-web/static/admin/images/imImages/arrowqs.png");
	}else{
		$(".emojiMore .prev").attr("src","/oms-web/static/admin/images/imImages/arrowqa.png");
	}
}
/*下一个图片*/
function nextTab(){
	var tabNum=$(".emojiList li").length;
	var total=tabNum*70;
	var left=$(".emojiList .emojitab ul").css("margin-left");
	var newLeft=Math.abs(Number(left.split("px")[0]))+463;
	if(total>463 && newLeft<=total){
		left =Number(left.split("px")[0]) - 70;
		$(".emojiList .emojitab ul").css("margin-left",left+"px");
		$(".emojiMore .prev").attr("src","/oms-web/static/admin/images/imImages/arrowqs1.png");
	}else{
		$(".emojiMore .next").attr("src","/oms-web/static/admin/images/imImages/arrowqa1.png");
	}

}
/*选中图片*/
function chosePic(event,info){
	var getPath= $(event).attr("src");
	var textInfo=$(".sendInfo").html();
	if("piction"==info){
		textInfo ='<img src="'+getPath+'" class="smallEmoji"/>';
	}else{
		//textInfo +='<img src="'+getPath+'" style="width:30px" />';
		insertImg('<img src="'+getPath+'" style="width:30px" />',"txtContent");
	}
	$(".zhegai").hide();
	if("piction"==info){
		var inf=$(".sendInfo").html();
		window.localStorage.setItem("piction1",inf);
		$(".sendInfo").html("")
		$(".sendInfo").html(textInfo);
		sendSub(47,event);
	}
}

/*上传图片*/
function upImg(fileId,event){
	var msgId=uuid();
	var params=window.location.search.substring(1).split("&");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	 
	var $file = $(event);
      var objUrl = $file[0].files[0];
      var fileType=objUrl.type;
      if("image/gif,image/jpeg,image/jpg,image/png,image/svg".indexOf(fileType)<0){
    	 	var msg ="暂时只能发图片哦!";
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
    	  var guideWxId=$('#guideWxId').val();
   	   $.ajaxFileUpload({
   	        url: $('#ctx').val() + '/im/sendChatMessage', //用于文件上传的服务器端请求地址
   	        secureuri: false, //是否需要安全协议，一般设置为false
   	        fileElementId: fileId, //文件上传域的ID
   	        type:'post',
   	        data:{
   	        	msgId:msgId,memberNo:memberNo,type:3,chatroomType:2,chatroomNoWx:guideWxId
   			},
   	        success: function (result) {//服务器成功响应处理函数
   	        	 var str = $(result).find("body").text();//获取返回的字符串
   	        	 var json = $.parseJSON(str);//把字符串转化为json对象
   	        	 var html='';
   	        	 if(json['zkclient_offline']){
   	        		 html='<div class="sendTime text-center"><span>'+json['zkclient_offline']+'</span></div>'
   	        	 }else{
   	        		 var $file = $(event);
   	 	            var objUrl = $file[0].files[0];
   	 	            //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
   	 	            var windowURL = window.URL || window.webkitURL;
   	 	            //createObjectURL创建一个指向该参数对象(图片)的URL
   	 	            var dataURL;
   	 	            dataURL = windowURL.createObjectURL(objUrl);
   	     			html +='<div class="mine general sendAll">';

   	     			if(json['true']){
   	     				html +='<div class="information removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><img src="'+dataURL+'" onclick="findChatImage(\'\',\''+dataURL+'\')" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
   	     			}else if(json['include_sensitive_words']){
   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
   	 					html +='<div class="information"><img src="'+dataURL+'" onclick="findChatImage(\'\',\''+dataURL+'\')" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
   	 					html += '<div class="errorInfo" style="display: block">'+json['include_sensitive_words']+'</div>';
   	     			}else{
   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
   	 					html +='<div class="information"><img src="'+dataURL+'" onclick="findChatImage(\'\',\''+dataURL+'\')" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
   	 					html += '<div class="errorInfo" style="display: block">'+json['false']+'</div>';
   	     			}

   	     			HISTORYCHATLIST.push(msgId);
   	        	 }
   	        	 $(".person .sign").before(html);
   	     			$(".sendInfo").html('');
   	     			$(" .person").scrollTop($(" .person")[0].scrollHeight);

   	     		$(".removeInfo").bind("contextmenu", function(){
	   	 		    return false;
	   	 		});
	   	 		$(".removeInfo").mousedown(function(e) {
	    		        //console.log(e.which);
	    		        //右键为3
	    		        if (3 == e.which) {
	    		        	var flag = $(".chexiaoInfo")
	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    		        	if(!flag.is(":visible")){
	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
    	    		        	var timestamp = Date.parse(new Date());			//当前时间
    	    		        	var datainfo= timestamp - chatTime;

    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
    	    			            $(this).parent().append(html);
    	    		        	}
	    		        	}else{
	    		        		$(".chexiaoInfo").remove()
	    		        	}

	    		        } else if (1 == e.which) {
	    		            //左键为1
	    		        }
	    		});

   	        }
   	    })
      }

}

/*上传视频*/
function upVideo(fileId,event){
	var msgId=uuid();
	var params=window.location.search.substring(1).split("&");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var merchantNo=getQueryString('merchantNo')||'';
	 var $file = $(event);
      var objUrl = $file[0].files[0];
	  if(objUrl!=undefined){
			var url = URL.createObjectURL(objUrl);
		  	console.log(url);
		  	document.getElementById("aa").src=url;
	  }
      var fileType=objUrl.type;
      var size= objUrl.size;			//文件大小
      if("video/mp4".indexOf(fileType)<0){
    	 	var msg ="暂时只支持MP4格式哦!";
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
    	  if(size>10*1024*1024){		//文件大于10M
        	  var msg ="文件大于10M发送可能失败，确定发送吗!";
    	   		 options = {
    				confirm:function(){
    					var guideWxId = $('#guideWxId').val();//导购微信号
  				   	   $.ajaxFileUpload({
  				   	        url: $('#ctx').val() + '/im/sendChatMessage', //用于文件上传的服务器端请求地址
  				   	        secureuri: false, //是否需要安全协议，一般设置为false
  				   	        fileElementId: fileId, //文件上传域的ID
  				   	        type:'post',
  				   	        data:{
  				   	        	msgId:msgId,memberNo:memberNo,type:43,chatroomType:2,chatroomNoWx:guideWxId
  				   			},
  				   	        success: function (result) {//服务器成功响应处理函数
  				   	        	 var str = $(result).find("body").text();//获取返回的字符串
  				   	        	 var json = $.parseJSON(str);//把字符串转化为json对象
  				   	        	 var html='';
  				   	        	 if(json['zkclient_offline']){
  				   	        		 html='<div class="sendTime text-center"><span>'+json['zkclient_offline']+'</span></div>'
  				   	        	 }else{
  				   	        		 var $file = $(event);
  				   	 	            var objUrl = $file[0].files[0];
  				   	 	            //createObjectURL创建一个指向该参数对象(图片)的URL
  				   	 	            var dataURL;
  				   	 	            dataURL = URL.createObjectURL(objUrl);
  				   	     			html +='<div class="mine general sendAll">';

  				   	     			if(json['true']){
  				   	     				html +='<div class="information  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
  				   	     			}else if(json['include_sensitive_words']){
  				   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
  				   	 					html +='<div class="information"><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
  				   	 					html += '<div class="errorInfo" style="display: block">'+json['include_sensitive_words']+'</div>';
  				   	     			}else{
  				   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
  				   	 					html +='<div class="information"><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
  				   	 					html += '<div class="errorInfo" style="display: block">'+json['false']+'</div>';
  				   	     			}

  				   	     			HISTORYCHATLIST.push(msgId);
  				   	        	 }
  				   	        	 $(".person .sign").before(html);
			   	     			$(".sendInfo").html('');
			   	     			$(" .person").scrollTop($(" .person")[0].scrollHeight);


			   	     		$(".removeInfo").bind("contextmenu", function(){
				   	 		    return false;
				   	 		});
			   	 			$(".removeInfo").mousedown(function(e) {
		    	    		        //console.log(e.which);
		    	    		        //右键为3
		    	    		        if (3 == e.which) {
		    	    		        	var flag = $(".chexiaoInfo")
		    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
		    	    		        	if(!flag.is(":visible")){
		    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
			    	    		        	var timestamp = Date.parse(new Date());			//当前时间
			    	    		        	var datainfo= timestamp - chatTime;
			    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
			    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
			    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
			    	    			            $(this).parent().append(html);
			    	    		        	}
		    	    		        	}else{
		    	    		        		$(".chexiaoInfo").remove()
		    	    		        	}

		    	    		        } else if (1 == e.which) {
		    	    		            //左键为1
		    	    		        }
		    	    		});

  				   	        }
  				   	    })
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
          }else{
        	  var guideWxId = $('#guideWxId').val();//微信号
		   	   $.ajaxFileUpload({
		   	        url: $('#ctx').val() + '/im/sendChatMessage', //用于文件上传的服务器端请求地址
		   	        secureuri: false, //是否需要安全协议，一般设置为false
		   	        fileElementId: fileId, //文件上传域的ID
		   	        type:'post',
		   	        data:{
		   	        	msgId:msgId,memberNo:memberNo,type:43,chatroomType:2,chatroomNoWx:guideWxId
		   			},
		   	        success: function (result) {//服务器成功响应处理函数
	   	        		var str = $(result).find("body").text();//获取返回的字符串
		   	        	 var json = $.parseJSON(str);//把字符串转化为json对象
		   	        	 var html='';
		   	        	 if(json['zkclient_offline']){
		   	        		 html='<div class="sendTime text-center"><span>'+json['zkclient_offline']+'</span></div>'
		   	        	 }else{
		   	        		 var $file = $(event);
		   	 	            var objUrl = $file[0].files[0];
		   	 	            //createObjectURL创建一个指向该参数对象(图片)的URL
		   	 	            var dataURL;
		   	 	            dataURL = URL.createObjectURL(objUrl);
		   	     			html +='<div class="mine general sendAll">';

		   	     			if(json['true']){
		   	     				html +='<div class="information  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
		   	     			}else if(json['include_sensitive_words']){
		   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
		   	 					html +='<div class="information"><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
		   	 					html += '<div class="errorInfo" style="display: block">'+json['include_sensitive_words']+'</div>';
		   	     			}else{
		   	     				html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
		   	 					html +='<div class="information"><video src="'+dataURL+'" controls="controls"  style="width:300px; height:200px;"></video></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
		   	 					html += '<div class="errorInfo" style="display: block">'+json['false']+'</div>';
		   	     			}

		   	     			HISTORYCHATLIST.push(msgId);
		   	        	 }
		   	        	 $(".person .sign").before(html);
		   	     			$(".sendInfo").html('');
		   	     			$(" .person").scrollTop($(" .person")[0].scrollHeight);

		   	     		$(".removeInfo").bind("contextmenu", function(){
			   	 		    return false;
			   	 		});
		   	 			$(".removeInfo").mousedown(function(e) {
	    	    		        //console.log(e.which);
	    	    		        //右键为3
	    	    		        if (3 == e.which) {
	    	    		        	var flag = $(".chexiaoInfo")
	    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    	    		        	if(!flag.is(":visible")){

	    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
		    	    		        	var timestamp = Date.parse(new Date());			//当前时间
		    	    		        	var datainfo= timestamp - chatTime;
		    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
		    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
		    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
		    	    			            $(this).parent().append(html);
		    	    		        	}
	    	    		        	}else{
	    	    		        		$(".chexiaoInfo").remove()
	    	    		        	}

	    	    		        } else if (1 == e.which) {
	    	    		            //左键为1
	    	    		        }
	    	    		});
		   	        }
		   	    })
          }

      }

}

//获取视频时长
function myFunction(ele) {
	var hour = parseInt((ele.duration)/3600);
	var minute = parseInt((ele.duration%3600)/60);
	var second = Math.ceil(ele.duration%60);
	console.log(hour+":"+minute+":"+second);
	//document.write("这段视频的时长为："+hour+"小时，"+minute+"分，"+second+"秒");
//	document.getElementById("getDuration").innerHTML="这段视频的时长为："+hour+"小时，"+minute+"分，"+second+"秒";
}


/*发送信息*/
function sendSub(num,event,robit){
	$('#loadFlayTwo').css('display','block')
	var info=$(".sendInfo").html().trim();
	var msgId=uuid();
	var params=window.location.search.substring(1).split("&");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var merchantNo=getQueryString('merchantNo')||'';

	var guideWxId=$("#guideWxId").val();
	var localT = parseInt(window.localStorage.getItem("chatstart"))||parseInt(window.localStorage.getItem("guidechatstart"));
	if(num==1){		//文本
		console.log($('#txtContent').html());
		if($('#txtContent').html().length>2000){
			alert("发送内容不能超过2000");
			$('#loadFlayTwo').css('display','none');
		}else{
			info=$.trim(info).replace(/<\/?((div)|(p\/?))>/g,"").replace(/&nbsp;/g,"").replace(/<br>/gi,"\n").replace(/<br type="_moz">/gi,"\n"); //info.replace(/<\/?((div)|(br\/?)|(p\/?))>/g,"").replace(/&nbsp;/g,"");
			var sendinfo=info;
			if($.trim(info)!=''){
				 var imgs=info.match(/<img[^>]+>/g);
				 if(imgs!=null){
					 for(var m=0;m<imgs.length;m++){
						 var key= imgs[m].split("emoji/")[1].split(".")[0];
						 sendinfo =sendinfo.replace(imgs[m],emoji[key]);
					 }
				 }else{
					 sendinfo=info;
				 }
				$.ajax({
			        type:"POST",
			        url:$('#ctx').val() + "/im/sendChatMessage",
			        data:{msgId:msgId,memberNo:memberNo,type:num,content:removeHtmlTab(sendinfo),chatroomType:2,
			    		chatroomNoWx:guideWxId},
			        dataType:'JSON',
			        success:function(result){
			        		if(robit=="true" ){
			        			var index=HISTORYCHATLIST.indexOf(msgId);
			        			HISTORYCHATLIST.splice(index,1);
			        			
			        			clock(memberNo,guideWxId);
			        			
			        			$("#txtContent").html("");
			        		}else{
			        			var html='';
					        	 if(result['zkclient_offline']){
					        		 html='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
					        	 }else{
					        		 var now=Number(new Date());
							        	if(now-localT>(1000 * 60 * 5)){
							        		html +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
							        		if(window.localStorage.getItem("chatstart")!=null){
							        			window.localStorage.setItem("chatstart", now);
							        		}
							        		if(window.localStorage.getItem("guidechatstart")!=null){
							        			window.localStorage.setItem("guidechatstart", now);
							        		}

							        	}
							        	html +='<div class="mine general sendAll">';
						        		if(result['true']){
											html +='<div class="information  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'>'+$.trim(info).replace(/\n/gi,'<br>')+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        		}else if(result['include_sensitive_words']){
						        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
						        			html +='<div class="information">'+$.trim(info).replace(/\n/gi,'<br>')+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        			html += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
						        		}else{
						        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
						        			html +='<div class="information">'+$.trim(info).replace(/\n/gi,'<br>')+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        			html += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
						        		}
					        	 }

				        		$(".person .sign").before(html);
			        			$(".sendInfo").html('');
			        			$(" .person").scrollTop($(" .person")[0].scrollHeight);

			        			/*撤销*/
			    	    		$(".removeInfo").bind("contextmenu", function(){
			    	    		    return false;
			    	    		});
			    	    		$(".removeInfo").mousedown(function(e) {
			    	    		      //  右键为3
			    	    		        if (3 == e.which) {
			    	    		        	var flag =$(".chexiaoInfo")
			    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
			    	    		        	if(!flag.is(":visible")){
			    	    		        		// console.log("隐藏")
			    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
				    	    		        	var timestamp = Date.parse(new Date());			//当前时间
				    	    		        	var datainfo= timestamp - chatTime;
				    	    		        	//console.log(datainfo)
				    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
				    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
				    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
				    	    			            $(this).parent().append(html);
				    	    		        	}
			    	    		        	}else{
			    	    		        		// console.log("显示")
			    	   		        			$(".chexiaoInfo").remove()
			    	    		        	}

			    	    		        } else if (1 == e.which) {

			    	    		            //左键为1
			    	    		        }
			    	    		});
			        		}
			        		$('#loadFlayTwo').css('display','none')
			        }
			    });
			}else{
				$('#loadFlayTwo').css('display','none')
			}
		}

	}else if(num==3){//图片
		info=$.trim(info).split("\"")[1];
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/sendChatMessage",
	        data:{msgId:msgId,memberNo:memberNo,type:num,resources:info,chatroomType:2,chatroomNoWx:guideWxId},
	        dataType:'JSON',
	        success:function(result){
	        	var html='';
	        	 if(result['zkclient_offline']){
	        		 html='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
	        	 }else{
	        		 var now=Number(new Date());
	 	        	if(now-localT>(1000 * 60 * 5)){
	 	        		html +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
	 	        		if(window.localStorage.getItem("chatstart")!=null){
	 	        			window.localStorage.setItem("chatstart", now);
	 	        		}
	 	        		if(window.localStorage.getItem("guidechatstart")!=null){
	 	        			window.localStorage.setItem("guidechatstart", now);
	 	        		}
	 	        	}
	     			html +='<div class="mine general sendAll">';
	         		if(result['true']){
	         			html +='<div class="information  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><img src="'+$(".sendInfo").html()+'" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         		}else if(result['include_sensitive_words']){
	         			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	         			html +='<div class="information"><img src="'+$(".sendInfo").html()+'" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         			html += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
	         		}else{
	         			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	         			html +='<div class="information"><img src="'+$(".sendInfo").html()+'" style="width:200px"></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         			html += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
	         		}
	        	 }

        		$(".person .sign").before(html);
    			$(".sendInfo").html('');
    			$(" .person").scrollTop($(" .person")[0].scrollHeight);
    			$('#loadFlayTwo').css('display','none');
    			var f=window.localStorage.getItem("piction1");
    			$("#txtContent").html(f);
    			window.localStorage.removeItem("piction1");

    			/*撤销*/
	    		$(".removeInfo").bind("contextmenu", function(){
	    		    return false;
	    		});
	    		$(".removeInfo").mousedown(function(e) {
	    		        //console.log(e.which);
	    		        //右键为3
	    		        if (3 == e.which) {
	    		        	var flag = $(".chexiaoInfo")
	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    		        	if(!flag.is(":visible")){
	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
    	    		        	var timestamp = Date.parse(new Date());			//当前时间
    	    		        	var datainfo= timestamp - chatTime;
    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
    	    			            $(this).parent().append(html);
    	    		        	}
	    		        	}else{
	    		        		$(".chexiaoInfo").remove()
	    		        	}

	    		        } else if (1 == e.which) {
	    		            //左键为1
	    		        }
	    		});
	        }
	    });
	}else if(num==47){

		info=$(event).parent().find(".emojiUrl").val();
		var content=$(event).parent().find(".imgCode").val();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/sendChatMessage",
	        data:{msgId:msgId,memberNo:memberNo,type:num,resources:info,chatroomType:2,chatroomNoWx:guideWxId,content:content},
	        dataType:'JSON',
	        success:function(result){

	        	console.log(result)
	        	var html='';
	        	 if(result['zkclient_offline']){
	        		 html='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
	        	 }else{
	        		 var now=Number(new Date());
	 	        	if(now-localT>(1000 * 60 * 5)){
	 	        		html +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
	 	        		if(window.localStorage.getItem("chatstart")!=null){
	 	        			window.localStorage.setItem("chatstart", now);
	 	        		}
	 	        		if(window.localStorage.getItem("guidechatstart")!=null){
	 	        			window.localStorage.setItem("guidechatstart", now);
	 	        		}
	 	        	}
	     			html +='<div class="mine general sendAll">';
	         		if(result['true']){
	         			html +='<div class="information  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'>'+$(".sendInfo").html()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         		}else if(result['include_sensitive_words']){
	         			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	         			html +='<div class="information">'+$(".sendInfo").html()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         			html += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
	         		}else{
	         			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	         			html +='<div class="information">'+$(".sendInfo").html()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	         			html += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
	         		}
	        	 }

        		$(".person .sign").before(html);
    			$(".sendInfo").html('');
    			$(".person").scrollTop($(" .person")[0].scrollHeight);
    			$('#loadFlayTwo').css('display','none');
    			var f=window.localStorage.getItem("piction1");
    			$("#txtContent").html(f);
    			window.localStorage.removeItem("piction1");

    			/*撤销*/
	    		$(".removeInfo").bind("contextmenu", function(){
	    		    return false;
	    		});
	    		$(".removeInfo").mousedown(function(e) {
			    	    		        //console.log(e.which);
			    	    		        //右键为3
			    	    		        if (3 == e.which) {
			    	    		        	var flag = $(".chexiaoInfo")
			    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
			    	    		        	if(!flag.is(":visible")){
			    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
				    	    		        	var timestamp = Date.parse(new Date());			//当前时间
				    	    		        	var datainfo= timestamp - chatTime;
				    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
				    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
				    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
				    	    			            $(this).parent().append(html);
				    	    		        	}
			    	    		        	}else{
			    	    		        		$(".chexiaoInfo").remove()
			    	    		        	}

			    	    		        } else if (1 == e.which) {
			    	    		            //左键为1
			    	    		        }
			    	    		});
	        }
	    });
	}else if(num==42){	// 发送名片，包括个人名片和公众号
		info=$("#cardContent .active .n img").attr("src");
		var alias="";
		if($("#cardContent .active .nwa").length>0){
			alias=$("#cardContent .active .nwa").val();
		}
		var certflag= $(".personCardContent .certFlag").val();
		var content ='';
		var gzhCode='';
		var shareDes="";
		var flag="";
		if(certflag == 0){	//个人名片
			content='{"username":"'+$("#cardContent .active .nwx").val()+'","alias":"'+alias+'",'+
			'"certflag":"'+certflag+'",'+'"memberNo":"'+
			$("#cardContent .active .mpmemberNo").val()+'","memberNoGm":"'+$("#cardContent .active .mpmemberNoGm").val()+'"}';
			gzhCode=$("#cardContent .active .mpCode").val();
			flag=0;
		}else if(certflag == 8){	//公众号名片
			content='{"username":"'+$("#cardContent .active .nwx").val()+'","alias":"'+alias+'",'+
			'"certflag":"'+certflag+'"}';
			gzhCode=$("#cardContent .active .gzhCode").val();
		}
		var shareTitle=$("#cardContent .active .d").text();

		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/sendChatMessage",
	        data:{msgId:msgId,memberNo:memberNo,type:num,resources:info,chatroomType:2,chatroomNoWx:guideWxId,content:content,shareTitle:shareTitle,shareDes:shareDes,code:gzhCode,flag:flag},
	        dataType:'JSON',
	        success:function(result){
	        	html +='';
	        	if(result['zkclient_offline']){
	    			html += '<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>';
	        	}else{
	        		html += '<div class="mine personCard">';
		        	if(result['false'] || result['include_sensitive_words']){
		        		html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
		        	}
		        	html +='<div class="shareInfo  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><div class="sendDec">';
					html +='<img alt="" src="'+info+'"><div class="sendDecText" style="display: flex;align-items: center;">';
					html +='<p>'+shareTitle+'</p>';
					html +='</div></div><p class="sendTitle">'
					if($(".personCardContent .certFlag").val() == 0){
						html +='个人名片';
					}else if($(".personCardContent .certFlag").val() == 8){
						html +='公众号名片';
					}
				    html +='</p></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
					if(result['false'] || result['include_sensitive_words']){
		        		html += '<div class="errorInfo" style="display: block">'+(result['include_sensitive_words'] || result['false'])+'</div>';
		        	}
	        	}
	        	$(".person .sign").before(html);
	    		$(".person").scrollTop($(".person")[0].scrollHeight);
	    		$(".personCardBox").css("display","none");

	    		/*撤销*/
	    		$(".removeInfo").bind("contextmenu", function(){
	    		    return false;
	    		});
	    		$(".removeInfo").mousedown(function(e) {
	    		        //console.log(e.which);
	    		        //右键为3
	    		        if (3 == e.which) {
	    		        	var flag = $(".chexiaoInfo")
	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    		        	if(!flag.is(":visible")){
	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
    	    		        	var timestamp = Date.parse(new Date());			//当前时间
    	    		        	var datainfo= timestamp - chatTime;
    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
    	    			            $(this).parent().append(html);
    	    		        	}
	    		        	}else{
	    		        		$(".chexiaoInfo").remove()
	    		        	}

	    		        } else if (1 == e.which) {
	    		            //左键为1
	    		        }
	    		});
	        }
	    });
	}else if(num==494){	// 发送小程序
		info=$("#wxSmallContent .active .n img").attr("src");
		var shareTitle=$("#wxSmallContent .active .d").text();
		var gzhCode=$("#wxSmallContent .active .spcode").val();
		var spDesc=$("#wxSmallContent .active .spDesc").val();
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/sendChatMessage",
	        data:{msgId:msgId,memberNo:memberNo,type:num,code:gzhCode,chatroomType:2,chatroomNoWx:guideWxId},
	        dataType:'JSON',
	        success:function(result){
	        	var html ='';
	        	if(result['zkclient_offline']){
	    			html += '<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>';
	        	}else{
	        		html += '<div class="mine personCard xcxT">';
		        	if(result['false'] || result['include_sensitive_words']){
		        		html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
		        	}
		        	html +='<div class="shareInfo  removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'><div class="sendDec">';
					html +='<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;">';
					 // html +='<p>'+shareTitle+'</p>';
					 html +='<p>'+spDesc.replace(/"/g,"").replace(/'/g,"")+'</p>';
					if(info.indexOf("file.png")==-1){
						html +='<img alt="" src="'+info+'">'
					}
					html +='</div></div><p class="sendTitle">'
						html +='小程序';
				    html +='</p></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
					if(result['false'] || result['include_sensitive_words']){
		        		html += '<div class="errorInfo" style="display: block">'+(result['include_sensitive_words'] || result['false'])+'</div>';
		        	}
	        	}
	        	$(".person .sign").before(html);
	    		$(".person").scrollTop($(".person")[0].scrollHeight);
	    		$(".wxSmallProgramBox").css("display","none");

	    		/*撤销*/
	    		$(".removeInfo").bind("contextmenu", function(){
	    		    return false;
	    		});
	    		$(".removeInfo").mousedown(function(e) {
	    		        //console.log(e.which);
	    		        //右键为3
	    		        if (3 == e.which) {
	    		        	var flag = $(".chexiaoInfo")
	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    		        	if(!flag.is(":visible")){
	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
		    		        	var timestamp = Date.parse(new Date());			//当前时间
		    		        	var datainfo= timestamp - chatTime;
		    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
		    		        		var chatCode=$(this).attr("data-code");	//聊天code
		    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
		    			            $(this).parent().append(html);
		    		        	}
	    		        	}else{
	    		        		$(".chexiaoInfo").remove()
	    		        	}

	    		        } else if (1 == e.which) {
	    		            //左键为1
	    		        }
	    		});

	        }
	    });
	}
	HISTORYCHATLIST.push(msgId);

}

function showContent(content,target,num){
		if(target=="hd1"){
			var typeMate=$("#"+target).parent().prev().find(".hdtitle li.active").attr("id");
			if(typeMate=="VR_mate"){
				$(".huodong1 .hd_list ."+typeMate+" .vr_info p").removeClass("active");
				$($(".huodong1 .hd_list ."+typeMate+" .vr_info p")[num]).addClass("active");
			}else{
				$($(".huodong1 .hd_list ."+typeMate+" div.active").next().children()).removeClass("active");
				$($(".huodong1 .hd_list ."+typeMate+" div.active").next().children()[num]).addClass("active");
			}

		}else{
			$("#"+target).parent().prev().find(".hd_list_det").removeClass("active");
			$($("#"+target).parent().prev().find(".hd_list_det")[num]).addClass("active");
		}
		if("yulan"==target){
			$(".yulan").show();
		}


//		$('#'+target).load(content);
		$('#'+target).attr('src',content);
}

function showContent2(content,target,num){
	if(content.indexOf("yun.kujiale.com")>-1 || content.indexOf("weixin")>-1 ||content.indexOf("qq")>-1 ){
		window.open(content);
	}else{
		if(target=="hd1"){
			var typeMate=$("#"+target).parent().prev().find(".hdtitle li.active").attr("id");
			if(typeMate=="VR_mate"){
				$(".huodong1 .hd_list ."+typeMate+" .vr_info p").removeClass("active");
				$($(".huodong1 .hd_list ."+typeMate+" .vr_info p")[num]).addClass("active");
			}else{
				$($(".huodong1 .hd_list ."+typeMate+" div.active").next().children()).removeClass("active");
				$($(".huodong1 .hd_list ."+typeMate+" div.active").next().children()[num]).addClass("active");
			}

		}else{
			$("#"+target).parent().prev().find(".hd_list_det").removeClass("active");
			$($("#"+target).parent().prev().find(".hd_list_det")[num]).addClass("active");
		}
		if("yulan"==target){
			$(".yulan").show();
		}


//		$('#'+target).load(content);
		$('#'+target).attr('src',content);
	}

}

function hdSend(con){
	var conInfo=$("."+con+" .hd_detail_tbd").attr('src');
	if(conInfo=="" || conInfo==undefined){
		return;
	}
	var localT = parseInt(window.localStorage.getItem("chatstart")) ||parseInt(window.localStorage.getItem("guidechatstart"));
	var info=$(".sendInfo").html();
	var msgId=uuid();
	var params=window.location.search.substring(1).split("&");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var merchantNo=getQueryString('merchantNo')||'';
	/*for(var i=0;i<params.length;i++){
		if(params[i].indexOf("memberNo")>-1 && params[i].split("=")[0]=="memberNo"){
			memberNo=params[i].split("=")[1];
		}else if(params[i].indexOf("memberNoGm")>-1){
			memberNoGm=params[i].split("=")[1];
		}else if(params[i].indexOf("merchantNo")>-1){
			merchantNo=params[i].split("=")[1];
		}
	}*/
	var guideWxId = $('#guideWxId').val();//微信号

	var options=null;
	var msg="";
	if(con=="huodong1"){
		msg="确定发送该素材";
	}else if(con=="huodong2"){
		msg="确定发送该活动";
	}else if(con=="huodong3"){
		msg="确定发送该优惠券";
	}
	else if(con=="huodong4"){
		msg="确定发送该位置";
	}
	 options = {
				confirm:function(){
					if(con=="huodong1"){
						var comD=$(".huodong1 .hd_list .common_mate").css("display");
						var persD=$(".huodong1 .hd_list .personnal").css("display");
						var VrM=$(".huodong1 .hd_list .VR_mate").css("display");
						var shareTitle="";
						var shareUrl="";
						var shareDes="";
						var img="";
						if(comD=="block"){
							shareTitle=$(".huodong1 .hd_list .common_mate div.active").next().find(" li.active  a").text();
							shareUrl=$(".huodong1 .hd_list .common_mate div.active").next().find("li.active .hd1URL") .val();
							shareDes=$(".huodong1 .hd_list .common_mate div.active").next().find("li.active .brief") .val();
							img=$(".huodong1 .hd_list .common_mate div.active").next().find("li.active .hd1imgAddr") .val();
						}else if(persD=="block"){
							shareTitle=$(".huodong1 .hd_list .personnal div.active").next().find(" li.active  a").text();
							shareUrl=$(".huodong1 .hd_list .personnal div.active").next().find("li.active .hd1URL") .val();
							shareDes=$(".huodong1 .hd_list .personnal div.active").next().find("li.active .brief") .val();
							img=$(".huodong1 .hd_list .personnal div.active").next().find("li.active .hd1imgAddr") .val();
						}else if(VrM=="block"){
							shareTitle=$(".huodong1 .VR_mate .vr_info .active").text();
							shareUrl=$(".huodong1 .VR_mate .vr_info .active .shareUrl").val();
							shareDes=$(".huodong1 .VR_mate .vr_info .active .shareDes").val();
							img=$(".huodong1 .VR_mate .vr_info .active .img").val();
						}
						if(shareDes==undefined || shareDes == "undefined"){
							shareDes="";
						}
						$.ajax({
					        type:"POST",
					        url:$('#ctx').val() + "/im/sendChatMessage",
					        data:{msgId:msgId,memberNo:memberNo,type:490,shareTitle:shareTitle,chatroomType:2,chatroomNoWx:guideWxId,shareDes:shareDes,shareUrl:shareUrl,resources:img},
					        dataType:'JSON',
					        success:function(result){
					        	console.log(result)
					        	var yhjHtml='';
					        	 if(result['zkclient_offline']){
					        		 yhjHtml='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
					        	 }else{
					        		 var now=Number(new Date());
							        	if(now-localT>(1000 * 60 * 5)){
							        		yhjHtml +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
							        		if(window.localStorage.getItem("chatstart")!=null){
							        			window.localStorage.setItem("chatstart", now);
							        		}
							        		if(window.localStorage.getItem("guidechatstart")!=null){
							        			window.localStorage.setItem("guidechatstart", now);
							        		}
							        	}
					        			yhjHtml +='<div class="mine promotion" >';
						        		if(result['true']){
						        			yhjHtml +='<div class="shareInfo removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+' onclick=\'javascript:showContent2("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+img+'" style="width:68px">';
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        		}else if(result['include_sensitive_words']){
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
						        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent2("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+img+'" style="width:68px">'
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
						        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
						        		}else{
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
							        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent2("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
							        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+img+'" style="width:68px">'
							        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
							        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
						        		}
					        	 }

				        		$(".person .sign").before(yhjHtml);
			        			$(".person").scrollTop($(".person")[0].scrollHeight);

			        			/*撤销*/
			    	    		$(".removeInfo").bind("contextmenu", function(){
			    	    		    return false;
			    	    		});
		    	    		$(".removeInfo").mousedown(function(e) {
	    	    		        //console.log(e.which);
	    	    		        //右键为3
	    	    		        if (3 == e.which) {
	    	    		        	var flag = $(".chexiaoInfo")
	    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
	    	    		        	if(!flag.is(":visible")){
	    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
		    	    		        	var timestamp = Date.parse(new Date());			//当前时间
		    	    		        	var datainfo= timestamp - chatTime;
		    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
		    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
		    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
		    	    			            $(this).parent().append(html);
		    	    		        	}
	    	    		        	}else{
	    	    		        		$(".chexiaoInfo").remove()
	    	    		        	}

	    	    		        } else if (1 == e.which) {
	    	    		            //左键为1
	    	    		        }
	    	    		});
					        }
					    });

						$(".huodong1").hide();
					}else if(con=="huodong2"){
						var shareTitle=$($(".huodong2 .hd_list .active p span")[0]).text();
						var shareUrl=$(".huodong2 .hd_list .active .hdShareUrl").val();
						var resources=$(".huodong2 .hd_list .active img").attr("src");
						var shareDes="活动开始时间:"+new Date($($(".huodong2 .hd_list .active p span")[1]).text().replace(/-/g,"/")).format("yyyy年MM月dd日  hh点mm分");
						$.ajax({
					        type:"POST",
					        url:$('#ctx').val() + "/im/sendChatMessage",
					        data:{msgId:msgId,memberNo:memberNo,type:492,shareTitle:shareTitle,chatroomType:2,chatroomNoWx:guideWxId,shareUrl:shareUrl,resources:resources,shareDes:shareDes},
					        dataType:'JSON',
					        success:function(result){
					        	console.log(result)
					        	var yhjHtml='';
					        	 if(result['zkclient_offline']){
					        		 yhjHtml='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
					        	 }else{
					        		 var now=Number(new Date());
							        	if(now-localT>(1000 * 60 * 5)){
							        		yhjHtml +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
							        		if(window.localStorage.getItem("chatstart")!=null){
							        			window.localStorage.setItem("chatstart", now);
							        		}
							        		if(window.localStorage.getItem("guidechatstart")!=null){
							        			window.localStorage.setItem("guidechatstart", now);
							        		}
							        	}
					        			yhjHtml +='<div class="mine promotion">';
						        		if(result['true']){
						        			yhjHtml +='<div class="shareInfo removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+' onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+resources+'" style="width:68px">';
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        		}else if(result['include_sensitive_words']){
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
						        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+resources+'" style="width:68px">'
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
						        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
						        		}else{
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
							        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
							        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+shareDes+'</div><img alt="" src="'+resources+'" style="width:68px">'
							        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
							        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
						        		}
					        	 }

				        		$(".person .sign").before(yhjHtml);

			        			$(".person").scrollTop($(".person")[0].scrollHeight);

			        			/*撤销*/
			    	    		$(".removeInfo").bind("contextmenu", function(){
			    	    		    return false;
			    	    		});
			    	    		$(".removeInfo").mousedown(function(e) {
			    	    		        //console.log(e.which);
			    	    		        //右键为3
			    	    		        if (3 == e.which) {
			    	    		        	var flag = $(".chexiaoInfo")
			    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
			    	    		        	if(!flag.is(":visible")){
			    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
				    	    		        	var timestamp = Date.parse(new Date());			//当前时间
				    	    		        	var datainfo= timestamp - chatTime;
				    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
				    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
				    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
				    	    			            $(this).parent().append(html);
				    	    		        	}
			    	    		        	}else{
			    	    		        		$(".chexiaoInfo").remove()
			    	    		        	}

			    	    		        } else if (1 == e.which) {
			    	    		            //左键为1
			    	    		        }
			    	    		});
					        }
					    });
						$(".huodong2").hide();
						$("#target").html("");
					}else if(con=="huodong3"){
						var shareTitle=$($(".huodong3 .hd_list .active .roll_right p")[0]).text();
						var shareDes=$($(".huodong3 .hd_list .active .roll_right p")[2]).text();
						var shareUrl=$(".huodong3 .hd_list .active .yhjShareUrl").val();
						var couponRuleCode=$(".huodong3 .hd_list .active .couponRuleCode").val();
						var merchantLogoUrl=$(".huodong3 .hd_list .active .merchantLogoUrl").val();
//						var explain = $('.roll_right p:nth-child(4)').html();
						var explain = "";
						if($('.roll_right p:nth-child(4)').html()!=undefined){
							 explain = $('.roll_right p:nth-child(4)').html();
						}else{
							explain = "";
						}
						$.ajax({
					        type:"POST",
					        url:$('#ctx').val() + "/im/sendChatMessage",
					        data:{msgId:msgId,memberNo:memberNo,type:491,shareTitle:shareTitle,chatroomType:2,chatroomNoWx:guideWxId,shareDes:shareDes,shareUrl:shareUrl,couponRuleCode:couponRuleCode},
					        dataType:'JSON',
					        success:function(result){
					        	var yhjHtml='';
					        	 if(result['zkclient_offline']){
					        		 yhjHtml='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
					        	 }else{
					        		 var now=Number(new Date());
							        	if(now-localT>(1000 * 60 * 5)){
							        		yhjHtml +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
							        		if(window.localStorage.getItem("chatstart")!=null){
							        			window.localStorage.setItem("chatstart", now);
							        		}
							        		if(window.localStorage.getItem("guidechatstart")!=null){
							        			window.localStorage.setItem("guidechatstart", now);
							        		}
							        	}
							        	yhjHtml +='<div class="mine promotion">';
						        		if(result['true']){
						        			yhjHtml +='<div class="shareInfo removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+' onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText"><p>'+shareDes+'</p><p>'+ explain  +'</p></div><img alt="" src="'+merchantLogoUrl+'" style="width:68px">';
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
						        		}else if(result['include_sensitive_words']){
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
						        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText"><p>'+shareDes+'</p><p>'+ explain  +'</p></div><img alt="" src="'+merchantLogoUrl+'" style="width:68px">';
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
						        			yhjHtml += '<div class="errorInfo" style="display: block">'+result.include_sensitive_words+'</div>';
						        		}else{
						        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
						        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+shareTitle+'</p>';
						        			yhjHtml +='<div class="sendDec"><div class="sendDecText"><p>'+shareDes+'</p><p>'+ explain  +'</p></div><img alt="" src="'+merchantLogoUrl+'" style="width:68px">';
						        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
						        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
						        		}
					        	 }

				        		$(".person .sign").before(yhjHtml);
			        			$(".person").scrollTop($(".person")[0].scrollHeight);

			        			/*撤销*/
			    	    		$(".removeInfo").bind("contextmenu", function(){
			    	    		    return false;
			    	    		});
			    	    		$(".removeInfo").mousedown(function(e) {
			    	    		        //console.log(e.which);
			    	    		        //右键为3
			    	    		        if (3 == e.which) {
			    	    		        	var flag = $(".chexiaoInfo")
			    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
			    	    		        	if(!flag.is(":visible")){
			    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
				    	    		        	var timestamp = Date.parse(new Date());			//当前时间
				    	    		        	var datainfo= timestamp - chatTime;
				    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
				    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
				    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
				    	    			            $(this).parent().append(html);
				    	    		        	}
			    	    		        	}else{
			    	    		        		$(".chexiaoInfo").remove()
			    	    		        	}

			    	    		        } else if (1 == e.which) {
			    	    		            //左键为1
			    	    		        }
			    	    		});
					        }
					    });
						$(".huodong3").hide();
					}
					$(".zhegai").hide();
					HISTORYCHATLIST.push(msgId);

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


function tree(info,event){
	$(event).siblings().removeClass("active");
	$(event).addClass("active");
	var merchantNo=getQueryString("merchantNo");
	var memberNoGm=getQueryString("memberNoGm");
	var memberNo=getQueryString("memberNo");
	if(info=="common_mate"){
		$(".huodong1 .common_mate").show();
		$(".huodong1 .personnal").hide();
		$(".huodong1 .VR_mate").hide();
		if($(".huodong1 .common_mate").find("li").length>0){
			$(".huodong1 .common_mate li.active a")[0].click();
		}else{
			$("#hd1").attr("src","")
		}

	}else if(info=="personnal"){
		$(".huodong1 .common_mate").hide();
		$(".huodong1 .VR_mate").hide();
		$(".huodong1 .personnal").show();

		if($(".huodong1 .personnal").find("li").length>0){
			$(".huodong1 .personnal li.active a")[0].click();
		}else{
			$("#hd1").attr("src","")
		}

	}else{
		$(".huodong1 .common_mate").hide();
		$(".huodong1 .VR_mate").show();
		$(".huodong1 .personnal").hide();
		if($(".huodong1 .vr_info").find("p").length>0){
			$(".huodong1 .vr_info p")[0].click();
		}else{
			$("#hd1").attr("src","")
		}

	}

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


//uuid
function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); 
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid.replace(/-/g,"");
}

/*语音播放*/
function play(event){
	var audios=$(event).parent().parent().siblings().find("audio");
	$.each(audios,function(i){
		audios[i].pause();
	});
	var myAudio = $(event).find("audio")[0]
	 if(myAudio.paused){
           myAudio.play();
       }else{
           myAudio.pause();
       }
}


function cursorControl(a){
    this.element=a;
};
cursorControl.prototype={
    getType:function(){
        return Object.prototype.toString.call(this.element).match(/^\[object\s(.*)\]$/)[1];
    },
    getStart:function(){
    	 var start;
         if (this.element.selectionStart || this.element.selectionStart == '0'){
             start = this.element.selectionStart;
         }
         else if (window.getSelection){
             var rng = window.getSelection().getRangeAt(0).cloneRange();
             rng.setStart(this.element, 0);
             start = rng.toString().length;
         };
         return start;
    },
    insertText:function(text){
        this.element.focus();
        if (document.all){
            var c = document.selection.createRange();
            document.selection.empty();
            c.text = text;
            c.collapse();
            c.select();
        }
        else{
            var start=this.getStart();
            if(this.getType()=='HTMLDivElement'){
                this.element.innerHTML=this.element.innerHTML.substr(0,start)+text+this.element.innerHTML.substr(start);
            }else{
                this.element.value=this.element.value.substr(0,start)+text+this.element.value.substr(start);
            }
        }
    }
};
function fn(id,text){
    var cc=new cursorControl(document.getElementById(id));
    cc.insertText(text);
};


//话术
function words(event,type){
	if(type==1){
		var info=$("#talkContent li.active .d").text();
		if(info==""){
			var msg ="请选择话术。";
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
      		return false;
		}
		$(".moreWordBox").hide();
	}else{
		var info=$(event).text();
	}

	var msgId=uuid();
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var merchantNo=getQueryString('merchantNo')||'';
	var guideWxId = $('#guideWxId').val();//微信号
	var localT = parseInt(window.localStorage.getItem("chatstart"))||parseInt(window.localStorage.getItem("guidechatstart"));
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/sendChatMessage",
        data:{msgId:msgId,memberNo:memberNo,type:1,content:info,chatroomType:2,chatroomNoWx:guideWxId},
        dataType:'JSON',
        success:function(result){
        	$(".emoticon .words").hide();
        	var html='';
        	 if(result['zkclient_offline']){
        		 html='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
        	 }else{
        		 var now=Number(new Date());
		        	if(now-localT>(1000 * 60 * 5)){
		        		html +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
		        		if(window.localStorage.getItem("chatstart")!=null){
		        			window.localStorage.setItem("chatstart", now);
		        		}
		        		if(window.localStorage.getItem("guidechatstart")!=null){
		        			window.localStorage.setItem("guidechatstart", now);
		        		}

		        	}
		        	html +='<div class="mine general sendAll">';
	        		if(result['true']){
						html +='<div class="information removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'>'+info.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        		}else if(result['include_sensitive_words']){
	        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	        			html +='<div class="information">'+info.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        			html += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
	        		}else{
	        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	        			html +='<div class="information">'+info.replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        			html += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
	        		}
        	 }

    		$(".person .sign").before(html);
			$(" .person").scrollTop($(" .person")[0].scrollHeight);
			HISTORYCHATLIST.push(msgId);

			$(".removeInfo").bind("contextmenu", function(){
			    return false;
			});
			$(".removeInfo").mousedown(function(e) {
			    	    		        //console.log(e.which);
			    	    		        //右键为3
			    	    		        if (3 == e.which) {
			    	    		        	var flag = $(".chexiaoInfo")
			    	    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
			    	    		        	if(!flag.is(":visible")){
			    	    		        		var chatTime=$(this).attr("data-time");		//聊天时间
				    	    		        	var timestamp = Date.parse(new Date());			//当前时间
				    	    		        	var datainfo= timestamp - chatTime;
				    	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
				    	    		        		var chatCode=$(this).attr("data-code");	//聊天code
				    	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
				    	    			            $(this).parent().append(html);
				    	    		        	}
			    	    		        	}else{
			    	    		        		$(".chexiaoInfo").remove()
			    	    		        	}

			    	    		        } else if (1 == e.which) {
			    	    		            //左键为1
			    	    		        }
			    	    		});
        }
    });
}

//更多话术
function moreWord(typeCode){
	var merchantNo=getQueryString('merchantNo')||'';
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/moreWords",
        data:{merchantNo:merchantNo,typeCode:typeCode},
        dataType:'JSON',
        success:function(result){
        	var html='<ul>';
        	if(result.infoList!=undefined){
        		for(var i=0;i<result.infoList.length;i++){
        			html +='<li onclick="choseTalk(this)"><span class="n">'+result.infoList[i].typeName+'</span>';
        			html +='<div class="d">'+result.infoList[i].content+'</div></li>';
        		}
        	}
        	html +='</ul>';
		    $("#talkContent").html(html);

		    if(typeCode==undefined){
		    	var sHtml='<select><option value="">全部</option>';
				for(var n=0;n<result.typeList.length;n++){
					sHtml +='<option onclick="moreWord(\''+result.typeList[n].code+'\')" value='+result.typeList[n].code+'>'+result.typeList[n].typeName+'</option>';
				}
				sHtml +='</select>';
				if($(".wordsContent .top  select").length>0){
					$(".wordsContent .top  select").remove();
				}
				$(".wordsContent .top .n").after(sHtml);

		    }
			$(".moreWordBox").css("display","flex");
		    $(".emoticon .words").hide();
        }
    });
}

function choseTalk(event){
	$(event).siblings().removeClass("active");
	$(event).addClass("active");
}
function showWord(){
	$(".emoticon .allCard").hide();
	if($(".emoticon .words").css("display")=='none'){
		var merchantNo=getQueryString('merchantNo')||'';
		$.ajax({
	        type:"POST",
	        url:$('#ctx').val() + "/im/wordsSelect",
	        data:{merchantNo:merchantNo},
	        dataType:'JSON',
	        success:function(result){
	        	if(result.length==0){
		        	 var msg ="还没有配置话术。";
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
			       		 return false;
	        	}else{
	        		var html='<dl>';
	        		for(var i=0;i<result.length;i++){
	        			html +='<dd onclick="words(this)" class="twoLine">'+result[i].content+'</dd>';
	        		}
	        		html +='</dl>';
	        		if(result.length>=4){
	        			html +='<span class="moreWord" onclick="moreWord()">更多</span>';
	        		}
	        		$(".emoticon .words").html(html);
	        	}
	        	$(".emoticon .words").show();
	        }
	    });

	}else{
		$(".emoticon .words").hide();
	}

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

//商城
function sendShop(sendinfo){
	var msgId=uuid();
	var params=window.location.search.substring(1).split("&");
	var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
	var merchantNo=getQueryString('merchantNo')||'';
	var guideWxId = $('#guideWxId').val();//微信号
	var localT = parseInt(window.localStorage.getItem("chatstart"))||parseInt(window.localStorage.getItem("guidechatstart"));
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/sendChatMessage",
        data:{msgId:msgId,memberNo:memberNo,type:1,content:sendinfo,chatroomType:2,chatroomNoWx:guideWxId},
        dataType:'JSON',
        success:function(result){
        	var html='';
        	 if(result['zkclient_offline']){
        		 html='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
        	 }else{
        		 var now=Number(new Date());
		        	if(now-localT>(1000 * 60 * 5)){
		        		html +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
		        		if(window.localStorage.getItem("chatstart")!=null){
		        			window.localStorage.setItem("chatstart", now);
		        		}
		        		if(window.localStorage.getItem("guidechatstart")!=null){
		        			window.localStorage.setItem("guidechatstart", now);
		        		}

		        	}
		        	html +='<div class="mine general sendAll">';
	        		if(result['true']){
						html +='<div class="information removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+'>'+sendinfo+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        		}else if(result['include_sensitive_words']){
	        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	        			html +='<div class="information">'+sendinfo+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        			html += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
	        		}else{
	        			html +='<div class="erroImg"><img  style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>';
	        			html +='<div class="information">'+sendinfo+'</div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        			html += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
	        		}
        	 }

    		$(".person .sign").before(html);
			$(".sendInfo").html('');
			$(" .person").scrollTop($(" .person")[0].scrollHeight);
			HISTORYCHATLIST.push(msgId);

			$(".removeInfo").bind("contextmenu", function(){
			    return false;
			});
			$(".removeInfo").mousedown(function(e) {
    		        //console.log(e.which);
    		        //右键为3
    		        if (3 == e.which) {
    		        	var flag = $(".chexiaoInfo")
    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
    		        	if(!flag.is(":visible")){
    		        		var chatTime=$(this).attr("data-time");		//聊天时间
	    		        	var timestamp = Date.parse(new Date());			//当前时间
	    		        	var datainfo= timestamp - chatTime;
	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
	    		        		var chatCode=$(this).attr("data-code");	//聊天code
	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
	    			            $(this).parent().append(html);
	    		        	}
    		        	}else{
    		        		$(".chexiaoInfo").remove()
    		        	}

    		        } else if (1 == e.which) {
    		            //左键为1
    		        }
    		});
        }
    });
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

function om(e){
	var cN=$(e.target).attr("class");
	if(cN=="inShop"){
		$('.inShop').hide();
	}
}


//积分商城发送
function sendJf(jfDate){
	var msgId=uuid();
	var memberNo=getQueryString('code');
	var guideWxId = $('#guideWxId').val();//微信号
	var localT = parseInt(window.localStorage.getItem("chatstart"))||parseInt(window.localStorage.getItem("guidechatstart"));
	$.ajax({
	    type:"POST",
	    url:$('#ctx').val() + "/im/sendChatMessage",
	    data:{msgId:msgId,memberNo:memberNo,type:49,shareTitle:jfDate.shareTitle,chatroomType:2,chatroomNoWx:guideWxId,shareDes:jfDate.shareDesc,shareUrl:jfDate.shareUrl,resources:jfDate.iconUrl},
	    dataType:'JSON',
	    success:function(result){
	    	console.log(result)
	    	var yhjHtml='';
	    	 if(result['zkclient_offline']){
	    		 yhjHtml='<div class="sendTime text-center"><span>'+result['zkclient_offline']+'</span></div>'
	    	 }else{
	    		 var now=Number(new Date());
		        	if(now-localT>(1000 * 60 * 5)){
		        		yhjHtml +='<div class="sendTime text-center"><span>'+(new Date(now).format('hh:mm'))+'</span></div>';
		        		if(window.localStorage.getItem("chatstart")!=null){
		        			window.localStorage.setItem("chatstart", now);
		        		}
		        		if(window.localStorage.getItem("guidechatstart")!=null){
		        			window.localStorage.setItem("guidechatstart", now);
		        		}
		        	}
	    			yhjHtml +='<div class="mine promotion" >';
	        		if(result['true']){
	        			yhjHtml +='<div class="shareInfo removeInfo" data-Time='+new Date().getTime()+' data-code='+msgId+' onclick=\'javascript:showContent("'+jfDate.shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+jfDate.shareTitle+'</p>';
	        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+jfDate.shareDesc+'</div><img alt="" src="'+jfDate.iconUrl+'" style="width:68px">';
	        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>';
	        		}else if(result['include_sensitive_words']){
	        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
	        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+jfDate.shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+jfDate.shareTitle+'</p>';
	        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+jfDate.shareDesc+'</div><img alt="" src="'+jfDate.iconUrl+'" style="width:68px">'
	        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
	        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['include_sensitive_words']+'</div>';
	        		}else{
	        			yhjHtml += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
		        			yhjHtml +='<div class="shareInfo" onclick=\'javascript:showContent("'+jfDate.shareUrl+'","yulan") \'><p class="sendTitle oneLine">'+jfDate.shareTitle+'</p>';
		        			yhjHtml +='<div class="sendDec"><div class="sendDecText">'+jfDate.shareDesc+'</div><img alt="" src="'+jfDate.iconUrl+'" style="width:68px">'
		        			yhjHtml +='</div></div><img alt="" class="headImg" src="'+sessionStorage.getItem("shopHeadImg")+'"></div>'
		        			yhjHtml += '<div class="errorInfo" style="display: block">'+result['false']+'</div>';
	        		}
	    	 }

			$(".person .sign").before(yhjHtml);
			$(".person").scrollTop($(".person")[0].scrollHeight);
			HISTORYCHATLIST.push(msgId);

			/*撤销*/
    		$(".removeInfo").bind("contextmenu", function(){
    		    return false;
    		});
			$(".removeInfo").mousedown(function(e) {
    		        //console.log(e.which);
    		        //右键为3
    		        if (3 == e.which) {
    		        	var flag = $(".chexiaoInfo")
    		        	//判断当前是否有显示撤回 false：就添加撤回  true：移除撤回
    		        	if(!flag.is(":visible")){
    		        		var chatTime=$(this).attr("data-time");		//聊天时间
	    		        	var timestamp = Date.parse(new Date());			//当前时间
	    		        	var datainfo= timestamp - chatTime;
	    		        	if(datainfo < 2*60*1000){		//聊天时间小于2分钟可以撤回
	    		        		var chatCode=$(this).attr("data-code");	//聊天code
	    		        		var html="<div class='chexiaoInfo' onclick='cheXiaoFun(\""+chatCode+"\",this)'>撤销</div>";
	    			            $(this).parent().append(html);
	    		        	}
    		        	}else{
    		        		$(".chexiaoInfo").remove()
    		        	}

    		        } else if (1 == e.which) {
    		            //左键为1
    		        }
    		});
	    }
	});
}

//聊天鼠标移上去改变图片
function changeimg(evn){
	var imgName=$(evn).attr("class");
	if(imgName.indexOf("_arr")<0){
		var url=$(evn).attr("src");
		url=url.replace(imgName,imgName+"_arr");
		$(evn).attr("src",url);
		$(evn).attr("class",imgName+"_arr");
	}else if(imgName.indexOf("_arr")>-1){
		var url=$(evn).attr("src");
		url=url.replace(imgName,imgName.replace("_arr",""));
		$(evn).attr("src",url);
		$(evn).attr("class",imgName.replace("_arr",""));
	}
}

function queryCard(){
	var typeCode = $(".certFlag").val();
	$(".personCardContent .pagination .pageShow").val(1);
	personCardOther(typeCode);
}

//个人或公众号名片
function personCardOther(typeCode){
	var url="";
	if(typeCode == 0){		//个人名片
		$(".personCardContent .top .n").text("个人名片");
		$(".personCardContent .top .certFlag").val(0);
		url = $('#ctx').val() + "/im/personMemberList";
	}else if(typeCode == 8){	//公众号名片
		$(".personCardContent .top .n").text("公众号名片");

		$(".personCardContent .top .certFlag").val(8);
		url=$('#ctx').val() + "/im/publicaccount/list";
	}
	var merchantNo=getQueryString('merchantNo')||'';
	var shopNo=$("#shopNo").val();
	var noWx=$("#guideWxId").val();
	var n=$(".personCardContent .pagination select").find("option:selected").text();
	var pageNo= $(".personCardContent .pagination .pageShow").val();
	var name=$(".personCardContent .top .mp input").val();
	$.ajax({
        type:"POST",
        url:url,
        data:{noWx:noWx,pageSize:Number(n),shopNo:shopNo,pageNo:pageNo,status:1,paName:name,keyWord:name,noWxZk:noWx,queryChatRoom:false},
        dataType:'JSON',
        success:function(result){
        	var dataInfo="";
        	if(typeCode == 0){	//个人名片
        		dataInfo=result.page;
        	}else if(typeCode == 8){	//公众号名片
        		dataInfo=result.data;
        	}

        	if(dataInfo){
                $(".personCardContent .pagination .pageShow").val(dataInfo.pageNo);
                $(".personCardContent .condition_all .kehuNum").text(dataInfo.count);
                $(".personCardContent .pagination .total").text(dataInfo.count);
                var totalPage=Math.ceil(dataInfo.count/dataInfo.pageSize);
                $(".personCardContent .pagination .totalPage").text(totalPage);

                var currPage=$(".personCardContent .pagination .pageShow").val();
                if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
                	$(".personCardContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
            		$(".personCardContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
            		$(".personCardContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
            		$(".personCardContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
            	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
            		$(".personCardContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
            		$(".personCardContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
            		$(".personCardContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
            		$(".personCardContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
            	}else  if(Number(currPage)==1  && totalPage>1){
                	$(".personCardContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
            		$(".personCardContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
            		$(".personCardContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
            		$(".personCardContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
            	}else{
            		$(".personCardContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
            		$(".personCardContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
            		$(".personCardContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
            		$(".personCardContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png")
            	}
        		if(dataInfo.list){
        			var html='';
                	if(dataInfo.list.length>0){
                		for(var i=0;i<dataInfo.list.length;i++){
                			if(typeCode == 0){	//个人名片
                				if(!dataInfo.list[i].chatRoomFlag){	//非群聊
                					html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+getHeadAddress(dataInfo.list[i].headAddress)+'">'+'</span>';
                        			html +='<div class="d">'+(dataInfo.list[i].memberName || dataInfo.list[i].nickNameWx)+'</div>';
                        			if(dataInfo.list[i].noWxAlias){
                        				html += '<input type="hidden" class="nwa" value="'+dataInfo.list[i].noWxAlias+'" />';
                        			}
                        			html += '<input type="hidden" class="mpCode" value="'+dataInfo.list[i].code+'" />';
                        			html += '<input type="hidden" class="nwx" value="'+dataInfo.list[i].noWx+'" />';
                        			html += '<input type="hidden" class="mpmemberNo" value="'+dataInfo.list[i].memberNo+'" />';
                        			html += '<input type="hidden" class="mpmemberNoGm" value="'+dataInfo.list[i].memberNoGm+'" />';
                        			html += '</li>';
                				}
                        	}else if(typeCode == 8){	//公众号名片
                        		html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+getHeadAddress(dataInfo.list[i].paLogo)+'">'+'</span>';
                    			html +='<div class="d">'+dataInfo.list[i].paName+'</div>';
                    			if(dataInfo.list[i].paAlias){
                    				html += '<input type="hidden" class="nwa" value="'+dataInfo.list[i].paAlias+'" />';
                    			}
                    			html += '<input type="hidden" class="nwx" value="'+dataInfo.list[i].paUsername+'" />';
                    			html += '<input type="hidden" class="gzhCode" value="'+dataInfo.list[i].code+'" />';
                    			html += '</li>';
                        	}

                		}
                	}
        		    $("#cardContent ul").html(html);

        			$(".personCardBox").css("display","flex");
        		}else{
        			if(typeCode == 0){		//个人名片
        				alert("没有搜索到联系人")
        			}else{
        				alert("没有搜索到公众号")
        			}

            	}
        	}else{
        		if(typeCode == 0){		//个人名片
    				alert("没有搜索到联系人")
    			}else{
    				alert("没有搜索到公众号")
    			}
        	}

		    $(".emoticon .allCard").hide();
        }
    });
}

/*显示名片列表*/
function showAllCard(){
	$(".emoticon .words").hide();
	if($(".emoticon .allCard").css("display")=='none'){
		$(".emoticon .allCard").show();
	}else{
		$(".emoticon .allCard").hide();
	}
}

/*名片分页*/
function changeCardPage(info){
	var pageNo=$(".personCardContent .pagination .pageShow").val();
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
	$(".personCardContent .pagination .pageShow").val(pageNo);
	personCardOther($(".certFlag").val());
}

/*显示小程序*/
function showXCX(num){
	var url=$('#ctx').val() + "/im/smallprogram/list";
	var merchantNo=getQueryString('merchantNo')||'';
	var shopNo=$("#shopNo").val();
	var noWx=$("#guideWxId").val();
	var n=$(".wxSmallProgramContent .pagination select").find("option:selected").text();
	var pageNo= $(".wxSmallProgramContent .pagination .pageShow").val();
	var spName=$(".wxSmallProgramContent .queryXcx .xcxM input").val();
	var type=$(".wxSmallProgramContent .queryXcx .xcxYhu option:selected").val();
	if(num == 1){
		spName == "";
		type == "";
	}
	$.ajax({
        type:"POST",
        url:url,
        data:{noWxZk:noWx,pageSize:Number(n),shopNo:shopNo,pageNo:pageNo,type:type,spName:spName,status:1},
        dataType:'JSON',
        success:function(result){
        	var xcxType=result.data.smallProgramTypes;
        	var xcxTypeHtml="<option value=''>全部</option>";
        	for(var key in xcxType){
        		xcxTypeHtml +="<option value='"+key+"'>"+xcxType[key]+"</option>";
        	}
        	$("#queryType").html(xcxTypeHtml);
        	var dataInfo=result.data.page;
        	if(dataInfo){
                $(".wxSmallProgramContent .pagination .pageShow").val(dataInfo.pageNo);
                $(".wxSmallProgramContent .condition_all .kehuNum").text(dataInfo.count);
                $(".wxSmallProgramContent .pagination .total").text(dataInfo.count);
                var totalPage=Math.ceil(dataInfo.count/dataInfo.pageSize);
                $(".wxSmallProgramContent .pagination .totalPage").text(totalPage);

                var currPage=$(".wxSmallProgramContent .pagination .pageShow").val();
                if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
                	$(".wxSmallProgramContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
            		$(".wxSmallProgramContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
            		$(".wxSmallProgramContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
            		$(".wxSmallProgramContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
            	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
            		$(".wxSmallProgramContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
            		$(".wxSmallProgramContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
            		$(".wxSmallProgramContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
            		$(".wxSmallProgramContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
            	}else  if(Number(currPage)==1  && totalPage>1){
                	$(".wxSmallProgramContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
            		$(".wxSmallProgramContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
            		$(".wxSmallProgramContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
            		$(".wxSmallProgramContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
            	}else{
            		$(".wxSmallProgramContent .pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
            		$(".wxSmallProgramContent .pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
            		$(".wxSmallProgramContent .pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
            		$(".wxSmallProgramContent .pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png")
            	}
        		if(dataInfo.list){
        			var html='';
                	if(dataInfo.list.length>0){
                		for(var i=0;i<dataInfo.list.length;i++){
                			html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+getHeadAddress(dataInfo.list[i].spUrl)+'">'+'</span>';
                			 html +='<div class="d">'+dataInfo.list[i].spName+'</div>';
                			html += '<input type="hidden" class="spDesc" value="'+dataInfo.list[i].spDesc.replace(/"/g,"").replace(/'/g,"")+'" />';
                			html += '<input type="hidden" class="spcode" value="'+dataInfo.list[i].code+'" />';
                			html += '</li>';
                		}
                	}
        		    $("#wxSmallContent ul").html(html);

        			$(".wxSmallProgramBox").css("display","flex");
        		}else{
            		alert("没有搜索到小程序")
            	}
        	}else{
        		alert("没有小程序")
        	}
		    $(".emoticon .allCard").hide();
        }
    });
}

/*小程序分页*/
function changeSmallProPage(info){
	var pageNo=$(".wxSmallProgramContent .pagination .pageShow").val();
	if(info=="first"){
		pageNo=1;
	}else if(info=="prev"){
		if(pageNo>1){
			pageNo=pageNo-1;
		}
	}else if(info=="next"){
		var last=$(".wxSmallProgramContent .pagination .totalPage").text();
		if(pageNo<Number(last)){
			pageNo=Number(pageNo)+1;
		}
	}else if(info=="last"){
		pageNo=$(".wxSmallProgramContent .pagination .totalPage").text();
	}
	$(".wxSmallProgramContent .pagination .pageShow").val(pageNo);
	showXCX();
}

function queryXCX(){

	showXCX();
}


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

/*撤销发送的信息开始*/
var cxCycle="";
function cheXiaoFun(code,event){
	event = $(event).parent();
	$(".chexiaoInfo").remove();
	var cxRepeat=6;
	$("#loadFlag").css("display","flex");
	$.ajax({
		type:"post",
    	url:$('#ctx').val() + "/im/toCancelChatInfo",
    	data:{code:code},
    	dataType:'JSON',
    	async: false,
    	success:function(res){
    		//console.log(res)
    		if(res.result == true){
					$("#loadFlag").css("display","none");
					var html = '<div class="sendTime text-center"><span>你撤回了一条消息</span></div>'
					$(event).replaceWith(html);
    		// 	cxCycle=setInterval(function() {
				//     if (cxRepeat == 0) {
				//     	window.clearInterval(cxCycle);
				//     	//findChatCancelStatus(code,cxRepeat,event);
				//     } else {
				//     	cxRepeat--;
				//        // findChatCancelStatus(code,cxRepeat,event);
				//     }
				// }, 2000);

    		}
    	}
	})
}


// function findChatCancelStatus(code,cxRepeat,event){
// 	$.ajax({
// 		 type:"POST",
// 	        url:$('#ctx').val()+"/im/findChatCancelStatus",
// 	        data:{code:code},
// 	        dataType:'JSON',
// 	        success:function(result){
// 	        	//console.log(result)
// 	        	if(result.result == true){		//撤回成功
// 	        		//console.log("成功")
// 	        		$("#loadFlag").css("display","none");
// 	        		var html = '<div class="sendTime text-center"><span>你撤回了一条消息</span></div>'
//         			$(event).replaceWith(html);
//         			window.clearInterval(cxCycle);
// 	        	}else if(cxRepeat == 0){
// 	        		$("#loadFlag").css("display","none");
// 	        		window.clearInterval(cxCycle);
// 	        	}
//
// 	        }
// 	})
//
// }
/*撤销发送的信息结束*/


/*群聊历史消息*/
function clock(memberNo,noWxGm,num)
{
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/updateThirdHaveRead",
        data:{memberNo:memberNo,noWxGm:noWxGm},
        dataType:'JSON',
        success:function(result){

        }
    });
	var limit=50;
	var chatTimeEnd=null;
	if(num== 2){
		var cd=Number(window.localStorage.getItem("chatTimeEnd"));
		if(cd>0){
			chatTimeEnd = new Date(cd).format('yyyy-MM-dd hh:mm:ss');
		}
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/gmChat",
        data:{memberNo:memberNo,limit:limit,chatTimeEnd:chatTimeEnd},
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
    			var gudHeadAddress=sessionStorage.getItem("shopHeadImg");
    			var mapList=new Array();
    			if(num == 2 || num == 1){
    				if(result.rows.length==limit){
    					html +='<div class="sendTime text-center"><span onclick="clock(\''+memberNo+'\',\''+noWxGm+'\',2)">加载更多</span></div>';
    				}
    				window.localStorage.setItem("chatTimeEnd", list[list.length-1].chatTime);
    			}
    			var flag=false;
    			for(var i=list.length; i>0;i-- ){
    				if(num==1 && i-1==list.length-1 && $(".person .sendAll").length==0 ){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if( num==1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if(i-1==list.length-1 && list[i-1].chatTime -parseInt(window.localStorage.getItem("chatstart"))>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}else if(i<=list.length-1 && i-1>=0 && list[i-1].chatTime - list[i].chatTime>(1000 * 60 * 5)){
    					html +='<div class="sendTime text-center"><span>'+historyDate(list[i-1].nowTime,list[i-1].chatTime)+'</span></div>';
    				}

    				if(i-1==list.length-1){
    					window.localStorage.setItem("chatstart", list[0].chatTime);
    				}
    				
    				var headImgs=sessionStorage.getItem("headUrl_"+list[i-1].chatroomNoWx);		//从缓存中加载
					var memberNmg=sessionStorage.getItem("nickName_"+list[i-1].chatroomNoWx);		//从缓存中加载
					var memberHead = getHeadAddress(headImgs);
					
					
    				if(list[i-1].senderFlag==2){	//客户发送
    					
    					if(list[i-1].type==1){			//文本
    						html += '<div class="other sendAll"><img alt="" data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;"  class="information">'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div></div></div>';
    					}else if(list[i-1].type==3){		//图片
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:200px"  onclick="findChatImage(\''+list[i-1].code+'\')" /></div></div></div>';
    						}else{
    							html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div></div></div>';
    						}

    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="information"><img src="'+list[i-1].resourcesPath +'" style="width:68px" /></div></div></div>';
    					}else if(list[i-1].type==34){		//语音
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						$.ajax({
    							type:"POST",
    							url:$('#ctx').val() + "/im/audioConvert",
    							data:{resource:list[i-1].resourcesPath},
    						    dataType:'text',
    						    async: false,
    						    success:function(re){
    						    	html += '<div><span>'+memberNmg+'</span><div class="information"><div class="audio" onclick="play(this)"><audio src="'+re+'"  class="yuying"></audio></div></div></div><div class="seCo" style="margin-top:48px;">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div></div>';
    						    }
    						});
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492 || list[i-1].type==49){	//分享1
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div><span>'+memberNmg+'</span><div class="shareInfo" onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
    						html += '<div class="sendDec"><img alt="" src="'+list[i-1].resourcesPath+'"  style="width:68px"/>';
    						html += '<div class="sendDecText">';
    						if(list[i-1].shareDes!=undefined &&list[i-1].shareDes.split(" ").length>1){
    							html += '<p>'+list[i-1].shareDes.split(" ")[0]+'</p><p>'+list[i-1].shareDes.split(" ")[1]+'</p>';
    						}else{
    							if(list[i-1].type==490 || list[i-1].type==49){
    								html += '<p>'+ list[i-1].shareDes +'</p>';
    							}else{
    								html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p><p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    							}
    						}
    						html +=	'</div></div></div></div></div>'

    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="shareInfo"  onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+gudHeadAddress+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div></div></div>';
    					}else if(list[i-1].type==48){ //位置
    						var poiname = JSON.parse(list[i-1].content).poiname;
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html +='<div><span>'+memberNmg+'</span><div class="mapToy"><div class="mapCon" onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div></div>';
    						html +=	'</div></div>'
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="other sendAll">';
							html += '<img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div class="information">';
    						html += '<div class="personCard sendAll redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div class="redEnvelopes-right" title="'+content.des+'"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '</div></div>'

    					}else if(list[i-1].type==43){		//视频
    						html += '<div class="other sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;"  class="information"  ><input type="hidden" class="videoLink" value="'+list[i-1].resourcesPath+'" /><div class="videoTop" onclick="findChatVideo(this,\''+list[i-1].code+'\')"></div><video  src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div></div></div>';
    					}else if(list[i-1].type==42){	//联系人名片或公众号名片
    						var content=JSON.parse(list[i-1].content);

    						html += '<div class="other personCard sendAll"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						if(content.certflag!=""){
    							if(content.certflag==0){
    								html += '<div><span>'+memberNmg+'</span><div  style="max-width:none;" class="shareInfo" onclick="findFriendsByWxNo(\''+content.username+'\',\''+content.alias+'\',\''+content.usernamev2+'\',\''+list[i-1].resourcesPath+'\',\''+list[i-1].shareTitle+'\',\''+list[i-1].noWxGm+'\')">';
    							}else{
    								html += '<div><span>'+memberNmg+'</span><div style="max-width:none;"  class="shareInfo">';
    							}
    						}else{
    							html += '<div><span>'+memberNmg+'</span><div style="max-width:none;"  class="shareInfo">';
    						}
    						html += '<div class="sendDec"><img alt=""  src="'+list[i-1].resourcesPath+'">';
    						html += '<div class="sendDecText" style="display: flex;align-items: center;"><p>'+list[i-1].shareTitle +'</p>';
    						html += '</div></div><p class="sendTitle">';
    						if(content.certflag!=""){
    							if(content.certflag==0){
    								html += '个人名片';
    							}else{
    								html += '公众号名片';
    							}
    						}
    						html +='</p></div></div></div>';
    					}else if(list[i-1].type==494){		//小程序
    						var content=JSON.parse(list[i-1].content);
    						html += '<div class="other personCard sendAll  xcxT"><img data-chatroomNoWx="'+list[i-1].chatroomNoWx+'" alt="" class="headImg" src="'+memberHead+'">';
    						html += '<div><span>'+memberNmg+'</span><div style="max-width:none;"  class="shareInfo"><div class="sendDec">';
    						//html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p><p>'+list[i-1].shareDes +'</p>';
    						html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p>';

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
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}

    						html +='<div class="information removeInfo" data-Time='+list[i-1].chatTime+' data-code='+list[i-1].code+'>'+html2Escape(list[i-1].content).replace(/(\r)?\n/g,'<br/>').expression().expressionFan()+'</div>';
    						html +='<img alt="" class="headImg" src="'+memberHead+'"></div>';
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
    						    	console.log(re)
    						    	html +='<div class="seCo">'+(JSON.parse( list[i-1].content).duration/1000).toFixed(0)+'"</div><div class="information removeInfo" data-Time='+list[i-1].chatTime+' data-code='+list[i-1].code+'><div class="audio" onclick="play(this)"><audio src="'+re+'"  class="yuying"></audio></div></div>';
    						    }
    						});

    						html +='<img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}

    					}else if(list[i-1].type==3){		//图片

    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}
    						if(list[i-1].resourcesPath.indexOf("http")>-1){ //完整地址
    							html +='<div class="information removeInfo" data-Time='+list[i-1].chatTime+' data-code='+list[i-1].code+'><img src="'+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}else{
    							html +='<div class="information removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'><img src="'+$("#UploadUrl").val()+list[i-1].resourcesPath +'" style="width:200px" onclick="findChatImage(\''+list[i-1].code+'\')" /></div>';
    						}
    						html +='<img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}

    					}else if(list[i-1].type==47){		//、图片表情
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}
    						html +='<div class="information removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'><img src="'+list[i-1].resourcesPath +'" style="width:68px"  /></div>';
    						html +='<img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==490 ||list[i-1].type==491||list[i-1].type==492|| list[i-1].type==49){	//分享
    						html += '<div class="mine general sendAll" >';
    						html += '<div class="shareInfo removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+' onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><p class="sendTitle oneLine">'+list[i-1].shareTitle+'</p>';
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
    						html += '</div></div><img alt="" class="headImg" src="'+memberHead+'"></div></div>';
    						if(list[i-1].status>2 &&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==493){	//导购名片
    						html += '<div class="mine personCard sendAll">';
    						html += '<div class="shareInfo removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+' onclick=\'javascript:showContent2("'+encodeURI(list[i-1].shareUrl)+'","yulan") \'><div class="sendDec"><img alt=""  src="'+memberHead+'">';
    						html += '<div class="sendDecText"><p>导购姓名：'+list[i-1].shareTitle +'</p>';
    						html += '<p>'+list[i-1].shareDes.split("\n")[0]+'</p>';
    						if(list[i-1].shareDes.split("\n")[1]!=undefined){
    							html +='<p>'+list[i-1].shareDes.split("\n")[1]+'</p>';
    						}
    						html += '</div></div><p class="sendTitle">个人名片</p></div><img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==48){//位置
    						var poiname = JSON.parse(list[i-1].content).poiname;
    						var label = JSON.parse(list[i-1].content).label;
    						var lat = JSON.parse(list[i-1].content).lat;
    						var lng = JSON.parse(list[i-1].content).lng;
    						html += '<div class="mine personCard sendAll">';
    						html +='<div class="mapToy"><div class="mapCon removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'  onclick="clickMap(\''+lat+'\',\''+lng+'\',\''+label+'\')"><h2 class="mapToy-title">'+ poiname +'</h2><span class="mapToyMs">'+ label +'</span><span class="mapToyMs">'+ "" +'</span><div id="'+ list[i-1].code +'" class="containerMap">' +'</div></div><img alt="" class="headImg" src="'+memberHead+'"></div></div>';
    						mapList.push(list[i-1]);
    					}else if(list[i-1].type==436207665){
    						var content = JSON.parse(list[i-1].content);
    						html += '<div class="mine sendAll">';
    						html += '<div class="redPackage removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'>';
    						html += '<div class="redEnvelopes">';
    						html += '<div class="redEnvelopes-left"></div>';
    						html += '<div title="'+content.des+'" class="redEnvelopes-right"><h2>'+ content.des +'</h2><p>¥ '+ Number(content.amt/100).toFixed(2) +'</p></div>';
    						html += '</div></div>'
    						html += '<img alt="" class="headImg" src="'+memberHead+'">';
    						html += '</div>'

    					}else if(list[i-1].type==43){	//视频
    						html += '<div class="mine general sendAll">';
    						if(list[i-1].status>2){
    						html += '<div class="erroImg"><img style="display: block" src="/oms-web/static/admin/images/imImages/tan.png"></div>'
    						}
    						html +='<div class="information removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'><video src="'+list[i-1].resourcesPath+'" controls="controls"  style="width:300px; height:200px;"></video></div>';
    						html +='<img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==42){	//联系人名片或公众号名片
    						var conJson="";
    						if(list[i-1].content){
    							conJson=JSON.parse(list[i-1].content);
    						}
    						html += '<div class="mine personCard sendAll">';
    						html += '<div class="shareInfo removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'><div class="sendDec"><img alt=""  src="'+list[i-1].resourcesPath+'">';
    						html += '<div class="sendDecText" style="display: flex;align-items: center;"><p>'+list[i-1].shareTitle +'</p>';
    						html += '</div></div><p class="sendTitle">';
    						if(conJson!=""){
    							if(conJson.certflag==0){
    								html += '个人名片';
    							}else if(conJson.certflag==8){
    								html += '公众号名片';
    							}
    						}
    						html +='</p></div><img alt="" class="headImg" src="'+memberHead+'"></div>';
    						if(list[i-1].status>2&&list[i-1].errorMessage !=undefined){
        						html += '<div class="errorInfo" style="display: block">'+list[i-1].errorMessage+'</div>';
        					}
    					}else if(list[i-1].type==494){		//小程序
    						var conJson="";
    						if(list[i-1].content){
    							conJson=JSON.parse(list[i-1].content);
    						}
    						html += '<div class="mine personCard sendAll  xcxT">';
    						html += '<div class="shareInfo removeInfo" data-code='+list[i-1].code+' data-Time='+list[i-1].chatTime+'><div class="sendDec">';
    						//html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p><p>'+list[i-1].shareDes +'</p>';
    						html += '<div class="sendDecText" style="display: flex;justify-content: center;flex-direction: column;"><p>'+list[i-1].shareTitle +'</p>';

    						if(list[i-1].shareUrl){
    							html += '<img alt=""  src="'+list[i-1].shareUrl+'">';
    						}
    						html += '</div></div><p class="sendTitle">';
    						html += '小程序';
    						html +='</p></div><img alt="" class="headImg" src="'+memberHead+'"></div>';
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
/*群聊历史消息结束*/

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
        var memberNo=$('#sendFlag').val() == 'sendFlag' ? $('.shopDetail.active').find('.clientMemNo').val() :getQueryString('code') ||'';
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
    		//console.log(res)
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


var myArrList = [] ; // 群成员数组
/*查询群成员*/
function queryGroupMember(pageNo){
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/chatroom/memberList",
        data:{roomCode:getQueryString("code"),status:1,pageNo:pageNo,pageSize:200},
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
       			            'userName':list[i].userName,
       			            'noWxZk': list[i].noWxZk,
							'chatRoomName': list[i].chatRoomName,
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
            			var name = list[i].nickName;
            			if(list[i].memberName){
            				name = list[i].memberName;
            			}
            			sessionStorage.setItem("nickName_"+list[i].userName,name);
            			
            			arr.push(info);
            			html +='<li id="'+list[i].userName+'"><span><img alt="" src="'+getHeadAddress(list[i].headUrl)+'">';
//            			html +='</span><span title="'+list[i].nickName+'">'+list[i].nickName+'</span></li>';
            			html +='</span><span title="'+name+'">'+name+'</span></li>';
            		}
            		myArrList = arr

            		//console.log(list)
            		html +='<li  onclick="addMembers()"><span style="width:64px;height:64px;"><img alt="" src="'+ctxStatic+'/admin/images/imImages/tianjia.png" >';
            		html += '</span><span></span></li>';
            		html +='<li  onclick="deleteMembers()"><span style="width:64px;height:64px;"><img alt="" src="'+ctxStatic+'/admin/images/imImages/jianqu.png" >';
            		html += '</span><span></span></li>';

            		$(".groupHeads ul").append(html);
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
        		clock(getQueryString("code"),$('#guideWxId').val(),1);
        	}

        }
	});
}

function moreGroupM(){
	var num = $("#pageNo").val();
	queryGroupMember(num);
}

//当前群内成员列表（踢人）
function deleteMembers(){
		$('#memberNosListId').css('display','flex');
		var rows = [];
		rows = myArrList
		var html = '';
		var checkedAll = $('#selectAllCoupon').is(':checked');
		for(var i=0;i < rows.length;i++){
			if( i == 0){
				html += '<li style="display:none">';
				html += '<input type="checkbox" '+'style="display:none"'+' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].userName +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
			}else{
				html += '<li>';
				html += '<input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].userName +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
			}
			html += '<label class="fmb" for="memberNosBox'+ i +'">';
			html += '<div class="i" style="background:url('+ rows[i].headUrl +') no-repeat; background-size:cover;"></div>';
			html += '<div class="r">';
			html += '<span class="n">'+ rows[i].nickName +'</span>';
			html += '<div class="dt">';
			html += '<span>微信号：'+ rows[i].userName +'; </span>';
			html += '</div></div>';
			html += '</label>';
			html += '</li>';
        }
        $("#memberNosListTable ul").append(html);
}

//踢出群人员请求
function saveMemberNos(elem){
	var checkedList = $('.memberNosBox:checked');
	var selectAll = $('#selectAllCoupon').is(':checked');
	if(checkedList.length == 0 && !selectAll){
		$.ConfirmBox({
			msg:'请选择客户',
			isCancel:false
		});
	}else{
		$(elem).attr('disabled','disabled');
		// $('#selectAllCoupon').prop('checked',false);
		var items = [];
		var nickName = [];
		$.each(checkedList,function(){
				var item = JSON.parse($(this).attr('data-item'));
				items.push(item.userName);
				nickName.push(item.nickName)
			});
		var vals = items.join(",");
		var nicks = nickName.join(",");
	}
	 console.log(nickName)
	var list = myArrList
	console.log(vals)
	var  url= $('#ctx').val() + '/im/chatroom/delChatRoomMember';
	var params = {
		noWxZk: list[0].noWxZk,
		chatRoomName: list[0].chatRoomName,
		userNames: vals,	//微信号
		nickNames: 	nicks,		//昵称
	};
	$.post(url,params,function(data){
		if(data.result === true){ // 成功
			$("#loadFlag").css("display","flex");
			$("#memberNosListTable ul").html('');
			$('#memberNosListId').hide();
			 setTimeout(function(){
			 	$(".groupHeads ul").html('');
			 	queryGroupMember(); //重新获取群员
			 	$("#loadFlag").css("display","none");
			 	 $.ConfirmBox({
					msg:data.data,
					isCancel:false
				 });
			 },5000)

		}else{
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
		}
		$(elem).removeAttr('disabled');
	},'json');

};


//添加群成员弹窗
 function addMembers(){
 	var noWxGm = $('#guideWxId').val();//终端微信号
 	var myList = myArrList
 	$('#memberNosListId2').css('display','flex');
 	//获取对象列表
 		$('#memberNosListTable2 ul').ImPaging({
		isTable:false,
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/couponmultipush/personMemberList';
			var params = {
				pageNo:pageData.pageNo || 1,
				pageSize:5000,
				noWx:noWxGm
			};
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.page.list;
					//console.log(rows)
					if(!rows || rows.length == 0) return '';
					var html = '';
					var htmlmmm = '';
					var checkedAll = $('#selectAllCoupon2').is(':checked');
					$.each(rows,function(i){
						htmlmmm='';
						html += '<li>';
						for(var j = 0;j < myList.length;j++){
                            var aaa=""+myList[j].userName;
                            var bbb=""+rows[i].noWx;
							if( aaa == bbb){
								htmlmmm = '<input type="checkbox"  '+'disabled = "disabled"'+'checked'+' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].userName +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
							    break;
							}else{
								htmlmmm = '<input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].userName +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
							}
						}
						html += htmlmmm;

						html += '<label class="fmb" for="memberNosBox'+ i +'">';
						html += '<div class="i" style="background:url('+ rows[i].headAddress +') no-repeat; background-size:cover;"></div>';
						html += '<div class="r">';
						html += '<span class="n">'+ rows[i].memberName +'</span>';
						html += '<div class="dt">';
						html += '<span>微信号：'+ rows[i].noWx +'; </span>';
						html += '</div></div>';
						html += '</label>';
						html += '</li>';
					});
					return html;
				});
				// var data = data.page;
				// data.pageNo = pageData.pageNo || 1;
				// data.limit = data.pageSize;
				// data.total = data.count;
				// data.start = (data.pageNo-1) * data.pageSize;
				// ui.createPages(elem,data); // 创建页码
			},'json');
		}
	});

 }

//添加群人员请求
function sureMemberNos(elem){
	var checkedList = $('.memberNosBox:checked');
	var selectAll = $('#selectAllCoupon').is(':checked');
	if(checkedList.length == 0 && !selectAll){
		$.ConfirmBox({
			msg:'请选择客户',
			isCancel:false
		});
		return
	}else{
		$(elem).attr('disabled','disabled');
		// $('#selectAllCoupon').prop('checked',false);
		var items = []; //勾选的成员微信号
		var addNick = []//勾选成员昵称
		$.each(checkedList,function(){
				if(!$(this).attr("disabled")){
					//console.log(1111)
					var item = JSON.parse($(this).attr('data-item'));
					items.push(item.noWx);
					addNick.push(item.nickNameWx)
					//console.log(item)
				}

			});
		//console.log(addNick)

	}
	if(items == '' || items == undefined ){
		$.ConfirmBox({
			msg:'请选择客户',
			isCancel:false
		});
		return
	}
	var list=[] ;
	  	list = myArrList ;//当前群已有成员
	var vals = items.join(",")
	var nickString = addNick.join(",")

	var  url= $('#ctx').val() + '/im/chatroom/addChatRoomMember';
	var params = {
		noWxZk: list[0].noWxZk,
		chatRoomName: list[0].chatRoomName,
		userNames: vals,
		nickNames: 	nickString,
	};
	$.post(url,params,function(data){
		if(data.result === true){ // 成功
			$("#loadFlag").css("display","flex");
			$("#memberNosListTable2 ul").html('');
			$('#memberNosListId2 ').hide();
			//$(".groupHeads ul").html('');
			items = []
			vals = []
			//myArrList = []
			setTimeout(function(){
				$(".groupHeads ul").html('');
				queryGroupMember(); //重新获取群员
				$("#loadFlag").css("display","none");
				 $.ConfirmBox({
					msg:data.data,
					isCancel:false
				 });
			},5000)
		}else{
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
		}
		$(elem).removeAttr('disabled');

	},'json');

};

//解散并退出群
function cancleMenber(){
	$.ConfirmBox({
		msg:"是否解散并退出该群?",
		isCancel:true,
		confirm: function(){
			var list = myArrList
			//console.log(list)
			var  url= $('#ctx').val() + '/im/chatroom/dismissChatRoom';
			var params = {
				noWxZk: list[0].noWxZk,
				chatRoomName: list[0].chatRoomName,
			};
			$.post(url,params,function(data){
				if(data.result === true){ // 成功
					$.ConfirmBox({
						msg:"解散成功",
						isCancel:false,
					});
					window.location.href = $('#ctx').val() + "/im/list"
				}else{
					$.ConfirmBox({
						msg:data.msg,
						isCancel:false
					});
				}
			},'json');
    	},
	    cancel: function(){
	       console.log("取消")
	    }
	});
}

function cancle(){
	$('#memberNosListId').hide();
	$("#memberNosListId #memberNosListTable ul").html('');//每次退出前  清空数组
	$('#memberNosListId2').hide();
	$("#memberNosListId2 #memberNosListTable2 ul").html('');//每次退出前  清空数组
}

function selectAll(elem){
	var isChecked = $(elem).is(':checked');
	$('.memberNosBox').prop('checked',isChecked);
}

function sltChecked(){
	$('#selectAllCoupon').prop('checked',false);
}
