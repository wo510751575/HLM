/**
 * IM 公用JS
 * 所有IM的JSP文件都会引用该JS
 */
Date.prototype.format = function(f){ //date_format
    var o ={
        "M+" : this.getMonth()+1, //month yyyy-MM-dd hh:mm:ss
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(f))f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(f))f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));return f
};

function historyDate(curentTime,chatTime){
	var curentTime = new Date();
	var cur = +new Date((new Date(curentTime)).format('yyyy-MM-dd 00:00:00'));
	var chat = +new Date((new Date(chatTime)).format('yyyy-MM-dd 00:00:00'));
	
	var timeSpan = cur - chat;
	var m = 60*60*24*1000;
	
	
	if(timeSpan < m){
		return (new Date(chatTime).format('hh:mm'));
	}else if(timeSpan >= m && timeSpan < m*2){
		return  '昨天  ' + (new Date(chatTime).format('hh:mm'));
	}else{
		return (new Date(chatTime).format('yyyy-MM-dd hh:mm'))
	}	
	debugger;
//	var timeS = chatTime;
//    var todayT = ''; //
//    var yestodayT = '';
//    var timeCha = new Date(timeS).getTime();
//    timeS = timeS.slice(-8);
//    todayT = new Date().getHours()*60*60*1000 + new Date().getMinutes()*60*1000 + new Date().getSeconds()*1000;
//    yestodayT = todayT + 24*60*60*1000;
//    if(timeCha > yestodayT) {
//        return argument.slice(0,11);
//    }
//    if(timeCha > todayT && timeCha < yestodayT) {
//        return timeS.slice(0,2)>12?'昨天 下午'+(timeS.slice(0,2)==12 ? 12 : timeS.slice(0,2) - 12)+timeS.slice(2,5):'昨天 上午'+timeS.slice(0,5);
//    }
//    if(timeCha < todayT) {
//        return timeS.slice(0,2)>=12?'下午'+(timeS.slice(0,2)==12 ? 12 : timeS.slice(0,2) - 12)+timeS.slice(2,5):'上午'+timeS.slice(0,5);
//    }
}

//删除所有HTML标签
function removeHtmlTab(tab) {
	 //return tab.replace(/<[^<>]+?>/g,'');
	var inf=tab.replace(/<(?!br)(\/?[^>]+)>/gi,'').replace(/<br>/gi,'\n');
	 return escape2Html($.trim(inf));
	}

//转意符换成普通字符
function escape2Html(str) { 
	 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'}; 
	 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];}); 
	} 

//普通字符转换成转意符
function html2Escape(sHtml) {
	 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt','>':'&gt','&':'&amp','"':'&quot'}[c];}); 
	} 

function getQueryString(name) { // 获取url的参数
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  decodeURI(r[2]); return null;
}

/*
* 表格分页
*/
(function($){
	var defaults = {
		success:function(){},
		counts:[10,20,30],
		isTable:true, // 是否是表格
	}
	var ImPage = function(elem,options){
		this.options = options;
		this.counts = options.counts;
		this.isTable = options.isTable;
		this.init(elem,this,options);
	};
	ImPage.prototype = {
		init:function(elem,ui,options){
			$(elem).html('');
			options.success(elem,ui);
		},
		createTable:function(elem,callback){
			var tags = '';
			if(this.isTable){
				var thead = this.options.thead || [];
				var twidth = this.options.twidth || [];
				tags += '<div class="tableFrame"><table class="table">';
				tags += '<thead>';
				for(var i = 0, len = thead.length; i < len; i++){
					tags += '<td width="'+ (twidth[i]||'') +'">'+ thead[i] +'</td>'
				}
				tags += '</thead></div>';
				tags += '<tbody>';
				try{
					tags += callback();
				}catch(e){}
				tags += '</tbody>';
				tags += '</table>';
			}else{
				tags += callback();
			}
			$(elem).find('.imTable').remove();
			$(elem).append(tags);
		},
		createPages:function(elem,data){ // 创建标签
			var counts = this.counts;
			var data = data || {};
			var totalPage = (data.total%data.limit ? parseInt(data.total/data.limit) + 1 : parseInt(data.total/data.limit))
			var tags = '';
			tags += '<div class="lj-imPaging">';
			tags += '<span>总计'+ (data.total || 0) +'条记录</span>';
			tags += '<span>共'+ totalPage +'页</span>';
//			tags += '当前第<input type="text" value="'+ data.pageNo +'" class="pageNo" />页&nbsp;';
			tags += '当前第'+ data.pageNo +'页&nbsp;';
			tags += '每页<select class="pageSize">';
			for(var i = 0 , len = counts.length; i < len; i++){
				tags += '<option value="'+ counts[i] +'" '+ (counts[i]==data.limit ? 'selected':'') +'>'+ counts[i] +'</option>';
			}
			tags += '</select>条';
			tags += '<input class="first pageBtn" page-no="1" '+ ((data.pageNo == 1)?'disabled="disabled"':'') +'/>';
			tags += '<input class="prev pageBtn" page-no="'+ (parseInt(data.pageNo) - 1) +'" '+ ((data.pageNo == 1)?'disabled="disabled"':'') +'/>';
			tags += '<input class="next pageBtn" page-no="'+ (parseInt(data.pageNo) + 1) +'" '+ ((data.pageNo == totalPage || totalPage == 0)?'disabled="disabled"':'') +'/>';
			tags += '<input class="last pageBtn" page-no="'+ totalPage +'" '+ ((data.pageNo == totalPage || totalPage == 0)?'disabled="disabled"':'') +'/>';
			tags += '</div>';
			$(elem).find('.lj-imPaging').remove();
			$(elem).append(tags);
			this.pageEvent(elem,this,this.options,'.lj-imPaging');
		},
		pageEvent:function(elem,ui,options,btn){
			var self = this;
			$(btn).on('click','.pageBtn',function(){
				$(elem).html('');
				var params = {
					pageNo:$(this).attr('page-no'),
					pageSize:$(this).siblings('.pageSize').val()
				};
				options.success(elem,ui,params);
			});
			$(btn).on('change','.pageSize',function(){
				$(elem).html('');
				var params = {
					pageNo:1,
					pageSize:$(this).val()
				};
				options.success(elem,ui,params);
			});
			$(btn).on('blur','.pageNo',function(){
				$(elem).html('');
				var params = {
					pageNo:$(this).val(),
					pageSize:$(this).siblings('.pageSize').val()
				};
				options.success(elem,ui,params);
			});
		}
	};

	$.fn.ImPaging = function(options){
		var options = $.extend({},defaults,options);
		var imPage = new ImPage(this,options);
	}
})(jQuery);

/*
* 多图片上传
* {
*   fileId:'', 上传按钮的id
*   imgId:'', 图片列表id
*   max:'5', 最大上传张数
*   isCompress:true 是否压缩
* }
*/
function ajaxFileUpload(data) {
	var data = data || {};
	var files = document.getElementById(data.fileId).files;
	var imgCount = $(data.imgId).find('img').length;
	if(imgCount + files.length > data.max){ // 超过最大张数
		$.ConfirmBox({
			msg:'最多不能超过'+data.max+'张图',
			isCancel:false,
		});
		$('#'+data.fileId).val('');
		return;
	}

	var flag = true;

	for(var i = 0, len = files.length; i < len; i++){ // 图片类型判断
		var reg = /(jpg|jpeg|bmp|png)/ig;
		var type = files[i].type;
		if(!reg.test(type)){
			flag = false;
			$.ConfirmBox({
				msg:files[i].name + '格式不正确，只能上传jpg,jpeg,bmp,png的图片',
				isCancel:false,
			});
			$('#'+data.fileId).val('');
			break;
		}
	}
	if(!flag) return;
	
	if(data.isCompress){
		ImgCompress({
			file:files,
			success:function(base64,type){
				var base64 = base64.split(',')[1];
				var url = $('#ctx').val() + '/cm/friendsimagemateria/uploadImgString';
				var params = {
					imgFiles:base64,
					type:type
				};
				$.post(url,params,function(res){
					
					var list = res.imgAddr.split(',');
					var tags = '';
					$.each(list,function(i){
						var url = $('#UploadUrl').val() + 'im/' +list[i];
						tags += '<li data-src="'+ list[i] +'"><img src="'+ url +'" class="i"><span class="del" onclick="$(this).parent(\'li\').remove()"></span></li>';
					});
					$(data.imgId).append(tags);
					$('#'+data.fileId).val('');
				},'json');
			}
		});
	}else{
		$.ajaxFileUpload({
			url: $('#ctx').val() + '/cm/friendsimagemateria/uploadImg', //用于文件上传的服务器端请求地址
			secureuri: false, //是否需要安全协议，一般设置为false
			fileElementId: data.fileId, //文件上传域的ID
			dataType: 'json', //返回值类型 一般设置为json
			type:'post',
			data:{
				fileType: 'image'
			},
			success:function(res, status) {//服务器成功响应处理函数
				if(res.success){
					var list = res.imgAddr.split(',');
					var tags = '';
					$.each(list,function(i){
						var url = $('#UploadUrl').val() + 'im/' +list[i];
						tags += '<li data-src="'+ list[i] +'"><img src="'+ url +'" class="i"><span class="del" onclick="$(this).parent(\'li\').remove()"></span></li>';
					});
					$(data.imgId).append(tags);
					$('#'+data.fileId).val('');
				}
			}
		})
	}
}


/*
* 上传视频
* {
*   fileId:'', 上传按钮的id
*   imgId:'', 图片列表id
*   max:'5', 最大上传张数
*   isCompress:true 是否压缩
* }
*/
function ajaxVideoUpload(data) {
	var data = data || {};
	var files = document.getElementById(data.fileId).files;

     var objUrl = files[0]; 
	 if(objUrl!=undefined){
		var url = URL.createObjectURL(objUrl);
		console.log(url);
		document.getElementById("aa").src=url;
	 }
    var fileType=objUrl.type;
    var size= objUrl.size;			//文件大小
    var time="";
    $("#aa")[0].addEventListener("loadedmetadata", function(){
		var tol = this.duration;//获取总时长
    	var hour = parseInt(tol/3600);
    	var minute = parseInt((tol%3600)/60);
    	var second = Math.round(tol%60);
    	time=""+hour+minute+second;
	}); 
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
    }else {
    	
    	var ptc=setInterval(function() { 
    		window.clearInterval(ptc);
    		if(Number(time)>10){	//朋友圈视频不能大于10S
            	var msg ="朋友圈视频不能大于10秒!";
         		 options = {
        				confirm:function(){
        					n =1;
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
         		$.ConfirmBox(options);
            }else{
            	$.ajaxFileUpload({
        			url: $('#ctx').val() + '/cm/friendsimagemateria/uploadImg', //用于文件上传的服务器端请求地址
        			secureuri: false, //是否需要安全协议，一般设置为false
        			fileElementId: data.fileId, //文件上传域的ID
        			dataType: 'json', //返回值类型 一般设置为json
        			type:'post',
        			data:{
        				fileType: 'video'
        			},
        			success:function(res, status) {//服务器成功响应处理函数
        				if(res.success){
        					if(res.imgAddr){
        						var list = res.imgAddr.split(',');
            					var tags = '';
            					$.each(list,function(i){
            						var url = $('#UploadUrl').val() + 'im/' +list[i];
            						tags += '<li data-src="'+ list[i] +'"><video style="width:300px" src="'+url+'" controls="controls" ></video><span class="del" onclick="$(this).parent(\'li\').remove()"></span></li>';
            					});
            					$(data.imgId).html(tags);
            					$('#'+data.fileId).val('');
        					}
        					
        				}
        			}
        		})
            }
		}, 2000);
    }
}

;(function($){ // 弹出框
	var defaultstt = {
		confirm:function(){}, // 确定调用方法
		cancel:function(){}, // 取消调用方法
		confirmBtn:{ // 确定按钮文本和背景
			text:'确定',
			background:'#fff',
			color:'#2a2a2a'
		},
		cancelBtn:{ // 取消按钮文本和背景
			text:'取消',
			background:'#00b204',
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
			tags +=	'<span class="text-center promitConfirmButton" style="width: 130px;height: 40px; cursor:pointer; border: 1px solid #d1d1d1;border-radius: 5px;color:'+ options.confirmBtn.color +';background: '+ options.confirmBtn.background +'">'+options.confirmBtn.text +'</span>';
			if(options.isCancel){ // 是否有取消按钮
				tags +='<span class="text-center promitCancleButton" style="width: 130px;height: 40px; cursor:pointer; border: 1px solid #d1d1d1;border-radius: 5px;color: '+ options.cancelBtn.color +';background: '+ options.cancelBtn.background +'">'+ options.cancelBtn.text +'</span>';
			}
			tags +=	'</div></div></div>';
			return tags;
		}
	};

	$.ConfirmBox = function(options){
		var options = $.extend({},defaultstt,options);
		new ConfirmRe(options);
	};
})(jQuery);


$.ajaxSetup({  
    type: 'POST',  
    complete: function(xhr,status) { 
        var sessionStatus = xhr.getResponseHeader('sessionstatus');  
        if(sessionStatus == 'timeout') {  
            var top = getTopWinow();  
            var yes = confirm('未登录或登录超时。请重新登录，谢谢！');  
            if (yes) {  
                top.location.href = '${ctx}/';              
            }  
        }  
    }  
}); 
/** 
 * 在页面中任何嵌套层次的窗口中获取顶层窗口 
 * @return 当前页面的顶层窗口对象 
 */  
function getTopWinow(){  
    var p = window;  
    while(p != p.parent){  
        p = p.parent;  
    }  
    return p;  
} 

/* 选择图生成转换成base64 */
;(function(){
	var defaults = {
		maxWidth:1000, // 图片在的最大宽度 = 最大高度
		opacity:1, // 图片转化的清晰度
		type:'image/jpeg', // 图片类型
	};
	var ImgCompress = function(options){
		var options = Object.assign(defaults,options);
		ImgCompress.prototype.init(options);
	};

	ImgCompress.prototype = {
		init:function(options){
			var self = this;
			var canvas = this.createCanvas();
			var file = options.file;
			
			for(var i = 0; i<file.length; i++){
				self.base64(file[i],canvas,options,function(data){
					options.success(data);
				});
			}
		},
		createCanvas:function(){ // create canvas
			var canvas = document.getElementById('ImgCompressCanvas');
			if(!canvas){
				var canvasTag = document.createElement('canvas');
				canvasTag.setAttribute('id','ImgCompressCanvas');
				canvasTag.setAttribute('style','display:none;');
				document.body.appendChild(canvasTag);
				canvas = document.getElementById('ImgCompressCanvas');
			}
			return canvas;
		},
		imgScaleW:function(maxWidth,width,height){
			var imgScale = {};
			var w = 0;
			var h = 0;
			//If the width of the picture is less than the maximum value of the limit, it does not need to scale
			if(width <= maxWidth && height <= maxWidth){
				imgScale = {
					width:width,
					height:height
				}
			}else{
				//  If the picture width is greater than the height
				if(width >= height){

					w = maxWidth;
					h = Math.ceil(maxWidth * height / width);
				}else{
					h = maxWidth;
					w = Math.ceil(maxWidth * width / height);
				}
				imgScale = {
					width:w,
					height:h
				}
			}
			return imgScale;
		},
		base64:function(file,canvas,options,callback){
			var type = file.type.replace(/image\//,'') == 'png' ? 'png': 'jpg';
			var reader = new FileReader();
			var image = new Image();
			var canvas = canvas;
			var ctx = canvas.getContext("2d");
			var self = this;
			reader.onload = function(){ // file loaded
				var result = this.result;
				image.onload = function(){ // image loaded
					var imgScale = self.imgScaleW(options.maxWidth,this.width,this.height);

					canvas.width = imgScale.width;
					canvas.height = imgScale.height;
					ctx.drawImage(image,0,0,imgScale.width,imgScale.height);
					var dataURL = canvas.toDataURL(type,options.opacity); // image base64
					ctx.clearRect(0,0,imgScale.width,imgScale.height); // clear context
					callback(dataURL,type);
				};
				image.src = result;
			};
			reader.readAsDataURL(file);
		},
		changeEvent:function(id,callback){
			id.addEventListener('change',callback,false);
		},
		$:function(id){
			return document.getElementById(id);
		}
	}
	window.ImgCompress = ImgCompress;
})();

//粘贴事件监控
$.fn.pasteEvents = function( delay ) {
    if (delay == undefined) delay = 10;
    return $(this).each(function() {
        var $el = $(this);
        $el.on("paste", function() {
            $el.trigger("prepaste");
            setTimeout(function() { $el.trigger("postpaste"); }, delay);
        });
    });
};
//粘贴的使用
$("#txtContent").on("postpaste", function(event) { 
	var text=$.trim(event.target.innerText);
	$("#txtContent").html(text.replace(/(\r)?\n/g,'<br/>'));
	setFocus($("#txtContent"));
}).pasteEvents();

function setFocus(el) {
    el = el[0]; // jquery 对象转dom对象  
    el.focus();
    var range = document.createRange();
    range.selectNodeContents(el);
    range.collapse(false);
    var sel = window.getSelection();
    //判断光标位置，如不需要可删除
    if(sel.anchorOffset!=0){
        return;
    };
    sel.removeAllRanges();
    sel.addRange(range);
};

document.ondragstart = function() {
    return false;
};

function allArtic(ev,num){
	if(num==1){	//全文
		$(ev).prev().css("max-height","none");
		$(ev).attr("class","hidden");
		$(ev).next().attr("class","show");
	}else{	//关闭
		$(ev).prev().prev().css("max-height","154px");
		$(ev).attr("class","hidden");
		$(ev).prev().attr("class","show");
	}
	
}

//当前导购助手下未回复客户数
function codefans(){
	$.ajax({
		type:"POST",
		url:$('#ctx').val() + "/im/findUnrespMemberCount.do",
		data:{},
	    dataType:'json',
	    success:function(re){
	    	console.log(re);
	    	if(re>0){
	    		var html = '<div class="popUp"><div class="popUpTips text-center">温馨提醒</div>';
		    	html += '<div class="popUpImg text-center"> <img src="'+$("#ctxStatic").val()+'/admin/images/imImages/wxTips.png" /></div>';
		    	html += '<div class="popUpMsg"> <p class="tip1">';
		    	html += '有'+re+'位客户信息未回复，请及时回复。</p><p style="font-size: 12px;">（与客户沟通请保证最后一条消息是客服回复的）</p></div>';
		    	html += '<div class="popUpClose text-center" onclick="$(\'.popUp\').hide()">';
		    	html += '<img src="'+$("#ctxStatic").val()+'/admin/images/imImages/close.png" /></div></div>';
	    		var flag=$(".popUp").length;
	    		if(flag>0){
	    			$(".popUp").remove();
	    			$("body").append(html);
	    		}else{
	    			$("body").append(html);
	    		}
	    	}
	    }
	});
}

/*主页*/
function ImIndex(){
	window.localStorage.removeItem("chatstart");
	window.localStorage.removeItem("hisData");
	window.localStorage.removeItem("guidechatstart");
	window.localStorage.removeItem("chatTimeEnd");
	window.localStorage.removeItem("GchatTimeEnd");
	window.location.href = localStorage.getItem("indexListHref") || "list";
	var noWxGm=getQueryString("noWxGm");
	var memberNo=getQueryString("memberNo");
}

/**
 * 加载头像-默认图
 * 是否包含http前缀
 * @param headAddress
 * @returns
 */
function getHeadAddress(headAddress){
	var url = '';
	if(headAddress){
		url = headAddress.indexOf('http') != -1 ? headAddress : ($('#UploadUrl').val() + headAddress);
	}else{
		url = $('#UploadUrl').val() + $('#ctxStatic').val() + '/admin/images/introduce/file.png';
	}
	return url;
}
